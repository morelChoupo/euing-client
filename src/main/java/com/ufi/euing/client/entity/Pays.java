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
@Table(name = "PAYS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pays.findAll", query = "SELECT p FROM Pays p")
    , @NamedQuery(name = "Pays.findByPsCode", query = "SELECT p FROM Pays p WHERE p.psCode = :psCode")
    , @NamedQuery(name = "Pays.findByPsCode2", query = "SELECT p FROM Pays p WHERE p.psCode2 = :psCode2")
    , @NamedQuery(name = "Pays.findByZone", query = "SELECT p FROM Pays p WHERE p.psZmCode.zmCode = :zone")
    , @NamedQuery(name = "Pays.findByPsIndicatif", query = "SELECT p FROM Pays p WHERE p.psIndicatif = :psIndicatif")
    , @NamedQuery(name = "Pays.findByPsLibelle", query = "SELECT p FROM Pays p WHERE p.psLibelle = :psLibelle")
    , @NamedQuery(name = "Pays.findByPsMessageSms", query = "SELECT p FROM Pays p WHERE p.psMessageSms = :psMessageSms")
    , @NamedQuery(name = "Pays.findByPsStatut", query = "SELECT p FROM Pays p WHERE p.psStatut = :psStatut")
    , @NamedQuery(name = "Pays.findByStatut", query = "SELECT p FROM Pays p WHERE p.statut = :statut")
    , @NamedQuery(name = "Pays.findByUserCreate", query = "SELECT p FROM Pays p WHERE p.userCreate = :userCreate")
    , @NamedQuery(name = "Pays.findByUserModif", query = "SELECT p FROM Pays p WHERE p.userModif = :userModif")
    , @NamedQuery(name = "Pays.findByDateCreate", query = "SELECT p FROM Pays p WHERE p.dateCreate = :dateCreate")
    , @NamedQuery(name = "Pays.findByDateModif", query = "SELECT p FROM Pays p WHERE p.dateModif = :dateModif")})
public class Pays implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "PS_CODE")
    private String psCode;
    @Size(max = 255)
    @Column(name = "PS_INDICATIF")
    private String psIndicatif;
    @Size(max = 2)
    @Column(name = "PS_CODE_2")
    private String psCode2;
    @Size(max = 255)
    @Column(name = "PS_LIBELLE")
    private String psLibelle;
    @Size(max = 255)
    @Column(name = "PS_MESSAGE_SMS")
    private String psMessageSms;
    @Column(name = "PS_STATUT")
    private Character psStatut;
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
    @JoinColumn(name = "PS_ZM_CODE", referencedColumnName = "ZM_CODE")
    @ManyToOne
    private ZoneMonetaire psZmCode;

    public Pays() {
    }

    public Pays(String psCode) {
        this.psCode = psCode;
    }

    public String getPsCode() {
        return psCode;
    }

    public String getPsCode2() {
        return psCode2;
    }

    public void setPsCode2(String psCode2) {
        this.psCode2 = psCode2;
    }
    

    public void setPsCode(String psCode) {
        this.psCode = psCode;
    }

    public String getPsIndicatif() {
        return psIndicatif;
    }

    public void setPsIndicatif(String psIndicatif) {
        this.psIndicatif = psIndicatif;
    }

    public String getPsLibelle() {
        return psLibelle;
    }

    public void setPsLibelle(String psLibelle) {
        this.psLibelle = psLibelle;
    }

    public String getPsMessageSms() {
        return psMessageSms;
    }

    public void setPsMessageSms(String psMessageSms) {
        this.psMessageSms = psMessageSms;
    }

    public Character getPsStatut() {
        return psStatut;
    }

    public void setPsStatut(Character psStatut) {
        this.psStatut = psStatut;
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

    public ZoneMonetaire getPsZmCode() {
        return psZmCode;
    }

    public void setPsZmCode(ZoneMonetaire psZmCode) {
        this.psZmCode = psZmCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (psCode != null ? psCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pays)) {
            return false;
        }
        Pays other = (Pays) object;
        if ((this.psCode == null && other.psCode != null) || (this.psCode != null && !this.psCode.equals(other.psCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Pays{" + "psCode=" + psCode + ", psIndicatif=" + psIndicatif + ", psLibelle=" + psLibelle + ", psMessageSms=" + psMessageSms + ", psStatut=" + psStatut + ", statut=" + statut + ", userCreate=" + userCreate + ", userModif=" + userModif + ", dateCreate=" + dateCreate + ", dateModif=" + dateModif + ", psZmCode=" + psZmCode + '}';
    }

}
