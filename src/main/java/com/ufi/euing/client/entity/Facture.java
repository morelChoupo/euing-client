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
 * @author EU
 */
@Entity
@Table(name = "FACTURE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Facture.findAll", query = "SELECT f FROM Facture f")
    , @NamedQuery(name = "Facture.findById", query = "SELECT f FROM Facture f WHERE f.id = :id")
    , @NamedQuery(name = "Facture.findByCodeCompagnie", query = "SELECT f FROM Facture f WHERE f.codeCompagnie = :codeCompagnie")
    , @NamedQuery(name = "Facture.findByDateCreate", query = "SELECT f FROM Facture f WHERE f.dateCreate = :dateCreate")
    , @NamedQuery(name = "Facture.findByDateLimite", query = "SELECT f FROM Facture f WHERE f.dateLimite = :dateLimite")
    , @NamedQuery(name = "Facture.findByDateModif", query = "SELECT f FROM Facture f WHERE f.dateModif = :dateModif")
    , @NamedQuery(name = "Facture.findByFact1", query = "SELECT f FROM Facture f WHERE f.fact1 = :fact1")
    , @NamedQuery(name = "Facture.findByFact2", query = "SELECT f FROM Facture f WHERE f.fact2 = :fact2")
    , @NamedQuery(name = "Facture.findByFact3", query = "SELECT f FROM Facture f WHERE f.fact3 = :fact3")
    , @NamedQuery(name = "Facture.findByFact4", query = "SELECT f FROM Facture f WHERE f.fact4 = :fact4")
    , @NamedQuery(name = "Facture.findByFact5", query = "SELECT f FROM Facture f WHERE f.fact5 = :fact5")
    , @NamedQuery(name = "Facture.findByFactureLibelle", query = "SELECT f FROM Facture f WHERE f.factureLibelle = :factureLibelle")
    , @NamedQuery(name = "Facture.findByFactureNumber", query = "SELECT f FROM Facture f WHERE f.factureNumber = :factureNumber")
    , @NamedQuery(name = "Facture.findByMontant", query = "SELECT f FROM Facture f WHERE f.montant = :montant")
    , @NamedQuery(name = "Facture.findByNomAbonne", query = "SELECT f FROM Facture f WHERE f.nomAbonne = :nomAbonne")
    , @NamedQuery(name = "Facture.findByNumeroAbonne", query = "SELECT f FROM Facture f WHERE f.numeroAbonne = :numeroAbonne")
    , @NamedQuery(name = "Facture.findByStatusFacture", query = "SELECT f FROM Facture f WHERE f.statusFacture = :statusFacture")
    , @NamedQuery(name = "Facture.findByUserCreate", query = "SELECT f FROM Facture f WHERE f.userCreate = :userCreate")
    , @NamedQuery(name = "Facture.findByUserModif", query = "SELECT f FROM Facture f WHERE f.userModif = :userModif")
    , @NamedQuery(name = "Facture.findByMinToPay", query = "SELECT f FROM Facture f WHERE f.minToPay = :minToPay")})
