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
public class WRTransactionWithTransNumResponse extends WRTransactionResponse {

    @JsonProperty("settlement_currency")
    private String settlement_currency;

    @JsonProperty("settlement_amount")
    private String settlement_amount;

    @JsonProperty("correspondent_commission")
    private String correspondent_commission;

    @JsonProperty("settlement_exchange_rate")
    private String settlement_exchange_rate;

    @JsonProperty("transaction_reference")
    private String transaction_reference;

    @JsonProperty("send_reason_code")
    private String send_reason_code;

    @JsonProperty("send_reason_desc")
    private String send_reason_desc;

    @Override
    public String toString() {
        return "TransactionWithTransNumResponse{" + "settlement_currency=" + settlement_currency + ", settlement_amount=" + settlement_amount + ", correspondent_commission=" + correspondent_commission + ", settlement_exchange_rate=" + settlement_exchange_rate + ", transaction_reference=" + transaction_reference + ", send_reason_code=" + send_reason_code + ", send_reason_desc=" + send_reason_desc + '}';
    }

}
