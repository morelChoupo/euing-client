package com.ufi.euing.client.integration.gimac.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.net.ssl.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.cert.X509Certificate;

@Component
public class GimacCallApi implements Serializable {

    public static void desactivateCert() throws Exception {

        //Security.setProperty("ssl.SocketFactory.provider",LazySSLSocketFactory.class.getName());
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
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

    public static <T extends Object> String callApiGenericGimac(String token, String url, T t) {
        HttpURLConnection conn;
        String result = null;
        try {
            System.out.println("url ::: " + url);
            //Connect
            conn = (HttpURLConnection) ((new URL(url).openConnection()));
            conn.setDoOutput(true);
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Content-type", "application/json");
            conn.setRequestProperty("Authorization", "Bearer " + token);
            conn.setRequestMethod("POST");
            //conn.setConnectTimeout(10000);
            desactivateCert();
            conn.connect();

            ObjectMapper mapper = new ObjectMapper();
            String data = mapper.writeValueAsString(t);
            System.out.println("data ::: " + data);
            //Write
            OutputStream outputStream = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            writer.write(data);
            writer.close();
            outputStream.close();

            if (conn.getResponseCode() == HttpsURLConnection.HTTP_CREATED
                    || conn.getResponseCode() == HttpsURLConnection.HTTP_OK) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                String line = null;
                StringBuilder sb = new StringBuilder();
                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }
                bufferedReader.close();
                result = sb.toString();
            } else {
                return "{\"error\":" + conn.getResponseCode() + "," + "\"error_description\":\"" + conn.getResponseMessage() + "}";
            }
        } catch (Exception ex) {
            result = "{\"error\":500," + "\"error_description\":\"" + ex.getMessage() + "}";
        }
        return result;
    }

    public static String callApiGimacOauth(String url, String params) {
        HttpURLConnection conn;
        String result;
        try {
            System.out.println("params ::: " + params);
            System.out.println("url oauth ::: " + url);
            //Connect
            conn = (HttpURLConnection) ((new URL(url).openConnection()));
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
            conn.setRequestMethod("POST");
            desactivateCert();
            conn.connect();

            //ObjectMapper mapper = new ObjectMapper();
            //String data = mapper.writeValueAsString(urlParameters);
            byte[] data = params.getBytes(StandardCharsets.UTF_8);
            System.out.println("data ::: " + data);
            //Write
            //DataOutputStream outputStream = null;

            try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
                wr.write(data);
            }

            System.out.println("responseCode ::: " + conn.getResponseCode());

            if (conn.getResponseCode() == HttpsURLConnection.HTTP_CREATED || conn.getResponseCode() == HttpsURLConnection.HTTP_OK) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                String line = null;
                StringBuilder sb = new StringBuilder();
                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }
                bufferedReader.close();
                return sb.toString();
            } else {
                return "{\"error\":" + conn.getResponseCode() + "," + "\"error_description\":\"" + conn.getResponseMessage() + "}";
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            result = "{\"error\":500," + "\"error_description\":\"" + ex.getMessage() + "}";
        }
        return result;
    }

}
