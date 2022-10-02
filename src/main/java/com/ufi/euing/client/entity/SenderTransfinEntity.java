package com.ufi.euing.client.entity;

import lombok.*;

import java.io.Serializable;


@Data

public class SenderTransfinEntity  implements Serializable {

    private String firstName;

    private String secondName;

    private String secondLastName;

    private String lastName;

    private String address1;

    private String address2;

    private String city;

    private String resident;

    private String state;

    private String postCode;

    private String country;

    private String phoneNumber;

    private String mobileNumber;

    private String email;



    public SenderTransfinEntity() {
    }

    public SenderTransfinEntity(
            Integer globeCardNumber,
            String firstName,
            String secondName,
            String secondLastName,
            String lastName,
            String address1,
            String address2,
            String city,
            String resident,
            String state,
            String postCode,
            String country,
            String phoneNumber,
            String mobileNumber,
            String email,
            int receiveMarketing,
            int updateCustomer,
            int registerCustomer,
           Integer sourceOfFundID) {

        this.firstName = firstName;
        this.secondName = secondName;
        this.secondLastName = secondLastName;
        this.lastName = lastName;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.resident=resident;
        this.state = state;
        this.postCode = postCode;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.mobileNumber = mobileNumber;
        this.email = email;

    }


    @Override
    public String toString() {
        return "SenderTransfinEntity [firstName=" + firstName + ", secondName="
                + secondName + ", secondLastName=" + secondLastName
                + ", lastName=" + lastName + ", address1=" + address1
                + ", address2=" + address2 + ", city=" + city + ", resident="
                + resident + ", state=" + state + ", postCode=" + postCode
                + ", country=" + country + ", phoneNumber=" + phoneNumber
                + ", mobileNumber=" + mobileNumber + ", email=" + email + "]";
    }


}
