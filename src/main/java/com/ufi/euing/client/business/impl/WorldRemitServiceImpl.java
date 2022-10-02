package com.ufi.euing.client.business.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufi.euing.client.business.*;
import com.ufi.euing.client.entity.*;
import com.ufi.euing.client.integration.juba.model.JubaUtil;
import com.ufi.euing.client.integration.worldremit.model.MarkPaidTransaction;
import com.ufi.euing.client.integration.worldremit.model.WorldRemitUtil;
import com.ufi.euing.client.integration.worldremit.response.WRTransactionResponse;
import com.ufi.euing.client.integration.worldremit.response.WRTransactionUpdateResponse;
import com.ufi.euing.client.integration.worldremit.service.WorldRemitCallApi;
import com.ufi.euing.client.repositories.GuichetRepository;
import com.ufi.euing.client.repositories.ParametreBaseRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

@Service
public class WorldRemitServiceImpl implements WorldRemitService {

    final ParametreBaseService parametreBaseService;

    final ParametrewsService parametrewsService;

    final TransactionEuingService transactionEuingService;

    final UtilisateurService utilisateurService;

    final SenderService senderService;

    final BeneficiaryService beneficiaryService;

    final GuichetService guichetService;

    final GuichetRepository guichetRepository;

    final ParametreBaseRepository parametreBaseRepository;

    final ServiceService serviceService;

    final PaysService paysService;

    final TypePieceIdentiteService typePieceIdentiteService;

    final ListToUseService listToUseService;

    public WorldRemitServiceImpl(ParametreBaseService parametreBaseService,
                                 ParametrewsService parametrewsService,
                                 TransactionEuingService transactionEuingService,
                                 UtilisateurService utilisateurService,
                                 SenderService senderService,
                                 BeneficiaryService beneficiaryService,
                                 GuichetService guichetService,
                                 GuichetRepository guichetRepository,
                                 ParametreBaseRepository parametreBaseRepository,
                                 ServiceService serviceService,
                                 PaysService paysService,
                                 TypePieceIdentiteService typePieceIdentiteService,
                                 ListToUseService listToUseService) {
        this.parametreBaseService = parametreBaseService;
        this.parametrewsService = parametrewsService;
        this.transactionEuingService = transactionEuingService;
        this.utilisateurService = utilisateurService;
        this.senderService = senderService;
        this.beneficiaryService = beneficiaryService;
        this.guichetService = guichetService;
        this.guichetRepository = guichetRepository;
        this.parametreBaseRepository = parametreBaseRepository;
        this.serviceService = serviceService;
        this.paysService = paysService;
        this.typePieceIdentiteService = typePieceIdentiteService;
        this.listToUseService = listToUseService;
    }

    @Override
    public GenericsResponse<TransactionDetailsEntity> doGetTransaction(CriterialSearchPayment criterial) {
        GenericsResponse<TransactionDetailsEntity> response;
        try {
            GenericsResponse<Parametrews> p = parametrewsService.getParametrewsByCode("WorldRemit");
            if (p.getResponseCode() == 200) {
                Parametrews pws = p.getT();
                String param = (WorldRemitUtil.WR_URI_GET_TRANS_BY_TRANS_NUM).replace("{param}", criterial.getReference());
                String url = pws.getPwsWebServiceUrl() + pws.getPwsMyagentIdPartner() + pws.getPwsMykeyPartner() + param;
                System.out.println("url doGetWorldRemitTransancation ::: " + url);

                WorldRemitCallApi worldRemitCallApi = new WorldRemitCallApi();
                String result = worldRemitCallApi.callApiGenericWorldRemit(WorldRemitUtil.WR_URI_REQUEST_METHOD_GET, pws.getLogin(), pws.getPassword(), url, null);
                System.out.println("result ::: " + result);

                WRTransactionResponse transactionResponse = new WRTransactionResponse();
                if (transactionResponse.getResponseCode() == 200) {
                    if (transactionResponse.getStatus().equalsIgnoreCase("Authorized") || transactionResponse.getStatus().equalsIgnoreCase("A")) {
                        TransactionDetailsEntity trx = doBuildtransactionEntity(transactionResponse, criterial.getUserSession());
                        System.out.println("trx = " + trx);
                        response = new GenericsResponse(trx);
                    } else {
                        response = new GenericsResponse(500, "Remittance " + criterial.getReference() + " Status = " + transactionResponse.getStatus(), null);
                    }
                } else {
                    response = new GenericsResponse(500, "Error doGetWorldRemit ::: " + transactionResponse.getResponseDesc() + " code = " + transactionResponse.getResponseCode(), null);
                }
            } else {
                response = new GenericsResponse(p.getResponseCode(), "Error doGetTransaction param ws " + p.getResponseDescription(), null);
            }
        } catch (Exception e) {
            System.out.println("Exception doGetTransaction ::: " + e.getMessage());
            response = new GenericsResponse(500, "Exception doGetTransaction ::: " + e.getMessage(), null);
        }
        return response;
    }

