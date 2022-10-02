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
@Table(name = "TYPE_FORMULAIRE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TypeFormulaire.findAll", query = "SELECT t FROM TypeFormulaire t")
    , @NamedQuery(name = "TypeFormulaire.findById", query = "SELECT t FROM TypeFormulaire t WHERE t.id = :id")
    , @NamedQuery(name = "TypeFormulaire.findByNom", query = "SELECT t FROM TypeFormulaire t WHERE t.nom = :nom")
    , @NamedQuery(name = "TypeFormulaire.findByDescription", query = "SELECT t FROM TypeFormulaire t WHERE t.description = :description")
    , @NamedQuery(name = "TypeFormulaire.findByStatut", query = "SELECT t FROM TypeFormulaire t WHERE t.statut = :statut")
    , @NamedQuery(name = "TypeFormulaire.findByUserCreate", query = "SELECT t FROM TypeFormulaire t WHERE t.userCreate = :userCreate")
    , @NamedQuery(name = "TypeFormulaire.findByUserModif", query = "SELECT t FROM TypeFormulaire t WHERE t.userModif = :userModif")
    , @NamedQuery(name = "TypeFormulaire.findByDateCreate", query = "SELECT t FROM TypeFormulaire t WHERE t.dateCreate = :dateCreate")
    , @NamedQuery(name = "TypeFormulaire.findByDateModif", query = "SELECT t FROM TypeFormulaire t WHERE t.dateModif = :dateModif")})
public class TypeFormulaire implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE_TYPE_FORMULAIRE")
    @SequenceGenerator(sequenceName = "SEQUENCE_TYPE_FORMULAIRE", allocationSize = 1, name = "SEQUENCE_TYPE_FORMULAIRE")
    @NotNull
    @Column(nullable = false, precision = 19, scale = 0)
    private BigDecimal id;
    @Size(max = 255)
    @Column(length = 255)
    private String nom;
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

    public TypeFormulaire() {
    }

    public TypeFormulaire(BigDecimal id) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TypeFormulaire)) {
            return false;
        }
        TypeFormulaire other = (TypeFormulaire) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ufi.euing.client.entity.TypeFormulaire[ id=" + id + " ]";
    }
    
}
