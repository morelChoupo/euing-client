package com.ufi.euing.client.business.impl;

import com.ufi.euing.client.business.*;
import com.ufi.euing.client.entity.*;
import com.ufi.euing.client.props.EuingProperties;
import com.ufi.euing.client.repositories.GuichetRepository;
import com.ufi.euing.client.repositories.ParametreBaseRepository;
import com.ufi.mfs.MfsUtils;
import com.ufi.mfs.models.*;
import org.springframework.stereotype.Service;

import com.ufi.mfs.MfsService;


import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service
public class MfsServiceImpl implements com.ufi.euing.client.business.MfsService {

    final ParametreBaseService parametreBaseService;

    final ParametrewsService parametrewsService;

    final UtilisateurService utilisateurService;

    final TransactionEuingService transactionEuingService;

    final SenderService senderService;

    final BeneficiaryService beneficiaryService;

    final EuingProperties euingProperties;

    final GuichetService guichetService;

    final GuichetRepository guichetRepository;

    final ParametreBaseRepository parametreBaseRepository;

    final ServiceService serviceService;

    final PaysService paysService;

    final TypePieceIdentiteService typePieceIdentiteService;

    MfsService mfsService = new MfsService();
    MfsUtils mfsUtils = new MfsUtils();

    public MfsServiceImpl(ParametreBaseService parametreBaseService,
                          ParametrewsService parametrewsService,
                          UtilisateurService utilisateurService, TransactionEuingService transactionEuingService,
                          SenderService senderService,
                          BeneficiaryService beneficiaryService,
                          EuingProperties euingProperties, GuichetService guichetService,
                          GuichetRepository guichetRepository,
                          ParametreBaseRepository parametreBaseRepository,
                          ServiceService serviceService,
                          PaysService paysService,
                          TypePieceIdentiteService typePieceIdentiteService) {
        this.parametreBaseService = parametreBaseService;
        this.parametrewsService = parametrewsService;
        this.utilisateurService = utilisateurService;
        this.transactionEuingService = transactionEuingService;
        this.senderService = senderService;
        this.beneficiaryService = beneficiaryService;
        this.euingProperties = euingProperties;
        this.guichetService = guichetService;
        this.guichetRepository = guichetRepository;
        this.parametreBaseRepository = parametreBaseRepository;
        this.serviceService = serviceService;
        this.paysService = paysService;
        this.typePieceIdentiteService = typePieceIdentiteService;
    }

