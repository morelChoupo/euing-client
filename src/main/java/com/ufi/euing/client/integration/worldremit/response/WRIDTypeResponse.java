/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufi.euing.client.integration.worldremit.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 *
 * @author User
 */

@Getter @Setter
public class WRIDTypeResponse implements Serializable {

    @JsonProperty("Code")
    private String code;
    @JsonProperty("Name")
    private String name;
    
    private int responseCode;
    private String responseDesc;

    @Override
    public String toString() {
        return "IDTypeResponse{" + "code=" + code + ", name=" + name + '}';
    }

}
