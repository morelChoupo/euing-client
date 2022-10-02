package com.ufi.euing.client.integration.billpay;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.text.Normalizer;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;

public class UtilsBillPay {

    public static final String CODE_MARCHAND_ENEO = "CODE_MARCHAND_ENEO";
    public static final String CODE_MARCHAND_CAMWATER = "CODE_MARCHAND_CAMWATER";
    public static final String CODE_MARCHAND_UNIV_YDE1 = "CODE_MARCHAND_UNIV_YDE1";
    public static final String CODE_MARCHAND_UNIV_DSC = "CODE_MARCHAND_UNIV_DSC";
    public static final String CODE_MARCHAND_UNIV_YDE2 = "CODE_MARCHAND_UNIV_YDE2";
    public static final String CODE_MARCHAND_EUMM = "EUMM";
    
    public static final String BILLPAY_ENEO_MODE = "BILLPAY_ENEO_MODE";
    public static final String BILLPAY_CAMWATER_MODE = "BILLPAY_CAMWATER_MODE";
    
    public static final String TYPE_CUSTOMER_ID = "CustomerId";
    public static final String TYPE_BILL_NUMBER = "BillNumber";
    public static final String TYPE_BILL_QUITUS = "quitus";
    public static final String TYPE_BILL_MATRICULE = "matricule";
    public static final String FACTURE_LIBELLE_ENEO = "FACTURE DE CONSOMATION ENEO";
    public static final String FACTURE_LIBELLE_CAMWATER = "FACTURE DE CONSOMATION CAMWATER";
    public static final String TYPE_ACCOUNT_INFO = "TYPE_ACCOUNT_INFO";
    public static final String TYPE_ACCOUNT_PAY = "TYPE_ACCOUNT_PAY";
    public static final String BILL_STATUS_PAID = "BILL_STATUS_PAID";
    public static final String BILL_STATUS_UNPAID = "BILL_STATUS_UNPAID";
    public static final String BILL_STATUS_NOT_FOUND = "BILL_STATUS_NOT_FOUND";
    public static final String ACCOUNT_NOT_FOUND = "ACCOUNT_NOT_FOUND";
    public static final String BILL_ALREADY_PAID = "BILL_ALREADY_PAID";
    public static final String BILL_ALREADY_CANCELED = "BILL_ALREADY_CANCELED";
    
    public static final String MSG_ERROR_NETWORK = "Error network | No return from provider";
    public static final String MSG_ID_TRANSACTION_USED = "Reference is already use or amount is no correct";
    public static final String MSG_AMOUNT_QUISTUS_NO_CORRECT = "Amount of this Quitus is no correct";
    public static final String MSG_EROOR_MARCHAND_CODE_NOT_FOUND = "Marchand non parametre dans parametre de base";
    public static final String MSG_EROOR_BILLPAY_ENEO_MODE_NOT_FOUND = "MSG_EROOR_BILLPAY_ENEO_MODE_NOT_FOUND";
    public static final String MSG_EROOR_BILLPAY_CAMWATER_MODE_NOT_FOUND = "MSG_EROOR_BILLPAY_CAMWATER_MODE_NOT_FOUND";
    public static final String MSG_EROOR_BILL_NOT_FOUND_OR_ALREADY_PAID = "MSG_EROOR_BILL_NOT_FOUND_OR_ALREADY_PAID";

    public static final String ENEO_URI_LOGIN = "oauth/token";
    public static final String ENEO_URI_SEARCH = "bills/search";
    public static final String ENEO_URI_PAY = "bills/pay";
    public static final String ENEO_URI_CHECK = "bills/check_transaction";
    public static final String ENEO_PAYMENT_METHOD = "TpCheckout";
    public static final String ENEO_PAYMENT_STATUS = "success";
    public static final String ENEO_BILLER_CODE = "ENEO";

    public static final String CAMWATER_URI_CONSULT = "accountInfo";
    public static final String CAMWATER_URI_PAY = "pay";
    public static final String CAMWATER_BILLER_CODE = "CAMWATER";

    public static final String UNIV_YDE2_URI_SEARCH = "get";
    public static final String UNIV_YDE2_URI_PAY = "notify";

    public static final String UNIV_YDE1_URI_QUITUS = "getQuitus";
    public static final String UNIV_YDE1_URI_MATRICULE = "getMatricule";
    public static final String UNIV_YDE1_URI_PAY = "payQuitus";
    public static final String UNIV_YDE1_BILLER_CODE = "UY1";

    public static final String URI_ESTEL_GET_BILL_STATUS = "service=getBillStatus";
    public static final String URI_ESTEL_PAY_BILL = "service=payBill";

    public static final String UNIV_DSC_URI_SEARCH = "getinfos";
    public static final String UNIV_DSC_URI_PAY = "setinfos";
    public static final String UNIV_DSC_BILLER_CODE = "DSC";

