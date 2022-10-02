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
public class MinesecSchoolDetail implements Serializable {

    private String country_code;
    private String country_name;
    private String merchant_code;
    private String school_code;
    private String school_name;
    private String region_code;
    private String region_name;
    private String division;
    private String subdivision;
    private String city;
    private List<MinesecClass> classes;

    public MinesecSchoolDetail() {
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public String getMerchant_code() {
        return merchant_code;
    }

    public void setMerchant_code(String merchant_code) {
        this.merchant_code = merchant_code;
    }

    public String getSchool_code() {
        return school_code;
    }

    public void setSchool_code(String school_code) {
        this.school_code = school_code;
    }

    public String getSchool_name() {
        return school_name;
    }

    public void setSchool_name(String school_name) {
        this.school_name = school_name;
    }

    public String getRegion_code() {
        return region_code;
    }

    public void setRegion_code(String region_code) {
        this.region_code = region_code;
    }

    public String getRegion_name() {
        return region_name;
    }

    public void setRegion_name(String region_name) {
        this.region_name = region_name;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getSubdivision() {
        return subdivision;
    }

    public void setSubdivision(String subdivision) {
        this.subdivision = subdivision;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<MinesecClass> getClasses() {
        return classes;
    }

    public void setClasses(List<MinesecClass> classes) {
        this.classes = classes;
    }

    @Override
    public String toString() {
        return "MinesecSchoolDetail{" + "country_code=" + country_code + ", country_name=" + country_name + ", merchant_code=" + merchant_code + ", school_code=" + school_code + ", school_name=" + school_name + ", region_code=" + region_code + ", region_name=" + region_name + ", division=" + division + ", subdivision=" + subdivision + ", city=" + city + ", classes=" + classes + '}';
    }
}
