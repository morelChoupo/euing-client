/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufi.euing.client.integration.juba.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author UFI
 */
public class JubaUtil implements Serializable {

    public static final String JUBA_URI_X_VERSION_1 = "1";
    //public static final String JUBA_URI_X_VERSION_3 = "3";
    public static final String JUBA_URI_DO_GET_EXCHANGE_RATE = "ExchangeRate/doGetExchangeRates";
    public static final String JUBA_URI_DO_GET_AGENTS = "Agent/doGetAgents";
    public static final String JUBA_URI_DO_CREATE_TRANSACTION_QUOTATION = "TransactionQuotation/doCreateTransactionQuotation";
    public static final String JUBA_URI_DO_REGISTER_CUSTOMER = "CustomerRegister/doRegisterCustomer";
    public static final String JUBA_URI_DO_SEND_TRANSACTIONS = "SendRemittance/doSendTransactions";
    public static final String JUBA_URI_DO_GET_PAY_REMITTANCE_STATUS = "PayRemittanceStatus/doGetPayRemittanceStatus";
    public static final String JUBA_URI_DO_GET_DETAIL = "RemittanceDetail/doGetDetail";
    public static final String JUBA_URI_DO_PAYMENT_CONFIRMATION = "PaymentConfirmation/doPaymentConfirmation";
    public static final String JUBA_URI_DO_GET_BENEFICIARY_NAME = "Beneficiary/doGetBeneficiaryName";
    public static final String JUBA_SEND_SERVICE_CODE = "ENVOI_JUBA_C2C_INTL";
    public static final String JUBA_PAY_SERVICE_CODE = "PAIEMENT_JUBA_C2C_INTL";

    public static final String getRemittanceStatus(int i) {
        String result;
        try {
            Map<Integer, String> map = new HashMap<>();
            map.put(3, "OnHold");
            map.put(4, "Transit");
            map.put(5, "ReadyToPay");
            map.put(6, "Paid");
            map.put(8, "ReadyToReturn");
            map.put(9, "Returned");
            map.put(11, "RequestForCancel");
            map.put(13, "UndoForPayment");
            result = map.get(i);
        } catch (Exception e) {
            e.getMessage();
            result = "Unknown";
        }
        System.out.println("result getRemittanceStatus = " + result);
        return result;
    }

    public static final String getDocumentType(int i) {
        String result;
        try {
            Map<Integer, String> map = new HashMap<>();
            map.put(1, "NIC");
            map.put(2, "Visa");
            map.put(3, "Passport");
            map.put(4, "Driving License");
            map.put(5, "Residence permit");
            map.put(6, "Green card");
            map.put(7, "State ID");
            map.put(8, "Military card");
            map.put(9, "SSN");
            map.put(10, "Travel Document");
            map.put(11, "Professional Driving License");
            map.put(12, "Entry Clearance Visa");
            map.put(13, "Utility Bill");
            map.put(14, "HM Revenue");
            map.put(15, "Bank Statement");
            map.put(16, "Pay Slip");
            map.put(17, "Consular Card");
            result = map.get(i);
        } catch (Exception e) {
            e.getMessage();
            result = "Unknown";
        }
        System.out.println("result getDocumentType = " + result);
        return result;
    }

    public static final String getTransactionPurpose(int i) {
        String result;
        try {
            Map<Integer, String> map = new HashMap<>();
            map.put(1, "Medical");
            map.put(2, "Travel");
            map.put(3, "Educational");
            map.put(4, "Business");
            map.put(5, "Investment");
            map.put(6, "Charity");
            map.put(7, "Other");
            map.put(8, "FamilySupport");
            result = map.get(i);
        } catch (Exception e) {
            e.getMessage();
            result = "Unknown";
        }
        System.out.println("result getTransactionPurpose = " + result);
        return result;
    }
   
}
