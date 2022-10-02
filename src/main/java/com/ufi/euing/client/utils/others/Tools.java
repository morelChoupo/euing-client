package com.ufi.euing.client.utils.others;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

@Component
public class Tools {

    private static final Logger LOGGER = LoggerFactory.getLogger(Tools.class);

    /**
     * Allow to sign a message with a secret code using HMACSHA1 Algorithm
     * @param message - message to sign
     * @param secret - secret use to sign
     * @return - a sign data
     * @throws NoSuchAlgorithmException - throw when api didn't find algorithm
     * @throws InvalidKeyException - throw when invalidKey founded
     */
    public String sign(String message, String secret) throws NoSuchAlgorithmException, InvalidKeyException {
        SecretKeySpec signingKey = new SecretKeySpec(secret.getBytes(), "HmacSHA1");
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(signingKey);
        return hexToString(mac.doFinal(message.getBytes()));
    }


    /**
     * Allow to convert an hexadecimal data to String
     * @param bytes - hexadecimal data
     * @return - String to return
     */
    private String hexToString(byte[] bytes) {
        try(Formatter formatter = new Formatter();) {
            for (byte b : bytes) {
                formatter.format("%02x", b);
            }
            return formatter.toString();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }


    public static String encodePassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}

