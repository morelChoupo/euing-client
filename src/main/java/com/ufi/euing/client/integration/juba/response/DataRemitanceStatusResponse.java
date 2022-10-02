package com.ufi.euing.client.integration.juba.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter @Setter
public class DataRemitanceStatusResponse implements Serializable {

    @JsonProperty(" ReferenceNum")
    public String referenceNum;
    @JsonProperty(" Status")
    public int status;

    @Override
    public String toString() {
        return "DataRemitanceStatusResponse{" + "referenceNum=" + referenceNum + ", status=" + status + '}';
    }

}
