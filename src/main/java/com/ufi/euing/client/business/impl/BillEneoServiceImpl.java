package com.ufi.euing.client.business.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufi.euing.client.business.*;
import com.ufi.euing.client.entity.*;
import com.ufi.euing.client.integration.billpay.*;
import com.ufi.euing.client.props.EuingProperties;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

@Component
public class BillEneoServiceImpl implements BillEneoService {

    SingleAuthEneo singleAuthEneo = SingleAuthEneo.getInstance();

    final TransactionBillService transactionBillService;

    final FactureService factureService;

    final EuingProperties euingProperties;

    final ParametreBaseService parametreBaseService;

    final ParametrewsService parametrewsService;

    final EventBillService eventBillService;

    public BillEneoServiceImpl(TransactionBillService transactionBillService,
                               FactureService factureService, EuingProperties euingProperties,
                               ParametreBaseService parametreBaseService,
                               ParametrewsService parametrewsService, EventBillService eventBillService) {
        this.transactionBillService = transactionBillService;
        this.factureService = factureService;
        this.euingProperties = euingProperties;
        this.parametreBaseService = parametreBaseService;
        this.parametrewsService = parametrewsService;
        this.eventBillService = eventBillService;
    }


    @Override
    public BillSearchResponse consultBillsEneo(String codePartenaire, String type, String value, String requestType) {
        GenericsResponse<Parametrews> p = parametrewsService.getParametrewsByPartnerCode(codePartenaire);
        Parametrews parametrews = p.getT();
        System.out.println("**********************************************");
        System.out.println(" ******** codePartenaire "+codePartenaire);
        System.out.println("************* type "+type);
        System.out.println("*************** value "+value);
        System.out.println("*************** requestType "+requestType);

        LoginResponse loginResponse = authentifiationEneo(parametrews);
        String currentToken = loginResponse.getAccess_token();
        SearchParamRequest paramRequest = new SearchParamRequest(type, value);
        BillSearchResponse paramResponse = null;
        boolean tokenFail;
        int iteration = 0;
        String endPointConsult = parametrews.getPwsWebServiceUrl() + UtilsBillPay.ENEO_URI_SEARCH;
        do {
            paramResponse = search(paramRequest, currentToken, endPointConsult, requestType, parametrews);
            if (paramResponse == null || paramResponse.getCode() == 200) {
                tokenFail = false;
            } else {
                singleAuthEneo.loginResponse = loginResponse; // Cas du token ayant expiré alors on récupére un nouveau via l'authentification sur sur ENEO
                tokenFail = true;
            }
            iteration++;
        } while (tokenFail && iteration <= 2);
        System.out.println("===Retour search eneo paramResponse === "+paramResponse.toString());
        return paramResponse;
    }

    @Override
    public GenericsResponse<Facture> calculFeesEneo(Compagnie compagnie, Guichet guichet, Long serviceID, String ServiceName, Facture facture, BigDecimal userID, String userLogin) {
        GenericsResponse<CalculTarifBill> response = transactionBillService.calculFee(compagnie, guichet, serviceID, ServiceName, facture.getMontant(), userID, userLogin);
        System.out.println("checkResponse = " + response);
        System.out.println("data = " + response.getT());
        if (response.getResponseCode() == 200 && response.getT() != null) {
            facture.setAmountToSent(BigDecimal.valueOf(response.getT().getAmountSent()));
            facture.setAmountToPaid(BigDecimal.valueOf(response.getT().getAmountToPaid()));
            facture.setExchangeRate(BigDecimal.valueOf(response.getT().getExchangeRate()));
            facture.setOtherfees(BigDecimal.valueOf(response.getT().getOthersFees()));
            facture.setFees(BigDecimal.valueOf(response.getT().getFees()));
            facture.setTotalToPaid(BigDecimal.valueOf(response.getT().getTotalToPaid()));
            return new GenericsResponse<>(facture);
        } else {
            return new GenericsResponse<>(response.getResponseCode(), response.getResponseDescription(), null);
        }
    }

    @Override
    public GenericsResponse<CheckBillResponse> precheckFeesEneo(Compagnie compagnie, Guichet guichet, Service service, BigDecimal montant, Utilisateur user) {
        return null;
    }

