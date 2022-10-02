/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufi.euing.client.integration.minesec.model;

import javax.net.ssl.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Map;

/**
 *
 * @author UFI
 */
public class MinesecUtils {

    /*public static final String MINESEC_URI = "http://213.251.145.169/eu_purchase_api/";
    public static final String MINESEC_URI_VERSION = "v2/";*/
    public static final String MINESEC_URI_SERVICE_LIST_CATEGORIES = "listCategories";
    public static final String MINESEC_URI_SERVICE_FIND_SCHOOL = "findSchool";
    public static final String MINESEC_URI_SERVICE_FIND_SCHOOL_BY_CATEGORY = "findSchoolByCategory"; // same with findSchool
    public static final String MINESEC_URI_SERVICE_SCHOOL_DETAILS = "schoolDetails";
    public static final String MINESEC_URI_SERVICE_SCHOOL_DETAILS_ALL = "schoolDetailsAll";
    public static final String MINESEC_URI_SERVICE_STUDENT_DETAILS = "studentDetails";
    public static final String MINESEC_URI_SERVICE_FIND_CLASSES = "findClasses";
    public static final String MINESEC_URI_SERVICE_FIND_CLASS_OPTIONS = "findClassOptions";
    public static final String MINESEC_URI_SERVICE_FIND_FEES_BY_CATEGORY = "findFeesByCategory";
    public static final String MINESEC_URI_SERVICE_FIND_FEES = "findFees";
    public static final String MINESEC_URI_SERVICE_FIND_STUDENT = "findStudent";

    public static final String MINESEC_URI_PARAM_SERVICE = "?service=";
    public static final String MINESEC_URI_PARAM_CATEGORY = "&category=";
    public static final String MINESEC_URI_PARAM_SEARCH = "&search=";
    public static final String MINESEC_URI_PARAM_COUNTRY_CODE = "&country_code=";
    public static final String MINESEC_URI_PARAM_SCHOOL_CODE = "&school_code=";
    public static final String MINESEC_URI_PARAM_STUDENT_CODE = "&student_code=";
    public static final String MINESEC_URI_PARAM_CLASS_ID = "&class_id=";

    public static final String MINESEC_PARAMTREWS_CODE = "MINESEC";
    public static final String PARAM_BASE_PAYER_MINESEC_PAR_ESTEL = "PAYER_MINESEC_PAR_ESTEL";
    public static final String CODE_MARCHAND_EUMM = "EUMM";
    public static final String URI_ESTEL_CASHTOM = "service=cashtom";
    public static final String MINESEC_SERVICE_CODE = "FEEPAYMENT";
    public static final String MINESEC_SERVICE_PREFIX = "SCOLARITE_";
    public static final String MINESEC_ANNEE_ACADEMIQUE = "ANNEE_ACADEMIQUE";

    public static String hashData(String inputData, String hashCode) throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance(hashCode);
        byte[] result = mDigest.digest(inputData.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

    public static String postFromEstel(String url, Map<String, String> params) {
        HttpURLConnection urlConnection;
        String result = null;
        try {
            urlConnection = (HttpURLConnection) ((new URL(url).openConnection()));
            urlConnection.setDoOutput(true);
            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            //urlConnection.setRequestProperty("Authorization", "Basic " + base64);
            urlConnection.setRequestProperty("Accept", "application/json");
            try {
                urlConnection.setRequestMethod("POST");
                urlConnection.setConnectTimeout(10000);
                urlConnection.connect();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            StringBuilder postData = new StringBuilder();
            for (Map.Entry<String, String> param : params.entrySet()) {
                if (postData.length() != 0) {
                    postData.append('&');
                }
                postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                postData.append('=');
                postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
            }
            byte[] postDataBytes = postData.toString().getBytes("UTF-8");
            OutputStream outputStream = null;
            try {
                outputStream = urlConnection.getOutputStream();
                outputStream.write(postDataBytes);
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                //writer.write(data);
                writer.close();
                outputStream.close();
                int responseCode = urlConnection.getResponseCode();
                if (responseCode == HttpsURLConnection.HTTP_OK) {
                    //Read
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));

                    String line = null;
                    StringBuilder sb = new StringBuilder();

                    while ((line = bufferedReader.readLine()) != null) {
                        sb.append(line);
                    }

                    bufferedReader.close();
                    result = sb.toString();
                } else {
                    result = "{\"status\":" + responseCode + "," + "\"message\":" + urlConnection.getResponseMessage() + "}";
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void desactivateCert() throws Exception {
        //Security.setProperty("ssl.SocketFactory.provider",LazySSLSocketFactory.class.getName());
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            @Override
            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }
        }
        };
        // Install the all-trusting trust manager
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

        // Create all-trusting host name verifier
        HostnameVerifier allHostsValid = new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };

        // Install the all-trusting host verifier
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
    }

    public static String callHttp(String url) {
        String result = null;
        try {
            System.out.println("url ::: " + url);
            URL urlCon = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlCon.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            //conn.setRequestProperty("Accept", "application/json");
            conn.setConnectTimeout(10000);
            desactivateCert();
            conn.connect();

            System.out.println("res callHttp UtilsMenesec = " + conn.getResponseCode());
            if (conn.getResponseCode() != 200) {
                return "{\"statut\":" + conn.getResponseCode() + ",\"message\":\"" + conn.getResponseMessage() + "\"}";
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            StringBuilder sb = new StringBuilder();
            String output;
            while ((output = br.readLine()) != null) {
                sb.append(output);
            }
            conn.disconnect();
            result = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
