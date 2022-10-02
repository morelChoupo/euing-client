/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufi.euing.client.integration.juba.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 *
 * @author User
 */

@Getter @Setter
public class DoGetPayRemittanceStatusResponse {

    @JsonProperty("Response")
    public Response response;

    @JsonProperty("Data")
    public List<DoGetPayRemittanceStatusDetails> data;

    @Override
    public String toString() {
        return "DoGetPayRemittanceStatusResponse{" + "response=" + response + ", data=" + data + '}';
    }

}
