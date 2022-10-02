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
public class MinesecClass implements Serializable {

    private String class_id;
    private String class_name;
    private MinesecFees fees;
    private List<MinesecOption> options;

    public MinesecClass() {
    }

    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public MinesecFees getFees() {
        return fees;
    }

    public void setFees(MinesecFees fees) {
        this.fees = fees;
    }

    public List<MinesecOption> getOptions() {
        return options;
    }

    public void setOptions(List<MinesecOption> options) {
        this.options = options;
    }

    @Override
    public String toString() {
        return "MinesecClass{" + "class_id=" + class_id + ", class_name=" + class_name + ", fees=" + fees + ", options=" + options + '}';
    }

}
