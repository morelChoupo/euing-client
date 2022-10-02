/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufi.euing.client.integration.minesec.model;

import java.io.Serializable;

/**
 *
 * @author UFI
 */
public class MinesecFeesDetailResponse implements Serializable {

    private int status;
    private String message;
    private MinesecFeesResponse result;

    public MinesecFeesDetailResponse() {
    }

    public MinesecFeesDetailResponse(MinesecFeesResponse result) {
        this.status = 200;
        this.message = "OK";
        this.result = result;
    }

    public MinesecFeesDetailResponse(int status, String message) {
        this.status = status;
        this.message = message;
        this.result = null;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MinesecFeesResponse getResult() {
        return result;
    }

    public void setResult(MinesecFeesResponse result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "MinesecFeesDetailResponse{" + "status=" + status + ", message=" + message + ", result=" + result + '}';
    }
}
