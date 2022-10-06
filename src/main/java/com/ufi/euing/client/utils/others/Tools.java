package com.ufi.euing.client.utils.others;

import com.ufi.euing.client.business.*;
import com.ufi.euing.client.entity.*;
import com.ufi.euing.client.exceptions.domain.EmailNotFoundException;
import com.ufi.euing.client.props.EuingProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.List;

@Component
public class Tools {

    private static final Logger LOGGER = LoggerFactory.getLogger(Tools.class);

    final EuingProperties euingProperties;

    final GuichetService guichetService;

    final PaysService paysService;

    final HoraireService horaireService;

    final SenderService senderService;

    final ParametreBaseService parametreBaseService;

    final CompagnieServiceSmsService compagnieServiceSmsService;

    final CompagnieTPIService compagnieTPIService;

    final UtilisateurService utilisateurService;

    final TransactionEuingService transactionEuingService;

    final ServiceService serviceService;

    final BeneficiaryService beneficiaryService;

    final CompagnieService compagnieService;

    final TypePieceIdentiteService typePieceIdentiteService;

    public Tools(EuingProperties euingProperties,
                 GuichetService guichetService,
                 PaysService paysService,
                 HoraireService horaireService, SenderService senderService,
                 ParametreBaseService parametreBaseService,
                 CompagnieServiceSmsService compagnieServiceSmsService, CompagnieTPIService compagnieTPIService,
                 UtilisateurService utilisateurService,
                 TransactionEuingService transactionEuingService,
                 ServiceService serviceService,
                 BeneficiaryService beneficiaryService,
                 CompagnieService compagnieService,
                 TypePieceIdentiteService typePieceIdentiteService) {
        this.euingProperties = euingProperties;
        this.guichetService = guichetService;
        this.paysService = paysService;
        this.horaireService = horaireService;
        this.senderService = senderService;
        this.parametreBaseService = parametreBaseService;
        this.compagnieServiceSmsService = compagnieServiceSmsService;
        this.compagnieTPIService = compagnieTPIService;
        this.utilisateurService = utilisateurService;
        this.transactionEuingService = transactionEuingService;
        this.serviceService = serviceService;
        this.beneficiaryService = beneficiaryService;
        this.compagnieService = compagnieService;
        this.typePieceIdentiteService = typePieceIdentiteService;
    }

    List<ParametreBase> listParametreSMS = new ArrayList<>();

    /**
     * Allow to sign a message with a secret code using HMACSHA1 Algorithm
     * @param message - message to sign
     * @param secret - secret use to sign
     * @return - a sign data
     * @throws NoSuchAlgorithmException - throw when api didn't find algorithm
     * @throws InvalidKeyException - throw when invalidKey founded
     */
    public String sign(String message, String secret) throws NoSuchAlgorithmException, InvalidKeyException {
        SecretKeySpec signingKey = new SecretKeySpec(secret.getBytes(), "HmacSHA1");
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(signingKey);
        return hexToString(mac.doFinal(message.getBytes()));
    }


    /**
     * Allow to convert an hexadecimal data to String
     * @param bytes - hexadecimal data
     * @return - String to return
     */
    private String hexToString(byte[] bytes) {
        try(Formatter formatter = new Formatter();) {
            for (byte b : bytes) {
                formatter.format("%02x", b);
            }
            return formatter.toString();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }


    public static String encodePassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }


