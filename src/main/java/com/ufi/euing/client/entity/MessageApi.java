package com.ufi.euing.client.entity;

import java.io.Serializable;

public class MessageApi implements Serializable {

    private String messageCode = "", messageDescription = "";
    private String sendCode = "";

    public String getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }

    public String getMessageDescription() {
        return messageDescription;
    }

    public void setMessageDescription(String messageDescription) {
        this.messageDescription = messageDescription;
    }

    public String getSendCode() {
        return sendCode;
    }

    public void setSendCode(String sendCode) {
        this.sendCode = sendCode;
    }

    public MessageApi() {
        super();
        // TODO Auto-generated constructor stub
    }

    public MessageApi(String messageCode, String messageDescription) {
        super();
        this.messageCode = messageCode;
        this.messageDescription = messageDescription;
    }

    @Override
    public String toString() {
        return "MessageApi{" + "messageCode=" + messageCode + ", messageDescription=" + messageDescription + ", sendCode=" + sendCode + '}';
    }

}
