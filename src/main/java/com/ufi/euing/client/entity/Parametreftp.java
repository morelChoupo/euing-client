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
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Parametreftp.findAll", query = "SELECT p FROM Parametreftp p")
    , @NamedQuery(name = "Parametreftp.findByPfId", query = "SELECT p FROM Parametreftp p WHERE p.pfId = :pfId")
    , @NamedQuery(name = "Parametreftp.findByPfDataSeparator", query = "SELECT p FROM Parametreftp p WHERE p.pfDataSeparator = :pfDataSeparator")
    , @NamedQuery(name = "Parametreftp.findByPfFtpIn", query = "SELECT p FROM Parametreftp p WHERE p.pfFtpIn = :pfFtpIn")
    , @NamedQuery(name = "Parametreftp.findByPfFtpLogin", query = "SELECT p FROM Parametreftp p WHERE p.pfFtpLogin = :pfFtpLogin")
    , @NamedQuery(name = "Parametreftp.findByPfFtpOut", query = "SELECT p FROM Parametreftp p WHERE p.pfFtpOut = :pfFtpOut")
    , @NamedQuery(name = "Parametreftp.findByPfFtpPassword", query = "SELECT p FROM Parametreftp p WHERE p.pfFtpPassword = :pfFtpPassword")
    , @NamedQuery(name = "Parametreftp.findByPfFtpServer", query = "SELECT p FROM Parametreftp p WHERE p.pfFtpServer = :pfFtpServer")
    , @NamedQuery(name = "Parametreftp.findByPfFtpType", query = "SELECT p FROM Parametreftp p WHERE p.pfFtpType = :pfFtpType")
    , @NamedQuery(name = "Parametreftp.findByPfLocalArchive", query = "SELECT p FROM Parametreftp p WHERE p.pfLocalArchive = :pfLocalArchive")
    , @NamedQuery(name = "Parametreftp.findByPfLocalIn", query = "SELECT p FROM Parametreftp p WHERE p.pfLocalIn = :pfLocalIn")
    , @NamedQuery(name = "Parametreftp.findByPfLocalOut", query = "SELECT p FROM Parametreftp p WHERE p.pfLocalOut = :pfLocalOut")
    , @NamedQuery(name = "Parametreftp.findByPfPartnerCode", query = "SELECT p FROM Parametreftp p WHERE p.pfPartnerCode = :pfPartnerCode")
    , @NamedQuery(name = "Parametreftp.findByPfWebServiceUrl", query = "SELECT p FROM Parametreftp p WHERE p.pfWebServiceUrl = :pfWebServiceUrl")
    , @NamedQuery(name = "Parametreftp.findByPfActive", query = "SELECT p FROM Parametreftp p WHERE p.pfActive = :pfActive")
    , @NamedQuery(name = "Parametreftp.findByPfPartnerName", query = "SELECT p FROM Parametreftp p WHERE p.pfPartnerName = :pfPartnerName")
    , @NamedQuery(name = "Parametreftp.findByStatut", query = "SELECT p FROM Parametreftp p WHERE p.statut = :statut")
    , @NamedQuery(name = "Parametreftp.findByUserCreate", query = "SELECT p FROM Parametreftp p WHERE p.userCreate = :userCreate")
    , @NamedQuery(name = "Parametreftp.findByUserModif", query = "SELECT p FROM Parametreftp p WHERE p.userModif = :userModif")
    , @NamedQuery(name = "Parametreftp.findByDateCreate", query = "SELECT p FROM Parametreftp p WHERE p.dateCreate = :dateCreate")
    , @NamedQuery(name = "Parametreftp.findByDateModif", query = "SELECT p FROM Parametreftp p WHERE p.dateModif = :dateModif")})
