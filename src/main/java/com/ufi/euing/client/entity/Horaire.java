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
import java.math.BigInteger;
import java.util.Date;

/**
 *
 * @author Cash-Xpress-User
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Horaire.findAll", query = "SELECT h FROM Horaire h")
    , @NamedQuery(name = "Horaire.findByHrId", query = "SELECT h FROM Horaire h WHERE h.hrId = :hrId")
    , @NamedQuery(name = "Horaire.findByHrDay", query = "SELECT h FROM Horaire h WHERE h.hrDay = :hrDay")
    , @NamedQuery(name = "Horaire.findByHrBegin", query = "SELECT h FROM Horaire h WHERE h.hrBegin = :hrBegin")
    , @NamedQuery(name = "Horaire.findByHrEnd", query = "SELECT h FROM Horaire h WHERE h.hrEnd = :hrEnd")
    , @NamedQuery(name = "Horaire.findByFormatteddebut", query = "SELECT h FROM Horaire h WHERE h.formatteddebut = :formatteddebut")
    , @NamedQuery(name = "Horaire.findByFormattedfin", query = "SELECT h FROM Horaire h WHERE h.formattedfin = :formattedfin")
    , @NamedQuery(name = "Horaire.findByHrUoId", query = "SELECT h FROM Horaire h WHERE h.hrUoId = :hrUoId")
    , @NamedQuery(name = "Horaire.findByDayTypeUoUoId", query = "SELECT h FROM Horaire h WHERE h.hrUoId = :hrUoId and h.hrDay = :hrDay and h.hrUoType = :hrUoType")
    , @NamedQuery(name = "Horaire.findByStatut", query = "SELECT h FROM Horaire h WHERE h.statut = :statut")
    , @NamedQuery(name = "Horaire.findByUserCreate", query = "SELECT h FROM Horaire h WHERE h.userCreate = :userCreate")
    , @NamedQuery(name = "Horaire.findByUserModif", query = "SELECT h FROM Horaire h WHERE h.userModif = :userModif")
    , @NamedQuery(name = "Horaire.findByDateCreate", query = "SELECT h FROM Horaire h WHERE h.dateCreate = :dateCreate")
    , @NamedQuery(name = "Horaire.findByDateModif", query = "SELECT h FROM Horaire h WHERE h.dateModif = :dateModif")
    , @NamedQuery(name = "Horaire.findByHrUoType", query = "SELECT h FROM Horaire h WHERE h.hrUoType = :hrUoType")})
public class Horaire implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_HORAIRE")
    @SequenceGenerator(sequenceName = "SEQ_HORAIRE", allocationSize = 1, name = "SEQ_HORAIRE")
    @Basic(optional = false)
    @NotNull
    @Column(name = "HR_ID", nullable = false, precision = 19, scale = 0)
    private BigDecimal hrId;
    @Column(name = "HR_DAY")
    private BigInteger hrDay;
    @Column(name = "HR_BEGIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hrBegin;
    @Column(name = "HR_END")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hrEnd;
    @Size(max = 255)
    @Column(length = 255)
    private String formatteddebut;
    @Size(max = 255)
    @Column(length = 255)
    private String formattedfin;
    @Column(name = "HR_UO_ID")
    private BigInteger hrUoId;
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
    @Size(max = 255)
    @Column(name = "HR_UO_TYPE", length = 255)
    private String hrUoType;

    public Horaire() {
    }

    public Horaire(BigDecimal hrId) {
        this.hrId = hrId;
    }

    public BigDecimal getHrId() {
        return hrId;
    }

    public void setHrId(BigDecimal hrId) {
        this.hrId = hrId;
    }

    public BigInteger getHrDay() {
        return hrDay;
    }

    public void setHrDay(BigInteger hrDay) {
        this.hrDay = hrDay;
    }

    public Date getHrBegin() {
        return hrBegin;
    }

    public void setHrBegin(Date hrBegin) {
        this.hrBegin = hrBegin;
    }

    public Date getHrEnd() {
        return hrEnd;
    }

    public void setHrEnd(Date hrEnd) {
        this.hrEnd = hrEnd;
    }

    public String getFormatteddebut() {
        return formatteddebut;
    }

    public void setFormatteddebut(String formatteddebut) {
        this.formatteddebut = formatteddebut;
    }

    public String getFormattedfin() {
        return formattedfin;
    }

    public void setFormattedfin(String formattedfin) {
        this.formattedfin = formattedfin;
    }

    public BigInteger getHrUoId() {
        return hrUoId;
    }

    public void setHrUoId(BigInteger hrUoId) {
        this.hrUoId = hrUoId;
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

    public String getHrUoType() {
        return hrUoType;
    }

    public void setHrUoType(String hrUoType) {
        this.hrUoType = hrUoType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hrId != null ? hrId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Horaire)) {
            return false;
        }
        Horaire other = (Horaire) object;
        if ((this.hrId == null && other.hrId != null) || (this.hrId != null && !this.hrId.equals(other.hrId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ufi.euing.entiteservice.entities.Horaire[ hrId=" + hrId + " ]";
    }
    
}
