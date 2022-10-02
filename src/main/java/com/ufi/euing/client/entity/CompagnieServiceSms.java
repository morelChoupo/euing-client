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

/**
 *
 * @author DNT
 */
@Entity
@Table(name = "COMPAGNIE_SERVICE_SMS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CompagnieServiceSms.findAll", query = "SELECT c FROM CompagnieServiceSms c")
    , @NamedQuery(name = "CompagnieServiceSms.findById", query = "SELECT c FROM CompagnieServiceSms c WHERE c.id = :id")
    , @NamedQuery(name = "CompagnieServiceSms.findByCompagnieAndService", query = "SELECT c FROM CompagnieServiceSms c WHERE c.compagnie.id = :compagnie AND c.service.id = :service AND c.statut = :statut")
    , @NamedQuery(name = "CompagnieServiceSms.findByFormatSms", query = "SELECT c FROM CompagnieServiceSms c WHERE c.formatSms = :formatSms")
    , @NamedQuery(name = "CompagnieServiceSms.findByNotificationFlag", query = "SELECT c FROM CompagnieServiceSms c WHERE c.notificationFlag = :notificationFlag")
    , @NamedQuery(name = "CompagnieServiceSms.findByStatut", query = "SELECT c FROM CompagnieServiceSms c WHERE c.statut = :statut")
    , @NamedQuery(name = "CompagnieServiceSms.findByUserCreate", query = "SELECT c FROM CompagnieServiceSms c WHERE c.userCreate = :userCreate")
    , @NamedQuery(name = "CompagnieServiceSms.findByUserModif", query = "SELECT c FROM CompagnieServiceSms c WHERE c.userModif = :userModif")
    , @NamedQuery(name = "CompagnieServiceSms.findByDateCreate", query = "SELECT c FROM CompagnieServiceSms c WHERE c.dateCreate = :dateCreate")
    , @NamedQuery(name = "CompagnieServiceSms.findByDateModif", query = "SELECT c FROM CompagnieServiceSms c WHERE c.dateModif = :dateModif")})
public class CompagnieServiceSms implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_COMPAGNIE_SERVICE_SMS")
    @SequenceGenerator(sequenceName = "SEQ_COMPAGNIE_SERVICE_SMS", allocationSize = 1, name = "SEQ_COMPAGNIE_SERVICE_SMS")
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Size(max = 500)
    @Column(name = "FORMAT_SMS")
    private String formatSms;
    @Column(name = "NOTIFICATION_FLAG")
    private Short notificationFlag;
    @Column(name = "STATUT")
    private Short statut;
    @Size(max = 255)
    @Column(name = "USER_CREATE")
    private String userCreate;
    @Size(max = 255)
    @Column(name = "USER_MODIF")
    private String userModif;
    @Column(name = "DATE_CREATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreate;
    @Column(name = "DATE_MODIF")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModif;
    @JoinColumn(name = "COMPAGNIE", referencedColumnName = "ID")
    @ManyToOne
    private Compagnie compagnie;
    @JoinColumn(name = "SERVICE", referencedColumnName = "ID")
    @ManyToOne
    private Service service;

    public CompagnieServiceSms() {
    }

    public CompagnieServiceSms(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFormatSms() {
        return formatSms;
    }

    public void setFormatSms(String formatSms) {
        this.formatSms = formatSms;
    }

    public Short getNotificationFlag() {
        return notificationFlag;
    }

    public void setNotificationFlag(Short notificationFlag) {
        this.notificationFlag = notificationFlag;
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

    public Compagnie getCompagnie() {
        return compagnie;
    }

    public void setCompagnie(Compagnie compagnie) {
        this.compagnie = compagnie;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
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
        if (!(object instanceof CompagnieServiceSms)) {
            return false;
        }
        CompagnieServiceSms other = (CompagnieServiceSms) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.CompagnieServiceSms[ id=" + id + " ]";
    }

}
