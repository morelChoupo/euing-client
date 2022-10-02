package com.ufi.euing.client.integration.juba.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ufi.euing.client.integration.juba.model.RemitanceDetails;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter @Setter
public class SendRemittanceResponse implements Serializable {

    @JsonProperty("Response")
    public Response response;

    @JsonProperty("Data")
    public RemitanceDetails data;

    @Override
    public String toString() {
        return "SendRemittanceResponse{" + "response=" + response + ", data=" + data + '}';
    }

}
