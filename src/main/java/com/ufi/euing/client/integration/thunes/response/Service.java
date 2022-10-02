package com.ufi.euing.client.integration.thunes.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter @Setter
public class Service implements Serializable {

    private int id;
    private String name;

    @Override
    public String toString() {
        return "Service{" + "id=" + id + ", name=" + name + '}';
    }
}
