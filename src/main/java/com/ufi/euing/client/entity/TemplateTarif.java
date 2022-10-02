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
@Table(name = "TEMPLATE_TARIF")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TemplateTarif.findAll", query = "SELECT t FROM TemplateTarif t")
    , @NamedQuery(name = "TemplateTarif.findByTtId", query = "SELECT t FROM TemplateTarif t WHERE t.ttId = :ttId")
    , @NamedQuery(name = "TemplateTarif.findByTtDesc", query = "SELECT t FROM TemplateTarif t WHERE t.ttDesc = :ttDesc")
    , @NamedQuery(name = "TemplateTarif.findByTtLibelle", query = "SELECT t FROM TemplateTarif t WHERE t.ttLibelle = :ttLibelle")
    , @NamedQuery(name = "TemplateTarif.findByStatut", query = "SELECT t FROM TemplateTarif t WHERE t.statut = :statut")
    , @NamedQuery(name = "TemplateTarif.findByUserCreate", query = "SELECT t FROM TemplateTarif t WHERE t.userCreate = :userCreate")
    , @NamedQuery(name = "TemplateTarif.findByUserModif", query = "SELECT t FROM TemplateTarif t WHERE t.userModif = :userModif")
    , @NamedQuery(name = "TemplateTarif.findByDateCreate", query = "SELECT t FROM TemplateTarif t WHERE t.dateCreate = :dateCreate")
    , @NamedQuery(name = "TemplateTarif.findByDateModif", query = "SELECT t FROM TemplateTarif t WHERE t.dateModif = :dateModif")})
public class TemplateTarif implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TEMPT_SEQ")
    @SequenceGenerator(sequenceName = "S_TEAMPLATE_TARIF", allocationSize = 1, name = "TEMPT_SEQ")
    @Basic(optional = false)
    @NotNull
    @Column(name = "TT_ID")
    private BigDecimal ttId;
    @Size(max = 512)
    @Column(name = "TT_DESC")
    private String ttDesc;
    @Size(max = 225)
    @Column(name = "TT_LIBELLE")
    private String ttLibelle;
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

    public TemplateTarif() {
    }

    public TemplateTarif(BigDecimal ttId) {
        this.ttId = ttId;
    }

    public BigDecimal getTtId() {
        return ttId;
    }

    public void setTtId(BigDecimal ttId) {
        this.ttId = ttId;
    }

    public String getTtDesc() {
        return ttDesc;
    }

    public void setTtDesc(String ttDesc) {
        this.ttDesc = ttDesc;
    }

    public String getTtLibelle() {
        return ttLibelle;
    }

    public void setTtLibelle(String ttLibelle) {
        this.ttLibelle = ttLibelle;
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
        hash += (ttId != null ? ttId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TemplateTarif)) {
            return false;
        }
        TemplateTarif other = (TemplateTarif) object;
        if ((this.ttId == null && other.ttId != null) || (this.ttId != null && !this.ttId.equals(other.ttId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TemplateTarif{" + "ttId=" + ttId + ", ttDesc=" + ttDesc + ", ttLibelle=" + ttLibelle + ", statut=" + statut + ", userCreate=" + userCreate + ", userModif=" + userModif + ", dateCreate=" + dateCreate + ", dateModif=" + dateModif + '}';
    }

   
}
