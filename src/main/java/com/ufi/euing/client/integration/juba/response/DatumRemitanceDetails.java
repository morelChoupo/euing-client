package com.ufi.euing.client.integration.juba.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ufi.euing.client.integration.juba.model.CustomerDocument;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;


@Getter @Setter
public class DatumRemitanceDetails implements Serializable {

    @JsonProperty("ReferenceNumber")
    public String referenceNumber;
    @JsonProperty("PayoutAmount")
    public double payoutAmount;
    @JsonProperty("PayoutCurrency")
    public String payoutCurrency;
    @JsonProperty("NominatedAgent")
    public String nominatedAgent;
    @JsonProperty("ModeOfPayment")
    public int modeOfPayment;
    @JsonProperty("Purpose")
    public int purpose;
    @JsonProperty("CustomerFirstName")
    public String customerFirstName;
    @JsonProperty("CustomerMiddleName")
    public String customerMiddleName;
    @JsonProperty("CustomerLastName")
    public String customerLastName;
    @JsonProperty("CustomerMobile")
    public String customerMobile;
    @JsonProperty("CustomerCountry")
    public String customerCountry;
    @JsonProperty("CustomerCity")
    public String customerCity;
    @JsonProperty("CustomerEmail")
    public Object customerEmail;
    @JsonProperty("BeneficiaryFirstName")
    public String beneficiaryFirstName;
    @JsonProperty("BeneficiaryMiddleName")
    public Object beneficiaryMiddleName;
    @JsonProperty("BeneficiaryLastName")
    public String beneficiaryLastName;
    @JsonProperty("BeneficiaryMobile")
    public String beneficiaryMobile;
    @JsonProperty("BeneficiaryEmail")
    public Object beneficiaryEmail;
    @JsonProperty("BeneficiaryCountry")
    public String beneficiaryCountry;
    @JsonProperty("BeneficiaryCity")
    public String beneficiaryCity;
    @JsonProperty("BeneficiaryAddress")
    public String beneficiaryAddress;
    @JsonProperty("BeneficiaryOccupation")
    public Object beneficiaryOccupation;
    @JsonProperty("BeneficiaryDOB")
    public Object beneficiaryDOB;
    @JsonProperty("BeneficiaryPlaceOfBirth")
    public Object beneficiaryPlaceOfBirth;
    @JsonProperty("BeneficiaryNationality")
    public Object beneficiaryNationality;
    @JsonProperty("BeneficiaryMessage")
    public Object beneficiaryMessage;
    @JsonProperty("BeneficiaryDocuments")
    public List<Object> beneficiaryDocuments;
    @JsonProperty("CustomerDocuments")
    public List<CustomerDocument> customerDocuments;
    @JsonProperty("TransferTelegramDetail")
    public Object transferTelegramDetail;
    @JsonProperty("BankAccountDetail")
    public Object bankAccountDetail;
    @JsonProperty("MobilePaymentDetail")
    public Object mobilePaymentDetail;


    @Override
    public String toString() {
        return "DatumRemitanceDetails{" + "referenceNumber=" + referenceNumber + ", payoutAmount=" + payoutAmount + ", payoutCurrency=" + payoutCurrency + ", nominatedAgent=" + nominatedAgent + ", modeOfPayment=" + modeOfPayment + ", purpose=" + purpose + ", customerFirstName=" + customerFirstName + ", customerMiddleName=" + customerMiddleName + ", customerLastName=" + customerLastName + ", customerMobile=" + customerMobile + ", customerCountry=" + customerCountry + ", customerCity=" + customerCity + ", customerEmail=" + customerEmail + ", beneficiaryFirstName=" + beneficiaryFirstName + ", beneficiaryMiddleName=" + beneficiaryMiddleName + ", beneficiaryLastName=" + beneficiaryLastName + ", beneficiaryMobile=" + beneficiaryMobile + ", beneficiaryEmail=" + beneficiaryEmail + ", beneficiaryCountry=" + beneficiaryCountry + ", beneficiaryCity=" + beneficiaryCity + ", beneficiaryAddress=" + beneficiaryAddress + ", beneficiaryOccupation=" + beneficiaryOccupation + ", beneficiaryDOB=" + beneficiaryDOB + ", beneficiaryPlaceOfBirth=" + beneficiaryPlaceOfBirth + ", beneficiaryNationality=" + beneficiaryNationality + ", beneficiaryMessage=" + beneficiaryMessage + ", beneficiaryDocuments=" + beneficiaryDocuments + ", customerDocuments=" + customerDocuments + ", transferTelegramDetail=" + transferTelegramDetail + ", bankAccountDetail=" + bankAccountDetail + ", mobilePaymentDetail=" + mobilePaymentDetail + '}';
    }

}