    public static final String PARAM_BASE_PAYER_UY1_PAR_ESTEL = "PAYER_UY1_PAR_ESTEL";
    public static final String PARAM_BASE_PAYER_DSC_PAR_ESTEL = "PAYER_DSC_PAR_ESTEL";
    public static final String PARAM_BASE_PAYER_ENEO_PAR_ESTEL = "PAYER_ENEO_PAR_ESTEL";
    public static final String PARAM_BASE_PAYER_CAMWATER_PAR_ESTEL = "PAYER_CAMWATER_PAR_ESTEL";

    public static String getHash(RequestCamwater requestCamwater, String type, String key) throws NoSuchAlgorithmException {
        System.out.println("timestamp = " + requestCamwater.getTimestamp());
        String toHash = "";
        if (type.equals(TYPE_ACCOUNT_INFO)) {
            toHash = requestCamwater.getCode() + requestCamwater.getAccountNumber() + requestCamwater.getTimestamp() + key;
        }
        if (type.equals(TYPE_ACCOUNT_PAY)) {
            toHash = requestCamwater.getCode() + requestCamwater.getAccountNumber() + requestCamwater.getAmount().longValue() + requestCamwater.getPhone()
                    + requestCamwater.getIdTransaction() + requestCamwater.getTimestamp() + key;
        }
        System.out.println("toHash = " + toHash);
        String hashed = hash(toHash, "SHA1");
        System.out.println("hashed = " + hashed);
        return hashed;
    }

    public static String hash(String inputData, String hashCode) throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance(hashCode);
        byte[] result = mDigest.digest(inputData.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

    public static String getSignature(String value, String secret, String hmacSha1Algo) {
        return signature(value, secret, hmacSha1Algo);
    }

    //Calcul de la signature de value à partir du key
    public static String signature(String value, String key, String hmacSha1Algo) {
        String hmac = "";
        System.out.println("value = " + value);
        System.out.println("key = " + key);
        try {
            hmac = calculateRFC2104HMAC(value, key, hmacSha1Algo);
        } catch (InvalidKeyException | SignatureException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hmac;
    }

    //Calcul du Hmac à partir du code Hexadecimal
    public static String calculateRFC2104HMAC(String data, String key, String hmacSha1Algo)
            throws SignatureException, NoSuchAlgorithmException, InvalidKeyException {
        System.out.println("hmacSha1Algo = " + hmacSha1Algo);
        SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), hmacSha1Algo);
        Mac mac = Mac.getInstance(hmacSha1Algo);
        mac.init(signingKey);
        return toHexString(mac.doFinal(data.getBytes()));
    }

    //Converstion en Hexadecimal
    public static String toHexString(byte[] bytes) {
        Formatter formatter = new Formatter();
        for (byte b : bytes) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    public static String transformDate(String dateToTransform, String defaultDate) {
        if (dateToTransform == null || dateToTransform.isEmpty() || dateToTransform.length() < 8) {
            return defaultDate;
        }
        String newDateLimit = dateToTransform.substring(0, 4);
        try {
            return String.format("%s%s", Integer.parseInt(newDateLimit) + 1000, dateToTransform.substring(4));
        } catch (NumberFormatException e) {
            System.out.println("Impossible de convertir cette date \nUtilisation de la date par défaut" + dateToTransform);
            return defaultDate;
        }
    }

    public static String deleteAccent(String s) {
        String data = Normalizer.normalize(s, Normalizer.Form.NFD);
        return data.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
    }

    public static String getEtablissementsByCodeAlpha(String codeAlpha) {
        Map<String, String> map = new HashMap<>();
        map.put("S", "SCI=Faculté des Sciences");
        map.put("A", "ASA=Faculté d'Agronomie et des Sciences Agricoles");
        map.put("D", "SJP=Faculté des Sciences Juridiques et Politiques");
        map.put("E", "SEG=Faculté des Sciences Economiques et de Gestion");
        map.put("L", "LSH=Faculté des Lettres et Sciences Humaines");
        map.put("B", "IUT=Institut Universitaire de Technologie Fotso Victor");
        map.put("I", "IBA=Institut des Beaux-Arts de Foumban");
        map.put("F", "ASAFMB=Faculté d'Agronomie et des Sciences Agricoles/FMBEE");
        map.put("C", "ASACFB=Faculté d'Agronomie et des Sciences Agricoles/CRESA-BOIS");
        map.put("Y", "ASABAF=Faculté d'Agronomie et des Sciences Agricoles/Annexe de BAFIA");
        map.put("M", "MSP=Faculté de Médecine et des Sciences Pharmaceutiques");
        return map.get(codeAlpha);
    }

    public static String getTypeDeFraisByCode(String code) {
        Map<String, String> map = new HashMap<>();
        map.put("1", "Droits Universitaires");
        map.put("2", "Frais d’étude de dossier");
        map.put("3", "Frais Médicaux");
        map.put("4", "Frais de concours");
        map.put("5", "Frais Médicaux");
        map.put("6", "");
        map.put("7", "Frais Médicaux");
        map.put("8", "Frais Médicaux");
        map.put("9", "");
        return map.get(code);
    }

}
