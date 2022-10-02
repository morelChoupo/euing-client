package com.ufi.euing.client.integration.juba.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class PaymentConfirmation implements Serializable {

    @JsonProperty("ReferenceNum")
    public String referenceNum;

    @JsonProperty("PayerCode")
    public String payerCode;

    @JsonProperty("PartnerTranReferenceNum")
    public String PartnerTranReferenceNum;

    @JsonProperty("BeneficiaryStreetNo")
    public String beneficiaryStreetNo;

    @JsonProperty("BeneficiaryHouse")
    public String beneficiaryHouse;

    @JsonProperty("BeneficiaryDateOfBrith")
    public String beneficiaryDateOfBrith;

    @JsonProperty("BeneficiaryEmail")
    public String beneficiaryEmail;

    @JsonProperty("BeneficiaryTelephone")
    public String beneficiaryTelephone;

    @JsonProperty("BeneficiaryState")
    public String beneficiaryState;

    @JsonProperty("BeneficiaryPlaceOfBirth")
    public String beneficiaryPlaceOfBirth;

    @JsonProperty("BeneficiaryNationality")
    public String beneficiaryNationality;

    @JsonProperty("BeneficiaryPostalCode")
    public String beneficiaryPostalCode;

    @JsonProperty("BeneficiaryOccupation")
    public String beneficiaryOccupation;

    @JsonProperty("BeneficiaryRemarks")
    public String beneficiaryRemarks;

    public String getReferenceNum() {
        return referenceNum;
    }

    public void setReferenceNum(String referenceNum) {
        this.referenceNum = referenceNum;
    }

    public String getPayerCode() {
        return payerCode;
    }

    public void setPayerCode(String payerCode) {
        this.payerCode = payerCode;
    }

    public String getBeneficiaryStreetNo() {
        return beneficiaryStreetNo;
    }

    public void setBeneficiaryStreetNo(String beneficiaryStreetNo) {
        this.beneficiaryStreetNo = beneficiaryStreetNo;
    }

    public String getBeneficiaryHouse() {
        return beneficiaryHouse;
    }

    public void setBeneficiaryHouse(String beneficiaryHouse) {
        this.beneficiaryHouse = beneficiaryHouse;
    }

    public String getBeneficiaryDateOfBrith() {
        return beneficiaryDateOfBrith;
    }

    public void setBeneficiaryDateOfBrith(String beneficiaryDateOfBrith) {
        this.beneficiaryDateOfBrith = beneficiaryDateOfBrith;
    }

    public String getBeneficiaryEmail() {
        return beneficiaryEmail;
    }

    public void setBeneficiaryEmail(String beneficiaryEmail) {
        this.beneficiaryEmail = beneficiaryEmail;
    }

    public String getBeneficiaryTelephone() {
        return beneficiaryTelephone;
    }

    public void setBeneficiaryTelephone(String beneficiaryTelephone) {
        this.beneficiaryTelephone = beneficiaryTelephone;
    }

    public String getBeneficiaryState() {
        return beneficiaryState;
    }

    public void setBeneficiaryState(String beneficiaryState) {
        this.beneficiaryState = beneficiaryState;
    }

    public String getBeneficiaryPlaceOfBirth() {
        return beneficiaryPlaceOfBirth;
    }

    public void setBeneficiaryPlaceOfBirth(String beneficiaryPlaceOfBirth) {
        this.beneficiaryPlaceOfBirth = beneficiaryPlaceOfBirth;
    }

    public String getBeneficiaryNationality() {
        return beneficiaryNationality;
    }

    public void setBeneficiaryNationality(String beneficiaryNationality) {
        this.beneficiaryNationality = beneficiaryNationality;
    }

    public String getBeneficiaryPostalCode() {
        return beneficiaryPostalCode;
    }

    public void setBeneficiaryPostalCode(String beneficiaryPostalCode) {
        this.beneficiaryPostalCode = beneficiaryPostalCode;
    }

    public String getBeneficiaryOccupation() {
        return beneficiaryOccupation;
    }

    public void setBeneficiaryOccupation(String beneficiaryOccupation) {
        this.beneficiaryOccupation = beneficiaryOccupation;
    }

    public String getBeneficiaryRemarks() {
        return beneficiaryRemarks;
    }

    public void setBeneficiaryRemarks(String beneficiaryRemarks) {
        this.beneficiaryRemarks = beneficiaryRemarks;
    }

    public String getPartnerTranReferenceNum() {
        return PartnerTranReferenceNum;
    }

    public void setPartnerTranReferenceNum(String PartnerTranReferenceNum) {
        this.PartnerTranReferenceNum = PartnerTranReferenceNum;
    }

    @Override
    public String toString() {
        return "PaymentConfirmation{" + "referenceNum=" + referenceNum + ", payerCode=" + payerCode + ", PartnerTranReferenceNum=" + PartnerTranReferenceNum + ", beneficiaryStreetNo=" + beneficiaryStreetNo + ", beneficiaryHouse=" + beneficiaryHouse + ", beneficiaryDateOfBrith=" + beneficiaryDateOfBrith + ", beneficiaryEmail=" + beneficiaryEmail + ", beneficiaryTelephone=" + beneficiaryTelephone + ", beneficiaryState=" + beneficiaryState + ", beneficiaryPlaceOfBirth=" + beneficiaryPlaceOfBirth + ", beneficiaryNationality=" + beneficiaryNationality + ", beneficiaryPostalCode=" + beneficiaryPostalCode + ", beneficiaryOccupation=" + beneficiaryOccupation + ", beneficiaryRemarks=" + beneficiaryRemarks + '}';
    }

}
