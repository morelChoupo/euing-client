package com.ufi.euing.client.entity;

import static com.ufi.euing.client.constants.Authority.*;

public enum Roles {
    ROLE_USER(USER_AUTHORITIES);
    private String[] authorities;

    Roles(String... authorities) {
        this.authorities = authorities;
    }

    public String[] getAuthorities() {
        return authorities;
    }
}
