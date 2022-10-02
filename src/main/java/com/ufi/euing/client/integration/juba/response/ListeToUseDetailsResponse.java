/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufi.euing.client.integration.juba.response;

import com.ufi.euing.client.entity.ListeToUse;
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
public class ListeToUseDetailsResponse implements Serializable {

    private int code;
    private String message;
    private List<ListeToUse> data;

    public ListeToUseDetailsResponse() {
    }

    public ListeToUseDetailsResponse(List<ListeToUse> data) {
        this.code = 200;
        this.message = "OK";
        this.data = data;
    }

    public ListeToUseDetailsResponse(int code, String message) {
        this.code = code;
        this.message = message;
        this.data = new ArrayList<>();
    }

    public ListeToUseDetailsResponse(int code, String message, List<ListeToUse> data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

}
