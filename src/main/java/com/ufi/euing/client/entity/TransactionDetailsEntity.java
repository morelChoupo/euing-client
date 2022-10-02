package com.ufi.euing.client.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.util.List;


@Data
public class TransactionDetailsEntity  implements Serializable {

    private String transactionNumber;

    private int typeTransaction; //2:transaction client mobile, 0:transaction client web;

    @JsonIgnore
    private Long userSession;

    private String partnerTransactionNo;

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

    private double localAmount; //

    private double exchangeRate;  	//

    private double serviceCharge;  	//encaisser

    private double customerCharge;  //commissions

    private double taxCharged;

    private double payoutAmount;


    private SenderTransfinEntity senderDetails;

    private SenderTransfinIdEntity senderIdDetails;

    private ReceiverTransfinEntity receiverDetails;

    private ReceiverTransfinPayEntity receiverPaymentDetails;

    private ReceiverTransfinIdEntity receiverIdDetails;

    private Partenaire partenaire;

    private List<AcceptedIdentity> idType;

    public TransactionDetailsEntity() {
    }




    public TransactionDetailsEntity(String transactionNumber,
                                    int typeTransaction, Long userSession, String partnerTransactionNo,
                                    String codeTransaction, String statusCode,
                                    String statusDescription, String transactionDate,
                                    String originatingCountry, String originatingCurrency,
                                    String originatingTown, String destinationCountry,
                                    String destinationTown, String destinationCurrency,
                                    String reference, double localAmount, double exchangeRate,
                                    double serviceCharge, double customerCharge, double taxCharged,
                                    double payoutAmount, SenderTransfinEntity senderDetails,
                                    SenderTransfinIdEntity senderIdDetails,
                                    ReceiverTransfinEntity receiverDetails,
                                    ReceiverTransfinPayEntity receiverPaymentDetails,
                                    ReceiverTransfinIdEntity receiverIdDetails, Partenaire partenaire,
                                    List<AcceptedIdentity> idType) {
        super();
        this.transactionNumber = transactionNumber;
        this.typeTransaction = typeTransaction;
        this.userSession = userSession;
        this.partnerTransactionNo = partnerTransactionNo;
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
        this.exchangeRate = exchangeRate;
        this.serviceCharge = serviceCharge;
        this.customerCharge = customerCharge;
        this.taxCharged = taxCharged;
        this.payoutAmount = payoutAmount;
        this.senderDetails = senderDetails;
        this.senderIdDetails = senderIdDetails;
        this.receiverDetails = receiverDetails;
        this.receiverPaymentDetails = receiverPaymentDetails;
        this.receiverIdDetails = receiverIdDetails;
        this.partenaire = partenaire;
        this.idType = idType;
    }





    @Override
    public String toString() {
        return "TransactionDetailsEntity [transactionNumber="
                + transactionNumber + ", typeTransaction=" + typeTransaction
                + ", userSession=" + userSession + ", partnerTransactionNo="
                + partnerTransactionNo + ", codeTransaction=" + codeTransaction
                + ", statusCode=" + statusCode + ", statusDescription="
                + statusDescription + ", transactionDate=" + transactionDate
                + ", originatingCountry=" + originatingCountry
                + ", originatingCurrency=" + originatingCurrency
                + ", originatingTown=" + originatingTown
                + ", destinationCountry=" + destinationCountry
                + ", destinationTown=" + destinationTown
                + ", destinationCurrency=" + destinationCurrency
                + ", reference=" + reference + ", localAmount=" + localAmount
                + ", exchangeRate=" + exchangeRate + ", serviceCharge="
                + serviceCharge + ", customerCharge=" + customerCharge
                + ", taxCharged=" + taxCharged + ", payoutAmount="
                + payoutAmount + ", senderDetails=" + senderDetails
                + ", senderIdDetails=" + senderIdDetails + ", receiverDetails="
                + receiverDetails + ", receiverPaymentDetails="
                + receiverPaymentDetails + ", receiverIdDetails="
                + receiverIdDetails + ", partenaire=" + partenaire
                + ", idType=" + idType + "]";
    }


}