    @Override
    public GenericsResponse doGetTarif(GetTransfinQuoteRequest request) {
        GenericsResponse<GetTransfinQuote> response;
        try {
            GenericsResponse<Parametrews> paramRep = parametrewsService.getParametrewsByPartnerCode(MfsUtils.MFS_COMPAGNIE_CODE);
            if (paramRep.getResponseCode() == 200) {
                Parametrews pws = paramRep.getT();
                //Compagnie compagnie = compagnieFacadeLocal.findByCodePartenaire(MfsUtils.MFS_COMPAGNIE_CODE);
                com.ufi.euing.client.entity.Service service = (serviceService.getServiceByCode(MfsUtils.MFS_SERVICE_CODE)).getData();
                Guichet guichet = guichetService.getById(euingProperties.getGuichetCode());
                Utilisateur usr = utilisateurService.find(euingProperties.getUtilisateurCode());

                Pays po = guichet.getAgence().getCompagnie().getPays();
                String originDev = po.getPsZmCode().getZmDevCode().getDevCode();
                Pays pd = paysService.findPaysByCodeIso(request.getDestinationCountry()).getData();
                String destDev = pd.getPsZmCode().getZmDevCode().getDevCode();

                List<RateModel> respRate = mfsService.doGetRate(pws.getLogin(), pws.getPassword(), pd.getPsCode2(), originDev, destDev);
                Double rate = null;
                Double amountToPaid = null;
                if (!respRate.isEmpty()) {
                    rate = respRate.get(0).getFxRate();
                    amountToPaid = rate * request.getLocalAmount();
                }

                GenericsResponse<CalculTarifInternational> feesResponse = transactionEuingService.calculTarifInterTransaction(guichet.getAgence().getCompagnie().getId(), guichet.getAgence().getCompagnie().getLibelle(),
                        service.getId(), service.getNom(), pd.getPsCode(), pd.getPsLibelle(), 1, request.getLocalAmount(), usr.getUsrId(), originDev, destDev);
                if (feesResponse.getResponseCode() == 200) {
                    CalculTarifInternational tarif = (CalculTarifInternational) feesResponse.getData();
                    GetTransfinQuote quote = new GetTransfinQuote();
                    if (rate == null) {
                        rate = tarif.getExchangeRate();
                    }
                    if (amountToPaid == null) {
                        amountToPaid = tarif.getAmountToPaid();
                    }

                    quote.setOriginatingCountry(po.getPsCode() + ":" + po.getPsLibelle());
                    quote.setDestinationCountry(pd.getPsCode() + ":" + pd.getPsLibelle());
                    quote.setOriginatingCurrency(originDev);
                    quote.setDestinationCurrency(destDev);
                    quote.setLocalAmount(tarif.getAmountSent());
                    quote.setCustomerCharge(tarif.getFees());
                    quote.setTaxCharged(tarif.getOthersFees());
                    //quote.setExchangeRate(tarif.getExchangeRate());
                    quote.setExchangeRate(rate);
                    //quote.setCashTellerReceiver(tarif.getAmountToPaid());
                    quote.setCashTellerReceiver(amountToPaid);
                    quote.setPayoutAmount(tarif.getTotalToPaid());
                    response = new GenericsResponse(quote);
                } else {
                    response = new GenericsResponse(feesResponse.getResponseCode(), feesResponse.getResponseDescription(), null);
                }
            } else {
                response = new GenericsResponse<>(paramRep.getResponseCode(), "Error Parametre ws " + MfsUtils.MFS_COMPAGNIE_CODE + " not found. Message ::: " + paramRep.getResponseDescription(), null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response = new GenericsResponse(500, "Exception do get tarif ::: " + e.getMessage(), null);
        }
        return response;
    }

    @Override
    public GenericsResponse doRemittance(TransactionDetailsEntity trxEntity) {
        GenericsResponse<TransactionEuing> response;
        try {
            GenericsResponse<Parametrews> paramRep = parametrewsService.getParametrewsByPartnerCode(MfsUtils.MFS_COMPAGNIE_CODE);
            if (paramRep.getResponseCode() == 200) {
                Parametrews pws = paramRep.getT();

                String typeOperation = trxEntity.getCodeTransaction();
                com.ufi.euing.client.entity.Service service = (serviceService.getServiceByCode(MfsUtils.MFS_SERVICE_CODE)).getData();
                Guichet guichet = guichetService.getById(euingProperties.getGuichetCode());
                Utilisateur usr = utilisateurService.find(euingProperties.getUtilisateurCode());
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String partnerTransId = "" + new Date().getTime() + "" + guichet.getId();

                String reference = "";
                GenericsResponse<TransactionEuing> trxRes = this.doBuildtransaction(trxEntity, pws, usr, service, guichet); //build transaction
                TransactionEuing trx = new TransactionEuing();
                if (trxRes.getResponseCode() == 200) {
                    trx = trxRes.getData();
                } else {
                    response = new GenericsResponse(trxRes.getResponseCode(), trxRes.getResponseDescription(), null);
                }

                SenderModel senderModel = new SenderModel();
                senderModel.setAddress(trx.getTransSenId().getSenAddress());
                senderModel.setCity(trx.getTransSenId().getSenCity());
                senderModel.setDateOfBirth(dateFormat.format(trx.getTransSenId().getSenDob()));
                DocumentModel docSen = new DocumentModel();
                docSen.setIdCountry(trx.getTransSenId().getSenIdDocumentIssueCountry().getPsCode2());
                docSen.setIdExpiry(dateFormat.format(trx.getTransSenId().getSenIdDocumentExpDate()));
                docSen.setIdNumber(trx.getTransSenId().getSenIdDocumentNumber());
                docSen.setIdType(mfsUtils.lcpIdType2Mfs(trx.getTransSenId().getSenIdDocumentType().getTpiCode()));
                System.out.println("docSen ::: " + docSen);
                senderModel.setDocument(docSen);
                senderModel.setEmail(trx.getTransSenId().getSenEmail());
                senderModel.setFromCountry(trx.getTransSenId().getSenCountryId().getPsCode2());
                senderModel.setMsisdn(trx.getTransSenId().getSenPhoneNumber1());
                senderModel.setName(trx.getTransSenId().getSenFirstname());
                senderModel.setPostalCode(trx.getTransSenId().getSenPostalCode());
                senderModel.setState(trx.getTransSenId().getSenState());
                senderModel.setSurname(trx.getTransSenId().getSenLastname());
                System.out.println("SenderModel ::: " + senderModel);

                RecipientModel recipientModel = new RecipientModel();
                recipientModel.setAddress(trx.getTransBenId().getBenAddress());
                recipientModel.setCity(trx.getTransBenId().getBenCity());
                recipientModel.setDateOfBirth((trx.getTransBenId().getBenDob() != null) ? dateFormat.format(trx.getTransBenId().getBenDob()) : null);
                DocumentModel docBen = new DocumentModel();
                recipientModel.setDocument(docBen);
                recipientModel.setEmail(trx.getTransBenId().getBenEmail());
                recipientModel.setToCountry(trx.getTransBenId().getBenCountryId().getPsCode2());
                recipientModel.setMsisdn(trx.getNg5());
                recipientModel.setName(trx.getTransBenId().getBenFirstname());
                recipientModel.setPostalCode(trx.getTransBenId().getBenPostalCode());
                recipientModel.setState(trx.getTransBenId().getBenState());
                StatusModel statusModel = new StatusModel();
                recipientModel.setStatus(statusModel);
                recipientModel.setSurname(trx.getTransBenId().getBenLastname());
                System.out.println("RecipientModel ::: " + recipientModel);

                if (typeOperation.equalsIgnoreCase(MfsUtils.MFS_CASH_PICKUP_REMITTANCE_CODE)) {
                    reference = trx.getNg2();
                }

                EResponseModel result = mfsService.doRemittance(pws.getLogin(), pws.getPassword(), Double.valueOf(trx.getTransAmountToPaid() + ""), trx.getTransDestCur().getDevCode(),
                        senderModel, recipientModel, partnerTransId, trx.getNg5(), trx.getNg2(), reference, typeOperation);
                System.out.println("result ::: " + result);
                if (result.getCode().getStatusCode().equalsIgnoreCase("MR101")) {
                    System.out.println("to be saved ::: " + result.geteTransId());
                    trx.setTransMttransactionnumber(result.getMfsTransId());
                    trx.setTransMttransactionid(result.getThirdPartyTransId());
                    trx.setTransMtstatus(result.getCode().getStatusCode());
                    trx.setTransMtobservation(result.geteTransId());
                    trx.setNg3(result.getAmountDebited() + "");

                    GenericsResponse<TransactionEuing> resSaveTrx = transactionEuingService.createNewInterTransactionC2C(trx);
                    if (resSaveTrx.getResponseCode() == 200) {
                        TransactionEuing resData = (TransactionEuing) resSaveTrx.getData();
                        response = new GenericsResponse<>(resData);
                    } else {
                        response = new GenericsResponse(500, "Error createNewInterTransactionC2C " + resSaveTrx.getResponseCode() + " - " + resSaveTrx.getResponseDescription(), null);
                    }
                } else {
                    response = new GenericsResponse(500, "Error doSendTransactions ::: Code = " + result.getCode().getStatusCode() + "; Message = " + result.getMessage(), null);
                }
            } else {
                response = new GenericsResponse<>(paramRep.getResponseCode(), "Error Parametre ws " + MfsUtils.MFS_COMPAGNIE_CODE + " not found. Message ::: " + paramRep.getResponseDescription(), null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response = new GenericsResponse(500, "Exception do send transaction ::: " + e.getMessage(), null);
        }
        return response;
    }

    GenericsResponse<TransactionEuing> doBuildtransaction(TransactionDetailsEntity trxEntity, Parametrews parametrews, Utilisateur usr, com.ufi.euing.client.entity.Service service, Guichet guichet) {
        GenericsResponse<TransactionEuing> response;
        try {
            boolean terminar = false;
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Agence agence = guichet.getAgence();
            Compagnie compagnie = agence.getCompagnie();

            Pays originCountry = compagnie.getPays();
            Pays destCountry = (paysService.findPaysByCodeIso(trxEntity.getDestinationCountry())).getData();

            Sender sender = new Sender();
            if (!terminar) {
                System.out.println("sender");
                sender.setSenFirstname(trxEntity.getSenderDetails().getFirstName());
                sender.setSenLastname(trxEntity.getSenderDetails().getLastName());
                sender.setSenCountryId(originCountry);
                sender.setSenCountry(originCountry.getPsLibelle());
                sender.setSenCountryResId(originCountry);
                sender.setSenCountryRes(originCountry.getPsLibelle());
                Pays psn = paysService.find(trxEntity.getSenderIdDetails().getNationality());
                sender.setSenNationality(psn.getPsLibelle());
                sender.setSenNationalityId(psn);
                sender.setSenCityId(agence.getVille());
                sender.setSenCity(agence.getVille().getViLibelle());
                sender.setSenState(trxEntity.getSenderIdDetails().getNationality());
                sender.setSenPostalCode(trxEntity.getSenderDetails().getPostCode());
                sender.setSenPhoneNumber1(trxEntity.getSenderDetails().getMobileNumber());
                sender.setSenPhoneNumber2(trxEntity.getSenderDetails().getPhoneNumber());
                sender.setSenEmail(trxEntity.getSenderDetails().getEmail());
                sender.setSenDob((trxEntity.getSenderIdDetails().getDateOfBirth() == null) ? null : formatter.parse(trxEntity.getSenderIdDetails().getDateOfBirth()));
                sender.setSenIdDocumentDelDate(formatter.parse(trxEntity.getSenderIdDetails().getDateOfIssue()));
                sender.setSenIdDocumentExpDate(formatter.parse(trxEntity.getSenderIdDetails().getDateOfExpiry()));
                sender.setSenIdDocumentNumber(trxEntity.getSenderIdDetails().getId1Number());
                sender.setSenIdDocumentIssueCountry(paysService.find(trxEntity.getSenderIdDetails().getCountryOfIssue()));
                sender.setSenIdDocumentType(typePieceIdentiteService.find(trxEntity.getSenderIdDetails().getCodeTypePiece()));
                sender.setSenGender(trxEntity.getSenderIdDetails().getGender());
                sender.setSenOccupation(trxEntity.getSenderIdDetails().getOccupation());
                sender.setSenAddress(trxEntity.getSenderDetails().getAddress1());
                sender.setSenAddress2(trxEntity.getSenderDetails().getAddress2());
                //create sender
                GenericsResponse<Sender> createSenderResponse = senderService.createSender(sender);
                if (createSenderResponse.getResponseCode() == 200) {
                    sender = createSenderResponse.getData();
                } else {
                    response = new GenericsResponse(createSenderResponse.getResponseCode(), "Error create sender ::: " + createSenderResponse.getResponseDescription(), null);
                    terminar = true;
                }
                System.out.println("sennder created ::: " + sender);
            }

            Beneficiary beneficiary = new Beneficiary();
            if (!terminar) {
                System.out.println("benef");
                beneficiary.setBenFirstname(trxEntity.getReceiverDetails().getFirstName());
                beneficiary.setBenLastname(trxEntity.getReceiverDetails().getLastName());
                beneficiary.setBenCountryId(destCountry);
                beneficiary.setBenCountry(destCountry.getPsLibelle());
                //Pays pdn = paysFacadeLocal.find(trxEntity.getReceiverIdDetails().getNationality());
                beneficiary.setBenNationalityId(destCountry);
                beneficiary.setBenNationality(destCountry.getPsLibelle());
                Ville ville = transactionEuingService.getCityByName("OTHERS").getData();
                beneficiary.setBenCityId(ville);
                beneficiary.setBenCity(ville.getViLibelle());
                beneficiary.setBenState(trxEntity.getReceiverDetails().getState());
                beneficiary.setBenPostalCode(trxEntity.getReceiverDetails().getPostCode());
                beneficiary.setBenPhoneNumber1(trxEntity.getReceiverDetails().getMobileNumber());
                beneficiary.setBenPhoneNumber2(trxEntity.getReceiverDetails().getPhoneNumber());
                beneficiary.setBenEmail(trxEntity.getReceiverDetails().getEmail());
                beneficiary.setBenGender("ND");
                beneficiary.setBenAddress(trxEntity.getReceiverDetails().getAddress1());
                beneficiary.setBenAddress2(trxEntity.getReceiverDetails().getAddress2());
                //create benef
                GenericsResponse<Beneficiary> createBenfResponse = beneficiaryService.createBeneficiary(sender.getSenId(), beneficiary);
                if (createBenfResponse.getResponseCode() == 200) {
                    beneficiary = createBenfResponse.getData();
                } else {
                    response = new GenericsResponse(createBenfResponse.getResponseCode(), "Error create beneficiary ::: " + createBenfResponse.getResponseDescription(), null);
                }
                System.out.println("beneficiary create ::: " + beneficiary);
            }

            if (!terminar) {
                System.out.println("transaction");
                TransactionEuing trx = new TransactionEuing();

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
                Succursale succursale = compagnie.getSuccursale();
                trx.setTransGroupId(succursale);
                trx.setTransGroup(succursale.getLibelle());

                //Utilisateur transaction
                trx.setTransCasher(usr.getUsrPrenom() + " " + usr.getUsrNom());
                trx.setTransUserid(usr);

                //origin country transaction
                trx.setTransOriginCountryId(originCountry);
                trx.setTransOriginCountry(originCountry.getPsLibelle());

                //destination country transaction
                trx.setTransDestCountryId(beneficiary.getBenCountryId());
                trx.setTransDestCountry(beneficiary.getBenCountry());

                //Origin Currency transaction
                trx.setTransOriginCur(originCountry.getPsZmCode().getZmDevCode());

                //Destination Currency transaction
                trx.setTransDestCur(destCountry.getPsZmCode().getZmDevCode());

                //Service Transaction
                trx.setTransServiceId(service);
                trx.setTransServiceName(service.getNom());

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

                //Beneficiary Transaction
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

                //Transaction money
                trx.setTransMessage("ND");
                trx.setTransMotif("ND");
                trx.setTransAmountSent(BigDecimal.valueOf(trxEntity.getLocalAmount()));
                trx.setTransAmountToPaid(BigDecimal.valueOf(trxEntity.getPayoutAmount()));
                trx.setTransFees(BigDecimal.valueOf(trxEntity.getCustomerCharge()));
                trx.setTransOthers(BigDecimal.valueOf(trxEntity.getTaxCharged()));
                trx.setTransTotal(BigDecimal.valueOf(trxEntity.getPayoutAmount()));
                trx.setTransExchangeRate(BigDecimal.valueOf(trxEntity.getExchangeRate()));

                //Transaction details
                trx.setTransInterfacage("M");
                trx.setTransStatut("I");
                trx.setTransSens("C2C_ENVOI");
                trx.setTransMotif("ND");
                trx.setTransMessage("ND");
                trx.setTransMtpartnercode(service.getNom());
                trx.setTransParamWs(parametrews);
                trx.setNg5(trxEntity.getPartenaire().getCodePartenaire());
                trx.setNg4(trxEntity.getPartenaire().getDenommination());
                trx.setNg2(trxEntity.getPartnerTransactionNo());

                trx.setTransServiceId(service);
                trx.setTransServiceName(service.getNom());
                System.out.println("trx = " + trx);
                response = new GenericsResponse(trx);
            } else {
                response = new GenericsResponse(500, "Error create transaction", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response = new GenericsResponse(500, "Exception doBuildtransaction Envoi ::: " + e.getMessage(), null);
        }
        return response;
    }
}
