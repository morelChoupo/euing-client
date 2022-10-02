package com.ufi.euing.client.integration.juba.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;


@Getter @Setter
public class SendRemidanceStatusResponse implements Serializable {

    @JsonProperty("Response")
    public Response response;
    @JsonProperty("Data")
    public List<DataRemitanceStatusResponse> data;


    @Override
    public String toString() {
        return "SendRemidanceStatusResponse{" + "response=" + response + ", data=" + data + '}';
    }

}
