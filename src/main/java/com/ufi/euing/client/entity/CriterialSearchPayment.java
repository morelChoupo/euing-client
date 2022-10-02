package com.ufi.euing.client.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

public class CriterialSearchPayment implements Serializable {

    private String transactionNumber;

    private int typeTransaction; // 1:transaction client web; 2:transaction client mobile

    @JsonIgnore
    private Long userSession;

    private String partnerCode;

    private String codeTransaction;

    private String statusCode;

    private String statusDescription;

    private String transactionDate;

    private String originatingCountry;

    private String originatingCurrency;

    private String originatingTown; //ZoneEnvoi

    private String destinationCountry;

    private String destinationTown;  //ZoneDestinataire

    private String destinationCurrency;

    private String reference;

    private double localAmount;

    public String getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public int getTypeTransaction() {
        return typeTransaction;
    }

    public void setTypeTransaction(int typeTransaction) {
        this.typeTransaction = typeTransaction;
    }

    public Long getUserSession() {
        return userSession;
    }

    public void setUserSession(Long userSession) {
        this.userSession = userSession;
    }

    public java.lang.String getPartnerCode() {
        return partnerCode;
    }

    public void setPartnerCode(String partnerCode) {
        this.partnerCode = partnerCode;
    }

//    public void setPartnerTransactionNo(java.lang.String partnerTransactionNo) {
//        this.partnerCode = partnerTransactionNo;
//    }

    public String getCodeTransaction() {
        return codeTransaction;
    }

    public void setCodeTransaction(String codeTransaction) {
        this.codeTransaction = codeTransaction;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getOriginatingCountry() {
        return originatingCountry;
    }

    public void setOriginatingCountry(String originatingCountry) {
        this.originatingCountry = originatingCountry;
    }

    public String getOriginatingCurrency() {
        return originatingCurrency;
    }

    public void setOriginatingCurrency(String originatingCurrency) {
        this.originatingCurrency = originatingCurrency;
    }

    public String getOriginatingTown() {
        return originatingTown;
    }

    public void setOriginatingTown(String originatingTown) {
        this.originatingTown = originatingTown;
    }

    public String getDestinationCountry() {
        return destinationCountry;
    }

    public void setDestinationCountry(String destinationCountry) {
        this.destinationCountry = destinationCountry;
    }

    public String getDestinationTown() {
        return destinationTown;
    }

    public void setDestinationTown(String destinationTown) {
        this.destinationTown = destinationTown;
    }

    public String getDestinationCurrency() {
        return destinationCurrency;
    }

    public void setDestinationCurrency(String destinationCurrency) {
        this.destinationCurrency = destinationCurrency;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public double getLocalAmount() {
        return localAmount;
    }

    public void setLocalAmount(double localAmount) {
        this.localAmount = localAmount;
    }

    public CriterialSearchPayment(String transactionNumber,
                                  int typeTransaction, Long userSession, String partnerCode,
                                  String codeTransaction, String statusCode,
                                  String statusDescription, String transactionDate,
                                  String originatingCountry, String originatingCurrency,
                                  String originatingTown, String destinationCountry,
                                  String destinationTown, String destinationCurrency,
                                  String reference, double localAmount) {
        super();
        this.transactionNumber = transactionNumber;
        this.typeTransaction = typeTransaction;
        this.userSession = userSession;
        this.partnerCode = partnerCode;
        this.codeTransaction = codeTransaction;
        this.statusCode = statusCode;
        this.statusDescription = statusDescription;
        this.transactionDate = transactionDate;
        this.originatingCountry = originatingCountry;
        this.originatingCurrency = originatingCurrency;
        this.originatingTown = originatingTown;
        this.destinationCountry = destinationCountry;
        this.destinationTown = destinationTown;
        this.destinationCurrency = destinationCurrency;
        this.reference = reference;
        this.localAmount = localAmount;
    }

    public CriterialSearchPayment() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public String toString() {
        return "CriterialSearchPayment [transactionNumber=" + transactionNumber
                + ", typeTransaction=" + typeTransaction + ", userSession="
                + userSession + ", partnerCode="
                + partnerCode + ", codeTransaction=" + codeTransaction
                + ", statusCode=" + statusCode + ", statusDescription="
                + statusDescription + ", transactionDate=" + transactionDate
                + ", originatingCountry=" + originatingCountry
                + ", originatingCurrency=" + originatingCurrency
                + ", originatingTown=" + originatingTown
                + ", destinationCountry=" + destinationCountry
                + ", destinationTown=" + destinationTown
                + ", destinationCurrency=" + destinationCurrency
                + ", reference=" + reference + ", localAmount=" + localAmount
                + "]";
    }

}

