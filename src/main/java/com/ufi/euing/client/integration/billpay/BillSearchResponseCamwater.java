/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufi.euing.client.integration.billpay;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 *
 * @author UFI
 */

@Getter @Setter
public class BillSearchResponseCamwater {

    private int code;
    private String message;
    private List<ResponseCamwater> data;

    public BillSearchResponseCamwater() {
    }

    public BillSearchResponseCamwater(int code, String message, List<ResponseCamwater> data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    @Override
    public String toString() {
        return "BillSearchResponseCamwater{" + "code=" + code + ", message=" + message + ", data=" + data + '}';
    }
}
