package com.ufi.euing.client.dto;

import lombok.Data;

@Data
public class UserCreateDTO {

    private String usrNom;
    private String usrPrenom;
    private String usrPassword;
    private String phoneNumber;
    private String country;
    private String usrEmail;
    private String usrEmailRec;
}
