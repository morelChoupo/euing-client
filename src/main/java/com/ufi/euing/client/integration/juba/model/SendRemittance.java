package com.ufi.euing.client.integration.juba.model;

import com.fasterxml.jackson.annotation.JsonProperty;


import java.io.Serializable;
import java.util.List;

public class SendRemittance implements Serializable {

    @JsonProperty("QuotationID")
    private String quotationId;

    @JsonProperty("SenderCode")
    public String senderCode;

    @JsonProperty("NominatedCode")
    public String nominatedCode;

    @JsonProperty("CustomerReferenceNo")
    public String customerReferenceNo;

    @JsonProperty("BeneficiaryReferenceNo")
    public String beneficiaryReferenceNo;

    @JsonProperty("Purpose")
    public Integer purpose;

    @JsonProperty("PurposeDescription")
    public String purposeDescription;

    @JsonProperty("SourceOfIncome")
    public Integer sourceOfIncome;

    @JsonProperty("SourceOfIncomeDescription")
    public String sourceOfIncomeDescription;

    @JsonProperty("SenderModeOfPayment")
    public Integer senderModeOfPayment;

    @JsonProperty("ReceiverModeOfPayment")
    public Integer receiverModeOfPayment;

    @JsonProperty("Remarks")
    public String remarks;

    @JsonProperty("CustomerDocuments")
    public List<CustomerDocument> customerDocuments;

    @JsonProperty("BeneficiaryDocuments")
    public List<CustomerDocument> beneficiaryDocuments;

    @JsonProperty("TotalCommission")
    public double totalCommission;

    @JsonProperty("PayoutCurrency")
    public String payoutCurrency;

    @JsonProperty("PayoutAmount")
    public double payoutAmount;

    @JsonProperty("SenderAccountNo")
    public String senderAccountNo;

    @JsonProperty("SenderAccountTitle")
    public String senderAccountTitle;

    @JsonProperty("SenderAccountCurrency")
    public String senderAccountCurrency;

    @JsonProperty("BeneficiaryAccountNo")
    public String beneficiaryAccountNo;

    @JsonProperty("BeneficiaryAccountTitle")
    public String beneficiaryAccountTitle;

    @JsonProperty("BeneficiaryAccountCurrency")
    public String beneficiaryAccountCurrency;

    @JsonProperty("BeneficiaryBankName")
    public String beneficiaryBankName;

    @JsonProperty("BeneficiaryBankAddress")
    public String beneficiaryBankAddress;

    @JsonProperty("BeneficiarySwiftCode")
    public String beneficiarySwiftCode;

    @JsonProperty("BeneficiaryBranchName")
    public String beneficiaryBranchName;

    @JsonProperty("BeneficaryBankCountry")
    public String beneficaryBankCountry;

    @JsonProperty("SendingCity")
    public String sendingCity;

    @JsonProperty("PartnerReferenceNum")
    public String partnerReferenceNum;

    @JsonProperty("TotalCommissionInSettlmentCurrency")
    public double totalCommissionInSettlmentCurrency;

    @JsonProperty("JubaCommisionInSettlement")
    public double jubaCommisionInSettlement;

    @JsonProperty("SettlementCurrencyCode")
    public String settlementCurrencyCode;

    public String getQuotationId() {
        return quotationId;
    }

    public void setQuotationId(String quotationId) {
        this.quotationId = quotationId;
    }

    public String getSenderCode() {
        return senderCode;
    }

    public void setSenderCode(String senderCode) {
        this.senderCode = senderCode;
    }

    public String getNominatedCode() {
        return nominatedCode;
    }

    public void setNominatedCode(String nominatedCode) {
        this.nominatedCode = nominatedCode;
    }

    public String getCustomerReferenceNo() {
        return customerReferenceNo;
    }

    public void setCustomerReferenceNo(String customerReferenceNo) {
        this.customerReferenceNo = customerReferenceNo;
    }

    public String getBeneficiaryReferenceNo() {
        return beneficiaryReferenceNo;
    }

    public void setBeneficiaryReferenceNo(String beneficiaryReferenceNo) {
        this.beneficiaryReferenceNo = beneficiaryReferenceNo;
    }

