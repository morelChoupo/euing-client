/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufi.euing.client.integration.gimac.model;

/**
 *
 * @author UFI
 */
public class AmaError {

    protected String error;
    protected String error_description;

    @SuppressWarnings("WeakerAccess")
    public boolean hasError() {
        return this.error != null || this.error_description != null;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError_description() {
        return error_description;
    }

    public void setError_description(String error_description) {
        this.error_description = error_description;
    }

    @Override
    public String toString() {
        return "AmaError{" + "error=" + error + ", error_description=" + error_description + '}';
    }

}
