package com.ufi.euing.client.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;


@Data
public class GetTransfinQuoteRequest implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    private Long usersessionId;

    @JsonIgnore
    private Long guichetId;

    private String originatingCountry;

    private Long originatingTown; // zone ou region

    private String originatingCurrency;

    private String destinationCountry;

    private Long destinationTown;   // zone ou region

    private String destinationCurrency;

    private double localAmount;

    private double exchangeRate;

    private double customerCharge;   // commission

    private double taxCharged;     // taxe

    private double payoutAmount; // montant � percevoir



    public GetTransfinQuoteRequest(Long usersessionId, Long guichetId,
                                   String originatingCountry, Long originatingTown,
                                   String originatingCurrency, String destinationCountry,
                                   Long destinationTown, String destinationCurrency,
                                   double localAmount, double exchangeRate, double customerCharge,
                                   double taxCharged, double payoutAmount) {
        super();
        this.usersessionId = usersessionId;
        this.guichetId = guichetId;
        this.originatingCountry = originatingCountry;
        this.originatingTown = originatingTown;
        this.originatingCurrency = originatingCurrency;
        this.destinationCountry = destinationCountry;
        this.destinationTown = destinationTown;
        this.destinationCurrency = destinationCurrency;
        this.localAmount = localAmount;
        this.exchangeRate = exchangeRate;
        this.customerCharge = customerCharge;
        this.taxCharged = taxCharged;
        this.payoutAmount = payoutAmount;
    }

    public GetTransfinQuoteRequest() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public String toString() {
        return "GetTransfinQuoteRequest [usersessionId=" + usersessionId
                + ", guichetId=" + guichetId + ", originatingCountry="
                + originatingCountry + ", originatingTown=" + originatingTown
                + ", originatingCurrency=" + originatingCurrency
                + ", destinationCountry=" + destinationCountry
                + ", destinationTown=" + destinationTown
                + ", destinationCurrency=" + destinationCurrency
                + ", localAmount=" + localAmount + ", exchangeRate="
                + exchangeRate + ", customerCharge=" + customerCharge
                + ", taxCharged=" + taxCharged + ", payoutAmount="
                + payoutAmount + "]";
    }

}

