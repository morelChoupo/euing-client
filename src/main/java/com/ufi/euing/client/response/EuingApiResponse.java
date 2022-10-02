package com.ufi.euing.client.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EuingApiResponse<T> {

    private T data;

    private String message;

    private int status;

    public EuingApiResponse<T> failure(int status, String message) {
        this.message = message;
        this.status = status;
        return this;
    }


    public EuingApiResponse<T> success(T data) {
        this.data = data;
        status = 200;
        message = "success";
        return this;
    }

    public EuingApiResponse<T> success() {
        this.data = null;
        status = 200;
        message = "success";
        return this;
    }


}