/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufi.euing.client.integration.juba.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author User
 */

@Data @NoArgsConstructor
public class DoRegisterCustomer {

    @JsonProperty("FirstName")
    private String FirstName;
    @JsonProperty("MiddleName")
    private String MiddleName;
    @JsonProperty("LastName")
    private String LastName;
    @JsonProperty("StreetNo")
    private String StreetNo;
    @JsonProperty("House")
    private String House;
    @JsonProperty("DateOfBrith")
    private String DateOfBrith;
    @JsonProperty("Email")
    private String Email;
    @JsonProperty("Telephone")
    private String Telephone;
    @JsonProperty("Mobile")
    private String Mobile;
    @JsonProperty("City")
    private String City;
    @JsonProperty("State")
    private String State;
    @JsonProperty("PlaceOfBirth")
    private String PlaceOfBirth;
    @JsonProperty("Nationality")
    private String Nationality;
    @JsonProperty("PostalCode")
    private String PostalCode;
    @JsonProperty("Occupation")
    private String Occupation;
    @JsonProperty("Remarks")
    private String Remarks;
    @JsonProperty("CustomerReferenceNo")
    private String CustomerReferenceNo;
    @JsonProperty("IsBeneficiary")
    private Boolean IsBeneficiary;

    @Override
    public String toString() {
        return "DoRegisterCustomer{" + "FirstName=" + FirstName + ", MiddleName=" + MiddleName + ", LastName=" + LastName + ", StreetNo=" + StreetNo + ", House=" + House + ", DateOfBrith=" + DateOfBrith + ", Email=" + Email + ", Telephone=" + Telephone + ", Mobile=" + Mobile + ", City=" + City + ", State=" + State + ", PlaceOfBirth=" + PlaceOfBirth + ", Nationality=" + Nationality + ", PostalCode=" + PostalCode + ", Occupation=" + Occupation + ", Remarks=" + Remarks + ", CustomerReferenceNo=" + CustomerReferenceNo + ", IsBeneficiary=" + IsBeneficiary + '}';
    }

}
