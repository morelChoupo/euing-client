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

@Entity
@Table(name = "COMPAGNIE_TPI", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"COMPAGNIE", "TPI_CODE"})})
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "CompagnieTpi.findAll", query = "SELECT c FROM CompagnieTpi c")
        , @NamedQuery(name = "CompagnieTpi.findByStatut", query = "SELECT c FROM CompagnieTpi c WHERE c.statut = :statut")
        , @NamedQuery(name = "CompagnieTpi.findByUserCreate", query = "SELECT c FROM CompagnieTpi c WHERE c.userCreate = :userCreate")
        , @NamedQuery(name = "CompagnieTpi.findByUserModif", query = "SELECT c FROM CompagnieTpi c WHERE c.userModif = :userModif")
        , @NamedQuery(name = "CompagnieTpi.findByDateCreate", query = "SELECT c FROM CompagnieTpi c WHERE c.dateCreate = :dateCreate")
        , @NamedQuery(name = "CompagnieTpi.findByDateModif", query = "SELECT c FROM CompagnieTpi c WHERE c.dateModif = :dateModif")
        , @NamedQuery(name = "CompagnieTpi.findById", query = "SELECT c FROM CompagnieTpi c WHERE c.id = :id")
        , @NamedQuery(name = "CompagnieTpi.findByCompagnie", query = "SELECT c FROM CompagnieTpi c WHERE c.compagnie.id = :compagnie")
//    , @NamedQuery(name = "CompagnieTpi.findByCompagnie", query = "SELECT t FROM CompagnieTpi c JOIN c.tpiCode t  WHERE t.statut = 1 AND c.compagnie.id = :compagnie")
        , @NamedQuery(name = "CompagnieTpi.checkDoublon", query = "SELECT c FROM CompagnieTpi c WHERE c.compagnie.id = :compagnie AND c.tpiCode.tpiCode = :tpiCode")
        , @NamedQuery(name = "CompagnieTpi.findByTpiCode", query = "SELECT c FROM CompagnieTpi c WHERE c.tpiCode.tpiCode = :tpiCode")})
public class CompagnieTpi implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_COMPAGNIE_TPI")
    @SequenceGenerator(sequenceName = "SEQ_COMPAGNIE_TPI", allocationSize = 1, name = "SEQ_COMPAGNIE_TPI")
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID", nullable = false)
    private Long id;
    @Column(name = "STATUT")
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
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATE_MODIF", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModif;

    //    @Basic(optional = false)
//    @NotNull
//    @Column(name = "COMPAGNIE", nullable = false)
    @JoinColumn(name = "COMPAGNIE", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Compagnie compagnie;
    //    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 20)
//    @Column(name = "TPI_CODE", nullable = false, length = 20)
    @JoinColumn(name = "TPI_CODE", referencedColumnName = "TPI_CODE")
    @ManyToOne(optional = false)
    private TypePieceIdentite tpiCode;

    @Column(name = "MIN_LENGTH")
    private Integer minLength;
    @Column(name = "MAX_LENGTH")
    private Integer maxLength;

    public CompagnieTpi() {
    }

    public CompagnieTpi(Long id) {
        this.id = id;
    }

//    public CompagnieTpi(Long id, Date dateModif, long compagnie, String tpiCode) {
//        this.id = id;
//        this.dateModif = dateModif;
//        this.compagnie = compagnie;
//        this.tpiCode = tpiCode;
//    }

    public Integer getMinLength() {
        return minLength;
    }

    public void setMinLength(Integer minLength) {
        this.minLength = minLength;
    }

    public Integer getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(Integer maxLength) {
        this.maxLength = maxLength;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Compagnie getCompagnie() {
        return compagnie;
    }

    public void setCompagnie(Compagnie compagnie) {
        this.compagnie = compagnie;
    }

    public TypePieceIdentite getTpiCode() {
        return tpiCode;
    }

    public void setTpiCode(TypePieceIdentite tpiCode) {
        this.tpiCode = tpiCode;
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
        if (!(object instanceof CompagnieTpi)) {
            return false;
        }
        CompagnieTpi other = (CompagnieTpi) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ufi.euing.entiteservice.entities.CompagnieTpi[ id=" + id + " ]";
    }

}