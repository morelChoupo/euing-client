package com.ufi.euing.client.business.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufi.euing.client.business.*;
import com.ufi.euing.client.entity.*;
import com.ufi.euing.client.exceptions.domain.EmailNotFoundException;
import com.ufi.euing.client.integration.juba.model.*;
import com.ufi.euing.client.integration.juba.response.*;
import com.ufi.euing.client.integration.juba.service.JubaCallApi;
import com.ufi.euing.client.props.EuingProperties;
import com.ufi.euing.client.repositories.GuichetRepository;
import com.ufi.euing.client.repositories.ParametreBaseRepository;
import org.springframework.stereotype.Service;

import com.ufi.euing.client.entity.SenderTransfinEntity;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service
public class JubaServiceImpl implements JubaService {

    final ParametreBaseService parametreBaseService;

    final ParametrewsService parametrewsService;

    final TransactionEuingService transactionEuingService;

    final UtilisateurService utilisateurService;

    final SenderService senderService;

    final BeneficiaryService beneficiaryService;

    final GuichetService guichetService;

    final GuichetRepository guichetRepository;

    final EuingProperties euingProperties;

    final ParametreBaseRepository parametreBaseRepository;

    final ServiceService serviceService;

    final PaysService paysService;

    final TypePieceIdentiteService typePieceIdentiteService;

    final ListToUseService listToUseService;

    public JubaServiceImpl(ParametrewsService parametrewsService,
                           ListToUseService listToUseService,
                           ParametreBaseService parametreBaseService,
                           TransactionEuingService transactionEuingService,
                           UtilisateurService utilisateurService,
                           SenderService senderService,
                           BeneficiaryService beneficiaryService,
                           GuichetService guichetService,
                           GuichetRepository guichetRepository,
                           EuingProperties euingProperties,
                           ParametreBaseRepository parametreBaseRepository,
                           ServiceService serviceService,
                           PaysService paysService,
                           TypePieceIdentiteService typePieceIdentiteService) {
        this.parametrewsService = parametrewsService;
        this.parametreBaseService = parametreBaseService;
        this.utilisateurService = utilisateurService;
        this.senderService = senderService;
        this.beneficiaryService = beneficiaryService;
        this.guichetService = guichetService;
        this.guichetRepository = guichetRepository;
        this.euingProperties = euingProperties;
        this.parametreBaseRepository = parametreBaseRepository;
        this.serviceService = serviceService;
        this.paysService = paysService;
        this.typePieceIdentiteService = typePieceIdentiteService;
        this.listToUseService = listToUseService;
        this.transactionEuingService = transactionEuingService;
    }


    @Override
    public GetAgentsDetailsResponse doGetAgent(String countryCode) {
        GetAgentsDetailsResponse response;
        try {
            GenericsResponse<Parametrews> p = parametrewsService.getParametrewsByCode("JUBA");

            if (p.getResponseCode() == 200) {
                Parametrews pws = p.getT();
                DoGetAgentRequest request = new DoGetAgentRequest();
                request.setCountry(countryCode.trim().toUpperCase());
                request.setCity("");

                JubaCallApi jubaCallApi = new JubaCallApi();
                String result = jubaCallApi.callApiGenericJubaPOST(JubaUtil.JUBA_URI_X_VERSION_1, pws.getLogin(), pws.getPassword(), pws.getPwsWebServiceUrl() + JubaUtil.JUBA_URI_DO_GET_AGENTS, request);
                System.out.println("Result call API Juba doGetAgent ::: " + result);
                ObjectMapper mapper = new ObjectMapper();
                DoGetAgentResponse getAgentResponse = mapper.readValue(result, DoGetAgentResponse.class);
                if (getAgentResponse.response.code.equalsIgnoreCase("001")) {
                    List<DoGetAgentsDetails> agents = getAgentResponse.data;
                    System.out.println("num agents found doGetAgent ::: " + agents.size());
                    response = new GetAgentsDetailsResponse(agents);
                } else {
                    response = new GetAgentsDetailsResponse(500, "Error doGetAgent ::: code =  " + getAgentResponse.response.code + " message = " + getAgentResponse.response.message);
                }
            } else {
                response = new GetAgentsDetailsResponse(p.getResponseCode(), "Error doGetAgent ::: " + p.getResponseDescription());
            }
        } catch (IOException e) {
            System.out.println("Exception doGetAgent ::: " + e.getMessage());
            response = new GetAgentsDetailsResponse(500, "Exception doGetAgent ::: " + e.getMessage());
        }
        return response;
    }

    @Override
    public ListeToUseDetailsResponse doGetPurpose() {
        ListeToUseDetailsResponse response;
        try {
            List<ListeToUse> list = listToUseService.getListeToUseByUsage("JUBA_ENVOIE_PURPOSE");
            if (!list.isEmpty()) {
                response = new ListeToUseDetailsResponse(list);
            } else {
                response = new ListeToUseDetailsResponse(500, "Error List to use Parameter JUBA_ENVOIE_PURPOSE not Found");
            }
        } catch (Exception e) {
            System.out.println("Exception doGetPurpose ::: " + e.getMessage());
            response = new ListeToUseDetailsResponse(500, "Exception doGetPurpose ::: " + e.getMessage());
        }
        return response;
    }

