package com.ufi.euing.client.integration.juba.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;


@Data
public class DoGetBeneficiaryName implements Serializable {

    @JsonProperty("MobileNo")
    public String MobileNo;


    @Override
    public String toString() {
        return "DoGetBeneficiary{" + "MobileNo=" + MobileNo + '}';
    }

}
