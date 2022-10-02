package com.ufi.euing.client.integration.thunes.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufi.euing.client.integration.thunes.dto.Quotation;
import com.ufi.euing.client.integration.thunes.dto.Transaction;
import com.ufi.euing.client.integration.thunes.response.QuotationResponse;
import com.ufi.euing.client.integration.thunes.response.TransactionResponse;

import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;


@Component
public class ThunesCallApi implements Serializable {

    public String callFlowsThunes(String apiKey, String apiSecret, String urlQuotatton, String urlTransaction, String urlConfirm, Transaction transaction, Quotation quotation) {

        ObjectMapper mapper = new ObjectMapper();
        String resultQuotations = callApiGenericThunesPOSTQuotationAndTransaction(apiKey, apiSecret, urlQuotatton, quotation);
        System.out.println("resultQuotations =====> " + resultQuotations);
        if (resultQuotations != null) {
            QuotationResponse quotationResponse = null;

            try {
                quotationResponse = mapper.readValue(resultQuotations, QuotationResponse.class);
            } catch (IOException e) {
                // e.printStackTrace();
                return resultQuotations;
            }

            if (quotationResponse != null) {
                urlTransaction = urlTransaction + "/" + quotationResponse.getId() + "/transactions";
                String resultTransaction = callApiGenericThunesPOSTQuotationAndTransaction(apiKey, apiSecret, urlTransaction, transaction);
                System.out.println("resultTransaction =====> " + resultTransaction);
                TransactionResponse transactionResponse = null;
                if (resultTransaction != null) {
                    try {
                        transactionResponse = mapper.readValue(resultTransaction, TransactionResponse.class);
                    } catch (IOException e) {
                        //  e.printStackTrace();
                        return resultTransaction;
                    }

                    if (transactionResponse != null) {
                        urlConfirm = urlConfirm + "/" + transactionResponse.getId() + "/confirm";
                        String resultConfirmation = callApiGenericThunesPOSTConfirmation(apiKey, apiSecret, urlConfirm, "");
                        System.out.println("resultConfirmation =====> " + resultConfirmation);
                        return resultConfirmation;
                    }
                }
            }
        }
        return null;
    }

    public <T extends Object> String callApiGenericThunesPOSTConfirmation(String apiKey, String apiSecret, String url, T t) {

        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);
            UsernamePasswordCredentials creds = new UsernamePasswordCredentials(apiKey, apiSecret);
            httpPost.addHeader(new BasicScheme().authenticate(creds, httpPost, null));
            ObjectMapper mapper = new ObjectMapper();
            String data = mapper.writeValueAsString(t);
            System.out.println("data ::: " + data);
            System.out.println("URL DIO === " + url);
            StringEntity entity = new StringEntity(data);
            httpPost.setEntity(entity);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");
            CloseableHttpResponse response = client.execute(httpPost);
            System.out.println("Response =====> " + response.getStatusLine().getStatusCode());
            System.out.println("Response =====> " + response.getEntity().getContent());
            if (response.getStatusLine().getStatusCode() == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                StringBuilder sb = new StringBuilder();
                String line = null;
                readInputStreamResultCallHttp(response, reader, sb);
                return sb.toString();
            } else {
                BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                StringBuilder sb = new StringBuilder();
                String line = null;
                readInputStreamResultCallHttp(response, reader, sb);
                System.out.println("Error ====> " + sb.toString());
                return sb.toString();
            }
        } catch (AuthenticationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void readInputStreamResultCallHttp(CloseableHttpResponse response, BufferedReader reader, StringBuilder sb) {
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                response.getEntity().getContent().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String callApiGenericThunesGET(String apiKey, String apiSecret, String url) {

        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);
            UsernamePasswordCredentials creds = new UsernamePasswordCredentials(apiKey, apiSecret);
            httpGet.addHeader(new BasicScheme().authenticate(creds, httpGet, null));
            httpGet.setHeader("Accept", "application/json");
            // httpPost.setHeader("Content-type", "application/json");
            CloseableHttpResponse response = client.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                StringBuilder sb = new StringBuilder();
                String line = null;
                readInputStreamResultCallHttp(response, reader, sb);
                return sb.toString();
            } else {
                BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                StringBuilder sb = new StringBuilder();
                String line = null;
                readInputStreamResultCallHttp(response, reader, sb);
                System.out.println("Error ====> " + sb.toString());
                return sb.toString();
            }

        } catch (AuthenticationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public <T extends Object> String callApiGenericThunesPOSTQuotationAndTransaction(String apiKey, String apiSecret, String url, T t) {

        try {
            System.out.println("URL DIO === " + url);
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);
            UsernamePasswordCredentials creds = new UsernamePasswordCredentials(apiKey, apiSecret);
            httpPost.addHeader(new BasicScheme().authenticate(creds, httpPost, null));
            ObjectMapper mapper = new ObjectMapper();
            String data = mapper.writeValueAsString(t);
            System.out.println("data ::: " + data);
            StringEntity entity = new StringEntity(data);
            httpPost.setEntity(entity);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");
            CloseableHttpResponse response = client.execute(httpPost);
            System.out.println("Response QT =====> " + response.getStatusLine().getStatusCode());
            System.out.println("Response QT =====> " + response.getEntity().getContent());
            if (response.getStatusLine().getStatusCode() == 201) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                StringBuilder sb = new StringBuilder();
                String line = null;
                readInputStreamResultCallHttp(response, reader, sb);
                return sb.toString();
            } else {
                BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                StringBuilder sb = new StringBuilder();
                String line = null;
                readInputStreamResultCallHttp(response, reader, sb);
                System.out.println("Error ====> " + sb.toString());
                return sb.toString();
            }
        } catch (AuthenticationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
