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
public class ModeOfPaymentDetails {

    @JsonProperty("Id")
    private Long Id;

    @JsonProperty("Name")
    private String Name;

    @Override
    public String toString() {
        return "ModeOfPaymentDetails{" + "Id=" + Id + ", Name=" + Name + '}';
    }

}
