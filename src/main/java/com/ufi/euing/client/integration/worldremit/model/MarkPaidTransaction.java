/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufi.euing.client.integration.worldremit.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 *
 * @author UFI
 */


@Getter @Setter
public class MarkPaidTransaction implements Serializable {
    
    @JsonProperty("office_id")
    private String officeId;
    @JsonProperty("beneficiary_id_number")
    private String beneficiaryIdNumber;
    @JsonProperty("beneficiary_id_type")
    private String beneficiaryIdType;

    @Override
    public String toString() {
        return "MarkPaidTransaction{" + "officeId=" + officeId + ", beneficiaryIdNumber=" + beneficiaryIdNumber + ", beneficiaryIdType=" + beneficiaryIdType + '}';
    }
}
