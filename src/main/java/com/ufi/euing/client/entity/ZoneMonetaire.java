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
@Table(name = "ZONE_MONETAIRE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ZoneMonetaire.findAll", query = "SELECT z FROM ZoneMonetaire z")
    , @NamedQuery(name = "ZoneMonetaire.findByZmCode", query = "SELECT z FROM ZoneMonetaire z WHERE z.zmCode = :zmCode")
    , @NamedQuery(name = "ZoneMonetaire.findByZmLibelle", query = "SELECT z FROM ZoneMonetaire z WHERE z.zmLibelle = :zmLibelle")
    , @NamedQuery(name = "ZoneMonetaire.findByStatut", query = "SELECT z FROM ZoneMonetaire z WHERE z.statut = :statut")
    , @NamedQuery(name = "ZoneMonetaire.findByUserCreate", query = "SELECT z FROM ZoneMonetaire z WHERE z.userCreate = :userCreate")
    , @NamedQuery(name = "ZoneMonetaire.findByUserModif", query = "SELECT z FROM ZoneMonetaire z WHERE z.userModif = :userModif")
    , @NamedQuery(name = "ZoneMonetaire.findByDateCreate", query = "SELECT z FROM ZoneMonetaire z WHERE z.dateCreate = :dateCreate")
    , @NamedQuery(name = "ZoneMonetaire.findByDateModif", query = "SELECT z FROM ZoneMonetaire z WHERE z.dateModif = :dateModif")})
public class ZoneMonetaire implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "ZM_CODE")
    private String zmCode;
    @Size(max = 255)
    @Column(name = "ZM_LIBELLE")
    private String zmLibelle;
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
    @JoinColumn(name = "ZM_DEV_CODE", referencedColumnName = "DEV_CODE")
    @ManyToOne
    private Devise zmDevCode;

    public ZoneMonetaire() {
    }

    public ZoneMonetaire(String zmCode) {
        this.zmCode = zmCode;
    }

    public String getZmCode() {
        return zmCode;
    }

    public void setZmCode(String zmCode) {
        this.zmCode = zmCode;
    }

    public String getZmLibelle() {
        return zmLibelle;
    }

    public void setZmLibelle(String zmLibelle) {
        this.zmLibelle = zmLibelle;
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

    public Devise getZmDevCode() {
        return zmDevCode;
    }

    public void setZmDevCode(Devise zmDevCode) {
        this.zmDevCode = zmDevCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (zmCode != null ? zmCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ZoneMonetaire)) {
            return false;
        }
        ZoneMonetaire other = (ZoneMonetaire) object;
        if ((this.zmCode == null && other.zmCode != null) || (this.zmCode != null && !this.zmCode.equals(other.zmCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ZoneMonetaire{" + "zmCode=" + zmCode + ", zmLibelle=" + zmLibelle + ", statut=" + statut + ", userCreate=" + userCreate + ", userModif=" + userModif + ", dateCreate=" + dateCreate + ", dateModif=" + dateModif + ", zmDevCode=" + zmDevCode + '}';
    }

}