    public synchronized MessageApi addMobileCashIn(TransactionDetailsEntity transDetails) throws EmailNotFoundException {
        boolean b = false;
        MessageApi mess = new MessageApi();
        TransactionEuing trx = new TransactionEuing();
        Guichet gui = guichetService.getById(euingProperties.getGuichetCode());
        Utilisateur usr = utilisateurService.find(euingProperties.getUtilisateurCode());
        boolean terminar = false;
        GenericsResponse responseSearchctpi = compagnieTPIService.findCompagnieTpiByTpiComagnie(gui.getAgence().getCompagnie().getId(), transDetails.getSenderIdDetails().getCodeTypePiece());
        int lengthIdDocument = 0;
        if (responseSearchctpi.getResponseCode() == 200) {
            CompagnieTpi res = (CompagnieTpi) responseSearchctpi.getData();
            int maxLength = (res.getMaxLength() == null) ? 0 : res.getMaxLength();
            int minLength = (res.getMinLength() == null) ? 0 : res.getMinLength();
            if (transDetails.getSenderIdDetails().getId1Number() == null) {
                lengthIdDocument = 10;
            } else {
                lengthIdDocument = transDetails.getSenderIdDetails().getId1Number().trim().length();
            }
            if (lengthIdDocument < minLength || lengthIdDocument > maxLength) {
                //message = "Erreur de SOLDE";
                mess.setMessageCode("305");
                mess.setMessageDescription("Longueur Piece Identité incorrecte");
                terminar = true;
            }
        } else {
            mess.setMessageCode(responseSearchctpi.getResponseCode() + "");
            mess.setMessageDescription(responseSearchctpi.getResponseDescription());
            terminar = true;
        }
        Sender sender = new Sender();
        if (!terminar) {

            sender.setSenFirstname(transDetails.getSenderDetails().getFirstName());
            sender.setSenLastname(transDetails.getSenderDetails().getLastName());
            sender.setSenPhoneNumber1(transDetails.getSenderDetails().getMobileNumber());
            sender.setSenPhoneNumber2(transDetails.getSenderDetails().getPhoneNumber());
            sender.setSenAddress(transDetails.getSenderDetails().getAddress1());
            sender.setSenAddress2(transDetails.getSenderDetails().getAddress2());
            sender.setSenEmail(transDetails.getSenderDetails().getEmail());
            sender.setSenPostalCode(transDetails.getSenderDetails().getPostCode());
            sender.setSenGender(transDetails.getSenderIdDetails().getGender());

            Ville villes = new Ville();
            /// on prend la ville du guichetier.
            System.out.println("===== Ville Sender === " + transDetails.getSenderDetails().getCity());
            //villes = ejbVil.find(transDetails.getSenderDetails().getCity());
            villes = gui.getAgence().getVille();
            sender.setSenCityId(villes);
            sender.setSenCity(villes.getViLibelle());

            Pays ps = new Pays();
            System.out.println("===== Pays Sender === " + transDetails.getSenderDetails().getCountry());
            ps = paysService.find(transDetails.getSenderDetails().getCountry());

            sender.setSenCountryId(ps);
            sender.setSenCountry(ps.getPsLibelle());
            sender.setSenCountryResId(ps);
            sender.setSenCountryRes(ps.getPsLibelle());

            Pays psn = new Pays();
            System.out.println("===== Pays Sender Nationalité === " + transDetails.getSenderIdDetails().getNationality());
            psn = paysService.find(transDetails.getSenderIdDetails().getNationality());
            sender.setSenNationality(psn.getPsLibelle());
            sender.setSenNationalityId(psn);
            try {
                sender.setSenIdDocumentDelDate(new Date(transDetails.getSenderIdDetails().getDateOfIssue()));
                sender.setSenIdDocumentExpDate(new Date(transDetails.getSenderIdDetails().getDateOfExpiry()));
                sender.setSenDob(new Date(transDetails.getSenderIdDetails().getDateOfIssue()));
            } catch (Exception ex) {
                System.out.println("================= Date incorrecte =======================");
                mess.setMessageCode("204");
                mess.setMessageDescription("Transaction echouée, date incorrecte !!!! ");
                mess.setSendCode("//");
                System.out.println("Message ==== " + mess.toString());
                terminar = true;
            }
            sender.setSenIdDocumentNumber(transDetails.getSenderIdDetails().getId1Number());
            //sender.setSenIdDocumentNumber(transDetails.getReference());
            sender.setSenIdDocumentIssueCountry(paysService.find(transDetails.getSenderIdDetails().getCountryOfIssue()));

            sender.setSenIdDocumentType(typePieceIdentiteService.find(transDetails.getSenderIdDetails().getCodeTypePiece()));
            sender.setSenOccupation(transDetails.getSenderIdDetails().getOccupation());
            //sender.setSenDob(new Date(transDetails.getSenderIdDetails().getDateOfIssue()));
            sender.setSenState(transDetails.getSenderIdDetails().getNationality());
            if (!terminar) {
                GenericsResponse response = senderService.createSender(sender);

                if (response.getResponseCode() == 200) {
                    Sender res = (Sender) response.getData();
                    sender = res;
                    //this.setCodeSender("" + res.getSenCode());
                    //JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/messages_fr").getString("SenderSavedSuccessfully"));
                } else {
                    //JsfUtil.addErrorMessage(response.getResponseCode() + " - " + response.getResponseDescription());
                    mess.setMessageCode(response.getResponseCode() + "");
                    mess.setMessageDescription(response.getResponseDescription());
                    terminar = true;
                }
            }
        }
        Beneficiary ben = new Beneficiary();

        if (!terminar) {
            // Enregistrement du bénéficiaire
            // this.setForeignFieldBenef();
            ben.setBenFirstname(transDetails.getReceiverDetails().getFirstName());
            ben.setBenLastname(transDetails.getReceiverDetails().getLastName());
            ben.setBenPhoneNumber1(transDetails.getReceiverDetails().getMobileNumber());
            ben.setBenPhoneNumber2(transDetails.getReceiverDetails().getPhoneNumber());
            ben.setBenAddress(transDetails.getReceiverDetails().getAddress1());
            ben.setBenAddress2(transDetails.getReceiverDetails().getAddress2());
            ben.setBenEmail(transDetails.getReceiverDetails().getEmail());
            ben.setBenPostalCode(transDetails.getReceiverDetails().getPostCode());
            ben.setBenGender("ND");

            Ville villes = new Ville();
            System.out.println("===== Ville Sender === " + transDetails.getReceiverDetails().getCity());
            // villes = ejbVil.find(transDetails.getReceiverDetails().getCity());
            villes = transactionEuingService.getCityByName("OTHERS").getData();
            ben.setBenCityId(villes);
            ben.setBenCity(villes.getViLibelle());

            Pays ps = new Pays();
            System.out.println("===== Pays Sender === " + transDetails.getReceiverDetails().getCountry());
            //ps = ejbPay.find(transDetails.getReceiverDetails().getCountry());
            ps = villes.getViZnId().getZnPsCode();
            ben.setBenCountryId(ps);
            ben.setBenCountry(ps.getPsLibelle());
            ben.setBenNationalityId(ps);
            ben.setBenNationality(ps.getPsLibelle());
            //ben.setBenCountryResId(ps);
            //ben.setBenCountryRes(ps.getPsLibelle());

            Pays psn = new Pays();
            //System.out.println("===== Pays Sender Nationalité === " + transDetails.getReceiverIdDetails().getNationality());
            // psn = ejbPay.find(transDetails.getReceiverIdDetails().getNationality());
//            psn = ps;
//            ben.setBenNationality(psn.getPsLibelle());
//            ben.setBenNationalityId(psn);

//            ben.setBenIdDocumentDelDate(new Date(transDetails.getReceiverIdDetails().getDateOfIssue()));
//            ben.setBenIdDocumentExpDate(new Date(transDetails.getReceiverIdDetails().getDateOfExpiry()));
//            ben.setBenIdDocumentNumber(transDetails.getReceiverIdDetails().getRID1Number());
//            ben.setBenIdDocumentIssueCountry(ejbPay.find(transDetails.getReceiverIdDetails().getStateOfIssue()));
//
//            ben.setBenIdDocumentType(ejbTyp.find(transDetails.getReceiverIdDetails().getCodeTypePieceRe()));
//            ben.setBenOccupation(transDetails.getReceiverIdDetails().getOccupation());
//            ben.setBenDob(new Date(transDetails.getReceiverIdDetails().getDateOfIssue()));
//            ben.setBenState(transDetails.getReceiverDetails().getState());
            GenericsResponse responseSaveBenef = beneficiaryService.createBeneficiary(sender.getSenId(), ben);

            if (responseSaveBenef.getResponseCode() == 200) {
                Beneficiary res = (Beneficiary) responseSaveBenef.getData();
                ben = res;
                //JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/messages_fr").getString("BenefSavedSuccessfully"));
            } else {
                mess.setMessageCode(responseSaveBenef.getResponseCode() + "");
                mess.setMessageDescription(responseSaveBenef.getResponseDescription());
                System.out.println("Ben Mess ==== " + mess.toString());
                terminar = true;
            }
        }

        try {
            ////////////////////////////////
            if (!checkTime(usr)) {
                mess.setMessageCode("204");
                mess.setMessageDescription("Activité non autorisée pour cette tranche d'heure");
                mess.setSendCode("//");
                System.out.println("CheckTime  Mess ==== " + mess.toString());
                terminar = true;
            }
        } catch (ParseException ex) {
            mess.setMessageCode("204");
            mess.setMessageDescription("Echec Transaction, une erreur survenue !!!!");
            mess.setSendCode("//");
            System.out.println("CheckTime Excepion Mess ==== " + mess.toString());
            //Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (!terminar) {
            trx = new TransactionEuing();

            trx.setTransSenId(sender);
            trx.setTransSenAddress(sender.getSenAddress());
            trx.setTransSenCity(sender.getSenCity());
            trx.setTransSenCountry(sender.getSenCountry());
            trx.setTransSenEmail(sender.getSenEmail());
            trx.setTransSenFirstname(sender.getSenFirstname());
            trx.setTransSenLastname(sender.getSenLastname());
            trx.setTransSenIdnumber(sender.getSenIdDocumentNumber());
            trx.setTransSenPostalCode(sender.getSenPostalCode());
            trx.setTransSenIdtype(sender.getSenIdDocumentType().getTpiCode());
            trx.setTransSenPhone(sender.getSenPhoneNumber1());

            trx.setTransBenId(ben);
            trx.setTransBenAddress(ben.getBenAddress());
            trx.setTransBenCity(ben.getBenCity());
            trx.setTransBenCountry(ben.getBenCountry());
            trx.setTransBenEmail(ben.getBenEmail());
            trx.setTransBenFirstname(ben.getBenFirstname());
            trx.setTransBenLastname(ben.getBenLastname());
            trx.setTransBenIdnumber(ben.getBenIdDocumentNumber());
            trx.setTransBenPostalCode(ben.getBenPostalCode());
            trx.setTransBenPhone(ben.getBenPhoneNumber1());
            //trx.setTransBenIdtype(ben.getBenIdDocumentType().getTpiCode());

            trx.setTransMessage("ND");
            trx.setTransMotif("ND");
            trx.setTransAmountSent(BigDecimal.valueOf(transDetails.getLocalAmount()));
            trx.setTransAmountToPaid(BigDecimal.valueOf(transDetails.getPayoutAmount()));
            trx.setTransFees(BigDecimal.valueOf(transDetails.getCustomerCharge()));
//            if (this.getRemise() == null) {
//                trx.setTransRemise(null);
//            } else {
//                trx.setTransRemise(BigDecimal.valueOf(this.getRemise()));
//            }
            trx.setTransOthers(BigDecimal.valueOf(transDetails.getTaxCharged()));
            trx.setTransTotal(BigDecimal.valueOf(transDetails.getPayoutAmount()));
            trx.setTransSens("C2C_ENVOI");
            trx.setTransCasher(usr.getUsrPrenom() + " " + usr.getUsrNom());
//            trx.setTransUserid(this.getUserLogged().getUsrId().toBigInteger());
            trx.setTransUserid(usr);
            trx.setTransInterfacage("M");
            trx.setTransStatut("E");
            trx.setTransGuichetId(gui);
            trx.setTransGuichet(gui.getLibelle());
            trx.setTransAgencyId(gui.getAgence());
            trx.setTransAgency(trx.getTransAgencyId().getLibelle());
            trx.setTransCompanyId(gui.getAgence().getCompagnie());
            trx.setTransCompany(trx.getTransCompanyId().getLibelle());
            trx.setTransGroupId(trx.getTransCompanyId().getSuccursale());
            trx.setTransGroup(trx.getTransGroupId().getLibelle());
            trx.setTransOriginCountryId(paysService.find(transDetails.getOriginatingCountry()));
            trx.setTransOriginCountry(trx.getTransOriginCountryId().getPsLibelle());
            trx.setTransOriginCur(trx.getTransOriginCountryId().getPsZmCode().getZmDevCode());
            trx.setTransDestCountryId(paysService.find(transDetails.getDestinationCountry()));
            trx.setTransDestCountry(trx.getTransDestCountryId().getPsLibelle());
            trx.setTransDestCur(trx.getTransDestCountryId().getPsZmCode().getZmDevCode());

            trx.setTransMotif("9.9.9.9.9.9");
            //transDetails.getReceiverPaymentDetails()
            trx.setTransMessage(transDetails.getReceiverPaymentDetails().getTestAnswer());

            Service ser = new Service();
            if (transDetails.getDestinationCountry().equalsIgnoreCase(gui.getAgence().getCompagnie().getPays().getPsCode())) {
                ser = serviceService.getServiceByCode("ENVOI_C2C_NAT").getData();
            } else {
                ser = serviceService.getServiceByCode("ENVOI_C2C_INTL").getData();
            }

            trx.setTransServiceId(ser);
            trx.setTransServiceName(ser.getNom());

            //this.setForeignFieldTransactions();
            GenericsResponse responseSaveTrx = transactionEuingService.createNewInterTransactionC2C(trx);

            if (responseSaveTrx.getResponseCode() == 200) {
                TransactionEuing resData = (TransactionEuing) responseSaveTrx.getData();
                mess.setMessageCode("200");
                mess.setMessageDescription("Transaction successfull !");
                mess.setSendCode(resData.getTransReference());
                sendSMSEnvoiInternationalC2C(resData, gui);
//              try {
//                    sendSMSEnvoiInternationalC2C();
//                } catch (Exception e) {
//                    System.out.println("An error occured during sending SMS transaction. Details : " + e.getMessage());
//                    e.printStackTrace();
//                }

            } else {
                mess.setMessageCode(responseSaveTrx.getResponseCode() + "");
                mess.setMessageDescription(responseSaveTrx.getResponseDescription());
                mess.setSendCode("ND");
            }
        }
        System.out.println("addMobileCashIn ==== " + mess.toString());
        return mess;
    }

