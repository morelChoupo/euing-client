package com.ufi.euing.client.integration.juba.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter @Setter
public class CustomerDocument implements Serializable {

    @JsonProperty("DocumentType")
    public int documentType;
    @JsonProperty("DocumentNumber")
    public String documentNumber;
    @JsonProperty("IssueDate")
    public String issueDate;
    @JsonProperty("ExpiryDate")
    public String expiryDate;
    @JsonProperty("Country")
    public String country;


    @Override
    public String toString() {
        return "CustomerDocument{" + "documentType=" + documentType + ", documentNumber=" + documentNumber + ", issueDate=" + issueDate + ", expiryDate=" + expiryDate + ", country=" + country + '}';
    }

}
