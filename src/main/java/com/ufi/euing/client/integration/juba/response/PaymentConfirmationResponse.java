package com.ufi.euing.client.integration.juba.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter @Setter
public class PaymentConfirmationResponse implements Serializable {

    @JsonProperty("Response")
    public Response response;
    @JsonProperty("Data")
    public Object data;

    @Override
    public String toString() {
        return "PaymentConfirmationResponse{" + "response=" + response + ", data=" + data + '}';
    }

}
