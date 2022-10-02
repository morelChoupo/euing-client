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
 * @author DNT
 */
@Entity
@Table(name = "COMPAGNIE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Compagnie.findAll", query = "SELECT c FROM Compagnie c")
    , @NamedQuery(name = "Compagnie.findById", query = "SELECT c FROM Compagnie c WHERE c.id = :id")
    , @NamedQuery(name = "Compagnie.findBySuccursaleCode", query = "SELECT c FROM Compagnie c WHERE c.succursale.id = :succursale")
    , @NamedQuery(name = "Compagnie.findByPaysCode", query = "SELECT c FROM Compagnie c WHERE c.pays.psCode = :pays and c.statut = :statut")
    , @NamedQuery(name = "Compagnie.findByCodePartenaire", query = "SELECT c FROM Compagnie c WHERE c.codePartenaire = :codePartenaire")
    , @NamedQuery(name = "Compagnie.findBySuccursale", query = "SELECT c FROM Compagnie c WHERE c.succursale = :succursale")
    , @NamedQuery(name = "Compagnie.findByLibelle", query = "SELECT c FROM Compagnie c WHERE c.libelle = :libelle")
    , @NamedQuery(name = "Compagnie.findByCodeCertificat", query = "SELECT c FROM Compagnie c WHERE c.codeCertificat = :codeCertificat")
    , @NamedQuery(name = "Compagnie.findByAdresse", query = "SELECT c FROM Compagnie c WHERE c.adresse = :adresse")
    , @NamedQuery(name = "Compagnie.findByTelephone", query = "SELECT c FROM Compagnie c WHERE c.telephone = :telephone")
    , @NamedQuery(name = "Compagnie.findByPays", query = "SELECT c FROM Compagnie c WHERE c.pays = :pays")
    , @NamedQuery(name = "Compagnie.findByNatureActivite", query = "SELECT c FROM Compagnie c WHERE c.natureActivite = :natureActivite")
    , @NamedQuery(name = "Compagnie.findBySeuilTransaction", query = "SELECT c FROM Compagnie c WHERE c.seuilTransaction = :seuilTransaction")
    , @NamedQuery(name = "Compagnie.findByTaxeOperationLocale", query = "SELECT c FROM Compagnie c WHERE c.taxeOperationLocale = :taxeOperationLocale")
    , @NamedQuery(name = "Compagnie.findByLibelleTaxe", query = "SELECT c FROM Compagnie c WHERE c.libelleTaxe = :libelleTaxe")
    , @NamedQuery(name = "Compagnie.findByCollecterTaxe", query = "SELECT c FROM Compagnie c WHERE c.collecterTaxe = :collecterTaxe")
    , @NamedQuery(name = "Compagnie.findByStatut", query = "SELECT c FROM Compagnie c WHERE c.statut = :statut")
    , @NamedQuery(name = "Compagnie.findBySuccursaleCodeStatut", query = "SELECT c FROM Compagnie c WHERE c.succursale.id = :succursale and c.statut = :statut")
    , @NamedQuery(name = "Compagnie.findByUserCreate", query = "SELECT c FROM Compagnie c WHERE c.userCreate = :userCreate")
    , @NamedQuery(name = "Compagnie.findByUserModif", query = "SELECT c FROM Compagnie c WHERE c.userModif = :userModif")
    , @NamedQuery(name = "Compagnie.findByDateCreate", query = "SELECT c FROM Compagnie c WHERE c.dateCreate = :dateCreate")
    , @NamedQuery(name = "Compagnie.findByDateModif", query = "SELECT c FROM Compagnie c WHERE c.dateModif = :dateModif")
    , @NamedQuery(name = "Compagnie.findByCheckIp", query = "SELECT c FROM Compagnie c WHERE c.checkIp = :checkIp")
    , @NamedQuery(name = "Compagnie.findByIp", query = "SELECT c FROM Compagnie c WHERE c.ip = :ip")
    , @NamedQuery(name = "Compagnie.findByOverdue", query = "SELECT c FROM Compagnie c WHERE c.overdue = :overdue")
    , @NamedQuery(name = "Compagnie.findByNumCpte", query = "SELECT c FROM Compagnie c WHERE c.numCpte = :numCpte")
    , @NamedQuery(name = "Compagnie.findByNumCpteCommission", query = "SELECT c FROM Compagnie c WHERE c.numCpteCommission = :numCpteCommission")
    , @NamedQuery(name = "Compagnie.findByNumCpteTva", query = "SELECT c FROM Compagnie c WHERE c.numCpteTva = :numCpteTva")})
public class Compagnie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COM_SEQ")
    @SequenceGenerator(sequenceName = "SEQUENCE_COMPAGNIE", allocationSize = 1, name = "COM_SEQ")
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
    @Column(name = "CODE_PARTENAIRE")
    private String codePartenaire;
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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "NATURE_ACTIVITE")
    private String natureActivite;
    @Column(name = "SEUIL_TRANSACTION")
    private BigInteger seuilTransaction;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TAXE_OPERATION_LOCALE")
    private short taxeOperationLocale;
    @Size(max = 255)
    @Column(name = "LIBELLE_TAXE")
    private String libelleTaxe;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COLLECTER_TAXE")
    private short collecterTaxe;
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
    @JoinColumn(name = "PAYS", referencedColumnName = "PS_CODE")
    @OneToOne(optional = false)
    private Pays pays;
    @JoinColumn(name = "SUCCURSALE", referencedColumnName = "ID")
    @OneToOne(optional = false)
    private Succursale succursale;
    @JoinColumn(name = "DEVISE_COMPENSATION", referencedColumnName = "DEV_CODE")
    @ManyToOne
    private Devise deviseCompensation;
