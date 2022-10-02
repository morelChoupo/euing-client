package com.ufi.euing.client.integration.juba.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter @Setter
public class BankAccountDetail implements Serializable {

    @JsonProperty("BankName")
    public String bankName;
    @JsonProperty("BankAccountNo")
    public String bankAccountNo;
    @JsonProperty("BankAccountTitle")
    public String bankAccountTitle;
    @JsonProperty("BankSwiftCode")
    public String bankSwiftCode;
    @JsonProperty("ReferenceNo")
    public String referenceNo;
    @JsonProperty("BankAddress")
    public String bankAddress;


    @Override
    public String toString() {
        return "BankAccountDetail{" + "bankName=" + bankName + ", bankAccountNo=" + bankAccountNo + ", bankAccountTitle=" + bankAccountTitle + ", bankSwiftCode=" + bankSwiftCode + ", referenceNo=" + referenceNo + ", bankAddress=" + bankAddress + '}';
    }

}
