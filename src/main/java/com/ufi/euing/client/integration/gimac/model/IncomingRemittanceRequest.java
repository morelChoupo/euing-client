/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufi.euing.client.integration.gimac.model;

/**
 *
 * @author User
 */
public class IncomingRemittanceRequest {

    private String issuertrxref;
    private String intent;
    private Long createtime;
    private String sendermobile;
    private String description;
    private String tomember;
    private double amount;
    private String currency;
    private SenderCustomerData sendercustomerdata;
    private ReceiverCustomerData receivercustomerdata;

    public String getIssuertrxref() {
        return issuertrxref;
    }

    public void setIssuertrxref(String issuertrxref) {
        this.issuertrxref = issuertrxref;
    }

    public String getIntent() {
        return intent;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }

    public Long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }

    public String getSendermobile() {
        return sendermobile;
    }

    public void setSendermobile(String sendermobile) {
        this.sendermobile = sendermobile;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTomember() {
        return tomember;
    }

    public void setTomember(String tomember) {
        this.tomember = tomember;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public SenderCustomerData getSendercustomerdata() {
        return sendercustomerdata;
    }

    public void setSendercustomerdata(SenderCustomerData sendercustomerdata) {
        this.sendercustomerdata = sendercustomerdata;
    }

    public ReceiverCustomerData getReceivercustomerdata() {
        return receivercustomerdata;
    }

    public void setReceivercustomerdata(ReceiverCustomerData receivercustomerdata) {
        this.receivercustomerdata = receivercustomerdata;
    }

    @Override
    public String toString() {
        return "IncomingRemittanceRequest{" + "issuertrxref=" + issuertrxref + ", intent=" + intent + ", createtime=" + createtime + ", sendermobile=" + sendermobile + ", description=" + description + ", tomember=" + tomember + ", amount=" + amount + ", currency=" + currency + ", sendercustomerdata=" + sendercustomerdata + ", receivercustomerdata=" + receivercustomerdata + '}';
    }

}
