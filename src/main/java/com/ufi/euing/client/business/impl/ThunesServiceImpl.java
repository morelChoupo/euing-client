package com.ufi.euing.client.business.impl;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.ufi.euing.client.business.*;
import com.ufi.euing.client.entity.*;
import com.ufi.euing.client.entity.Beneficiary;
import com.ufi.euing.client.entity.Sender;
import com.ufi.euing.client.integration.thunes.dto.*;
import com.ufi.euing.client.integration.thunes.response.QuotationResponse;
import com.ufi.euing.client.integration.thunes.response.TransactionResponse;
import com.ufi.euing.client.integration.thunes.service.ThunesCallApi;
import com.ufi.euing.client.props.EuingProperties;
import com.ufi.euing.client.repositories.GuichetRepository;
import com.ufi.euing.client.repositories.ParametreBaseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


@Component
public class ThunesServiceImpl implements ThunesService {

    final EuingProperties euingProperties;

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

    public ThunesServiceImpl(EuingProperties euingProperties,
                             ParametreBaseService parametreBaseService,
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
                             TypePieceIdentiteService typePieceIdentiteService) {
        this.euingProperties = euingProperties;
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
    }

    private static final Logger log = LoggerFactory.getLogger(ThunesServiceImpl.class);


    @Override
    public GenericsResponse<GetTransfinQuote> doGetTarif(GetTransfinQuoteRequest request) {
        GenericsResponse<GetTransfinQuote> response;
        try {
            log.info("request =  {}",  request);
            Guichet guichet = guichetService.getById(euingProperties.getGuichetCode());
            Utilisateur usr = utilisateurService.find(euingProperties.getUtilisateurCode());
            if (guichet == null) {
                response = new GenericsResponse(500, "Error doGetTarif ::: Guichet not found", null);
            } else {
                Pays po = guichet.getAgence().getCompagnie().getPays();
                request.setOriginatingCurrency(po.getPsZmCode().getZmDevCode().getDevCode());

                Pays pd = paysService.getCountryByCode(request.getDestinationCountry());
                request.setDestinationCurrency(pd.getPsZmCode().getZmDevCode().getDevCode());

                com.ufi.euing.client.entity.Service service = (serviceService.getServiceByCode(ThunesUtil.THUNES_SEND_SERVICE_CODE)).getData();

                GenericsResponse<QuotationResponse> quotationResponse = doQuotation(request.getLocalAmount(), po.getPsCode(), pd.getPsCode(), request.getOriginatingCurrency(), request.getDestinationCurrency(), usr.getUsrId());
                if (quotationResponse.getResponseCode() == 200) {
                    System.out.println("pCOMPANY_ID = " + guichet.getAgence().getCompagnie().getId()
                            + "  comp lbl  " + guichet.getAgence().getCompagnie().getLibelle()
                            + " pSERVICE_ID = " + service.getId()
                            + "  ser lbl  " + service.getNom()
                            + " pDESTINATION_COUNTRY_CODE = " + pd.getPsCode()
                            + "  pays lbl  " + pd.getPsLibelle()
                            + " pCALCULATION_MODE = " + guichet.getAgence().getCompagnie().getModeCalculPartner()
                            + " pAMOUNT = " + (float) request.getLocalAmount()
                            + " pSENDER_ID = " + usr.getUsrId()
                            + " pTRANS_ORIGIN_CUR = " + request.getOriginatingCurrency()
                            + " pTRANS_DEST_CUR = " + request.getDestinationCurrency());
                    GenericsResponse<CalculTarifInternational> feesResponse = transactionEuingService.calculTarifInterTransaction(guichet.getAgence().getCompagnie().getId(), guichet.getAgence().getCompagnie().getLibelle(), service.getId(), service.getNom(),
                            pd.getPsCode(), pd.getPsLibelle(), guichet.getAgence().getCompagnie().getModeCalculPartner(), request.getLocalAmount(), usr.getUsrId(), request.getOriginatingCurrency(), request.getDestinationCurrency());
                    if (feesResponse.getResponseCode() == 200) {
                        CalculTarifInternational tarif = (CalculTarifInternational) feesResponse.getData();
                        System.out.println("tarif intern = " + tarif);
                        if (tarif != null && tarif.getRes() == 1) {
                            QuotationResponse quotation = quotationResponse.getData();
                            System.out.println("get tarif quotation = " + quotation);
                            GetTransfinQuote quote = new GetTransfinQuote();
                            quote.setOriginatingCountry(po.getPsCode() + ":" + po.getPsLibelle());
                            quote.setDestinationCountry(pd.getPsCode() + ":" + pd.getPsLibelle());
                            quote.setOriginatingCurrency(request.getOriginatingCurrency());
                            quote.setDestinationCurrency(request.getDestinationCurrency());
                            quote.setLocalAmount(tarif.getAmountSent());
                            //quote.setCustomerCharge(tarif.getFees());
                            quote.setTaxCharged(tarif.getOthersFees());
                            quote.setExchangeRate(tarif.getExchangeRate());
                            //quote.setCashTellerReceiver(tarif.getAmountToPaid());
                            quote.setPayoutAmount((float) quotation.getDestination().getAmount());
                            float frais;
                            if (quotation.getFee().getAmount() > tarif.getFees()) {
                                frais = (float) quotation.getFee().getAmount();
                            } else {
                                frais = (float) tarif.getFees();
                            }
                            quote.setCustomerCharge(frais);
                            double amtToPay = frais + tarif.getAmountSent();
                            quote.setCashTellerReceiver(amtToPay);
                            response = new GenericsResponse(quote);
                        } else {
                            response = new GenericsResponse(500, "No fees set for company " + guichet.getAgence().getCompagnie().getLibelle() + ", service " + service.getNom() + ", destination " + pd.getPsLibelle() + " and amount " + tarif.getAmountSent()
                                    + ". The others reason is that the company or service or destination is not active!", null);
                        }
                    } else {
                        response = new GenericsResponse(feesResponse.getResponseCode(), feesResponse.getResponseDescription(), null);
                    }
                } else {
                    response = new GenericsResponse(500, "Error doQuotation of doGetTarifThunes ::: " + quotationResponse.getResponseDescription(), null);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception doGetTarifThunes ::: " + e.getMessage());
            response = new GenericsResponse(500, "Exception doGetTarifThunes ::: " + e.getMessage(), null);
        }
        return response;
    }


    GenericsResponse<QuotationResponse> doQuotation(double amount, String originCountryIso3, String destCountryIso3, String originCurrency, String destCurrency, BigDecimal senderId) {
        GenericsResponse<QuotationResponse> response;
        try {
            Utilisateur usr = utilisateurService.find(euingProperties.getUtilisateurCode());
            GenericsResponse<Parametrews> p = parametrewsService.getParametrewsByPartnerCode("THUNES");
            if (p.getResponseCode() == 200) {
                String payerId = null;
                String resMsg = "Error ::: ";

                ParametreBase parametreBase = parametreBaseService.find("THUNES_PAYER_ID_"+destCountryIso3);
                if (parametreBase == null) resMsg += " Impossible to continue, this service is not used for destination " + destCountryIso3;
                else payerId = parametreBase.getValeur();

                System.out.println("payerId = " + payerId);

                if (payerId != null) {
                    Parametrews pws = p.getT();

                    Source source = new Source();
                    source.setAmount(amount);
                    //source.setCountry_iso_code(originCountryIso3);
                    source.setCountry_iso_code(("CM".equals(originCountryIso3)) ? "CMR" : originCountryIso3);
                    source.setCurrency(originCurrency);

                    Destination destination = new Destination();
                    destination.setCurrency(destCurrency);
                    destination.setAmount(1);

                    String sdt = new SimpleDateFormat("yyyyMMddHHmmss", Locale.FRENCH).format(new Date());
                    Quotation quotation = new Quotation();
                    quotation.setExternal_id(usr.getUsrId() + "" + sdt);
                    quotation.setMode(ThunesUtil.THUNES_QUOT_MODE_SOURCE_AMOUNT);
                    quotation.setTransaction_type("C2C");
                    quotation.setSource(source);
                    quotation.setDestination(destination);
                    quotation.setPayer_id(payerId);

                    ThunesCallApi thunesCallApi = new ThunesCallApi();
                    String result = thunesCallApi.callApiGenericThunesPOSTQuotationAndTransaction(pws.getLogin(), pws.getPassword(), pws.getPwsWebServiceUrl() + ThunesUtil.THUNES_URI_QUOTATION, quotation); // do quotation
                    System.out.println("Result call API Thunes doQuotation ::: " + result);

                    if (result != null && !result.contains("error")) {
                        ObjectMapper mapper = new ObjectMapper();
                        QuotationResponse quotationResponse = mapper.readValue(result, QuotationResponse.class);
                        System.out.println("quotationResponse = " + quotationResponse);
                        response = new GenericsResponse(quotationResponse);
                    } else {
                        response = new GenericsResponse(500, "An error occured during quotation on partner. Details :  " + result, null);
                    }
                } else {
                    response = new GenericsResponse(500, resMsg, null);
                }
            } else {
                response = new GenericsResponse(p.getResponseCode(), "Error doQuotation :::  " + p.getResponseDescription(), null);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Exception doQuotation ::: " + e.getMessage());
            response = new GenericsResponse(500, "Exception doQuotation ::: " + e.getMessage(), null);
        }
        return response;
    }

    @Override
    public GenericsResponse<TransactionEuing> doSendTransaction(TransactionDetailsEntity trxEntity) {
        GenericsResponse response;
        try {
            System.out.println("doSendTransaction ::: ");
            GenericsResponse<Parametrews> p = parametrewsService.getParametrewsByCode("THUNES"); //get param web service
            if (p.getResponseCode() == 200) {
                Parametrews pws = (Parametrews) p.getData();

                Guichet guichet = guichetService.getById(euingProperties.getGuichetCode());
                Utilisateur usr = utilisateurService.find(euingProperties.getUtilisateurCode());
                //Agence agence = guichet.getAgence();
                //Compagnie compagnie = agence.getCompagnie();
                //Pays originCountry = compagnie.getPays();

                Service service = (serviceService.getServiceByCode(ThunesUtil.THUNES_SEND_SERVICE_CODE)).getData();
                System.out.println("service ::: " + service);
                /*Pays destCounrty = (paysFacadeLocal.findPaysByCodeIso(trxEntity.getDestinationCountry())).getData();

                GenericsResponse<CalculTarifInternational> tarifRes = this.doGetTarif(1, compagnie.getId(), compagnie.getLibelle(), service.getId(), service.getNom(),
                        destCounrty.getPsCode(), destCounrty.getPsLibelle(), trxEntity.getOriginatingCountry(), trxEntity.getLocalAmount(), usr.getUsrId(),
                        trxEntity.getOriginatingCurrency(), trxEntity.getDestinationCurrency());
                CalculTarifInternational tarif = tarifRes.getData();*/
                GenericsResponse<TransactionEuing> trxRes = doBuildtransaction(trxEntity, pws, usr, service, guichet); //build transaction
                System.out.println("trxRes ::: " + trxRes.getResponseCode() + trxRes.getResponseDescription() + trxRes.getData());
                TransactionEuing trx = new TransactionEuing();
                if (trxRes.getResponseCode() == 200) {
                    trx = trxRes.getData();
                } else {
                    response = new GenericsResponse(trxRes.getResponseCode(), trxRes.getResponseDescription(), null);
                }

                String sdt = new SimpleDateFormat("yyyyMMddHHmmss", Locale.FRENCH).format(new Date());
                Transaction transaction = new Transaction();
                transaction.setExternal_id(trxEntity.getUserSession() + "" + sdt);
                transaction.setRetail_fee(1);
                transaction.setRetail_fee_currency("EUR");
                transaction.setCallback_url("");
                CreditPartyIdentifier creditPartyIdentifier = new CreditPartyIdentifier();
                creditPartyIdentifier.setMsisdn(trx.getTransBenPhone());
                transaction.setCredit_party_identifier(creditPartyIdentifier);

                com.ufi.euing.client.integration.thunes.dto.Sender senderThunes = new com.ufi.euing.client.integration.thunes.dto.Sender();
                senderThunes.setLastname(trx.getTransSenFirstname());
                senderThunes.setFirstname(trx.getTransSenLastname());
                senderThunes.setNationality_country_iso_code(("CM".equals(trxEntity.getOriginatingCountry())) ? "CMR" : trxEntity.getOriginatingCountry());
                senderThunes.setDate_of_birth(new SimpleDateFormat("yyyy-MM-dd", Locale.FRENCH).format(trx.getTransSenId().getSenDob()));
                senderThunes.setGender(("M".equals(trx.getTransSenId().getSenGender())) ? "MALE" : "FEMALE");
                senderThunes.setAddress(trx.getTransSenAddress());
                senderThunes.setPostal_code(trx.getTransSenPostalCode());
                senderThunes.setCity(trx.getTransSenCity());
                senderThunes.setCountry_iso_code(("CM".equals(trxEntity.getOriginatingCountry())) ? "CMR" : trxEntity.getOriginatingCountry());
                senderThunes.setMsisdn(trx.getTransSenPhone());
                senderThunes.setEmail(trx.getTransSenEmail());
                senderThunes.setId_type("NATIONAL_ID");
                senderThunes.setId_number(trx.getTransSenIdnumber());
                senderThunes.setId_delivery_date(new SimpleDateFormat("yyyy-MM-dd", Locale.FRENCH).format(trx.getTransSenId().getSenIdDocumentDelDate()));
                senderThunes.setOccupation(trx.getTransSenId().getSenOccupation());
                transaction.setSender(senderThunes);

                com.ufi.euing.client.integration.thunes.dto.Beneficiary beneficiaryThunes = new com.ufi.euing.client.integration.thunes.dto.Beneficiary();
                beneficiaryThunes.setLastname(trx.getTransBenLastname());
                beneficiaryThunes.setFirstname(trx.getTransBenFirstname());
                beneficiaryThunes.setNationality_country_iso_code(trxEntity.getDestinationCountry());
                beneficiaryThunes.setDate_of_birth("1971-01-01");
                beneficiaryThunes.setCountry_iso_code(trxEntity.getDestinationCountry());
                beneficiaryThunes.setGender("");
                beneficiaryThunes.setAddress(trx.getTransBenAddress());
                beneficiaryThunes.setPostal_code("");
                beneficiaryThunes.setCity(trx.getTransBenCity());
                beneficiaryThunes.setCountry_iso_code(trxEntity.getDestinationCountry());
                beneficiaryThunes.setMsisdn(trx.getTransBenPhone());
                beneficiaryThunes.setEmail(trx.getTransBenEmail());
                beneficiaryThunes.setId_country_iso_code(trxEntity.getDestinationCountry());
                beneficiaryThunes.setId_type("");
                beneficiaryThunes.setId_number("");
                beneficiaryThunes.setOccupation("");
                transaction.setBeneficiary(beneficiaryThunes);

                System.out.println("transaction thunes ::: " + transaction);
                GenericsResponse<QuotationResponse> quotresp = doQuotation(trxEntity.getLocalAmount(), trxEntity.getOriginatingCountry(), trxEntity.getDestinationCountry(), trxEntity.getOriginatingCurrency(), trxEntity.getDestinationCurrency(), null);
                System.out.println("quotresp ::: " + quotresp.getResponseCode() + quotresp.getResponseDescription() + quotresp.getData());
                if (quotresp.getResponseCode() == 200) {
                    QuotationResponse quotation = quotresp.getData();
                    System.out.println("quotation response = " + quotation);
                    ThunesCallApi thunesCallApi = new ThunesCallApi();
                    String resultTransaction = thunesCallApi.callApiGenericThunesPOSTQuotationAndTransaction(pws.getLogin(), pws.getPassword(), pws.getPwsWebServiceUrl()
                            + ThunesUtil.THUNES_URI_QUOTATION + "/" + quotation.getId() + "/" + ThunesUtil.THUNES_URI_TRANSACTION, transaction);
                    System.out.println("resultTransaction ::: " + resultTransaction);
                    if (resultTransaction != null && !resultTransaction.contains("error")) {
                        ObjectMapper mapper = new ObjectMapper();
                        TransactionResponse transactionResponse = mapper.readValue(resultTransaction, TransactionResponse.class);
                        System.out.println("transactionResponse ::: " + transactionResponse);
                        trx.setTransMtstatus(transactionResponse.getStatus_class_message());
                        trx.setTransMttransactionid(transactionResponse.getId() + "");
                        trx.setTransMtobservation(transactionResponse.getExternal_id());

                        GenericsResponse resSaveTrx = transactionEuingService.createNewInterTransactionC2C(trx); //ok
                        System.out.println("resSaveTrx ::: " + resSaveTrx.getData());
                        if (resSaveTrx.getResponseCode() == 200) {
                            trx = (TransactionEuing) resSaveTrx.getData();
                            //Confirm tansaction
                            System.out.println("trans ::: " + trx.getTransMttransactionid());
                            System.out.println("Reference ::: " + trx.getTransReference());
                            String resultConfirmation = thunesCallApi.callApiGenericThunesPOSTConfirmation(pws.getLogin(), pws.getPassword(), pws.getPwsWebServiceUrl() + ThunesUtil.THUNES_URI_TRANSACTION + "/" + trx.getTransMttransactionid() + "/" + ThunesUtil.THUNES_URI_CONFIRM, "");
                            System.out.println("resultConfirmation ::: " + resultConfirmation);
                            if (resultConfirmation != null && !resultConfirmation.contains("error")) {
                                TransactionResponse confirmationResponse = new ObjectMapper().readValue(resultConfirmation, TransactionResponse.class);
                                System.out.println("confirmationResponse ==> " + confirmationResponse.toString());
                                if (confirmationResponse.getStatus_class().equalsIgnoreCase("2")) {
                                    System.out.println("Pays Thunes ===> " + confirmationResponse.getPayer().getCountry_iso_code());
                                    trx.setTransMtstatus(confirmationResponse.getStatus_class_message());
                                    trx.setTransMttransactionid(confirmationResponse.getId() + "");
                                    //trx.setTransMttransactionnumber(confirmationResponse.getPayer_transaction_code());
                                    trx.setTransMtobservation(confirmationResponse.getExternal_id());
                                    trx.setTransInterfacage("O");

                                    String externalReference = (confirmationResponse.getPayer().getCountry_iso_code().equals("NGA"))
                                            ? "" + confirmationResponse.getPayer_transaction_code() : "" + confirmationResponse.getId();
                                    System.out.println("externalReference ::: " + externalReference);
                                    System.out.println("transReference ::: " + trx.getTransReference());

                                    trx.setTransMttransactionnumber(externalReference);

                                    GenericsResponse resSaveTrx2 = transactionEuingService.createNewInterTransactionC2C(trx); //ok
                                    System.out.println("trxThunesConfirme === >" + resSaveTrx2.getResponseCode());
                                    System.out.println("trxThunesConfirmeDesc === >" + resSaveTrx2.getResponseDescription());
                                    System.out.println("trxThunesConfirmeData === >" + resSaveTrx2.getData());
                                    response = new GenericsResponse(resSaveTrx2.getResponseCode(), resSaveTrx2.getResponseDescription(), resSaveTrx2.getData());
                                } else {
                                    response = new GenericsResponse(resSaveTrx.getResponseCode(), "An error occured during confirmation of transaction on partner. Details : " + confirmationResponse.getStatus_message(), null);
                                }
                            } else {
                                response = new GenericsResponse(500, "An error occured during confirmation of transaction on partner. Details : " + resultConfirmation.toString(), null);
                            }
                        } else {
                            response = new GenericsResponse(resSaveTrx.getResponseCode(), "Error createNewInterTransactionC2C ::: " + resSaveTrx.getResponseDescription(), null);
                        }
                    } else {
                        response = new GenericsResponse(500, "An error occured during quotation on partner. Details : " + resultTransaction, null);
                    }
                } else {
                    response = new GenericsResponse(quotresp.getResponseCode(), "Error doQuotation " + quotresp.getResponseDescription(), null);
                }
            } else {
                response = new GenericsResponse(p.getResponseCode(), "Error doSendTransaction param ws " + p.getResponseDescription(), null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response = new GenericsResponse(500, "Exception doSendTransaction ::: " + e.getMessage(), null);
        }
        return response;
    }



    GenericsResponse<TransactionEuing> doBuildtransaction(TransactionDetailsEntity trxEntity, Parametrews parametrews, Utilisateur usr, Service service, Guichet guichet) {
        GenericsResponse<TransactionEuing> response = new GenericsResponse();
        try {
            System.out.println("trxEntity ::: " + trxEntity);
            System.out.println("parametrews ::: " + parametrews);
            System.out.println("usr ::: " + usr);
            System.out.println("service ::: " + service);
            System.out.println("guichet ::: " + guichet);
            boolean terminar = false;
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Agence agence = guichet.getAgence();
            Compagnie compagnie = agence.getCompagnie();

            Pays originCountry = compagnie.getPays();
            Pays destCountry = (paysService.findPaysByCodeIso(trxEntity.getDestinationCountry())).getData();

            Sender sender = new Sender();
            if (!terminar) {
                System.out.println("sender ::: " + trxEntity.getSenderDetails());
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

                //add senderID
                //sender.setSenId(usr.getUsrId());
                //sender.setSenCode(1);
                //sender.setSenPhoneNumber2("//");
                //sender.setSenIdDocumentImg1("//");
                //sender.setSenIdDocumentImg2("//");
                //sender.setSenIdDocumentImg3("//");
                //sender.setSenComment("test");
                //create sender
                System.out.println("sender " + sender);
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
                System.out.println("benef ::: " + trxEntity.getReceiverDetails());
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
                trx.setTransStatut("E");
                trx.setTransSens("C2C_ENVOI_THUNES");
                trx.setTransMotif("ND");
                trx.setTransMessage("ND");
                trx.setTransMtpartnercode(service.getNom());
                trx.setTransParamWs(parametrews);
                //trx.setTransMotif("9.9.9.9.9.9");
                //trx.setTransMessage(trxEntity.getReceiverPaymentDetails().getTestAnswer());

                trx.setTransServiceId(service);
                trx.setTransServiceName(service.getNom());
                System.out.println("trx = " + trx);
                response = new GenericsResponse(trx);
            } else {
                response = new GenericsResponse(500, "Error create transaction", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception doBuildtransaction Envoie ::: " + e.getMessage());
        }
        return response;
    }
}
