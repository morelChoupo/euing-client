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

/**
 *
 * @author Cash-Xpress-User
 */
@Entity
@Table(name = "TYPE_EVENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TypeEvent.findAll", query = "SELECT t FROM TypeEvent t")
    , @NamedQuery(name = "TypeEvent.findById", query = "SELECT t FROM TypeEvent t WHERE t.id = :id")
    , @NamedQuery(name = "TypeEvent.findByDescription", query = "SELECT t FROM TypeEvent t WHERE t.description = :description")})
public class TypeEvent implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(nullable = false, length = 255)
    private String description;

    public TypeEvent() {
    }

    public TypeEvent(Long id) {
        this.id = id;
    }

    public TypeEvent(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        if (!(object instanceof TypeEvent)) {
            return false;
        }
        TypeEvent other = (TypeEvent) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.TypeEvent[ id=" + id + " ]";
    }
    
}
