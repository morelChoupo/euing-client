package com.ufi.euing.client.dto;


import lombok.Data;

@Data
public class UserUpdateDto {
    private String usrNom;
    private String usrPrenom;
    private String phoneNumber;
    private String country;
    private String usrEmail;
    private String usrEmailRec;

}
