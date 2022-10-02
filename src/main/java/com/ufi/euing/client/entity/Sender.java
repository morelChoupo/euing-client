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
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Cash-Xpress-User
 */
@Entity
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"SEN_CODE"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sender.findAll", query = "SELECT s FROM Sender s")
    , @NamedQuery(name = "Sender.findBySenId", query = "SELECT s FROM Sender s WHERE s.senId = :senId")
    , @NamedQuery(name = "Sender.findBySenCode", query = "SELECT s FROM Sender s WHERE s.senCode = :senCode")
    , @NamedQuery(name = "Sender.findBySenFirstname", query = "SELECT s FROM Sender s WHERE s.senFirstname = :senFirstname")
    , @NamedQuery(name = "Sender.findBySenLastname", query = "SELECT s FROM Sender s WHERE s.senLastname = :senLastname")
    , @NamedQuery(name = "Sender.findBySenCodeFirstnameLastnameStatut", query = "SELECT s FROM Sender s WHERE s.senStatut = 1 AND (:senCode = 0 OR s.senCode = :senCode) AND (lower(s.senFirstname) LIKE lower(concat('%', :senFirstname,'%')) AND lower(s.senLastname) LIKE lower(concat('%', :senLastname,'%')) )")
    , @NamedQuery(name = "Sender.findBySenCodeFirstnameLastnamePhoneNumberStatut", query = "SELECT s FROM Sender s WHERE s.senStatut = 1 AND (:senCode = 0 OR s.senCode = :senCode) AND (lower(s.senFirstname) LIKE lower(concat('%', :senFirstname,'%')) AND lower(s.senLastname) LIKE lower(concat('%', :senLastname,'%')) AND lower(s.senPhoneNumber1) LIKE lower(concat('%', :senPhoneNumber,'%')) )")
    , @NamedQuery(name = "Sender.findBySenCodeFirstnameLastname", query = "SELECT s FROM Sender s WHERE (:senCode = 0 OR s.senCode = :senCode) AND (lower(s.senFirstname) LIKE lower(concat('%', :senFirstname,'%')) AND lower(s.senLastname) LIKE lower(concat('%', :senLastname,'%')))")
    , @NamedQuery(name = "Sender.findBySenCodeFirstnameLastnamePhoneNumber", query = "SELECT s FROM Sender s WHERE (:senCode = 0 OR s.senCode = :senCode) AND (lower(s.senFirstname) LIKE lower(concat('%', :senFirstname,'%')) AND lower(s.senLastname) LIKE lower(concat('%', :senLastname,'%')) AND lower(s.senPhoneNumber1) LIKE lower(concat('%', :senPhoneNumber,'%')) )")
    , @NamedQuery(name = "Sender.findBySenCountry", query = "SELECT s FROM Sender s WHERE s.senCountry = :senCountry")
    , @NamedQuery(name = "Sender.findBySenNationality", query = "SELECT s FROM Sender s WHERE s.senNationality = :senNationality")
    , @NamedQuery(name = "Sender.findBySenCity", query = "SELECT s FROM Sender s WHERE s.senCity = :senCity")
    , @NamedQuery(name = "Sender.findBySenState", query = "SELECT s FROM Sender s WHERE s.senState = :senState")
    , @NamedQuery(name = "Sender.findBySenPostalCode", query = "SELECT s FROM Sender s WHERE s.senPostalCode = :senPostalCode")
    , @NamedQuery(name = "Sender.findBySenPhoneNumber1", query = "SELECT s FROM Sender s WHERE s.senPhoneNumber1 = :senPhoneNumber1")
    , @NamedQuery(name = "Sender.findBySenPhoneNumber2", query = "SELECT s FROM Sender s WHERE s.senPhoneNumber2 = :senPhoneNumber2")
    , @NamedQuery(name = "Sender.findBySenEmail", query = "SELECT s FROM Sender s WHERE s.senEmail = :senEmail")
    , @NamedQuery(name = "Sender.findBySenDob", query = "SELECT s FROM Sender s WHERE s.senDob = :senDob")
    , @NamedQuery(name = "Sender.findBySenStatut", query = "SELECT s FROM Sender s WHERE s.senStatut = :senStatut")
    , @NamedQuery(name = "Sender.findBySenIdDocumentNumber", query = "SELECT s FROM Sender s WHERE s.senIdDocumentNumber = :senIdDocumentNumber")
    , @NamedQuery(name = "Sender.findBySenIdDocumentImg1", query = "SELECT s FROM Sender s WHERE s.senIdDocumentImg1 = :senIdDocumentImg1")
    , @NamedQuery(name = "Sender.findBySenIdDocumentImg2", query = "SELECT s FROM Sender s WHERE s.senIdDocumentImg2 = :senIdDocumentImg2")
    , @NamedQuery(name = "Sender.findBySenIdDocumentImg3", query = "SELECT s FROM Sender s WHERE s.senIdDocumentImg3 = :senIdDocumentImg3")
    , @NamedQuery(name = "Sender.findBySenIdDocumentExpDate", query = "SELECT s FROM Sender s WHERE s.senIdDocumentExpDate = :senIdDocumentExpDate")
    , @NamedQuery(name = "Sender.findBySenIdDocumentDelDate", query = "SELECT s FROM Sender s WHERE s.senIdDocumentDelDate = :senIdDocumentDelDate")
    , @NamedQuery(name = "Sender.findBySenGender", query = "SELECT s FROM Sender s WHERE s.senGender = :senGender")
    , @NamedQuery(name = "Sender.findBySenOccupation", query = "SELECT s FROM Sender s WHERE s.senOccupation = :senOccupation")
    , @NamedQuery(name = "Sender.findBySenComment", query = "SELECT s FROM Sender s WHERE s.senComment = :senComment")
    , @NamedQuery(name = "Sender.findBySenDateCreate", query = "SELECT s FROM Sender s WHERE s.senDateCreate = :senDateCreate")
    , @NamedQuery(name = "Sender.findBySenDateModif", query = "SELECT s FROM Sender s WHERE s.senDateModif = :senDateModif")
    , @NamedQuery(name = "Sender.findBySenAddress", query = "SELECT s FROM Sender s WHERE s.senAddress = :senAddress")
    , @NamedQuery(name = "Sender.findBySenAddress2", query = "SELECT s FROM Sender s WHERE s.senAddress2 = :senAddress2")})
