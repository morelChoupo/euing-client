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
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"BEN_CODE"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Beneficiary.findAll", query = "SELECT b FROM Beneficiary b")
    , @NamedQuery(name = "Beneficiary.searchBeneficiaryBySender", query = "SELECT b FROM SenderBeneficiary s JOIN s.sebBenId b where s.sebSenId.senId = :senId and b.benStatut = 1")
    , @NamedQuery(name = "Beneficiary.searchBeneficiaryBySenderByFirstnameLastname", query = "SELECT b FROM SenderBeneficiary s JOIN s.sebBenId b where s.sebSenId.senId = :senId and b.benStatut = 1  AND ((:benLastname is null or b.benLastname LIKE :benLastname)  and (:benFirstname is null or b.benFirstname LIKE :benFirstname))")
    , @NamedQuery(name = "Beneficiary.findByBenId", query = "SELECT b FROM Beneficiary b WHERE b.benId = :benId")
    , @NamedQuery(name = "Beneficiary.findByBenCode", query = "SELECT b FROM Beneficiary b WHERE b.benCode = :benCode")
    , @NamedQuery(name = "Beneficiary.findByBenFirstname", query = "SELECT b FROM Beneficiary b WHERE b.benFirstname = :benFirstname")
    , @NamedQuery(name = "Beneficiary.findByBenLastname", query = "SELECT b FROM Beneficiary b WHERE b.benLastname = :benLastname")
    , @NamedQuery(name = "Beneficiary.findByBenCountry", query = "SELECT b FROM Beneficiary b WHERE b.benCountry = :benCountry")
    , @NamedQuery(name = "Beneficiary.findByBenNationality", query = "SELECT b FROM Beneficiary b WHERE b.benNationality = :benNationality")
    , @NamedQuery(name = "Beneficiary.findByBenCity", query = "SELECT b FROM Beneficiary b WHERE b.benCity = :benCity")
    , @NamedQuery(name = "Beneficiary.findByBenState", query = "SELECT b FROM Beneficiary b WHERE b.benState = :benState")
    , @NamedQuery(name = "Beneficiary.findByBenPostalCode", query = "SELECT b FROM Beneficiary b WHERE b.benPostalCode = :benPostalCode")
    , @NamedQuery(name = "Beneficiary.findByBenPhoneNumber1", query = "SELECT b FROM Beneficiary b WHERE b.benPhoneNumber1 = :benPhoneNumber1")
    , @NamedQuery(name = "Beneficiary.findByBenPhoneNumber2", query = "SELECT b FROM Beneficiary b WHERE b.benPhoneNumber2 = :benPhoneNumber2")
    , @NamedQuery(name = "Beneficiary.findByBenEmail", query = "SELECT b FROM Beneficiary b WHERE b.benEmail = :benEmail")
    , @NamedQuery(name = "Beneficiary.findByBenDob", query = "SELECT b FROM Beneficiary b WHERE b.benDob = :benDob")
    , @NamedQuery(name = "Beneficiary.findByBenStatut", query = "SELECT b FROM Beneficiary b WHERE b.benStatut = :benStatut")
    , @NamedQuery(name = "Beneficiary.findByBenIdDocumentNumber", query = "SELECT b FROM Beneficiary b WHERE b.benIdDocumentNumber = :benIdDocumentNumber")
    , @NamedQuery(name = "Beneficiary.findByBenIdDocumentImg1", query = "SELECT b FROM Beneficiary b WHERE b.benIdDocumentImg1 = :benIdDocumentImg1")
    , @NamedQuery(name = "Beneficiary.findByBenIdDocumentImg2", query = "SELECT b FROM Beneficiary b WHERE b.benIdDocumentImg2 = :benIdDocumentImg2")
    , @NamedQuery(name = "Beneficiary.findByBenIdDocumentImg3", query = "SELECT b FROM Beneficiary b WHERE b.benIdDocumentImg3 = :benIdDocumentImg3")
    , @NamedQuery(name = "Beneficiary.findByBenIdDocumentExpDate", query = "SELECT b FROM Beneficiary b WHERE b.benIdDocumentExpDate = :benIdDocumentExpDate")
    , @NamedQuery(name = "Beneficiary.findByBenIdDocumentDelDate", query = "SELECT b FROM Beneficiary b WHERE b.benIdDocumentDelDate = :benIdDocumentDelDate")
    , @NamedQuery(name = "Beneficiary.findByBenGender", query = "SELECT b FROM Beneficiary b WHERE b.benGender = :benGender")
    , @NamedQuery(name = "Beneficiary.findByBenOccupation", query = "SELECT b FROM Beneficiary b WHERE b.benOccupation = :benOccupation")
    , @NamedQuery(name = "Beneficiary.findByBenComment", query = "SELECT b FROM Beneficiary b WHERE b.benComment = :benComment")
    , @NamedQuery(name = "Beneficiary.findByBenDateCreate", query = "SELECT b FROM Beneficiary b WHERE b.benDateCreate = :benDateCreate")
    , @NamedQuery(name = "Beneficiary.findByBenDateModif", query = "SELECT b FROM Beneficiary b WHERE b.benDateModif = :benDateModif")
    , @NamedQuery(name = "Beneficiary.findByBenAddress", query = "SELECT b FROM Beneficiary b WHERE b.benAddress = :benAddress")
    , @NamedQuery(name = "Beneficiary.findByBenAddress2", query = "SELECT b FROM Beneficiary b WHERE b.benAddress2 = :benAddress2")})