    @Override
    public ListeToUseDetailsResponse doGetSourceOfIncome() {

        ListeToUseDetailsResponse response;
        try {
            List<ListeToUse> list = listToUseService.getListeToUseByUsage("JUBA_ENVOIE_SOURCE_OF_INCOME");
            if (list.size() > 0) {
                response = new ListeToUseDetailsResponse(list);
            } else {
                response = new ListeToUseDetailsResponse(500, "Error List to use Parameter JUBA_ENVOIE_SOURCE_OF_INCOME not Found");
            }
        } catch (Exception e) {
            System.out.println("Exception doGetSourcesOfIncome ::: " + e.getMessage());
            response = new ListeToUseDetailsResponse(500, "Exception doGetSourceOfIncome ::: " + e.getMessage());
        }
        return response;
    }

    @Override
    public ListeToUseDetailsResponse doGetCollectionMode() {

        ListeToUseDetailsResponse response;
        try {
            List<ListeToUse> list = listToUseService.getListeToUseByUsage("JUBA_ENVOIE_COLLECTION_MODE");
            if (list.size() > 0) {
                response = new ListeToUseDetailsResponse(list);
            } else {
                response = new ListeToUseDetailsResponse(500, "Error List to use Parameter JUBA_ENVOIE_COLLECTION_MODE not Found");
            }
        } catch (Exception e) {
            System.out.println("Exception doGetCollectionModes ::: " + e.getMessage());
            response = new ListeToUseDetailsResponse(500, "Exception doGetCollectionMode ::: " + e.getMessage());
        }
        return response;
    }

    @Override
    public GenericsResponse<GetTransfinQuote> doGetTarif(GetTransfinQuoteRequest request) {
        GenericsResponse<GetTransfinQuote> response;
        try {
            // do quotation
            System.out.println("request = " + request);
            Guichet guichet = guichetService.getById(euingProperties.getGuichetCode());
            if (guichet == null) {
                response = new GenericsResponse(500, "Error doGetTarif ::: Guichet not found", null);
            } else {
                Pays po = guichet.getAgence().getCompagnie().getPays();
                request.setOriginatingCurrency(po.getPsZmCode().getZmDevCode().getDevCode());

                Pays pd = paysService.find(request.getDestinationCountry());
                request.setDestinationCurrency(pd.getPsZmCode().getZmDevCode().getDevCode());

                com.ufi.euing.client.entity.Service service = (serviceService.getServiceByCode(JubaUtil.JUBA_SEND_SERVICE_CODE)).getData();

                GenericsResponse<CotationDetails> quotationResponse = this.doQuotation(request.getOriginatingCountry(), request.getLocalAmount() + "", request.getOriginatingCurrency(), request.getDestinationCurrency());
                if (quotationResponse.getResponseCode() == 200) {
                    GenericsResponse<CalculTarifInternational> feesResponse = transactionEuingService.calculTarifInterTransaction(guichet.getAgence().getCompagnie().getId(), guichet.getAgence().getCompagnie().getLibelle(), service.getId(), service.getNom(),
                            pd.getPsCode(), pd.getPsLibelle(), guichet.getAgence().getCompagnie().getModeCalculPartner(), request.getLocalAmount(), BigDecimal.valueOf(request.getUsersessionId()), request.getOriginatingCurrency(), request.getDestinationCurrency());
                    if (feesResponse.getResponseCode() == 200) {
                        CalculTarifInternational tarif = (CalculTarifInternational) feesResponse.getData();
                        System.out.println("tarif intern = " + tarif);
                        if (tarif != null && tarif.getRes() == 1) {
                            CotationDetails quotation = quotationResponse.getData();
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
                            quote.setPayoutAmount(quotation.getAmountInPayOut());
                            float frais;
                            if (quotation.getCommissionAmount() > tarif.getFees()) {
                                frais = (float) quotation.getCommissionAmount();
                            } else {
                                frais = (float) tarif.getFees();
                            }
                            quote.setCustomerCharge(frais);
                            double amtToPay = frais + tarif.getAmountSent();
                            quote.setCashTellerReceiver(amtToPay);
                            response = new GenericsResponse(quote);
                        } else {
                            response = new GenericsResponse(500, "Error doGetTarif ::: Tarif not found ", null);
                        }
                    } else {
                        response = new GenericsResponse(feesResponse.getResponseCode(), feesResponse.getResponseDescription(), null);
                    }
                } else {
                    response = new GenericsResponse(500, "Error doQuotation of doGetTarifJuba ::: " + quotationResponse.getResponseDescription(), null);
                }
            }
        } catch (Exception e) {
            System.out.println("Exception doGetTarifJuba ::: " + e.getMessage());
            response = new GenericsResponse(500, "Exception doGetTarifJuba ::: " + e.getMessage(), null);
        }
        return response;
    }

