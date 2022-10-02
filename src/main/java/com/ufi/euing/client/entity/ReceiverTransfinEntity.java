package com.ufi.euing.client.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Data @Getter @Setter
public class ReceiverTransfinEntity  implements Serializable {


    private String firstName;

    private String secondName;

    private String secondLastName;

    private String lastName;

    private String address1;

    private String address2;

    private String city;

    private String state;

    private String postCode;

    private String country;

    private String phoneNumber;

    private String mobileNumber;

    private String email;

    public ReceiverTransfinEntity() {
    }

    public ReceiverTransfinEntity(
            String firstName,
            String secondName,
            String secondLastName,
            String lastName,
            String address1,
            String address2,
            String city,
            String state,
            String postCode,
            String country,
            String phoneNumber,
            String mobileNumber,
            String email) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.secondLastName = secondLastName;
        this.lastName = lastName;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.state = state;
        this.postCode = postCode;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.mobileNumber = mobileNumber;
        this.email = email;
    }

    @Override
    public String toString() {
        return "ReceiverTransfinEntity [firstName=" + firstName
                + ", secondName=" + secondName + ", secondLastName="
                + secondLastName + ", lastName=" + lastName + ", address1="
                + address1 + ", address2=" + address2 + ", city=" + city
                + ", state=" + state + ", postCode=" + postCode + ", country="
                + country + ", phoneNumber=" + phoneNumber + ", mobileNumber="
                + mobileNumber + ", email=" + email + "]";
    }





}

