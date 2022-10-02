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
 * @author Cash-Xpress-User
 */
@Entity
@Table(name = "FINANCIAL_TRACKING_TEMPLATE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LimitationTransfert.findAll", query = "SELECT l FROM LimitationTransfert l")
    , @NamedQuery(name = "LimitationTransfert.findByFttCode", query = "SELECT l FROM LimitationTransfert l WHERE l.fttCode = :fttCode")
    , @NamedQuery(name = "LimitationTransfert.findByFttDescription", query = "SELECT l FROM LimitationTransfert l WHERE l.fttDescription = :fttDescription")
    , @NamedQuery(name = "LimitationTransfert.findByStatut", query = "SELECT l FROM LimitationTransfert l WHERE l.statut = :statut")
    , @NamedQuery(name = "LimitationTransfert.findByUserCreate", query = "SELECT l FROM LimitationTransfert l WHERE l.userCreate = :userCreate")
    , @NamedQuery(name = "LimitationTransfert.findByUserModif", query = "SELECT l FROM LimitationTransfert l WHERE l.userModif = :userModif")
    , @NamedQuery(name = "LimitationTransfert.findByDateCreate", query = "SELECT l FROM LimitationTransfert l WHERE l.dateCreate = :dateCreate")
    , @NamedQuery(name = "LimitationTransfert.findByDateModif", query = "SELECT l FROM LimitationTransfert l WHERE l.dateModif = :dateModif")})
public class LimitationTransfert implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "FTT_CODE", nullable = false, length = 255)
    private String fttCode;
    @Size(max = 255)
    @Column(name = "FTT_DESCRIPTION", length = 255)
    private String fttDescription;
    private Short statut;
    @Size(max = 20)
    @Column(name = "USER_CREATE", length = 20)
    private String userCreate;
    @Size(max = 20)
    @Column(name = "USER_MODIF", length = 20)
    private String userModif;
    @Column(name = "DATE_CREATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreate;
    @Column(name = "DATE_MODIF")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModif;
    @JoinColumn(name = "FTT_PS_CODE", referencedColumnName = "PS_CODE")
    @ManyToOne
    private Pays fttPsCode;


    public LimitationTransfert() {
    }

    public LimitationTransfert(String fttCode) {
        this.fttCode = fttCode;
    }

    public String getFttCode() {
        return fttCode;
    }

    public void setFttCode(String fttCode) {
        this.fttCode = fttCode;
    }

    public String getFttDescription() {
        return fttDescription;
    }

    public void setFttDescription(String fttDescription) {
        this.fttDescription = fttDescription;
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

    public Pays getFttPsCode() {
        return fttPsCode;
    }

    public void setFttPsCode(Pays fttPsCode) {
        this.fttPsCode = fttPsCode;
    }


//    @XmlTransient
//    public List<LimitationTransfertDetails> getLimitationTransfertDetailsList() {
//        return limitationTransfertDetailsList;
//    }
//
//    public void setLimitationTransfertDetailsList(List<LimitationTransfertDetails> limitationTransfertDetailsList) {
//        this.limitationTransfertDetailsList = limitationTransfertDetailsList;
//    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fttCode != null ? fttCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LimitationTransfert)) {
            return false;
        }
        LimitationTransfert other = (LimitationTransfert) object;
        if ((this.fttCode == null && other.fttCode != null) || (this.fttCode != null && !this.fttCode.equals(other.fttCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ufi.euing.client.entity.LimitationTransfert[ fttCode=" + fttCode + " ]";
    }

}
