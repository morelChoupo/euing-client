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
 * @author EUC
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Parametrews.findAll", query = "SELECT p FROM Parametrews p")
    , @NamedQuery(name = "Parametrews.findByCompagnie", query = "SELECT p FROM Parametrews p WHERE p.pwsUoId.id = :compagnieId and p.wstype = :wstype")
    , @NamedQuery(name = "Parametrews.findByPwsId", query = "SELECT p FROM Parametrews p WHERE p.pwsId = :pwsId")
    , @NamedQuery(name = "Parametrews.findByPfActive", query = "SELECT p FROM Parametrews p WHERE p.pfActive = :pfActive")
    , @NamedQuery(name = "Parametrews.findByLogin", query = "SELECT p FROM Parametrews p WHERE p.login = :login")
    , @NamedQuery(name = "Parametrews.findByPwsPartnerCode", query = "SELECT p FROM Parametrews p WHERE p.pwsPartnerCode = :pwsPartnerCode and p.statut = 1 and p.wstype = 'OUT'")
    , @NamedQuery(name = "Parametrews.findByPwsCode", query = "SELECT p FROM Parametrews p WHERE p.pwsPartnerCode = :pwsPartnerCode and p.statut = 1")
    , @NamedQuery(name = "Parametrews.findByPassword", query = "SELECT p FROM Parametrews p WHERE p.password = :password")
    , @NamedQuery(name = "Parametrews.findByPrefixetransaction", query = "SELECT p FROM Parametrews p WHERE p.prefixetransaction = :prefixetransaction")
    , @NamedQuery(name = "Parametrews.findByServerip", query = "SELECT p FROM Parametrews p WHERE p.serverip = :serverip")
    , @NamedQuery(name = "Parametrews.findByPwsWebServiceUrl", query = "SELECT p FROM Parametrews p WHERE p.pwsWebServiceUrl = :pwsWebServiceUrl")
    , @NamedQuery(name = "Parametrews.findByWstype", query = "SELECT p FROM Parametrews p WHERE p.wstype = :wstype and p.statut = :statut")
    , @NamedQuery(name = "Parametrews.findByPwsPartnerName", query = "SELECT p FROM Parametrews p WHERE p.pwsPartnerName = :pwsPartnerName")
    , @NamedQuery(name = "Parametrews.findByAutorized", query = "SELECT p FROM Parametrews p WHERE p.autorized = :autorized")
    , @NamedQuery(name = "Parametrews.findByTokenautorized", query = "SELECT p FROM Parametrews p WHERE p.tokenautorized = :tokenautorized")
    , @NamedQuery(name = "Parametrews.findByPwsMyagentIdPartner", query = "SELECT p FROM Parametrews p WHERE p.pwsMyagentIdPartner = :pwsMyagentIdPartner")
    , @NamedQuery(name = "Parametrews.findByPwsMykeyPartner", query = "SELECT p FROM Parametrews p WHERE p.pwsMykeyPartner = :pwsMykeyPartner")
    , @NamedQuery(name = "Parametrews.findByPwsMystatutIdPartner", query = "SELECT p FROM Parametrews p WHERE p.pwsMystatutIdPartner = :pwsMystatutIdPartner")
    , @NamedQuery(name = "Parametrews.findByStatut", query = "SELECT p FROM Parametrews p WHERE p.statut = :statut")
    , @NamedQuery(name = "Parametrews.findByUserCreate", query = "SELECT p FROM Parametrews p WHERE p.userCreate = :userCreate")
    , @NamedQuery(name = "Parametrews.findByUserModif", query = "SELECT p FROM Parametrews p WHERE p.userModif = :userModif")
    , @NamedQuery(name = "Parametrews.findByDateCreate", query = "SELECT p FROM Parametrews p WHERE p.dateCreate = :dateCreate")
    , @NamedQuery(name = "Parametrews.findByDateModif", query = "SELECT p FROM Parametrews p WHERE p.dateModif = :dateModif")})
