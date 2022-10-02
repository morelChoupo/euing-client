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
 * @author Cash-Xpress-User
 */
@Entity
@Table(name = "TYPE_UNITE_ORGANISATIONNELLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TypeUo.findAll", query = "SELECT t FROM TypeUo t")
    , @NamedQuery(name = "TypeUo.findByTuoCode", query = "SELECT t FROM TypeUo t WHERE t.tuoCode = :tuoCode")
    , @NamedQuery(name = "TypeUo.findByTuoDesc", query = "SELECT t FROM TypeUo t WHERE t.tuoDesc = :tuoDesc")
    , @NamedQuery(name = "TypeUo.findByTuoLibelle", query = "SELECT t FROM TypeUo t WHERE t.tuoLibelle = :tuoLibelle")
    , @NamedQuery(name = "TypeUo.findByStatut", query = "SELECT t FROM TypeUo t WHERE t.statut = :statut")
    , @NamedQuery(name = "TypeUo.findByUserCreate", query = "SELECT t FROM TypeUo t WHERE t.userCreate = :userCreate")
    , @NamedQuery(name = "TypeUo.findByUserModif", query = "SELECT t FROM TypeUo t WHERE t.userModif = :userModif")
    , @NamedQuery(name = "TypeUo.findByDateCreate", query = "SELECT t FROM TypeUo t WHERE t.dateCreate = :dateCreate")
    , @NamedQuery(name = "TypeUo.findByDateModif", query = "SELECT t FROM TypeUo t WHERE t.dateModif = :dateModif")})
public class TypeUo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "TUO_CODE", nullable = false, length = 128)
    private String tuoCode;
    @Size(max = 128)
    @Column(name = "TUO_DESC", length = 128)
    private String tuoDesc;
    @Size(max = 128)
    @Column(name = "TUO_LIBELLE", length = 128)
    private String tuoLibelle;
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
//    @OneToMany(mappedBy = "tuoParentCode")
//    private List<TypeUo> typeUoList;
    @JoinColumn(name = "TUO_PARENT_CODE", referencedColumnName = "TUO_CODE")
    @ManyToOne
    private TypeUo tuoParentCode;

    public TypeUo() {
    }

    public TypeUo(String tuoCode) {
        this.tuoCode = tuoCode;
    }

    public String getTuoCode() {
        return tuoCode;
    }

    public void setTuoCode(String tuoCode) {
        this.tuoCode = tuoCode;
    }

    public String getTuoDesc() {
        return tuoDesc;
    }

    public void setTuoDesc(String tuoDesc) {
        this.tuoDesc = tuoDesc;
    }

    public String getTuoLibelle() {
        return tuoLibelle;
    }

    public void setTuoLibelle(String tuoLibelle) {
        this.tuoLibelle = tuoLibelle;
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

//    @XmlTransient
//    public List<TypeUo> getTypeUoList() {
//        return typeUoList;
//    }
//
//    public void setTypeUoList(List<TypeUo> typeUoList) {
//        this.typeUoList = typeUoList;
//    }

    public TypeUo getTuoParentCode() {
        return tuoParentCode;
    }

    public void setTuoParentCode(TypeUo tuoParentCode) {
        this.tuoParentCode = tuoParentCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tuoCode != null ? tuoCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TypeUo)) {
            return false;
        }
        TypeUo other = (TypeUo) object;
        if ((this.tuoCode == null && other.tuoCode != null) || (this.tuoCode != null && !this.tuoCode.equals(other.tuoCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ufi.euing.userservice.entities.TypeUo[ tuoCode=" + tuoCode + " ]";
    }
    
}
