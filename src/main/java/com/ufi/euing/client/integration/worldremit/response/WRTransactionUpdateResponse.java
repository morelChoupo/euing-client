/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufi.euing.client.integration.worldremit.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 *
 * @author User
 */

@Setter @Getter
public class WRTransactionUpdateResponse implements Serializable{

    @JsonProperty("TransactionId")
    private String transactionId;
    @JsonProperty("Code")
    private String code;
    @JsonProperty("CodeDescription")
    private String codeDescription;

    @Override
    public String toString() {
        return "TransactionUpdateResponse{" + "transactionId=" + transactionId + ", code=" + code + ", codeDescription=" + codeDescription + '}';
    }

}
