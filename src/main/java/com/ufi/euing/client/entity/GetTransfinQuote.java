package com.ufi.euing.client.entity;

import java.util.Date;

public class GetTransfinQuote  implements java.io.Serializable {

    private int responseCode;

    private String message;

    private Date processDateTime;

    private String originatingCountry;

    private String originatingCurrency;

    private String destinationCountry;

    private String destinationCurrency;

    private double localAmount;

    private double exchangeRate;

    private double customerCharge;

    private double cashTellerReceiver;

    private double taxCharged;

    private double payoutAmount;


    public GetTransfinQuote() {
    }


    public GetTransfinQuote(int responseCode, String message,
                            Date processDateTime, String originatingCountry,
                            String originatingCurrency, String destinationCountry,
                            String destinationCurrency, double localAmount,
                            double exchangeRate, double customerCharge,
                            double cashTellerReceiver, double taxCharged, double payoutAmount) {
        super();
        this.responseCode = responseCode;
        this.message = message;
        this.processDateTime = processDateTime;
        this.originatingCountry = originatingCountry;
        this.originatingCurrency = originatingCurrency;
        this.destinationCountry = destinationCountry;
        this.destinationCurrency = destinationCurrency;
        this.localAmount = localAmount;
        this.exchangeRate = exchangeRate;
        this.customerCharge = customerCharge;
        this.cashTellerReceiver = cashTellerReceiver;
        this.taxCharged = taxCharged;
        this.payoutAmount = payoutAmount;
    }


    /**
     * Gets the responseCode value for this GetTransfinQuote.
     *
     * @return responseCode
     */
    public int getResponseCode() {
        return responseCode;
    }


    /**
     * Sets the responseCode value for this GetTransfinQuote.
     *
     * @param responseCode
     */
    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }


    /**
     * Gets the message value for this GetTransfinQuote.
     *
     * @return message
     */
    public java.lang.String getMessage() {
        return message;
    }


    /**
     * Sets the message value for this GetTransfinQuote.
     *
     * @param message
     */
    public void setMessage(java.lang.String message) {
        this.message = message;
    }


    /**
     * Gets the processDateTime value for this GetTransfinQuote.
     *
     * @return processDateTime
     */
    public java.util.Date getProcessDateTime() {
        return processDateTime;
    }


    /**
     * Sets the processDateTime value for this GetTransfinQuote.
     *
     * @param processDateTime
     */
    public void setProcessDateTime(java.util.Date processDateTime) {
        this.processDateTime = processDateTime;
    }


    /**
     * Gets the originatingCountry value for this GetTransfinQuote.
     *
     * @return originatingCountry
     */
    public java.lang.String getOriginatingCountry() {
        return originatingCountry;
    }


    /**
     * Sets the originatingCountry value for this GetTransfinQuote.
     *
     * @param originatingCountry
     */
    public void setOriginatingCountry(java.lang.String originatingCountry) {
        this.originatingCountry = originatingCountry;
    }


    /**
     * Gets the originatingCurrency value for this GetTransfinQuote.
     *
     * @return originatingCurrency
     */
    public java.lang.String getOriginatingCurrency() {
        return originatingCurrency;
    }


    /**
     * Sets the originatingCurrency value for this GetTransfinQuote.
     *
     * @param originatingCurrency
     */
    public void setOriginatingCurrency(java.lang.String originatingCurrency) {
        this.originatingCurrency = originatingCurrency;
    }


    /**
     * Gets the destinationCountry value for this GetTransfinQuote.
     *
     * @return destinationCountry
     */
    public java.lang.String getDestinationCountry() {
        return destinationCountry;
    }


    /**
     * Sets the destinationCountry value for this GetTransfinQuote.
     *
     * @param destinationCountry
     */
    public void setDestinationCountry(java.lang.String destinationCountry) {
        this.destinationCountry = destinationCountry;
    }


    /**
     * Gets the destinationCurrency value for this GetTransfinQuote.
     *
     * @return destinationCurrency
     */
    public java.lang.String getDestinationCurrency() {
        return destinationCurrency;
    }


    /**
     * Sets the destinationCurrency value for this GetTransfinQuote.
     *
     * @param destinationCurrency
     */
    public void setDestinationCurrency(java.lang.String destinationCurrency) {
        this.destinationCurrency = destinationCurrency;
    }




    /**
     * Gets the localAmount value for this GetTransfinQuote.
     *
     * @return localAmount
     */
    public double getLocalAmount() {
        return localAmount;
    }


    /**
     * Sets the localAmount value for this GetTransfinQuote.
     *
     * @param localAmount
     */
    public void setLocalAmount(double localAmount) {
        this.localAmount = localAmount;
    }


    /**
     * Gets the exchangeRate value for this GetTransfinQuote.
     *
     * @return exchangeRate
     */
    public double getExchangeRate() {
        return exchangeRate;
    }


    /**
     * Sets the exchangeRate value for this GetTransfinQuote.
     *
     * @param exchangeRate
     */
    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }


    /**
     * Gets the customerCharge value for this GetTransfinQuote.
     *
     * @return customerCharge
     */
    public double getCustomerCharge() {
        return customerCharge;
    }


    /**
     * Sets the customerCharge value for this GetTransfinQuote.
     *
     * @param customerCharge
     */
    public void setCustomerCharge(double customerCharge) {
        this.customerCharge = customerCharge;
    }


    public double getCashTellerReceiver() {
        return cashTellerReceiver;
    }

    public void setCashTellerReceiver(double cashTellerReceiver) {
        this.cashTellerReceiver = cashTellerReceiver;
    }

    /**
     * Gets the taxCharged value for this GetTransfinQuote.
     *
     * @return taxCharged
     */
    public double getTaxCharged() {
        return taxCharged;
    }


    /**
     * Sets the taxCharged value for this GetTransfinQuote.
     *
     * @param taxCharged
     */
    public void setTaxCharged(double taxCharged) {
        this.taxCharged = taxCharged;
    }


    /**
     * Gets the payoutAmount value for this GetTransfinQuote.
     *
     * @return payoutAmount
     */
    public double getPayoutAmount() {
        return payoutAmount;
    }


    /**
     * Sets the payoutAmount value for this GetTransfinQuote.
     *
     * @param payoutAmount
     */
    public void setPayoutAmount(double payoutAmount) {
        this.payoutAmount = payoutAmount;
    }

    @Override
    public String toString() {
        return "GetTransfinQuote{" + "responseCode=" + responseCode + ", message=" + message + ", processDateTime=" + processDateTime + ", originatingCountry=" + originatingCountry + ", originatingCurrency=" + originatingCurrency + ", destinationCountry=" + destinationCountry + ", destinationCurrency=" + destinationCurrency + ", localAmount=" + localAmount + ", exchangeRate=" + exchangeRate + ", customerCharge=" + customerCharge + ", cashTellerReceiver=" + cashTellerReceiver + ", taxCharged=" + taxCharged + ", payoutAmount=" + payoutAmount + '}';
    }


}
