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
 * @author UFI
 */
@Entity
@Table(name = "TRANSACTION_BILL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TransactionBill.findAll", query = "SELECT t FROM TransactionBill t")
    , @NamedQuery(name = "TransactionBill.findById", query = "SELECT t FROM TransactionBill t WHERE t.id = :id")
    , @NamedQuery(name = "TransactionBill.findByTrans1", query = "SELECT t FROM TransactionBill t WHERE t.trans1 = :trans1")
    , @NamedQuery(name = "TransactionBill.findByTrans2", query = "SELECT t FROM TransactionBill t WHERE t.trans2 = :trans2")
    , @NamedQuery(name = "TransactionBill.findByTrans3", query = "SELECT t FROM TransactionBill t WHERE t.trans3 = :trans3")
    , @NamedQuery(name = "TransactionBill.findByTrans4", query = "SELECT t FROM TransactionBill t WHERE t.trans4 = :trans4")
    , @NamedQuery(name = "TransactionBill.findByTrans5", query = "SELECT t FROM TransactionBill t WHERE t.trans5 = :trans5")
    , @NamedQuery(name = "TransactionBill.findByTransAgency", query = "SELECT t FROM TransactionBill t WHERE t.transAgency = :transAgency")
    , @NamedQuery(name = "TransactionBill.findByTransCasher", query = "SELECT t FROM TransactionBill t WHERE t.transCasher = :transCasher")
    , @NamedQuery(name = "TransactionBill.findByTransComisAgence", query = "SELECT t FROM TransactionBill t WHERE t.transComisAgence = :transComisAgence")
    , @NamedQuery(name = "TransactionBill.findByTransComisCompanie", query = "SELECT t FROM TransactionBill t WHERE t.transComisCompanie = :transComisCompanie")
    , @NamedQuery(name = "TransactionBill.findByTransComisGroupe", query = "SELECT t FROM TransactionBill t WHERE t.transComisGroupe = :transComisGroupe")
    , @NamedQuery(name = "TransactionBill.findByTransComisGuichet", query = "SELECT t FROM TransactionBill t WHERE t.transComisGuichet = :transComisGuichet")
    , @NamedQuery(name = "TransactionBill.findByTransComisSysteme", query = "SELECT t FROM TransactionBill t WHERE t.transComisSysteme = :transComisSysteme")
    , @NamedQuery(name = "TransactionBill.findByTransCompany", query = "SELECT t FROM TransactionBill t WHERE t.transCompany = :transCompany")
    , @NamedQuery(name = "TransactionBill.findByTransDateCreate", query = "SELECT t FROM TransactionBill t WHERE t.transDateCreate = :transDateCreate")
    , @NamedQuery(name = "TransactionBill.findByTransDateModif", query = "SELECT t FROM TransactionBill t WHERE t.transDateModif = :transDateModif")
    , @NamedQuery(name = "TransactionBill.findByTransDestCompagnieCode", query = "SELECT t FROM TransactionBill t WHERE t.transDestCompagnieCode = :transDestCompagnieCode")
    , @NamedQuery(name = "TransactionBill.findByTransDestCountry", query = "SELECT t FROM TransactionBill t WHERE t.transDestCountry = :transDestCountry")
    , @NamedQuery(name = "TransactionBill.findByTransExchangeRate", query = "SELECT t FROM TransactionBill t WHERE t.transExchangeRate = :transExchangeRate")
    , @NamedQuery(name = "TransactionBill.findByTransExchangeRateCust", query = "SELECT t FROM TransactionBill t WHERE t.transExchangeRateCust = :transExchangeRateCust")
    , @NamedQuery(name = "TransactionBill.findByTransExchangeRateMargin", query = "SELECT t FROM TransactionBill t WHERE t.transExchangeRateMargin = :transExchangeRateMargin")
    , @NamedQuery(name = "TransactionBill.findByTransFees", query = "SELECT t FROM TransactionBill t WHERE t.transFees = :transFees")
    , @NamedQuery(name = "TransactionBill.findByTransGroup", query = "SELECT t FROM TransactionBill t WHERE t.transGroup = :transGroup")
    , @NamedQuery(name = "TransactionBill.findByTransGuichet", query = "SELECT t FROM TransactionBill t WHERE t.transGuichet = :transGuichet")
    , @NamedQuery(name = "TransactionBill.findByTransOriginCountry", query = "SELECT t FROM TransactionBill t WHERE t.transOriginCountry = :transOriginCountry")
    , @NamedQuery(name = "TransactionBill.findByTransOthersTaxes", query = "SELECT t FROM TransactionBill t WHERE t.transOthersTaxes = :transOthersTaxes")
    , @NamedQuery(name = "TransactionBill.findByTransServiceName", query = "SELECT t FROM TransactionBill t WHERE t.transServiceName = :transServiceName")
    , @NamedQuery(name = "TransactionBill.findByTransTaxes", query = "SELECT t FROM TransactionBill t WHERE t.transTaxes = :transTaxes")
    , @NamedQuery(name = "TransactionBill.findByTransTotal", query = "SELECT t FROM TransactionBill t WHERE t.transTotal = :transTotal")
    , @NamedQuery(name = "TransactionBill.findByTransUserCreate", query = "SELECT t FROM TransactionBill t WHERE t.transUserCreate = :transUserCreate")
    , @NamedQuery(name = "TransactionBill.findByTransUserModif", query = "SELECT t FROM TransactionBill t WHERE t.transUserModif = :transUserModif")
    , @NamedQuery(name = "TransactionBill.findByTransReference", query = "SELECT t FROM TransactionBill t WHERE t.transReference = :transReference")
    , @NamedQuery(name = "TransactionBill.findByTransOriginCur", query = "SELECT t FROM TransactionBill t WHERE t.transOriginCur = :transOriginCur")
    , @NamedQuery(name = "TransactionBill.findByTransDestCur", query = "SELECT t FROM TransactionBill t WHERE t.transDestCur = :transDestCur")
    , @NamedQuery(name = "TransactionBill.findByTransServiceId", query = "SELECT t FROM TransactionBill t WHERE t.transServiceId = :transServiceId")
    , @NamedQuery(name = "TransactionBill.findByTransOriginCountryId", query = "SELECT t FROM TransactionBill t WHERE t.transOriginCountryId = :transOriginCountryId")
    , @NamedQuery(name = "TransactionBill.findByTransDestCountryId", query = "SELECT t FROM TransactionBill t WHERE t.transDestCountryId = :transDestCountryId")})