public class Beneficiary implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "BEN_ID", nullable = false, precision = 19, scale = 0)
    private BigDecimal benId;
    @Column(name = "BEN_CODE")
    private Integer benCode;
    @Size(max = 255)
    @Column(name = "BEN_FIRSTNAME", length = 255)
    private String benFirstname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "BEN_LASTNAME", nullable = false, length = 255)
    private String benLastname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "BEN_COUNTRY", nullable = false, length = 255)
    private String benCountry;
    @Size(max = 255)
    @Column(name = "BEN_NATIONALITY", length = 255)
    private String benNationality;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "BEN_CITY", nullable = false, length = 255)
    private String benCity;
    @Size(max = 50)
    @Column(name = "BEN_STATE", length = 50)
    private String benState;
    @Size(max = 255)
    @Column(name = "BEN_POSTAL_CODE", length = 255)
    private String benPostalCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "BEN_PHONE_NUMBER1", nullable = false, length = 50)
    private String benPhoneNumber1;
    @Size(max = 50)
    @Column(name = "BEN_PHONE_NUMBER2", length = 50)
    private String benPhoneNumber2;
    @Size(max = 255)
    @Column(name = "BEN_EMAIL", length = 255)
    private String benEmail;
//    @Basic(optional = false)
//    @NotNull
    @Column(name = "BEN_DOB")
    @Temporal(TemporalType.TIMESTAMP)
    private Date benDob;
    @Basic(optional = false)
    @NotNull
    @Column(name = "BEN_STATUT", nullable = false)
    private short benStatut;
    @Size(max = 255)
    @Column(name = "BEN_ID_DOCUMENT_NUMBER", length = 255)
    private String benIdDocumentNumber;
    @Size(max = 255)
    @Column(name = "BEN_ID_DOCUMENT_IMG1", length = 255)
    private String benIdDocumentImg1;
    @Size(max = 255)
    @Column(name = "BEN_ID_DOCUMENT_IMG2", length = 255)
    private String benIdDocumentImg2;
    @Size(max = 255)
    @Column(name = "BEN_ID_DOCUMENT_IMG3", length = 255)
    private String benIdDocumentImg3;
    @Column(name = "BEN_ID_DOCUMENT_EXP_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date benIdDocumentExpDate;
    @Column(name = "BEN_ID_DOCUMENT_DEL_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date benIdDocumentDelDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "BEN_GENDER", nullable = false, length = 50)
    private String benGender;
    @Size(max = 255)
    @Column(name = "BEN_OCCUPATION", length = 255)
    private String benOccupation;
    @Size(max = 255)
    @Column(name = "BEN_COMMENT", length = 255)
    private String benComment;
    @Column(name = "BEN_DATE_CREATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date benDateCreate;
    @Column(name = "BEN_DATE_MODIF")
    @Temporal(TemporalType.TIMESTAMP)
    private Date benDateModif;
    @Size(max = 255)
    @Column(name = "BEN_ADDRESS", length = 255)
    private String benAddress;
    @Size(max = 255)
    @Column(name = "BEN_ADDRESS2", length = 255)
    private String benAddress2;
    @JoinColumn(name = "BEN_ID_DOCUMENT_ISSUE_COUNTRY", referencedColumnName = "PS_CODE")
    @ManyToOne
    private Pays benIdDocumentIssueCountry;
    @JoinColumn(name = "BEN_COUNTRY_ID", referencedColumnName = "PS_CODE", nullable = false)
    @ManyToOne(optional = false)
    private Pays benCountryId;
    @JoinColumn(name = "BEN_NATIONALITY_ID", referencedColumnName = "PS_CODE")
    @ManyToOne
    private Pays benNationalityId;
    @JoinColumn(name = "BEN_ID_DOCUMENT_TYPE", referencedColumnName = "TPI_CODE")
    @ManyToOne
    private TypePieceIdentite benIdDocumentType;
    @JoinColumn(name = "BEN_CITY_ID", referencedColumnName = "VI_ID", nullable = false)
    @ManyToOne(optional = false)
    private Ville benCityId;
//    @OneToMany(mappedBy = "sebBenId")
//    private List<SenderBeneficiary> senderBeneficiaryList;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "transBenId")
//    private List<TransactionEuing> transactionEuingList;

    public Beneficiary() {
    }

    public Beneficiary(BigDecimal benId) {
        this.benId = benId;
    }

    public Beneficiary(BigDecimal benId, String benLastname, String benCountry, String benCity, String benPhoneNumber1, Date benDob, short benStatut, String benGender) {
        this.benId = benId;
        this.benLastname = benLastname;
        this.benCountry = benCountry;
        this.benCity = benCity;
        this.benPhoneNumber1 = benPhoneNumber1;
        this.benDob = benDob;
        this.benStatut = benStatut;
        this.benGender = benGender;
    }

    public BigDecimal getBenId() {
        return benId;
    }

    public void setBenId(BigDecimal benId) {
        this.benId = benId;
    }

    public Integer getBenCode() {
        return benCode;
    }

    public void setBenCode(Integer benCode) {
        this.benCode = benCode;
    }

    public String getBenFirstname() {
        return benFirstname;
    }

    public void setBenFirstname(String benFirstname) {
        this.benFirstname = benFirstname;
    }

    public String getBenLastname() {
        return benLastname;
    }

    public void setBenLastname(String benLastname) {
        this.benLastname = benLastname;
    }

    public String getBenCountry() {
        return benCountry;
    }

    public void setBenCountry(String benCountry) {
        this.benCountry = benCountry;
    }

    public String getBenNationality() {
        return benNationality;
    }

    public void setBenNationality(String benNationality) {
        this.benNationality = benNationality;
    }

    public String getBenCity() {
        return benCity;
    }

    public void setBenCity(String benCity) {
        this.benCity = benCity;
    }

    public String getBenState() {
        return benState;
    }

    public void setBenState(String benState) {
        this.benState = benState;
    }

    public String getBenPostalCode() {
        return benPostalCode;
    }

    public void setBenPostalCode(String benPostalCode) {
        this.benPostalCode = benPostalCode;
    }

    public String getBenPhoneNumber1() {
        return benPhoneNumber1;
    }

    public void setBenPhoneNumber1(String benPhoneNumber1) {
        this.benPhoneNumber1 = benPhoneNumber1;
    }

    public String getBenPhoneNumber2() {
        return benPhoneNumber2;
    }

    public void setBenPhoneNumber2(String benPhoneNumber2) {
        this.benPhoneNumber2 = benPhoneNumber2;
    }

    public String getBenEmail() {
        return benEmail;
    }

    public void setBenEmail(String benEmail) {
        this.benEmail = benEmail;
    }

    public Date getBenDob() {
        return benDob;
    }

    public void setBenDob(Date benDob) {
        this.benDob = benDob;
    }

    public short getBenStatut() {
        return benStatut;
    }

    public void setBenStatut(short benStatut) {
        this.benStatut = benStatut;
    }

    public String getBenIdDocumentNumber() {
        return benIdDocumentNumber;
    }

    public void setBenIdDocumentNumber(String benIdDocumentNumber) {
        this.benIdDocumentNumber = benIdDocumentNumber;
    }

    public String getBenIdDocumentImg1() {
        return benIdDocumentImg1;
    }

    public void setBenIdDocumentImg1(String benIdDocumentImg1) {
        this.benIdDocumentImg1 = benIdDocumentImg1;
    }

    public String getBenIdDocumentImg2() {
        return benIdDocumentImg2;
    }

    public void setBenIdDocumentImg2(String benIdDocumentImg2) {
        this.benIdDocumentImg2 = benIdDocumentImg2;
    }

    public String getBenIdDocumentImg3() {
        return benIdDocumentImg3;
    }

    public void setBenIdDocumentImg3(String benIdDocumentImg3) {
        this.benIdDocumentImg3 = benIdDocumentImg3;
    }

    public Date getBenIdDocumentExpDate() {
        return benIdDocumentExpDate;
    }

    public void setBenIdDocumentExpDate(Date benIdDocumentExpDate) {
        this.benIdDocumentExpDate = benIdDocumentExpDate;
    }

    public Date getBenIdDocumentDelDate() {
        return benIdDocumentDelDate;
    }

    public void setBenIdDocumentDelDate(Date benIdDocumentDelDate) {
        this.benIdDocumentDelDate = benIdDocumentDelDate;
    }

    public String getBenGender() {
        return benGender;
    }

    public void setBenGender(String benGender) {
        this.benGender = benGender;
    }

    public String getBenOccupation() {
        return benOccupation;
    }

    public void setBenOccupation(String benOccupation) {
        this.benOccupation = benOccupation;
    }

    public String getBenComment() {
        return benComment;
    }

    public void setBenComment(String benComment) {
        this.benComment = benComment;
    }

    public Date getBenDateCreate() {
        return benDateCreate;
    }

    public void setBenDateCreate(Date benDateCreate) {
        this.benDateCreate = benDateCreate;
    }

    public Date getBenDateModif() {
        return benDateModif;
    }

    public void setBenDateModif(Date benDateModif) {
        this.benDateModif = benDateModif;
    }

    public String getBenAddress() {
        return benAddress;
    }

    public void setBenAddress(String benAddress) {
        this.benAddress = benAddress;
    }

    public String getBenAddress2() {
        return benAddress2;
    }

    public void setBenAddress2(String benAddress2) {
        this.benAddress2 = benAddress2;
    }

    public Pays getBenIdDocumentIssueCountry() {
        return benIdDocumentIssueCountry;
    }

    public void setBenIdDocumentIssueCountry(Pays benIdDocumentIssueCountry) {
        this.benIdDocumentIssueCountry = benIdDocumentIssueCountry;
    }

    public Pays getBenCountryId() {
        return benCountryId;
    }

    public void setBenCountryId(Pays benCountryId) {
        this.benCountryId = benCountryId;
    }

    public Pays getBenNationalityId() {
        return benNationalityId;
    }

    public void setBenNationalityId(Pays benNationalityId) {
        this.benNationalityId = benNationalityId;
    }

    public TypePieceIdentite getBenIdDocumentType() {
        return benIdDocumentType;
    }

    public void setBenIdDocumentType(TypePieceIdentite benIdDocumentType) {
        this.benIdDocumentType = benIdDocumentType;
    }

    public Ville getBenCityId() {
        return benCityId;
    }

    public void setBenCityId(Ville benCityId) {
        this.benCityId = benCityId;
    }

//    @XmlTransient
//    public List<SenderBeneficiary> getSenderBeneficiaryList() {
//        return senderBeneficiaryList;
//    }
//
//    public void setSenderBeneficiaryList(List<SenderBeneficiary> senderBeneficiaryList) {
//        this.senderBeneficiaryList = senderBeneficiaryList;
//    }

//    @XmlTransient
//    public List<TransactionEuing> getTransactionEuingList() {
//        return transactionEuingList;
//    }
//
//    public void setTransactionEuingList(List<TransactionEuing> transactionEuingList) {
//        this.transactionEuingList = transactionEuingList;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (benId != null ? benId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Beneficiary)) {
            return false;
        }
        Beneficiary other = (Beneficiary) object;
        if ((this.benId == null && other.benId != null) || (this.benId != null && !this.benId.equals(other.benId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Beneficiary{" + "benId=" + benId + ", benCode=" + benCode + ", benFirstname=" + benFirstname + ", benLastname=" + benLastname + ", benCountry=" + benCountry + ", benNationality=" + benNationality + ", benCity=" + benCity + ", benState=" + benState + ", benPostalCode=" + benPostalCode + ", benPhoneNumber1=" + benPhoneNumber1 + ", benPhoneNumber2=" + benPhoneNumber2 + ", benEmail=" + benEmail + ", benDob=" + benDob + ", benStatut=" + benStatut + ", benIdDocumentNumber=" + benIdDocumentNumber + ", benIdDocumentImg1=" + benIdDocumentImg1 + ", benIdDocumentImg2=" + benIdDocumentImg2 + ", benIdDocumentImg3=" + benIdDocumentImg3 + ", benIdDocumentExpDate=" + benIdDocumentExpDate + ", benIdDocumentDelDate=" + benIdDocumentDelDate + ", benGender=" + benGender + ", benOccupation=" + benOccupation + ", benComment=" + benComment + ", benDateCreate=" + benDateCreate + ", benDateModif=" + benDateModif + ", benAddress=" + benAddress + ", benAddress2=" + benAddress2 + ", benIdDocumentIssueCountry=" + benIdDocumentIssueCountry + ", benCountryId=" + benCountryId + ", benNationalityId=" + benNationalityId + ", benIdDocumentType=" + benIdDocumentType + ", benCityId=" + benCityId + '}';
    }
    
}
