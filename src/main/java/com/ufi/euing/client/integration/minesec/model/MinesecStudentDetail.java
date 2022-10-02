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
public class MinesecStudentDetail implements Serializable {

    private String student_regnumber;
    private String student_name;
    private String student_birthdate;
    private String student_gender;
    private String student_class_id;
    private String student_class_name;
    private String student_option_id;
    private String student_option_name;
    private String student_phone;
    private String student_email;
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
    private List<MinesecFeesDetail> fees;

    public MinesecStudentDetail() {
    }

    public String getStudent_regnumber() {
        return student_regnumber;
    }

    public void setStudent_regnumber(String student_regnumber) {
        this.student_regnumber = student_regnumber;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getStudent_birthdate() {
        return student_birthdate;
    }

    public void setStudent_birthdate(String student_birthdate) {
        this.student_birthdate = student_birthdate;
    }

    public String getStudent_gender() {
        return student_gender;
    }

    public void setStudent_gender(String student_gender) {
        this.student_gender = student_gender;
    }

    public String getStudent_class_id() {
        return student_class_id;
    }

    public void setStudent_class_id(String student_class_id) {
        this.student_class_id = student_class_id;
    }

    public String getStudent_class_name() {
        return student_class_name;
    }

    public void setStudent_class_name(String student_class_name) {
        this.student_class_name = student_class_name;
    }

    public String getStudent_option_id() {
        return student_option_id;
    }

    public void setStudent_option_id(String student_option_id) {
        this.student_option_id = student_option_id;
    }

    public String getStudent_option_name() {
        return student_option_name;
    }

    public void setStudent_option_name(String student_option_name) {
        this.student_option_name = student_option_name;
    }

    public String getStudent_phone() {
        return student_phone;
    }

    public void setStudent_phone(String student_phone) {
        this.student_phone = student_phone;
    }

    public String getStudent_email() {
        return student_email;
    }

    public void setStudent_email(String student_email) {
        this.student_email = student_email;
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

    public List<MinesecFeesDetail> getFees() {
        return fees;
    }

    public void setFees(List<MinesecFeesDetail> fees) {
        this.fees = fees;
    }

    @Override
    public String toString() {
        return "MinesecStudentDetail{" + "student_regnumber=" + student_regnumber + ", student_name=" + student_name + ", student_birthdate=" + student_birthdate + ", student_gender=" + student_gender + ", student_class_id=" + student_class_id + ", student_class_name=" + student_class_name + ", student_option_id=" + student_option_id + ", student_option_name=" + student_option_name + ", student_phone=" + student_phone + ", student_email=" + student_email + ", country_code=" + country_code + ", country_name=" + country_name + ", merchant_code=" + merchant_code + ", school_code=" + school_code + ", school_name=" + school_name + ", region_code=" + region_code + ", region_name=" + region_name + ", division=" + division + ", subdivision=" + subdivision + ", city=" + city + ", fees=" + fees + '}';
    }

}
