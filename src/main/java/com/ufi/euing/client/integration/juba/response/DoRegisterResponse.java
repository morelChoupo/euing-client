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
public class DoRegisterResponse {

    @JsonProperty("Response")
    public Response response;

    @JsonProperty("Data")
    public DoRegisterDetails data;

    @Override
    public String toString() {
        return "DoRegisterResponse{" + "response=" + response + ", data=" + data + '}';
    }

}
