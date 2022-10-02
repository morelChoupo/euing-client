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
@Table(name = "BLACK_LIST_COMPLIANCE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BlackList.findAll", query = "SELECT b FROM BlackList b")
    , @NamedQuery(name = "BlackList.findByBlcId", query = "SELECT b FROM BlackList b WHERE b.blcId = :blcId")
    , @NamedQuery(name = "BlackList.findByBlcAdresse", query = "SELECT b FROM BlackList b WHERE b.blcAdresse = :blcAdresse")
    , @NamedQuery(name = "BlackList.findByBlcNom", query = "SELECT b FROM BlackList b WHERE b.blcNom = :blcNom")
    , @NamedQuery(name = "BlackList.findByBlcNumeroPiece", query = "SELECT b FROM BlackList b WHERE b.blcNumeroPiece = :blcNumeroPiece")
    , @NamedQuery(name = "BlackList.findByBlcPrenom", query = "SELECT b FROM BlackList b WHERE b.blcPrenom = :blcPrenom")
    , @NamedQuery(name = "BlackList.findByBlcTelephone", query = "SELECT b FROM BlackList b WHERE b.blcTelephone = :blcTelephone")
    , @NamedQuery(name = "BlackList.findByStatut", query = "SELECT b FROM BlackList b WHERE b.statut = :statut")
    , @NamedQuery(name = "BlackList.findByUserCreate", query = "SELECT b FROM BlackList b WHERE b.userCreate = :userCreate")
    , @NamedQuery(name = "BlackList.findByUserModif", query = "SELECT b FROM BlackList b WHERE b.userModif = :userModif")
    , @NamedQuery(name = "BlackList.findByDateCreate", query = "SELECT b FROM BlackList b WHERE b.dateCreate = :dateCreate")
    , @NamedQuery(name = "BlackList.checkDoublon", query = "SELECT b FROM BlackList b WHERE b.blcNom = :blcNom and b.blcPrenom = :blcPrenom ")
    , @NamedQuery(name = "BlackList.findByDateModif", query = "SELECT b FROM BlackList b WHERE b.dateModif = :dateModif")})
public class BlackList implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BLACK_SEQ")
    @SequenceGenerator(sequenceName = "S_BLACK_LIST_COMPLIANCE", allocationSize = 1, name = "BLACK_SEQ")
    @Column(name = "BLC_ID", nullable = false, precision = 19, scale = 0)
    private BigDecimal blcId;
    @Size(max = 255)
    @Column(name = "BLC_ADRESSE", length = 255)
    private String blcAdresse;
    @Size(max = 255)
    @Column(name = "BLC_NOM", length = 255)
    private String blcNom;
    @Size(max = 255)
    @Column(name = "BLC_NUMERO_PIECE", length = 255)
    private String blcNumeroPiece;
    @Size(max = 255)
    @Column(name = "BLC_PRENOM", length = 255)
    private String blcPrenom;
    @Size(max = 255)
    @Column(name = "BLC_TELEPHONE", length = 255)
    private String blcTelephone;
    @Size(max = 255)
    @Column(name = "BLC_COMMENT", length = 255)
    private String blcComment;
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
    @JoinColumn(name = "BLC_PS_CODE", referencedColumnName = "PS_CODE")
    @ManyToOne
    private Pays blcPsCode;
    @JoinColumn(name = "BLC_TPI_CODE", referencedColumnName = "TPI_CODE")
    @ManyToOne
    private TypePieceIdentite blcTpiCode;

    public BlackList() {
    }

    public BlackList(BigDecimal blcId) {
        this.blcId = blcId;
    }

    public BigDecimal getBlcId() {
        return blcId;
    }

    public void setBlcId(BigDecimal blcId) {
        this.blcId = blcId;
    }

    public String getBlcAdresse() {
        return blcAdresse;
    }

    public void setBlcAdresse(String blcAdresse) {
        this.blcAdresse = blcAdresse;
    }

    public String getBlcNom() {
        return blcNom;
    }

    public void setBlcNom(String blcNom) {
        this.blcNom = blcNom;
    }

    public String getBlcNumeroPiece() {
        return blcNumeroPiece;
    }

    public void setBlcNumeroPiece(String blcNumeroPiece) {
        this.blcNumeroPiece = blcNumeroPiece;
    }

    public String getBlcPrenom() {
        return blcPrenom;
    }

    public void setBlcPrenom(String blcPrenom) {
        this.blcPrenom = blcPrenom;
    }

    public String getBlcTelephone() {
        return blcTelephone;
    }

    public void setBlcTelephone(String blcTelephone) {
        this.blcTelephone = blcTelephone;
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

    public Pays getBlcPsCode() {
        return blcPsCode;
    }

    public void setBlcPsCode(Pays blcPsCode) {
        this.blcPsCode = blcPsCode;
    }

    public TypePieceIdentite getBlcTpiCode() {
        return blcTpiCode;
    }

    public void setBlcTpiCode(TypePieceIdentite blcTpiCode) {
        this.blcTpiCode = blcTpiCode;
    }

    public String getBlcComment() {
        return blcComment;
    }

    public void setBlcComment(String blcComment) {
        this.blcComment = blcComment;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (blcId != null ? blcId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BlackList)) {
            return false;
        }
        BlackList other = (BlackList) object;
        if ((this.blcId == null && other.blcId != null) || (this.blcId != null && !this.blcId.equals(other.blcId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BlackList{" + "blcId=" + blcId + ", blcAdresse=" + blcAdresse + ", blcNom=" + blcNom + ", blcNumeroPiece=" + blcNumeroPiece + ", blcPrenom=" + blcPrenom + ", blcTelephone=" + blcTelephone + ", statut=" + statut + ", userCreate=" + userCreate + ", userModif=" + userModif + ", dateCreate=" + dateCreate + ", dateModif=" + dateModif + ", blcPsCode=" + blcPsCode + ", blcTpiCode=" + blcTpiCode + '}';
    }

    
}
