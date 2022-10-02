package com.ufi.euing.client.utils.others;

import java.security.SecureRandom;
import java.util.Base64;

public class GenerateCodeUtils {

    /**
     * Instantiates a new generate code utils.
     */
    private GenerateCodeUtils() {
    }

    /**
     * Generate secret key.
     *
     * @return the string
     */
    public static String generatePassword() {
        final SecureRandom random = new SecureRandom();
        final byte[] bytes = new byte[12];
        random.nextBytes(bytes);
        final Base64.Encoder encoder = Base64.getUrlEncoder().withoutPadding();
        return encoder.encodeToString(bytes);
    }


    public static String generateUserCode() {
        final SecureRandom random = new SecureRandom();
        final byte[] bytes = new byte[4];
        random.nextBytes(bytes);
        final Base64.Encoder encoder = Base64.getUrlEncoder().withoutPadding();
        return encoder.encodeToString(bytes);
    }

    public static String generateCode(int length) {
        final SecureRandom random = new SecureRandom();
        final byte[] bytes = new byte[length];
        random.nextBytes(bytes);
        final Base64.Encoder encoder = Base64.getUrlEncoder().withoutPadding();
        return encoder.encodeToString(bytes);
    }
}
