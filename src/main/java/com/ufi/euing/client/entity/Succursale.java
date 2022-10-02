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
 * @author Cash-Xpress-User
 */
@Entity
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"CODE"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Succursale.findAll", query = "SELECT s FROM Succursale s")
    , @NamedQuery(name = "Succursale.findById", query = "SELECT s FROM Succursale s WHERE s.id = :id")
    , @NamedQuery(name = "Succursale.findByCode", query = "SELECT s FROM Succursale s WHERE s.code = :code")
    , @NamedQuery(name = "Succursale.findByLibelle", query = "SELECT s FROM Succursale s WHERE s.libelle = :libelle")
    , @NamedQuery(name = "Succursale.findByInstance", query = "SELECT s FROM Succursale s WHERE s.instance = :instance")
    , @NamedQuery(name = "Succursale.findByStatut", query = "SELECT s FROM Succursale s WHERE s.statut = :statut")
    , @NamedQuery(name = "Succursale.findByUserCreate", query = "SELECT s FROM Succursale s WHERE s.userCreate = :userCreate")
    , @NamedQuery(name = "Succursale.findByUserModif", query = "SELECT s FROM Succursale s WHERE s.userModif = :userModif")
    , @NamedQuery(name = "Succursale.findByDateCreate", query = "SELECT s FROM Succursale s WHERE s.dateCreate = :dateCreate")
    , @NamedQuery(name = "Succursale.findByDateModif", query = "SELECT s FROM Succursale s WHERE s.dateModif = :dateModif")
    , @NamedQuery(name = "Succursale.findByCheckIp", query = "SELECT s FROM Succursale s WHERE s.checkIp = :checkIp")
    , @NamedQuery(name = "Succursale.findByIp", query = "SELECT s FROM Succursale s WHERE s.ip = :ip")
    , @NamedQuery(name = "Succursale.findByOverdue", query = "SELECT s FROM Succursale s WHERE s.overdue = :overdue")})
public class Succursale implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SUC_SEQ")
    @SequenceGenerator(sequenceName = "SEQUENCE_SUCCURSALE", allocationSize = 1, name = "SUC_SEQ")
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Column(name = "CHECK_IP")
    private Short checkIp;
    @Size(max = 255)
    @Column(name = "IP")
    private String ip;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "CODE")
    private String code;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "LIBELLE")
    private String libelle;
    @Column(name = "INSTANCE")
    private Long instance;
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
//    @Column(name = "CHECK_IP")
//    private Short checkIp;
//    @Size(max = 255)
//    @Column(name = "IP")
//    private String ip;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "OVERDUE")
    private BigDecimal overdue;
    @Column(name = "CHECK_CERT")
    private Short checkCert;
    @Size(max = 255)
    @Column(name = "EMAIL_SUPPORT")
    private String emailSupport;
    @JoinColumn(name = "DEV_CODE", referencedColumnName = "DEV_CODE")
    @ManyToOne
    private Devise devCode;

    public Succursale() {
    }
    
    

    public Succursale(Long id) {
        this.id = id;
    }

    public Devise getDevCode() {
        return devCode;
    }

    public void setDevCode(Devise devCode) {
        this.devCode = devCode;
    }
    
    

    public Short getCheckCert() {
        return checkCert;
    }

    public void setCheckCert(Short checkCert) {
        this.checkCert = checkCert;
    }

    public String getEmailSupport() {
        return emailSupport;
    }

    public void setEmailSupport(String emailSupport) {
        this.emailSupport = emailSupport;
    }

    public Succursale(Long id, String code, String libelle) {
        this.id = id;
        this.code = code;
        this.libelle = libelle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Long getInstance() {
        return instance;
    }

    public void setInstance(Long instance) {
        this.instance = instance;
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

    public Short getCheckIp() {
        return checkIp;
    }

    public void setCheckIp(Short checkIp) {
        this.checkIp = checkIp;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public BigDecimal getOverdue() {
        return overdue;
    }

    public void setOverdue(BigDecimal overdue) {
        this.overdue = overdue;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Succursale)) {
            return false;
        }
        Succursale other = (Succursale) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Succursale{" + "id=" + id + ", code=" + code + ", libelle=" + libelle + ", instance=" + instance + ", statut=" + statut + ", userCreate=" + userCreate + ", userModif=" + userModif + ", dateCreate=" + dateCreate + ", dateModif=" + dateModif + '}';
    }

}
