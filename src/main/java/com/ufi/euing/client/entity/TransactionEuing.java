/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufi.euing.client.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "TRANSACTION_EUING")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TransactionEuing.findAll", query = "SELECT t FROM TransactionEuing t")
    , @NamedQuery(name = "TransactionEuing.findByTransId", query = "SELECT t FROM TransactionEuing t WHERE t.transId = :transId")
    , @NamedQuery(name = "TransactionEuing.findByTransGuichet", query = "SELECT t FROM TransactionEuing t WHERE t.transGuichet = :transGuichet")
    , @NamedQuery(name = "TransactionEuing.findByTransAgency", query = "SELECT t FROM TransactionEuing t WHERE t.transAgency = :transAgency")
    , @NamedQuery(name = "TransactionEuing.findByTransCompany", query = "SELECT t FROM TransactionEuing t WHERE t.transCompany = :transCompany")
    , @NamedQuery(name = "TransactionEuing.findByTransGroup", query = "SELECT t FROM TransactionEuing t WHERE t.transGroup = :transGroup")
    , @NamedQuery(name = "TransactionEuing.findByTransCasher", query = "SELECT t FROM TransactionEuing t WHERE t.transCasher = :transCasher")
    , @NamedQuery(name = "TransactionEuing.findByTransOriginCountry", query = "SELECT t FROM TransactionEuing t WHERE t.transOriginCountry = :transOriginCountry")
    , @NamedQuery(name = "TransactionEuing.findByTransDestCountry", query = "SELECT t FROM TransactionEuing t WHERE t.transDestCountry = :transDestCountry")
    , @NamedQuery(name = "TransactionEuing.findByTransExchangeRate", query = "SELECT t FROM TransactionEuing t WHERE t.transExchangeRate = :transExchangeRate")
    , @NamedQuery(name = "TransactionEuing.findByTransExchangeRateCust", query = "SELECT t FROM TransactionEuing t WHERE t.transExchangeRateCust = :transExchangeRateCust")
    , @NamedQuery(name = "TransactionEuing.findByTransExchangeRateMargin", query = "SELECT t FROM TransactionEuing t WHERE t.transExchangeRateMargin = :transExchangeRateMargin")
    , @NamedQuery(name = "TransactionEuing.findByTransAmountSent", query = "SELECT t FROM TransactionEuing t WHERE t.transAmountSent = :transAmountSent")
    , @NamedQuery(name = "TransactionEuing.findByTransOthers", query = "SELECT t FROM TransactionEuing t WHERE t.transOthers = :transOthers")
    , @NamedQuery(name = "TransactionEuing.findByTransFees", query = "SELECT t FROM TransactionEuing t WHERE t.transFees = :transFees")
    , @NamedQuery(name = "TransactionEuing.findByTransTaxes", query = "SELECT t FROM TransactionEuing t WHERE t.transTaxes = :transTaxes")
    , @NamedQuery(name = "TransactionEuing.findByTransTotal", query = "SELECT t FROM TransactionEuing t WHERE t.transTotal = :transTotal")
    , @NamedQuery(name = "TransactionEuing.findByTransAmountToPaid", query = "SELECT t FROM TransactionEuing t WHERE t.transAmountToPaid = :transAmountToPaid")
    , @NamedQuery(name = "TransactionEuing.findByTransReference", query = "SELECT t FROM TransactionEuing t WHERE t.transReference = :transReference")
    , @NamedQuery(name = "TransactionEuing.findByTransInterfacage", query = "SELECT t FROM TransactionEuing t WHERE t.transInterfacage = :transInterfacage")
    , @NamedQuery(name = "TransactionEuing.findByTransStatut", query = "SELECT t FROM TransactionEuing t WHERE t.transStatut = :transStatut")
    , @NamedQuery(name = "TransactionEuing.findByTransSens", query = "SELECT t FROM TransactionEuing t WHERE t.transSens = :transSens")
    , @NamedQuery(name = "TransactionEuing.findByTransServiceName", query = "SELECT t FROM TransactionEuing t WHERE t.transServiceName = :transServiceName")
    , @NamedQuery(name = "TransactionEuing.findByTransSenFirstname", query = "SELECT t FROM TransactionEuing t WHERE t.transSenFirstname = :transSenFirstname")
    , @NamedQuery(name = "TransactionEuing.findByTransSenLastname", query = "SELECT t FROM TransactionEuing t WHERE t.transSenLastname = :transSenLastname")
    , @NamedQuery(name = "TransactionEuing.findByTransSenCountry", query = "SELECT t FROM TransactionEuing t WHERE t.transSenCountry = :transSenCountry")
    , @NamedQuery(name = "TransactionEuing.findByTransSenCity", query = "SELECT t FROM TransactionEuing t WHERE t.transSenCity = :transSenCity")
    , @NamedQuery(name = "TransactionEuing.findByTransSenPostalCode", query = "SELECT t FROM TransactionEuing t WHERE t.transSenPostalCode = :transSenPostalCode")
    , @NamedQuery(name = "TransactionEuing.findByTransSenAddress", query = "SELECT t FROM TransactionEuing t WHERE t.transSenAddress = :transSenAddress")
    , @NamedQuery(name = "TransactionEuing.findByTransSenPhone", query = "SELECT t FROM TransactionEuing t WHERE t.transSenPhone = :transSenPhone")
    , @NamedQuery(name = "TransactionEuing.findByTransSenEmail", query = "SELECT t FROM TransactionEuing t WHERE t.transSenEmail = :transSenEmail")
    , @NamedQuery(name = "TransactionEuing.findByTransSenIdnumber", query = "SELECT t FROM TransactionEuing t WHERE t.transSenIdnumber = :transSenIdnumber")
    , @NamedQuery(name = "TransactionEuing.findByTransSenIdtype", query = "SELECT t FROM TransactionEuing t WHERE t.transSenIdtype = :transSenIdtype")
    , @NamedQuery(name = "TransactionEuing.findByTransBenFirstname", query = "SELECT t FROM TransactionEuing t WHERE t.transBenFirstname = :transBenFirstname")
    , @NamedQuery(name = "TransactionEuing.findByTransBenLastname", query = "SELECT t FROM TransactionEuing t WHERE t.transBenLastname = :transBenLastname")
    , @NamedQuery(name = "TransactionEuing.findByTransBenCountry", query = "SELECT t FROM TransactionEuing t WHERE t.transBenCountry = :transBenCountry")
    , @NamedQuery(name = "TransactionEuing.findByTransBenCity", query = "SELECT t FROM TransactionEuing t WHERE t.transBenCity = :transBenCity")
    , @NamedQuery(name = "TransactionEuing.findByTransBenPostalCode", query = "SELECT t FROM TransactionEuing t WHERE t.transBenPostalCode = :transBenPostalCode")
    , @NamedQuery(name = "TransactionEuing.findByTransBenAddress", query = "SELECT t FROM TransactionEuing t WHERE t.transBenAddress = :transBenAddress")
    , @NamedQuery(name = "TransactionEuing.findByTransBenPhone", query = "SELECT t FROM TransactionEuing t WHERE t.transBenPhone = :transBenPhone")
    , @NamedQuery(name = "TransactionEuing.findByTransBenEmail", query = "SELECT t FROM TransactionEuing t WHERE t.transBenEmail = :transBenEmail")
    , @NamedQuery(name = "TransactionEuing.findByTransBenIdnumber", query = "SELECT t FROM TransactionEuing t WHERE t.transBenIdnumber = :transBenIdnumber")
    , @NamedQuery(name = "TransactionEuing.findByTransBenIdtype", query = "SELECT t FROM TransactionEuing t WHERE t.transBenIdtype = :transBenIdtype")
    , @NamedQuery(name = "TransactionEuing.findByTransMotif", query = "SELECT t FROM TransactionEuing t WHERE t.transMotif = :transMotif")
    , @NamedQuery(name = "TransactionEuing.findByTransMessage", query = "SELECT t FROM TransactionEuing t WHERE t.transMessage = :transMessage")
    , @NamedQuery(name = "TransactionEuing.findByTransComisAgence", query = "SELECT t FROM TransactionEuing t WHERE t.transComisAgence = :transComisAgence")
    , @NamedQuery(name = "TransactionEuing.findByTransComisCompanie", query = "SELECT t FROM TransactionEuing t WHERE t.transComisCompanie = :transComisCompanie")
    , @NamedQuery(name = "TransactionEuing.findByTransComisGroupe", query = "SELECT t FROM TransactionEuing t WHERE t.transComisGroupe = :transComisGroupe")
    , @NamedQuery(name = "TransactionEuing.findByTransComisSysteme", query = "SELECT t FROM TransactionEuing t WHERE t.transComisSysteme = :transComisSysteme")
    , @NamedQuery(name = "TransactionEuing.findByTransDateCreated", query = "SELECT t FROM TransactionEuing t WHERE t.transDateCreated = :transDateCreated")
    , @NamedQuery(name = "TransactionEuing.findByTransDateValidated", query = "SELECT t FROM TransactionEuing t WHERE t.transDateValidated = :transDateValidated")
    , @NamedQuery(name = "TransactionEuing.findByTransDateCancelRequest", query = "SELECT t FROM TransactionEuing t WHERE t.transDateCancelRequest = :transDateCancelRequest")
    , @NamedQuery(name = "TransactionEuing.findByTransDateCanceled", query = "SELECT t FROM TransactionEuing t WHERE t.transDateCanceled = :transDateCanceled")
    , @NamedQuery(name = "TransactionEuing.findByTransDatePaid", query = "SELECT t FROM TransactionEuing t WHERE t.transDatePaid = :transDatePaid")
    , @NamedQuery(name = "TransactionEuing.findByTransDateAccounted", query = "SELECT t FROM TransactionEuing t WHERE t.transDateAccounted = :transDateAccounted")
    , @NamedQuery(name = "TransactionEuing.findByTransDateInstance", query = "SELECT t FROM TransactionEuing t WHERE t.transDateInstance = :transDateInstance")
    , @NamedQuery(name = "TransactionEuing.findByTransComisGuichet", query = "SELECT t FROM TransactionEuing t WHERE t.transComisGuichet = :transComisGuichet")
    , @NamedQuery(name = "TransactionEuing.findByTransPayergroup", query = "SELECT t FROM TransactionEuing t WHERE t.transPayergroup = :transPayergroup")
    , @NamedQuery(name = "TransactionEuing.findByTransPayercompany", query = "SELECT t FROM TransactionEuing t WHERE t.transPayercompany = :transPayercompany")
    , @NamedQuery(name = "TransactionEuing.findByTransPayeragency", query = "SELECT t FROM TransactionEuing t WHERE t.transPayeragency = :transPayeragency")
    , @NamedQuery(name = "TransactionEuing.findByTransPayerguichet", query = "SELECT t FROM TransactionEuing t WHERE t.transPayerguichet = :transPayerguichet")
    , @NamedQuery(name = "TransactionEuing.findByTransPayercasher", query = "SELECT t FROM TransactionEuing t WHERE t.transPayercasher = :transPayercasher")
    , @NamedQuery(name = "TransactionEuing.findByTransMtchangerate", query = "SELECT t FROM TransactionEuing t WHERE t.transMtchangerate = :transMtchangerate")
    , @NamedQuery(name = "TransactionEuing.findByTransMtpartnercode", query = "SELECT t FROM TransactionEuing t WHERE t.transMtpartnercode = :transMtpartnercode")
    , @NamedQuery(name = "TransactionEuing.findByTransMtsenderemail", query = "SELECT t FROM TransactionEuing t WHERE t.transMtsenderemail = :transMtsenderemail")
    , @NamedQuery(name = "TransactionEuing.findByTransMtstatus", query = "SELECT t FROM TransactionEuing t WHERE t.transMtstatus = :transMtstatus")
    , @NamedQuery(name = "TransactionEuing.findByTransMttransactionid", query = "SELECT t FROM TransactionEuing t WHERE t.transMttransactionid = :transMttransactionid")
    , @NamedQuery(name = "TransactionEuing.findByTransMttransactionnumber", query = "SELECT t FROM TransactionEuing t WHERE t.transMttransactionnumber = :transMttransactionnumber")
    , @NamedQuery(name = "TransactionEuing.findByTransMtvaliditytime", query = "SELECT t FROM TransactionEuing t WHERE t.transMtvaliditytime = :transMtvaliditytime")
    , @NamedQuery(name = "TransactionEuing.findByTransMtobservation", query = "SELECT t FROM TransactionEuing t WHERE t.transMtobservation = :transMtobservation")
    , @NamedQuery(name = "TransactionEuing.findByTransPayeramount", query = "SELECT t FROM TransactionEuing t WHERE t.transPayeramount = :transPayeramount")
    , @NamedQuery(name = "TransactionEuing.findByTransPayerfees", query = "SELECT t FROM TransactionEuing t WHERE t.transPayerfees = :transPayerfees")
    , @NamedQuery(name = "TransactionEuing.findByTransPayertaxes", query = "SELECT t FROM TransactionEuing t WHERE t.transPayertaxes = :transPayertaxes")
    , @NamedQuery(name = "TransactionEuing.findByTransPayerothertaxes", query = "SELECT t FROM TransactionEuing t WHERE t.transPayerothertaxes = :transPayerothertaxes")
    , @NamedQuery(name = "TransactionEuing.findByTransPayercomisgroup", query = "SELECT t FROM TransactionEuing t WHERE t.transPayercomisgroup = :transPayercomisgroup")
    , @NamedQuery(name = "TransactionEuing.findByTransPayercomiscompany", query = "SELECT t FROM TransactionEuing t WHERE t.transPayercomiscompany = :transPayercomiscompany")
    , @NamedQuery(name = "TransactionEuing.findByTransPayercomisagency", query = "SELECT t FROM TransactionEuing t WHERE t.transPayercomisagency = :transPayercomisagency")
    , @NamedQuery(name = "TransactionEuing.findByTransPayercomisguichet", query = "SELECT t FROM TransactionEuing t WHERE t.transPayercomisguichet = :transPayercomisguichet")
    , @NamedQuery(name = "TransactionEuing.findByTransPayercomissysteme", query = "SELECT t FROM TransactionEuing t WHERE t.transPayercomissysteme = :transPayercomissysteme")
    , @NamedQuery(name = "TransactionEuing.findByTransKeepFeeAnnulation", query = "SELECT t FROM TransactionEuing t WHERE t.transKeepFeeAnnulation = :transKeepFeeAnnulation")
    , @NamedQuery(name = "TransactionEuing.findByTransExRateSysComp", query = "SELECT t FROM TransactionEuing t WHERE t.transExRateSysComp = :transExRateSysComp")
    , @NamedQuery(name = "TransactionEuing.findByTransExRateSysGrp", query = "SELECT t FROM TransactionEuing t WHERE t.transExRateSysGrp = :transExRateSysGrp")
    , @NamedQuery(name = "TransactionEuing.findByTransTarifPreferentielUsed", query = "SELECT t FROM TransactionEuing t WHERE t.transTarifPreferentielUsed = :transTarifPreferentielUsed")
    , @NamedQuery(name = "TransactionEuing.findByTransRemise", query = "SELECT t FROM TransactionEuing t WHERE t.transRemise = :transRemise")
    , @NamedQuery(name = "TransactionEuing.findByTransPaisRes", query = "SELECT t FROM TransactionEuing t WHERE t.transPaisRes = :transPaisRes")
    , @NamedQuery(name = "TransactionEuing.findByComFlag", query = "SELECT t FROM TransactionEuing t WHERE t.comFlag = :comFlag")
    , @NamedQuery(name = "TransactionEuing.findByNg1", query = "SELECT t FROM TransactionEuing t WHERE t.ng1 = :ng1")
    , @NamedQuery(name = "TransactionEuing.findByNg2", query = "SELECT t FROM TransactionEuing t WHERE t.ng2 = :ng2")
    , @NamedQuery(name = "TransactionEuing.findByNg3", query = "SELECT t FROM TransactionEuing t WHERE t.ng3 = :ng3")
    , @NamedQuery(name = "TransactionEuing.findByNg4", query = "SELECT t FROM TransactionEuing t WHERE t.ng4 = :ng4")
    , @NamedQuery(name = "TransactionEuing.findByNg5", query = "SELECT t FROM TransactionEuing t WHERE t.ng5 = :ng5")
    , @NamedQuery(name = "TransactionEuing.findByTransRefundDate", query = "SELECT t FROM TransactionEuing t WHERE t.transRefundDate = :transRefundDate")
    , @NamedQuery(name = "TransactionEuing.findByTransIncidenceDate", query = "SELECT t FROM TransactionEuing t WHERE t.transIncidenceDate = :transIncidenceDate")
    , @NamedQuery(name = "TransactionEuing.findByComFlagPaid", query = "SELECT t FROM TransactionEuing t WHERE t.comFlagPaid = :comFlagPaid")
    , @NamedQuery(name = "TransactionEuing.findByTransComisSysReal", query = "SELECT t FROM TransactionEuing t WHERE t.transComisSysReal = :transComisSysReal")
    , @NamedQuery(name = "TransactionEuing.findByTransComisGrpReal", query = "SELECT t FROM TransactionEuing t WHERE t.transComisGrpReal = :transComisGrpReal")
    , @NamedQuery(name = "TransactionEuing.findByTransTaxesGrpReal", query = "SELECT t FROM TransactionEuing t WHERE t.transTaxesGrpReal = :transTaxesGrpReal")
    , @NamedQuery(name = "TransactionEuing.findByTransExRateSysCompPayer", query = "SELECT t FROM TransactionEuing t WHERE t.transExRateSysCompPayer = :transExRateSysCompPayer")
    , @NamedQuery(name = "TransactionEuing.findByTransExRateSysGrpPayer", query = "SELECT t FROM TransactionEuing t WHERE t.transExRateSysGrpPayer = :transExRateSysGrpPayer")
    , @NamedQuery(name = "TransactionEuing.findByTransComisSysRealPayer", query = "SELECT t FROM TransactionEuing t WHERE t.transComisSysRealPayer = :transComisSysRealPayer")
    , @NamedQuery(name = "TransactionEuing.findByTransComisGrpRealPayer", query = "SELECT t FROM TransactionEuing t WHERE t.transComisGrpRealPayer = :transComisGrpRealPayer")
    , @NamedQuery(name = "TransactionEuing.findByTransTaxesGrpRealPayer", query = "SELECT t FROM TransactionEuing t WHERE t.transTaxesGrpRealPayer = :transTaxesGrpRealPayer")
    , @NamedQuery(name = "TransactionEuing.findByTransDateAuthorization", query = "SELECT t FROM TransactionEuing t WHERE t.transDateAuthorization = :transDateAuthorization")
    , @NamedQuery(name = "TransactionEuing.searchTransactionByReferenceEuiToPaid", query = "SELECT t FROM TransactionEuing t WHERE t.transReference = :transReference and t.transDestCountryId.psCode = :destinationCountryCode and (t.transStatut = 'A' or t.transStatut = 'S')")
    , @NamedQuery(name = "TransactionEuing.searchTransactionByReferenceEuiToMAJ", query = "SELECT t FROM TransactionEuing t WHERE t.transReference = :transReference and t.transOriginCountryId.psCode = :origineCountryCode and (t.transStatut = 'A' or t.transStatut = 'S' or t.transStatut = 'H')")
    , @NamedQuery(name = "TransactionEuing.searchTransaction", query = "SELECT t FROM TransactionEuing t WHERE t.transDateCreated >= :dateStart and t.transDateCreated <= :dateEnd and (t.transGroupId.id = :transGroupId or -1 = :transGroupId) and (t.transCompanyId.id = :searchCompagnieId or -1 = :searchCompagnieId) and (t.transAgencyId.id = :searchAgenceId or -1 = :searchAgenceId) and (t.transGuichetId.id = :searchGuichetId or -1 = :searchGuichetId) and (t.transReference = :searchReference or '' = :searchReference)  and (trim(upper(concat(t.transSenLastname,t.transSenFirstname))) = :searchExpediteur or '' = :searchExpediteur)  and (trim(upper(concat(t.transBenLastname,t.transBenFirstname))) = :searchBeneficiaire or '' = :searchBeneficiaire)")
    , @NamedQuery(name = "TransactionEuing.findByTransInstance", query = "SELECT t FROM TransactionEuing t WHERE t.transInstance = :transInstance")
    , @NamedQuery(name = "TransactionEuing.findByTransDateTransmission", query = "SELECT t FROM TransactionEuing t WHERE t.transDateTransmission = :transDateTransmission")})
