package com.ufi.euing.client.integration.thunes.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter @Setter
public class Beneficiary implements Serializable {

    private String lastname;
    private String firstname;
    private String nationality_country_iso_code;
    private String date_of_birth;
    private String country_of_birth_iso_code;
    private String gender;
    private String address;
    private String postal_code;
    private String city;
    private String country_iso_code;
    private String msisdn;
    private String email;
    private String id_type;
    private String id_country_iso_code;
    private String id_number;
    private String occupation;

    @Override
    public String toString() {
        return "Beneficiary{" + "lastname=" + lastname + ", firstname=" + firstname + ", nationality_country_iso_code=" + nationality_country_iso_code + ", date_of_birth=" + date_of_birth + ", country_of_birth_iso_code=" + country_of_birth_iso_code + ", gender=" + gender + ", address=" + address + ", postal_code=" + postal_code + ", city=" + city + ", country_iso_code=" + country_iso_code + ", msisdn=" + msisdn + ", email=" + email + ", id_type=" + id_type + ", id_country_iso_code=" + id_country_iso_code + ", id_number=" + id_number + ", occupation=" + occupation + '}';
    }

}
