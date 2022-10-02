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
import java.math.BigInteger;
import java.util.Date;

/**
 *
 * @author Cash-Xpress-User
 */
@Entity
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"USR_LOGIN"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Utilisateur.findAll", query = "SELECT u FROM Utilisateur u")
    , @NamedQuery(name = "Utilisateur.findByUsrId", query = "SELECT u FROM Utilisateur u WHERE u.usrId = :usrId")
    , @NamedQuery(name = "Utilisateur.findByUsrConnexionCounter", query = "SELECT u FROM Utilisateur u WHERE u.usrConnexionCounter = :usrConnexionCounter")
    , @NamedQuery(name = "Utilisateur.findByUsrPasswordDuration", query = "SELECT u FROM Utilisateur u WHERE u.usrPasswordDuration = :usrPasswordDuration")
    , @NamedQuery(name = "Utilisateur.findByUsrEmail", query = "SELECT u FROM Utilisateur u WHERE u.usrEmail = :usrEmail")
    , @NamedQuery(name = "Utilisateur.findByUsrLastTimeUpdate", query = "SELECT u FROM Utilisateur u WHERE u.usrLastTimeUpdate = :usrLastTimeUpdate")
    , @NamedQuery(name = "Utilisateur.findByUsrLogin", query = "SELECT u FROM Utilisateur u WHERE u.usrLogin = :usrLogin")
    , @NamedQuery(name = "Utilisateur.findByUsrNom", query = "SELECT u FROM Utilisateur u WHERE u.usrNom = :usrNom")
    , @NamedQuery(name = "Utilisateur.findByUsrPassword", query = "SELECT u FROM Utilisateur u WHERE u.usrPassword = :usrPassword")
    , @NamedQuery(name = "Utilisateur.findByUsrPrenom", query = "SELECT u FROM Utilisateur u WHERE u.usrPrenom = :usrPrenom")
    , @NamedQuery(name = "Utilisateur.findByUsrStatut", query = "SELECT u FROM Utilisateur u WHERE u.usrStatut = :usrStatut")
    , @NamedQuery(name = "Utilisateur.findByStatut", query = "SELECT u FROM Utilisateur u WHERE u.statut = :statut")
    , @NamedQuery(name = "Utilisateur.findByUserCreate", query = "SELECT u FROM Utilisateur u WHERE u.userCreate = :userCreate")
    , @NamedQuery(name = "Utilisateur.findByUserModif", query = "SELECT u FROM Utilisateur u WHERE u.userModif = :userModif")
    , @NamedQuery(name = "Utilisateur.findByDateCreate", query = "SELECT u FROM Utilisateur u WHERE u.dateCreate = :dateCreate")
    , @NamedQuery(name = "Utilisateur.findByDateModif", query = "SELECT u FROM Utilisateur u WHERE u.dateModif = :dateModif")
    , @NamedQuery(name = "Utilisateur.findByUoIDAndType", query = "SELECT u FROM Utilisateur u WHERE u.usrUoId = :usrUoId AND u.usrTypeUo = :usrTypeUo")
    , @NamedQuery(name = "Utilisateur.findByEcran", query = "SELECT u FROM Utilisateur u WHERE u.ecran = :ecran")})
