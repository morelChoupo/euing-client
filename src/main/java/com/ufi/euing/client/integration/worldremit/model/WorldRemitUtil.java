/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufi.euing.client.integration.worldremit.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author UFI
 */
public class WorldRemitUtil implements Serializable {

    public static final String WR_URI_REQUEST_METHOD_GET = "GET";
    public static final String WR_URI_REQUEST_METHOD_PUT = "PUT";
    
    public static final String WR_URI_GET_TRANS_BY_TRANS_ID = "transactions/{param}";
    public static final String WR_URI_GET_TRANS_BY_TRANS_NUM = "transactions?wr_transaction_number={param}";
    public static final String WR_URI_GET_TRANS_BY_SERVICE_AND_STATUS = "transactions?product={param}&status=";
    public static final String WR_URI_GET_TRANS_BY_SERVICE = "transactions?product={param}";
    public static final String WR_URI_GET_BENEFICIARY_ID_TYPES = "catalogs/beneficiaryidtypes";
    public static final String WR_URI_GET_REJECTION_CODES = "catalogs/RejectCodes";
    
    public static final String WR_URI_LOCK_TRANS_BY_TRANS_ID = "transactions/{param}/lock";
    public static final String WR_URI_UNLOCK_TRANS_BY_TRANS_ID = "transactions/{param}/unlock";
    public static final String WR_URI_MARKPAID_TRANS_BY_TRANS_ID = "transactions/{param}/markpaid";
    public static final String WR_URI_REJECT_TRANS_BY_TRANS_ID = "transactions/{param}/reject";
    public static final String WR_URI_UPDATE_TRANS_BY_TRANS_ID = "transactions/{param}/update";
    
    public static final String WR_PAY_SERVICE_CODE = "PAIEMENT_WORLD_REMIT_C2C_INTL";

    public static final String getRemitProduct(String s) {
        String result;
        try {
            Map<String, String> map = new HashMap<>();
            map.put("BNK", "Dépôt bancaire");
            map.put("CSH", "Collecte d’espèces");
            map.put("MOB", "Portefeuille mobile");
            map.put("DTD", "Porte à porte");
            map.put("CRD", "Paiement sur carte");
            result = map.get(s);
        } catch (Exception e) {
            e.getMessage();
            result = "Unknown";
        }
        System.out.println("result getRemitProduct = " + result);
        return result;
    }
   
    public static final String getRemitStatus(String s) {
        String result;
        try {
            Map<String, String> map = new HashMap<>();
            map.put("A", "Autorisé");
            map.put("T", "Transmis/ Verrouillé");
            map.put("P", "Payé");
            map.put("F", "Echouée");
            result = map.get(s);
        } catch (Exception e) {
            e.getMessage();
            result = "Unknown";
        }
        System.out.println("result getRemitProduct = " + result);
        return result;
    }
}
