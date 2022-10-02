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
@Table(name = "GUICHET")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Guichet.findAll", query = "SELECT g FROM Guichet g")
    , @NamedQuery(name = "Guichet.findById", query = "SELECT g FROM Guichet g WHERE g.id = :id")
    , @NamedQuery(name = "Guichet.findByAgence", query = "SELECT g FROM Guichet g WHERE g.agence.id = :agence")
    , @NamedQuery(name = "Guichet.findByLibelle", query = "SELECT g FROM Guichet g WHERE g.libelle = :libelle")
    , @NamedQuery(name = "Guichet.findByStatut", query = "SELECT g FROM Guichet g WHERE g.statut = :statut")
    , @NamedQuery(name = "Guichet.findByUserCreate", query = "SELECT g FROM Guichet g WHERE g.userCreate = :userCreate")
    , @NamedQuery(name = "Guichet.findByUserModif", query = "SELECT g FROM Guichet g WHERE g.userModif = :userModif")
    , @NamedQuery(name = "Guichet.findByDateCreate", query = "SELECT g FROM Guichet g WHERE g.dateCreate = :dateCreate")
    , @NamedQuery(name = "Guichet.findByDateModif", query = "SELECT g FROM Guichet g WHERE g.dateModif = :dateModif")})
public class Guichet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GUI_SEQ")
    @SequenceGenerator(sequenceName = "SEQUENCE_GUICHET", allocationSize = 1, name = "GUI_SEQ")
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Column(name = "CHECK_IP")
    private Short checkIp;
    @Size(max = 255)
    @Column(name = "IP")
    private String ip;    
    @Size(max = 255)
    @Column(name = "LIBELLE")
    private String libelle;
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
    @JoinColumn(name = "AGENCE", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Agence agence;
     @Column(name = "CHECK_CERT")
    private Short checkCert;
    @Size(max = 255)
    @Column(name = "EMAIL_SUPPORT")
    private String emailSupport;
    @Column(name = "OVERDUE")
    private BigDecimal overdue;

    public Guichet() {
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

    public BigDecimal getOverdue() {
        return overdue;
    }

    public void setOverdue(BigDecimal overdue) {
        this.overdue = overdue;
    }
    
    

    public Guichet(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
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

    public Agence getAgence() {
        return agence;
    }

    public void setAgence(Agence agence) {
        this.agence = agence;
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
    
    
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Guichet)) {
            return false;
        }
        Guichet other = (Guichet) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Guichet{" + "id=" + id + ", libelle=" + libelle + ", statut=" + statut + ", userCreate=" + userCreate + ", userModif=" + userModif + ", dateCreate=" + dateCreate + ", dateModif=" + dateModif + ", agence=" + agence + '}';
    }

}
