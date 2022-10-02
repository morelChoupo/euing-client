/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufi.euing.client.entity;

import javax.persistence.*;
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
@Table(name = "TRANSACTION_DOCUMENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TransactionDocument.findAll", query = "SELECT t FROM TransactionDocument t")
    , @NamedQuery(name = "TransactionDocument.findById", query = "SELECT t FROM TransactionDocument t WHERE t.id = :id")
    , @NamedQuery(name = "TransactionDocument.findByNom", query = "SELECT t FROM TransactionDocument t WHERE t.nom = :nom")
    , @NamedQuery(name = "TransactionDocument.findByDateAjout", query = "SELECT t FROM TransactionDocument t WHERE t.dateAjout = :dateAjout")
    , @NamedQuery(name = "TransactionDocument.findByDescription", query = "SELECT t FROM TransactionDocument t WHERE t.description = :description")
    , @NamedQuery(name = "TransactionDocument.findByStatut", query = "SELECT t FROM TransactionDocument t WHERE t.statut = :statut")
    , @NamedQuery(name = "TransactionDocument.findByUserCreate", query = "SELECT t FROM TransactionDocument t WHERE t.userCreate = :userCreate")
    , @NamedQuery(name = "TransactionDocument.findByUserModif", query = "SELECT t FROM TransactionDocument t WHERE t.userModif = :userModif")
    , @NamedQuery(name = "TransactionDocument.findByTrx", query = "SELECT t FROM TransactionDocument t WHERE t.transactionId.transId = :transId and t.statut = 1")
    , @NamedQuery(name = "TransactionDocument.findByDateCreate", query = "SELECT t FROM TransactionDocument t WHERE t.dateCreate = :dateCreate")
    , @NamedQuery(name = "TransactionDocument.findByDateModif", query = "SELECT t FROM TransactionDocument t WHERE t.dateModif = :dateModif")})
public class TransactionDocument implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TRX_DOC_SEQ")
    @SequenceGenerator(sequenceName = "SEQUENCE_TRANSACTION_DOCUMENT", allocationSize = 1, name = "TRX_DOC_SEQ")
    @Basic(optional = false)
//    @NotNull
    @Column(nullable = false, precision = 19, scale = 0)
    private BigDecimal id;
    @Size(max = 255)
    @Column(length = 255)
    private String nom;
    @Column(name = "DATE_AJOUT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAjout;
    @Size(max = 255)
    @Column(length = 255)
    private String description;
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
    @JoinColumn(name = "TRANSACTION_ID", referencedColumnName = "TRANS_ID")
    @ManyToOne
    private TransactionEuing transactionId;
    @JoinColumn(name = "TYPE_FORMULAIRE", referencedColumnName = "ID")
    @ManyToOne
    private TypeFormulaire typeFormulaire;

    public TransactionDocument() {
    }

    public TransactionDocument(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDateAjout() {
        return dateAjout;
    }

    public void setDateAjout(Date dateAjout) {
        this.dateAjout = dateAjout;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public TransactionEuing getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(TransactionEuing transactionId) {
        this.transactionId = transactionId;
    }

    public TypeFormulaire getTypeFormulaire() {
        return typeFormulaire;
    }

    public void setTypeFormulaire(TypeFormulaire typeFormulaire) {
        this.typeFormulaire = typeFormulaire;
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
        if (!(object instanceof TransactionDocument)) {
            return false;
        }
        TransactionDocument other = (TransactionDocument) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.TransactionDocument[ id=" + id + " ]";
    }
    
}
