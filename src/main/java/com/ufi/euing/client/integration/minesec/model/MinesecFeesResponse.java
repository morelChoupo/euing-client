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
public class MinesecFeesResponse implements Serializable {

    private List<MinesecSchoolDetail> school_details;
    private String class_name;
    private List<MinesecFeesDetail> fees_details;

    public MinesecFeesResponse() {
    }

    public List<MinesecSchoolDetail> getSchool_details() {
        return school_details;
    }

    public void setSchool_details(List<MinesecSchoolDetail> school_details) {
        this.school_details = school_details;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public List<MinesecFeesDetail> getFees_details() {
        return fees_details;
    }

    public void setFees_details(List<MinesecFeesDetail> fees_details) {
        this.fees_details = fees_details;
    }

    @Override
    public String toString() {
        return "MinesecFeesResponse{" + "school_details=" + school_details + ", class_name=" + class_name + ", fees_details=" + fees_details + '}';
    }

}
