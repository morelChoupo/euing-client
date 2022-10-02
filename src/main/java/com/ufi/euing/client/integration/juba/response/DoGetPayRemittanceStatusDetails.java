/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufi.euing.client.integration.juba.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author User
 */

@Getter @Setter
public class DoGetPayRemittanceStatusDetails {

    @JsonProperty("ReferenceNum")
    private String ReferenceNum;

    @JsonProperty("JubaReferenceNum")
    private String JubaReferenceNum;

    @JsonProperty("Status")
    private int Status;

    @JsonProperty("Reason")
    private String Reason;

    @Override
    public String toString() {
        return "DoGetPayRemittanceStatusDetails{" + "ReferenceNum=" + ReferenceNum + ", JubaReferenceNum=" + JubaReferenceNum + ", Status=" + Status + ", Reason=" + Reason + '}';
    }

}
