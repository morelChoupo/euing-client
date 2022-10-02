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
@Table(name = "TYPE_PIECE_IDENTITE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TypePieceIdentite.findAll", query = "SELECT t FROM TypePieceIdentite t")
    , @NamedQuery(name = "TypePieceIdentite.findByTpiCode", query = "SELECT t FROM TypePieceIdentite t WHERE t.tpiCode = :tpiCode")
    , @NamedQuery(name = "TypePieceIdentite.findByTpiLibelle", query = "SELECT t FROM TypePieceIdentite t WHERE t.tpiLibelle = :tpiLibelle")
    , @NamedQuery(name = "TypePieceIdentite.findByStatut", query = "SELECT t FROM TypePieceIdentite t WHERE t.statut = :statut")
    , @NamedQuery(name = "TypePieceIdentite.findByUserCreate", query = "SELECT t FROM TypePieceIdentite t WHERE t.userCreate = :userCreate")
    , @NamedQuery(name = "TypePieceIdentite.findByUserModif", query = "SELECT t FROM TypePieceIdentite t WHERE t.userModif = :userModif")
    , @NamedQuery(name = "TypePieceIdentite.findByDateCreate", query = "SELECT t FROM TypePieceIdentite t WHERE t.dateCreate = :dateCreate")
    , @NamedQuery(name = "TypePieceIdentite.findByDateModif", query = "SELECT t FROM TypePieceIdentite t WHERE t.dateModif = :dateModif")})
public class TypePieceIdentite implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "TPI_CODE", nullable = false, length = 4)
    private String tpiCode;
    @Size(max = 32)
    @Column(name = "TPI_LIBELLE", length = 32)
    private String tpiLibelle;
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
//    @OneToMany(mappedBy = "blcTpiCode")
//    private List<BlackList> blackListList;

    public TypePieceIdentite() {
    }

    public TypePieceIdentite(String tpiCode) {
        this.tpiCode = tpiCode;
    }

    public String getTpiCode() {
        return tpiCode;
    }

    public void setTpiCode(String tpiCode) {
        this.tpiCode = tpiCode;
    }

    public String getTpiLibelle() {
        return tpiLibelle;
    }

    public void setTpiLibelle(String tpiLibelle) {
        this.tpiLibelle = tpiLibelle;
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
//    public List<BlackList> getBlackListList() {
//        return blackListList;
//    }
//
//    public void setBlackListList(List<BlackList> blackListList) {
//        this.blackListList = blackListList;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tpiCode != null ? tpiCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TypePieceIdentite)) {
            return false;
        }
        TypePieceIdentite other = (TypePieceIdentite) object;
        if ((this.tpiCode == null && other.tpiCode != null) || (this.tpiCode != null && !this.tpiCode.equals(other.tpiCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ufi.euing.client.entity.TypePieceIdentite[ tpiCode=" + tpiCode + " ]";
    }
    
}
