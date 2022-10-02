/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufi.euing.client.integration.billpay;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author UFI
 */

@Getter @Setter
public class RequestCamwater {

    private String accountNumber;
    private Double amount;
    private String signature;
    private Long timestamp;
    private String hash;
    private String code;
    private String phone;
    private String idTransaction;


    @Override
    public String toString() {
        return "RequestCamwater{" + "accountNumber=" + accountNumber + ", amount=" + amount + ", signature=" + signature + ", timestamp=" + timestamp + ", hash=" + hash + ", code=" + code + ", phone=" + phone + ", idTransaction=" + idTransaction + '}';
    }
}
