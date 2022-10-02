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
public class DoGetBeneficiaryNameResponse {

    @JsonProperty("Response")
    public Response response;

    @JsonProperty("Data")
    public DoGetBeneficiaryNameDetails data;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public DoGetBeneficiaryNameDetails getData() {
        return data;
    }

    public void setData(DoGetBeneficiaryNameDetails data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DoGetBeneficiaryResponse{" + "response=" + response + ", data=" + data + '}';
    }

}
