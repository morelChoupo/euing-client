/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufi.euing.client.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author Cash-Xpress-User
 */
@Entity
@Table(name = "SENDER_BENEFICIARY", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"SEB_SEN_ID", "SEB_BEN_ID"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SenderBeneficiary.findAll", query = "SELECT s FROM SenderBeneficiary s")
    , @NamedQuery(name = "SenderBeneficiary.findBySebId", query = "SELECT s FROM SenderBeneficiary s WHERE s.sebId = :sebId")})
public class SenderBeneficiary implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "SEB_ID", nullable = false, precision = 19, scale = 0)
    private BigDecimal sebId;
    @JoinColumn(name = "SEB_BEN_ID", referencedColumnName = "BEN_ID")
    @ManyToOne
    private Beneficiary sebBenId;
    @JoinColumn(name = "SEB_SEN_ID", referencedColumnName = "SEN_ID")
    @ManyToOne
    private Sender sebSenId;

    public SenderBeneficiary() {
    }

    public SenderBeneficiary(BigDecimal sebId) {
        this.sebId = sebId;
    }

    public BigDecimal getSebId() {
        return sebId;
    }

    public void setSebId(BigDecimal sebId) {
        this.sebId = sebId;
    }

    public Beneficiary getSebBenId() {
        return sebBenId;
    }

    public void setSebBenId(Beneficiary sebBenId) {
        this.sebBenId = sebBenId;
    }

    public Sender getSebSenId() {
        return sebSenId;
    }

    public void setSebSenId(Sender sebSenId) {
        this.sebSenId = sebSenId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sebId != null ? sebId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SenderBeneficiary)) {
            return false;
        }
        SenderBeneficiary other = (SenderBeneficiary) object;
        if ((this.sebId == null && other.sebId != null) || (this.sebId != null && !this.sebId.equals(other.sebId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ufi.euing.client.entity.SenderBeneficiary[ sebId=" + sebId + " ]";
    }
    
}
