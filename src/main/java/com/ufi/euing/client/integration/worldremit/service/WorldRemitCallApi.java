package com.ufi.euing.client.integration.worldremit.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;


@Component
public class WorldRemitCallApi implements Serializable {

    public <T extends Object> String callApiGenericWorldRemit(String httpMethod, String apiKey, String apiSecret, String url, T t) {
        HttpURLConnection urlConnection;
        String result;
        try {
            String auth = apiKey + ":" + apiSecret;
            String base64 = new String(Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII"))));
            System.out.println("Authorization : " + base64);
            //Connect
            urlConnection = (HttpURLConnection) ((new URL(url).openConnection()));
            urlConnection.setDoOutput(true);
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setRequestProperty("Content-type", "application/json");
            urlConnection.setRequestProperty("Authorization", "Basic " + base64);
            urlConnection.setRequestMethod(httpMethod);
            //urlConnection.setConnectTimeout(10000);
            urlConnection.connect();

            //Write
            OutputStream outputStream;
            outputStream = urlConnection.getOutputStream();
            BufferedWriter writer;
            writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            if (t == null) {
                ObjectMapper mapper = new ObjectMapper();
                String data = mapper.writeValueAsString(t);
                System.out.println("data = " + data);
                writer.write(data);
            }
            writer.close();
            outputStream.close();
            int responseCode = urlConnection.getResponseCode();
            BufferedReader bufferedReader;
            Map<String, String> m = new HashMap<>();
            m.put("responseCode", "200");
            m.put("responseDesc", "OK");
            if (responseCode == HttpsURLConnection.HTTP_CREATED) {
                bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
                String line;
                StringBuilder sb = new StringBuilder();
                sb.append(m);
                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }
                bufferedReader.close();
                result = sb.toString();
            } else {
                if (responseCode == HttpsURLConnection.HTTP_OK) {
                    bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
                    String line;
                    StringBuilder sb = new StringBuilder();
                    sb.append(m);
                    while ((line = bufferedReader.readLine()) != null) {
                        sb.append(line);
                    }
                    bufferedReader.close();
                    result = sb.toString();
                } else {
                    result = "{\"responseCode\": \"" + urlConnection.getResponseCode() + "\", \"responseDesc\": \"Error ::: " + urlConnection.getResponseCode() + "\"}";
                }
            }
        } catch (IOException e) {
            System.out.println("Error ::: " + e.getMessage());
            result = "{\"responseCode\": \"500\", \"responseDesc\": \"Error ::: " + e.getMessage() + "\"}";
            e.printStackTrace();
        }
        System.out.println("result = " + result);
        return result;
    }

}
