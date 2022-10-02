/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufi.euing.client.integration.worldremit.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 *
 * @author User
 */

@Getter @Setter
public class WRTransactionResponse implements Serializable {

    @JsonProperty("wr_transaction_id")
    private String wr_transaction_id;

    @JsonProperty("wr_transaction_number")
    private String wr_transaction_number;

    @JsonProperty("status")
    private String status;

    @JsonProperty("transaction_date")
    private String transaction_date;

    @JsonProperty("originating_country")
    private String originating_country;

    @JsonProperty("destination_country")
    private String destination_country;

    @JsonProperty("sending_amount")
    private String sending_amount;

    @JsonProperty("originating_currency")
    private String originating_currency;

    @JsonProperty("destination_currency")
    private String destination_currency;

    @JsonProperty("customer_fee")
    private String customer_fee;

    @JsonProperty("payout_amount")
    private String payout_amount;

    @JsonProperty("exchange_rate")
    private String exchange_rate;

    @JsonProperty("product")
    private String product;

    @JsonProperty("sender_id")
    private String sender_id;

    @JsonProperty("sender_first_name")
    private String sender_first_name;

    @JsonProperty("sender_last_name")
    private String sender_last_name;

    @JsonProperty("sender_country")
    private String sender_country;

    @JsonProperty("sender_city")
    private String sender_city;

    @JsonProperty("receiver_first_name")
    private String receiver_first_name;

    @JsonProperty("receiver_middle_name")
    private String receiver_middle_name;

    @JsonProperty("receiver_last_name")
    private String receiver_last_name;

    @JsonProperty("receiver_address_1")
    private String receiver_address_1;

    @JsonProperty("receiver_address_2")
    private String receiver_address_2;

    @JsonProperty("receiver_city")
    private String receiver_city;

    @JsonProperty("receiver_state")
    private String receiver_state;

    @JsonProperty("receiver_postcode")
    private String receiver_postcode;

    @JsonProperty("receiver_country")
    private String receiver_country;

    @JsonProperty("receiver_mobile_number")
    private String receiver_mobile_number;

    @JsonProperty("receiver_landline_number")
    private String receiver_landline_number;

    @JsonProperty("receiver_email_id")
    private String receiver_email_id;

    @JsonProperty("receiver_momo_account")
    private String receiver_momo_account;

    @JsonProperty("transaction_pay_date")
    private String transaction_pay_date;
    
    private int responseCode;
    private String responseDesc;


    @Override
    public String toString() {
        return "WRTransactionResponse{" + "wr_transaction_id=" + wr_transaction_id + ", wr_transaction_number=" + wr_transaction_number + ", status=" + status + ", transaction_date=" + transaction_date + ", originating_country=" + originating_country + ", destination_country=" + destination_country + ", sending_amount=" + sending_amount + ", originating_currency=" + originating_currency + ", destination_currency=" + destination_currency + ", customer_fee=" + customer_fee + ", payout_amount=" + payout_amount + ", exchange_rate=" + exchange_rate + ", product=" + product + ", sender_id=" + sender_id + ", sender_first_name=" + sender_first_name + ", sender_last_name=" + sender_last_name + ", sender_country=" + sender_country + ", sender_city=" + sender_city + ", receiver_first_name=" + receiver_first_name + ", receiver_middle_name=" + receiver_middle_name + ", receiver_last_name=" + receiver_last_name + ", receiver_address_1=" + receiver_address_1 + ", receiver_address_2=" + receiver_address_2 + ", receiver_city=" + receiver_city + ", receiver_state=" + receiver_state + ", receiver_postcode=" + receiver_postcode + ", receiver_country=" + receiver_country + ", receiver_mobile_number=" + receiver_mobile_number + ", receiver_landline_number=" + receiver_landline_number + ", receiver_email_id=" + receiver_email_id + ", receiver_momo_account=" + receiver_momo_account + ", transaction_pay_date=" + transaction_pay_date + ", responseCode=" + responseCode + ", responseDesc=" + responseDesc + '}';
    }

}
