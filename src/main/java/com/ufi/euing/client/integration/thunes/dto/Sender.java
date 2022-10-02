package com.ufi.euing.client.integration.thunes.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter @Setter
public class Sender implements Serializable {

    String lastname;
    String firstname;
    String nationality_country_iso_code;
    String date_of_birth;
    String country_of_birth_iso_code;
    String gender;
    String address;
    String postal_code;
    String city;
    String country_iso_code;
    String msisdn;
    String email;
    String id_type;
    String id_number;
    String id_delivery_date;
    String occupation;

    @Override
    public String toString() {
        return "Sender{" + "lastname=" + lastname + ", firstname=" + firstname + ", nationality_country_iso_code=" + nationality_country_iso_code + ", date_of_birth=" + date_of_birth + ", country_of_birth_iso_code=" + country_of_birth_iso_code + ", gender=" + gender + ", address=" + address + ", postal_code=" + postal_code + ", city=" + city + ", country_iso_code=" + country_iso_code + ", msisdn=" + msisdn + ", email=" + email + ", id_type=" + id_type + ", id_number=" + id_number + ", id_delivery_date=" + id_delivery_date + ", occupation=" + occupation + '}';
    }

}
