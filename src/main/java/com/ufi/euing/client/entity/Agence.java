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
@Table(name = "AGENCE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Agence.findAll", query = "SELECT a FROM Agence a")
    , @NamedQuery(name = "Agence.findById", query = "SELECT a FROM Agence a WHERE a.id = :id")
    , @NamedQuery(name = "Agence.findByCompagnie", query = "SELECT a FROM Agence a WHERE a.compagnie.id = :compagnie and a.statut = 1")
    , @NamedQuery(name = "Agence.findByVille", query = "SELECT a FROM Agence a WHERE a.ville.viId = :ville and a.statut = 1")
    , @NamedQuery(name = "Agence.findByCheckIp", query = "SELECT a FROM Agence a WHERE a.checkIp = :checkIp")
    , @NamedQuery(name = "Agence.findByIp", query = "SELECT a FROM Agence a WHERE a.ip = :ip")
    , @NamedQuery(name = "Agence.findByLibelle", query = "SELECT a FROM Agence a WHERE a.libelle = :libelle")
    , @NamedQuery(name = "Agence.findByCodeCertificat", query = "SELECT a FROM Agence a WHERE a.codeCertificat = :codeCertificat")
    , @NamedQuery(name = "Agence.findByAdresse", query = "SELECT a FROM Agence a WHERE a.adresse = :adresse")
    , @NamedQuery(name = "Agence.findByTelephone", query = "SELECT a FROM Agence a WHERE a.telephone = :telephone")
    , @NamedQuery(name = "Agence.findByFax", query = "SELECT a FROM Agence a WHERE a.fax = :fax")
    , @NamedQuery(name = "Agence.findByStatut", query = "SELECT a FROM Agence a WHERE a.statut = :statut")
    , @NamedQuery(name = "Agence.findByUserCreate", query = "SELECT a FROM Agence a WHERE a.userCreate = :userCreate")
    , @NamedQuery(name = "Agence.findByUserModif", query = "SELECT a FROM Agence a WHERE a.userModif = :userModif")
    , @NamedQuery(name = "Agence.findByDateCreate", query = "SELECT a FROM Agence a WHERE a.dateCreate = :dateCreate")
    , @NamedQuery(name = "Agence.findByDateModif", query = "SELECT a FROM Agence a WHERE a.dateModif = :dateModif")
    , @NamedQuery(name = "Agence.findByOverdue", query = "SELECT a FROM Agence a WHERE a.overdue = :overdue")})
public class Agence implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AGE_SEQ")
    @SequenceGenerator(sequenceName = "SEQUENCE_AGENCE", allocationSize = 1, name = "AGE_SEQ")
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
    @Size(min = 1, max = 255)
    @Column(name = "LIBELLE")
    private String libelle;
    @Size(max = 255)
    @Column(name = "CODE_CERTIFICAT")
    private String codeCertificat;
    @Size(max = 255)
    @Column(name = "ADRESSE")
    private String adresse;
    @Size(max = 255)
    @Column(name = "TELEPHONE")
    private String telephone;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 255)
    @Column(name = "FAX")
    private String fax;
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
    @JoinColumn(name = "COMPAGNIE", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Compagnie compagnie;
    @JoinColumn(name = "VILLE", referencedColumnName = "VI_ID")
    @ManyToOne(optional = false)
    private Ville ville;
    @Column(name = "OVERDUE")
    private BigDecimal overdue;
    @Column(name = "CHECK_CERT")
    private Short checkCert;
    @Size(max = 255)
    @Column(name = "EMAIL_SUPPORT")
    private String emailSupport;

    public Agence() {
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

    public Agence(Long id) {
        this.id = id;
    }

    public Agence(Long id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getCodeCertificat() {
        return codeCertificat;
    }

    public void setCodeCertificat(String codeCertificat) {
        this.codeCertificat = codeCertificat;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
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

    public BigDecimal getOverdue() {
        return overdue;
    }

    public void setOverdue(BigDecimal overdue) {
        this.overdue = overdue;
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

    public Compagnie getCompagnie() {
        return compagnie;
    }

    public void setCompagnie(Compagnie compagnie) {
        this.compagnie = compagnie;
    }

    public Ville getVille() {
        return ville;
    }

    public void setVille(Ville ville) {
        this.ville = ville;
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
        if (!(object instanceof Agence)) {
            return false;
        }
        Agence other = (Agence) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Agence{" + "id=" + id + ", checkIp=" + checkIp + ", ip=" + ip + ", libelle=" + libelle + ", codeCertificat=" + codeCertificat + ", adresse=" + adresse + ", telephone=" + telephone + ", fax=" + fax + ", statut=" + statut + ", userCreate=" + userCreate + ", userModif=" + userModif + ", dateCreate=" + dateCreate + ", dateModif=" + dateModif + ", compagnie=" + compagnie + ", ville=" + ville + '}';
    }

}
