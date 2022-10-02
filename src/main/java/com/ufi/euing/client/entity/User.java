package com.ufi.euing.client.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="EUI_CLIENT")
public class User {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_UTILISATEUR")
    @SequenceGenerator(sequenceName = "S_UTILISATEUR", allocationSize = 1, name = "S_UTILISATEUR")
    @Column(name = "USR_ID", nullable = false, precision = 19, scale = 0)
    private BigDecimal usrId;

    @Size(max = 64)
    @Column(name = "USR_CODE", length = 64)
    private String usrCode; //email de l'utilisateur

    @Size(max = 64)
    @Column(name = "USR_EMAIL", length = 64)
    private String usrEmail; //email de l'utilisateur

    @Size(max = 64)
    @Column(name = "USR_EMAIL_REC", length = 64)
    private String usrEmailRec; //email de recuperation de l'utilisateur

    @Column(name = "USR_LAST_TIME_UPDATE")
    private LocalDateTime usrLastTimeUpdate; //derniere fois qu'il a mis a jour son mot de passe

    @Size(max = 56)
    @Column(name = "USR_NOM", length = 56)
    private String usrNom; //nom
    @Size(max = 300)
    @Column(name = "USR_PASSWORD", length = 300)
    private String usrPassword; //mot de passe
    @Size(max = 56)
    @Column(name = "USR_PRENOM", length = 56)
    private String usrPrenom;  //prenom
    @Size(max = 12)
    @Column(name = "USR_PHONENUMBER", length = 12)
    private String phoneNumber;
    @Size(max = 2)
    @Column(name = "USR_COUNTRY", length = 2)
    private String country;
    @Column(name = "DATE_LASTLOGIN")
    private LocalDateTime lastLoginDate;
    @Column(name = "DATE_CREATE")
    private LocalDateTime joinDate;
    @Column(name = "DATE_MODIF")
    private LocalDateTime modifDate; // date de modification
    private Boolean modifPassword;
    private String role;
    private boolean isActive;
    private boolean isNotLocked;
    private String[] authorities;
}
