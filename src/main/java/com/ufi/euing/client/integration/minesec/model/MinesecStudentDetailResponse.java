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
public class MinesecStudentDetailResponse implements Serializable {

    private int status;
    private String message;
    private List<MinesecStudentDetail> result;

    public MinesecStudentDetailResponse() {
    }

    public MinesecStudentDetailResponse(List<MinesecStudentDetail> result) {
        this.status = 200;
        this.message = "OK";
        this.result = result;
    }

    public MinesecStudentDetailResponse(int status, String message) {
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

    public List<MinesecStudentDetail> getResult() {
        return result;
    }

    public void setResult(List<MinesecStudentDetail> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "MinesecStudentDetailResponse{" + "status=" + status + ", message=" + message + ", result=" + result + '}';
    }
}
