package com.ufi.euing.client.business.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufi.euing.client.business.*;
import com.ufi.euing.client.entity.*;
import com.ufi.euing.client.integration.billpay.*;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.util.*;

@Component
public class BillCamwaterServiceImpl implements BillCamwaterService {

    final TransactionBillService transactionBillService;

    final ParametreBaseService parametreBaseService;

    final ParametrewsService parametrewsService;

    final EventBillService eventBillService;

    public BillCamwaterServiceImpl(TransactionBillService transactionBillService,
                                   ParametreBaseService parametreBaseService,
                                   ParametrewsService parametrewsService,
                                   EventBillService eventBillService) {
        this.transactionBillService = transactionBillService;
        this.parametreBaseService = parametreBaseService;
        this.parametrewsService = parametrewsService;
        this.eventBillService = eventBillService;
    }


    @Override
    public BillSearchResponseCamwater consultBillsCamwater(String codePartenaire, String type, String value, String requestType) {
        String result = null;
        ObjectMapper objectMapper = new ObjectMapper();
        BillSearchResponseCamwater response = new BillSearchResponseCamwater();
        RequestCamwater requestCamwater = new RequestCamwater();
        try {
            GenericsResponse<Parametrews> p = parametrewsService.getParametrewsByPartnerCode(codePartenaire);
            Parametrews parametrews = p.getT();
            String url = parametrews.getPwsWebServiceUrl();
            String partner = parametrews.getPwsMyagentIdPartner();
            if (requestType.equalsIgnoreCase("DIRECT")) {

                String opCode = parametrews.getLogin();
                String key = parametrews.getPassword();
                String token = parametrews.getTokenautorized();
                String secret = parametrews.getPwsMykeyPartner();
                String hmacSha1Algo = parametrews.getAutorized();
                String defaultDateLimit = parametrews.getPwsMystatutIdPartner();
                Long timestamp = new Date().getTime();
                // Ajout des informations au paramètres de la requête
                requestCamwater.setAccountNumber(value);
                requestCamwater.setCode(opCode);
                requestCamwater.setTimestamp(timestamp);
                // Calcul de hash et signature de la requête
                String hash = "";
                String signature = "";
                try {
                    signature = UtilsBillPay.getSignature(token + "" + timestamp, secret, hmacSha1Algo);
                    hash = UtilsBillPay.getHash(requestCamwater, UtilsBillPay.TYPE_ACCOUNT_INFO, key);
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                requestCamwater.setHash(hash);
                requestCamwater.setSignature(signature);
                System.out.println("requestCamwater = " + requestCamwater);

                String endPointConsult = url + "" + UtilsBillPay.CAMWATER_URI_CONSULT + ""
                        + "?merchantCode=" + codePartenaire + "&partner=" + partner + "" + "&type=" + type + "&value=" + value;

                System.out.println("endPointConsult = " + endPointConsult);
                BillCallHTTP callHTTPCamwater = new BillCallHTTP();
                result = callHTTPCamwater.callRestCheckCamwater(requestCamwater, endPointConsult, key);
                System.out.println("result consultBillsCamwater = " + result);

                //ObjectMapper objectMapper = new ObjectMapper();
                ResponseCamwater responseCamwater = new ResponseCamwater();
                try {
                    responseCamwater = objectMapper.readValue(result, ResponseCamwater.class);
                } catch (JsonProcessingException e1) {
                    e1.printStackTrace();
                }
                if (responseCamwater == null) {
                    response = new BillSearchResponseCamwater(403, UtilsBillPay.MSG_ERROR_NETWORK, null);
                }
                if (responseCamwater.getStatus() == 104) {
                    response = new BillSearchResponseCamwater(404, responseCamwater.getMessage(), null);
                }
                List<ResponseCamwater> bills = new ArrayList<>();
                if (responseCamwater.getStatus() == 200 && responseCamwater.getBalance() != 0) {
                    // Exclure les factures au solde nul de la liste à retourner
                    if (responseCamwater.getUnpaid() != 0) {
                        bills.add(responseCamwater);
                    }
                    response = new BillSearchResponseCamwater(200, UtilsBillPay.BILL_STATUS_UNPAID, bills);
                } else {
                    response = new BillSearchResponseCamwater(200, UtilsBillPay.BILL_ALREADY_PAID, bills);
                }
                if (responseCamwater.getCode() == 404) {
                    response = new BillSearchResponseCamwater(404, UtilsBillPay.ACCOUNT_NOT_FOUND + " " + responseCamwater.getMessage(), null);
                }
                System.out.println("response = " + response);

            } else {
                ResponseMilddelwareConsult responseMilddelwareConsult = new ResponseMilddelwareConsult();
                ParametreBase parametreBase = parametreBaseService.find("URL_MIDDLEWARE_CONSULT");
                String endPointConsult = parametreBase.getValeur()
                        + "?merchantCode=" + codePartenaire + "&partner=" + partner + "" + "&type=" + type + "&value=" + value;
                System.out.println("endPointConsult = " + endPointConsult);
                BillCallHTTP callHTTPMilddelWare = new BillCallHTTP();
                result = callHTTPMilddelWare.ConsulAndCkeckBillByMiddleware(endPointConsult);
                System.out.println("result consultBillsBy Middl= " + result);
                try {

                    responseMilddelwareConsult = objectMapper.readValue(result, ResponseMilddelwareConsult.class);
                    if (responseMilddelwareConsult.getStatus() == 200) {
                        List<ResponseCamwater> bills = new ArrayList<>();
                        for (BillsMilddelWare billsMilddelWare : responseMilddelwareConsult.getBills()) {
                            ResponseCamwater responseCamwater = new ResponseCamwater();
                            responseCamwater.setStatus(responseMilddelwareConsult.getStatus());
                            responseCamwater.setBalance(Long.parseLong(billsMilddelWare.getBillAmount()));
                            responseCamwater.setCustomerName(billsMilddelWare.getCustomerName());
                            responseCamwater.setDateLimit(billsMilddelWare.getBillDueDate());
                            responseCamwater.setIdTransaction(billsMilddelWare.getBillNumber());
                            responseCamwater.setMessage(billsMilddelWare.getBillStatus());
                            responseCamwater.setIdTransaction(billsMilddelWare.getBillId());
                            responseCamwater.setMinToPay(Long.parseLong(billsMilddelWare.getBillAmount()));
                            if (responseCamwater.getBalance() != 0) {
                                bills.add(responseCamwater);
                            }
                        }
                        response = new BillSearchResponseCamwater(200, UtilsBillPay.BILL_STATUS_UNPAID, bills);
                    }
                } catch (JsonProcessingException e) {
                    response = new BillSearchResponseCamwater(500, "ERROR", null);

                }
            }

        } catch (Exception e) {
            System.out.println("Service Search CAMWATER Failed ::: HTTP error code = " + e.getMessage());
            response.setCode(500);
            response.setMessage(result);
        }
        return response;
    }

    @Override
    public GenericsResponse<TransactionBill> payBillCamwater(Compagnie compagnie, Guichet guichet, Facture facture, Service service, Utilisateur user, String depositorName, String depositorPhone, String requestType) {
        RequestCamwater requestCamwater = new RequestCamwater();
        String result = null;
        GenericsResponse genericsResponse = new GenericsResponse<>();
        try {
            int status = 200;
            String payerParEstel;
            ParametreBase param = parametreBaseService.find(UtilsBillPay.PARAM_BASE_PAYER_CAMWATER_PAR_ESTEL);
            if (param != null) {
                payerParEstel = param.getValeur();
            } else {
                return new GenericsResponse(404, UtilsBillPay.PARAM_BASE_PAYER_CAMWATER_PAR_ESTEL + " NOT CONFIGURED", null);
            }
            EstelResponse estelResponse = new EstelResponse();
            if (payerParEstel.equalsIgnoreCase("1")) {
                GenericsResponse<Parametrews> p = parametrewsService.getParametrewsByPartnerCode(UtilsBillPay.CODE_MARCHAND_EUMM);
                Parametrews parametrews = p.getT();
                String toHash = parametrews.getLogin() + parametrews.getPassword() + UtilsBillPay.CAMWATER_BILLER_CODE + facture.getNumeroAbonne() + parametrews.getPwsMykeyPartner();
                String hashed = UtilsBillPay.hash(toHash, "MD5");
                String url = parametrews.getPwsWebServiceUrl() + UtilsBillPay.URI_ESTEL_PAY_BILL;

                Map<String, String> params = new HashMap<>();
                params.put("id", parametrews.getLogin());
                params.put("pwd", parametrews.getPassword());
                params.put("hash", hashed);
                params.put("billno", facture.getNumeroAbonne());
                params.put("biller", UtilsBillPay.CAMWATER_BILLER_CODE);

                System.out.println("url = " + url);
                System.out.println("params = " + params);
                BillCallHTTP callHTTP = new BillCallHTTP();
                String resutat = callHTTP.postToEstel(url, params);
                System.out.println("resutat = " + resutat);
                ObjectMapper objectMapper = new ObjectMapper();
                estelResponse = objectMapper.readValue(resutat, EstelResponse.class);
                if (!estelResponse.getStatut().equalsIgnoreCase("200")) {
                    status = 500;
                }
                genericsResponse.setResponseCode(status);
                genericsResponse.setResponseDescription(estelResponse.getMessage());
            }

            if (status == 200) {
                TransactionBill transaction = new TransactionBill();
                transaction.setTransGuichet(guichet.getLibelle());
                transaction.setTransAgency(guichet.getAgence().getLibelle());
                transaction.setTransCompany(guichet.getAgence().getCompagnie().getLibelle());
                transaction.setTransGroup(guichet.getAgence().getCompagnie().getSuccursale().getLibelle());
                transaction.setTransCasher(user.getUsrLogin());
                transaction.setTransReference(null);
                transaction.setTransOriginCountry(compagnie.getPays().getPsLibelle());
                transaction.setTransDestCountry(guichet.getAgence().getCompagnie().getPays().getPsLibelle());
                transaction.setTransExchangeRate(facture.getExchangeRate());
                transaction.setTransExchangeRateCust(null);
                transaction.setTransExchangeRateMargin(null);
                transaction.setTransAmountSent(facture.getMontant());
                transaction.setTransOthersTaxes(facture.getOtherfees());
                transaction.setTransFees(facture.getFees());
                transaction.setTransTaxes(null);
                transaction.setTransTotal(facture.getTotalToPaid());
                transaction.setTransServiceName(service.getNom());
                transaction.setTransComisCompanie(null);
                transaction.setTransComisGroupe(null);
                transaction.setTransComisSysteme(null);
                transaction.setTransDateCreate(new Date());
                transaction.setTransDateModif(new Date());
                transaction.setTransUserCreate(user.getUsrLogin());
                transaction.setTransUserModif(user.getUsrLogin());
                transaction.setTransComisGuichet(null);
                transaction.setTransactionStatus("I");
                transaction.setDateCompleted(new Date());
                transaction.setTransactionReturnCode(null);
                transaction.setTransactionReturnMessage(null);
                transaction.setThirdPartyReference(null);
                transaction.setFacture(facture);
                transaction.setTransDestCompagnieId(compagnie);
                transaction.setTransDestCompagnieCode(guichet.getAgence().getCompagnie().getCodePartenaire());
                transaction.setTransGuichetId(guichet);
                transaction.setTransAgenceId(guichet.getAgence());
                transaction.setTransCompagnieId(guichet.getAgence().getCompagnie());
                transaction.setTransGroupId(guichet.getAgence().getCompagnie().getSuccursale());
                transaction.setTransUserid(user);
                transaction.setTransServiceId(service);
                transaction.setTransOriginCountryId(compagnie.getPays());
                transaction.setTransDestCountryId(guichet.getAgence().getCompagnie().getPays());
                transaction.setDepositorName(depositorName);
                transaction.setDepositorPhone(depositorPhone);
                transaction.setTrans1(null);
                transaction.setTrans2(null);
                transaction.setTrans3(null);
                transaction.setTrans4(null);
                transaction.setTrans5(null);
                transaction.setTransOriginCur(compagnie.getDeviseCompensation().getDevCode());
                transaction.setTransDestCur(guichet.getAgence().getCompagnie().getDeviseCompensation().getDevCode());

                GenericsResponse<TransactionBill> response = transactionBillService.insertTrxBillPay(transaction);
                System.out.println("response = " + response);
                if (response != null && response.getResponseCode() == 200) {
                    if (payerParEstel.equalsIgnoreCase("1")) {
                        transaction = transactionBillService.findById(response.getT().getId());
                        System.out.println("transaction ID " + transaction.getId());
                        transaction.setTransactionStatus("P");
                        transaction.setTransactionReturnCode(estelResponse.getStatut());
                        transaction.setTransactionReturnMessage(estelResponse.getMessage());
                        transaction.setThirdPartyReference(estelResponse.getTransaction());
                        transaction.setTransDateModif(new Date());
                        System.out.println("thirdPartyReference camwater " + transaction.getThirdPartyReference());
                        transactionBillService.edit(transaction);

                        TypeEvent typeEve = new TypeEvent();
                        typeEve.setId(Long.valueOf(10));
                        EventsBill eveBill = new EventsBill();
                        eveBill.setEveTransId(transaction);
                        eveBill.setEveType(typeEve);
                        eveBill.setEveUserId(transaction.getTransUserid());
                        eveBill.setEveDate(transaction.getTransDateModif());
                        eveBill.setEveComment("");
                        eventBillService.create(eveBill);

                        genericsResponse.setResponseCode(200);
                        genericsResponse.setResponseDescription(estelResponse.getMessage());
                        genericsResponse.setT(transaction);
                    } else {
                        GenericsResponse<Parametrews> p = parametrewsService.getParametrewsByPartnerCode(compagnie.getCodePartenaire());
                        Parametrews parametrews = p.getT();
                        String url = parametrews.getPwsWebServiceUrl();
                        String partner = parametrews.getPwsMyagentIdPartner();
                        String opCode = parametrews.getLogin();
                        String key = parametrews.getPassword();
                        String token = parametrews.getTokenautorized();
                        String secret = parametrews.getPwsMykeyPartner();
                        String hmacSha1Algo = parametrews.getAutorized();
                        String defaultDateLimit = parametrews.getPwsMystatutIdPartner();
                        Long timestamp = new Date().getTime();

                        if (requestType.equalsIgnoreCase("DIRECT")) {
                            // Ajout des informations au paramètres de la requête
                            requestCamwater.setAccountNumber(facture.getNumeroAbonne());
                            requestCamwater.setCode(opCode);
                            requestCamwater.setTimestamp(timestamp);
                            requestCamwater.setPhone(depositorPhone);
                            requestCamwater.setAmount(Double.valueOf(facture.getMontant().toString()));
                            requestCamwater.setIdTransaction(response.getT().getTransReference());
                            // Calcul de hash et signature de la requête
                            String hash = "";
                            String signature = "";
                            try {
                                signature = UtilsBillPay.getSignature(token + "" + timestamp, secret, hmacSha1Algo);
                                hash = UtilsBillPay.getHash(requestCamwater, UtilsBillPay.TYPE_ACCOUNT_PAY, key);
                            } catch (NoSuchAlgorithmException e) {
                                e.printStackTrace();
                            }
                            requestCamwater.setHash(hash);
                            requestCamwater.setSignature(signature);
                            System.out.println("requestCamwater = " + requestCamwater);

                            String endPointPay = url + UtilsBillPay.CAMWATER_URI_PAY;
                            BillCallHTTP callHTTPCamwater = new BillCallHTTP();
                            result = callHTTPCamwater.callRestCheckCamwater(requestCamwater, endPointPay, key);
                            System.out.println("result payBillCamwater = " + result);

                            ObjectMapper objectMapper = new ObjectMapper();
                            ResponseCamwater responseCamwater = objectMapper.readValue(result, ResponseCamwater.class);
                            if (responseCamwater.getStatus() == 200) {
                                transaction = transactionBillService.findById(response.getT().getId());
                                System.out.println("transaction ID " + transaction.getId());
                                transaction.setTransactionStatus("P");
                                transaction.setTransactionReturnCode(String.valueOf(responseCamwater.getStatus()));
                                transaction.setTransactionReturnMessage(responseCamwater.getMessage());
                                transaction.setThirdPartyReference(responseCamwater.getIdTransaction());
                                transaction.setTransDateModif(new Date());
                                System.out.println("transaction ID " + transaction.getThirdPartyReference());
                                transactionBillService.save(transaction);
                                TypeEvent typeEve = new TypeEvent();
                                typeEve.setId(Long.valueOf(10));
                                EventsBill eveBill = new EventsBill();
                                eveBill.setEveTransId(transaction);
                                eveBill.setEveType(typeEve);
                                eveBill.setEveUserId(transaction.getTransUserid());
                                eveBill.setEveDate(transaction.getTransDateModif());
                                eveBill.setEveComment("");
                                eventBillService.create(eveBill);
                                genericsResponse.setResponseCode(200);
                                genericsResponse.setResponseDescription(responseCamwater.getMessage());
                                genericsResponse.setT(transaction);
                            } else {
                                genericsResponse.setResponseCode(400);
                                genericsResponse.setResponseDescription(responseCamwater.getMessage());
                                genericsResponse.setT(transaction);
                            }

                        } else {
                            ParametreBase parametreBase = parametreBaseService.find("URL_MIDDLEWARE_PAY");
                            String endPointpay = parametreBase.getValeur()
                                    + "?amount=" + facture.getAmountToPaid() + "&billNumber=" + facture.getFactureNumber() + "" + "&merchantCode=" + parametrews.getPwsPartnerCode() + "&partner=" + parametrews.getPwsMyagentIdPartner()
                                    + "&paymentCheckoutCode=E_BELL&phonenumber=" + depositorPhone + "&userId=azerty";

                            PayMilddawareResponse payMilddawareResponse = new PayMilddawareResponse();
                            BillCallHTTP callHTTPMilddelWare = new BillCallHTTP();
                            result = callHTTPMilddelWare.payEneoByMiddleware(endPointpay);
                            System.out.println("result = " + result);
                            ObjectMapper objectMapper = new ObjectMapper();
                            try {
                                payMilddawareResponse = objectMapper.readValue(result, PayMilddawareResponse.class);
                                if (payMilddawareResponse.getStatus() == 200) {
                                    transaction = transactionBillService.findById(response.getT().getId());
                                    System.out.println("transaction ID " + transaction.getId());
                                    transaction.setTransactionStatus("P");
                                    transaction.setTransactionReturnCode(String.valueOf(payMilddawareResponse.getStatus()));
                                    transaction.setTransactionReturnMessage(payMilddawareResponse.getMessage());
                                    transaction.setThirdPartyReference(payMilddawareResponse.getTransaction().getTransactionId());
                                    System.out.println("transaction ID " + transaction.getThirdPartyReference());
                                    transaction.setTransDateModif(new Date());
                                    transactionBillService.save(transaction);
                                    TypeEvent typeEve = new TypeEvent();
                                    typeEve.setId(Long.valueOf(10));
                                    EventsBill eveBill = new EventsBill();
                                    eveBill.setEveTransId(transaction);
                                    eveBill.setEveType(typeEve);
                                    eveBill.setEveUserId(transaction.getTransUserid());
                                    eveBill.setEveDate(transaction.getTransDateModif());
                                    eveBill.setEveComment("");
                                    eventBillService.create(eveBill);
                                    genericsResponse.setResponseCode(200);
                                    genericsResponse.setResponseDescription(payMilddawareResponse.getMessage());
                                    genericsResponse.setT(transaction);
                                }
                                System.out.println("finish Request!");
                            } catch (JsonProcessingException e) {
                                genericsResponse.setResponseCode(400);
                                genericsResponse.setResponseDescription(payMilddawareResponse.getMessage());
                                genericsResponse.setT(transaction);

                            }
                        }
                    }
                } else {
                    genericsResponse.setResponseCode(response.getResponseCode());
                    genericsResponse.setResponseDescription(response.getResponseDescription());
                    genericsResponse.setT(null);
                }
            }
        } catch (Exception e) {
            System.out.println("Service Pay CAMWATER Failed ::: HTTP error code = " + e.getMessage());
        }
        return genericsResponse;
    }
}