public class Parametreftp implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PF_ID", nullable = false, precision = 19, scale = 0)
    private BigDecimal pfId;
    @Size(max = 255)
    @Column(name = "PF_DATA_SEPARATOR", length = 255)
    private String pfDataSeparator;
    @Size(max = 255)
    @Column(name = "PF_FTP_IN", length = 255)
    private String pfFtpIn;
    @Size(max = 255)
    @Column(name = "PF_FTP_LOGIN", length = 255)
    private String pfFtpLogin;
    @Size(max = 255)
    @Column(name = "PF_FTP_OUT", length = 255)
    private String pfFtpOut;
    @Size(max = 255)
    @Column(name = "PF_FTP_PASSWORD", length = 255)
    private String pfFtpPassword;
    @Size(max = 255)
    @Column(name = "PF_FTP_SERVER", length = 255)
    private String pfFtpServer;
    @Size(max = 255)
    @Column(name = "PF_FTP_TYPE", length = 255)
    private String pfFtpType;
    @Size(max = 255)
    @Column(name = "PF_LOCAL_ARCHIVE", length = 255)
    private String pfLocalArchive;
    @Size(max = 255)
    @Column(name = "PF_LOCAL_IN", length = 255)
    private String pfLocalIn;
    @Size(max = 255)
    @Column(name = "PF_LOCAL_OUT", length = 255)
    private String pfLocalOut;
    @Size(max = 255)
    @Column(name = "PF_PARTNER_CODE", length = 255)
    private String pfPartnerCode;
    @Size(max = 255)
    @Column(name = "PF_WEB_SERVICE_URL", length = 255)
    private String pfWebServiceUrl;
    @Size(max = 255)
    @Column(name = "PF_ACTIVE", length = 255)
    private String pfActive;
    @Size(max = 255)
    @Column(name = "PF_PARTNER_NAME", length = 255)
    private String pfPartnerName;
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
    @JoinColumn(name = "PF_CO_ID", referencedColumnName = "ID")
    @ManyToOne
    private Compagnie pfCoId;
    @JoinColumn(name = "PF_ST_ID", referencedColumnName = "ST_ID")
    @ManyToOne
    private Station pfStId;
    @JoinColumn(name = "PF_USR_ID", referencedColumnName = "USR_ID")
    @ManyToOne
    private Utilisateur pfUsrId;
    @OneToMany(mappedBy = "transParamFtp")
    private List<TransactionEuing> transactionEuingList;

    public Parametreftp() {
    }

    public Parametreftp(BigDecimal pfId) {
        this.pfId = pfId;
    }

    public BigDecimal getPfId() {
        return pfId;
    }

    public void setPfId(BigDecimal pfId) {
        this.pfId = pfId;
    }

    public String getPfDataSeparator() {
        return pfDataSeparator;
    }

    public void setPfDataSeparator(String pfDataSeparator) {
        this.pfDataSeparator = pfDataSeparator;
    }

    public String getPfFtpIn() {
        return pfFtpIn;
    }

    public void setPfFtpIn(String pfFtpIn) {
        this.pfFtpIn = pfFtpIn;
    }

    public String getPfFtpLogin() {
        return pfFtpLogin;
    }

    public void setPfFtpLogin(String pfFtpLogin) {
        this.pfFtpLogin = pfFtpLogin;
    }

    public String getPfFtpOut() {
        return pfFtpOut;
    }

    public void setPfFtpOut(String pfFtpOut) {
        this.pfFtpOut = pfFtpOut;
    }

    public String getPfFtpPassword() {
        return pfFtpPassword;
    }

    public void setPfFtpPassword(String pfFtpPassword) {
        this.pfFtpPassword = pfFtpPassword;
    }

    public String getPfFtpServer() {
        return pfFtpServer;
    }

    public void setPfFtpServer(String pfFtpServer) {
        this.pfFtpServer = pfFtpServer;
    }

    public String getPfFtpType() {
        return pfFtpType;
    }

    public void setPfFtpType(String pfFtpType) {
        this.pfFtpType = pfFtpType;
    }

    public String getPfLocalArchive() {
        return pfLocalArchive;
    }

    public void setPfLocalArchive(String pfLocalArchive) {
        this.pfLocalArchive = pfLocalArchive;
    }

    public String getPfLocalIn() {
        return pfLocalIn;
    }

    public void setPfLocalIn(String pfLocalIn) {
        this.pfLocalIn = pfLocalIn;
    }

    public String getPfLocalOut() {
        return pfLocalOut;
    }

    public void setPfLocalOut(String pfLocalOut) {
        this.pfLocalOut = pfLocalOut;
    }

    public String getPfPartnerCode() {
        return pfPartnerCode;
    }

    public void setPfPartnerCode(String pfPartnerCode) {
        this.pfPartnerCode = pfPartnerCode;
    }

    public String getPfWebServiceUrl() {
        return pfWebServiceUrl;
    }

    public void setPfWebServiceUrl(String pfWebServiceUrl) {
        this.pfWebServiceUrl = pfWebServiceUrl;
    }

    public String getPfActive() {
        return pfActive;
    }

    public void setPfActive(String pfActive) {
        this.pfActive = pfActive;
    }

    public String getPfPartnerName() {
        return pfPartnerName;
    }

    public void setPfPartnerName(String pfPartnerName) {
        this.pfPartnerName = pfPartnerName;
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

    public Compagnie getPfCoId() {
        return pfCoId;
    }

    public void setPfCoId(Compagnie pfCoId) {
        this.pfCoId = pfCoId;
    }

    public Station getPfStId() {
        return pfStId;
    }

    public void setPfStId(Station pfStId) {
        this.pfStId = pfStId;
    }

    public Utilisateur getPfUsrId() {
        return pfUsrId;
    }

    public void setPfUsrId(Utilisateur pfUsrId) {
        this.pfUsrId = pfUsrId;
    }

    @XmlTransient
    public List<TransactionEuing> getTransactionEuingList() {
        return transactionEuingList;
    }

    public void setTransactionEuingList(List<TransactionEuing> transactionEuingList) {
        this.transactionEuingList = transactionEuingList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pfId != null ? pfId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Parametreftp)) {
            return false;
        }
        Parametreftp other = (Parametreftp) object;
        if ((this.pfId == null && other.pfId != null) || (this.pfId != null && !this.pfId.equals(other.pfId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ufi.euing.client.entity.Parametreftp[ pfId=" + pfId + " ]";
    }
    
}
