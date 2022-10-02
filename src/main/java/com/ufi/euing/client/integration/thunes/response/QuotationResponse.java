package com.ufi.euing.client.integration.thunes.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter @Setter
public class QuotationResponse implements Serializable {

    private String creation_date;
    private Destination destination;
    private String expiration_date;
    private String external_id;
    private Fee fee;
    private int id;
    private String mode;
    private Payer payer;
    private SentAmount sent_amount;
    private Source source;
    private String transaction_type;
    private double wholesale_fx_rate;

    @Override
    public String toString() {
        return "QuotationResponse{" + "creation_date=" + creation_date + ", destination=" + destination + ", expiration_date=" + expiration_date + ", external_id=" + external_id + ", fee=" + fee + ", id=" + id + ", mode=" + mode + ", payer=" + payer + ", sent_amount=" + sent_amount + ", source=" + source + ", transaction_type=" + transaction_type + ", wholesale_fx_rate=" + wholesale_fx_rate + '}';
    }

}