public class Parametrews implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PWS_ID", nullable = false, precision = 19, scale = 0)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_PARAMETREWS")
    @SequenceGenerator(sequenceName = "S_PARAMETREWS", allocationSize = 1, name = "S_PARAMETREWS")
    private BigDecimal pwsId;
    @Size(max = 255)
    @Column(name = "PF_ACTIVE", length = 255)
    private String pfActive;
    @Size(max = 255)
    @Column(length = 255)
    private String login;
    @Size(max = 255)
    @Column(name = "PWS_PARTNER_CODE", length = 255)
    private String pwsPartnerCode;
    @Size(max = 255)
    @Column(length = 255)
    private String password;
    @Size(max = 255)
    @Column(length = 255)
    private String prefixetransaction;
    @Size(max = 255)
    @Column(length = 255)
    private String serverip;
    @Size(max = 255)
    @Column(name = "PWS_WEB_SERVICE_URL", length = 255)
    private String pwsWebServiceUrl;
    @Size(max = 255)
    @Column(length = 255)
    private String wstype;
    @Size(max = 255)
    @Column(name = "PWS_PARTNER_NAME", length = 255)
    private String pwsPartnerName;
    @Size(max = 255)
    @Column(length = 255)
    private String autorized;
    @Size(max = 255)
    @Column(length = 255)
    private String tokenautorized;
    @Size(max = 255)
    @Column(name = "PWS_MYAGENT_ID_PARTNER", length = 255)
    private String pwsMyagentIdPartner;
    @Size(max = 255)
    @Column(name = "PWS_MYKEY_PARTNER", length = 255)
    private String pwsMykeyPartner;
    @Size(max = 255)
    @Column(name = "PWS_MYSTATUT_ID_PARTNER", length = 255)
    private String pwsMystatutIdPartner;
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
    @JoinColumn(name = "PWS_ST_ID", referencedColumnName = "ST_ID")
    @ManyToOne
    private Station pwsStId;
    @JoinColumn(name = "PWS_UO_ID", referencedColumnName = "ID")
    @ManyToOne
    private Compagnie pwsUoId;
    @JoinColumn(name = "PWS_USR_ID", referencedColumnName = "USR_ID")
    @ManyToOne
    private Utilisateur pwsUsrId;

    public Parametrews() {
    }

    public Parametrews(BigDecimal pwsId) {
        this.pwsId = pwsId;
    }

    public BigDecimal getPwsId() {
        return pwsId;
    }

    public void setPwsId(BigDecimal pwsId) {
        this.pwsId = pwsId;
    }

    public String getPfActive() {
        return pfActive;
    }

    public void setPfActive(String pfActive) {
        this.pfActive = pfActive;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPwsPartnerCode() {
        return pwsPartnerCode;
    }

    public void setPwsPartnerCode(String pwsPartnerCode) {
        this.pwsPartnerCode = pwsPartnerCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPrefixetransaction() {
        return prefixetransaction;
    }

    public void setPrefixetransaction(String prefixetransaction) {
        this.prefixetransaction = prefixetransaction;
    }

    public String getServerip() {
        return serverip;
    }

    public void setServerip(String serverip) {
        this.serverip = serverip;
    }

    public String getPwsWebServiceUrl() {
        return pwsWebServiceUrl;
    }

    public void setPwsWebServiceUrl(String pwsWebServiceUrl) {
        this.pwsWebServiceUrl = pwsWebServiceUrl;
    }

    public String getWstype() {
        return wstype;
    }

    public void setWstype(String wstype) {
        this.wstype = wstype;
    }

    public String getPwsPartnerName() {
        return pwsPartnerName;
    }

    public void setPwsPartnerName(String pwsPartnerName) {
        this.pwsPartnerName = pwsPartnerName;
    }

    public String getAutorized() {
        return autorized;
    }

    public void setAutorized(String autorized) {
        this.autorized = autorized;
    }

    public String getTokenautorized() {
        return tokenautorized;
    }

    public void setTokenautorized(String tokenautorized) {
        this.tokenautorized = tokenautorized;
    }

    public String getPwsMyagentIdPartner() {
        return pwsMyagentIdPartner;
    }

    public void setPwsMyagentIdPartner(String pwsMyagentIdPartner) {
        this.pwsMyagentIdPartner = pwsMyagentIdPartner;
    }

    public String getPwsMykeyPartner() {
        return pwsMykeyPartner;
    }

    public void setPwsMykeyPartner(String pwsMykeyPartner) {
        this.pwsMykeyPartner = pwsMykeyPartner;
    }

    public String getPwsMystatutIdPartner() {
        return pwsMystatutIdPartner;
    }

    public void setPwsMystatutIdPartner(String pwsMystatutIdPartner) {
        this.pwsMystatutIdPartner = pwsMystatutIdPartner;
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

    public Station getPwsStId() {
        return pwsStId;
    }

    public void setPwsStId(Station pwsStId) {
        this.pwsStId = pwsStId;
    }

    public Compagnie getPwsUoId() {
        return pwsUoId;
    }

    public void setPwsUoId(Compagnie pwsUoId) {
        this.pwsUoId = pwsUoId;
    }

    public Utilisateur getPwsUsrId() {
        return pwsUsrId;
    }

    public void setPwsUsrId(Utilisateur pwsUsrId) {
        this.pwsUsrId = pwsUsrId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pwsId != null ? pwsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Parametrews)) {
            return false;
        }
        Parametrews other = (Parametrews) object;
        if ((this.pwsId == null && other.pwsId != null) || (this.pwsId != null && !this.pwsId.equals(other.pwsId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Parametrews[ pwsId=" + pwsId + " ]";
    }
    
}
