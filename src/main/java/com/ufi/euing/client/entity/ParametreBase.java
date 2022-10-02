/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufi.euing.client.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author DNT
 */
@Entity
@Table(name = "PARAMETRE_BASE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ParametreBase.findAll", query = "SELECT p FROM ParametreBase p")
    , @NamedQuery(name = "ParametreBase.findByCle", query = "SELECT p FROM ParametreBase p WHERE p.cle = :cle")
    , @NamedQuery(name = "ParametreBase.findParamSMS", query = "SELECT p FROM ParametreBase p WHERE p.cle in ('SMS_PROVIDER_ID','SMS_PROVIDER_KEY','SMS_PROVIDER_SECRET','SMS_PROVIDER_TOKEN','SMS_PROVIDER_URL')")
    , @NamedQuery(name = "ParametreBase.findByValeur", query = "SELECT p FROM ParametreBase p WHERE p.valeur = :valeur")
    , @NamedQuery(name = "ParametreBase.findByUserCreate", query = "SELECT p FROM ParametreBase p WHERE p.userCreate = :userCreate")
    , @NamedQuery(name = "ParametreBase.findByUserModif", query = "SELECT p FROM ParametreBase p WHERE p.userModif = :userModif")
    , @NamedQuery(name = "ParametreBase.findByDateCreate", query = "SELECT p FROM ParametreBase p WHERE p.dateCreate = :dateCreate")
    , @NamedQuery(name = "ParametreBase.findByDateModif", query = "SELECT p FROM ParametreBase p WHERE p.dateModif = :dateModif")})
public class ParametreBase implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "CLE")
    private String cle;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "VALEUR")
    private String valeur;
    @Size(max = 20)
    @Column(name = "USER_CREATE")
    private String userCreate;
    @Size(max = 20)
    @Column(name = "USER_MODIF")
    private String userModif;
    @Column(name = "DATE_CREATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreate;
    @Column(name = "DATE_MODIF")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModif;

    public ParametreBase() {
    }

    public ParametreBase(String cle) {
        this.cle = cle;
    }

    public ParametreBase(String cle, String valeur) {
        this.cle = cle;
        this.valeur = valeur;
    }

    public String getCle() {
        return cle;
    }

    public void setCle(String cle) {
        this.cle = cle;
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    public String getUserCreate() {
        return userCreate;
    }

    public void setUserCreate(String userCreate) {
        this.userCreate = userCreate;
    }

    public String getUserModif() {
        return userModif;
    }

    public void setUserModif(String userModif) {
        this.userModif = userModif;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Date getDateModif() {
        return dateModif;
    }

    public void setDateModif(Date dateModif) {
        this.dateModif = dateModif;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cle != null ? cle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ParametreBase)) {
            return false;
        }
        ParametreBase other = (ParametreBase) object;
        if ((this.cle == null && other.cle != null) || (this.cle != null && !this.cle.equals(other.cle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ParametreBase[ cle=" + cle + " ]";
    }
    
}
