package com.ufi.euing.client.integration.billpay;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlRootElement
@XmlType(propOrder = {"code", "transactionId", "message", "status"})
public class CheckBillResponse implements Serializable {

    private Integer code;
    private String transactionId;
    private String message;
    private String status;

    public CheckBillResponse() {
    }

    public CheckBillResponse(Integer code, String transactionId, String message, String status) {
        this.code = code;
        this.transactionId = transactionId;
        this.message = message;
        this.status = status;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
