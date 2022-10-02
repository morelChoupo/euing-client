package com.ufi.euing.client.integration.juba.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter
public class PayRemittanceStatus implements Serializable {

    @JsonProperty("FromDate")
    public String fromDate;
    @JsonProperty("ToDate")
    public String toDate;
    @JsonProperty("ReferenceNum")
    public String referenceNum;

    @Override
    public String toString() {
        return "PayRemittanceStatus{" + "fromDate=" + fromDate + ", toDate=" + toDate + ", referenceNum=" + referenceNum + '}';
    }

}
