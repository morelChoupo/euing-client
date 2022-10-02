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
 * @author EUC
 */
@Entity
@Table(name = "LISTE_TO_USE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ListeToUse.findAll", query = "SELECT l FROM ListeToUse l")
    , @NamedQuery(name = "ListeToUse.findById", query = "SELECT l FROM ListeToUse l WHERE l.id = :id")
    , @NamedQuery(name = "ListeToUse.findByLibelle", query = "SELECT l FROM ListeToUse l WHERE l.libelle = :libelle")
    , @NamedQuery(name = "ListeToUse.findByUsage", query = "SELECT l FROM ListeToUse l WHERE l.usage = :usage")
    , @NamedQuery(name = "ListeToUse.findByStatut", query = "SELECT l FROM ListeToUse l WHERE l.statut = :statut")
    , @NamedQuery(name = "ListeToUse.findByUsageStatut", query = "SELECT l FROM ListeToUse l WHERE l.usage = :usage and l.statut = :statut")
    , @NamedQuery(name = "ListeToUse.findByUserCreate", query = "SELECT l FROM ListeToUse l WHERE l.userCreate = :userCreate")
    , @NamedQuery(name = "ListeToUse.findByUserModif", query = "SELECT l FROM ListeToUse l WHERE l.userModif = :userModif")
    , @NamedQuery(name = "ListeToUse.findByDateCreate", query = "SELECT l FROM ListeToUse l WHERE l.dateCreate = :dateCreate")
    , @NamedQuery(name = "ListeToUse.findByDateModif", query = "SELECT l FROM ListeToUse l WHERE l.dateModif = :dateModif")
    , @NamedQuery(name = "ListeToUse.checkDoublon", query = "SELECT l FROM ListeToUse l WHERE l.libelle = :libelle AND l.usage = :usage")})

public class ListeToUse implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_LISTE_TO_USE")
    @SequenceGenerator(sequenceName = "SEQ_LISTE_TO_USE", allocationSize = 1, name = "SEQ_LISTE_TO_USE")
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "LIBELLE")
    private String libelle;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "USAGE")
    private String usage;
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
    @Size(max = 255)
    @Column(name = "CODE")
    private String code;

    public ListeToUse() {
    }

    public ListeToUse(Long id) {
        this.id = id;
    }

    public ListeToUse(Long id, String libelle, String usage) {
        this.id = id;
        this.libelle = libelle;
        this.usage = usage;
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

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public Short getStatut() {
        return statut;
    }

    public void setStatut(Short statut) {
        this.statut = statut;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
        if (!(object instanceof ListeToUse)) {
            return false;
        }
        ListeToUse other = (ListeToUse) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ufi.euing.systemeservice.entities.ListeToUse[ id=" + id + " ]";
    }

}