public class Facture implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FACTURE_SEQ")
    @SequenceGenerator(sequenceName = "SEQUENCE_FACTURE", allocationSize = 1, name = "FACTURE_SEQ")
    @Column(name = "ID", nullable = false, precision = 19, scale = 0)
    private BigDecimal id;
    @Size(max = 255)
    @Column(name = "CODE_COMPAGNIE", length = 255)
    private String codeCompagnie;
    @Column(name = "DATE_CREATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreate;
    @Column(name = "DATE_LIMITE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateLimite;
    @Column(name = "DATE_MODIF")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModif;
    @Size(max = 255)
    @Column(name = "FACT1", length = 255)
    private String fact1;
    @Size(max = 255)
    @Column(name = "FACT2", length = 255)
    private String fact2;
    @Size(max = 255)
    @Column(name = "FACT3", length = 255)
    private String fact3;
    @Size(max = 255)
    @Column(name = "FACT4", length = 255)
    private String fact4;
    @Size(max = 255)
    @Column(name = "FACT5", length = 255)
    private String fact5;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "FACTURE_LIBELLE", nullable = false, length = 255)
    private String factureLibelle;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "FACTURE_NUMBER", nullable = false, length = 255)
    private String factureNumber;
    @Column(name = "MONTANT")
    private BigDecimal montant;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "NOM_ABONNE", nullable = false, length = 255)
    private String nomAbonne;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "NUMERO_ABONNE", nullable = false, length = 255)
    private String numeroAbonne;
    @Column(name = "STATUS_FACTURE")
    @Size(min = 1, max = 1)
    private String statusFacture;
    @Size(max = 255)
    @Column(name = "USER_CREATE", length = 255)
    private String userCreate;
    @Size(max = 255)
    @Column(name = "USER_MODIF", length = 255)
    private String userModif;
    @Column(name = "MIN_TO_PAY")
    private BigDecimal minToPay;
    @JoinColumn(name = "COMPAGNIE", referencedColumnName = "ID")
    @ManyToOne
    private Compagnie compagnie;

    @Transient
    private BigDecimal amountToSent;
    @Transient
    private BigDecimal amountToPaid;
    @Transient
    private BigDecimal exchangeRate;
    @Transient
    private BigDecimal otherfees;
    @Transient
    private BigDecimal fees;
    @Transient
    private BigDecimal totalToPaid;
    @Transient
    private Integer res;
    @Transient
    private String resMessage;

    public Facture() {
    }

    public Facture(BigDecimal id) {
        this.id = id;
    }

    public Facture(BigDecimal id, String factureLibelle, String factureNumber, String nomAbonne, String numeroAbonne) {
        this.id = id;
        this.factureLibelle = factureLibelle;
        this.factureNumber = factureNumber;
        this.nomAbonne = nomAbonne;
        this.numeroAbonne = numeroAbonne;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getCodeCompagnie() {
        return codeCompagnie;
    }

    public void setCodeCompagnie(String codeCompagnie) {
        this.codeCompagnie = codeCompagnie;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Date getDateLimite() {
        return dateLimite;
    }

    public void setDateLimite(Date dateLimite) {
        this.dateLimite = dateLimite;
    }

    public Date getDateModif() {
        return dateModif;
    }

    public void setDateModif(Date dateModif) {
        this.dateModif = dateModif;
    }

    public String getFact1() {
        return fact1;
    }

    public void setFact1(String fact1) {
        this.fact1 = fact1;
    }

    public String getFact2() {
        return fact2;
    }

    public void setFact2(String fact2) {
        this.fact2 = fact2;
    }

    public String getFact3() {
        return fact3;
    }

    public void setFact3(String fact3) {
        this.fact3 = fact3;
    }

    public String getFact4() {
        return fact4;
    }

    public void setFact4(String fact4) {
        this.fact4 = fact4;
    }

    public String getFact5() {
        return fact5;
    }

    public void setFact5(String fact5) {
        this.fact5 = fact5;
    }

    public String getFactureLibelle() {
        return factureLibelle;
    }

    public void setFactureLibelle(String factureLibelle) {
        this.factureLibelle = factureLibelle;
    }

    public String getFactureNumber() {
        return factureNumber;
    }

    public void setFactureNumber(String factureNumber) {
        this.factureNumber = factureNumber;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public String getNomAbonne() {
        return nomAbonne;
    }

    public void setNomAbonne(String nomAbonne) {
        this.nomAbonne = nomAbonne;
    }

    public String getNumeroAbonne() {
        return numeroAbonne;
    }

    public void setNumeroAbonne(String numeroAbonne) {
        this.numeroAbonne = numeroAbonne;
    }

    public String getStatusFacture() {
        return statusFacture;
    }

    public void setStatusFacture(String statusFacture) {
        this.statusFacture = statusFacture;
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

    public BigDecimal getMinToPay() {
        return minToPay;
    }

    public void setMinToPay(BigDecimal minToPay) {
        this.minToPay = minToPay;
    }

    public Compagnie getCompagnie() {
        return compagnie;
    }

    public void setCompagnie(Compagnie compagnie) {
        this.compagnie = compagnie;
    }

    public BigDecimal getAmountToSent() {
        return amountToSent;
    }

    public void setAmountToSent(BigDecimal amountToSent) {
        this.amountToSent = amountToSent;
    }

    public BigDecimal getAmountToPaid() {
        return amountToPaid;
    }

    public void setAmountToPaid(BigDecimal amountToPaid) {
        this.amountToPaid = amountToPaid;
    }

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public BigDecimal getOtherfees() {
        return otherfees;
    }

    public void setOtherfees(BigDecimal otherfees) {
        this.otherfees = otherfees;
    }

    public BigDecimal getFees() {
        return fees;
    }

    public void setFees(BigDecimal fees) {
        this.fees = fees;
    }

    public BigDecimal getTotalToPaid() {
        return totalToPaid;
    }

    public void setTotalToPaid(BigDecimal totalToPaid) {
        this.totalToPaid = totalToPaid;
    }

    public Integer getRes() {
        return res;
    }

    public void setRes(Integer res) {
        this.res = res;
    }

    public String getResMessage() {
        return resMessage;
    }

    public void setResMessage(String resMessage) {
        this.resMessage = resMessage;
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
        if (!(object instanceof Facture)) {
            return false;
        }
        Facture other = (Facture) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Facture{" + "id=" + id + ", codeCompagnie=" + codeCompagnie + ", dateCreate=" + dateCreate + ", dateLimite=" + dateLimite + ", dateModif=" + dateModif + ", fact1=" + fact1 + ", fact2=" + fact2 + ", fact3=" + fact3 + ", fact4=" + fact4 + ", fact5=" + fact5 + ", factureLibelle=" + factureLibelle + ", factureNumber=" + factureNumber + ", montant=" + montant + ", nomAbonne=" + nomAbonne + ", numeroAbonne=" + numeroAbonne + ", statusFacture=" + statusFacture + ", userCreate=" + userCreate + ", userModif=" + userModif + ", minToPay=" + minToPay + ", compagnie=" + compagnie + ", amountToSent=" + amountToSent + ", amountToPaid=" + amountToPaid + ", exchangeRate=" + exchangeRate + ", otherfees=" + otherfees + ", fees=" + fees + ", totalToPaid=" + totalToPaid + ", res=" + res + ", resMessage=" + resMessage + '}';
    }

}
