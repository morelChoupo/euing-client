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
public class CurrenciesDetails {

    @JsonProperty("Code")
    private String Code;

    @JsonProperty("Name")
    private String Name;

    @Override
    public String toString() {
        return "CurrenciesDetails{" + "Code=" + Code + ", Name=" + Name + '}';
    }

}
