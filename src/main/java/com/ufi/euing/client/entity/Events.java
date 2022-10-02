/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufi.euing.client.entity;

import javax.persistence.*;
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
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Events.findAll", query = "SELECT e FROM Events e")
    , @NamedQuery(name = "Events.findByEveId", query = "SELECT e FROM Events e WHERE e.eveId = :eveId")
    , @NamedQuery(name = "Events.findByEveDate", query = "SELECT e FROM Events e WHERE e.eveDate = :eveDate")
    , @NamedQuery(name = "Events.findByEveComment", query = "SELECT e FROM Events e WHERE e.eveComment = :eveComment")
    , @NamedQuery(name = "Events.findByEveTransId", query = "SELECT e FROM Events e WHERE e.eveTransId.transId = :transId order by e.eveId desc")
    , @NamedQuery(name = "Events.findByEveType", query = "SELECT e FROM Events e WHERE e.eveType = :eveType")
    , @NamedQuery(name = "Events.findByEveUserId", query = "SELECT e FROM Events e WHERE e.eveUserId = :eveUserId")})
public class Events implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EVE_SEQ")
    @SequenceGenerator(sequenceName = "SEQ_EVENTS", allocationSize = 1, name = "EVE_SEQ")
    @Basic(optional = false)
//    @NotNull
    @Column(name = "EVE_ID", nullable = false, precision = 19, scale = 0)
    private BigDecimal eveId;
    @Column(name = "EVE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date eveDate;
    @Size(max = 1024)
    @Column(name = "EVE_COMMENT", length = 1024)
    private String eveComment;
    @JoinColumn(name = "EVE_TRANS_ID", referencedColumnName = "TRANS_ID")
    @ManyToOne
    private TransactionEuing eveTransId;
    @JoinColumn(name = "EVE_TYPE", referencedColumnName = "ID")
    @ManyToOne
    private TypeEvent eveType;
    @JoinColumn(name = "EVE_USER_ID", referencedColumnName = "USR_ID")
    @ManyToOne
    private Utilisateur eveUserId;

    public Events() {
    }

    public Events(BigDecimal eveId) {
        this.eveId = eveId;
    }

    public BigDecimal getEveId() {
        return eveId;
    }

    public void setEveId(BigDecimal eveId) {
        this.eveId = eveId;
    }

    public Date getEveDate() {
        return eveDate;
    }

    public void setEveDate(Date eveDate) {
        this.eveDate = eveDate;
    }

    public String getEveComment() {
        return eveComment;
    }

    public void setEveComment(String eveComment) {
        this.eveComment = eveComment;
    }

    public TransactionEuing getEveTransId() {
        return eveTransId;
    }

    public void setEveTransId(TransactionEuing eveTransId) {
        this.eveTransId = eveTransId;
    }

    public TypeEvent getEveType() {
        return eveType;
    }

    public void setEveType(TypeEvent eveType) {
        this.eveType = eveType;
    }

    public Utilisateur getEveUserId() {
        return eveUserId;
    }

    public void setEveUserId(Utilisateur eveUserId) {
        this.eveUserId = eveUserId;
    }

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (eveId != null ? eveId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Events)) {
            return false;
        }
        Events other = (Events) object;
        if ((this.eveId == null && other.eveId != null) || (this.eveId != null && !this.eveId.equals(other.eveId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ufi.euing.client.entity.Events[ eveId=" + eveId + " ]";
    }
    
}