    public Integer getPurpose() {
        return purpose;
    }

    public void setPurpose(Integer purpose) {
        this.purpose = purpose;
    }

    public String getPurposeDescription() {
        return purposeDescription;
    }

    public void setPurposeDescription(String purposeDescription) {
        this.purposeDescription = purposeDescription;
    }

    public Integer getSourceOfIncome() {
        return sourceOfIncome;
    }

    public void setSourceOfIncome(Integer sourceOfIncome) {
        this.sourceOfIncome = sourceOfIncome;
    }

    public String getSourceOfIncomeDescription() {
        return sourceOfIncomeDescription;
    }

    public void setSourceOfIncomeDescription(String sourceOfIncomeDescription) {
        this.sourceOfIncomeDescription = sourceOfIncomeDescription;
    }

    public Integer getSenderModeOfPayment() {
        return senderModeOfPayment;
    }

    public void setSenderModeOfPayment(Integer senderModeOfPayment) {
        this.senderModeOfPayment = senderModeOfPayment;
    }

    public Integer getReceiverModeOfPayment() {
        return receiverModeOfPayment;
    }

    public void setReceiverModeOfPayment(Integer receiverModeOfPayment) {
        this.receiverModeOfPayment = receiverModeOfPayment;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public List<CustomerDocument> getCustomerDocuments() {
        return customerDocuments;
    }

    public void setCustomerDocuments(List<CustomerDocument> customerDocuments) {
        this.customerDocuments = customerDocuments;
    }

    public List<CustomerDocument> getBeneficiaryDocuments() {
        return beneficiaryDocuments;
    }

    public void setBeneficiaryDocuments(List<CustomerDocument> beneficiaryDocuments) {
        this.beneficiaryDocuments = beneficiaryDocuments;
    }

    public double getTotalCommission() {
        return totalCommission;
    }

    public void setTotalCommission(double totalCommission) {
        this.totalCommission = totalCommission;
    }

    public String getPayoutCurrency() {
        return payoutCurrency;
    }

    public void setPayoutCurrency(String payoutCurrency) {
        this.payoutCurrency = payoutCurrency;
    }

    public double getPayoutAmount() {
        return payoutAmount;
    }

    public void setPayoutAmount(double payoutAmount) {
        this.payoutAmount = payoutAmount;
    }

    public String getSenderAccountNo() {
        return senderAccountNo;
    }

    public void setSenderAccountNo(String senderAccountNo) {
        this.senderAccountNo = senderAccountNo;
    }

    public String getSenderAccountTitle() {
        return senderAccountTitle;
    }

    public void setSenderAccountTitle(String senderAccountTitle) {
        this.senderAccountTitle = senderAccountTitle;
    }

    public String getSenderAccountCurrency() {
        return senderAccountCurrency;
    }

    public void setSenderAccountCurrency(String senderAccountCurrency) {
        this.senderAccountCurrency = senderAccountCurrency;
    }

    public String getBeneficiaryAccountNo() {
        return beneficiaryAccountNo;
    }

    public void setBeneficiaryAccountNo(String beneficiaryAccountNo) {
        this.beneficiaryAccountNo = beneficiaryAccountNo;
    }

    public String getBeneficiaryAccountTitle() {
        return beneficiaryAccountTitle;
    }

    public void setBeneficiaryAccountTitle(String beneficiaryAccountTitle) {
        this.beneficiaryAccountTitle = beneficiaryAccountTitle;
    }

    public String getBeneficiaryAccountCurrency() {
        return beneficiaryAccountCurrency;
    }

    public void setBeneficiaryAccountCurrency(String beneficiaryAccountCurrency) {
        this.beneficiaryAccountCurrency = beneficiaryAccountCurrency;
    }

    public String getBeneficiaryBankName() {
        return beneficiaryBankName;
    }

    public void setBeneficiaryBankName(String beneficiaryBankName) {
        this.beneficiaryBankName = beneficiaryBankName;
    }

    public String getBeneficiaryBankAddress() {
        return beneficiaryBankAddress;
    }

    public void setBeneficiaryBankAddress(String beneficiaryBankAddress) {
        this.beneficiaryBankAddress = beneficiaryBankAddress;
    }

    public String getBeneficiarySwiftCode() {
        return beneficiarySwiftCode;
    }

    public void setBeneficiarySwiftCode(String beneficiarySwiftCode) {
        this.beneficiarySwiftCode = beneficiarySwiftCode;
    }

    public String getBeneficiaryBranchName() {
        return beneficiaryBranchName;
    }

    public void setBeneficiaryBranchName(String beneficiaryBranchName) {
        this.beneficiaryBranchName = beneficiaryBranchName;
    }

    public String getBeneficaryBankCountry() {
        return beneficaryBankCountry;
    }

    public void setBeneficaryBankCountry(String beneficaryBankCountry) {
        this.beneficaryBankCountry = beneficaryBankCountry;
    }

    public String getSendingCity() {
        return sendingCity;
    }

    public void setSendingCity(String sendingCity) {
        this.sendingCity = sendingCity;
    }

    public String getPartnerReferenceNum() {
        return partnerReferenceNum;
    }

    public void setPartnerReferenceNum(String partnerReferenceNum) {
        this.partnerReferenceNum = partnerReferenceNum;
    }

    public double getTotalCommissionInSettlmentCurrency() {
        return totalCommissionInSettlmentCurrency;
    }

    public void setTotalCommissionInSettlmentCurrency(double totalCommissionInSettlmentCurrency) {
        this.totalCommissionInSettlmentCurrency = totalCommissionInSettlmentCurrency;
    }

    public double getJubaCommisionInSettlement() {
        return jubaCommisionInSettlement;
    }

    public void setJubaCommisionInSettlement(double jubaCommisionInSettlement) {
        this.jubaCommisionInSettlement = jubaCommisionInSettlement;
    }

    public String getSettlementCurrencyCode() {
        return settlementCurrencyCode;
    }

    public void setSettlementCurrencyCode(String settlementCurrencyCode) {
        this.settlementCurrencyCode = settlementCurrencyCode;
    }

    @Override
    public String toString() {
        return "SendRemittance{" + "quotationId=" + quotationId + ", senderCode=" + senderCode + ", nominatedCode=" + nominatedCode + ", customerReferenceNo=" + customerReferenceNo + ", beneficiaryReferenceNo=" + beneficiaryReferenceNo + ", purpose=" + purpose + ", purposeDescription=" + purposeDescription + ", sourceOfIncome=" + sourceOfIncome + ", sourceOfIncomeDescription=" + sourceOfIncomeDescription + ", senderModeOfPayment=" + senderModeOfPayment + ", receiverModeOfPayment=" + receiverModeOfPayment + ", remarks=" + remarks + ", customerDocuments=" + customerDocuments + ", beneficiaryDocuments=" + beneficiaryDocuments + ", totalCommission=" + totalCommission + ", payoutCurrency=" + payoutCurrency + ", payoutAmount=" + payoutAmount + ", senderAccountNo=" + senderAccountNo + ", senderAccountTitle=" + senderAccountTitle + ", senderAccountCurrency=" + senderAccountCurrency + ", beneficiaryAccountNo=" + beneficiaryAccountNo + ", beneficiaryAccountTitle=" + beneficiaryAccountTitle + ", beneficiaryAccountCurrency=" + beneficiaryAccountCurrency + ", beneficiaryBankName=" + beneficiaryBankName + ", beneficiaryBankAddress=" + beneficiaryBankAddress + ", beneficiarySwiftCode=" + beneficiarySwiftCode + ", beneficiaryBranchName=" + beneficiaryBranchName + ", beneficaryBankCountry=" + beneficaryBankCountry + ", sendingCity=" + sendingCity + ", partnerReferenceNum=" + partnerReferenceNum + ", totalCommissionInSettlmentCurrency=" + totalCommissionInSettlmentCurrency + ", jubaCommisionInSettlement=" + jubaCommisionInSettlement + ", settlementCurrencyCode=" + settlementCurrencyCode + '}';
    }

}
