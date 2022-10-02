/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufi.euing.client.integration.gimac.model;

/**
 *
 * @author UFI
 */
public class InquiryRequest {

    private String issuertrxref;

    public String getIssuertrxref() {
        return issuertrxref;
    }

    public void setIssuertrxref(String issuertrxref) {
        this.issuertrxref = issuertrxref;
    }

    @Override
    public String toString() {
        return "InquiryRequest{" + "issuertrxref=" + issuertrxref + '}';
    }
}