//    @Column(name = "CHECK_IP")
//    private Short checkIp;
//    @Size(max = 255)
//    @Column(name = "IP")
//    private String ip;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "OVERDUE")
    private BigDecimal overdue;
    @Size(max = 255)
    @Column(name = "NUM_CPTE")
    private String numCpte;
    @Size(max = 255)
    @Column(name = "NUM_CPTE_COMMISSION")
    private String numCpteCommission;
    @Size(max = 255)
    @Column(name = "NUM_CPTE_TVA")
    private String numCpteTva;
    @Column(name = "CHECK_CERT")
    private Short checkCert;
    @Size(max = 255)
    @Column(name = "EMAIL_SUPPORT")
    private String emailSupport;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "LOGO")
    private String logo;    
    @Column(name = "MODE_CALCUL_PARTNER")
    private int modeCalculPartner;
    
    
    
    public Compagnie() {
    }

    public int getModeCalculPartner() {
        return modeCalculPartner;
    }

    public void setModeCalculPartner(int modeCalculPartner) {
        this.modeCalculPartner = modeCalculPartner;
    }

    
    public Devise getDeviseCompensation() {
        return deviseCompensation;
    }

    public void setDeviseCompensation(Devise deviseCompensation) {
        this.deviseCompensation = deviseCompensation;
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

    public Compagnie(String codePartenaire, String libelle, String codeCertificat, String adresse, String telephone, String natureActivite, BigInteger seuilTransaction, short taxeOperationLocale, String libelleTaxe, short collecterTaxe, Short statut) {
        this.codePartenaire = codePartenaire;
        this.libelle = libelle;
        this.codeCertificat = codeCertificat;
        this.adresse = adresse;
        this.telephone = telephone;
        this.natureActivite = natureActivite;
        this.seuilTransaction = seuilTransaction;
        this.taxeOperationLocale = taxeOperationLocale;
        this.libelleTaxe = libelleTaxe;
        this.collecterTaxe = collecterTaxe;
        this.statut = statut;
    }

    public Compagnie(Long id) {
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

    public String getNatureActivite() {
        return natureActivite;
    }

    public void setNatureActivite(String natureActivite) {
        this.natureActivite = natureActivite;
    }

    public BigInteger getSeuilTransaction() {
        return seuilTransaction;
    }

    public void setSeuilTransaction(BigInteger seuilTransaction) {
        this.seuilTransaction = seuilTransaction;
    }

    public short getTaxeOperationLocale() {
        return taxeOperationLocale;
    }

    public void setTaxeOperationLocale(short taxeOperationLocale) {
        this.taxeOperationLocale = taxeOperationLocale;
    }

    public String getLibelleTaxe() {
        return libelleTaxe;
    }

    public void setLibelleTaxe(String libelleTaxe) {
        this.libelleTaxe = libelleTaxe;
    }

    public short getCollecterTaxe() {
        return collecterTaxe;
    }

    public void setCollecterTaxe(short collecterTaxe) {
        this.collecterTaxe = collecterTaxe;
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

    public String getNumCpte() {
        return numCpte;
    }

    public void setNumCpte(String numCpte) {
        this.numCpte = numCpte;
    }

    public String getNumCpteCommission() {
        return numCpteCommission;
    }

    public void setNumCpteCommission(String numCpteCommission) {
        this.numCpteCommission = numCpteCommission;
    }

    public String getNumCpteTva() {
        return numCpteTva;
    }

    public void setNumCpteTva(String numCpteTva) {
        this.numCpteTva = numCpteTva;
    }

    public Succursale getSuccursale() {
        return succursale;
    }

    public void setSuccursale(Succursale succursale) {
        this.succursale = succursale;
    }
//
//    public Short getCheckIp() {
//        return checkIp;
//    }
//
//    public void setCheckIp(Short checkIp) {
//        this.checkIp = checkIp;
//    }
//
//    public String getIp() {
//        return ip;
//    }
//
//    public void setIp(String ip) {
//        this.ip = ip;
//    }

    public String getCodePartenaire() {
        return codePartenaire;
    }

    public void setCodePartenaire(String codePartenaire) {
        this.codePartenaire = codePartenaire;
    }

    public Pays getPays() {
        return pays;
    }

    public void setPays(Pays pays) {
        this.pays = pays;
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
        if (!(object instanceof Compagnie)) {
            return false;
        }
        Compagnie other = (Compagnie) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
    
    

    @Override
    public String toString() {
        return "Compagnie{" + "id=" + id + ", codePartenaire=" + codePartenaire + ", libelle=" + libelle + ", codeCertificat=" + codeCertificat + ", adresse=" + adresse + ", telephone=" + telephone + ", natureActivite=" + natureActivite + ", seuilTransaction=" + seuilTransaction + ", taxeOperationLocale=" + taxeOperationLocale + ", libelleTaxe=" + libelleTaxe + ", collecterTaxe=" + collecterTaxe + ", statut=" + statut + ", userCreate=" + userCreate + ", userModif=" + userModif + ", dateCreate=" + dateCreate + ", dateModif=" + dateModif + ", pays=" + pays + ", succursale=" + succursale + '}';
    }

}
