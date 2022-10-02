/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufi.euing.client.integration.minesec.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author UFI
 */
public class MinesecSchoolDetailResponse implements Serializable {

    private int status;
    private String message;
    private List<MinesecSchoolDetail> result;

    public MinesecSchoolDetailResponse() {
    }

    public MinesecSchoolDetailResponse(List<MinesecSchoolDetail> result) {
        this.status = 200;
        this.message = "OK";
        this.result = result;
    }

    public MinesecSchoolDetailResponse(int status, String message) {
        this.status = status;
        this.message = message;
        this.result = new ArrayList<>();
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

    public List<MinesecSchoolDetail> getResult() {
        return result;
    }

    public void setResult(List<MinesecSchoolDetail> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "MinesecResp{" + "status=" + status + ", message=" + message + ", result=" + result + '}';
    }
}
