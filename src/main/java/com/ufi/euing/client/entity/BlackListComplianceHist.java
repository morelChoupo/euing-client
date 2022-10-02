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
 * @author MS GRoUp
 */
@Entity
@Table(name = "BLACK_LIST_COMPLIANCE_HIST")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BlackListComplianceHist.findAll", query = "SELECT b FROM BlackListComplianceHist b")
    , @NamedQuery(name = "BlackListComplianceHist.findByBlchId", query = "SELECT b FROM BlackListComplianceHist b WHERE b.blchId = :blchId")
    , @NamedQuery(name = "BlackListComplianceHist.findByBlchAdresse", query = "SELECT b FROM BlackListComplianceHist b WHERE b.blchAdresse = :blchAdresse")
    , @NamedQuery(name = "BlackListComplianceHist.findByBlchNom", query = "SELECT b FROM BlackListComplianceHist b WHERE b.blchNom = :blchNom")
    , @NamedQuery(name = "BlackListComplianceHist.findByBlchNumeroPiece", query = "SELECT b FROM BlackListComplianceHist b WHERE b.blchNumeroPiece = :blchNumeroPiece")
    , @NamedQuery(name = "BlackListComplianceHist.findByBlchPrenom", query = "SELECT b FROM BlackListComplianceHist b WHERE b.blchPrenom = :blchPrenom")
    , @NamedQuery(name = "BlackListComplianceHist.findByBlchTelephone", query = "SELECT b FROM BlackListComplianceHist b WHERE b.blchTelephone = :blchTelephone")
    , @NamedQuery(name = "BlackListComplianceHist.findByBlchComment", query = "SELECT b FROM BlackListComplianceHist b WHERE b.blchComment = :blchComment")
    , @NamedQuery(name = "BlackListComplianceHist.findByUserCreate", query = "SELECT b FROM BlackListComplianceHist b WHERE b.userCreate = :userCreate")
    , @NamedQuery(name = "BlackListComplianceHist.findByUserModif", query = "SELECT b FROM BlackListComplianceHist b WHERE b.userModif = :userModif")
    , @NamedQuery(name = "BlackListComplianceHist.findByDateCreate", query = "SELECT b FROM BlackListComplianceHist b WHERE b.dateCreate = :dateCreate")
    , @NamedQuery(name = "BlackListComplianceHist.findByDateModif", query = "SELECT b FROM BlackListComplianceHist b WHERE b.dateModif = :dateModif")})
public class BlackListComplianceHist implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BLACK_HIST_SEQ")
    @SequenceGenerator(sequenceName = "S_BLACK_LIST_COMPLIANCE_HIST", allocationSize = 1, name = "BLACK_HIST_SEQ")
    @Column(name = "BLCH_ID", nullable = false, precision = 19, scale = 0)
    private BigDecimal blchId;
    @Size(max = 255)
    @Column(name = "BLCH_ADRESSE", length = 255)
    private String blchAdresse;
    @Size(max = 255)
    @Column(name = "BLCH_NOM", length = 255)
    private String blchNom;
    @Size(max = 255)
    @Column(name = "BLCH_NUMERO_PIECE", length = 255)
    private String blchNumeroPiece;
    @Size(max = 255)
    @Column(name = "BLCH_PRENOM", length = 255)
    private String blchPrenom;
    @Size(max = 255)
    @Column(name = "BLCH_TELEPHONE", length = 255)
    private String blchTelephone;
    @Size(max = 255)
    @Column(name = "BLCH_COMMENT", length = 255)
    private String blchComment;
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
    @JoinColumn(name = "BLCH_PS_CODE", referencedColumnName = "PS_CODE")
    @ManyToOne
    private Pays blcPsCode;
    @JoinColumn(name = "BLCH_TPI_CODE", referencedColumnName = "TPI_CODE")
    @ManyToOne
    private TypePieceIdentite blcTpiCode;
    
    
    public BlackListComplianceHist() {
    }

    public BlackListComplianceHist(BigDecimal blchId) {
        this.blchId = blchId;
    }

    public BigDecimal getBlchId() {
        return blchId;
    }

    public void setBlchId(BigDecimal blchId) {
        this.blchId = blchId;
    }

    public String getBlchAdresse() {
        return blchAdresse;
    }

    public void setBlchAdresse(String blchAdresse) {
        this.blchAdresse = blchAdresse;
    }

    public String getBlchNom() {
        return blchNom;
    }

    public void setBlchNom(String blchNom) {
        this.blchNom = blchNom;
    }

    public String getBlchNumeroPiece() {
        return blchNumeroPiece;
    }

    public void setBlchNumeroPiece(String blchNumeroPiece) {
        this.blchNumeroPiece = blchNumeroPiece;
    }

    public String getBlchPrenom() {
        return blchPrenom;
    }

    public void setBlchPrenom(String blchPrenom) {
        this.blchPrenom = blchPrenom;
    }

    public String getBlchTelephone() {
        return blchTelephone;
    }

    public void setBlchTelephone(String blchTelephone) {
        this.blchTelephone = blchTelephone;
    }

    public String getBlchComment() {
        return blchComment;
    }

    public void setBlchComment(String blchComment) {
        this.blchComment = blchComment;
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

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (blchId != null ? blchId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BlackListComplianceHist)) {
            return false;
        }
        BlackListComplianceHist other = (BlackListComplianceHist) object;
        if ((this.blchId == null && other.blchId != null) || (this.blchId != null && !this.blchId.equals(other.blchId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BlackListComplianceHist{" + "blchId=" + blchId + ", blchAdresse=" + blchAdresse + ", blchNom=" + blchNom + ", blchNumeroPiece=" + blchNumeroPiece + ", blchPrenom=" + blchPrenom + ", blchTelephone=" + blchTelephone + ", blchComment=" + blchComment + ", userCreate=" + userCreate + ", userModif=" + userModif + ", dateCreate=" + dateCreate + ", dateModif=" + dateModif + ", blcPsCode=" + blcPsCode + ", blcTpiCode=" + blcTpiCode + '}';
    }

    
    
}