    public GetTransfinQuote onPaysChanged(GetTransfinQuoteRequest requete) throws Exception {
        BigDecimal commissions, total, totalBeneficier;
        BigDecimal principal;
        String devisePayer, deviseSender;
        String nomService;
        Long idService;

        /*
        ENVOI_C2C_NAT
PAIEMENT_C2C_NAT
ENVOI_C2C_INTL
PAIEMENT_C2C_INTL
BILL_ELECTRICITY
PAIEMENT_C2C_INTL_OPI

         */
        GetTransfinQuote reponse = new GetTransfinQuote();
        Guichet gui = guichetService.getById(euingProperties.getGuichetCode());
        Pays po;
        Pays pd = paysService.find(requete.getDestinationCountry());
        if (gui == null) {
        } else {
            po = gui.getAgence().getCompagnie().getPays();
            requete.setOriginatingCountry(po.getPsCode());
            if (requete.getDestinationCountry().equalsIgnoreCase(requete.getOriginatingCountry())) {
                Service ser = serviceService.getServiceByCode("ENVOI_C2C_NAT").getData();
                idService = ser.getId();
                nomService = ser.getNom();
            } else {
                Service ser = serviceService.getServiceByCode("ENVOI_C2C_INTL").getData();
                idService = ser.getId();
                nomService = ser.getNom();
            }

            System.out.println(" Mode  === > " + gui.getAgence().getCompagnie().getModeCalculPartner());
            GenericsResponse fee = transactionEuingService.calculTarifInterTransaction(gui.getAgence().getCompagnie().getId(), gui.getAgence().getCompagnie().getLibelle(), idService, nomService, requete.getDestinationCountry(), requete.getDestinationCountry(), gui.getAgence().getCompagnie().getModeCalculPartner(), requete.getLocalAmount(), new BigDecimal(0), po.getPsZmCode().getZmDevCode().getDevCode(), pd.getPsZmCode().getZmDevCode().getDevCode());
            CalculTarifInternational a = (CalculTarifInternational) fee.getData();
            // System.out.println("Fee ==== > "+a.toString());
            if (fee.getResponseCode() == 200) {
                System.out.println("=========== Talla ======");
                CalculTarifInternational c = (CalculTarifInternational) fee.getData();
                reponse.setOriginatingCountry(po.getPsCode() + ":" + po.getPsLibelle());
                reponse.setDestinationCountry(requete.getDestinationCountry());
                reponse.setOriginatingCurrency(po.getPsZmCode().getZmDevCode().getDevCode());
                reponse.setDestinationCurrency(pd.getPsZmCode().getZmDevCode().getDevCode());
                reponse.setLocalAmount(c.getAmountSent());
                ///reponse.setPayoutAmount(c.getAmountSent());
                reponse.setCustomerCharge(c.getFees());
                reponse.setTaxCharged(c.getOthersFees());
                reponse.setExchangeRate(c.getExchangeRate());
                reponse.setCashTellerReceiver(c.getAmountToPaid());
                // edite by jb 01022022
                reponse.setPayoutAmount(c.getAmountToPaid());
            } else {
                System.out.println("James ==== " + fee.getResponseDescription());
            }
        }

        return reponse;
    }