    @Override
    public GenericsResponse<TransactionEuing> doPayTransaction(TransactionDetailsEntity trxEntity) {
        GenericsResponse<TransactionEuing> response;
        try {
            GenericsResponse<Parametrews> p = parametrewsService.getParametrewsByCode("JUBA");
            if (p.getResponseCode() == 200) {
                Parametrews pws = p.getT();
                GenericsResponse<Boolean> lock = this.doUpdateWorldRemitTransancation(1, trxEntity.getTransactionNumber(), pws);
                if (lock.getResponseCode() == 200) {
                    GenericsResponse<Boolean> pay = this.doUpdateWorldRemitTransancation(3, trxEntity.getTransactionNumber(), pws);
                    if (pay.getResponseCode() == 200) {
                        //build transaction
                        TransactionEuing trx = doBuildtransactionForPayment(trxEntity, pws);
                        response = new GenericsResponse(trx);
                    } else {
                        //response = new GenericsResponse(pay.getResponseCode(), "Error doPaymentConfirmation param ws " + pay.getResponseDescription(), null);
                        GenericsResponse<Boolean> unlock = this.doUpdateWorldRemitTransancation(1, trxEntity.getTransactionNumber(), pws);
                        if (unlock.getResponseCode() == 200) {
                            response = new GenericsResponse(204, "Transaction " + trxEntity.getTransactionNumber() + " unlocked", null);
                        } else {
                            response = new GenericsResponse(unlock.getResponseCode(), "Error doPaymentConfirmation param ws " + unlock.getResponseDescription(), null);
                        }
                    }
                } else {
                    response = new GenericsResponse(lock.getResponseCode(), "Error doPaymentConfirmation param ws " + lock.getResponseDescription(), null);
                }
            } else {
                response = new GenericsResponse(p.getResponseCode(), "Error doPaymentConfirmation param ws " + p.getResponseDescription(), null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception doPaymentConfirmation ::: " + e.getMessage());
            response = new GenericsResponse(500, "Exception doPaymentConfirmation ::: " + e.getMessage(), null);
        }
        return response;
    }


    TransactionDetailsEntity doBuildtransactionEntity(WRTransactionResponse t, Long usrId) {
        TransactionDetailsEntity trx = new TransactionDetailsEntity();
        try {
            trx.setTransactionNumber(t.getWr_transaction_id());
            trx.setTypeTransaction(2);
            trx.setUserSession(usrId);
            trx.setPartnerTransactionNo("//");
            trx.setCodeTransaction("//");
            trx.setStatusCode("V");
            trx.setStatusDescription("Authorized");
            trx.setTransactionDate("//");

            SenderTransfinEntity senderDetails = new SenderTransfinEntity();
            senderDetails.setFirstName(t.getSender_first_name());
            senderDetails.setLastName(t.getSender_last_name());
            senderDetails.setMobileNumber("N/A");
            senderDetails.setCity((t.getSender_city().isEmpty()) ? "N/A" : t.getSender_city());
            senderDetails.setEmail("N/A");

            SenderTransfinIdEntity senderIdDetails = new SenderTransfinIdEntity();
            TypePieceIdentite tpi = new TypePieceIdentite();
            senderIdDetails.setCodeTypePiece("N/A");
            senderIdDetails.setId1Number("N/A");
            senderIdDetails.setDateOfIssue("N/A");
            senderIdDetails.setDateOfExpiry("N/A");
            senderIdDetails.setCountryOfIssue("N/A");
            trx.setSenderIdDetails(senderIdDetails);

            GenericsResponse paysSenderRes = paysService.findPaysByCodeIso(t.getSender_country());
            if (paysSenderRes.getResponseCode() == 200) {
                Pays paysSender = (Pays) paysSenderRes.getData();
                trx.setOriginatingCountry(paysSender.getPsCode());
                trx.setOriginatingCurrency(paysSender.getPsZmCode().getZmDevCode().getDevCode());
                trx.setOriginatingTown((t.getSender_city().isEmpty()) ? "N/A" : t.getSender_city());

                senderDetails.setAddress1(paysSender.getPsLibelle());
                senderDetails.setCountry(paysSender.getPsLibelle());
            } else {
                trx.setOriginatingCountry("//");
                trx.setOriginatingCurrency("//");
                trx.setOriginatingTown("//");
                System.out.println("Error le pays " + t.getSender_country() + " de l'expediture n'existe pas");
            }
            trx.setSenderDetails(senderDetails);

            GenericsResponse paysBenefRes = paysService.findPaysByCodeIso(t.getReceiver_country());
            if (paysBenefRes.getResponseCode() == 200) {
                Pays paysBenef = (Pays) paysBenefRes.getData();
                trx.setDestinationCountry(paysBenef.getPsCode());
                trx.setDestinationTown(t.getReceiver_city());
                trx.setDestinationCurrency(paysBenef.getPsZmCode().getZmDevCode().getDevCode());
            } else {
                trx.setDestinationCountry("//");
                trx.setDestinationTown("//");
                trx.setDestinationCurrency("//");
                System.out.println("Error le pays " + t.getReceiver_country() + " du beneficaire n'existe pas");
            }

            trx.setReference(t.getWr_transaction_id());
            trx.setLocalAmount(Double.valueOf(t.getPayout_amount()));
            trx.setPayoutAmount(Double.valueOf(t.getPayout_amount()));

            ReceiverTransfinEntity receiverDetails = new ReceiverTransfinEntity();
            receiverDetails.setFirstName(t.getReceiver_first_name());
            receiverDetails.setLastName((t.getReceiver_middle_name().isEmpty()) ? t.getReceiver_last_name() : t.getReceiver_middle_name() + " " + t.getReceiver_last_name());
            /*receiverDetails.setAddress1(t.getReceiver_address_1());
            receiverDetails.setMobileNumber(t.getReceiver_mobile_number());
            receiverDetails.setCountry(t.getReceiver_country());
            receiverDetails.setCity(t.getReceiver_city());
            receiverDetails.setEmail(t.getReceiver_email_id());*/
            trx.setReceiverDetails(receiverDetails);

            ReceiverTransfinPayEntity receiverPaymentDetails = new ReceiverTransfinPayEntity();
            receiverPaymentDetails.setTestAnswer("//");
            receiverPaymentDetails.setTestQuestion("//");
            receiverPaymentDetails.setDataForServices("//");
            receiverPaymentDetails.setReasonForPayment("//");
            trx.setReceiverPaymentDetails(receiverPaymentDetails);

            trx.setReceiverIdDetails(new ReceiverTransfinIdEntity());

            Partenaire partenaire = new Partenaire();
            com.ufi.euing.client.entity.Service service = (serviceService.getServiceByCode(WorldRemitUtil.WR_PAY_SERVICE_CODE)).getData();
            partenaire.setCodePartenaire(service.getId() + "");
            partenaire.setDenommination(service.getNom());
            trx.setPartenaire(partenaire);

            //trx.setIdType();
        } catch (NumberFormatException e) {
            System.out.println("Error doBuildtransaction Paiement ::: " + e.getMessage());
        }
        return trx;
    }

    GenericsResponse<Boolean> doUpdateWorldRemitTransancation(int wrUpdateMode, String transId, Parametrews pws) {
        GenericsResponse<Boolean> b;
        try {
            String param;
            MarkPaidTransaction request = null;
            switch (wrUpdateMode) {
                case 1: //lock
                    param = (WorldRemitUtil.WR_URI_LOCK_TRANS_BY_TRANS_ID).replace("{param}", transId);
                    break;
                case 2: //unlock
                    param = (WorldRemitUtil.WR_URI_UNLOCK_TRANS_BY_TRANS_ID).replace("{param}", transId);
                    break;
                case 3: // pay
                    param = (WorldRemitUtil.WR_URI_MARKPAID_TRANS_BY_TRANS_ID).replace("{param}", transId);
                    request = new MarkPaidTransaction();
                    request.setOfficeId("");
                    request.setBeneficiaryIdNumber("");
                    request.setBeneficiaryIdType("");
                    break;
                case 4: // reject
                    param = (WorldRemitUtil.WR_URI_REJECT_TRANS_BY_TRANS_ID).replace("{param}", transId);
                    break;
                default: //update
                    param = (WorldRemitUtil.WR_URI_UPDATE_TRANS_BY_TRANS_ID).replace("{param}", transId);
                    break;
            }

            String url = pws.getPwsWebServiceUrl() + pws.getPwsMyagentIdPartner() + pws.getPwsMykeyPartner() + param;
            System.out.println("url doUpdateWorldRemit mode " + wrUpdateMode + " ::: " + url);

            WorldRemitCallApi worldRemitCallApi = new WorldRemitCallApi();
            String result = worldRemitCallApi.callApiGenericWorldRemit(WorldRemitUtil.WR_URI_REQUEST_METHOD_GET, pws.getLogin(), pws.getPassword(), url, request);
            System.out.println("result ::: " + result);

            ObjectMapper mapper = new ObjectMapper();
            WRTransactionUpdateResponse updateResponse = mapper.readValue(result, WRTransactionUpdateResponse.class);
            System.out.println("updateResponse = " + updateResponse);
            if (updateResponse.getCode().equalsIgnoreCase("102")) {
                b = new GenericsResponse(Boolean.TRUE);
            } else {
                b = new GenericsResponse(500, "Error doUpdateWorldRemit ::: Mode = " + wrUpdateMode + "Code = " + updateResponse.getCode() + "; Message = " + updateResponse.getCodeDescription(), Boolean.FALSE);
            }
        } catch (Exception e) {
            b = new GenericsResponse(500, "Exception doUpdateWorldRemit ::: Mode = " + wrUpdateMode + " Message: " + e.getMessage(), Boolean.FALSE);
        }
        System.out.println("response = " + b);
        return b;
    }

    TransactionEuing doBuildtransactionForPayment(TransactionDetailsEntity trxEntity, Parametrews parametrews) {
        TransactionEuing trx = new TransactionEuing();
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Utilisateur usr = utilisateurService.find(BigDecimal.valueOf(trxEntity.getUserSession()));
            com.ufi.euing.client.entity.Service service = (serviceService.getServiceByCode(JubaUtil.JUBA_PAY_SERVICE_CODE)).getData();
            Guichet guichet = guichetService.find(Long.valueOf(usr.getUsrUoId() + ""));
            Agence agence = guichet.getAgence();
            Compagnie compagnie = agence.getCompagnie();
            Succursale succursale = compagnie.getSuccursale();
            Pays destCountry = compagnie.getPays();
            Devise destDevise = destCountry.getPsZmCode().getZmDevCode();

            // sender
            Sender sender = new Sender();
            sender.setSenFirstname(trxEntity.getSenderDetails().getFirstName());
            sender.setSenLastname(trxEntity.getSenderDetails().getLastName());
            GenericsResponse paysSenderRes = paysService.findPaysByCodeIso(trxEntity.getSenderDetails().getCountry());
            if (paysSenderRes.getResponseCode() == 200 && paysSenderRes.getData() != null) {
                Pays paysSender = (Pays) paysSenderRes.getData();
                sender.setSenNationalityId(paysSender);
                sender.setSenNationality(paysSender.getPsLibelle());
                sender.setSenAddress(trxEntity.getSenderDetails().getCity() + ", " + paysSender.getPsLibelle());
                sender.setSenCountryResId(paysSender);
                sender.setSenCountryRes(paysSender.getPsLibelle());
                sender.setSenCountryId(paysSender);
                sender.setSenCountry(paysSender.getPsLibelle());
                sender.setSenIdDocumentIssueCountry(paysSender);
                trx.setTransOriginCur(paysSender.getPsZmCode().getZmDevCode()); //Origin Currency transaction
            } else {
                System.out.println("Error le pays " + trxEntity.getSenderDetails().getCountry() + " de l'expediture.");
            }
            sender.setSenGender((trxEntity.getSenderIdDetails().getGender() == null) ? "0" : trxEntity.getSenderIdDetails().getGender());
            sender.setSenAddress(trxEntity.getSenderDetails().getAddress1());
            sender.setSenAddress2(trxEntity.getSenderDetails().getAddress2());
            sender.setSenDob(("".equals(trxEntity.getSenderIdDetails().getDateOfBirth())) ? null : new SimpleDateFormat("dd/MM/yyyy").parse(trxEntity.getSenderIdDetails().getDateOfBirth()));
            sender.setSenPostalCode(trxEntity.getSenderDetails().getPostCode());
            TypePieceIdentite tpi = new TypePieceIdentite();
            sender.setSenIdDocumentNumber(trxEntity.getSenderIdDetails().getId1Number());
            sender.setSenIdDocumentType(tpi);
            sender.setSenIdDocumentDelDate(("".equals(trxEntity.getSenderIdDetails().getDateOfIssue())) ? null : new SimpleDateFormat("dd/MM/yyyy").parse(trxEntity.getSenderIdDetails().getDateOfIssue()));
            sender.setSenIdDocumentExpDate(("".equals(trxEntity.getSenderIdDetails().getDateOfExpiry())) ? null : new SimpleDateFormat("dd/MM/yyyy").parse(trxEntity.getSenderIdDetails().getDateOfExpiry()));
            sender.setSenOccupation(trxEntity.getSenderIdDetails().getOccupation());
            sender.setSenCityId(agence.getVille());
            sender.setSenCity((trxEntity.getSenderDetails().getCity() == null) ? "N/A" : trxEntity.getSenderDetails().getCity());
            sender.setSenPhoneNumber1((trxEntity.getSenderDetails().getMobileNumber() == null) ? "N/A" : trxEntity.getSenderDetails().getMobileNumber());
            sender.setSenPhoneNumber2((trxEntity.getSenderDetails().getPhoneNumber() == null) ? "N/A" : trxEntity.getSenderDetails().getPhoneNumber());
            sender.setSenEmail((trxEntity.getSenderDetails().getEmail() == null) ? "N/A" : trxEntity.getSenderDetails().getEmail());
            sender.setSenState("N/A");

            //create sender
            GenericsResponse<Sender> createSenderResponse = senderService.createSender(sender);
            if (createSenderResponse.getResponseCode() == 200) {
                sender = (Sender) createSenderResponse.getData();
                trx.setTransSenId(sender);
            } else {
                System.out.println("Error creating sender ::: " + createSenderResponse.getResponseDescription() + " code = " + createSenderResponse.getResponseCode());
            }
            System.out.println("sender created ::: " + trx.getTransSenId());

            //beneficiary
            Beneficiary beneficiary = new Beneficiary();
            beneficiary.setBenFirstname(trxEntity.getReceiverDetails().getFirstName());
            beneficiary.setBenLastname(trxEntity.getReceiverDetails().getLastName());
            beneficiary.setBenCountry(destCountry.getPsLibelle());
            beneficiary.setBenCountryId(destCountry);
            if (!"{}".equals(trxEntity.getReceiverIdDetails().toString())) {
                GenericsResponse natBenefRes = paysService.findPaysByCodeIso((trxEntity.getReceiverIdDetails().getNationality() == null) ? "" : trxEntity.getReceiverIdDetails().getNationality());
                if (natBenefRes.getResponseCode() == 200 && natBenefRes.getData() != null) {
                    beneficiary.setBenNationality(((Pays) natBenefRes.getData()).getPsLibelle());
                    beneficiary.setBenNationalityId((Pays) natBenefRes.getData());
                }
                beneficiary.setBenIdDocumentNumber((trxEntity.getReceiverIdDetails().getRid1Number() == null) ? null : trxEntity.getReceiverIdDetails().getRid1Number());
                beneficiary.setBenIdDocumentDelDate((trxEntity.getReceiverIdDetails().getDateOfIssue() == null) ? null : formatter.parse(trxEntity.getReceiverIdDetails().getDateOfIssue()));
                beneficiary.setBenIdDocumentExpDate((trxEntity.getReceiverIdDetails().getDateOfExpiry() == null) ? null : formatter.parse(trxEntity.getReceiverIdDetails().getDateOfExpiry()));
                beneficiary.setBenGender((trxEntity.getReceiverIdDetails().getGender() == null) ? null : trxEntity.getReceiverIdDetails().getGender());
                beneficiary.setBenOccupation((trxEntity.getReceiverIdDetails().getOccupation() == null) ? null : trxEntity.getReceiverIdDetails().getOccupation());
                GenericsResponse payBenefIdRes = paysService.findPaysByCodeIso((trxEntity.getReceiverIdDetails().getCountryOfIssue() == null) ? "" : trxEntity.getReceiverIdDetails().getCountryOfIssue());
                if (payBenefIdRes.getResponseCode() == 200 && payBenefIdRes.getData() != null) {
                    beneficiary.setBenIdDocumentIssueCountry((Pays) payBenefIdRes.getData());
                }
                GenericsResponse tpiBenefRes = typePieceIdentiteService.getTypePieceIdentiteByCode((trxEntity.getReceiverIdDetails().getCodeTypePieceRe() == null) ? "" : trxEntity.getReceiverIdDetails().getCodeTypePieceRe());
                if (tpiBenefRes.getResponseCode() == 200 && tpiBenefRes.getData() != null) {
                    beneficiary.setBenIdDocumentType(((TypePieceIdentite) tpiBenefRes.getData()));
                }
            }
            beneficiary.setBenCity(agence.getVille().getViLibelle());
            beneficiary.setBenCityId(agence.getVille());
            beneficiary.setBenState(trxEntity.getReceiverDetails().getState());
            beneficiary.setBenPostalCode(trxEntity.getReceiverDetails().getPostCode());
            beneficiary.setBenPhoneNumber1(trxEntity.getReceiverDetails().getMobileNumber());
            beneficiary.setBenPhoneNumber2(trxEntity.getReceiverDetails().getPhoneNumber());
            beneficiary.setBenEmail(trxEntity.getReceiverDetails().getEmail());
            beneficiary.setBenDob(null);
            beneficiary.setBenAddress(trxEntity.getReceiverDetails().getAddress1());
            beneficiary.setBenAddress2(trxEntity.getReceiverDetails().getAddress2());

            //create benef
            GenericsResponse<Beneficiary> createBenfResponse = beneficiaryService.createBeneficiary(sender.getSenId(), beneficiary);
            if (createBenfResponse.getResponseCode() == 200) {
                beneficiary = (Beneficiary) createBenfResponse.getData();
                trx.setTransBenId(beneficiary);
            } else {
                System.out.println("Error creating beneficiary ::: " + createBenfResponse.getResponseDescription() + " code = " + createSenderResponse.getResponseCode());
            }
            System.out.println("beneficiary creted ::: " + trx.getTransBenId());

            //Guichet transaction
            trx.setTransGuichetId(guichet);
            trx.setTransGuichet(guichet.getLibelle());

            //Agence transaction
            trx.setTransAgencyId(agence);
            trx.setTransAgency(agence.getLibelle());

            //Compagnie transaction
            trx.setTransCompanyId(compagnie);
            trx.setTransCompany(compagnie.getLibelle());

            //Groupe transaction
            trx.setTransGroupId(succursale);
            trx.setTransGroup(succursale.getLibelle());

            //Utilisateur transaction
            trx.setTransCasher(usr.getUsrPrenom() + " " + usr.getUsrNom());
            trx.setTransUserid(usr);

            //Sender Transaction
            trx.setTransSenId(sender);
            trx.setTransSenFirstname(sender.getSenFirstname());
            trx.setTransSenLastname(sender.getSenLastname());
            trx.setTransSenCountry(sender.getSenCountry());
            trx.setTransSenCity(sender.getSenCity());
            trx.setTransSenPostalCode(sender.getSenPostalCode());
            trx.setTransSenAddress(sender.getSenAddress());
            trx.setTransSenPhone(sender.getSenPhoneNumber1());
            trx.setTransSenEmail(sender.getSenEmail());
            trx.setTransSenIdnumber(sender.getSenIdDocumentNumber());
            trx.setTransSenIdtype(sender.getSenIdDocumentType().getTpiCode());
            trx.setTransOriginCountry(sender.getSenCountry());
            trx.setTransOriginCountryId(sender.getSenCountryId());

            //Benef Transaction
            trx.setTransBenId(beneficiary);
            trx.setTransBenFirstname(beneficiary.getBenFirstname());
            trx.setTransBenLastname(beneficiary.getBenLastname());
            trx.setTransBenAddress(beneficiary.getBenAddress());
            trx.setTransBenCity(beneficiary.getBenCity());
            trx.setTransBenCountry(beneficiary.getBenCountry());
            trx.setTransBenEmail(beneficiary.getBenEmail());
            trx.setTransBenIdnumber(beneficiary.getBenIdDocumentNumber());
            trx.setTransBenIdtype((beneficiary.getBenIdDocumentType() != null) ? beneficiary.getBenIdDocumentType().getTpiCode() : "");
            trx.setTransBenPhone(beneficiary.getBenPhoneNumber1());
            trx.setTransBenPostalCode(beneficiary.getBenPostalCode());

            //Service Transaction
            trx.setTransServiceId(service);
            trx.setTransServiceName(service.getNom());

            //Transaction details
            trx.setTransAmountToPaid(new BigDecimal(trxEntity.getPayoutAmount()));
            trx.setTransDestCountry(destCountry.getPsLibelle());
            trx.setTransDestCountryId(destCountry);
            trx.setTransDestCur(destDevise); //Destination Currency transaction
            trx.setTransInterfacage("O");
            trx.setTransStatut("P");
            trx.setTransSens("C2C_PAIEMENT_WORLD_REMIT");
            trx.setTransMessage("ND");
            trx.setTransMotif("ND");
            trx.setTransMtpartnercode(service.getNom());
            trx.setTransMttransactionnumber(trxEntity.getReference().trim().toUpperCase());
            trx.setTransParamWs(parametrews);

            //Payer network
            trx.setTransPayerguichetid(guichet);
            trx.setTransPayerguichet(guichet.getLibelle());
            trx.setTransPayeragencyid(agence);
            trx.setTransPayeragency(agence.getLibelle());
            trx.setTransPayercompanyid(compagnie);
            trx.setTransPayercompany(compagnie.getLibelle());
            trx.setTransPayergroupid(succursale);
            trx.setTransPayergroup(succursale.getLibelle());
            trx.setTransPayercasherid(usr);
            trx.setTransPayercasher(usr.getUsrNom());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception doBuildtransaction Paiement ::: " + e.getMessage());
        }
        return trx;
    }
}
