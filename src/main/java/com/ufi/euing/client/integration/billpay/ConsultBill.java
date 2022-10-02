/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufi.euing.client.integration.billpay;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 *
 * @author UFI
 */

@Getter @Setter
public class ConsultBill implements Serializable {

    String marchatCode;
    String serviceCode;
    String searchType;
    String searchValue;

    @JsonIgnore
    Long userId;

    public ConsultBill() {
    }

    @Override
    public String toString() {
        return "ConsultBill{" + "marchatCode=" + marchatCode + ", serviceCode=" + serviceCode + ", searchType=" + searchType + ", searchValue=" + searchValue + ", userId=" + userId + '}';
    }
}
