/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufi.euing.client.integration.juba.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author User
 */

@Getter @Setter
public class DoGetAgentRequest {

    @JsonProperty("Country")
    private String Country;

    @JsonProperty("City")
    private String City;


    @Override
    public String toString() {
        return "DoGetAgentRequest{" + "Country=" + Country + ", City=" + City + '}';
    }

}
