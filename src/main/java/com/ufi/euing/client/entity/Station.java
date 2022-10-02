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
 * @author DNT
 */
@Entity
@Table(name = "STATION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Station.findAll", query = "SELECT s FROM Station s")
    , @NamedQuery(name = "Station.findByStId", query = "SELECT s FROM Station s WHERE s.stId = :stId")
    , @NamedQuery(name = "Station.findByStEtat", query = "SELECT s FROM Station s WHERE s.stEtat = :stEtat")
    , @NamedQuery(name = "Station.findByStIp", query = "SELECT s FROM Station s WHERE s.stIp = :stIp")
    , @NamedQuery(name = "Station.findByStMac", query = "SELECT s FROM Station s WHERE s.stMac = :stMac")
    , @NamedQuery(name = "Station.findByStMacAndSerialAndIp", query = "SELECT s FROM Station s WHERE s.stMac LIKE (:stMac) and s.stSerial LIKE (:stSerial) AND s.stIp LIKE (:stIp)")
    , @NamedQuery(name = "Station.findByStSerial", query = "SELECT s FROM Station s WHERE s.stSerial = :stSerial")
    , @NamedQuery(name = "Station.findBySerialAgenceEtat", query = "SELECT s FROM Station s WHERE s.stSerial LIKE (:stSerial) AND s.stUoId = :agence AND s.stEtat LIKE (:etat)")
    , @NamedQuery(name = "Station.findByStImei", query = "SELECT s FROM Station s WHERE s.stImei = :stImei")
    , @NamedQuery(name = "Station.findByStImeiAndImsi", query = "SELECT s FROM Station s WHERE s.stImei = :stImei and s.stImsi = :stImsi order by s.stId desc")
    , @NamedQuery(name = "Station.findByStImeiAndSerial", query = "SELECT s FROM Station s WHERE s.stImei = :stImei and s.stSerial = :stSerial order by s.stId desc")
    , @NamedQuery(name = "Station.findByStImsi", query = "SELECT s FROM Station s WHERE s.stImsi = :stImsi")
    , @NamedQuery(name = "Station.findByStMkey", query = "SELECT s FROM Station s WHERE s.stMkey = :stMkey order by s.stId desc")
    , @NamedQuery(name = "Station.findByStMserial", query = "SELECT s FROM Station s WHERE s.stMserial = :stMserial")
    , @NamedQuery(name = "Station.findByStatut", query = "SELECT s FROM Station s WHERE s.statut = :statut")
    , @NamedQuery(name = "Station.findByUserCreate", query = "SELECT s FROM Station s WHERE s.userCreate = :userCreate")
    , @NamedQuery(name = "Station.findByUserModif", query = "SELECT s FROM Station s WHERE s.userModif = :userModif")
    , @NamedQuery(name = "Station.findByDateCreate", query = "SELECT s FROM Station s WHERE s.dateCreate = :dateCreate")
    , @NamedQuery(name = "Station.findByDateModif", query = "SELECT s FROM Station s WHERE s.dateModif = :dateModif")})
public class Station implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STA_SEQ")
    @SequenceGenerator(sequenceName = "S_STATION", allocationSize = 1, name = "STA_SEQ")
    @Basic(optional = false)
    @NotNull
    @Column(name = "ST_ID")
    private BigDecimal stId;
    @Size(max = 255)
    @Column(name = "ST_ETAT")
    private String stEtat;
    @Size(max = 255)
    @Column(name = "ST_IP")
    private String stIp;
    @Size(max = 255)
    @Column(name = "ST_MAC")
    private String stMac;
    @Size(max = 255)
    @Column(name = "ST_SERIAL")
    private String stSerial;
    @Size(max = 255)
    @Column(name = "ST_IMEI")
    private String stImei;
    @Size(max = 255)
    @Column(name = "ST_IMSI")
    private String stImsi;
    @Size(max = 255)
    @Column(name = "ST_MKEY")
    private String stMkey;
    @Size(max = 255)
    @Column(name = "ST_MSERIAL")
    private String stMserial;
    @Column(name = "STATUT")
    private Short statut;
    @Size(max = 20)
    @Column(name = "USER_CREATE")
    private String userCreate;
    @Size(max = 20)
    @Column(name = "USER_MODIF")
    private String userModif;
    @Column(name = "DATE_CREATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreate;
    @Column(name = "DATE_MODIF")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModif;
    @Column(name = "ST_TYPE_UO", length = 20)
    private String stTypeUo;
    @Column(name = "ST_UO_ID")
    private int stUoId;
//    @JoinColumn(name = "ST_UO_ID", referencedColumnName = "ID")
//    @ManyToOne(optional = false)
//    private Agence agence;

    public Station() {
    }

    public String getStTypeUo() {
        return stTypeUo;
    }

    public void setStTypeUo(String stTypeUo) {
        this.stTypeUo = stTypeUo;
    }

    public int getStUoId() {
        return stUoId;
    }

    public void setStUoId(int stUoId) {
        this.stUoId = stUoId;
    }

    public Station(BigDecimal stId) {
        this.stId = stId;
    }

    public BigDecimal getStId() {
        return stId;
    }

    public void setStId(BigDecimal stId) {
        this.stId = stId;
    }

    public String getStEtat() {
        return stEtat;
    }

    public void setStEtat(String stEtat) {
        this.stEtat = stEtat;
    }

    public String getStIp() {
        return stIp;
    }

    public void setStIp(String stIp) {
        this.stIp = stIp;
    }

    public String getStMac() {
        return stMac;
    }

    public void setStMac(String stMac) {
        this.stMac = stMac;
    }

    public String getStSerial() {
        return stSerial;
    }

    public void setStSerial(String stSerial) {
        this.stSerial = stSerial;
    }

    public String getStImei() {
        return stImei;
    }

    public void setStImei(String stImei) {
        this.stImei = stImei;
    }

    public String getStImsi() {
        return stImsi;
    }

    public void setStImsi(String stImsi) {
        this.stImsi = stImsi;
    }

    public String getStMkey() {
        return stMkey;
    }

    public void setStMkey(String stMkey) {
        this.stMkey = stMkey;
    }

    public String getStMserial() {
        return stMserial;
    }

    public void setStMserial(String stMserial) {
        this.stMserial = stMserial;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (stId != null ? stId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Station)) {
            return false;
        }
        Station other = (Station) object;
        if ((this.stId == null && other.stId != null) || (this.stId != null && !this.stId.equals(other.stId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Station{" + "stId=" + stId + ", stEtat=" + stEtat + ", stIp=" + stIp + ", stMac=" + stMac + ", stSerial=" + stSerial + ", stImei=" + stImei + ", stImsi=" + stImsi + ", stMkey=" + stMkey + ", stMserial=" + stMserial + ", statut=" + statut + ", userCreate=" + userCreate + ", userModif=" + userModif + ", dateCreate=" + dateCreate + ", dateModif=" + dateModif + ", stTypeUo=" + stTypeUo + ", stUoId=" + stUoId + '}';
    }

}
