/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufi.euing.client.integration.juba.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author UFI
 */

@Getter @Setter
public class GetAgentsDetailsResponse implements Serializable {

    private int code;
    private String message;
    private List<DoGetAgentsDetails> data;

    public GetAgentsDetailsResponse() {
    }

    public GetAgentsDetailsResponse(List<DoGetAgentsDetails> data) {
        this.code = 200;
        this.message = "OK";
        this.data = data;
    }

    public GetAgentsDetailsResponse(int code, String message) {
        this.code = code;
        this.message = message;
        this.data = new ArrayList<>();
    }

    public GetAgentsDetailsResponse(int code, String message, List<DoGetAgentsDetails> data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    @Override
    public String toString() {
        return "GetAgentsDetailsResponse{" + "code=" + code + ", message=" + message + ", data=" + data + '}';
    }
}
