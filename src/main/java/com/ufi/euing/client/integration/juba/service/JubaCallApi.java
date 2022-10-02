package com.ufi.euing.client.integration.juba.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

@Component
public class JubaCallApi implements Serializable {

    public <T extends Object> String callApiGenericJubaPOST(String version, String apiKey, String apiSecret, String url, T t) {
        HttpURLConnection urlConnection;
        String result; // = "{\"Response\": {\"Code\": \"500\", \"Message\": \"Error ::: Connection timeout.\"},\"Data\": []}";
        //int responseCode = 0;
        try {
            String auth = apiKey + ":" + apiSecret;
            String base64 = new String(Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII"))));
            System.out.println("Authorization : " + base64);
            //Connect
            urlConnection = (HttpURLConnection) ((new URL(url).openConnection()));
            urlConnection.setDoOutput(true);
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setRequestProperty("X-Version", version);

            urlConnection.setRequestProperty("Content-type", "application/json");
            urlConnection.setRequestProperty("Authorization", "Basic " + base64);
            urlConnection.setRequestMethod("POST");
            //urlConnection.setConnectTimeout(10000);
            urlConnection.connect();

            ObjectMapper mapper = new ObjectMapper();
            String data = mapper.writeValueAsString(t);
            System.out.println("data = " + data);
            //Write
            OutputStream outputStream;
            outputStream = urlConnection.getOutputStream();
            BufferedWriter writer;
            writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            writer.write(data);
            writer.close();
            outputStream.close();
            int responseCode = urlConnection.getResponseCode();
            BufferedReader bufferedReader;
            if (responseCode == HttpsURLConnection.HTTP_CREATED) {
                bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
                String line;
                StringBuilder sb = new StringBuilder();
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
                    while ((line = bufferedReader.readLine()) != null) {
                        sb.append(line);
                    }
                    bufferedReader.close();
                    result = sb.toString();
                } else {
                    result = "{\"Response\": {\"Code\": \"" + urlConnection.getResponseCode() + "\", \"Message\": \"Error ::: " + urlConnection.getResponseMessage() + "\"},\"Data\": []}";
                }
            }
            System.out.println("result ::: " + result);
        } catch (IOException e) {
            System.out.println("Error ::: " + e.getMessage());
            //result = "Call HTTP Exception ::: " + e.getMessage();
            result = "{\"Response\": {\"Code\": \"500\", \"Message\": \"Call HTTP Exception ::: " + e.getMessage() + "\"},\"Data\": []}";
            e.printStackTrace();
        }
        return result;
    }

}