public class TransactionEuing implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "TRANS_ID", nullable = false, precision = 19, scale = 0)
    private BigDecimal transId;
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
    @Column(name = "TRANS_AMOUNT_SENT", nullable = false, precision = 19, scale = 5)
    private BigDecimal transAmountSent;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TRANS_OTHERS", nullable = false, precision = 19, scale = 5)
    private BigDecimal transOthers;
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
    @Column(name = "TRANS_AMOUNT_TO_PAID", nullable = false, precision = 19, scale = 5)
    private BigDecimal transAmountToPaid;
    @Size(max = 255)
    @Column(name = "TRANS_REFERENCE", length = 255)
    private String transReference;
    @Size(max = 1)
    @Column(name = "TRANS_INTERFACAGE", length = 1)
    private String transInterfacage;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "TRANS_STATUT", nullable = false, length = 1)
    private String transStatut;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "TRANS_SENS", nullable = false, length = 20)
    private String transSens;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "TRANS_SERVICE_NAME", nullable = false, length = 255)
    private String transServiceName;
    @Size(max = 255)
    @Column(name = "TRANS_SEN_FIRSTNAME", length = 255)
    private String transSenFirstname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "TRANS_SEN_LASTNAME", nullable = false, length = 255)
    private String transSenLastname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "TRANS_SEN_COUNTRY", nullable = false, length = 255)
    private String transSenCountry;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "TRANS_SEN_CITY", nullable = false, length = 255)
    private String transSenCity;
    @Size(max = 255)
    @Column(name = "TRANS_SEN_POSTAL_CODE", length = 255)
    private String transSenPostalCode;
    @Size(max = 255)
    @Column(name = "TRANS_SEN_ADDRESS", length = 255)
    private String transSenAddress;
    @Size(max = 50)
    @Column(name = "TRANS_SEN_PHONE", length = 50)
    private String transSenPhone;
    @Size(max = 255)
    @Column(name = "TRANS_SEN_EMAIL", length = 255)
    private String transSenEmail;
    @Size(max = 50)
    @Column(name = "TRANS_SEN_IDNUMBER", length = 50)
    private String transSenIdnumber;
    @Size(max = 255)
    @Column(name = "TRANS_SEN_IDTYPE", length = 255)
    private String transSenIdtype;
    @Size(max = 255)
    @Column(name = "TRANS_BEN_FIRSTNAME", length = 255)
    private String transBenFirstname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "TRANS_BEN_LASTNAME", nullable = false, length = 255)
    private String transBenLastname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "TRANS_BEN_COUNTRY", nullable = false, length = 255)
    private String transBenCountry;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "TRANS_BEN_CITY", nullable = false, length = 255)
    private String transBenCity;
    @Size(max = 255)
    @Column(name = "TRANS_BEN_POSTAL_CODE", length = 255)
    private String transBenPostalCode;
    @Size(max = 255)
    @Column(name = "TRANS_BEN_ADDRESS", length = 255)
    private String transBenAddress;
    @Size(max = 50)
    @Column(name = "TRANS_BEN_PHONE", length = 50)
    private String transBenPhone;
    @Size(max = 255)
    @Column(name = "TRANS_BEN_EMAIL", length = 255)
    private String transBenEmail;
    @Size(max = 50)
    @Column(name = "TRANS_BEN_IDNUMBER", length = 50)
    private String transBenIdnumber;
    @Size(max = 255)
    @Column(name = "TRANS_BEN_IDTYPE", length = 255)
    private String transBenIdtype;
    @Size(max = 255)
    @Column(name = "TRANS_MOTIF", length = 255)
    private String transMotif;
    @Size(max = 255)
    @Column(name = "TRANS_MESSAGE", length = 255)
    private String transMessage;
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
    @Column(name = "TRANS_DATE_CREATED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date transDateCreated;
    @Column(name = "TRANS_DATE_VALIDATED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date transDateValidated;
    @Column(name = "TRANS_DATE_CANCEL_REQUEST")
    @Temporal(TemporalType.TIMESTAMP)
    private Date transDateCancelRequest;
    @Column(name = "TRANS_DATE_CANCELED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date transDateCanceled;
    @Column(name = "TRANS_DATE_PAID")
    @Temporal(TemporalType.TIMESTAMP)
    private Date transDatePaid;
    @Column(name = "TRANS_DATE_ACCOUNTED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date transDateAccounted;
    @Column(name = "TRANS_DATE_INSTANCE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date transDateInstance;
    @Size(max = 1)
    @Column(name = "TRANS_INSTANCE", length = 1)
    private String transInstance;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TRANS_COMIS_GUICHET", nullable = false, precision = 10, scale = 5)
    private BigDecimal transComisGuichet;
    @Size(max = 255)
    @Column(name = "TRANS_PAYERGROUP", length = 255)
    private String transPayergroup;
    @Size(max = 255)
    @Column(name = "TRANS_PAYERCOMPANY", length = 255)
    private String transPayercompany;
    @Size(max = 255)
    @Column(name = "TRANS_PAYERAGENCY", length = 255)
    private String transPayeragency;
    @Size(max = 255)
    @Column(name = "TRANS_PAYERGUICHET", length = 255)
    private String transPayerguichet;
    @Size(max = 255)
    @Column(name = "TRANS_PAYERCASHER", length = 255)
    private String transPayercasher;
    @Column(name = "TRANS_MTCHANGERATE", precision = 19, scale = 5)
    private BigDecimal transMtchangerate;
    @Size(max = 255)
    @Column(name = "TRANS_MTPARTNERCODE", length = 255)
    private String transMtpartnercode;
    @Size(max = 255)
    @Column(name = "TRANS_MTSENDEREMAIL", length = 255)
    private String transMtsenderemail;
    @Size(max = 255)
    @Column(name = "TRANS_MTSTATUS", length = 255)
    private String transMtstatus;
    @Size(max = 255)
    @Column(name = "TRANS_MTTRANSACTIONID", length = 255)
    private String transMttransactionid;
    @Size(max = 255)
    @Column(name = "TRANS_MTTRANSACTIONNUMBER", length = 255)
    private String transMttransactionnumber;
    @Column(name = "TRANS_MTVALIDITYTIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date transMtvaliditytime;
    @Size(max = 255)
    @Column(name = "TRANS_MTOBSERVATION", length = 255)
    private String transMtobservation;
    @Column(name = "TRANS_PAYERAMOUNT", precision = 19, scale = 5)
    private BigDecimal transPayeramount;
    @Column(name = "TRANS_PAYERFEES", precision = 19, scale = 5)
    private BigDecimal transPayerfees;
    @Column(name = "TRANS_PAYERTAXES", precision = 19, scale = 5)
    private BigDecimal transPayertaxes;
    @Column(name = "TRANS_PAYEROTHERTAXES", precision = 19, scale = 5)
    private BigDecimal transPayerothertaxes;
    @Column(name = "TRANS_PAYERCOMISGROUP", precision = 19, scale = 5)
    private BigDecimal transPayercomisgroup;
    @Column(name = "TRANS_PAYERCOMISCOMPANY", precision = 19, scale = 5)
    private BigDecimal transPayercomiscompany;
    @Column(name = "TRANS_PAYERCOMISAGENCY", precision = 19, scale = 5)
    private BigDecimal transPayercomisagency;
    @Column(name = "TRANS_PAYERCOMISGUICHET", precision = 19, scale = 5)
    private BigDecimal transPayercomisguichet;
    @Column(name = "TRANS_PAYERCOMISSYSTEME", precision = 19, scale = 5)
    private BigDecimal transPayercomissysteme;
    @Column(name = "TRANS_KEEP_FEE_ANNULATION")
    private Short transKeepFeeAnnulation;
    @Column(name = "TRANS_EX_RATE_SYS_COMP", precision = 19, scale = 5)
    private BigDecimal transExRateSysComp;
    @Column(name = "TRANS_EX_RATE_SYS_GRP", precision = 19, scale = 5)
    private BigDecimal transExRateSysGrp;
    @Column(name = "TRANS_TARIF_PREFERENTIEL_USED")
    private Short transTarifPreferentielUsed;
    @Column(name = "TRANS_REMISE", precision = 19, scale = 5)
    private BigDecimal transRemise;
    @Size(max = 255)
    @Column(name = "TRANS_PAIS_RES", length = 255)
    private String transPaisRes;
    @Size(max = 1)
    @Column(name = "COM_FLAG", length = 1)
    private String comFlag;
    @Size(max = 255)
    @Column(length = 255)
    private String ng1;
    @Size(max = 255)
    @Column(length = 255)
    private String ng2;
    @Size(max = 255)
    @Column(length = 255)
    private String ng3;
    @Size(max = 255)
    @Column(length = 255)
    private String ng4;
    @Size(max = 255)
    @Column(length = 255)
    private String ng5;
    @Column(name = "TRANS_REFUND_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date transRefundDate;
    @Column(name = "TRANS_INCIDENCE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date transIncidenceDate;
    @Size(max = 1)
    @Column(name = "COM_FLAG_PAID", length = 1)
    private String comFlagPaid;
    @Column(name = "TRANS_COMIS_SYS_REAL", precision = 19, scale = 5)
    private BigDecimal transComisSysReal;
    @Column(name = "TRANS_COMIS_GRP_REAL", precision = 19, scale = 5)
    private BigDecimal transComisGrpReal;
    @Column(name = "TRANS_TAXES_GRP_REAL", precision = 19, scale = 5)
    private BigDecimal transTaxesGrpReal;
    @Column(name = "TRANS_EX_RATE_SYS_COMP_PAYER", precision = 19, scale = 5)
    private BigDecimal transExRateSysCompPayer;
    @Column(name = "TRANS_EX_RATE_SYS_GRP_PAYER", precision = 19, scale = 5)
    private BigDecimal transExRateSysGrpPayer;
    @Column(name = "TRANS_COMIS_SYS_REAL_PAYER", precision = 19, scale = 5)
    private BigDecimal transComisSysRealPayer;
    @Column(name = "TRANS_COMIS_GRP_REAL_PAYER", precision = 19, scale = 5)
    private BigDecimal transComisGrpRealPayer;
    @Column(name = "TRANS_TAXES_GRP_REAL_PAYER", precision = 19, scale = 5)
    private BigDecimal transTaxesGrpRealPayer;
    @Column(name = "TRANS_DATE_AUTHORIZATION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date transDateAuthorization;
    @Column(name = "TRANS_DATE_HOLD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date transDateHold;
    @Column(name = "TRANS_DATE_TRANSMISSION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date transDateTransmission;

    @JsonIgnore
    @OneToMany(mappedBy = "eveTransId")
    private List<Events> eventsList;
    @JoinColumn(name = "TRANS_BEN_ID", referencedColumnName = "BEN_ID", nullable = false)
    @ManyToOne(optional = false)
    private Beneficiary transBenId;
    @JoinColumn(name = "TRANS_AGENCY_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Agence transAgencyId;
    @JoinColumn(name = "TRANS_PAYERAGENCYID", referencedColumnName = "ID")
    @ManyToOne
    private Agence transPayeragencyid;
    @JoinColumn(name = "TRANS_PAYERCASHERID", referencedColumnName = "USR_ID")
    @ManyToOne
    private Utilisateur transPayercasherid;
    @JoinColumn(name = "TRANS_PAYERCOMPANYID", referencedColumnName = "ID")
    @ManyToOne
    private Compagnie transPayercompanyid;
    @JoinColumn(name = "TRANS_USERID", referencedColumnName = "USR_ID", nullable = false)
    @ManyToOne(optional = false)
    private Utilisateur transUserid;
    @JoinColumn(name = "TRANS_PAIS_RES_ID", referencedColumnName = "PS_CODE")
    @ManyToOne
    private Pays transPaisResId;
    @JoinColumn(name = "TRANS_INCIDENCE_USER_ID", referencedColumnName = "USR_ID")
    @ManyToOne
    private Utilisateur transIncidenceUserId;
    @JoinColumn(name = "TRANS_DEST_COUNTRY_ID", referencedColumnName = "PS_CODE", nullable = false)
    @ManyToOne(optional = false)
    private Pays transDestCountryId;
    @JoinColumn(name = "TRANS_COMPANY_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Compagnie transCompanyId;
    @JoinColumn(name = "TRANS_ORIGIN_COUNTRY_ID", referencedColumnName = "PS_CODE", nullable = false)
    @ManyToOne(optional = false)
    private Pays transOriginCountryId;
    @JoinColumn(name = "TRANS_TARIF_ID", referencedColumnName = "TT_ID")
    @ManyToOne
    private TemplateTarif transTarifId;
    @JoinColumn(name = "TRANS_REFUND_USER_ID", referencedColumnName = "USR_ID")
    @ManyToOne
    private Utilisateur transRefundUserId;
    @JoinColumn(name = "TRANS_DEST_CUR", referencedColumnName = "DEV_CODE", nullable = false)
    @ManyToOne(optional = false)
    private Devise transDestCur;
    @JoinColumn(name = "TRANS_ORIGIN_CUR", referencedColumnName = "DEV_CODE", nullable = false)
    @ManyToOne(optional = false)
    private Devise transOriginCur;
    @JoinColumn(name = "TRANS_PAYERGUICHETID", referencedColumnName = "ID")
    @ManyToOne
    private Guichet transPayerguichetid;
    @JoinColumn(name = "TRANS_GROUP_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Succursale transGroupId;
    @JoinColumn(name = "TRANS_PAYERGROUPID", referencedColumnName = "ID")
    @ManyToOne
    private Succursale transPayergroupid;
    @JoinColumn(name = "TRANS_GUICHET_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Guichet transGuichetId;
    @JoinColumn(name = "TRANS_PARAM_FTP", referencedColumnName = "PF_ID")
    @ManyToOne
    private Parametreftp transParamFtp;
    @JoinColumn(name = "TRANS_SEN_ID", referencedColumnName = "SEN_ID", nullable = false)
    @ManyToOne(optional = false)
    private Sender transSenId;
    @JoinColumn(name = "TRANS_PARAM_WS", referencedColumnName = "PWS_ID")
    @ManyToOne
    private Parametrews transParamWs;
    @JoinColumn(name = "TRANS_SERVICE_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Service transServiceId;
    @OneToMany(mappedBy = "transactionId")
    private List<TransactionDocument> transactionDocumentList;

    public TransactionEuing() {
    }

    public TransactionEuing(BigDecimal transId) {
        this.transId = transId;
    }

    public TransactionEuing(BigDecimal transId, String transGuichet, String transAgency, String transCompany, String transGroup, String transCasher, String transOriginCountry, String transDestCountry, BigDecimal transExchangeRate, BigDecimal transExchangeRateCust, BigDecimal transExchangeRateMargin, BigDecimal transAmountSent, BigDecimal transOthers, BigDecimal transFees, BigDecimal transTaxes, BigDecimal transTotal, BigDecimal transAmountToPaid, String transStatut, String transSens, String transServiceName, String transSenLastname, String transSenCountry, String transSenCity, String transBenLastname, String transBenCountry, String transBenCity, BigDecimal transComisAgence, BigDecimal transComisCompanie, BigDecimal transComisGroupe, BigDecimal transComisSysteme, BigDecimal transComisGuichet) {
        this.transId = transId;
        this.transGuichet = transGuichet;
        this.transAgency = transAgency;
        this.transCompany = transCompany;
        this.transGroup = transGroup;
        this.transCasher = transCasher;
        this.transOriginCountry = transOriginCountry;
        this.transDestCountry = transDestCountry;
        this.transExchangeRate = transExchangeRate;
        this.transExchangeRateCust = transExchangeRateCust;
        this.transExchangeRateMargin = transExchangeRateMargin;
        this.transAmountSent = transAmountSent;
        this.transOthers = transOthers;
        this.transFees = transFees;
        this.transTaxes = transTaxes;
        this.transTotal = transTotal;
        this.transAmountToPaid = transAmountToPaid;
        this.transStatut = transStatut;
        this.transSens = transSens;
        this.transServiceName = transServiceName;
        this.transSenLastname = transSenLastname;
        this.transSenCountry = transSenCountry;
        this.transSenCity = transSenCity;
        this.transBenLastname = transBenLastname;
        this.transBenCountry = transBenCountry;
        this.transBenCity = transBenCity;
        this.transComisAgence = transComisAgence;
        this.transComisCompanie = transComisCompanie;
        this.transComisGroupe = transComisGroupe;
        this.transComisSysteme = transComisSysteme;
        this.transComisGuichet = transComisGuichet;
    }

    public Date getTransDateHold() {
        return transDateHold;
    }

    public void setTransDateHold(Date transDateHold) {
        this.transDateHold = transDateHold;
    }

    public BigDecimal getTransId() {
        return transId;
    }

    public void setTransId(BigDecimal transId) {
        this.transId = transId;
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

    public BigDecimal getTransAmountSent() {
        return transAmountSent;
    }

    public void setTransAmountSent(BigDecimal transAmountSent) {
        this.transAmountSent = transAmountSent;
    }

    public BigDecimal getTransOthers() {
        return transOthers;
    }

    public void setTransOthers(BigDecimal transOthers) {
        this.transOthers = transOthers;
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

    public BigDecimal getTransAmountToPaid() {
        return transAmountToPaid;
    }

    public void setTransAmountToPaid(BigDecimal transAmountToPaid) {
        this.transAmountToPaid = transAmountToPaid;
    }

    public String getTransReference() {
        return transReference;
    }

    public void setTransReference(String transReference) {
        this.transReference = transReference;
    }

    public String getTransInterfacage() {
        return transInterfacage;
    }

    public void setTransInterfacage(String transInterfacage) {
        this.transInterfacage = transInterfacage;
    }

    public String getTransStatut() {
        return transStatut;
    }

    public void setTransStatut(String transStatut) {
        this.transStatut = transStatut;
    }

    public String getTransSens() {
        return transSens;
    }

    public void setTransSens(String transSens) {
        this.transSens = transSens;
    }

    public String getTransServiceName() {
        return transServiceName;
    }

    public void setTransServiceName(String transServiceName) {
        this.transServiceName = transServiceName;
    }

    public String getTransSenFirstname() {
        return transSenFirstname;
    }

    public void setTransSenFirstname(String transSenFirstname) {
        this.transSenFirstname = transSenFirstname;
    }

    public String getTransSenLastname() {
        return transSenLastname;
    }

    public void setTransSenLastname(String transSenLastname) {
        this.transSenLastname = transSenLastname;
    }

    public String getTransSenCountry() {
        return transSenCountry;
    }

    public void setTransSenCountry(String transSenCountry) {
        this.transSenCountry = transSenCountry;
    }

    public String getTransSenCity() {
        return transSenCity;
    }

    public void setTransSenCity(String transSenCity) {
        this.transSenCity = transSenCity;
    }

    public String getTransSenPostalCode() {
        return transSenPostalCode;
    }

    public void setTransSenPostalCode(String transSenPostalCode) {
        this.transSenPostalCode = transSenPostalCode;
    }

    public String getTransSenAddress() {
        return transSenAddress;
    }

    public void setTransSenAddress(String transSenAddress) {
        this.transSenAddress = transSenAddress;
    }

    public String getTransSenPhone() {
        return transSenPhone;
    }

    public void setTransSenPhone(String transSenPhone) {
        this.transSenPhone = transSenPhone;
    }

    public String getTransSenEmail() {
        return transSenEmail;
    }

    public void setTransSenEmail(String transSenEmail) {
        this.transSenEmail = transSenEmail;
    }

    public String getTransSenIdnumber() {
        return transSenIdnumber;
    }

    public void setTransSenIdnumber(String transSenIdnumber) {
        this.transSenIdnumber = transSenIdnumber;
    }

    public String getTransSenIdtype() {
        return transSenIdtype;
    }

    public void setTransSenIdtype(String transSenIdtype) {
        this.transSenIdtype = transSenIdtype;
    }

    public String getTransBenFirstname() {
        return transBenFirstname;
    }

    public void setTransBenFirstname(String transBenFirstname) {
        this.transBenFirstname = transBenFirstname;
    }

    public String getTransBenLastname() {
        return transBenLastname;
    }

    public void setTransBenLastname(String transBenLastname) {
        this.transBenLastname = transBenLastname;
    }

    public String getTransBenCountry() {
        return transBenCountry;
    }

    public void setTransBenCountry(String transBenCountry) {
        this.transBenCountry = transBenCountry;
    }

    public String getTransBenCity() {
        return transBenCity;
    }

    public void setTransBenCity(String transBenCity) {
        this.transBenCity = transBenCity;
    }

    public String getTransBenPostalCode() {
        return transBenPostalCode;
    }

    public void setTransBenPostalCode(String transBenPostalCode) {
        this.transBenPostalCode = transBenPostalCode;
    }

    public String getTransBenAddress() {
        return transBenAddress;
    }

    public void setTransBenAddress(String transBenAddress) {
        this.transBenAddress = transBenAddress;
    }

    public String getTransBenPhone() {
        return transBenPhone;
    }

    public void setTransBenPhone(String transBenPhone) {
        this.transBenPhone = transBenPhone;
    }

    public String getTransBenEmail() {
        return transBenEmail;
    }

    public void setTransBenEmail(String transBenEmail) {
        this.transBenEmail = transBenEmail;
    }

    public String getTransBenIdnumber() {
        return transBenIdnumber;
    }

    public void setTransBenIdnumber(String transBenIdnumber) {
        this.transBenIdnumber = transBenIdnumber;
    }

    public String getTransBenIdtype() {
        return transBenIdtype;
    }

    public void setTransBenIdtype(String transBenIdtype) {
        this.transBenIdtype = transBenIdtype;
    }

    public String getTransMotif() {
        return transMotif;
    }

    public void setTransMotif(String transMotif) {
        this.transMotif = transMotif;
    }

    public String getTransMessage() {
        return transMessage;
    }

    public void setTransMessage(String transMessage) {
        this.transMessage = transMessage;
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

    public Date getTransDateCreated() {
        return transDateCreated;
    }

    public void setTransDateCreated(Date transDateCreated) {
        this.transDateCreated = transDateCreated;
    }

    public Date getTransDateValidated() {
        return transDateValidated;
    }

    public void setTransDateValidated(Date transDateValidated) {
        this.transDateValidated = transDateValidated;
    }

    public Date getTransDateCancelRequest() {
        return transDateCancelRequest;
    }

    public void setTransDateCancelRequest(Date transDateCancelRequest) {
        this.transDateCancelRequest = transDateCancelRequest;
    }

    public Date getTransDateCanceled() {
        return transDateCanceled;
    }

    public void setTransDateCanceled(Date transDateCanceled) {
        this.transDateCanceled = transDateCanceled;
    }

    public Date getTransDatePaid() {
        return transDatePaid;
    }

    public void setTransDatePaid(Date transDatePaid) {
        this.transDatePaid = transDatePaid;
    }

    public Date getTransDateAccounted() {
        return transDateAccounted;
    }

    public void setTransDateAccounted(Date transDateAccounted) {
        this.transDateAccounted = transDateAccounted;
    }

    public Date getTransDateInstance() {
        return transDateInstance;
    }

    public void setTransDateInstance(Date transDateInstance) {
        this.transDateInstance = transDateInstance;
    }

    public String getTransInstance() {
        return transInstance;
    }

    public void setTransInstance(String transInstance) {
        this.transInstance = transInstance;
    }

    public BigDecimal getTransComisGuichet() {
        return transComisGuichet;
    }

    public void setTransComisGuichet(BigDecimal transComisGuichet) {
        this.transComisGuichet = transComisGuichet;
    }

    public String getTransPayergroup() {
        return transPayergroup;
    }

    public void setTransPayergroup(String transPayergroup) {
        this.transPayergroup = transPayergroup;
    }

    public String getTransPayercompany() {
        return transPayercompany;
    }

    public void setTransPayercompany(String transPayercompany) {
        this.transPayercompany = transPayercompany;
    }

    public String getTransPayeragency() {
        return transPayeragency;
    }

    public void setTransPayeragency(String transPayeragency) {
        this.transPayeragency = transPayeragency;
    }

    public String getTransPayerguichet() {
        return transPayerguichet;
    }

    public void setTransPayerguichet(String transPayerguichet) {
        this.transPayerguichet = transPayerguichet;
    }

    public String getTransPayercasher() {
        return transPayercasher;
    }

    public void setTransPayercasher(String transPayercasher) {
        this.transPayercasher = transPayercasher;
    }

    public BigDecimal getTransMtchangerate() {
        return transMtchangerate;
    }

    public void setTransMtchangerate(BigDecimal transMtchangerate) {
        this.transMtchangerate = transMtchangerate;
    }

    public String getTransMtpartnercode() {
        return transMtpartnercode;
    }

    public void setTransMtpartnercode(String transMtpartnercode) {
        this.transMtpartnercode = transMtpartnercode;
    }

    public String getTransMtsenderemail() {
        return transMtsenderemail;
    }

    public void setTransMtsenderemail(String transMtsenderemail) {
        this.transMtsenderemail = transMtsenderemail;
    }

    public String getTransMtstatus() {
        return transMtstatus;
    }

    public void setTransMtstatus(String transMtstatus) {
        this.transMtstatus = transMtstatus;
    }

    public String getTransMttransactionid() {
        return transMttransactionid;
    }

    public void setTransMttransactionid(String transMttransactionid) {
        this.transMttransactionid = transMttransactionid;
    }

    public String getTransMttransactionnumber() {
        return transMttransactionnumber;
    }

    public void setTransMttransactionnumber(String transMttransactionnumber) {
        this.transMttransactionnumber = transMttransactionnumber;
    }

    public Date getTransMtvaliditytime() {
        return transMtvaliditytime;
    }

    public void setTransMtvaliditytime(Date transMtvaliditytime) {
        this.transMtvaliditytime = transMtvaliditytime;
    }

    public String getTransMtobservation() {
        return transMtobservation;
    }

    public void setTransMtobservation(String transMtobservation) {
        this.transMtobservation = transMtobservation;
    }

    public BigDecimal getTransPayeramount() {
        return transPayeramount;
    }

    public void setTransPayeramount(BigDecimal transPayeramount) {
        this.transPayeramount = transPayeramount;
    }

    public BigDecimal getTransPayerfees() {
        return transPayerfees;
    }

    public void setTransPayerfees(BigDecimal transPayerfees) {
        this.transPayerfees = transPayerfees;
    }

    public BigDecimal getTransPayertaxes() {
        return transPayertaxes;
    }

    public void setTransPayertaxes(BigDecimal transPayertaxes) {
        this.transPayertaxes = transPayertaxes;
    }

    public BigDecimal getTransPayerothertaxes() {
        return transPayerothertaxes;
    }

    public void setTransPayerothertaxes(BigDecimal transPayerothertaxes) {
        this.transPayerothertaxes = transPayerothertaxes;
    }

    public BigDecimal getTransPayercomisgroup() {
        return transPayercomisgroup;
    }

    public void setTransPayercomisgroup(BigDecimal transPayercomisgroup) {
        this.transPayercomisgroup = transPayercomisgroup;
    }

    public BigDecimal getTransPayercomiscompany() {
        return transPayercomiscompany;
    }

    public void setTransPayercomiscompany(BigDecimal transPayercomiscompany) {
        this.transPayercomiscompany = transPayercomiscompany;
    }

    public BigDecimal getTransPayercomisagency() {
        return transPayercomisagency;
    }

    public void setTransPayercomisagency(BigDecimal transPayercomisagency) {
        this.transPayercomisagency = transPayercomisagency;
    }

    public BigDecimal getTransPayercomisguichet() {
        return transPayercomisguichet;
    }

    public void setTransPayercomisguichet(BigDecimal transPayercomisguichet) {
        this.transPayercomisguichet = transPayercomisguichet;
    }

    public BigDecimal getTransPayercomissysteme() {
        return transPayercomissysteme;
    }

    public void setTransPayercomissysteme(BigDecimal transPayercomissysteme) {
        this.transPayercomissysteme = transPayercomissysteme;
    }

    public Short getTransKeepFeeAnnulation() {
        return transKeepFeeAnnulation;
    }

    public void setTransKeepFeeAnnulation(Short transKeepFeeAnnulation) {
        this.transKeepFeeAnnulation = transKeepFeeAnnulation;
    }

    public BigDecimal getTransExRateSysComp() {
        return transExRateSysComp;
    }

    public void setTransExRateSysComp(BigDecimal transExRateSysComp) {
        this.transExRateSysComp = transExRateSysComp;
    }

    public BigDecimal getTransExRateSysGrp() {
        return transExRateSysGrp;
    }

    public void setTransExRateSysGrp(BigDecimal transExRateSysGrp) {
        this.transExRateSysGrp = transExRateSysGrp;
    }

    public Short getTransTarifPreferentielUsed() {
        return transTarifPreferentielUsed;
    }

    public void setTransTarifPreferentielUsed(Short transTarifPreferentielUsed) {
        this.transTarifPreferentielUsed = transTarifPreferentielUsed;
    }

    public BigDecimal getTransRemise() {
        return transRemise;
    }

    public void setTransRemise(BigDecimal transRemise) {
        this.transRemise = transRemise;
    }

    public String getTransPaisRes() {
        return transPaisRes;
    }

    public void setTransPaisRes(String transPaisRes) {
        this.transPaisRes = transPaisRes;
    }

    public String getComFlag() {
        return comFlag;
    }

    public void setComFlag(String comFlag) {
        this.comFlag = comFlag;
    }

    public String getNg1() {
        return ng1;
    }

    public void setNg1(String ng1) {
        this.ng1 = ng1;
    }

    public String getNg2() {
        return ng2;
    }

    public void setNg2(String ng2) {
        this.ng2 = ng2;
    }

    public String getNg3() {
        return ng3;
    }

    public void setNg3(String ng3) {
        this.ng3 = ng3;
    }

    public String getNg4() {
        return ng4;
    }

    public void setNg4(String ng4) {
        this.ng4 = ng4;
    }

    public String getNg5() {
        return ng5;
    }

    public void setNg5(String ng5) {
        this.ng5 = ng5;
    }

    public Date getTransRefundDate() {
        return transRefundDate;
    }

    public void setTransRefundDate(Date transRefundDate) {
        this.transRefundDate = transRefundDate;
    }

    public Date getTransIncidenceDate() {
        return transIncidenceDate;
    }

    public void setTransIncidenceDate(Date transIncidenceDate) {
        this.transIncidenceDate = transIncidenceDate;
    }

    public String getComFlagPaid() {
        return comFlagPaid;
    }

    public void setComFlagPaid(String comFlagPaid) {
        this.comFlagPaid = comFlagPaid;
    }

    public BigDecimal getTransComisSysReal() {
        return transComisSysReal;
    }

    public void setTransComisSysReal(BigDecimal transComisSysReal) {
        this.transComisSysReal = transComisSysReal;
    }

    public BigDecimal getTransComisGrpReal() {
        return transComisGrpReal;
    }

    public void setTransComisGrpReal(BigDecimal transComisGrpReal) {
        this.transComisGrpReal = transComisGrpReal;
    }

    public BigDecimal getTransTaxesGrpReal() {
        return transTaxesGrpReal;
    }

    public void setTransTaxesGrpReal(BigDecimal transTaxesGrpReal) {
        this.transTaxesGrpReal = transTaxesGrpReal;
    }

    public BigDecimal getTransExRateSysCompPayer() {
        return transExRateSysCompPayer;
    }

    public void setTransExRateSysCompPayer(BigDecimal transExRateSysCompPayer) {
        this.transExRateSysCompPayer = transExRateSysCompPayer;
    }

    public BigDecimal getTransExRateSysGrpPayer() {
        return transExRateSysGrpPayer;
    }

    public void setTransExRateSysGrpPayer(BigDecimal transExRateSysGrpPayer) {
        this.transExRateSysGrpPayer = transExRateSysGrpPayer;
    }

    public BigDecimal getTransComisSysRealPayer() {
        return transComisSysRealPayer;
    }

    public void setTransComisSysRealPayer(BigDecimal transComisSysRealPayer) {
        this.transComisSysRealPayer = transComisSysRealPayer;
    }

    public BigDecimal getTransComisGrpRealPayer() {
        return transComisGrpRealPayer;
    }

    public void setTransComisGrpRealPayer(BigDecimal transComisGrpRealPayer) {
        this.transComisGrpRealPayer = transComisGrpRealPayer;
    }

    public BigDecimal getTransTaxesGrpRealPayer() {
        return transTaxesGrpRealPayer;
    }

    public void setTransTaxesGrpRealPayer(BigDecimal transTaxesGrpRealPayer) {
        this.transTaxesGrpRealPayer = transTaxesGrpRealPayer;
    }

    public Date getTransDateAuthorization() {
        return transDateAuthorization;
    }

    public void setTransDateAuthorization(Date transDateAuthorization) {
        this.transDateAuthorization = transDateAuthorization;
    }

    public Date getTransDateTransmission() {
        return transDateTransmission;
    }

    public void setTransDateTransmission(Date transDateTransmission) {
        this.transDateTransmission = transDateTransmission;
    }

    @XmlTransient
    public List<Events> getEventsList() {
        return eventsList;
    }

    public void setEventsList(List<Events> eventsList) {
        this.eventsList = eventsList;
    }

    public Beneficiary getTransBenId() {
        return transBenId;
    }

    public void setTransBenId(Beneficiary transBenId) {
        this.transBenId = transBenId;
    }

    public Agence getTransAgencyId() {
        return transAgencyId;
    }

    public void setTransAgencyId(Agence transAgencyId) {
        this.transAgencyId = transAgencyId;
    }

    public Agence getTransPayeragencyid() {
        return transPayeragencyid;
    }

    public void setTransPayeragencyid(Agence transPayeragencyid) {
        this.transPayeragencyid = transPayeragencyid;
    }

    public Utilisateur getTransPayercasherid() {
        return transPayercasherid;
    }

    public void setTransPayercasherid(Utilisateur transPayercasherid) {
        this.transPayercasherid = transPayercasherid;
    }

    public Compagnie getTransPayercompanyid() {
        return transPayercompanyid;
    }

    public void setTransPayercompanyid(Compagnie transPayercompanyid) {
        this.transPayercompanyid = transPayercompanyid;
    }

    public Utilisateur getTransUserid() {
        return transUserid;
    }

    public void setTransUserid(Utilisateur transUserid) {
        this.transUserid = transUserid;
    }

    public Pays getTransPaisResId() {
        return transPaisResId;
    }

    public void setTransPaisResId(Pays transPaisResId) {
        this.transPaisResId = transPaisResId;
    }

    public Utilisateur getTransIncidenceUserId() {
        return transIncidenceUserId;
    }

    public void setTransIncidenceUserId(Utilisateur transIncidenceUserId) {
        this.transIncidenceUserId = transIncidenceUserId;
    }

    public Pays getTransDestCountryId() {
        return transDestCountryId;
    }

    public void setTransDestCountryId(Pays transDestCountryId) {
        this.transDestCountryId = transDestCountryId;
    }

    public Compagnie getTransCompanyId() {
        return transCompanyId;
    }

    public void setTransCompanyId(Compagnie transCompanyId) {
        this.transCompanyId = transCompanyId;
    }

    public Pays getTransOriginCountryId() {
        return transOriginCountryId;
    }

    public void setTransOriginCountryId(Pays transOriginCountryId) {
        this.transOriginCountryId = transOriginCountryId;
    }

    public TemplateTarif getTransTarifId() {
        return transTarifId;
    }

    public void setTransTarifId(TemplateTarif transTarifId) {
        this.transTarifId = transTarifId;
    }

    public Utilisateur getTransRefundUserId() {
        return transRefundUserId;
    }

    public void setTransRefundUserId(Utilisateur transRefundUserId) {
        this.transRefundUserId = transRefundUserId;
    }

    public Devise getTransDestCur() {
        return transDestCur;
    }

    public void setTransDestCur(Devise transDestCur) {
        this.transDestCur = transDestCur;
    }

    public Devise getTransOriginCur() {
        return transOriginCur;
    }

    public void setTransOriginCur(Devise transOriginCur) {
        this.transOriginCur = transOriginCur;
    }

    public Guichet getTransPayerguichetid() {
        return transPayerguichetid;
    }

    public void setTransPayerguichetid(Guichet transPayerguichetid) {
        this.transPayerguichetid = transPayerguichetid;
    }

    public Succursale getTransGroupId() {
        return transGroupId;
    }

    public void setTransGroupId(Succursale transGroupId) {
        this.transGroupId = transGroupId;
    }

    public Succursale getTransPayergroupid() {
        return transPayergroupid;
    }

    public void setTransPayergroupid(Succursale transPayergroupid) {
        this.transPayergroupid = transPayergroupid;
    }

    public Guichet getTransGuichetId() {
        return transGuichetId;
    }

    public void setTransGuichetId(Guichet transGuichetId) {
        this.transGuichetId = transGuichetId;
    }

    public Parametreftp getTransParamFtp() {
        return transParamFtp;
    }

    public void setTransParamFtp(Parametreftp transParamFtp) {
        this.transParamFtp = transParamFtp;
    }

    public Sender getTransSenId() {
        return transSenId;
    }

    public void setTransSenId(Sender transSenId) {
        this.transSenId = transSenId;
    }

    public Parametrews getTransParamWs() {
        return transParamWs;
    }

    public void setTransParamWs(Parametrews transParamWs) {
        this.transParamWs = transParamWs;
    }

    public Service getTransServiceId() {
        return transServiceId;
    }

    public void setTransServiceId(Service transServiceId) {
        this.transServiceId = transServiceId;
    }

    @XmlTransient
    public List<TransactionDocument> getTransactionDocumentList() {
        return transactionDocumentList;
    }

    public void setTransactionDocumentList(List<TransactionDocument> transactionDocumentList) {
        this.transactionDocumentList = transactionDocumentList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (transId != null ? transId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TransactionEuing)) {
            return false;
        }
        TransactionEuing other = (TransactionEuing) object;
        if ((this.transId == null && other.transId != null) || (this.transId != null && !this.transId.equals(other.transId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TransactionEuing{" + "transId=" + transId + ", transGuichet=" + transGuichet + ", transAgency=" + transAgency + ", transCompany=" + transCompany + ", transGroup=" + transGroup + ", transCasher=" + transCasher + ", transOriginCountry=" + transOriginCountry + ", transDestCountry=" + transDestCountry + ", transExchangeRate=" + transExchangeRate + ", transExchangeRateCust=" + transExchangeRateCust + ", transExchangeRateMargin=" + transExchangeRateMargin + ", transAmountSent=" + transAmountSent + ", transOthers=" + transOthers + ", transFees=" + transFees + ", transTaxes=" + transTaxes + ", transTotal=" + transTotal + ", transAmountToPaid=" + transAmountToPaid + ", transReference=" + transReference + ", transInterfacage=" + transInterfacage + ", transStatut=" + transStatut + ", transSens=" + transSens + ", transServiceName=" + transServiceName + ", transSenFirstname=" + transSenFirstname + ", transSenLastname=" + transSenLastname + ", transSenCountry=" + transSenCountry + ", transSenCity=" + transSenCity + ", transSenPostalCode=" + transSenPostalCode + ", transSenAddress=" + transSenAddress + ", transSenPhone=" + transSenPhone + ", transSenEmail=" + transSenEmail + ", transSenIdnumber=" + transSenIdnumber + ", transSenIdtype=" + transSenIdtype + ", transBenFirstname=" + transBenFirstname + ", transBenLastname=" + transBenLastname + ", transBenCountry=" + transBenCountry + ", transBenCity=" + transBenCity + ", transBenPostalCode=" + transBenPostalCode + ", transBenAddress=" + transBenAddress + ", transBenPhone=" + transBenPhone + ", transBenEmail=" + transBenEmail + ", transBenIdnumber=" + transBenIdnumber + ", transBenIdtype=" + transBenIdtype + ", transMotif=" + transMotif + ", transMessage=" + transMessage + ", transComisAgence=" + transComisAgence + ", transComisCompanie=" + transComisCompanie + ", transComisGroupe=" + transComisGroupe + ", transComisSysteme=" + transComisSysteme + ", transDateCreated=" + transDateCreated + ", transDateValidated=" + transDateValidated + ", transDateCancelRequest=" + transDateCancelRequest + ", transDateCanceled=" + transDateCanceled + ", transDatePaid=" + transDatePaid + ", transDateAccounted=" + transDateAccounted + ", transDateInstance=" + transDateInstance + ", transInstance=" + transInstance + ", transComisGuichet=" + transComisGuichet + ", transPayergroup=" + transPayergroup + ", transPayercompany=" + transPayercompany + ", transPayeragency=" + transPayeragency + ", transPayerguichet=" + transPayerguichet + ", transPayercasher=" + transPayercasher + ", transMtchangerate=" + transMtchangerate + ", transMtpartnercode=" + transMtpartnercode + ", transMtsenderemail=" + transMtsenderemail + ", transMtstatus=" + transMtstatus + ", transMttransactionid=" + transMttransactionid + ", transMttransactionnumber=" + transMttransactionnumber + ", transMtvaliditytime=" + transMtvaliditytime + ", transMtobservation=" + transMtobservation + ", transPayeramount=" + transPayeramount + ", transPayerfees=" + transPayerfees + ", transPayertaxes=" + transPayertaxes + ", transPayerothertaxes=" + transPayerothertaxes + ", transPayercomisgroup=" + transPayercomisgroup + ", transPayercomiscompany=" + transPayercomiscompany + ", transPayercomisagency=" + transPayercomisagency + ", transPayercomisguichet=" + transPayercomisguichet + ", transPayercomissysteme=" + transPayercomissysteme + ", transKeepFeeAnnulation=" + transKeepFeeAnnulation + ", transExRateSysComp=" + transExRateSysComp + ", transExRateSysGrp=" + transExRateSysGrp + ", transTarifPreferentielUsed=" + transTarifPreferentielUsed + ", transRemise=" + transRemise + ", transPaisRes=" + transPaisRes + ", comFlag=" + comFlag + ", ng1=" + ng1 + ", ng2=" + ng2 + ", ng3=" + ng3 + ", ng4=" + ng4 + ", ng5=" + ng5 + ", transRefundDate=" + transRefundDate + ", transIncidenceDate=" + transIncidenceDate + ", comFlagPaid=" + comFlagPaid + ", transComisSysReal=" + transComisSysReal + ", transComisGrpReal=" + transComisGrpReal + ", transTaxesGrpReal=" + transTaxesGrpReal + ", transExRateSysCompPayer=" + transExRateSysCompPayer + ", transExRateSysGrpPayer=" + transExRateSysGrpPayer + ", transComisSysRealPayer=" + transComisSysRealPayer + ", transComisGrpRealPayer=" + transComisGrpRealPayer + ", transTaxesGrpRealPayer=" + transTaxesGrpRealPayer + ", transDateAuthorization=" + transDateAuthorization + ", transDateHold=" + transDateHold + ", transDateTransmission=" + transDateTransmission + ", eventsList=" + eventsList + ", transBenId=" + transBenId + ", transAgencyId=" + transAgencyId + ", transPayeragencyid=" + transPayeragencyid + ", transPayercasherid=" + transPayercasherid + ", transPayercompanyid=" + transPayercompanyid + ", transUserid=" + transUserid + ", transPaisResId=" + transPaisResId + ", transIncidenceUserId=" + transIncidenceUserId + ", transDestCountryId=" + transDestCountryId + ", transCompanyId=" + transCompanyId + ", transOriginCountryId=" + transOriginCountryId + ", transTarifId=" + transTarifId + ", transRefundUserId=" + transRefundUserId + ", transDestCur=" + transDestCur + ", transOriginCur=" + transOriginCur + ", transPayerguichetid=" + transPayerguichetid + ", transGroupId=" + transGroupId + ", transPayergroupid=" + transPayergroupid + ", transGuichetId=" + transGuichetId + ", transParamFtp=" + transParamFtp + ", transSenId=" + transSenId + ", transParamWs=" + transParamWs + ", transServiceId=" + transServiceId + ", transactionDocumentList=" + transactionDocumentList + '}';
    }

}