    @Override
    public GenericsResponse<TransactionBill> payBillEneo(Compagnie compagnie, Guichet guichet, Facture facture, Service service, Utilisateur user, String depositorName, String depositorPhone, String requestType) {
        GenericsResponse genericsResponse = new GenericsResponse<>();
        try {
            int status = 200;
            String payerParEstel;
            ParametreBase param = parametreBaseService.find(UtilsBillPay.PARAM_BASE_PAYER_ENEO_PAR_ESTEL);
            if (param != null) {
                payerParEstel = param.getValeur();
            } else {
                return new GenericsResponse(404, UtilsBillPay.PARAM_BASE_PAYER_ENEO_PAR_ESTEL + " NOT CONFIGURED", null);
            }

            EstelResponse estelResponse = new EstelResponse();
            if (payerParEstel.equalsIgnoreCase("1")) {
                GenericsResponse<Parametrews> p = parametrewsService.getParametrewsByPartnerCode(UtilsBillPay.CODE_MARCHAND_EUMM);
                Parametrews parametrews = p.getT();
                String toHash = parametrews.getLogin() + parametrews.getPassword() + UtilsBillPay.ENEO_BILLER_CODE + facture.getFactureNumber() + parametrews.getPwsMykeyPartner();
                String hashed = UtilsBillPay.hash(toHash, "MD5");
                String url = parametrews.getPwsWebServiceUrl() + UtilsBillPay.URI_ESTEL_PAY_BILL;

                Map<String, String> params = new HashMap<>();
                params.put("id", parametrews.getLogin());
                params.put("pwd", parametrews.getPassword());
                params.put("hash", hashed);
                params.put("billno", facture.getFactureNumber());
                params.put("biller", UtilsBillPay.ENEO_BILLER_CODE);

                System.out.println("url = " + url);
                System.out.println("params = " + params);
                BillCallHTTP callHTTP = new BillCallHTTP();
                String result = callHTTP.postToEstel(url, params);
                System.out.println("result = " + result);
                ObjectMapper objectMapper = new ObjectMapper();
                estelResponse = objectMapper.readValue(result, EstelResponse.class);
                if (!estelResponse.getStatut().equalsIgnoreCase("200")) {
                    status = 500;
                    genericsResponse.setResponseCode(status);
                }
                genericsResponse.setResponseDescription(estelResponse.getMessage());
            }

            if (status == 200) {
                GenericsResponse<Parametrews> p = parametrewsService.getParametrewsByPartnerCode(compagnie.getCodePartenaire());
                Parametrews parametrews = p.getT();

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
                System.out.println("response = " + response.getData().toString());
                //GenericsResponse genericsResponse = new GenericsResponse<>();
                if (response != null && response.getResponseCode() == 200) {
                    if (payerParEstel.equalsIgnoreCase("1")) {
                        // transaction = transactionFacadeLocal.findById(response.getT().getId());
                        transaction = response.getData();
                        System.out.println("transaction ID " + transaction.getId());
                        transaction.setTransactionStatus("P");
                        transaction.setTransactionReturnCode(estelResponse.getStatut());
                        transaction.setTransactionReturnMessage(estelResponse.getMessage());
                        transaction.setThirdPartyReference(estelResponse.getTransaction());
                        transaction.setTransDateModif(new Date());
                        System.out.println("thirdPartyReference eneo " + transaction.getThirdPartyReference());
                        transactionBillService.edit(transaction);
                        System.out.println("YMDF == "+transaction.getTransactionStatus());
                        TypeEvent typeEve = new TypeEvent();
                        typeEve.setId(Long.valueOf(10));
                        EventsBill eveBill = new EventsBill();
                        eveBill.setEveTransId(transaction);
                        eveBill.setEveType(typeEve);
                        eveBill.setEveUserId(transaction.getTransUserid());
                        eveBill.setEveDate(transaction.getTransDateModif());
                        eveBill.setEveComment("");
                        eventBillService.create(eveBill);
                        System.out.println("YMDF == "+eveBill.toString());
                        genericsResponse.setResponseCode(200);
                        genericsResponse.setResponseDescription(estelResponse.getMessage());
                        genericsResponse.setT(transaction);
                    } else {
                        String currentToken = authentifiationEneo(parametrews).getAccess_token();
                        String endPointPay = parametrews.getPwsWebServiceUrl() + UtilsBillPay.ENEO_URI_PAY;

                        PayBillRequest payBillRequest = new PayBillRequest(facture.getMontant(), guichet.getId(), UtilsBillPay.ENEO_PAYMENT_METHOD,
                                UtilsBillPay.ENEO_PAYMENT_STATUS, facture.getFactureNumber());
                        PayBillResponse payBillResponse = payBill(payBillRequest, currentToken, endPointPay, requestType, parametrews, depositorPhone);
                        System.out.println("payResponse = " + payBillResponse);

                        if (payBillResponse.getCode() == 200) {
                            //transaction = transactionFacadeLocal.findById(response.getT().getId());
                            transaction = response.getData();
                            System.out.println("transaction ID " + transaction.getId());
                            transaction.setTransactionStatus("U");
                            transaction.setTransactionReturnCode(String.valueOf(payBillResponse.getCode()));
                            transaction.setTransactionReturnMessage(payBillResponse.getMessage());
                            transaction.setThirdPartyReference(payBillResponse.getData().getTransactionId());
                            System.out.println("transaction ID " + transaction.getThirdPartyReference());
                            transactionBillService.edit(transaction);

                            genericsResponse.setResponseCode(200);
                            genericsResponse.setResponseDescription(payBillResponse.getMessage());
                            genericsResponse.setT(transaction);
                        } else {
                            genericsResponse.setResponseCode(400);
                            genericsResponse.setResponseDescription(payBillResponse.getMessage());
                            genericsResponse.setT(transaction);
                        }
                    }
                } else {
                    genericsResponse.setResponseCode(response.getResponseCode());
                    genericsResponse.setResponseDescription(response.getResponseDescription());
                    genericsResponse.setT(null);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Service Pay ENEO Failed ::: HTTP error code = " + e.getMessage());
        }
        return genericsResponse;
    }

    @Override
    public GenericsResponse<TransactionBill> payBillEneoRecall(TransactionBill transaction, String requestType) {
        GenericsResponse<Parametrews> p = parametrewsService.getParametrewsByPartnerCode(transaction.getFacture().getCodeCompagnie());
        Parametrews parametrews = p.getT();

        String currentToken = authentifiationEneo(parametrews).getAccess_token();
        String endPointPay = parametrews.getPwsWebServiceUrl() + UtilsBillPay.ENEO_URI_PAY;

        PayBillRequest payBillRequest = new PayBillRequest(transaction.getFacture().getMontant(), transaction.getTransGuichetId().getId(), UtilsBillPay.ENEO_PAYMENT_METHOD,
                UtilsBillPay.ENEO_PAYMENT_STATUS, transaction.getFacture().getFactureNumber());
        PayBillResponse payBillResponse = payBill(payBillRequest, currentToken, endPointPay, requestType, parametrews, transaction.getDepositorPhone());
        System.out.println("payResponse = " + payBillResponse);

        if (payBillResponse.getCode() == 200) {
            transaction = transactionBillService.findById(transaction.getId());
            System.out.println("transaction ID " + transaction.getId());
            transaction.setTransactionStatus("U");
            transaction.setTransactionReturnCode(String.valueOf(payBillResponse.getCode()));
            transaction.setTransactionReturnMessage(payBillResponse.getMessage());
            transaction.setThirdPartyReference(payBillResponse.getData().getTransactionId());
            System.out.println("transaction ID " + transaction.getThirdPartyReference());
            transactionBillService.save(transaction);
        }

        return null;
    }

    @Override
    public LoginResponse authentifiationEneo(Parametrews parametrews) {
        String url = parametrews.getPwsWebServiceUrl();
        String username = parametrews.getLogin();
        String password = parametrews.getPassword();
        String grantType = parametrews.getAutorized();
        String clientId = parametrews.getPwsMystatutIdPartner();
        String authUser = parametrews.getPwsMykeyPartner();
        String authPwd = parametrews.getTokenautorized();
        String endPointLogin = url + UtilsBillPay.ENEO_URI_LOGIN;

        Map<String, Object> params = new LinkedHashMap<>();
        params.put("username", username);
        params.put("password", password);
        params.put("grant_type", grantType);
        params.put("client_id", clientId);
        BillCallHTTP callHTTPEneo = new BillCallHTTP();

        String result = callHTTPEneo.authEneo(authUser, authPwd, endPointLogin, params);
        ObjectMapper objectMapper = new ObjectMapper();
        LoginResponse loginResponse = null;
        try {
            loginResponse = objectMapper.readValue(result, LoginResponse.class);
        } catch (Exception e) {
            throw new RuntimeException("Service Authentification ENEO ::: HTTP error = " + result);
        }
        return loginResponse;
    }

    private BillSearchResponse search(SearchParamRequest paramRequest, String token, String url, String requestType, Parametrews parametrews) {
        BillSearchResponse paramResponse = new BillSearchResponse();
        String urlFinal = url + "?" + "billSearchBy=" + paramRequest.getType() + "&" + "searchParam=" + paramRequest.getSearchParam();
        String result = null;
        try {
            if (requestType.equalsIgnoreCase("DIRECT")) {
                BillCallHTTP callHTTPEneo = new BillCallHTTP();
                result = callHTTPEneo.callRestConsulEneo(token, urlFinal);
                ObjectMapper objectMapper = new ObjectMapper();
                if (result.equalsIgnoreCase("400")) {
                    paramResponse.setMessage(UtilsBillPay.BILL_STATUS_PAID);
                    paramResponse.setCode(400);
                } else if (result.equalsIgnoreCase("404")) {
                    paramResponse.setMessage(UtilsBillPay.BILL_STATUS_NOT_FOUND);
                    paramResponse.setCode(404);
                } else {
                    Bill[] bills = objectMapper.readValue(result, Bill[].class);
                    paramResponse.setData(Arrays.asList(bills));
                    paramResponse.setMessage(UtilsBillPay.BILL_STATUS_UNPAID);
                    paramResponse.setCode(200);
                }
            } else {
                ResponseMilddelwareConsult responseMilddelwareConsult = new ResponseMilddelwareConsult();
                ParametreBase parametreBase = parametreBaseService.find("URL_MIDDLEWARE_CONSULT");
                String endPointConsult = parametreBase.getValeur()
                        + "?merchantCode=" + parametrews.getPwsPartnerCode() + "&partner=" + parametrews.getPwsMyagentIdPartner() + "" + "&type=" + paramRequest.getType() + "&value=" + paramRequest.getSearchParam();

                System.out.println("endPointConsult = " + endPointConsult);
                BillCallHTTP callHTTPMilddelWare = new BillCallHTTP();
                result = callHTTPMilddelWare.ConsulAndCkeckBillByMiddleware(endPointConsult);
                System.out.println("result consultBillsBy Middl= " + result);
                try {
                    ObjectMapper objectMapper = new ObjectMapper();
                    responseMilddelwareConsult = objectMapper.readValue(result, ResponseMilddelwareConsult.class);
                    if (responseMilddelwareConsult.getStatus() == 200) {
                        List<Bill> bills = new ArrayList<>();

                        for (BillsMilddelWare billsMilddelWare : responseMilddelwareConsult.getBills()) {
                            Bill bill = new Bill();
                            bill.setBillId(billsMilddelWare.getBillId());
                            bill.setBillNumber(billsMilddelWare.getBillNumber());
                            bill.setBillGenerationDate(billsMilddelWare.getBillGenerationDate());
                            bill.setBalance(Long.valueOf(billsMilddelWare.getBillAmount()));
                            bill.setBillAmount(Math.toIntExact(Long.valueOf(billsMilddelWare.getBillAmount())));
                            bill.setBillDueDate(String.valueOf(billsMilddelWare.getBillDueDate()));
                            bill.setCustomerName(billsMilddelWare.getCustomerName());
                            bill.setCustomerId(billsMilddelWare.getCustomerId());
                            if (bill.getBillAmount() != 0) {
                                bills.add(bill);
                            }
                        }
                        paramResponse = new BillSearchResponse(200, UtilsBillPay.BILL_STATUS_UNPAID, bills);
                    }

                } catch (Exception e) {
                    if (result.equalsIgnoreCase("400")) {
                        paramResponse.setMessage(UtilsBillPay.BILL_STATUS_PAID);
                        paramResponse.setCode(400);
                    } else {
                        paramResponse.setMessage(UtilsBillPay.BILL_STATUS_NOT_FOUND);
                        paramResponse.setCode(404);
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Service Search ENEO Failed ::: HTTP error code = " + e.getMessage());
            paramResponse.setCode(500);
            paramResponse.setMessage(result);
        }
        return paramResponse;
    }

    @Override
    public CheckBillResponse checkBillStatus(String transactionId, String token, String url) {
        System.out.println("Service CHECKBILL ENEO");
        CheckBillResponse checkBillEneoResponse = new CheckBillResponse();
        String urlFinal = url + "?" + "transactionId=" + transactionId;
        String result = null;
        System.out.println("Send CheckBill Request with transactionId={}" + transactionId);
        try {
            BillCallHTTP callHTTP = new BillCallHTTP();
            result = callHTTP.callRestConsulEneo(token, urlFinal);
            ObjectMapper objectMapper = new ObjectMapper();
            checkBillEneoResponse = objectMapper.readValue(result, CheckBillResponse.class);
            checkBillEneoResponse.setCode(200);
            System.out.println("finish Request!");
            return checkBillEneoResponse;
        } catch (Exception e) {
            System.out.println("HttpErrorException->message = {}" + e.getMessage());
            System.out.println("Body in error -> {}" + e.getMessage());
            checkBillEneoResponse.setCode(400);
            checkBillEneoResponse.setMessage(result);
            return checkBillEneoResponse;
        }
    }

    private PayBillResponse payBill(PayBillRequest billRequest, String token, String url, String requestType, Parametrews parametrews, String depositorPhone) {
        System.out.println("Service PAIMENT FACTURE ENEO");
        System.out.println("send Request = " + billRequest);
        PayBillResponse payBillResponse = new PayBillResponse();

        if (requestType.equalsIgnoreCase("DIRECT")) {
            try {
                BillCallHTTP callHTTP = new BillCallHTTP();
                String result = callHTTP.callRestPayeEneo(billRequest, token, url);
                System.out.println("result = " + result);
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    payBillResponse = objectMapper.readValue(result, PayBillResponse.class);
                    System.out.println("finish Request!");
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                    payBillResponse.setCode(400);
                    payBillResponse.setMessage(result);
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("HttpErrorException->message = {} " + e.getMessage());
                payBillResponse.setCode(400);
                payBillResponse.setMessage("null");
            }
        } else {
            ParametreBase parametreBase = parametreBaseService.find("URL_MIDDLEWARE_PAY");
            String endPointpay = parametreBase.getValeur()
                    + "?amount=" + billRequest.getAmount() + "&billNumber=" + billRequest.getBillNumber() + "" + "&merchantCode=" + parametrews.getPwsPartnerCode() + "&partner=" + parametrews.getPwsMyagentIdPartner()
                    + "&paymentCheckoutCode=E_BELL&phonenumber=" + depositorPhone + "&userId=azerty";

            PayMilddawareResponse payMilddawareResponse = new PayMilddawareResponse();
            BillCallHTTP callHTTPMilddelWare = new BillCallHTTP();
            String result = callHTTPMilddelWare.payEneoByMiddleware(endPointpay);
            System.out.println("result = " + result);
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                payMilddawareResponse = objectMapper.readValue(result, PayMilddawareResponse.class);
                payBillResponse.setCode(200);
                payBillResponse.setMessage("");
                PaymentEneo payment = new PaymentEneo();
                payment.setTransactionId(payMilddawareResponse.getTransaction().transactionId);
                payment.setStatus("200");
                payment.setMessage(payMilddawareResponse.getMessage());
                payBillResponse.setData(payment);
                System.out.println("finish Request!");
            } catch (Exception e) {
                e.printStackTrace();
                payMilddawareResponse.setStatus(500);
                payBillResponse.setMessage("error 500");
            }
        }

        return payBillResponse;
    }
}
