package com.ufi.euing.client.utils.others;

import com.ufi.euing.client.entity.ParametreBase;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.*;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SendSMSRunnable implements Runnable {

    String url;
    Long id;
    Long timestamp;
    String destinations;
    String token;
    String secret;
    String content;

    private final String HMAC_SHA1_ALGORITHM = "HmacSHA1";

    public static void disableSslVerification() {
        try {
            // Create a trust manager that does not validate certificate chains
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
                }

                public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
                }
            }
            };

            // Install the all-trusting trust manager
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            // Create all-trusting host name verifier
            HostnameVerifier allHostsValid = new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            };

            // Install the all-trusting host verifier
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            disableSslVerification();
            this.SendSmsAsync();
        } catch (IOException ex) {
            Logger.getLogger(SendSMSRunnable.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SignatureException ex) {
            Logger.getLogger(SendSMSRunnable.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(SendSMSRunnable.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(SendSMSRunnable.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(SendSMSRunnable.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(SendSMSRunnable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setParameters(String url, Long id, Long timestamp, String secret, String token, String destinations, String content) {
        this.url = url;
        this.id = id;
        this.timestamp = timestamp;
        this.destinations = destinations;
        this.secret = secret;
        this.token = token;
        this.content = content;
    }

    private String toHexString(byte[] bytes) {
        Formatter formatter = new Formatter();

        for (byte b : bytes) {
            formatter.format("%02x", b);
        }

        return formatter.toString();
    }

    public String calculateRFC2104HMAC(String data, String key)
            throws SignatureException, NoSuchAlgorithmException, InvalidKeyException {
        SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), HMAC_SHA1_ALGORITHM);
        Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
        mac.init(signingKey);
        return toHexString(mac.doFinal(data.getBytes()));
    }

    public void sendGenericSMS(List<ParametreBase> listParametreSMS, String message, String recipient) {
        SendSMSAVS(listParametreSMS, message, recipient);
    }

    private void SendSMSAVS(List<ParametreBase> listParametreSMS, String message, String recipient) {
//        try {
        this.timestamp = System.currentTimeMillis();
        this.destinations = recipient;
        this.content = message;

        for (ParametreBase item : listParametreSMS) {
            if (item.getCle().equals("SMS_PROVIDER_ID")) {
                this.id = new Long(item.getValeur());
            }
//            if(item.getCle().equals("SMS_PROVIDER_KEY"))
//                this.secret = item.getValeur();
            if (item.getCle().equals("SMS_PROVIDER_SECRET")) {
                this.secret = item.getValeur();
            }
            if (item.getCle().equals("SMS_PROVIDER_TOKEN")) {
                this.token = item.getValeur();
            }
            if (item.getCle().equals("SMS_PROVIDER_URL")) {
                this.url = item.getValeur();
            }
        }

        run();

    }

    private void SendSmsAsync() throws IOException, SignatureException, NoSuchAlgorithmException, InvalidKeyException, InterruptedException, Exception {

        CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
        // Start the client
        disableSslVerification();
        httpclient.start();
        String signature = calculateRFC2104HMAC(token + "" + timestamp, secret);
        // Execute request
        HttpPost httpPost = new HttpPost(url);

        StringBuilder json = new StringBuilder();
        json.append("{");
        json.append("\"id\":").append(id).append(",");
        json.append("\"timestamp\":").append(timestamp).append(",");
        json.append("\"signature\":\"").append(signature).append("\",");
        json.append("\"phonenumber\":\"").append(destinations).append("\",");
        json.append("\"schedule\":\"\",");
        json.append("\"sms\":\"").append(content).append("\"");
        json.append("}");
        System.out.println("Content : " + json.toString());
        StringEntity entity = new StringEntity(json.toString());
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");

        // One most likely would want to use a callback for operation result
        final CountDownLatch latch1 = new CountDownLatch(1);
        disableSslVerification();
        httpclient.execute(httpPost, new FutureCallback<HttpResponse>() {
            @Override
            public void completed(final HttpResponse response2) {
                latch1.countDown();
                System.out.println("Step 2 ======> " + response2.getEntity());
            }

            @Override
            public void failed(final Exception ex) {
                latch1.countDown();
                System.out.println("Step 2 ======> Http request failed");
                //System.out.println("Step 2 ======> " + httpPost.getRequestLine() + "->" + ex);
            }

            @Override
            public void cancelled() {
                latch1.countDown();
                System.out.println("Step 2 ======> Http request canceled");
                // System.out.println("Step 2 ======> " + httpPost.getRequestLine() + " cancelled");
            }

        });
        latch1.await();

    }

    private void SendSms() throws IOException {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            String signature = calculateRFC2104HMAC(token + "" + timestamp, secret);
            HttpPost httpPost = new HttpPost(url);
            StringBuilder json = new StringBuilder();
            json.append("{");
            json.append("\"id\":").append(id).append(",");
            json.append("\"timestamp\":").append(timestamp).append(",");
            json.append("\"signature\":\"").append(signature).append("\",");
            json.append("\"phonenumber\":\"").append(destinations).append("\",");
            json.append("\"schedule\":\"\",");
            json.append("\"sms\":\"").append(content).append("\"");
            json.append("}");
            System.out.println("Content : " + json.toString());
            StringEntity entity = new StringEntity(json.toString());
            httpPost.setEntity(entity);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");
            desactivateCert();
            CloseableHttpResponse response = client.execute(httpPost);
            System.out.println("Fin d'envoi du sms à " + new Date().toString());
            String result = EntityUtils.toString(response.getEntity());
            System.out.println("\n\n**** Message sent successfully to " + destinations + " result : " + result + "****");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void setParameters(List<ParametreBase> listParametreSMS, String message, String recipient) {
        this.timestamp = System.currentTimeMillis();
        this.destinations = recipient;
        this.content = message;

        for (ParametreBase item : listParametreSMS) {
            if (item.getCle().equals("SMS_PROVIDER_ID")) {
                this.id = new Long(item.getValeur());
            }
//            if(item.getCle().equals("SMS_PROVIDER_KEY"))
//                this.secret = item.getValeur();
            if (item.getCle().equals("SMS_PROVIDER_SECRET")) {
                this.secret = item.getValeur();
            }
            if (item.getCle().equals("SMS_PROVIDER_TOKEN")) {
                this.token = item.getValeur();
            }
            if (item.getCle().equals("SMS_PROVIDER_URL")) {
                this.url = item.getValeur();
            }
        }
    }

    public static void desactivateCert() throws Exception {
        System.out.println(" ************ Begin Accept All certicate **************");
        //Security.setProperty("ssl.SocketFactory.provider",LazySSLSocketFactory.class.getName());
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            @Override
            public void checkClientTrusted(java.security.cert.X509Certificate[] xcs, String string) throws CertificateException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void checkServerTrusted(java.security.cert.X509Certificate[] xcs, String string) throws CertificateException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        System.out.println(" ************ End Accept All certicate **************");
    }

}
