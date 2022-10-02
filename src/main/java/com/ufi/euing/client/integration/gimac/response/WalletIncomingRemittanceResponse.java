/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufi.euing.client.integration.gimac.response;

import com.ufi.euing.client.integration.gimac.model.WalletIncomingRemittanceRequest;


/**
 *
 * @author User
 */
public class WalletIncomingRemittanceResponse extends WalletIncomingRemittanceRequest {

    private String state;
    private String vouchercode;
    private String acquirertrxref;
    private String frommember;
    private String error;
    private String error_description;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getVouchercode() {
        return vouchercode;
    }

    public void setVouchercode(String vouchercode) {
        this.vouchercode = vouchercode;
    }

    public String getAcquirertrxref() {
        return acquirertrxref;
    }

    public void setAcquirertrxref(String acquirertrxref) {
        this.acquirertrxref = acquirertrxref;
    }

    public String getFrommember() {
        return frommember;
    }

    public void setFrommember(String frommember) {
        this.frommember = frommember;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError_description() {
        return error_description;
    }

    public void setError_description(String error_description) {
        this.error_description = error_description;
    }

    @Override
    public String toString() {
        return "WalletIncomingRemittanceResponse{" + "state=" + state + ", vouchercode=" + vouchercode + ", acquirertrxref=" + acquirertrxref + ", frommember=" + frommember + ", error=" + error + ", error_description=" + error_description + '}';
    }

}
