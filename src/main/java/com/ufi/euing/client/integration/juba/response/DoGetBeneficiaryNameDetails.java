/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufi.euing.client.integration.juba.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author User
 */

@Getter @Setter
public class DoGetBeneficiaryNameDetails {

    @JsonProperty("FirstName")
    private String FirstName;
    @JsonProperty("LastName")
    private String LastName;


    @Override
    public String toString() {
        return "DoGetBeneficiaryDetails{" + "FirstName=" + FirstName + ", LastName=" + LastName + '}';
    }

}
