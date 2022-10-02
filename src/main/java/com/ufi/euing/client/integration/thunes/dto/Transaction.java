package com.ufi.euing.client.integration.thunes.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;



@Getter @Setter
public class Transaction implements Serializable {

    private CreditPartyIdentifier credit_party_identifier;
    private Sender sender;
    private Beneficiary beneficiary;
    private String external_id;
    private int retail_fee;
    private String retail_fee_currency;
    private String purpose_of_remittance;
    private String document_reference_number;
    private String callback_url;

    @Override
    public String toString() {
        return "Transaction{" + "credit_party_identifier=" + credit_party_identifier + ", sender=" + sender + ", beneficiary=" + beneficiary + ", external_id=" + external_id + ", retail_fee=" + retail_fee + ", retail_fee_currency=" + retail_fee_currency + ", purpose_of_remittance=" + purpose_of_remittance + ", document_reference_number=" + document_reference_number + ", callback_url=" + callback_url + '}';
    }

}
