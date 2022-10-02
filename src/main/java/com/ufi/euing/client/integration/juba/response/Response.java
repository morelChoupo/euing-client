package com.ufi.euing.client.integration.juba.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter @Setter
public class Response implements Serializable {

    @JsonProperty("Code")
    public String code;
    @JsonProperty("Message")
    public String message;

    @Override
    public String toString() {
        return "Response{" + "code=" + code + ", message=" + message + '}';
    }

}