public class Utilisateur implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_UTILISATEUR")
    @SequenceGenerator(sequenceName = "S_UTILISATEUR", allocationSize = 1, name = "S_UTILISATEUR")
    @Column(name = "USR_ID", nullable = false, precision = 19, scale = 0)
    private BigDecimal usrId;
    @Column(name = "USR_CONNEXION_COUNTER")
    private BigInteger usrConnexionCounter; //nombre de fois il est déja connecté
    @Column(name = "USR_PASSWORD_DURATION")
    private BigInteger usrPasswordDuration; //date d'expiration du mot de passe
    @Size(max = 64)
    @Column(name = "USR_EMAIL", length = 64)
    private String usrEmail; //email de l'utilisateur
    @Column(name = "USR_LAST_TIME_UPDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date usrLastTimeUpdate; //derniere fois qu'il a mis a jour son mot de passe
    @Size(max = 32)
    @Column(name = "USR_LOGIN", length = 32)
    private String usrLogin; //login
    @Size(max = 56)
    @Column(name = "USR_NOM", length = 56)
    private String usrNom; //nom
    @Size(max = 300)
    @Column(name = "USR_PASSWORD", length = 300)
    private String usrPassword; //mot de passe
    @Size(max = 56)
    @Column(name = "USR_PRENOM", length = 56)
    private String usrPrenom;  //prenom
    @Column(name = "USR_STATUT")
    private Character usrStatut; //status de l'utilisateur (bloqué-activé)
    private Short statut; // hash qui permet d'authentifier le client
    @Size(max = 20)
    @Column(name = "USER_CREATE", length = 20)
    private String userCreate; // administrateur qui a crée l'utilisateur
    @Size(max = 20)
    @Column(name = "USER_MODIF", length = 20)
    private String userModif; // administrateur qui a modifié l'utilisateur
    @Column(name = "DATE_CREATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreate; // date de création
    @Column(name = "DATE_MODIF")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModif; // date de modification
    private Short ecran;  //savoir s'il est connecté ou pas
    @Column(name = "USR_TYPE_UO", length = 20)
    private String usrTypeUo; //unité organisationnelle ou il est attaché (agence - guichet - compagnie)
    @Column(name = "USR_UO_ID")
    private int usrUoId; //Id de l'unité organisationnelle
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idtbUtilisateur")
//    private List<FonctionUtilisateur> fonctionUtilisateurList;

    public Utilisateur() {
    }

    public Utilisateur(BigDecimal usrId) {
        this.usrId = usrId;
    }

    public BigDecimal getUsrId() {
        return usrId;
    }

    public void setUsrId(BigDecimal usrId) {
        this.usrId = usrId;
    }

    public BigInteger getUsrConnexionCounter() {
        return usrConnexionCounter;
    }

    public void setUsrConnexionCounter(BigInteger usrConnexionCounter) {
        this.usrConnexionCounter = usrConnexionCounter;
    }

    public BigInteger getUsrPasswordDuration() {
        return usrPasswordDuration;
    }

    public void setUsrPasswordDuration(BigInteger usrPasswordDuration) {
        this.usrPasswordDuration = usrPasswordDuration;
    }

    public String getUsrEmail() {
        return usrEmail;
    }

    public void setUsrEmail(String usrEmail) {
        this.usrEmail = usrEmail;
    }

    public Date getUsrLastTimeUpdate() {
        return usrLastTimeUpdate;
    }

    public void setUsrLastTimeUpdate(Date usrLastTimeUpdate) {
        this.usrLastTimeUpdate = usrLastTimeUpdate;
    }

    public String getUsrLogin() {
        return usrLogin;
    }

    public void setUsrLogin(String usrLogin) {
        this.usrLogin = usrLogin;
    }

    public String getUsrNom() {
        return usrNom;
    }

    public void setUsrNom(String usrNom) {
        this.usrNom = usrNom;
    }

    public String getUsrPassword() {
        return usrPassword;
    }

    public void setUsrPassword(String usrPassword) {
        this.usrPassword = usrPassword;
    }

    public String getUsrPrenom() {
        return usrPrenom;
    }

    public void setUsrPrenom(String usrPrenom) {
        this.usrPrenom = usrPrenom;
    }

    public Character getUsrStatut() {
        return usrStatut;
    }

    public void setUsrStatut(Character usrStatut) {
        this.usrStatut = usrStatut;
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

    public Short getEcran() {
        return ecran;
    }

    public void setEcran(Short ecran) {
        this.ecran = ecran;
    }

    public String getUsrTypeUo() {
        return usrTypeUo;
    }

    public void setUsrTypeUo(String usrTypeUo) {
        this.usrTypeUo = usrTypeUo;
    }

    public int getUsrUoId() {
        return usrUoId;
    }

    public void setUsrUoId(int usrUoId) {
        this.usrUoId = usrUoId;
    }

//    @XmlTransient
//    public List<FonctionUtilisateur> getFonctionUtilisateurList() {
//        return fonctionUtilisateurList;
//    }
//
//    public void setFonctionUtilisateurList(List<FonctionUtilisateur> fonctionUtilisateurList) {
//        this.fonctionUtilisateurList = fonctionUtilisateurList;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usrId != null ? usrId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Utilisateur)) {
            return false;
        }
         Utilisateur other = (Utilisateur) object;
        if ((this.usrId == null && other.usrId != null) || (this.usrId != null && !this.usrId.equals(other.usrId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ufi.euing.client.entity.Utilisateur[ usrId=" + usrId + " ]";
    }
    
}
