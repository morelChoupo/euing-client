package com.ufi.euing.client.integration.thunes.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter @Setter
public class Beneficiary implements Serializable {

    private String address;
    private String bank_account_holder_name;
    private String city;
    private String code;
    private String country_iso_code;
    private String country_of_birth_iso_code;
    private String date_of_birth;
    private String email;
    private String firstname;
    private String gender;
    private String id_country_iso_code;
    private String id_delivery_date;
    private String id_expiration_date;
    private String id_number;
    private String id_type;
    private String lastname;
    private String lastname2;
    private String middlename;
    private String msisdn;
    private String nationality_country_iso_code;
    private String nativename;
    private String occupation;
    private String postal_code;
    private String province_state;

    @Override
    public String toString() {
        return "Beneficiary{" + "address=" + address + ", bank_account_holder_name=" + bank_account_holder_name + ", city=" + city + ", code=" + code + ", country_iso_code=" + country_iso_code + ", country_of_birth_iso_code=" + country_of_birth_iso_code + ", date_of_birth=" + date_of_birth + ", email=" + email + ", firstname=" + firstname + ", gender=" + gender + ", id_country_iso_code=" + id_country_iso_code + ", id_delivery_date=" + id_delivery_date + ", id_expiration_date=" + id_expiration_date + ", id_number=" + id_number + ", id_type=" + id_type + ", lastname=" + lastname + ", lastname2=" + lastname2 + ", middlename=" + middlename + ", msisdn=" + msisdn + ", nationality_country_iso_code=" + nationality_country_iso_code + ", nativename=" + nativename + ", occupation=" + occupation + ", postal_code=" + postal_code + ", province_state=" + province_state + '}';
    }
}
