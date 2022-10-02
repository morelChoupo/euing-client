package com.ufi.euing.client.integration.thunes.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Setter  @Getter
public class CreditPartyIdentifier implements Serializable {

    private String msisdn;
    private String bank_account_number;
    private String swift_bic_code;

    @Override
    public String toString() {
        return "CreditPartyIdentifier{" + "msisdn=" + msisdn + ", bank_account_number=" + bank_account_number + ", swift_bic_code=" + swift_bic_code + '}';
    }

}
