/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufi.euing.client.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Cash-Xpress-User
 * @param <T>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GenericsResponse", propOrder = {
    "responseCode",
    "responseDescription",
    "data",
})
//@XmlSeeAlso(ArrayList.class)

public class GenericsResponse<T> {
    
    @XmlElement(name = "ResponseCode", required = true)
    private int responseCode;
    @XmlElement(name = "ResponseDescription", required = true)
    private String responseDescription;
    @XmlElement(name = "Data", required = true)
    private T data;
    
    
    public GenericsResponse() {
    }
    
    public GenericsResponse(T t) {
        this.responseCode = 200;
        this.responseDescription = "OK";
        this.data = t;
    }

    public GenericsResponse(int responseCode, String responseDescription, T t) {
        this.responseCode = responseCode;
        this.responseDescription = responseDescription;
        this.data = t;
    }


    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseDescription() {
        return responseDescription;
    }

    public void setResponseDescription(String responseDescription) {
        this.responseDescription = responseDescription;
    }

    public T getT() {
        return data;
    }

    public void setT(T t) {
        this.data = t;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
    
}
