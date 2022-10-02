package com.ufi.euing.client.entity;

import java.io.Serializable;

public class ReceiverTransfinPayEntity  implements Serializable {

    private String testQuestion;

    private String testAnswer;

    private String dataForServices;

    private String reasonForPayment;


    public ReceiverTransfinPayEntity() {
    }




    public ReceiverTransfinPayEntity(String testQuestion, String testAnswer,
                                     String dataForServices, String reasonForPayment) {
        super();
        this.testQuestion = testQuestion;
        this.testAnswer = testAnswer;
        this.dataForServices = dataForServices;
        this.reasonForPayment = reasonForPayment;
    }

    /**
     * Gets the testQuestion value for this ReceiverPaymentEntity.
     *
     * @return testQuestion
     */
    public String getTestQuestion() {
        return testQuestion;
    }


    /**
     * Sets the testQuestion value for this ReceiverPaymentEntity.
     *
     * @param testQuestion
     */
    public void setTestQuestion(String testQuestion) {
        this.testQuestion = testQuestion;
    }


    /**
     * Gets the testAnswer value for this ReceiverPaymentEntity.
     *
     * @return testAnswer
     */
    public String getTestAnswer() {
        return testAnswer;
    }


    /**
     * Sets the testAnswer value for this ReceiverPaymentEntity.
     *
     * @param testAnswer
     */
    public void setTestAnswer(String testAnswer) {
        this.testAnswer = testAnswer;
    }


    /**
     * Gets the dataForServices value for this ReceiverPaymentEntity.
     *
     * @return dataForServices
     */
    public String getDataForServices() {
        return dataForServices;
    }


    /**
     * Sets the dataForServices value for this ReceiverPaymentEntity.
     *
     * @param dataForServices
     */
    public void setDataForServices(String dataForServices) {
        this.dataForServices = dataForServices;
    }


    /**
     * Gets the reasonForPayment value for this ReceiverPaymentEntity.
     *
     * @return reasonForPayment
     */
    public String getReasonForPayment() {
        return reasonForPayment;
    }


    /**
     * Sets the reasonForPayment value for this ReceiverPaymentEntity.
     *
     * @param reasonForPayment
     */
    public void setReasonForPayment(String reasonForPayment) {
        this.reasonForPayment = reasonForPayment;
    }




    @Override
    public String toString() {
        return "ReceiverTransfinPayEntity [testQuestion=" + testQuestion
                + ", testAnswer=" + testAnswer + ", dataForServices="
                + dataForServices + ", reasonForPayment=" + reasonForPayment
                + "]";
    }




}