public class TransactionBill implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TRANSBILL_SEQ")
    @SequenceGenerator(sequenceName = "SEQUENCE_TRANSACTION_BILL", allocationSize = 1, name = "TRANSBILL_SEQ")
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "TRANS_GUICHET", nullable = false, length = 255)
    private String transGuichet;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "TRANS_AGENCY", nullable = false, length = 255)
    private String transAgency;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "TRANS_COMPANY", nullable = false, length = 255)
    private String transCompany;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "TRANS_GROUP", nullable = false, length = 255)
    private String transGroup;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "TRANS_CASHER", nullable = false, length = 255)
    private String transCasher;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "TRANS_REFERENCE", nullable = false, length = 255)
    private String transReference;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "TRANS_ORIGIN_COUNTRY", nullable = false, length = 255)
    private String transOriginCountry;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "TRANS_DEST_COUNTRY", nullable = false, length = 255)
    private String transDestCountry;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TRANS_EXCHANGE_RATE", nullable = false, precision = 19, scale = 5)
    private BigDecimal transExchangeRate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TRANS_EXCHANGE_RATE_CUST", nullable = false, precision = 19, scale = 5)
    private BigDecimal transExchangeRateCust;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TRANS_EXCHANGE_RATE_MARGIN", nullable = false, precision = 19, scale = 5)
    private BigDecimal transExchangeRateMargin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TRANS_AMOUNT", nullable = false, precision = 19, scale = 5)
    private BigDecimal transAmountSent;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TRANS_OTHERS_TAXES", nullable = false, precision = 19, scale = 5)
    private BigDecimal transOthersTaxes;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TRANS_FEES", nullable = false, precision = 19, scale = 5)
    private BigDecimal transFees;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TRANS_TAXES", nullable = false, precision = 19, scale = 5)
    private BigDecimal transTaxes;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TRANS_TOTAL", nullable = false, precision = 19, scale = 5)
    private BigDecimal transTotal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "TRANS_SERVICE_NAME", nullable = false, length = 255)
    private String transServiceName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TRANS_COMIS_AGENCE", nullable = false, precision = 19, scale = 5)
    private BigDecimal transComisAgence;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TRANS_COMIS_COMPANIE", nullable = false, precision = 19, scale = 5)
    private BigDecimal transComisCompanie;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TRANS_COMIS_GROUPE", nullable = false, precision = 19, scale = 5)
    private BigDecimal transComisGroupe;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TRANS_COMIS_SYSTEME", nullable = false, precision = 19, scale = 5)
    private BigDecimal transComisSysteme;
    @Column(name = "TRANS_DATE_CREATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date transDateCreate;
    @Column(name = "TRANS_DATE_MODIF")
    @Temporal(TemporalType.TIMESTAMP)
    private Date transDateModif;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TRANS_USER_CREATE")
    private String transUserCreate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TRANS_USER_MODIF")
    private String transUserModif;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TRANS_COMIS_GUICHET", nullable = false, precision = 10, scale = 5)
    private BigDecimal transComisGuichet;
    @NotNull
    @Column(name = "TRANS_STATUS")
    @Size(min = 1, max = 1)
    private String transactionStatus;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "TRANS_DATE_COMPLETED")
    private Date dateCompleted;
    @Column(name = "TRANS_RETURN_CODE")
    private String transactionReturnCode;
    @Column(name = "TRANS_RETURN_MESSAGE")
    private String transactionReturnMessage;
    @Column(name = "TRANS_THIRD_PARTY_REF")
    private String thirdPartyReference;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FACTURE", referencedColumnName = "ID")
    private Facture facture;
    @JoinColumn(name = "TRANS_DEST_COMPAGNIE_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Compagnie transDestCompagnieId;
    @Column(name = "TRANS_DEST_COMPAGNIE_CODE")
    private String transDestCompagnieCode;
    @JoinColumn(name = "TRANS_GUICHET_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Guichet transGuichetId;
    @JoinColumn(name = "TRANS_AGENCE_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Agence transAgenceId;
    @JoinColumn(name = "TRANS_COMPAGNIE_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Compagnie transCompagnieId;
    @JoinColumn(name = "TRANS_GROUPE_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Succursale transGroupId;
    @JoinColumn(name = "TRANS_USERID", referencedColumnName = "USR_ID", nullable = false)
    @ManyToOne(optional = false)
    private Utilisateur transUserid;
    @JoinColumn(name = "TRANS_SERVICE_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Service transServiceId;
    @JoinColumn(name = "TRANS_ORIGIN_COUNTRY_ID", referencedColumnName = "PS_CODE", nullable = false)
    @ManyToOne(optional = false)
    private Pays transOriginCountryId;
    @JoinColumn(name = "TRANS_DEST_COUNTRY_ID", referencedColumnName = "PS_CODE", nullable = false)
    @ManyToOne(optional = false)
    private Pays transDestCountryId;
    @Column(name = "TRANS_DEPOSITOR_NAME")
    private String depositorName;
    @Column(name = "TRANS_DEPOSITOR_PHONE")
    private String depositorPhone;
    @Column(name = "TRANS1")
    private String trans1;
    @Column(name = "TRANS2")
    private String trans2;
    @Column(name = "TRANS3")
    private String trans3;
    @Column(name = "TRANS4")
    private String trans4;
    @Column(name = "TRANS5")
    private String trans5;
    @Column(name = "TRANS_ORIGIN_CUR")
    private String transOriginCur;
    @Column(name = "TRANS_DEST_CUR")
    private String transDestCur;

    public TransactionBill() {
    }

    public String getTransGuichet() {
        return transGuichet;
    }

    public void setTransGuichet(String transGuichet) {
        this.transGuichet = transGuichet;
    }

    public String getTransAgency() {
        return transAgency;
    }

    public void setTransAgency(String transAgency) {
        this.transAgency = transAgency;
    }

    public String getTransCompany() {
        return transCompany;
    }

    public void setTransCompany(String transCompany) {
        this.transCompany = transCompany;
    }

    public String getTransGroup() {
        return transGroup;
    }

    public void setTransGroup(String transGroup) {
        this.transGroup = transGroup;
    }

    public String getTransCasher() {
        return transCasher;
    }

    public void setTransCasher(String transCasher) {
        this.transCasher = transCasher;
    }

    public String getTransOriginCountry() {
        return transOriginCountry;
    }

    public void setTransOriginCountry(String transOriginCountry) {
        this.transOriginCountry = transOriginCountry;
    }

    public String getTransDestCountry() {
        return transDestCountry;
    }

    public void setTransDestCountry(String transDestCountry) {
        this.transDestCountry = transDestCountry;
    }

    public BigDecimal getTransAmountSent() {
        return transAmountSent;
    }

    public void setTransAmountSent(BigDecimal transAmountSent) {
        this.transAmountSent = transAmountSent;
    }

    public BigDecimal getTransOthersTaxes() {
        return transOthersTaxes;
    }

    public void setTransOthersTaxes(BigDecimal transOthersTaxes) {
        this.transOthersTaxes = transOthersTaxes;
    }

    public BigDecimal getTransFees() {
        return transFees;
    }

    public void setTransFees(BigDecimal transFees) {
        this.transFees = transFees;
    }

    public BigDecimal getTransTaxes() {
        return transTaxes;
    }

    public void setTransTaxes(BigDecimal transTaxes) {
        this.transTaxes = transTaxes;
    }

    public BigDecimal getTransTotal() {
        return transTotal;
    }

    public void setTransTotal(BigDecimal transTotal) {
        this.transTotal = transTotal;
    }

    public String getTransServiceName() {
        return transServiceName;
    }

    public void setTransServiceName(String transServiceName) {
        this.transServiceName = transServiceName;
    }

    public BigDecimal getTransComisAgence() {
        return transComisAgence;
    }

    public void setTransComisAgence(BigDecimal transComisAgence) {
        this.transComisAgence = transComisAgence;
    }

    public BigDecimal getTransComisCompanie() {
        return transComisCompanie;
    }

    public void setTransComisCompanie(BigDecimal transComisCompanie) {
        this.transComisCompanie = transComisCompanie;
    }

    public BigDecimal getTransComisGroupe() {
        return transComisGroupe;
    }

    public void setTransComisGroupe(BigDecimal transComisGroupe) {
        this.transComisGroupe = transComisGroupe;
    }

    public BigDecimal getTransComisSysteme() {
        return transComisSysteme;
    }

    public void setTransComisSysteme(BigDecimal transComisSysteme) {
        this.transComisSysteme = transComisSysteme;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getTransExchangeRate() {
        return transExchangeRate;
    }

    public void setTransExchangeRate(BigDecimal transExchangeRate) {
        this.transExchangeRate = transExchangeRate;
    }

    public BigDecimal getTransExchangeRateCust() {
        return transExchangeRateCust;
    }

    public void setTransExchangeRateCust(BigDecimal transExchangeRateCust) {
        this.transExchangeRateCust = transExchangeRateCust;
    }

    public BigDecimal getTransExchangeRateMargin() {
        return transExchangeRateMargin;
    }

    public void setTransExchangeRateMargin(BigDecimal transExchangeRateMargin) {
        this.transExchangeRateMargin = transExchangeRateMargin;
    }

    public Date getTransDateCreate() {
        return transDateCreate;
    }

    public void setTransDateCreate(Date transDateCreate) {
        this.transDateCreate = transDateCreate;
    }

    public Date getTransDateModif() {
        return transDateModif;
    }

    public void setTransDateModif(Date transDateModif) {
        this.transDateModif = transDateModif;
    }

    public String getTransUserCreate() {
        return transUserCreate;
    }

    public void setTransUserCreate(String transUserCreate) {
        this.transUserCreate = transUserCreate;
    }

    public String getTransUserModif() {
        return transUserModif;
    }

    public void setTransUserModif(String transUserModif) {
        this.transUserModif = transUserModif;
    }

    public String getTrans1() {
        return trans1;
    }

    public void setTrans1(String trans1) {
        this.trans1 = trans1;
    }

    public String getTrans2() {
        return trans2;
    }

    public void setTrans2(String trans2) {
        this.trans2 = trans2;
    }

    public String getTrans3() {
        return trans3;
    }

    public void setTrans3(String trans3) {
        this.trans3 = trans3;
    }

    public String getTrans4() {
        return trans4;
    }

    public void setTrans4(String trans4) {
        this.trans4 = trans4;
    }

    public String getTrans5() {
        return trans5;
    }

    public void setTrans5(String trans5) {
        this.trans5 = trans5;
    }

    public BigDecimal getTransComisGuichet() {
        return transComisGuichet;
    }

    public void setTransComisGuichet(BigDecimal transComisGuichet) {
        this.transComisGuichet = transComisGuichet;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public Date getDateCompleted() {
        return dateCompleted;
    }

    public void setDateCompleted(Date dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    public String getTransactionReturnCode() {
        return transactionReturnCode;
    }

    public void setTransactionReturnCode(String transactionReturnCode) {
        this.transactionReturnCode = transactionReturnCode;
    }

    public String getTransactionReturnMessage() {
        return transactionReturnMessage;
    }

    public void setTransactionReturnMessage(String transactionReturnMessage) {
        this.transactionReturnMessage = transactionReturnMessage;
    }

    public String getThirdPartyReference() {
        return thirdPartyReference;
    }

    public void setThirdPartyReference(String thirdPartyReference) {
        this.thirdPartyReference = thirdPartyReference;
    }

    public Facture getFacture() {
        return facture;
    }

    public void setFacture(Facture facture) {
        this.facture = facture;
    }

    public Compagnie getTransDestCompagnieId() {
        return transDestCompagnieId;
    }

    public void setTransDestCompagnieId(Compagnie transDestCompagnieId) {
        this.transDestCompagnieId = transDestCompagnieId;
    }

    public String getTransDestCompagnieCode() {
        return transDestCompagnieCode;
    }

    public void setTransDestCompagnieCode(String transDestCompagnieCode) {
        this.transDestCompagnieCode = transDestCompagnieCode;
    }

    public Guichet getTransGuichetId() {
        return transGuichetId;
    }

    public void setTransGuichetId(Guichet transGuichetId) {
        this.transGuichetId = transGuichetId;
    }

    public Agence getTransAgenceId() {
        return transAgenceId;
    }

    public void setTransAgenceId(Agence transAgenceId) {
        this.transAgenceId = transAgenceId;
    }

    public Compagnie getTransCompagnieId() {
        return transCompagnieId;
    }

    public void setTransCompagnieId(Compagnie transCompagnieId) {
        this.transCompagnieId = transCompagnieId;
    }

    public Succursale getTransGroupId() {
        return transGroupId;
    }

    public void setTransGroupId(Succursale transGroupId) {
        this.transGroupId = transGroupId;
    }

    public Utilisateur getTransUserid() {
        return transUserid;
    }

    public void setTransUserid(Utilisateur transUserid) {
        this.transUserid = transUserid;
    }

    public String getDepositorName() {
        return depositorName;
    }

    public void setDepositorName(String depositorName) {
        this.depositorName = depositorName;
    }

    public String getDepositorPhone() {
        return depositorPhone;
    }

    public void setDepositorPhone(String depositorPhone) {
        this.depositorPhone = depositorPhone;
    }

    public String getTransReference() {
        return transReference;
    }

    public void setTransReference(String transReference) {
        this.transReference = transReference;
    }

    public Service getTransServiceId() {
        return transServiceId;
    }

    public void setTransServiceId(Service transServiceId) {
        this.transServiceId = transServiceId;
    }

    public Pays getTransOriginCountryId() {
        return transOriginCountryId;
    }

    public void setTransOriginCountryId(Pays transOriginCountryId) {
        this.transOriginCountryId = transOriginCountryId;
    }

    public Pays getTransDestCountryId() {
        return transDestCountryId;
    }

    public void setTransDestCountryId(Pays transDestCountryId) {
        this.transDestCountryId = transDestCountryId;
    }

    public String getTransOriginCur() {
        return transOriginCur;
    }

    public void setTransOriginCur(String transOriginCur) {
        this.transOriginCur = transOriginCur;
    }

    public String getTransDestCur() {
        return transDestCur;
    }

    public void setTransDestCur(String transDestCur) {
        this.transDestCur = transDestCur;
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
        if (!(object instanceof TransactionBill)) {
            return false;
        }
        TransactionBill other = (TransactionBill) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TransactionBill{" + "id=" + id + ", transGuichet=" + transGuichet + ", transAgency=" + transAgency + ", transCompany=" + transCompany + ", transGroup=" + transGroup + ", transCasher=" + transCasher + ", transReference=" + transReference + ", transOriginCountry=" + transOriginCountry + ", transDestCountry=" + transDestCountry + ", transExchangeRate=" + transExchangeRate + ", transExchangeRateCust=" + transExchangeRateCust + ", transExchangeRateMargin=" + transExchangeRateMargin + ", transAmountSent=" + transAmountSent + ", transOthersTaxes=" + transOthersTaxes + ", transFees=" + transFees + ", transTaxes=" + transTaxes + ", transTotal=" + transTotal + ", transServiceName=" + transServiceName + ", transComisAgence=" + transComisAgence + ", transComisCompanie=" + transComisCompanie + ", transComisGroupe=" + transComisGroupe + ", transComisSysteme=" + transComisSysteme + ", transDateCreate=" + transDateCreate + ", transDateModif=" + transDateModif + ", transUserCreate=" + transUserCreate + ", transUserModif=" + transUserModif + ", transComisGuichet=" + transComisGuichet + ", transactionStatus=" + transactionStatus + ", dateCompleted=" + dateCompleted + ", transactionReturnCode=" + transactionReturnCode + ", transactionReturnMessage=" + transactionReturnMessage + ", thirdPartyReference=" + thirdPartyReference + ", facture=" + facture + ", transDestCompagnieId=" + transDestCompagnieId + ", transDestCompagnieCode=" + transDestCompagnieCode + ", transGuichetId=" + transGuichetId + ", transAgenceId=" + transAgenceId + ", transCompagnieId=" + transCompagnieId + ", transGroupId=" + transGroupId + ", transUserid=" + transUserid + ", transServiceId=" + transServiceId + ", transOriginCountryId=" + transOriginCountryId + ", transDestCountryId=" + transDestCountryId + ", depositorName=" + depositorName + ", depositorPhone=" + depositorPhone + ", trans1=" + trans1 + ", trans2=" + trans2 + ", trans3=" + trans3 + ", trans4=" + trans4 + ", trans5=" + trans5 + ", transOriginCur=" + transOriginCur + ", transDestCur=" + transDestCur + '}';
    }

}
