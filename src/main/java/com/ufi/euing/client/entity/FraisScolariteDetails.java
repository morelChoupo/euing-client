/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufi.euing.client.entity;



import com.ufi.euing.client.integration.minesec.model.MinesecFeesDetail;
import com.ufi.euing.client.integration.minesec.model.MinesecStudentDetail;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author UFI
 */
@Entity
@Table(name = "FRAIS_SCOLARITE_DETAILS", catalog = "", schema = "EUING")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FraisScolariteDetails.findAll", query = "SELECT f FROM FraisScolariteDetails f")
    , @NamedQuery(name = "FraisScolariteDetails.findById", query = "SELECT f FROM FraisScolariteDetails f WHERE f.id = :id")
    , @NamedQuery(name = "FraisScolariteDetails.findByCompagnieCode", query = "SELECT f FROM FraisScolariteDetails f WHERE f.compagnieCode = :compagnieCode")
    , @NamedQuery(name = "FraisScolariteDetails.findByCompagnieLibelle", query = "SELECT f FROM FraisScolariteDetails f WHERE f.compagnieLibelle = :compagnieLibelle")
    , @NamedQuery(name = "FraisScolariteDetails.findByEtablissementCode", query = "SELECT f FROM FraisScolariteDetails f WHERE f.etablissementCode = :etablissementCode")
    , @NamedQuery(name = "FraisScolariteDetails.findByEtablissementLibelle", query = "SELECT f FROM FraisScolariteDetails f WHERE f.etablissementLibelle = :etablissementLibelle")
    , @NamedQuery(name = "FraisScolariteDetails.findByEleveCode", query = "SELECT f FROM FraisScolariteDetails f WHERE f.eleveCode = :eleveCode")
    , @NamedQuery(name = "FraisScolariteDetails.findByEleveNom", query = "SELECT f FROM FraisScolariteDetails f WHERE f.eleveNom = :eleveNom")
    , @NamedQuery(name = "FraisScolariteDetails.findByEleveSexe", query = "SELECT f FROM FraisScolariteDetails f WHERE f.eleveSexe = :eleveSexe")
    , @NamedQuery(name = "FraisScolariteDetails.findByEleveDateNaissance", query = "SELECT f FROM FraisScolariteDetails f WHERE f.eleveDateNaissance = :eleveDateNaissance")
    , @NamedQuery(name = "FraisScolariteDetails.findByEleveTelephone", query = "SELECT f FROM FraisScolariteDetails f WHERE f.eleveTelephone = :eleveTelephone")
    , @NamedQuery(name = "FraisScolariteDetails.findByEleveEmail", query = "SELECT f FROM FraisScolariteDetails f WHERE f.eleveEmail = :eleveEmail")
    , @NamedQuery(name = "FraisScolariteDetails.findByClasseCode", query = "SELECT f FROM FraisScolariteDetails f WHERE f.classeCode = :classeCode")
    , @NamedQuery(name = "FraisScolariteDetails.findByClasseLibelle", query = "SELECT f FROM FraisScolariteDetails f WHERE f.classeLibelle = :classeLibelle")
    , @NamedQuery(name = "FraisScolariteDetails.findByClasseOptionId", query = "SELECT f FROM FraisScolariteDetails f WHERE f.classeOptionId = :classeOptionId")
    , @NamedQuery(name = "FraisScolariteDetails.findByClasseOptionName", query = "SELECT f FROM FraisScolariteDetails f WHERE f.classeOptionName = :classeOptionName")
    , @NamedQuery(name = "FraisScolariteDetails.findByPayeurNom", query = "SELECT f FROM FraisScolariteDetails f WHERE f.payeurNom = :payeurNom")
    , @NamedQuery(name = "FraisScolariteDetails.findByPayeurTelephone", query = "SELECT f FROM FraisScolariteDetails f WHERE f.payeurTelephone = :payeurTelephone")
    , @NamedQuery(name = "FraisScolariteDetails.findByPayeurEmail", query = "SELECT f FROM FraisScolariteDetails f WHERE f.payeurEmail = :payeurEmail")
    , @NamedQuery(name = "FraisScolariteDetails.findByAnneeAcademique", query = "SELECT f FROM FraisScolariteDetails f WHERE f.anneeAcademique = :anneeAcademique")
    , @NamedQuery(name = "FraisScolariteDetails.findByFeeId", query = "SELECT f FROM FraisScolariteDetails f WHERE f.feeId = :feeId")
    , @NamedQuery(name = "FraisScolariteDetails.findByFeeName", query = "SELECT f FROM FraisScolariteDetails f WHERE f.feeName = :feeName")
    , @NamedQuery(name = "FraisScolariteDetails.findByFeeAmount", query = "SELECT f FROM FraisScolariteDetails f WHERE f.feeAmount = :feeAmount")
    , @NamedQuery(name = "FraisScolariteDetails.findByFeePartiality", query = "SELECT f FROM FraisScolariteDetails f WHERE f.feePartiality = :feePartiality")
    , @NamedQuery(name = "FraisScolariteDetails.findByIsExported", query = "SELECT f FROM FraisScolariteDetails f WHERE f.isExported = :isExported")
    , @NamedQuery(name = "FraisScolariteDetails.findByUserCreate", query = "SELECT f FROM FraisScolariteDetails f WHERE f.userCreate = :userCreate")
    , @NamedQuery(name = "FraisScolariteDetails.findByDateCreate", query = "SELECT f FROM FraisScolariteDetails f WHERE f.dateCreate = :dateCreate")
    , @NamedQuery(name = "FraisScolariteDetails.findByUserModif", query = "SELECT f FROM FraisScolariteDetails f WHERE f.userModif = :userModif")
    , @NamedQuery(name = "FraisScolariteDetails.findByDateModif", query = "SELECT f FROM FraisScolariteDetails f WHERE f.dateModif = :dateModif")
    , @NamedQuery(name = "FraisScolariteDetails.findByCompagnie", query = "SELECT f FROM FraisScolariteDetails f WHERE f.compagnie = :compagnie")
    , @NamedQuery(name = "FraisScolariteDetails.findByTransId", query = "SELECT f FROM FraisScolariteDetails f WHERE f.transId.id = :transId")
    , @NamedQuery(name = "FraisScolariteDetails.findDuplicatedPayment", query = "SELECT f FROM FraisScolariteDetails f WHERE f.etablissementCode = :etablissementCode AND f.eleveNom = :eleveNom AND f.classeCode = :classeCode AND f.feeId = :feeId")
    , @NamedQuery(name = "FraisScolariteDetails.findMultiCriteria", query = "SELECT f FROM FraisScolariteDetails f WHERE "
            + "lower(f.compagnieCode) LIKE :compagnieCode "
            + "AND (f.dateCreate BETWEEN :startDate AND :endDate) "
            + "AND lower(f.etablissementLibelle) LIKE :etablissementLibelle "
            + "AND lower(f.eleveNom) LIKE :eleveNom "
            + "AND f.anneeAcademique LIKE :anneeAcademique"
            + "")
    , @NamedQuery(name = "FraisScolariteDetails.findMultiCriteriaWithAmount", query = "SELECT f FROM FraisScolariteDetails f WHERE "
            + "lower(f.compagnieCode) LIKE :compagnieCode "
            + "AND (f.dateCreate BETWEEN :startDate AND :endDate) "
            + "AND lower(f.etablissementLibelle) LIKE :etablissementLibelle "
            + "AND lower(f.eleveNom) LIKE :eleveNom "
            + "AND f.feeAmount = :feeAmount "
            + "AND f.anneeAcademique LIKE :anneeAcademique"
            + "")})
