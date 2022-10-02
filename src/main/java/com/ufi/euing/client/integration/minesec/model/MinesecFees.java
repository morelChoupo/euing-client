/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufi.euing.client.integration.minesec.model;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author UFI
 */
public class MinesecFees implements Serializable {

    private List<MinesecFeesDetail> fees_details;

    public MinesecFees() {
    }

    public List<MinesecFeesDetail> getFees_details() {
        return fees_details;
    }

    public void setFees_details(List<MinesecFeesDetail> fees_details) {
        this.fees_details = fees_details;
    }

    @Override
    public String toString() {
        return "MinesecFees{" + "fees_details=" + fees_details + '}';
    }

}
