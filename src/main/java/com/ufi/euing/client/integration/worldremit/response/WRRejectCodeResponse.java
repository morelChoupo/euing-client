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
 * @author UFI
 */

@Getter @Setter
public class WRRejectCodeResponse implements Serializable {

    @JsonProperty("code")
    private String code;
    @JsonProperty("description")
    private String description;
    
    private int responseCode;
    private String responseDesc;

    @Override
    public String toString() {
        return "RejectCodeResponse{" + "code=" + code + ", description=" + description + '}';
    }
}
