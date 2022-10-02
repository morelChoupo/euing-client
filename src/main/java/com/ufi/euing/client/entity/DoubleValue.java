package com.ufi.euing.client.entity;

import java.io.Serializable;

public class DoubleValue implements Serializable {
    String code;
    String libelle;


    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    public DoubleValue(String code, String libelle) {
        super();
        this.code = code;
        this.libelle = libelle;
    }
    public DoubleValue() {
        super();
        // TODO Auto-generated constructor stub
    }

}
