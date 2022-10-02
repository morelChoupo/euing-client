package com.ufi.euing.client.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Data
@Getter
@Setter
public class Partenaire implements Serializable {

    private String codePartenaire;
    private String denommination;


    public Partenaire() {
        super();
        // TODO Auto-generated constructor stub
    }
    public Partenaire(String codePartenaire, String denommination) {
        super();
        this.codePartenaire = codePartenaire;
        this.denommination = denommination;
    }

    @Override
    public String toString() {
        return "Partenaire [codePartenaire=" + codePartenaire
                + ", denommination=" + denommination + "]";
    }

}
