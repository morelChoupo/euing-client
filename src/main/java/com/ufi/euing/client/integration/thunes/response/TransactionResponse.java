package com.ufi.euing.client.integration.thunes.response;

import com.ufi.euing.client.integration.thunes.dto.CreditPartyIdentifier;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter @Setter
public class TransactionResponse implements Serializable {

    private String additional_information_1;
    private String additional_information_2;
    private String additional_information_3;
    private Beneficiary beneficiary;
    private String callback_url;
    private String creation_date;
    private CreditPartyIdentifier credit_party_identifier;
    private Destination destination;
    private String document_reference_number;
    private String expiration_date;
    private String external_code;
    private String external_id;
    private Fee fee;
    private int id;
    private Payer payer;
    private String payer_transaction_code;
    private String payer_transaction_reference;
    private String purpose_of_remittance;
    private int retail_fee;
    private String retail_fee_currency;
    private String retail_rate;
    private Sender sender;
    private SentAmount sent_amount;
    private Source source;
    private String status;
    private String status_class;
    private String status_class_message;
    private String status_message;
    private String transaction_type;
    private double wholesale_fx_rate;

    @Override
    public String toString() {
        return "TransactionResponse{" + "additional_information_1=" + additional_information_1 + ", additional_information_2=" + additional_information_2 + ", additional_information_3=" + additional_information_3 + ", beneficiary=" + beneficiary + ", callback_url=" + callback_url + ", creation_date=" + creation_date + ", credit_party_identifier=" + credit_party_identifier + ", destination=" + destination + ", document_reference_number=" + document_reference_number + ", expiration_date=" + expiration_date + ", external_code=" + external_code + ", external_id=" + external_id + ", fee=" + fee + ", id=" + id + ", payer=" + payer + ", payer_transaction_code=" + payer_transaction_code + ", payer_transaction_reference=" + payer_transaction_reference + ", purpose_of_remittance=" + purpose_of_remittance + ", retail_fee=" + retail_fee + ", retail_fee_currency=" + retail_fee_currency + ", retail_rate=" + retail_rate + ", sender=" + sender + ", sent_amount=" + sent_amount + ", source=" + source + ", status=" + status + ", status_class=" + status_class + ", status_class_message=" + status_class_message + ", status_message=" + status_message + ", transaction_type=" + transaction_type + ", wholesale_fx_rate=" + wholesale_fx_rate + '}';
    }

}
