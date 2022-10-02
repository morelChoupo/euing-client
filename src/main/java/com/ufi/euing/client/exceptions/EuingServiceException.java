package com.ufi.euing.client.exceptions;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class EuingServiceException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public EuingServiceException(String message) {
        super(message);
        this.status = 500;
    }

    public EuingServiceException(String message, int status) {
        super(message);
        this.status = status;
    }

    protected int status;
}
