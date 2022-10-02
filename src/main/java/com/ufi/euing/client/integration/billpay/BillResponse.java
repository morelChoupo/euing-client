/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufi.euing.client.integration.billpay;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author UFI
 * @param <T>
 */

@Getter @Setter
public class BillResponse<T> implements Serializable {

    private int code;
    private String message;
    private List<T> data;

    public BillResponse() {
    }

    public BillResponse(int code, String message, List<T> data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public BillResponse(int code, String message) {
        this.code = code;
        this.message = message;
        this.data = new ArrayList<>();
    }

    public BillResponse(List<T> data) {
        this.code = 200;
        this.message = "OK";
        this.data = data;
    }

    @Override
    public String toString() {
        return "BillResponse{" + "code=" + code + ", message=" + message + ", data=" + data + '}';
    }
}