    @Override
    public GenericsResponse<TransactionEuing> doSendTransaction(TransactionDetailsEntity trxEntity) {

        GenericsResponse<TransactionEuing> response;
        try {
            GenericsResponse<Parametrews> p = parametrewsService.getParametrewsByCode("JUBA"); //get param web service
            if (p.getResponseCode() == 200) {
                Parametrews pws = (Parametrews) p.getData();

                Guichet guichet = guichetService.getById(euingProperties.getGuichetCode());
                Utilisateur usr = utilisateurService.find(euingProperties.getUtilisateurCode());
                //Agence agence = guichet.getAgence();
                //Compagnie compagnie = agence.getCompagnie();

                com.ufi.euing.client.entity.Service service = (serviceService.getServiceByCode(JubaUtil.JUBA_SEND_SERVICE_CODE)).getData();
                //Pays destCountry = (paysFacadeLocal.findPaysByCodeIso(trxEntity.getDestinationCountry())).getData();

                /*CalculTarifInternational tarif = (this.doGetTarif(compagnie.getId(), compagnie.getLibelle(), service.getId(), service.getNom(), destCountry.getPsCode(),
                        destCountry.getPsLibelle(), trxEntity.getLocalAmount(), usr.getUsrId(), trxEntity.getOriginatingCurrency(), trxEntity.getDestinationCurrency(),
                        trxEntity.getPartenaire().getCodePartenaire())).getData();*/
                GenericsResponse<TransactionEuing> trxRes = this.doBuildtransaction(trxEntity, pws, usr, service, guichet); //build transaction
                TransactionEuing trx = new TransactionEuing();
                if (trxRes.getResponseCode() == 200) {
                    trx = trxRes.getData();
                } else {
                    response = new GenericsResponse(trxRes.getResponseCode(), trxRes.getResponseDescription(), null);
                }

                String precheck = this.doPrecheckEnvoi(trx); //do precheck
                if ("".equals(precheck)) {
                    //do quotation
                    GenericsResponse<CotationDetails> quotationResponse = this.doQuotation(trxEntity.getPartenaire().getCodePartenaire(), trx.getTransAmountSent() + "", trx.getTransOriginCur().getDevCode(), trx.getTransDestCur().getDevCode());
                    if (quotationResponse.getResponseCode() == 200) {
                        CotationDetails quotation = quotationResponse.getData();
                        String customerReferenceNo = this.doRegisterCustomer(trx, trxEntity.getPartenaire().getDenommination(), false);
                        String beneficiaryReferenceNo = this.doRegisterCustomer(trx, trxEntity.getPartenaire().getDenommination(), true);
                        if (customerReferenceNo != null && beneficiaryReferenceNo != null) {
                            String partnerTransId = "" + new Date().getTime() + "" + trx.getTransGuichetId().getId();
                            SendRemittance request = new SendRemittance();
                            request.setQuotationId(quotation.getQuotationID());
                            request.setSenderCode(pws.getPwsMykeyPartner());
                            request.setNominatedCode(trxEntity.getPartenaire().getCodePartenaire());
                            request.setCustomerReferenceNo(customerReferenceNo);
                            request.setBeneficiaryReferenceNo(beneficiaryReferenceNo);
                            request.setPurpose(8);
                            request.setPurposeDescription(trx.getTransMotif());
                            request.setSourceOfIncome(5); // 5 = Savings
                            request.setSenderModeOfPayment(1); // 1 = cash
                            request.setReceiverModeOfPayment(1); // 1 - cash
                            if (null != trx.getNg5()) {
                                System.out.println("paying in an accout");
                                request.setBeneficiaryAccountNo(trx.getNg5());
                                request.setBeneficiaryAccountTitle(trx.getNg4());
                                request.setBeneficiaryAccountCurrency(trx.getTransDestCur().getDevCode());
                            }
                            request.setPayoutAmount(quotation.getAmountInPayOut());
                            request.setPayoutCurrency(trxEntity.getDestinationCurrency());
                            request.setSettlementCurrencyCode(pws.getPwsMyagentIdPartner());
                            request.setRemarks("Remarks");
                            request.setSendingCity(trx.getTransSenId().getSenCity());
                            request.setPartnerReferenceNum(partnerTransId);
                            request.setJubaCommisionInSettlement(quotation.getCommissionAmount());

                            JubaCallApi jubaCallApi = new JubaCallApi();
                            String result = jubaCallApi.callApiGenericJubaPOST(JubaUtil.JUBA_URI_X_VERSION_1, pws.getLogin(), pws.getPassword(), pws.getPwsWebServiceUrl() + JubaUtil.JUBA_URI_DO_SEND_TRANSACTIONS, request);
                            System.out.println("Result call API Juba doSendTransaction ::: " + result);

                            ObjectMapper mapper = new ObjectMapper();
                            SendRemittanceResponse sendRemittanceResponse = mapper.readValue(result, SendRemittanceResponse.class);
                            if (sendRemittanceResponse.response.code.equalsIgnoreCase("001")) {
                                String thirdPartyReference = sendRemittanceResponse.data.getReferenceNum();
                                System.out.println("thirdpartyRef ::: " + thirdPartyReference);

                                trx.setTransMttransactionnumber(thirdPartyReference);
                                trx.setTransMttransactionid(partnerTransId);
                                trx.setTransMtstatus(sendRemittanceResponse.response.code);
                                trx.setTransMtobservation(sendRemittanceResponse.response.message);

                                GenericsResponse resSaveTrx = transactionEuingService.createNewInterTransactionC2C(trx);
                                if (resSaveTrx.getResponseCode() == 200) {
                                    TransactionEuing transaction = (TransactionEuing) resSaveTrx.getData();
                                    response = new GenericsResponse(transaction);
                                } else {
                                    response = new GenericsResponse(resSaveTrx.getResponseCode(), "Error createNewInterTransactionC2C ::: " + resSaveTrx.getResponseDescription(), null);
                                }
                            } else {
                                response = new GenericsResponse(500, "Error doSendTransaction ::: code = " + sendRemittanceResponse.response.code + " message = " + sendRemittanceResponse.response.message, null);
                            }
                        } else {
                            response = new GenericsResponse(500, "Error registring customer", null);
                        }
                    } else {
                        response = new GenericsResponse(500, "Error doQuotation of doSendTransaction ::: ", null);
                    }
                } else {
                    response = new GenericsResponse(500, "Eroor precheck ::: " + precheck, null);
                }
            } else {
                response = new GenericsResponse(p.getResponseCode(), "Error doSendTransaction param ws " + p.getResponseDescription(), null);
            }
        } catch (IOException | NumberFormatException | EmailNotFoundException e) {
            System.out.println("Exception doSendTransaction ::: " + e.getMessage());
            response = new GenericsResponse(500, "Exception doSendTransaction ::: " + e.getMessage(), null);
        }
        return response;
    }

