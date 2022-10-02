/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufi.euing.client.integration.juba.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 *
 * @author User
 */
public class DoGetAgentResponse {

    @JsonProperty("Response")
    public Response response;

    @JsonProperty("Data")
    public List<DoGetAgentsDetails> data;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public List<DoGetAgentsDetails> getData() {
        return data;
    }

    public void setData(List<DoGetAgentsDetails> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DoGetAgentResponse{" + "response=" + response + ", data=" + data + '}';
    }

}