public class Sender implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "SEN_ID", nullable = false, precision = 19, scale = 0)
    private BigDecimal senId;
    @Column(name = "SEN_CODE")
    private Integer senCode;
    @Size(max = 255)
    @Column(name = "SEN_FIRSTNAME", length = 255)
    private String senFirstname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "SEN_LASTNAME", nullable = false, length = 255)
    private String senLastname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "SEN_COUNTRY", nullable = false, length = 255)
    private String senCountry;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "SEN_COUNTRY_RES", nullable = false, length = 255)
    private String senCountryRes;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "SEN_NATIONALITY", nullable = false, length = 255)
    private String senNationality;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "SEN_CITY", nullable = false, length = 255)
    private String senCity;
    @Size(max = 50)
    @Column(name = "SEN_STATE", length = 50)
    private String senState;
    @Size(max = 255)
    @Column(name = "SEN_POSTAL_CODE", length = 255)
    private String senPostalCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "SEN_PHONE_NUMBER1", nullable = false, length = 50)
    private String senPhoneNumber1;
    @Size(max = 50)
    @Column(name = "SEN_PHONE_NUMBER2", length = 50)
    private String senPhoneNumber2;
    @Size(max = 255)
    @Column(name = "SEN_EMAIL", length = 255)
    private String senEmail;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SEN_DOB", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date senDob;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SEN_STATUT", nullable = false)
    private short senStatut;
    @Size(max = 255)
    @Column(name = "SEN_ID_DOCUMENT_NUMBER", length = 255)
    private String senIdDocumentNumber;
    @Size(max = 255)
    @Column(name = "SEN_ID_DOCUMENT_IMG1", length = 255)
    private String senIdDocumentImg1;
    @Size(max = 255)
    @Column(name = "SEN_ID_DOCUMENT_IMG2", length = 255)
    private String senIdDocumentImg2;
    @Size(max = 255)
    @Column(name = "SEN_ID_DOCUMENT_IMG3", length = 255)
    private String senIdDocumentImg3;
    @Column(name = "SEN_ID_DOCUMENT_EXP_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date senIdDocumentExpDate;
    @Column(name = "SEN_ID_DOCUMENT_DEL_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date senIdDocumentDelDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "SEN_GENDER", nullable = false, length = 50)
    private String senGender;
    @Size(max = 255)
    @Column(name = "SEN_OCCUPATION", length = 255)
    private String senOccupation;
    @Size(max = 255)
    @Column(name = "SEN_COMMENT", length = 255)
    private String senComment;
    @Column(name = "SEN_DATE_CREATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date senDateCreate;
    @Column(name = "SEN_DATE_MODIF")
    @Temporal(TemporalType.TIMESTAMP)
    private Date senDateModif;
    @Size(max = 255)
    @Column(name = "SEN_ADDRESS", length = 255)
    private String senAddress;
    @Size(max = 255)
    @Column(name = "SEN_ADDRESS2", length = 255)
    private String senAddress2;
    @OneToMany(mappedBy = "sebSenId")
    private List<SenderBeneficiary> senderBeneficiaryList;
    @JoinColumn(name = "SEN_ID_DOCUMENT_ISSUE_COUNTRY", referencedColumnName = "PS_CODE")
    @ManyToOne
    private Pays senIdDocumentIssueCountry;
    @JoinColumn(name = "SEN_COUNTRY_ID", referencedColumnName = "PS_CODE", nullable = false)
    @ManyToOne(optional = false)
    private Pays senCountryId;
    @JoinColumn(name = "SEN_COUNTRY_RES_ID", referencedColumnName = "PS_CODE", nullable = false)
    @ManyToOne(optional = false)
    private Pays senCountryResId;
    @JoinColumn(name = "SEN_NATIONALITY_ID", referencedColumnName = "PS_CODE", nullable = false)
    @ManyToOne(optional = false)
    private Pays senNationalityId;
    @JoinColumn(name = "SEN_ID_DOCUMENT_TYPE", referencedColumnName = "TPI_CODE")
    @ManyToOne
    private TypePieceIdentite senIdDocumentType;
    @JoinColumn(name = "SEN_CITY_ID", referencedColumnName = "VI_ID", nullable = false)
    @ManyToOne(optional = false)
    private Ville senCityId;
    
    @JoinColumn(name = "SEN_FTT_ID", referencedColumnName = "FTT_CODE")
    @ManyToOne
    private LimitationTransfert senFttId;
    @JoinColumn(name = "SEN_TEMPLATE_TARIF_ID", referencedColumnName = "TT_ID")
    @ManyToOne
    private TemplateTarif senTemplateTarifId;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "transSenId")
//    private List<TransactionEuing> transactionEuingList;

    public Sender() {
    }

    public String getSenCountryRes() {
        return senCountryRes;
    }

    public void setSenCountryRes(String senCountryRes) {
        this.senCountryRes = senCountryRes;
    }

    public Pays getSenCountryResId() {
        return senCountryResId;
    }

    public void setSenCountryResId(Pays senCountryResId) {
        this.senCountryResId = senCountryResId;
    }

    
    
    public Sender(BigDecimal senId) {
        this.senId = senId;
    }
//
//    public List<TransactionEuing> getTransactionEuingList() {
//        return transactionEuingList;
//    }
//
//    public void setTransactionEuingList(List<TransactionEuing> transactionEuingList) {
//        this.transactionEuingList = transactionEuingList;
//    }
        
    public Sender(BigDecimal senId, String senLastname, String senCountry, String senNationality, String senCity, String senPhoneNumber1, Date senDob, short senStatut, String senGender) {
        this.senId = senId;
        this.senLastname = senLastname;
        this.senCountry = senCountry;
        this.senNationality = senNationality;
        this.senCity = senCity;
        this.senPhoneNumber1 = senPhoneNumber1;
        this.senDob = senDob;
        this.senStatut = senStatut;
        this.senGender = senGender;
    }

    public LimitationTransfert getSenFttId() {
        return senFttId;
    }

    public void setSenFttId(LimitationTransfert senFttId) {
        this.senFttId = senFttId;
    }

    public TemplateTarif getSenTemplateTarifId() {
        return senTemplateTarifId;
    }

    public void setSenTemplateTarifId(TemplateTarif senTemplateTarifId) {
        this.senTemplateTarifId = senTemplateTarifId;
    }
    
    

    public BigDecimal getSenId() {
        return senId;
    }

    public void setSenId(BigDecimal senId) {
        this.senId = senId;
    }

    public Integer getSenCode() {
        return senCode;
    }

    public void setSenCode(Integer senCode) {
        this.senCode = senCode;
    }

    public String getSenFirstname() {
        return senFirstname;
    }

    public void setSenFirstname(String senFirstname) {
        this.senFirstname = senFirstname;
    }

    public String getSenLastname() {
        return senLastname;
    }

    public void setSenLastname(String senLastname) {
        this.senLastname = senLastname;
    }

    public String getSenCountry() {
        return senCountry;
    }

    public void setSenCountry(String senCountry) {
        this.senCountry = senCountry;
    }

    public String getSenNationality() {
        return senNationality;
    }

    public void setSenNationality(String senNationality) {
        this.senNationality = senNationality;
    }

    public String getSenCity() {
        return senCity;
    }

    public void setSenCity(String senCity) {
        this.senCity = senCity;
    }

    public String getSenState() {
        return senState;
    }

    public void setSenState(String senState) {
        this.senState = senState;
    }

    public String getSenPostalCode() {
        return senPostalCode;
    }

    public void setSenPostalCode(String senPostalCode) {
        this.senPostalCode = senPostalCode;
    }

    public String getSenPhoneNumber1() {
        return senPhoneNumber1;
    }

    public void setSenPhoneNumber1(String senPhoneNumber1) {
        this.senPhoneNumber1 = senPhoneNumber1;
    }

    public String getSenPhoneNumber2() {
        return senPhoneNumber2;
    }

    public void setSenPhoneNumber2(String senPhoneNumber2) {
        this.senPhoneNumber2 = senPhoneNumber2;
    }

    public String getSenEmail() {
        return senEmail;
    }

    public void setSenEmail(String senEmail) {
        this.senEmail = senEmail;
    }

    public Date getSenDob() {
        return senDob;
    }

    public void setSenDob(Date senDob) {
        this.senDob = senDob;
    }

    public short getSenStatut() {
        return senStatut;
    }

    public void setSenStatut(short senStatut) {
        this.senStatut = senStatut;
    }

    public String getSenIdDocumentNumber() {
        return senIdDocumentNumber;
    }

    public void setSenIdDocumentNumber(String senIdDocumentNumber) {
        this.senIdDocumentNumber = senIdDocumentNumber;
    }

    public String getSenIdDocumentImg1() {
        return senIdDocumentImg1;
    }

    public void setSenIdDocumentImg1(String senIdDocumentImg1) {
        this.senIdDocumentImg1 = senIdDocumentImg1;
    }

    public String getSenIdDocumentImg2() {
        return senIdDocumentImg2;
    }

    public void setSenIdDocumentImg2(String senIdDocumentImg2) {
        this.senIdDocumentImg2 = senIdDocumentImg2;
    }

    public String getSenIdDocumentImg3() {
        return senIdDocumentImg3;
    }

    public void setSenIdDocumentImg3(String senIdDocumentImg3) {
        this.senIdDocumentImg3 = senIdDocumentImg3;
    }

    public Date getSenIdDocumentExpDate() {
        return senIdDocumentExpDate;
    }

    public void setSenIdDocumentExpDate(Date senIdDocumentExpDate) {
        this.senIdDocumentExpDate = senIdDocumentExpDate;
    }

    public Date getSenIdDocumentDelDate() {
        return senIdDocumentDelDate;
    }

    public void setSenIdDocumentDelDate(Date senIdDocumentDelDate) {
        this.senIdDocumentDelDate = senIdDocumentDelDate;
    }

    public String getSenGender() {
        return senGender;
    }

    public void setSenGender(String senGender) {
        this.senGender = senGender;
    }

    public String getSenOccupation() {
        return senOccupation;
    }

    public void setSenOccupation(String senOccupation) {
        this.senOccupation = senOccupation;
    }

    public String getSenComment() {
        return senComment;
    }

    public void setSenComment(String senComment) {
        this.senComment = senComment;
    }

    public Date getSenDateCreate() {
        return senDateCreate;
    }

    public void setSenDateCreate(Date senDateCreate) {
        this.senDateCreate = senDateCreate;
    }

    public Date getSenDateModif() {
        return senDateModif;
    }

    public void setSenDateModif(Date senDateModif) {
        this.senDateModif = senDateModif;
    }

    public String getSenAddress() {
        return senAddress;
    }

    public void setSenAddress(String senAddress) {
        this.senAddress = senAddress;
    }

    public String getSenAddress2() {
        return senAddress2;
    }

    public void setSenAddress2(String senAddress2) {
        this.senAddress2 = senAddress2;
    }

    @XmlTransient
    public List<SenderBeneficiary> getSenderBeneficiaryList() {
        return senderBeneficiaryList;
    }

    public void setSenderBeneficiaryList(List<SenderBeneficiary> senderBeneficiaryList) {
        this.senderBeneficiaryList = senderBeneficiaryList;
    }

    public Pays getSenIdDocumentIssueCountry() {
        return senIdDocumentIssueCountry;
    }

    public void setSenIdDocumentIssueCountry(Pays senIdDocumentIssueCountry) {
        this.senIdDocumentIssueCountry = senIdDocumentIssueCountry;
    }

    public Pays getSenCountryId() {
        return senCountryId;
    }

    public void setSenCountryId(Pays senCountryId) {
        this.senCountryId = senCountryId;
    }

    public Pays getSenNationalityId() {
        return senNationalityId;
    }

    public void setSenNationalityId(Pays senNationalityId) {
        this.senNationalityId = senNationalityId;
    }

    public TypePieceIdentite getSenIdDocumentType() {
        return senIdDocumentType;
    }

    public void setSenIdDocumentType(TypePieceIdentite senIdDocumentType) {
        this.senIdDocumentType = senIdDocumentType;
    }

    public Ville getSenCityId() {
        return senCityId;
    }

    public void setSenCityId(Ville senCityId) {
        this.senCityId = senCityId;
    }

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
        hash += (senId != null ? senId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sender)) {
            return false;
        }
        Sender other = (Sender) object;
        if ((this.senId == null && other.senId != null) || (this.senId != null && !this.senId.equals(other.senId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Sender{" + "senId=" + senId + ", senCode=" + senCode + ", senFirstname=" + senFirstname + ", senLastname=" + senLastname + ", senCountry=" + senCountry + ", senCountryRes=" + senCountryRes + ", senNationality=" + senNationality + ", senCity=" + senCity + ", senState=" + senState + ", senPostalCode=" + senPostalCode + ", senPhoneNumber1=" + senPhoneNumber1 + ", senPhoneNumber2=" + senPhoneNumber2 + ", senEmail=" + senEmail + ", senDob=" + senDob + ", senStatut=" + senStatut + ", senIdDocumentNumber=" + senIdDocumentNumber + ", senIdDocumentImg1=" + senIdDocumentImg1 + ", senIdDocumentImg2=" + senIdDocumentImg2 + ", senIdDocumentImg3=" + senIdDocumentImg3 + ", senIdDocumentExpDate=" + senIdDocumentExpDate + ", senIdDocumentDelDate=" + senIdDocumentDelDate + ", senGender=" + senGender + ", senOccupation=" + senOccupation + ", senComment=" + senComment + ", senDateCreate=" + senDateCreate + ", senDateModif=" + senDateModif + ", senAddress=" + senAddress + ", senAddress2=" + senAddress2 + ", senderBeneficiaryList=" + senderBeneficiaryList + ", senIdDocumentIssueCountry=" + senIdDocumentIssueCountry + ", senCountryId=" + senCountryId + ", senCountryResId=" + senCountryResId + ", senNationalityId=" + senNationalityId + ", senIdDocumentType=" + senIdDocumentType + ", senCityId=" + senCityId + ", senFttId=" + senFttId + ", senTemplateTarifId=" + senTemplateTarifId + '}';
    }
    
}