public class FraisScolariteDetails implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FRAIS_SCOLARITE_DETAILS_SEQ")
    @SequenceGenerator(sequenceName = "SEQUENCE_FRAIS_SCOLARITE", allocationSize = 1, name = "FRAIS_SCOLARITE_DETAILS_SEQ")
    @Column(name = "ID", nullable = false, precision = 19, scale = 0)
    private BigDecimal id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "COMPAGNIE_CODE", nullable = false, length = 255)
    private String compagnieCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "COMPAGNIE_LIBELLE", nullable = false, length = 255)
    private String compagnieLibelle;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "ETABLISSEMENT_CODE", nullable = false, length = 255)
    private String etablissementCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "ETABLISSEMENT_LIBELLE", nullable = false, length = 255)
    private String etablissementLibelle;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "ELEVE_CODE", nullable = false, length = 255)
    private String eleveCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "ELEVE_NOM", nullable = false, length = 255)
    private String eleveNom;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "ELEVE_SEXE", nullable = false, length = 255)
    private String eleveSexe;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "ELEVE_DATE_NAISSANCE", nullable = false, length = 255)
    private String eleveDateNaissance;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "ELEVE_TELEPHONE", nullable = false, length = 255)
    private String eleveTelephone;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "ELEVE_EMAIL", nullable = false, length = 255)
    private String eleveEmail;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "CLASSE_CODE", nullable = false, length = 255)
    private String classeCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "CLASSE_LIBELLE", nullable = false, length = 255)
    private String classeLibelle;
    @Size(min = 1, max = 255)
    @Column(name = "CLASSE_OPTION_ID", nullable = false, length = 255)
    private String classeOptionId;
    @Size(max = 255)
    @Column(name = "CLASSE_OPTION_NAME", length = 255)
    private String classeOptionName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "PAYEUR_NOM", nullable = false, length = 255)
    private String payeurNom;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "PAYEUR_TELEPHONE", nullable = false, length = 255)
    private String payeurTelephone;
    @Size(max = 255)
    @Column(name = "PAYEUR_EMAIL", length = 255)
    private String payeurEmail;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "ANNEE_ACADEMIQUE", nullable = false, length = 255)
    private String anneeAcademique;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "FEE_ID", nullable = false, length = 255)
    private String feeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "FEE_NAME", nullable = false, length = 255)
    private String feeName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FEE_AMOUNT", nullable = false)
    private BigDecimal feeAmount;
    @Column(name = "FEE_PARTIALITY")
    private BigDecimal feePartiality;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IS_EXPORTED", nullable = false)
    private String isExported;
    @Size(max = 255)
    @Column(name = "USER_CREATE", length = 255)
    private String userCreate;
    @Column(name = "DATE_CREATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreate;
    @Size(max = 255)
    @Column(name = "USER_MODIF", length = 255)
    private String userModif;
    @Column(name = "DATE_MODIF")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModif;
    @Size(max = 255)
    @Column(name = "REGION_CODE", length = 255)
    private String regionCode;
    @Size(max = 255)
    @Column(name = "REGION_LIBELLE", length = 255)
    private String regionLibelle;
    @Size(max = 255)
    @Column(name = "DIVISION", length = 255)
    private String division;
    @Size(max = 255)
    @Column(name = "SUBDIVISION", length = 255)
    private String subdivision;
    @Size(max = 255)
    @Column(name = "CITY", length = 255)
    private String city;
    @JoinColumn(name = "COMPAGNIE", referencedColumnName = "ID")
    @ManyToOne
    private Compagnie compagnie;
    @JoinColumn(name = "TRANS_ID", referencedColumnName = "ID")
    @ManyToOne
    private TransactionBill transId;

    public FraisScolariteDetails() {
    }

    public FraisScolariteDetails(BigDecimal id) {
        this.id = id;
    }

    public FraisScolariteDetails(BigDecimal id, String compagnieCode, String compagnieLibelle, String etablissementCode, String etablissementLibelle, String eleveCode, 
            String eleveNom, String eleveSexe, String eleveDateNaissance, String eleveTelephone, String eleveEmail, String classeCode, String classeLibelle, 
            String payeurNom, String payeurTelephone, String anneeAcademique, String feeId, String feeName, BigDecimal feeAmount, String isExported, Compagnie compagnie,
            TransactionBill transId) {
        this.id = id;
        this.compagnieCode = compagnieCode;
        this.compagnieLibelle = compagnieLibelle;
        this.etablissementCode = etablissementCode;
        this.etablissementLibelle = etablissementLibelle;
        this.eleveCode = eleveCode;
        this.eleveNom = eleveNom;
        this.eleveSexe = eleveSexe;
        this.eleveDateNaissance = eleveDateNaissance;
        this.eleveTelephone = eleveTelephone;
        this.eleveEmail = eleveEmail;
        this.classeCode = classeCode;
        this.classeLibelle = classeLibelle;
        this.payeurNom = payeurNom;
        this.payeurTelephone = payeurTelephone;
        this.anneeAcademique = anneeAcademique;
        this.feeId = feeId;
        this.feeName = feeName;
        this.feeAmount = feeAmount;
        this.isExported = isExported;
        this.compagnie = compagnie;
        this.transId = transId;
    }

    public FraisScolariteDetails(MinesecStudentDetail studentDetail, String payeurNom, String payeurTelephone, String payeurEmail, String anneeAcademique,
                                 MinesecFeesDetail feesDetail, String user, Compagnie compagnie, TransactionBill transId) {
        this.etablissementCode = studentDetail.getSchool_code();
        this.etablissementLibelle = studentDetail.getSchool_name();
        this.eleveCode = studentDetail.getStudent_regnumber();
        this.eleveNom = studentDetail.getStudent_name();
        this.eleveSexe = studentDetail.getStudent_gender();
        this.eleveDateNaissance = studentDetail.getStudent_birthdate();
        this.eleveTelephone = studentDetail.getStudent_phone();
        this.eleveEmail = studentDetail.getStudent_email();
        this.classeCode = studentDetail.getStudent_class_id();
        this.classeLibelle = studentDetail.getStudent_class_name();
        this.classeOptionId = studentDetail.getStudent_option_id();
        this.classeOptionName = studentDetail.getStudent_option_name();
        this.payeurNom = payeurNom;
        this.payeurTelephone = payeurTelephone;
        this.payeurEmail = payeurEmail;
        this.anneeAcademique = anneeAcademique;
        this.feeId = feesDetail.getFee_id();
        this.feeName = feesDetail.getFee_name();
        this.feeAmount = BigDecimal.valueOf(Long.valueOf(feesDetail.getFee_amount()));
        this.feePartiality = BigDecimal.valueOf(Long.valueOf(feesDetail.getPartiality()));
        this.compagnie = compagnie;
        this.transId = transId;
        this.compagnieCode = compagnie.getCodePartenaire();
        this.compagnieLibelle = compagnie.getLibelle();
        this.userCreate = user;
        this.dateCreate = new Date();
        this.userModif = user;
        this.dateModif = new Date();
        this.isExported = "0";
        this.regionCode = studentDetail.getRegion_code();
        this.regionLibelle = studentDetail.getRegion_name();
        this.division = studentDetail.getDivision();
        this.subdivision = studentDetail.getSubdivision();
        this.city = studentDetail.getCity();
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getCompagnieCode() {
        return compagnieCode;
    }

    public void setCompagnieCode(String compagnieCode) {
        this.compagnieCode = compagnieCode;
    }

    public String getCompagnieLibelle() {
        return compagnieLibelle;
    }

    public void setCompagnieLibelle(String compagnieLibelle) {
        this.compagnieLibelle = compagnieLibelle;
    }

    public String getEtablissementCode() {
        return etablissementCode;
    }

    public void setEtablissementCode(String etablissementCode) {
        this.etablissementCode = etablissementCode;
    }

    public String getEtablissementLibelle() {
        return etablissementLibelle;
    }

    public void setEtablissementLibelle(String etablissementLibelle) {
        this.etablissementLibelle = etablissementLibelle;
    }

    public String getEleveCode() {
        return eleveCode;
    }

    public void setEleveCode(String eleveCode) {
        this.eleveCode = eleveCode;
    }

    public String getEleveNom() {
        return eleveNom;
    }

    public void setEleveNom(String eleveNom) {
        this.eleveNom = eleveNom;
    }

    public String getEleveSexe() {
        return eleveSexe;
    }

    public void setEleveSexe(String eleveSexe) {
        this.eleveSexe = eleveSexe;
    }

    public String getEleveDateNaissance() {
        return eleveDateNaissance;
    }

    public void setEleveDateNaissance(String eleveDateNaissance) {
        this.eleveDateNaissance = eleveDateNaissance;
    }

    public String getEleveTelephone() {
        return eleveTelephone;
    }

    public void setEleveTelephone(String eleveTelephone) {
        this.eleveTelephone = eleveTelephone;
    }

    public String getEleveEmail() {
        return eleveEmail;
    }

    public void setEleveEmail(String eleveEmail) {
        this.eleveEmail = eleveEmail;
    }

    public String getClasseCode() {
        return classeCode;
    }

    public void setClasseCode(String classeCode) {
        this.classeCode = classeCode;
    }

    public String getClasseLibelle() {
        return classeLibelle;
    }

    public void setClasseLibelle(String classeLibelle) {
        this.classeLibelle = classeLibelle;
    }

    public String getClasseOptionId() {
        return classeOptionId;
    }

    public void setClasseOptionId(String classeOptionId) {
        this.classeOptionId = classeOptionId;
    }

    public String getClasseOptionName() {
        return classeOptionName;
    }

    public void setClasseOptionName(String classeOptionName) {
        this.classeOptionName = classeOptionName;
    }

    public String getPayeurNom() {
        return payeurNom;
    }

    public void setPayeurNom(String payeurNom) {
        this.payeurNom = payeurNom;
    }

    public String getPayeurTelephone() {
        return payeurTelephone;
    }

    public void setPayeurTelephone(String payeurTelephone) {
        this.payeurTelephone = payeurTelephone;
    }

    public String getPayeurEmail() {
        return payeurEmail;
    }

    public void setPayeurEmail(String payeurEmail) {
        this.payeurEmail = payeurEmail;
    }

    public String getAnneeAcademique() {
        return anneeAcademique;
    }

    public void setAnneeAcademique(String anneeAcademique) {
        this.anneeAcademique = anneeAcademique;
    }

    public String getFeeId() {
        return feeId;
    }

    public void setFeeId(String feeId) {
        this.feeId = feeId;
    }

    public String getFeeName() {
        return feeName;
    }

    public void setFeeName(String feeName) {
        this.feeName = feeName;
    }

    public BigDecimal getFeeAmount() {
        return feeAmount;
    }

    public void setFeeAmount(BigDecimal feeAmount) {
        this.feeAmount = feeAmount;
    }

    public BigDecimal getFeePartiality() {
        return feePartiality;
    }

    public void setFeePartiality(BigDecimal feePartiality) {
        this.feePartiality = feePartiality;
    }

    public String getIsExported() {
        return isExported;
    }

    public void setIsExported(String isExported) {
        this.isExported = isExported;
    }

    public String getUserCreate() {
        return userCreate;
    }

    public void setUserCreate(String userCreate) {
        this.userCreate = userCreate;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getUserModif() {
        return userModif;
    }

    public void setUserModif(String userModif) {
        this.userModif = userModif;
    }

    public Date getDateModif() {
        return dateModif;
    }

    public void setDateModif(Date dateModif) {
        this.dateModif = dateModif;
    }

    public Compagnie getCompagnie() {
        return compagnie;
    }

    public void setCompagnie(Compagnie compagnie) {
        this.compagnie = compagnie;
    }

    public TransactionBill getTransaction() {
        return transId;
    }

    public void setTransaction(TransactionBill transId) {
        this.transId = transId;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getRegionLibelle() {
        return regionLibelle;
    }

    public void setRegionLibelle(String regionLibelle) {
        this.regionLibelle = regionLibelle;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getSubdivision() {
        return subdivision;
    }

    public void setSubdivision(String subdivision) {
        this.subdivision = subdivision;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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
        if (!(object instanceof FraisScolariteDetails)) {
            return false;
        }
        FraisScolariteDetails other = (FraisScolariteDetails) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "FraisScolariteDetails{" + "id=" + id + ", compagnieCode=" + compagnieCode + ", compagnieLibelle=" + compagnieLibelle + ", etablissementCode=" + etablissementCode + ", etablissementLibelle=" + etablissementLibelle + ", eleveCode=" + eleveCode + ", eleveNom=" + eleveNom + ", eleveSexe=" + eleveSexe + ", eleveDateNaissance=" + eleveDateNaissance + ", eleveTelephone=" + eleveTelephone + ", eleveEmail=" + eleveEmail + ", classeCode=" + classeCode + ", classeLibelle=" + classeLibelle + ", classeOptionId=" + classeOptionId + ", classeOptionName=" + classeOptionName + ", payeurNom=" + payeurNom + ", payeurTelephone=" + payeurTelephone + ", payeurEmail=" + payeurEmail + ", anneeAcademique=" + anneeAcademique + ", feeId=" + feeId + ", feeName=" + feeName + ", isExported=" + isExported + ", userCreate=" + userCreate + ", dateCreate=" + dateCreate + ", userModif=" + userModif + ", dateModif=" + dateModif + ", regionCode=" + regionCode + ", regionLibelle=" + regionLibelle + ", division=" + division + ", subdivision=" + subdivision + ", city=" + city + ", compagnie=" + compagnie + ", transId=" + transId + '}';
    }

}