    @Override
    public GenericsResponse<TransactionDetailsEntity> doGetTransaction(CriterialSearchPayment criterial) {
        GenericsResponse<TransactionDetailsEntity> response;
        try {
            GenericsResponse<Parametrews> p = parametrewsService.getParametrewsByCode("JUBA");
            Utilisateur usr = utilisateurService.find(euingProperties.getUtilisateurCode());
            if (p.getResponseCode() == 200) {
                Parametrews pws = p.getT();
                DoGetPayRemittanceStatusDetails statusResponse = doGetPayRemittanceStatus(criterial.getReference(), pws);
                System.out.println("statusResponse = " + statusResponse);
                if (statusResponse.getStatus() == 5) {
                    DoGetTransactionsDetails detailsResponse = this.doGetDetail(criterial.getReference(), pws);
                    System.out.println("detailsResponse = " + detailsResponse);
                    if (detailsResponse != null) {
                        TransactionDetailsEntity trx = this.doBuildtransactionEntity(detailsResponse, usr.getUsrId().longValue());
                        System.out.println("trx = " + trx);
                        response = new GenericsResponse(trx);
                    } else {
                        response = new GenericsResponse(500, "Error doGetTransaction", null);
                    }
                } else {
                    response = new GenericsResponse(500, "Remittance " + criterial.getReference() + " Status : " + JubaUtil.getRemittanceStatus(statusResponse.getStatus()), null);
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


    DoGetTransactionsDetails doGetDetail(String reference, Parametrews pws) {
        DoGetTransactionsDetails response = null;
        try {
            RemitanceDetails request = new RemitanceDetails();
            request.setReferenceNum(reference);

            JubaCallApi jubaCallApi = new JubaCallApi();
            String result = jubaCallApi.callApiGenericJubaPOST(JubaUtil.JUBA_URI_X_VERSION_1, pws.getLogin(), pws.getPassword(), pws.getPwsWebServiceUrl() + JubaUtil.JUBA_URI_DO_GET_DETAIL, request);
            System.out.println("Result call API Juba doGetDetail ::: " + result);

            ObjectMapper mapper = new ObjectMapper();
            DoGetDetailsResponse details = mapper.readValue(result, DoGetDetailsResponse.class);
            if (details.response.code.equalsIgnoreCase("001")) {
                System.out.println("data doGetDetail ::: " + details.data);
                if ((details.data).size() > 0) {
                    response = details.data.get(0);
                }
            }
        } catch (IOException e) {
            System.out.println("Exception doGetDetail ::: " + e.getMessage());
        }
        return response;
    }

    TransactionDetailsEntity doBuildtransactionEntity(DoGetTransactionsDetails t, Long usrId) {
        TransactionDetailsEntity trx = new TransactionDetailsEntity();
        try {
            trx.setTransactionNumber(t.getReferenceNumber());
            trx.setTypeTransaction(2);
            trx.setUserSession(usrId);
            trx.setPartnerTransactionNo("//");
            trx.setCodeTransaction("//");
            trx.setStatusCode("V");
            trx.setStatusDescription("Authorized");
            trx.setTransactionDate("//");

            GenericsResponse paysSenderRes = paysService.findPaysByCodeIso(t.getCustomerCountry());
            if (paysSenderRes.getResponseCode() == 200) {
                Pays paysSender = (Pays) paysSenderRes.getData();
                trx.setOriginatingCountry(paysSender.getPsCode());
                trx.setOriginatingCurrency(paysSender.getPsZmCode().getZmDevCode().getDevCode());
                trx.setOriginatingTown(t.getCustomerCity());
            } else {
                trx.setOriginatingCountry("//");
                trx.setOriginatingCurrency("//");
                trx.setOriginatingTown("//");
                System.out.println("Error le pays " + t.getCustomerCountry() + " de l'expediture n'existe pas");
            }

            GenericsResponse paysBenefRes = paysService.findPaysByCodeIso(t.getBeneficiaryCountry());
            if (paysBenefRes.getResponseCode() == 200) {
                Pays paysBenef = (Pays) paysBenefRes.getData();
                trx.setDestinationCountry(paysBenef.getPsCode());
                trx.setDestinationTown(t.getBeneficiaryCity());
                trx.setDestinationCurrency(paysBenef.getPsZmCode().getZmDevCode().getDevCode());
            } else {
                trx.setDestinationCountry("//");
                trx.setDestinationTown("//");
                trx.setDestinationCurrency("//");
                System.out.println("Error le pays " + t.getBeneficiaryCountry() + " du beneficaire n'existe pas");
            }

            trx.setReference(t.getReferenceNumber());
            trx.setLocalAmount(t.getPayoutAmount());
            trx.setPayoutAmount(t.getPayoutAmount());

            SenderTransfinEntity senderDetails = new SenderTransfinEntity();
            senderDetails.setFirstName(t.getCustomerFirstName());
            senderDetails.setLastName((t.getCustomerMiddleName() == null) ? t.getCustomerLastName() : t.getCustomerMiddleName() + " " + t.getCustomerLastName());
            senderDetails.setAddress1(t.getCustomerCountry());
            senderDetails.setMobileNumber(t.getCustomerMobile());
            senderDetails.setCountry(t.getCustomerCountry());
            senderDetails.setCity(t.getCustomerCity());
            senderDetails.setEmail(t.getCustomerEmail());
            trx.setSenderDetails(senderDetails);

            SenderTransfinIdEntity senderIdDetails = new SenderTransfinIdEntity();
            if (!t.getCustomerDocuments().isEmpty()) {
                CustomerDocument docSender = t.getCustomerDocuments().get(0);
                senderIdDetails.setCodeTypePiece("PAS");
                senderIdDetails.setId1Number(docSender.getDocumentNumber());
                senderIdDetails.setDateOfIssue(("".equals(docSender.getIssueDate())) ? null : docSender.getIssueDate());
                senderIdDetails.setDateOfExpiry(("".equals(docSender.getExpiryDate())) ? null : docSender.getExpiryDate());
                senderIdDetails.setCountryOfIssue(docSender.getCountry());
            }
            trx.setSenderIdDetails(senderIdDetails);

            ReceiverTransfinEntity receiverDetails = new ReceiverTransfinEntity();
            receiverDetails.setFirstName(t.getBeneficiaryFirstName());
            receiverDetails.setLastName((t.getBeneficiaryMiddleName() == null) ? t.getBeneficiaryLastName() : t.getBeneficiaryMiddleName() + " " + t.getBeneficiaryLastName());
            receiverDetails.setAddress1(t.getBeneficiaryAddress());
            receiverDetails.setMobileNumber(t.getBeneficiaryMobile());
            receiverDetails.setCountry(t.getBeneficiaryCountry());
            receiverDetails.setCity(t.getBeneficiaryCity());
            receiverDetails.setEmail(t.getBeneficiaryEmail());
            trx.setReceiverDetails(receiverDetails);

            ReceiverTransfinPayEntity receiverPaymentDetails = new ReceiverTransfinPayEntity();
            receiverPaymentDetails.setTestAnswer("//");
            receiverPaymentDetails.setTestQuestion("//");
            receiverPaymentDetails.setDataForServices("//");
            receiverPaymentDetails.setReasonForPayment(JubaUtil.getTransactionPurpose(Integer.valueOf(t.getPurpose())));
            trx.setReceiverPaymentDetails(receiverPaymentDetails);

            trx.setReceiverIdDetails(new ReceiverTransfinIdEntity());

            Partenaire partenaire = new Partenaire();
            com.ufi.euing.client.entity.Service service = (serviceService.getServiceByCode(JubaUtil.JUBA_PAY_SERVICE_CODE)).getData();
            partenaire.setCodePartenaire(service.getId() + "");
            partenaire.setDenommination(service.getNom());
            trx.setPartenaire(partenaire);

            //trx.setIdType();
        } catch (Exception e) {
            System.out.println("Error doBuildtransaction Paiement ::: " + e.getMessage());
        }
        return trx;
    }

    String doPrecheckPaiement(TransactionEuing trx) {
        String result = "";
        try {
            GenericsResponse response = transactionEuingService.precheckPaymentTransaction(trx);
            result = (String) response.getData();
        } catch (Exception e) {
            System.out.println("Exception doPrecheckPaiement ::: " + e.getMessage());
        }
        return result;
    }

    TransactionEuing doBuildtransactionForPayment(TransactionDetailsEntity trxEntity, Parametrews parametrews) {
        TransactionEuing trx = new TransactionEuing();
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Guichet guichet = guichetService.getById(euingProperties.getGuichetCode());
            Utilisateur usr = utilisateurService.find(euingProperties.getUtilisateurCode());
            com.ufi.euing.client.entity.Service service = (serviceService.getServiceByCode(JubaUtil.JUBA_PAY_SERVICE_CODE)).getData();
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
            sender.setSenDob(new Date());
            sender.setSenPostalCode(trxEntity.getSenderDetails().getPostCode());
            sender.setSenIdDocumentNumber(trxEntity.getSenderIdDetails().getId1Number());
            TypePieceIdentite tpi = new TypePieceIdentite();
            GenericsResponse tpiSenderRes = typePieceIdentiteService.getTypePieceIdentiteByCode(trxEntity.getSenderIdDetails().getCodeTypePiece());
            if (tpiSenderRes.getResponseCode() == 200) {
                tpi.setTpiCode(((TypePieceIdentite) tpiSenderRes.getData()).getTpiCode());
                tpi.setTpiLibelle(((TypePieceIdentite) tpiSenderRes.getData()).getTpiLibelle());
            } else {
                ParametreBase pb = parametreBaseService.find("DEFAULT_TPI");
                GenericsResponse pbRes = typePieceIdentiteService.getTypePieceIdentiteByCode(pb.getValeur());
                tpi.setTpiCode(((TypePieceIdentite) pbRes.getData()).getTpiCode());
                tpi.setTpiLibelle(((TypePieceIdentite) pbRes.getData()).getTpiLibelle());
            }
            sender.setSenIdDocumentType(tpi);
            sender.setSenIdDocumentDelDate(("".equals(trxEntity.getSenderIdDetails().getDateOfIssue())) ? null : new SimpleDateFormat("dd/MM/yyyy").parse(trxEntity.getSenderIdDetails().getDateOfIssue()));
            sender.setSenIdDocumentExpDate(("".equals(trxEntity.getSenderIdDetails().getDateOfExpiry())) ? null : new SimpleDateFormat("dd/MM/yyyy").parse(trxEntity.getSenderIdDetails().getDateOfExpiry()));
            sender.setSenOccupation(trxEntity.getSenderIdDetails().getOccupation());
            sender.setSenCityId(agence.getVille());
            sender.setSenCity((trxEntity.getSenderDetails().getCity() == null) ? "N/A" : trxEntity.getSenderDetails().getCity());
            sender.setSenPhoneNumber1(trxEntity.getSenderDetails().getMobileNumber());
            sender.setSenPhoneNumber2(trxEntity.getSenderDetails().getPhoneNumber());
            sender.setSenEmail(trxEntity.getSenderDetails().getEmail());
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
            trx.setTransSens("C2C_PAIEMENT_JUBA");
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

    @Override
    public GenericsResponse<TransactionEuing> doPaymentConfirmation(TransactionDetailsEntity trxEntity) {
        GenericsResponse<TransactionEuing> response;
        try {
            GenericsResponse<Parametrews> p = parametrewsService.getParametrewsByCode("JUBA");
            if (p.getResponseCode() == 200) {
                Parametrews pws = (Parametrews) p.getData();
                //DoGetTransactionsDetails trxDetails = this.doGetDetail(trxEntity.getReference(), pws);
                TransactionEuing trx = this.doBuildtransactionForPayment(trxEntity, pws);
                String precheck = this.doPrecheckPaiement(trx); //do precheck
                if ("".equals(precheck)) {
                    PaymentConfirmation request = new PaymentConfirmation();
                    request.setReferenceNum(trx.getTransMttransactionnumber());
                    request.setPayerCode(pws.getPwsMykeyPartner());
                    request.setBeneficiaryStreetNo(null);
                    request.setBeneficiaryHouse(null);
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    request.setBeneficiaryDateOfBrith((trx.getTransBenId().getBenDob() == null) ? null : formatter.format(trx.getTransBenId().getBenDob()));
                    request.setBeneficiaryEmail(trx.getTransBenId().getBenEmail());
                    request.setBeneficiaryTelephone(trx.getTransBenId().getBenPhoneNumber1());
                    request.setBeneficiaryState(trx.getTransBenId().getBenCity());
                    request.setBeneficiaryPlaceOfBirth(null);
                    request.setBeneficiaryNationality(trx.getTransBenId().getBenNationality());
                    request.setBeneficiaryPostalCode(trx.getTransBenId().getBenPostalCode());
                    request.setBeneficiaryOccupation(trx.getTransBenId().getBenOccupation());
                    request.setBeneficiaryRemarks("Paid Remittance " + trx.getTransMttransactionnumber());
                    String transRefCode = "" + new Date().getTime() + "" + trx.getTransGuichetId().getId();
                    request.setPartnerTranReferenceNum(transRefCode);
                    System.out.println("request ::: " + request);

                    JubaCallApi jubaCallApi = new JubaCallApi();
                    String result = jubaCallApi.callApiGenericJubaPOST(JubaUtil.JUBA_URI_X_VERSION_1, pws.getLogin(), pws.getPassword(), pws.getPwsWebServiceUrl() + JubaUtil.JUBA_URI_DO_PAYMENT_CONFIRMATION, request);
                    System.out.println("Result call API Juba doPaymentConfirmation ::: " + result);

                    ObjectMapper mapper = new ObjectMapper();
                    PaymentConfirmationResponse payResponse = mapper.readValue(result, PaymentConfirmationResponse.class);
                    if (payResponse.response.code.equalsIgnoreCase("001")) {
                        trx.setTransReference(transRefCode);
                        System.out.println("save transaction");
                        GenericsResponse resSaveTrx = transactionEuingService.payTransactionC2COPI(trx);
                        if (resSaveTrx.getResponseCode() == 200) {
                            TransactionEuing transaction = (TransactionEuing) resSaveTrx.getData();
                            response = new GenericsResponse(transaction);
                        } else {
                            response = new GenericsResponse(500, "Error createNewInterTransactionC2C " + resSaveTrx.getResponseCode() + " - " + resSaveTrx.getResponseDescription(), null);
                        }
                    } else {
                        response = new GenericsResponse(500, "Error doPaymentConfirmation ::: Code = " + payResponse.response.code + "; Message = " + payResponse.response.message, null);
                    }
                } else {
                    response = new GenericsResponse(500, "Eroor precheck ::: " + precheck, null);
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

    @Override
    public GenericsResponse<DoGetBeneficiaryNameDetails> jubaDoGetBeneficiaryName(String mobileNo) {
        GenericsResponse<DoGetBeneficiaryNameDetails> response;
        try {
            GenericsResponse<Parametrews> p = parametrewsService.getParametrewsByCode("JUBA");
            if (p.getResponseCode() == 200) {
                Parametrews pws = p.getT();
                DoGetBeneficiaryName request = new DoGetBeneficiaryName();
                request.setMobileNo(mobileNo);

                JubaCallApi jubaCallApi = new JubaCallApi();
                String result = jubaCallApi.callApiGenericJubaPOST(JubaUtil.JUBA_URI_X_VERSION_1, pws.getLogin(), pws.getPassword(), pws.getPwsWebServiceUrl() + JubaUtil.JUBA_URI_DO_GET_BENEFICIARY_NAME, request); // do quotation
                System.out.println("Result call API Juba doGetBeneficiarName ::: " + result);

                ObjectMapper mapper = new ObjectMapper();
                DoGetBeneficiaryNameResponse benNameResponse = mapper.readValue(result, DoGetBeneficiaryNameResponse.class);
                if (benNameResponse.response.code.equalsIgnoreCase("001")) {
                    DoGetBeneficiaryNameDetails details = benNameResponse.data;
                    System.out.println("details = " + details);
                    response = new GenericsResponse(details);
                } else {
                    response = new GenericsResponse(500, "Error doGetBeneficiarName ::: code = " + benNameResponse.response.code + " message = " + benNameResponse.response.message, null);
                }
            } else {
                response = new GenericsResponse(p.getResponseCode(), "Error doGetBeneficiarName :::  " + p.getResponseDescription(), null);
            }
        } catch (IOException e) {
            System.out.println("Exception doGetBeneficiarName ::: " + e.getMessage());
            response = new GenericsResponse(500, "Exception doGetBeneficiarName ::: " + e.getMessage(), null);
        }
        return response;
    }

    GenericsResponse<CotationDetails> doQuotation(String agentCode, String amount, String originCurrency, String destCurrency) {
        GenericsResponse<CotationDetails> response;
        try {
            GenericsResponse<Parametrews> p = parametrewsService.getParametrewsByCode("JUBA");
            if (p.getResponseCode() == 200) {
                Parametrews pws = p.getT();
                CreateTransactionsCotation request = new CreateTransactionsCotation();
                request.setNominatedCode(agentCode);
                request.setSendingAmount(amount);
                request.setSendingCurrencyCode(originCurrency);
                request.setPayingCurrencyCode(destCurrency);
                request.setAmountType("SendingAmount");
                request.setPayingAmount("");
                request.setTotalAmount("");

                JubaCallApi jubaCallApi = new JubaCallApi();
                String result = jubaCallApi.callApiGenericJubaPOST(JubaUtil.JUBA_URI_X_VERSION_1, pws.getLogin(), pws.getPassword(), pws.getPwsWebServiceUrl() + JubaUtil.JUBA_URI_DO_CREATE_TRANSACTION_QUOTATION, request); // do quotation
                System.out.println("Result call API Juba doQuotation ::: " + result);

                ObjectMapper mapper = new ObjectMapper();
                CotationResponse cotationResponse = mapper.readValue(result, CotationResponse.class);
                System.out.println("cotationResponse.response.code === " + cotationResponse.response.code);
                if (cotationResponse.response.code.equalsIgnoreCase("001")) {
                    CotationDetails quotation = cotationResponse.data;
                    System.out.println("quotation = " + quotation);
                    response = new GenericsResponse(quotation);
                } else {
                    response = new GenericsResponse(500, "Error doQuotation ::: code = " + cotationResponse.response.code + " message = " + cotationResponse.response.message, null);
                }
            } else {
                response = new GenericsResponse(p.getResponseCode(), "Error doQuotation :::  " + p.getResponseDescription(), null);
            }
        } catch (IOException e) {
            System.out.println("Exception doQuotation ::: " + e.getMessage());
            response = new GenericsResponse(500, "Exception doQuotation ::: " + e.getMessage(), null);
        }
        return response;
    }

    String doPrecheckEnvoi(TransactionEuing trx) {
        String result = "";
        try {
            GenericsResponse response = transactionEuingService.precheckEnvoiInterC2C(trx);
            result = (String) response.getData();
        } catch (Exception e) {
            System.out.println("Exception doPrecheckEnvoi ::: " + e.getMessage());
        }
        return result;
    }

    String doRegisterCustomer(TransactionEuing trx, String agentCity, boolean isBeneficiary) {
        String regNo = null;
        try {
            GenericsResponse<Parametrews> p = parametrewsService.getParametrewsByCode("JUBA");
            if (p.getResponseCode() == 200) {
                Parametrews pws = (Parametrews) p.getData();
                DoRegisterCustomer customer = new DoRegisterCustomer();
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                if (isBeneficiary) {
                    customer.setFirstName(trx.getTransBenId().getBenFirstname());
                    customer.setLastName(trx.getTransBenId().getBenLastname());
                    customer.setStreetNo(trx.getTransBenId().getBenAddress());
                    customer.setDateOfBrith(null);
                    customer.setEmail(trx.getTransBenId().getBenEmail());
                    customer.setTelephone(trx.getTransBenId().getBenPhoneNumber2());
                    customer.setMobile(trx.getTransBenId().getBenPhoneNumber1());
                    customer.setCity(agentCity);
                    customer.setState(trx.getTransBenId().getBenCity());
                    customer.setNationality(trx.getTransBenId().getBenNationalityId().getPsLibelle());
                    customer.setPostalCode(trx.getTransBenId().getBenPostalCode());
                    customer.setOccupation(trx.getTransBenId().getBenOccupation());
                } else {
                    customer.setFirstName(trx.getTransSenId().getSenFirstname());
                    customer.setLastName(trx.getTransSenId().getSenLastname());
                    customer.setStreetNo(trx.getTransSenId().getSenAddress());
                    customer.setDateOfBrith((trx.getTransSenId().getSenDob().compareTo(new Date()) > 0) ? formatter.format(trx.getTransSenId().getSenDob()) : "01/01/1970");
                    customer.setEmail(trx.getTransSenId().getSenEmail());
                    customer.setTelephone(trx.getTransSenId().getSenPhoneNumber2());
                    customer.setMobile(trx.getTransSenId().getSenPhoneNumber1());
                    customer.setCity("DOU"); // Douala = DOU, Yaounde = YAO
                    customer.setState(trx.getTransSenId().getSenCity());
                    customer.setNationality(trx.getTransSenId().getSenNationalityId().getPsLibelle());
                    customer.setPostalCode(trx.getTransSenId().getSenPostalCode());
                    customer.setOccupation(trx.getTransSenId().getSenOccupation());
                }
                customer.setFirstName(customer.getFirstName().replaceAll("\\d", ""));
                customer.setMiddleName(null);
                customer.setLastName(customer.getLastName().replaceAll("\\d", ""));
//                if (customer.getMobile().length() > 9) {
//                    customer.setMobile(customer.getMobile().substring(customer.getMobile().length() - 9));
//                }
//                if (customer.getTelephone().length() > 9) {
//                    customer.setTelephone(customer.getTelephone().substring(customer.getTelephone().length() - 9));
//                }
                customer.setState(customer.getState().replaceAll("\\d", ""));
                customer.setPlaceOfBirth(null);
                customer.setHouse(null);
                customer.setRemarks(null);
                customer.setCustomerReferenceNo(null);
                customer.setIsBeneficiary(isBeneficiary);
                System.out.println("customer ::: " + customer);

                JubaCallApi jubaCallApi = new JubaCallApi();
                String result = jubaCallApi.callApiGenericJubaPOST(JubaUtil.JUBA_URI_X_VERSION_1, pws.getLogin(), pws.getPassword(), pws.getPwsWebServiceUrl() + JubaUtil.JUBA_URI_DO_REGISTER_CUSTOMER, customer);
                System.out.println("Result call API Juba doRegisterCustomer ::: " + result);

                ObjectMapper mapper = new ObjectMapper();
                DoRegisterResponse registerCustomer = mapper.readValue(result, DoRegisterResponse.class);
                if (registerCustomer.response.code.equalsIgnoreCase("001")) {
                    regNo = registerCustomer.data.getCustomerReferenceNo();
                }
            }
        } catch (IOException e) {
            System.out.println("Exception doRegisterCustomer ::: " + e.getMessage());
        }
        System.out.println("regNo ::: " + isBeneficiary + " === " + regNo);
        return regNo;
    }

    DoGetPayRemittanceStatusDetails doGetPayRemittanceStatus(String reference, Parametrews pws) {
        DoGetPayRemittanceStatusDetails statusDetails = null;
        try {
            DoGetPayRemittanceStatusRequest request = new DoGetPayRemittanceStatusRequest();
            request.setFromDate("");
            request.setToDate("");
            request.setReferenceNum(reference);
            JubaCallApi jubaCallApi = new JubaCallApi();
            String result = jubaCallApi.callApiGenericJubaPOST(JubaUtil.JUBA_URI_X_VERSION_1, pws.getLogin(), pws.getPassword(), pws.getPwsWebServiceUrl() + JubaUtil.JUBA_URI_DO_GET_PAY_REMITTANCE_STATUS, request);
            System.out.println("Result call API Juba doGetPayRemittanceStatus ::: " + result);

            ObjectMapper mapper = new ObjectMapper();
            DoGetPayRemittanceStatusResponse status = mapper.readValue(result, DoGetPayRemittanceStatusResponse.class);
            if (status.response.code.equalsIgnoreCase("001")) {
                List<DoGetPayRemittanceStatusDetails> data = status.data;
                System.out.println("data doGetPayRemittanceStatus ::: " + data);
                if (data.size() > 0) {
                    statusDetails = data.get(0);
                }
            }
        } catch (IOException e) {
            System.out.println("Exception doGetPayRemittanceStatus ::: " + e.getMessage());
        }
        return statusDetails;
    }

    GenericsResponse<TransactionEuing> doBuildtransaction(TransactionDetailsEntity trxEntity, Parametrews parametrews, Utilisateur usr, com.ufi.euing.client.entity.Service service, Guichet guichet) {
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
            //Pays destCountry = (paysService.getCountryByCode(trxEntity.getDestinationCountry())).getData();
            Pays destCountry = paysService.getCountryByCode(trxEntity.getDestinationCountry());
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
                //create sender
                GenericsResponse<Sender> createSenderResponse = senderService.createSender(sender);
                if (createSenderResponse.getResponseCode() == 200) {
                    sender = createSenderResponse.getData();
                } else {
                    response = new GenericsResponse(createSenderResponse.getResponseCode(), "Error create sender ::: " + createSenderResponse.getResponseDescription(), null);
                    terminar = true;
                }
                System.out.println("sender created ::: " + sender);
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
