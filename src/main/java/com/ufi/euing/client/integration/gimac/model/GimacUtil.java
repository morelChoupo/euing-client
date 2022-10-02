/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufi.euing.client.integration.gimac.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author UFI
 */
public class GimacUtil implements Serializable {

    public static final String GIMAC_AMA_INTENT_BILL_INQUIRY = "bill_inquiry";
    public static final String GIMAC_AMA_INTENT_BILL_PAYMENT = "bill_payment";
    public static final String GIMAC_AMA_INTENT_CARDLESS_WITHDRAWAL = "cardless_withdrawal";
    public static final String GIMAC_AMA_INTENT_CASH_DEPOSIT = "cash_deposit";
    public static final String GIMAC_AMA_INTENT_INC_WAL_REMIT = "inc_wal_remit";
    public static final String GIMAC_AMA_INTENT_MOBILE_TRANSFER = "mobile_transfer";
    public static final String GIMAC_AMA_INTENT_PP_RELOAD = "pp_reload";
    public static final String GIMAC_AMA_INTENT_MOBILE_RELOAD = "mobile_reload";
    public static final String GIMAC_AMA_INTENT_MERCHANT_PURCHASE = "merchant_purchase";
    public static final String GIMAC_AMA_INTENT_INC_ACC_REMIT = "inc_acc_remit";

    public static final String GIMAC_AMA_VOUCHER_STATE_EXPIRED = "EXPIRED";
    public static final String GIMAC_AMA_VOUCHER_STATE_PENDING = "PENDING";
    public static final String GIMAC_AMA_VOUCHER_STATE_CANCELLED = "CANCELLED";
    public static final String GIMAC_AMA_VOUCHER_STATE_REVERSED = "REVERSED";
    public static final String GIMAC_AMA_VOUCHER_STATE_ACCEPTED = "ACCEPTED";
    public static final String GIMAC_AMA_VOUCHER_STATE_REJECTED = "REJECTED";

    public static final String GIMAC_CODE_MARCHAND = "GIMAC";
    public static final String GIMAC_CODE_SERVICE = "ENVOI_C2C_OPI_GIMAC";
    public static final String GIMAC_ACCESS_TOKEN_REQUEST_SCOPE = "read";
    public static final String GIMAC_CURRENCY_PREFIX = "GIMAC_CODE_CURRENCY_";
    public static final String GIMAC_TYPE_OPERATION = "GIMAC_TYPE_OPERATION";
    public static final String GIMAC_MEMBERS_WALLET_PREFIX = "GIMAC_MEMBERS_WALLET_";
    public static final String GIMAC_MEMBERS_ACCOUNT_PREFIX = "GIMAC_MEMBERS_ACCOUNT_";

    public static final String GIMAC_WALLET_REMITTANCE_CODE = "wallet";
    public static final String GIMAC_ACCOUNT_REMITTANCE_CODE = "account";
    public static final String GIMAC_CASH_TO_WALLET_REMITTANCE_CODE = "cash";

    public static final String GIMAC_URI_OAUTH = "/oauth/token";
    public static final String GIMAC_URI_PAYMENT_SEND = "/payment/send";
    public static final String GIMAC_URI_PAYMENT_UPDATE = "/payment/update";
    public static final String GIMAC_URI_PAYMENT_INQUIRY = "/payment/inquiry";

    public static final String GIMAC_CURENCY_NUMERIC = "GIMAC_CURENCY_NUMERIC";
    public static final String GIMAC_CURENCY_NUMERIC_DEFAULT = "950";
    public static final String GIMAC_PREFIXE_TRANSACTION = "PREFIXE_TRANSACTION";

    public static final String getAmaBillingStatus(String s) {
        String result;
        try {
            Map<String, String> map = new HashMap<>();
            map.put("P", "Paid");
            map.put("U", "Unpaid");
            map.put("A", "All");
            result = map.get(s);
        } catch (Exception e) {
            e.getMessage();
            result = "Unknown";
        }
        System.out.println("result getAmaBillingStatus = " + result);
        return result;
    }

    public static final String getAmaVoucherState(String s) {
        String result;
        try {
            Map<String, String> map = new HashMap<>();
            map.put("EXPIRED", "Voucher Expired");
            map.put("PENDING", "Voucher In Pending");
            map.put("CANCELLED", "Voucher Cancelled");
            map.put("REVERSED", "Voucher Reversed");
            map.put("ACCEPTED", "Voucher Accepted");
            map.put("REJECTED", "Voucher Rejected");
            result = map.get(s);
        } catch (Exception e) {
            e.getMessage();
            result = "Unknown";
        }
        System.out.println("result getAmaVoucherState = " + result);
        return result;
    }
}
