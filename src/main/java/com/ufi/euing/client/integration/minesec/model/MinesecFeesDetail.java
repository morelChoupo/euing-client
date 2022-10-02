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
public class MinesecFeesDetail implements Serializable {

    private String fee_id;
    private String fee_name;
    private String fee_amount;
    private String partiality;

    public MinesecFeesDetail() {
    }

    public String getFee_id() {
        return fee_id;
    }

    public void setFee_id(String fee_id) {
        this.fee_id = fee_id;
    }

    public String getFee_name() {
        return fee_name;
    }

    public void setFee_name(String fee_name) {
        this.fee_name = fee_name;
    }

    public String getFee_amount() {
        return fee_amount;
    }

    public void setFee_amount(String fee_amount) {
        this.fee_amount = fee_amount;
    }

    public String getPartiality() {
        return partiality;
    }

    public void setPartiality(String partiality) {
        this.partiality = partiality;
    }

    @Override
    public String toString() {
        return "MinesecFeesDetail{" + "fee_id=" + fee_id + ", fee_name=" + fee_name + ", fee_amount=" + fee_amount + ", partiality=" + partiality + '}';
    }
}
