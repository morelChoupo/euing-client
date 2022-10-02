/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufi.euing.client.integration.juba.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ufi.euing.client.integration.juba.model.CustomerDocument;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 *
 * @author User
 */

@Getter @Setter
public class DoGetTransactionsDetails {

    @JsonProperty("ReferenceNumber")
    private String ReferenceNumber;

    @JsonProperty("PayoutAmount")
    private double PayoutAmount;

    @JsonProperty("PayoutCurrency")
    private String PayoutCurrency;

    @JsonProperty("NominatedAgent")
    private String NominatedAgent;

    @JsonProperty("ModeOfPayment")
    private String ModeOfPayment;

    @JsonProperty("Purpose")
    private String Purpose;

    @JsonProperty("CustomerFirstName")
    private String CustomerFirstName;

    @JsonProperty("CustomerMiddleName")
    private String CustomerMiddleName;

    @JsonProperty("CustomerLastName")
    private String CustomerLastName;

    @JsonProperty("CustomerMobile")
    private String CustomerMobile;

    @JsonProperty("CustomerCountry")
    private String CustomerCountry;

    @JsonProperty("CustomerCity")
    private String CustomerCity;

    @JsonProperty("CustomerEmail")
    private String CustomerEmail;

    @JsonProperty("BeneficiaryFirstName")
    private String BeneficiaryFirstName;

    @JsonProperty("BeneficiaryMiddleName")
    private String BeneficiaryMiddleName;

    @JsonProperty("BeneficiaryLastName")
    private String BeneficiaryLastName;

    @JsonProperty("BeneficiaryMobile")
    private String BeneficiaryMobile;

    @JsonProperty("BeneficiaryEmail")
    private String BeneficiaryEmail;

    @JsonProperty("BeneficiaryCountry")
    private String BeneficiaryCountry;

    @JsonProperty("BeneficiaryCity")
    private String BeneficiaryCity;

    @JsonProperty("BeneficiaryAddress")
    private String BeneficiaryAddress;

    @JsonProperty("BeneficiaryOccupation")
    private String BeneficiaryOccupation;

    @JsonProperty("BeneficiaryDOB")
    private String BeneficiaryDOB;

    @JsonProperty("BeneficiaryPlaceOfBirth")
    private String BeneficiaryPlaceOfBirth;

    @JsonProperty("BeneficiaryNationality")
    private String BeneficiaryNationality;

    @JsonProperty("BeneficiaryMessage")
    private String BeneficiaryMessage;

    @JsonProperty("BeneficiaryDocuments")
    List<CustomerDocument> BeneficiaryDocuments;

    @JsonProperty("CustomerDocuments")
    List<CustomerDocument> CustomerDocuments;

    @JsonProperty("TransferTelegramDetail")
    private String TransferTelegramDetail;

    @JsonProperty("BankAccountDetail")
    private BankAccountDetail BankAccountDetail;

    @JsonProperty("MobilePaymentDetail")
    private String MobilePaymentDetail;

    @JsonProperty("PartnerCommissionDetail")
    private PartnerCommissionDetail PartnerCommissionDetail;

    @JsonProperty("CustomerPartnerID")
    private Long CustomerPartnerID;

    @JsonProperty("CustomerID")
    private Long CustomerID;

    @JsonProperty("BeneficiaryPartnerID")
    private Long BeneficiaryPartnerID;

    @JsonProperty("BeneficiaryId")
    private Long BeneficiaryId;

    @Override
    public String toString() {
        return "DoGetTransactionsDetails{" + "ReferenceNumber=" + ReferenceNumber + ", PayoutAmount=" + PayoutAmount + ", PayoutCurrency=" + PayoutCurrency + ", NominatedAgent=" + NominatedAgent + ", ModeOfPayment=" + ModeOfPayment + ", Purpose=" + Purpose + ", CustomerFirstName=" + CustomerFirstName + ", CustomerMiddleName=" + CustomerMiddleName + ", CustomerLastName=" + CustomerLastName + ", CustomerMobile=" + CustomerMobile + ", CustomerCountry=" + CustomerCountry + ", CustomerCity=" + CustomerCity + ", CustomerEmail=" + CustomerEmail + ", BeneficiaryFirstName=" + BeneficiaryFirstName + ", BeneficiaryMiddleName=" + BeneficiaryMiddleName + ", BeneficiaryLastName=" + BeneficiaryLastName + ", BeneficiaryMobile=" + BeneficiaryMobile + ", BeneficiaryEmail=" + BeneficiaryEmail + ", BeneficiaryCountry=" + BeneficiaryCountry + ", BeneficiaryCity=" + BeneficiaryCity + ", BeneficiaryAddress=" + BeneficiaryAddress + ", BeneficiaryOccupation=" + BeneficiaryOccupation + ", BeneficiaryDOB=" + BeneficiaryDOB + ", BeneficiaryPlaceOfBirth=" + BeneficiaryPlaceOfBirth + ", BeneficiaryNationality=" + BeneficiaryNationality + ", BeneficiaryMessage=" + BeneficiaryMessage + ", BeneficiaryDocuments=" + BeneficiaryDocuments + ", CustomerDocuments=" + CustomerDocuments + ", TransferTelegramDetail=" + TransferTelegramDetail + ", BankAccountDetail=" + BankAccountDetail + ", MobilePaymentDetail=" + MobilePaymentDetail + ", PartnerCommissionDetail=" + PartnerCommissionDetail + ", CustomerPartnerID=" + CustomerPartnerID + ", CustomerID=" + CustomerID + ", BeneficiaryPartnerID=" + BeneficiaryPartnerID + ", BeneficiaryId=" + BeneficiaryId + '}';
    }

}
