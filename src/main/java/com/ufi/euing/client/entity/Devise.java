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
@Table(name = "DEVISE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Devise.findAll", query = "SELECT d FROM Devise d")
    , @NamedQuery(name = "Devise.findByDevCode", query = "SELECT d FROM Devise d WHERE d.devCode = :devCode")
    , @NamedQuery(name = "Devise.findByDevUniteComptable", query = "SELECT d FROM Devise d WHERE d.devUniteComptable = :devUniteComptable")
    , @NamedQuery(name = "Devise.findByDevUniteMonetaire", query = "SELECT d FROM Devise d WHERE d.devUniteMonetaire = :devUniteMonetaire")
    , @NamedQuery(name = "Devise.findByStatut", query = "SELECT d FROM Devise d WHERE d.statut = :statut")
    , @NamedQuery(name = "Devise.findByUserCreate", query = "SELECT d FROM Devise d WHERE d.userCreate = :userCreate")
    , @NamedQuery(name = "Devise.findByUserModif", query = "SELECT d FROM Devise d WHERE d.userModif = :userModif")
    , @NamedQuery(name = "Devise.findByDateCreate", query = "SELECT d FROM Devise d WHERE d.dateCreate = :dateCreate")
    , @NamedQuery(name = "Devise.findByDateModif", query = "SELECT d FROM Devise d WHERE d.dateModif = :dateModif")})
public class Devise implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "DEV_CODE")
    private String devCode;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "DEV_UNITE_COMPTABLE")
    private Double devUniteComptable;
    @Column(name = "DEV_UNITE_MONETAIRE")
    private Double devUniteMonetaire;
    @Column(name = "STATUT")
    private Short statut;
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

    public Devise() {
    }

    public Devise(String devCode) {
        this.devCode = devCode;
    }

    public String getDevCode() {
        return devCode;
    }

    public void setDevCode(String devCode) {
        this.devCode = devCode;
    }

    public Double getDevUniteComptable() {
        return devUniteComptable;
    }

    public void setDevUniteComptable(Double devUniteComptable) {
        this.devUniteComptable = devUniteComptable;
    }

    public Double getDevUniteMonetaire() {
        return devUniteMonetaire;
    }

    public void setDevUniteMonetaire(Double devUniteMonetaire) {
        this.devUniteMonetaire = devUniteMonetaire;
    }

    public Short getStatut() {
        return statut;
    }

    public void setStatut(Short statut) {
        this.statut = statut;
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
        hash += (devCode != null ? devCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Devise)) {
            return false;
        }
        Devise other = (Devise) object;
        if ((this.devCode == null && other.devCode != null) || (this.devCode != null && !this.devCode.equals(other.devCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Devise{" + "devCode=" + devCode + ", devUniteComptable=" + devUniteComptable + ", devUniteMonetaire=" + devUniteMonetaire + ", statut=" + statut + ", userCreate=" + userCreate + ", userModif=" + userModif + ", dateCreate=" + dateCreate + ", dateModif=" + dateModif + '}';
    }

}
