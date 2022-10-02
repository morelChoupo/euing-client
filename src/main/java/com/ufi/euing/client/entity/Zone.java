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
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author DNT
 */
@Entity
@Table(name = "ZONE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Zone.findAll", query = "SELECT z FROM Zone z")
    , @NamedQuery(name = "Zone.findByZnId", query = "SELECT z FROM Zone z WHERE z.znId = :znId")
    , @NamedQuery(name = "Zone.findByPays", query = "SELECT z FROM Zone z WHERE z.znPsCode.psCode = :pays")
    , @NamedQuery(name = "Zone.findByGmt", query = "SELECT z FROM Zone z WHERE z.gmt = :gmt")
    , @NamedQuery(name = "Zone.findByZnLibelle", query = "SELECT z FROM Zone z WHERE z.znLibelle = :znLibelle")
    , @NamedQuery(name = "Zone.findByStatut", query = "SELECT z FROM Zone z WHERE z.statut = :statut")
    , @NamedQuery(name = "Zone.findByPaysStatut", query = "SELECT z FROM Zone z WHERE z.znPsCode.psCode = :pays and z.statut = :statut")
    , @NamedQuery(name = "Zone.findByUserCreate", query = "SELECT z FROM Zone z WHERE z.userCreate = :userCreate")
    , @NamedQuery(name = "Zone.findByUserModif", query = "SELECT z FROM Zone z WHERE z.userModif = :userModif")
    , @NamedQuery(name = "Zone.findByDateCreate", query = "SELECT z FROM Zone z WHERE z.dateCreate = :dateCreate")
    , @NamedQuery(name = "Zone.findByDateModif", query = "SELECT z FROM Zone z WHERE z.dateModif = :dateModif")})

public class Zone implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ZONE_SEQ")
    @SequenceGenerator(sequenceName = "S_ZONE", allocationSize = 1, name = "ZONE_SEQ")
    @Basic(optional = false)
    @NotNull
    @Column(name = "ZN_ID")
    private BigDecimal znId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "GMT")
    private long gmt;
    @Size(max = 255)
    @Column(name = "ZN_LIBELLE")
    private String znLibelle;
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
    @JoinColumn(name = "ZN_PS_CODE", referencedColumnName = "PS_CODE")
    @ManyToOne
    private Pays znPsCode;

    public Zone() {
    }

    public Zone(BigDecimal znId) {
        this.znId = znId;
    }

    public Zone(BigDecimal znId, long gmt) {
        this.znId = znId;
        this.gmt = gmt;
    }

    public BigDecimal getZnId() {
        return znId;
    }

    public void setZnId(BigDecimal znId) {
        this.znId = znId;
    }

    public long getGmt() {
        return gmt;
    }

    public void setGmt(long gmt) {
        this.gmt = gmt;
    }

    public String getZnLibelle() {
        return znLibelle;
    }

    public void setZnLibelle(String znLibelle) {
        this.znLibelle = znLibelle;
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

    public Pays getZnPsCode() {
        return znPsCode;
    }

    public void setZnPsCode(Pays znPsCode) {
        this.znPsCode = znPsCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (znId != null ? znId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Zone)) {
            return false;
        }
        Zone other = (Zone) object;
        if ((this.znId == null && other.znId != null) || (this.znId != null && !this.znId.equals(other.znId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Zone{" + "znId=" + znId + ", gmt=" + gmt + ", znLibelle=" + znLibelle + ", statut=" + statut + ", userCreate=" + userCreate + ", userModif=" + userModif + ", dateCreate=" + dateCreate + ", dateModif=" + dateModif + ", znPsCode=" + znPsCode + '}';
    }

}
