/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufi.euing.client.integration.juba.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 *
 * @author User
 */
@Data
public class DoGetPayRemittanceStatusRequest {

    @JsonProperty("FromDate")
    private String FromDate;

    @JsonProperty("ToDate")
    private String ToDate;

    @JsonProperty("ReferenceNum")
    private String ReferenceNum;


    @Override
    public String toString() {
        return "DoGetPayRemittanceStatusRequest{" + "FromDate=" + FromDate + ", ToDate=" + ToDate + ", ReferenceNum=" + ReferenceNum + '}';
    }

}
