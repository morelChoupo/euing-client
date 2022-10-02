package com.ufi.euing.client.integration.gimac.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ufi.euing.client.integration.gimac.model.AmaError;


@JsonIgnoreProperties(ignoreUnknown = true)
public class AccessTokenResponse extends AmaError {

    private String access_token;
    private String token_type;
    private String refresh_token;
    private Long expires_in;
    private String scope;

    private boolean expired;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public Long getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(Long expires_in) {
        this.expires_in = expires_in;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
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
        return "AccessTokenResponse{" + "access_token=" + access_token + ", token_type=" + token_type + ", refresh_token=" + refresh_token + ", expires_in=" + expires_in + ", scope=" + scope + ", expired=" + expired + '}';
    }

}
