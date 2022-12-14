package com.ufi.euing.client.integration.billpay;

import java.io.Serializable;

public class LoginResponse implements Serializable {

    /*@JsonAlias("access_token")
    @JsonProperty("accessToken")*/
    private String access_token;
//    @JsonAlias("token_type")
//    @JsonProperty("tokenType")
    private String token_type;
    /*@JsonAlias("refresh_token")
    @JsonProperty("refreshToken")*/
    private String refresh_token;
    /*  @JsonAlias("expires_in")
    @JsonProperty("expiresIn")*/
    private long expires_in;
    private String scope;

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

    public long getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(long expires_in) {
        this.expires_in = expires_in;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
