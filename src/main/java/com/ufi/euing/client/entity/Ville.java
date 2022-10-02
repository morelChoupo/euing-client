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
@Table(name = "VILLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ville.findAll", query = "SELECT v FROM Ville v")
    , @NamedQuery(name = "Ville.findByViId", query = "SELECT v FROM Ville v WHERE v.viId = :viId")
    , @NamedQuery(name = "Ville.findByCountry", query = "SELECT v FROM Ville v JOIN v.viZnId z JOIN z.znPsCode p   WHERE p.psCode = :psCode and v.statut = 1")
    , @NamedQuery(name = "Ville.findByZone", query = "SELECT v FROM Ville v WHERE v.viZnId.znId = :zone and v.statut = 1")
    , @NamedQuery(name = "Ville.findByViLibelle", query = "SELECT v FROM Ville v WHERE v.viLibelle = :viLibelle")
    , @NamedQuery(name = "Ville.findByStatut", query = "SELECT v FROM Ville v WHERE v.statut = :statut")
    , @NamedQuery(name = "Ville.findByZoneStatut", query = "SELECT v FROM Ville v WHERE v.viZnId.znId = :zone and v.statut = :statut")
    , @NamedQuery(name = "Ville.findByUserCreate", query = "SELECT v FROM Ville v WHERE v.userCreate = :userCreate")
    , @NamedQuery(name = "Ville.findByUserModif", query = "SELECT v FROM Ville v WHERE v.userModif = :userModif")
    , @NamedQuery(name = "Ville.findByDateCreate", query = "SELECT v FROM Ville v WHERE v.dateCreate = :dateCreate")
    , @NamedQuery(name = "Ville.findByDateModif", query = "SELECT v FROM Ville v WHERE v.dateModif = :dateModif")})
public class Ville implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VILLE_SEQ")
    @SequenceGenerator(sequenceName = "S_VILLE", allocationSize = 1, name = "VILLE_SEQ")
    @Basic(optional = false)
    @NotNull
    @Column(name = "VI_ID")
    private BigDecimal viId;
    @Size(max = 255)
    @Column(name = "VI_LIBELLE")
    private String viLibelle;
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
    @JoinColumn(name = "VI_ZN_ID", referencedColumnName = "ZN_ID")
    @ManyToOne
    private Zone viZnId;

    public Ville() {
    }

    public Ville(BigDecimal viId) {
        this.viId = viId;
    }

    public BigDecimal getViId() {
        return viId;
    }

    public void setViId(BigDecimal viId) {
        this.viId = viId;
    }

    public String getViLibelle() {
        return viLibelle;
    }

    public void setViLibelle(String viLibelle) {
        this.viLibelle = viLibelle;
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

    public Zone getViZnId() {
        return viZnId;
    }

    public void setViZnId(Zone viZnId) {
        this.viZnId = viZnId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (viId != null ? viId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ville)) {
            return false;
        }
        Ville other = (Ville) object;
        if ((this.viId == null && other.viId != null) || (this.viId != null && !this.viId.equals(other.viId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Ville{" + "viId=" + viId + ", viLibelle=" + viLibelle + ", statut=" + statut + ", userCreate=" + userCreate + ", userModif=" + userModif + ", dateCreate=" + dateCreate + ", dateModif=" + dateModif + ", viZnId=" + viZnId + '}';
    }

}
