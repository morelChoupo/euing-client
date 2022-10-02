package com.ufi.euing.client.integration.billpay;

public class SingleAuthEneo {
    //   static final Logger LOGGER = LoggerFactory.getLogger(SingleAuthEneo.class);

    private static SingleAuthEneo singleAuthEneoInstance = null;
    public LoginResponse loginResponse;

    private SingleAuthEneo() {
        //
    }

    public static SingleAuthEneo getInstance() {
        if (singleAuthEneoInstance == null) {
            return new SingleAuthEneo();
        }
        return singleAuthEneoInstance;
    }
}
