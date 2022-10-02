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
public class MinesecPayFees implements Serializable {

    Long guichetId;
    Long usersessionId;
    String payerName;
    String payerPhone;
    MinesecStudentDetail studentDetail;
    String academicYear;
    Double localAmount;
    String destinationCountry;
    String category;

    public MinesecPayFees() {
    }

    public Long getGuichetId() {
        return guichetId;
    }

    public void setGuichetId(Long guichetId) {
        this.guichetId = guichetId;
    }

    public Long getUsersessionId() {
        return usersessionId;
    }

    public void setUsersessionId(Long usersessionId) {
        this.usersessionId = usersessionId;
    }

    public String getPayerName() {
        return payerName;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }

    public String getPayerPhone() {
        return payerPhone;
    }

    public void setPayerPhone(String payerPhone) {
        this.payerPhone = payerPhone;
    }

    public MinesecStudentDetail getStudentDetail() {
        return studentDetail;
    }

    public void setStudentDetail(MinesecStudentDetail studentDetail) {
        this.studentDetail = studentDetail;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public Double getLocalAmount() {
        return localAmount;
    }

    public void setLocalAmount(Double localAmount) {
        this.localAmount = localAmount;
    }

    public String getDestinationCountry() {
        return destinationCountry;
    }

    public void setDestinationCountry(String destinationCountry) {
        this.destinationCountry = destinationCountry;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "MinesecPayFees{" + "guichetId=" + guichetId + ", usersessionId=" + usersessionId + ", payerName=" + payerName + ", payerPhone=" + payerPhone + ", studentDetail=" + studentDetail + ", academicYear=" + academicYear + ", localAmount=" + localAmount + ", destinationCountry=" + destinationCountry + ", category=" + category + '}';
    }

}
