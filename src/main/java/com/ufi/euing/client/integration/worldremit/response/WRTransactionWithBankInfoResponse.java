/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufi.euing.client.integration.worldremit.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author User
 */

 @Getter @Setter
public class WRTransactionWithBankInfoResponse extends WRTransactionWithTransNumResponse {

    @JsonProperty("bank_account")
    private String bank_account;

    @JsonProperty("bank_name")
    private String bank_name;

    @JsonProperty("bank_code")
    private String bank_code;

    @JsonProperty("local_bank_code")
    private String local_bank_code;

    @JsonProperty("swift_code")
    private String swift_code;

    @JsonProperty("bank_branch_name")
    private String bank_branch_name;

    @JsonProperty("bank_address")
    private String bank_address;

    @JsonProperty("bank_city")
    private String bank_city;

    @JsonProperty("bank_country")
    private String bank_country;

    @JsonProperty("iban")
    private String iban;

    @Override
    public String toString() {
        return "TransactionWithBankInfoResponse{" + "bank_account=" + bank_account + ", bank_name=" + bank_name + ", bank_code=" + bank_code + ", local_bank_code=" + local_bank_code + ", swift_code=" + swift_code + ", bank_branch_name=" + bank_branch_name + ", bank_address=" + bank_address + ", bank_city=" + bank_city + ", bank_country=" + bank_country + ", iban=" + iban + '}';
    }

}