    private void sendSMSEnvoiInternationalC2C(TransactionEuing trx, Guichet gui) {
        listParametreSMS = parametreBaseService.findParametreSMS();
        CompagnieServiceSms css = compagnieServiceSmsService.findByCompagnieAndService(gui.getAgence().getCompagnie().getId(), trx.getTransServiceId().getId());
        if (css != null) {
            css.setFormatSms(unaccent(css.getFormatSms()));
            String compagnie = gui.getAgence().getCompagnie().getLibelle();
            String reference = trx.getTransReference();
            if (css.getNotificationFlag() == 1) { // envoi du sms juste à l'expediteur
                String phoneSender = trx.getTransSenId().getSenPhoneNumber1();
                String civile = ("0".equals(trx.getTransSenId().getSenGender())) ? "M/Mme" : ("F".equals(trx.getTransSenId().getSenGender())) ? "Mme" : trx.getTransSenId().getSenGender();
                String nom = trx.getTransSenId().getSenLastname() + " " + trx.getTransSenId().getSenFirstname();

                String message = css.getFormatSms()
                        .replaceAll("p_civilite", civile)
                        .replaceAll("p_nom", nom)
                        .replaceAll("p_cmp", compagnie)
                        .replaceAll("p_reference", reference);

                SendSMSRunnable smsrunnable = new SendSMSRunnable();
                smsrunnable.setParameters(listParametreSMS, message, phoneSender);
                Thread t = new Thread(smsrunnable);
                t.start();
            } else if (css.getNotificationFlag() == 2) { // envoi sms juste au beneficiaire
                String phoneBenef = trx.getTransBenId().getBenPhoneNumber1();
                String civile = ("0".equals(trx.getTransBenId().getBenGender())) ? "M/Mme" : ("F".equals(trx.getTransBenId().getBenGender())) ? "Mme" : trx.getTransBenId().getBenGender();
                String nom = trx.getTransBenId().getBenLastname() + " " + trx.getTransBenId().getBenFirstname();

                String message = css.getFormatSms()
                        .replaceAll("p_civilite", civile)
                        .replaceAll("p_nom", nom)
                        .replaceAll("p_cmp", compagnie)
                        .replaceAll("p_reference", reference);

                SendSMSRunnable smsrunnable = new SendSMSRunnable();
                smsrunnable.setParameters(listParametreSMS, message, phoneBenef);
                Thread t = new Thread(smsrunnable);
                t.start();
            } else if (css.getNotificationFlag() == 3) { // envoi sms à l'expediteur et au beneficiaire
                String phoneSender = trx.getTransSenId().getSenPhoneNumber1();
                String civile = ("0".equals(trx.getTransSenId().getSenGender())) ? "M/Mme" : ("F".equals(trx.getTransSenId().getSenGender())) ? "Mme" : trx.getTransSenId().getSenGender();
                String nomSender = trx.getTransSenId().getSenLastname() + " " + trx.getTransSenId().getSenFirstname();

                String message = css.getFormatSms()
                        .replaceAll("p_civilite", civile)
                        .replaceAll("p_nom", nomSender)
                        .replaceAll("p_cmp", compagnie)
                        .replaceAll("p_reference", reference);
                SendSMSRunnable smsrunnable = new SendSMSRunnable();
                smsrunnable.setParameters(listParametreSMS, message, phoneSender);
                Thread t = new Thread(smsrunnable);
                t.start();

                String phoneBenef = trx.getTransBenId().getBenPhoneNumber1();
                String civileBenef = ("0".equals(trx.getTransBenId().getBenGender())) ? "M/Mme" : ("F".equals(trx.getTransBenId().getBenGender())) ? "Mme" : trx.getTransBenId().getBenGender();
                String nomBenef = trx.getTransBenId().getBenLastname() + " " + trx.getTransBenId().getBenFirstname();

                String messageBenef = css.getFormatSms()
                        .replaceAll("p_civilite", civileBenef)
                        .replaceAll("p_nom", nomBenef)
                        .replaceAll("p_cmp", compagnie)
                        .replaceAll("p_reference", reference);

                SendSMSRunnable smsrunnable2 = new SendSMSRunnable();
                smsrunnable2.setParameters(listParametreSMS, messageBenef, phoneBenef);
                Thread t2 = new Thread(smsrunnable2);
                t2.start();
            }
        } else {
        }
    }

    public static String unaccent(String src) {
        return Normalizer
                .normalize(src, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "");
    }


    public boolean checkTime(Utilisateur usr) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        if (usr.getUsrUoId() != 0) {
            GenericsResponse res = horaireService.findHoraireByDayTypeUoUoId(BigInteger.valueOf(date.getDay()), usr.getUsrTypeUo(), BigInteger.valueOf(usr.getUsrUoId()));
            if (res.getResponseCode() == 200) {
                Horaire hor = (Horaire) res.getData();
                Date heureConnexion = sdf.parse(sdf.format(new Date()));
                Date debut = sdf.parse(hor.getFormatteddebut());
                Date fin = sdf.parse(hor.getFormattedfin());
                if ((heureConnexion.compareTo(debut) >= 0) && (heureConnexion.compareTo(fin) <= 0)) {
                    return true;
                }
            }
        }
        return false;
    }
}

