/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufi.euing.client.integration.juba.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 *
 * @author User
 */

@Getter @Setter
public class CotationResponse implements Serializable {

    @JsonProperty("Response")
    public Response response;

    @JsonProperty("Data")
    public CotationDetails data;

    @Override
    public String toString() {
        return "CotationResponse{" + "response=" + response + ", data=" + data + '}';
    }

}
