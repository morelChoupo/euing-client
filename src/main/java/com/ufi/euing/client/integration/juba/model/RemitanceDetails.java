package com.ufi.euing.client.integration.juba.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter @Setter
public class RemitanceDetails implements Serializable {

    @JsonProperty("ReferenceNum")
    public String ReferenceNum;


    @Override
    public String toString() {
        return "RemitanceDetails{" + "ReferenceNum=" + ReferenceNum + '}';
    }

}
