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
import java.math.BigInteger;
import java.util.Date;

/**
 *
 * @author DNT
 */
@Entity
@Table(name = "VW_TRANSACTION_EUING")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VwTransactionEuing.findAll", query = "SELECT v FROM VwTransactionEuing v")
    , @NamedQuery(name = "VwTransactionEuing.findByRowkey", query = "SELECT v FROM VwTransactionEuing v WHERE v.rowkey = :rowkey")
    , @NamedQuery(name = "VwTransactionEuing.findByServiceCode", query = "SELECT v FROM VwTransactionEuing v WHERE v.serviceCode = :serviceCode")
    , @NamedQuery(name = "VwTransactionEuing.findByServiceName", query = "SELECT v FROM VwTransactionEuing v WHERE v.serviceName = :serviceName")
    , @NamedQuery(name = "VwTransactionEuing.findByTransId", query = "SELECT v FROM VwTransactionEuing v WHERE v.transId = :transId")
    , @NamedQuery(name = "VwTransactionEuing.findByTransGuichetId", query = "SELECT v FROM VwTransactionEuing v WHERE v.transGuichetId = :transGuichetId")
    , @NamedQuery(name = "VwTransactionEuing.findByTransGuichet", query = "SELECT v FROM VwTransactionEuing v WHERE v.transGuichet = :transGuichet")
    , @NamedQuery(name = "VwTransactionEuing.findByTransAgencyId", query = "SELECT v FROM VwTransactionEuing v WHERE v.transAgencyId = :transAgencyId")
    , @NamedQuery(name = "VwTransactionEuing.findByTransAgency", query = "SELECT v FROM VwTransactionEuing v WHERE v.transAgency = :transAgency")
    , @NamedQuery(name = "VwTransactionEuing.findByTransCompany", query = "SELECT v FROM VwTransactionEuing v WHERE v.transCompany = :transCompany")
    , @NamedQuery(name = "VwTransactionEuing.findByTransCompanyId", query = "SELECT v FROM VwTransactionEuing v WHERE v.transCompanyId = :transCompanyId")
    , @NamedQuery(name = "VwTransactionEuing.findByTransGroup", query = "SELECT v FROM VwTransactionEuing v WHERE v.transGroup = :transGroup")
    , @NamedQuery(name = "VwTransactionEuing.findByTransGroupId", query = "SELECT v FROM VwTransactionEuing v WHERE v.transGroupId = :transGroupId")
    , @NamedQuery(name = "VwTransactionEuing.findByTransCasher", query = "SELECT v FROM VwTransactionEuing v WHERE v.transCasher = :transCasher")
    , @NamedQuery(name = "VwTransactionEuing.findByTransUserid", query = "SELECT v FROM VwTransactionEuing v WHERE v.transUserid = :transUserid")
    , @NamedQuery(name = "VwTransactionEuing.findByTransOriginCountry", query = "SELECT v FROM VwTransactionEuing v WHERE v.transOriginCountry = :transOriginCountry")
    , @NamedQuery(name = "VwTransactionEuing.findByTransOriginCountryId", query = "SELECT v FROM VwTransactionEuing v WHERE v.transOriginCountryId = :transOriginCountryId")
    , @NamedQuery(name = "VwTransactionEuing.findByTransDestCountry", query = "SELECT v FROM VwTransactionEuing v WHERE v.transDestCountry = :transDestCountry")
    , @NamedQuery(name = "VwTransactionEuing.findByTransDestCountryId", query = "SELECT v FROM VwTransactionEuing v WHERE v.transDestCountryId = :transDestCountryId")
    , @NamedQuery(name = "VwTransactionEuing.findByTransOriginCur", query = "SELECT v FROM VwTransactionEuing v WHERE v.transOriginCur = :transOriginCur")
    , @NamedQuery(name = "VwTransactionEuing.findByTransDestCur", query = "SELECT v FROM VwTransactionEuing v WHERE v.transDestCur = :transDestCur")
    , @NamedQuery(name = "VwTransactionEuing.findByTransExchangeRate", query = "SELECT v FROM VwTransactionEuing v WHERE v.transExchangeRate = :transExchangeRate")
    , @NamedQuery(name = "VwTransactionEuing.findByTransExchangeRateCust", query = "SELECT v FROM VwTransactionEuing v WHERE v.transExchangeRateCust = :transExchangeRateCust")
    , @NamedQuery(name = "VwTransactionEuing.findByTransExchangeRateMargin", query = "SELECT v FROM VwTransactionEuing v WHERE v.transExchangeRateMargin = :transExchangeRateMargin")
    , @NamedQuery(name = "VwTransactionEuing.findByTransAmountSent", query = "SELECT v FROM VwTransactionEuing v WHERE v.transAmountSent = :transAmountSent")
    , @NamedQuery(name = "VwTransactionEuing.findByTransOthers", query = "SELECT v FROM VwTransactionEuing v WHERE v.transOthers = :transOthers")
    , @NamedQuery(name = "VwTransactionEuing.findByTransFees", query = "SELECT v FROM VwTransactionEuing v WHERE v.transFees = :transFees")
    , @NamedQuery(name = "VwTransactionEuing.findByTransTaxes", query = "SELECT v FROM VwTransactionEuing v WHERE v.transTaxes = :transTaxes")
    , @NamedQuery(name = "VwTransactionEuing.findByTransTotal", query = "SELECT v FROM VwTransactionEuing v WHERE v.transTotal = :transTotal")
    , @NamedQuery(name = "VwTransactionEuing.findByTransAmountToPaid", query = "SELECT v FROM VwTransactionEuing v WHERE v.transAmountToPaid = :transAmountToPaid")
    , @NamedQuery(name = "VwTransactionEuing.findByTransReference", query = "SELECT v FROM VwTransactionEuing v WHERE v.transReference = :transReference")
    , @NamedQuery(name = "VwTransactionEuing.findByTransInterfacage", query = "SELECT v FROM VwTransactionEuing v WHERE v.transInterfacage = :transInterfacage")
    , @NamedQuery(name = "VwTransactionEuing.findByTransStatut", query = "SELECT v FROM VwTransactionEuing v WHERE v.transStatut = :transStatut")
    , @NamedQuery(name = "VwTransactionEuing.findByTransSens", query = "SELECT v FROM VwTransactionEuing v WHERE v.transSens = :transSens")
    , @NamedQuery(name = "VwTransactionEuing.findByTransServiceId", query = "SELECT v FROM VwTransactionEuing v WHERE v.transServiceId = :transServiceId")
    , @NamedQuery(name = "VwTransactionEuing.findByTransServiceName", query = "SELECT v FROM VwTransactionEuing v WHERE v.transServiceName = :transServiceName")
    , @NamedQuery(name = "VwTransactionEuing.findByTransSenId", query = "SELECT v FROM VwTransactionEuing v WHERE v.transSenId = :transSenId")
    , @NamedQuery(name = "VwTransactionEuing.findByTransSenFirstname", query = "SELECT v FROM VwTransactionEuing v WHERE v.transSenFirstname = :transSenFirstname")
    , @NamedQuery(name = "VwTransactionEuing.findByTransSenLastname", query = "SELECT v FROM VwTransactionEuing v WHERE v.transSenLastname = :transSenLastname")
    , @NamedQuery(name = "VwTransactionEuing.findByTransSenCountry", query = "SELECT v FROM VwTransactionEuing v WHERE v.transSenCountry = :transSenCountry")
    , @NamedQuery(name = "VwTransactionEuing.findByTransSenCity", query = "SELECT v FROM VwTransactionEuing v WHERE v.transSenCity = :transSenCity")
    , @NamedQuery(name = "VwTransactionEuing.findByTransSenPostalCode", query = "SELECT v FROM VwTransactionEuing v WHERE v.transSenPostalCode = :transSenPostalCode")
    , @NamedQuery(name = "VwTransactionEuing.findByTransSenAddress", query = "SELECT v FROM VwTransactionEuing v WHERE v.transSenAddress = :transSenAddress")
    , @NamedQuery(name = "VwTransactionEuing.findByTransSenPhone", query = "SELECT v FROM VwTransactionEuing v WHERE v.transSenPhone = :transSenPhone")
    , @NamedQuery(name = "VwTransactionEuing.findByTransSenEmail", query = "SELECT v FROM VwTransactionEuing v WHERE v.transSenEmail = :transSenEmail")
    , @NamedQuery(name = "VwTransactionEuing.findByTransSenIdnumber", query = "SELECT v FROM VwTransactionEuing v WHERE v.transSenIdnumber = :transSenIdnumber")
    , @NamedQuery(name = "VwTransactionEuing.findByTransSenIdtype", query = "SELECT v FROM VwTransactionEuing v WHERE v.transSenIdtype = :transSenIdtype")
    , @NamedQuery(name = "VwTransactionEuing.findByTransBenId", query = "SELECT v FROM VwTransactionEuing v WHERE v.transBenId = :transBenId")
    , @NamedQuery(name = "VwTransactionEuing.findByTransBenFirstname", query = "SELECT v FROM VwTransactionEuing v WHERE v.transBenFirstname = :transBenFirstname")
    , @NamedQuery(name = "VwTransactionEuing.findByTransBenLastname", query = "SELECT v FROM VwTransactionEuing v WHERE v.transBenLastname = :transBenLastname")
    , @NamedQuery(name = "VwTransactionEuing.findByTransBenCountry", query = "SELECT v FROM VwTransactionEuing v WHERE v.transBenCountry = :transBenCountry")
    , @NamedQuery(name = "VwTransactionEuing.findByTransBenCity", query = "SELECT v FROM VwTransactionEuing v WHERE v.transBenCity = :transBenCity")
    , @NamedQuery(name = "VwTransactionEuing.findByTransBenPostalCode", query = "SELECT v FROM VwTransactionEuing v WHERE v.transBenPostalCode = :transBenPostalCode")
    , @NamedQuery(name = "VwTransactionEuing.findByTransBenAddress", query = "SELECT v FROM VwTransactionEuing v WHERE v.transBenAddress = :transBenAddress")
    , @NamedQuery(name = "VwTransactionEuing.findByTransBenPhone", query = "SELECT v FROM VwTransactionEuing v WHERE v.transBenPhone = :transBenPhone")
    , @NamedQuery(name = "VwTransactionEuing.findByTransBenEmail", query = "SELECT v FROM VwTransactionEuing v WHERE v.transBenEmail = :transBenEmail")
    , @NamedQuery(name = "VwTransactionEuing.findByTransBenIdnumber", query = "SELECT v FROM VwTransactionEuing v WHERE v.transBenIdnumber = :transBenIdnumber")
    , @NamedQuery(name = "VwTransactionEuing.findByTransBenIdtype", query = "SELECT v FROM VwTransactionEuing v WHERE v.transBenIdtype = :transBenIdtype")
    , @NamedQuery(name = "VwTransactionEuing.findByTransMotif", query = "SELECT v FROM VwTransactionEuing v WHERE v.transMotif = :transMotif")
    , @NamedQuery(name = "VwTransactionEuing.findByTransMessage", query = "SELECT v FROM VwTransactionEuing v WHERE v.transMessage = :transMessage")
    , @NamedQuery(name = "VwTransactionEuing.findByTransComisAgence", query = "SELECT v FROM VwTransactionEuing v WHERE v.transComisAgence = :transComisAgence")
    , @NamedQuery(name = "VwTransactionEuing.findByTransComisCompanie", query = "SELECT v FROM VwTransactionEuing v WHERE v.transComisCompanie = :transComisCompanie")
    , @NamedQuery(name = "VwTransactionEuing.findByTransComisGroupe", query = "SELECT v FROM VwTransactionEuing v WHERE v.transComisGroupe = :transComisGroupe")
    , @NamedQuery(name = "VwTransactionEuing.findByTransComisSysteme", query = "SELECT v FROM VwTransactionEuing v WHERE v.transComisSysteme = :transComisSysteme")
    , @NamedQuery(name = "VwTransactionEuing.findByTransDateCreated", query = "SELECT v FROM VwTransactionEuing v WHERE v.transDateCreated = :transDateCreated")
    , @NamedQuery(name = "VwTransactionEuing.findByTransDateValidated", query = "SELECT v FROM VwTransactionEuing v WHERE v.transDateValidated = :transDateValidated")
    , @NamedQuery(name = "VwTransactionEuing.findByTransDateCancelRequest", query = "SELECT v FROM VwTransactionEuing v WHERE v.transDateCancelRequest = :transDateCancelRequest")
    , @NamedQuery(name = "VwTransactionEuing.findByTransDateCanceled", query = "SELECT v FROM VwTransactionEuing v WHERE v.transDateCanceled = :transDateCanceled")
    , @NamedQuery(name = "VwTransactionEuing.findByTransDatePaid", query = "SELECT v FROM VwTransactionEuing v WHERE v.transDatePaid = :transDatePaid")
    , @NamedQuery(name = "VwTransactionEuing.findByTransDateAccounted", query = "SELECT v FROM VwTransactionEuing v WHERE v.transDateAccounted = :transDateAccounted")
    , @NamedQuery(name = "VwTransactionEuing.findByTransDateInstance", query = "SELECT v FROM VwTransactionEuing v WHERE v.transDateInstance = :transDateInstance")
    , @NamedQuery(name = "VwTransactionEuing.findByTransInstance", query = "SELECT v FROM VwTransactionEuing v WHERE v.transInstance = :transInstance")
    , @NamedQuery(name = "VwTransactionEuing.findByTransParamFtp", query = "SELECT v FROM VwTransactionEuing v WHERE v.transParamFtp = :transParamFtp")
    , @NamedQuery(name = "VwTransactionEuing.findByTransParamWs", query = "SELECT v FROM VwTransactionEuing v WHERE v.transParamWs = :transParamWs")
    , @NamedQuery(name = "VwTransactionEuing.findByTransComisGuichet", query = "SELECT v FROM VwTransactionEuing v WHERE v.transComisGuichet = :transComisGuichet")
    , @NamedQuery(name = "VwTransactionEuing.findByTransPayergroup", query = "SELECT v FROM VwTransactionEuing v WHERE v.transPayergroup = :transPayergroup")
    , @NamedQuery(name = "VwTransactionEuing.findByTransPayercompany", query = "SELECT v FROM VwTransactionEuing v WHERE v.transPayercompany = :transPayercompany")
    , @NamedQuery(name = "VwTransactionEuing.findByTransPayeragency", query = "SELECT v FROM VwTransactionEuing v WHERE v.transPayeragency = :transPayeragency")
    , @NamedQuery(name = "VwTransactionEuing.findByTransPayerguichet", query = "SELECT v FROM VwTransactionEuing v WHERE v.transPayerguichet = :transPayerguichet")
    , @NamedQuery(name = "VwTransactionEuing.findByTransPayercasher", query = "SELECT v FROM VwTransactionEuing v WHERE v.transPayercasher = :transPayercasher")
    , @NamedQuery(name = "VwTransactionEuing.findByTransPayergroupid", query = "SELECT v FROM VwTransactionEuing v WHERE v.transPayergroupid = :transPayergroupid")
    , @NamedQuery(name = "VwTransactionEuing.findByTransPayercompanyid", query = "SELECT v FROM VwTransactionEuing v WHERE v.transPayercompanyid = :transPayercompanyid")
    , @NamedQuery(name = "VwTransactionEuing.findByTransPayeragencyid", query = "SELECT v FROM VwTransactionEuing v WHERE v.transPayeragencyid = :transPayeragencyid")
    , @NamedQuery(name = "VwTransactionEuing.findByTransPayerguichetid", query = "SELECT v FROM VwTransactionEuing v WHERE v.transPayerguichetid = :transPayerguichetid")
    , @NamedQuery(name = "VwTransactionEuing.findByTransPayercasherid", query = "SELECT v FROM VwTransactionEuing v WHERE v.transPayercasherid = :transPayercasherid")
    , @NamedQuery(name = "VwTransactionEuing.findByTransMtchangerate", query = "SELECT v FROM VwTransactionEuing v WHERE v.transMtchangerate = :transMtchangerate")
    , @NamedQuery(name = "VwTransactionEuing.findByTransMtpartnercode", query = "SELECT v FROM VwTransactionEuing v WHERE v.transMtpartnercode = :transMtpartnercode")
    , @NamedQuery(name = "VwTransactionEuing.findByTransMtsenderemail", query = "SELECT v FROM VwTransactionEuing v WHERE v.transMtsenderemail = :transMtsenderemail")
    , @NamedQuery(name = "VwTransactionEuing.findByTransMtstatus", query = "SELECT v FROM VwTransactionEuing v WHERE v.transMtstatus = :transMtstatus")
    , @NamedQuery(name = "VwTransactionEuing.findByTransMttransactionid", query = "SELECT v FROM VwTransactionEuing v WHERE v.transMttransactionid = :transMttransactionid")
    , @NamedQuery(name = "VwTransactionEuing.findByTransMttransactionnumber", query = "SELECT v FROM VwTransactionEuing v WHERE v.transMttransactionnumber = :transMttransactionnumber")
    , @NamedQuery(name = "VwTransactionEuing.findByTransMtvaliditytime", query = "SELECT v FROM VwTransactionEuing v WHERE v.transMtvaliditytime = :transMtvaliditytime")
    , @NamedQuery(name = "VwTransactionEuing.findByTransMtobservation", query = "SELECT v FROM VwTransactionEuing v WHERE v.transMtobservation = :transMtobservation")
    , @NamedQuery(name = "VwTransactionEuing.findByTransPayeramount", query = "SELECT v FROM VwTransactionEuing v WHERE v.transPayeramount = :transPayeramount")
    , @NamedQuery(name = "VwTransactionEuing.findByTransPayerfees", query = "SELECT v FROM VwTransactionEuing v WHERE v.transPayerfees = :transPayerfees")
    , @NamedQuery(name = "VwTransactionEuing.findByTransPayertaxes", query = "SELECT v FROM VwTransactionEuing v WHERE v.transPayertaxes = :transPayertaxes")
    , @NamedQuery(name = "VwTransactionEuing.findByTransPayerothertaxes", query = "SELECT v FROM VwTransactionEuing v WHERE v.transPayerothertaxes = :transPayerothertaxes")
    , @NamedQuery(name = "VwTransactionEuing.findByTransPayercomisgroup", query = "SELECT v FROM VwTransactionEuing v WHERE v.transPayercomisgroup = :transPayercomisgroup")
    , @NamedQuery(name = "VwTransactionEuing.findByTransPayercomiscompany", query = "SELECT v FROM VwTransactionEuing v WHERE v.transPayercomiscompany = :transPayercomiscompany")
    , @NamedQuery(name = "VwTransactionEuing.findByTransPayercomisagency", query = "SELECT v FROM VwTransactionEuing v WHERE v.transPayercomisagency = :transPayercomisagency")
    , @NamedQuery(name = "VwTransactionEuing.findByTransPayercomisguichet", query = "SELECT v FROM VwTransactionEuing v WHERE v.transPayercomisguichet = :transPayercomisguichet")
    , @NamedQuery(name = "VwTransactionEuing.findByTransPayercomissysteme", query = "SELECT v FROM VwTransactionEuing v WHERE v.transPayercomissysteme = :transPayercomissysteme")
    , @NamedQuery(name = "VwTransactionEuing.findByTransKeepFeeAnnulation", query = "SELECT v FROM VwTransactionEuing v WHERE v.transKeepFeeAnnulation = :transKeepFeeAnnulation")
    , @NamedQuery(name = "VwTransactionEuing.findByTransExRateSysComp", query = "SELECT v FROM VwTransactionEuing v WHERE v.transExRateSysComp = :transExRateSysComp")
    , @NamedQuery(name = "VwTransactionEuing.findByTransExRateSysGrp", query = "SELECT v FROM VwTransactionEuing v WHERE v.transExRateSysGrp = :transExRateSysGrp")
    , @NamedQuery(name = "VwTransactionEuing.findByComFlag", query = "SELECT v FROM VwTransactionEuing v WHERE v.comFlag = :comFlag")
    , @NamedQuery(name = "VwTransactionEuing.findByNg1", query = "SELECT v FROM VwTransactionEuing v WHERE v.ng1 = :ng1")
    , @NamedQuery(name = "VwTransactionEuing.findByNg2", query = "SELECT v FROM VwTransactionEuing v WHERE v.ng2 = :ng2")
    , @NamedQuery(name = "VwTransactionEuing.findByNg3", query = "SELECT v FROM VwTransactionEuing v WHERE v.ng3 = :ng3")
    , @NamedQuery(name = "VwTransactionEuing.findByNg4", query = "SELECT v FROM VwTransactionEuing v WHERE v.ng4 = :ng4")
    , @NamedQuery(name = "VwTransactionEuing.findByNg5", query = "SELECT v FROM VwTransactionEuing v WHERE v.ng5 = :ng5")
    , @NamedQuery(name = "VwTransactionEuing.findByTransTarifPreferentielUsed", query = "SELECT v FROM VwTransactionEuing v WHERE v.transTarifPreferentielUsed = :transTarifPreferentielUsed")
    , @NamedQuery(name = "VwTransactionEuing.findByTransTarifId", query = "SELECT v FROM VwTransactionEuing v WHERE v.transTarifId = :transTarifId")
    , @NamedQuery(name = "VwTransactionEuing.findByTransRemise", query = "SELECT v FROM VwTransactionEuing v WHERE v.transRemise = :transRemise")
    , @NamedQuery(name = "VwTransactionEuing.findByTransPaisResId", query = "SELECT v FROM VwTransactionEuing v WHERE v.transPaisResId = :transPaisResId")
    , @NamedQuery(name = "VwTransactionEuing.findByTransPaisRes", query = "SELECT v FROM VwTransactionEuing v WHERE v.transPaisRes = :transPaisRes")
    , @NamedQuery(name = "VwTransactionEuing.findByTransRefundUserId", query = "SELECT v FROM VwTransactionEuing v WHERE v.transRefundUserId = :transRefundUserId")
    , @NamedQuery(name = "VwTransactionEuing.findByTransRefundDate", query = "SELECT v FROM VwTransactionEuing v WHERE v.transRefundDate = :transRefundDate")
    , @NamedQuery(name = "VwTransactionEuing.findByTransIncidenceDate", query = "SELECT v FROM VwTransactionEuing v WHERE v.transIncidenceDate = :transIncidenceDate")
    , @NamedQuery(name = "VwTransactionEuing.findByTransIncidenceUserId", query = "SELECT v FROM VwTransactionEuing v WHERE v.transIncidenceUserId = :transIncidenceUserId")
    , @NamedQuery(name = "VwTransactionEuing.findByComFlagPaid", query = "SELECT v FROM VwTransactionEuing v WHERE v.comFlagPaid = :comFlagPaid")
    , @NamedQuery(name = "VwTransactionEuing.findByTransComisSysReal", query = "SELECT v FROM VwTransactionEuing v WHERE v.transComisSysReal = :transComisSysReal")
    , @NamedQuery(name = "VwTransactionEuing.findByTransComisGrpReal", query = "SELECT v FROM VwTransactionEuing v WHERE v.transComisGrpReal = :transComisGrpReal")
    , @NamedQuery(name = "VwTransactionEuing.findByTransExRateSysCompPayer", query = "SELECT v FROM VwTransactionEuing v WHERE v.transExRateSysCompPayer = :transExRateSysCompPayer")
    , @NamedQuery(name = "VwTransactionEuing.findByTransExRateSysGrpPayer", query = "SELECT v FROM VwTransactionEuing v WHERE v.transExRateSysGrpPayer = :transExRateSysGrpPayer")
    , @NamedQuery(name = "VwTransactionEuing.findByTransComisSysRealPayer", query = "SELECT v FROM VwTransactionEuing v WHERE v.transComisSysRealPayer = :transComisSysRealPayer")
    , @NamedQuery(name = "VwTransactionEuing.findByTransComisGrpRealPayer", query = "SELECT v FROM VwTransactionEuing v WHERE v.transComisGrpRealPayer = :transComisGrpRealPayer")
    , @NamedQuery(name = "VwTransactionEuing.findByTransTaxesGrpRealPayer", query = "SELECT v FROM VwTransactionEuing v WHERE v.transTaxesGrpRealPayer = :transTaxesGrpRealPayer")
    , @NamedQuery(name = "VwTransactionEuing.findByTransTaxesGrpReal", query = "SELECT v FROM VwTransactionEuing v WHERE v.transTaxesGrpReal = :transTaxesGrpReal")
    , @NamedQuery(name = "VwTransactionEuing.findByTransDateAuthorization", query = "SELECT v FROM VwTransactionEuing v WHERE v.transDateAuthorization = :transDateAuthorization")
    , @NamedQuery(name = "VwTransactionEuing.findByTransDateTransmission", query = "SELECT v FROM VwTransactionEuing v WHERE v.transDateTransmission = :transDateTransmission")
    , @NamedQuery(name = "VwTransactionEuing.findByTransDateHold", query = "SELECT v FROM VwTransactionEuing v WHERE v.transDateHold = :transDateHold")
    , @NamedQuery(name = "VwTransactionEuing.findByTransInstanceDelay", query = "SELECT v FROM VwTransactionEuing v WHERE v.transInstanceDelay = :transInstanceDelay")})
public class VwTransactionEuing implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 295)
    @Column(name = "ROWKEY")
    @Id
    private String rowkey;
    @Size(max = 21)
    @Column(name = "SERVICE_CODE")
    private String serviceCode;
    @Size(max = 255)
    @Column(name = "SERVICE_NAME")
    private String serviceName;
    @Column(name = "TRANS_ID")
    private BigInteger transId;
    @Column(name = "TRANS_GUICHET_ID")
    private BigInteger transGuichetId;
    @Size(max = 255)
    @Column(name = "TRANS_GUICHET")
    private String transGuichet;
    @Column(name = "TRANS_AGENCY_ID")
    private BigInteger transAgencyId;
    @Size(max = 255)
    @Column(name = "TRANS_AGENCY")
    private String transAgency;
    @Size(max = 255)
    @Column(name = "TRANS_COMPANY")
    private String transCompany;
    @Column(name = "TRANS_COMPANY_ID")
    private BigInteger transCompanyId;
    @Size(max = 255)
    @Column(name = "TRANS_GROUP")
    private String transGroup;
    @Column(name = "TRANS_GROUP_ID")
    private BigInteger transGroupId;
    @Size(max = 255)
    @Column(name = "TRANS_CASHER")
    private String transCasher;
    @Column(name = "TRANS_USERID")
    private BigInteger transUserid;
    @Size(max = 255)
    @Column(name = "TRANS_ORIGIN_COUNTRY")
    private String transOriginCountry;
    @Size(max = 3)
    @Column(name = "TRANS_ORIGIN_COUNTRY_ID")
    private String transOriginCountryId;
    @Size(max = 255)
    @Column(name = "TRANS_DEST_COUNTRY")
    private String transDestCountry;
    @Size(max = 3)
    @Column(name = "TRANS_DEST_COUNTRY_ID")
    private String transDestCountryId;
    @Size(max = 3)
    @Column(name = "TRANS_ORIGIN_CUR")
    private String transOriginCur;
    @Size(max = 3)
    @Column(name = "TRANS_DEST_CUR")
    private String transDestCur;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "TRANS_EXCHANGE_RATE")
    private BigDecimal transExchangeRate;
    @Column(name = "TRANS_EXCHANGE_RATE_CUST")
    private BigDecimal transExchangeRateCust;
    @Column(name = "TRANS_EXCHANGE_RATE_MARGIN")
    private BigDecimal transExchangeRateMargin;
    @Column(name = "TRANS_AMOUNT_SENT")
    private BigDecimal transAmountSent;
    @Column(name = "TRANS_OTHERS")
    private BigDecimal transOthers;
    @Column(name = "TRANS_FEES")
    private BigDecimal transFees;
    @Column(name = "TRANS_TAXES")
    private BigDecimal transTaxes;
    @Column(name = "TRANS_TOTAL")
    private BigDecimal transTotal;
    @Column(name = "TRANS_AMOUNT_TO_PAID")
    private BigDecimal transAmountToPaid;
    @Size(max = 255)
    @Column(name = "TRANS_REFERENCE")
    private String transReference;
    @Size(max = 1)
    @Column(name = "TRANS_INTERFACAGE")
    private String transInterfacage;
    @Size(max = 1)
    @Column(name = "TRANS_STATUT")
    private String transStatut;
    @Size(max = 20)
    @Column(name = "TRANS_SENS")
    private String transSens;
    @Column(name = "TRANS_SERVICE_ID")
    private BigInteger transServiceId;
    @Size(max = 255)
    @Column(name = "TRANS_SERVICE_NAME")
    private String transServiceName;
    @Column(name = "TRANS_SEN_ID")
    private BigInteger transSenId;
    @Size(max = 255)
    @Column(name = "TRANS_SEN_FIRSTNAME")
    private String transSenFirstname;
    @Size(max = 255)
    @Column(name = "TRANS_SEN_LASTNAME")
    private String transSenLastname;
    @Size(max = 255)
    @Column(name = "TRANS_SEN_COUNTRY")
    private String transSenCountry;
    @Size(max = 255)
    @Column(name = "TRANS_SEN_CITY")
    private String transSenCity;
    @Size(max = 255)
    @Column(name = "TRANS_SEN_POSTAL_CODE")
    private String transSenPostalCode;
    @Size(max = 255)
    @Column(name = "TRANS_SEN_ADDRESS")
    private String transSenAddress;
    @Size(max = 50)
    @Column(name = "TRANS_SEN_PHONE")
    private String transSenPhone;
    @Size(max = 255)
    @Column(name = "TRANS_SEN_EMAIL")
    private String transSenEmail;
    @Size(max = 50)
    @Column(name = "TRANS_SEN_IDNUMBER")
    private String transSenIdnumber;
    @Size(max = 255)
    @Column(name = "TRANS_SEN_IDTYPE")
    private String transSenIdtype;
    @Column(name = "TRANS_BEN_ID")
    private BigInteger transBenId;
    @Size(max = 255)
    @Column(name = "TRANS_BEN_FIRSTNAME")
    private String transBenFirstname;
    @Size(max = 255)
    @Column(name = "TRANS_BEN_LASTNAME")
    private String transBenLastname;
    @Size(max = 255)
    @Column(name = "TRANS_BEN_COUNTRY")
    private String transBenCountry;
    @Size(max = 255)
    @Column(name = "TRANS_BEN_CITY")
    private String transBenCity;
    @Size(max = 255)
    @Column(name = "TRANS_BEN_POSTAL_CODE")
    private String transBenPostalCode;
    @Size(max = 255)
    @Column(name = "TRANS_BEN_ADDRESS")
    private String transBenAddress;
    @Size(max = 50)
    @Column(name = "TRANS_BEN_PHONE")
    private String transBenPhone;
    @Size(max = 255)
    @Column(name = "TRANS_BEN_EMAIL")
    private String transBenEmail;
    @Size(max = 50)
    @Column(name = "TRANS_BEN_IDNUMBER")
    private String transBenIdnumber;
    @Size(max = 255)
    @Column(name = "TRANS_BEN_IDTYPE")
    private String transBenIdtype;
    @Size(max = 255)
    @Column(name = "TRANS_MOTIF")
    private String transMotif;
    @Size(max = 255)
    @Column(name = "TRANS_MESSAGE")
    private String transMessage;
    @Column(name = "TRANS_COMIS_AGENCE")
    private BigDecimal transComisAgence;
    @Column(name = "TRANS_COMIS_COMPANIE")
    private BigDecimal transComisCompanie;
    @Column(name = "TRANS_COMIS_GROUPE")
    private BigDecimal transComisGroupe;
    @Column(name = "TRANS_COMIS_SYSTEME")
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
    @Column(name = "TRANS_INSTANCE")
    private String transInstance;
    @Column(name = "TRANS_PARAM_FTP")
    private BigInteger transParamFtp;
    @Column(name = "TRANS_PARAM_WS")
    private BigInteger transParamWs;
    @Column(name = "TRANS_COMIS_GUICHET")
    private BigDecimal transComisGuichet;
    @Size(max = 255)
    @Column(name = "TRANS_PAYERGROUP")
    private String transPayergroup;
    @Size(max = 255)
    @Column(name = "TRANS_PAYERCOMPANY")
    private String transPayercompany;
    @Size(max = 255)
    @Column(name = "TRANS_PAYERAGENCY")
    private String transPayeragency;
    @Size(max = 255)
    @Column(name = "TRANS_PAYERGUICHET")
    private String transPayerguichet;
    @Size(max = 255)
    @Column(name = "TRANS_PAYERCASHER")
    private String transPayercasher;
    @Column(name = "TRANS_PAYERGROUPID")
    private BigInteger transPayergroupid;
    @Column(name = "TRANS_PAYERCOMPANYID")
    private BigInteger transPayercompanyid;
    @Column(name = "TRANS_PAYERAGENCYID")
    private BigInteger transPayeragencyid;
    @Column(name = "TRANS_PAYERGUICHETID")
    private BigInteger transPayerguichetid;
    @Column(name = "TRANS_PAYERCASHERID")
    private BigInteger transPayercasherid;
    @Column(name = "TRANS_MTCHANGERATE")
    private BigDecimal transMtchangerate;
    @Size(max = 255)
    @Column(name = "TRANS_MTPARTNERCODE")
    private String transMtpartnercode;
    @Size(max = 255)
    @Column(name = "TRANS_MTSENDEREMAIL")
    private String transMtsenderemail;
    @Size(max = 255)
    @Column(name = "TRANS_MTSTATUS")
    private String transMtstatus;
    @Size(max = 255)
    @Column(name = "TRANS_MTTRANSACTIONID")
    private String transMttransactionid;
    @Size(max = 255)
    @Column(name = "TRANS_MTTRANSACTIONNUMBER")
    private String transMttransactionnumber;
    @Column(name = "TRANS_MTVALIDITYTIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date transMtvaliditytime;
    @Size(max = 255)
    @Column(name = "TRANS_MTOBSERVATION")
    private String transMtobservation;
    @Column(name = "TRANS_PAYERAMOUNT")
    private BigDecimal transPayeramount;
    @Column(name = "TRANS_PAYERFEES")
    private BigDecimal transPayerfees;
    @Column(name = "TRANS_PAYERTAXES")
    private BigDecimal transPayertaxes;
    @Column(name = "TRANS_PAYEROTHERTAXES")
    private BigDecimal transPayerothertaxes;
    @Column(name = "TRANS_PAYERCOMISGROUP")
    private BigDecimal transPayercomisgroup;
    @Column(name = "TRANS_PAYERCOMISCOMPANY")
    private BigDecimal transPayercomiscompany;
    @Column(name = "TRANS_PAYERCOMISAGENCY")
    private BigDecimal transPayercomisagency;
    @Column(name = "TRANS_PAYERCOMISGUICHET")
    private BigDecimal transPayercomisguichet;
    @Column(name = "TRANS_PAYERCOMISSYSTEME")
    private BigDecimal transPayercomissysteme;
    @Column(name = "TRANS_KEEP_FEE_ANNULATION")
    private Short transKeepFeeAnnulation;
    @Column(name = "TRANS_EX_RATE_SYS_COMP")
    private BigDecimal transExRateSysComp;
    @Column(name = "TRANS_EX_RATE_SYS_GRP")
    private BigDecimal transExRateSysGrp;
    @Size(max = 1)
    @Column(name = "COM_FLAG")
    private String comFlag;
    @Size(max = 255)
    @Column(name = "NG1")
    private String ng1;
    @Size(max = 255)
    @Column(name = "NG2")
    private String ng2;
    @Size(max = 255)
    @Column(name = "NG3")
    private String ng3;
    @Size(max = 255)
    @Column(name = "NG4")
    private String ng4;
    @Size(max = 255)
    @Column(name = "NG5")
    private String ng5;
    @Column(name = "TRANS_TARIF_PREFERENTIEL_USED")
    private Short transTarifPreferentielUsed;
    @Column(name = "TRANS_TARIF_ID")
    private BigInteger transTarifId;
    @Column(name = "TRANS_REMISE")
    private BigDecimal transRemise;
    @Size(max = 3)
    @Column(name = "TRANS_PAIS_RES_ID")
    private String transPaisResId;
    @Size(max = 255)
    @Column(name = "TRANS_PAIS_RES")
    private String transPaisRes;
    @Column(name = "TRANS_REFUND_USER_ID")
    private BigInteger transRefundUserId;
    @Column(name = "TRANS_REFUND_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date transRefundDate;
    @Column(name = "TRANS_INCIDENCE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date transIncidenceDate;
    @Column(name = "TRANS_INCIDENCE_USER_ID")
    private BigInteger transIncidenceUserId;
    @Size(max = 1)
    @Column(name = "COM_FLAG_PAID")
    private String comFlagPaid;
    @Column(name = "TRANS_COMIS_SYS_REAL")
    private BigDecimal transComisSysReal;
    @Column(name = "TRANS_COMIS_GRP_REAL")
    private BigDecimal transComisGrpReal;
    @Column(name = "TRANS_EX_RATE_SYS_COMP_PAYER")
    private BigDecimal transExRateSysCompPayer;
    @Column(name = "TRANS_EX_RATE_SYS_GRP_PAYER")
    private BigDecimal transExRateSysGrpPayer;
    @Column(name = "TRANS_COMIS_SYS_REAL_PAYER")
    private BigDecimal transComisSysRealPayer;
    @Column(name = "TRANS_COMIS_GRP_REAL_PAYER")
    private BigDecimal transComisGrpRealPayer;
    @Column(name = "TRANS_TAXES_GRP_REAL_PAYER")
    private BigDecimal transTaxesGrpRealPayer;
    @Column(name = "TRANS_TAXES_GRP_REAL")
    private BigDecimal transTaxesGrpReal;
    @Column(name = "TRANS_DATE_AUTHORIZATION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date transDateAuthorization;
    @Column(name = "TRANS_DATE_TRANSMISSION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date transDateTransmission;
    @Column(name = "TRANS_DATE_HOLD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date transDateHold;
    @Column(name = "TRANS_INSTANCE_DELAY")
    private BigInteger transInstanceDelay;

    public VwTransactionEuing() {
    }

    public String getRowkey() {
        return rowkey;
    }

    public void setRowkey(String rowkey) {
        this.rowkey = rowkey;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public BigInteger getTransId() {
        return transId;
    }

    public void setTransId(BigInteger transId) {
        this.transId = transId;
    }

    public BigInteger getTransGuichetId() {
        return transGuichetId;
    }

    public void setTransGuichetId(BigInteger transGuichetId) {
        this.transGuichetId = transGuichetId;
    }

    public String getTransGuichet() {
        return transGuichet;
    }

    public void setTransGuichet(String transGuichet) {
        this.transGuichet = transGuichet;
    }

    public BigInteger getTransAgencyId() {
        return transAgencyId;
    }

    public void setTransAgencyId(BigInteger transAgencyId) {
        this.transAgencyId = transAgencyId;
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

    public BigInteger getTransCompanyId() {
        return transCompanyId;
    }

    public void setTransCompanyId(BigInteger transCompanyId) {
        this.transCompanyId = transCompanyId;
    }

    public String getTransGroup() {
        return transGroup;
    }

    public void setTransGroup(String transGroup) {
        this.transGroup = transGroup;
    }

    public BigInteger getTransGroupId() {
        return transGroupId;
    }

    public void setTransGroupId(BigInteger transGroupId) {
        this.transGroupId = transGroupId;
    }

    public String getTransCasher() {
        return transCasher;
    }

    public void setTransCasher(String transCasher) {
        this.transCasher = transCasher;
    }

    public BigInteger getTransUserid() {
        return transUserid;
    }

    public void setTransUserid(BigInteger transUserid) {
        this.transUserid = transUserid;
    }

    public String getTransOriginCountry() {
        return transOriginCountry;
    }

    public void setTransOriginCountry(String transOriginCountry) {
        this.transOriginCountry = transOriginCountry;
    }

    public String getTransOriginCountryId() {
        return transOriginCountryId;
    }

    public void setTransOriginCountryId(String transOriginCountryId) {
        this.transOriginCountryId = transOriginCountryId;
    }

    public String getTransDestCountry() {
        return transDestCountry;
    }

    public void setTransDestCountry(String transDestCountry) {
        this.transDestCountry = transDestCountry;
    }

    public String getTransDestCountryId() {
        return transDestCountryId;
    }

    public void setTransDestCountryId(String transDestCountryId) {
        this.transDestCountryId = transDestCountryId;
    }

    public String getTransOriginCur() {
        return transOriginCur;
    }

    public void setTransOriginCur(String transOriginCur) {
        this.transOriginCur = transOriginCur;
    }

    public String getTransDestCur() {
        return transDestCur;
    }

    public void setTransDestCur(String transDestCur) {
        this.transDestCur = transDestCur;
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

    public BigInteger getTransServiceId() {
        return transServiceId;
    }

    public void setTransServiceId(BigInteger transServiceId) {
        this.transServiceId = transServiceId;
    }

    public String getTransServiceName() {
        return transServiceName;
    }

    public void setTransServiceName(String transServiceName) {
        this.transServiceName = transServiceName;
    }

    public BigInteger getTransSenId() {
        return transSenId;
    }

    public void setTransSenId(BigInteger transSenId) {
        this.transSenId = transSenId;
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

    public BigInteger getTransBenId() {
        return transBenId;
    }

    public void setTransBenId(BigInteger transBenId) {
        this.transBenId = transBenId;
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

    public BigInteger getTransParamFtp() {
        return transParamFtp;
    }

    public void setTransParamFtp(BigInteger transParamFtp) {
        this.transParamFtp = transParamFtp;
    }

    public BigInteger getTransParamWs() {
        return transParamWs;
    }

    public void setTransParamWs(BigInteger transParamWs) {
        this.transParamWs = transParamWs;
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

    public BigInteger getTransPayergroupid() {
        return transPayergroupid;
    }

    public void setTransPayergroupid(BigInteger transPayergroupid) {
        this.transPayergroupid = transPayergroupid;
    }

    public BigInteger getTransPayercompanyid() {
        return transPayercompanyid;
    }

    public void setTransPayercompanyid(BigInteger transPayercompanyid) {
        this.transPayercompanyid = transPayercompanyid;
    }

    public BigInteger getTransPayeragencyid() {
        return transPayeragencyid;
    }

    public void setTransPayeragencyid(BigInteger transPayeragencyid) {
        this.transPayeragencyid = transPayeragencyid;
    }

    public BigInteger getTransPayerguichetid() {
        return transPayerguichetid;
    }

    public void setTransPayerguichetid(BigInteger transPayerguichetid) {
        this.transPayerguichetid = transPayerguichetid;
    }

    public BigInteger getTransPayercasherid() {
        return transPayercasherid;
    }

    public void setTransPayercasherid(BigInteger transPayercasherid) {
        this.transPayercasherid = transPayercasherid;
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

    public Short getTransTarifPreferentielUsed() {
        return transTarifPreferentielUsed;
    }

    public void setTransTarifPreferentielUsed(Short transTarifPreferentielUsed) {
        this.transTarifPreferentielUsed = transTarifPreferentielUsed;
    }

    public BigInteger getTransTarifId() {
        return transTarifId;
    }

    public void setTransTarifId(BigInteger transTarifId) {
        this.transTarifId = transTarifId;
    }

    public BigDecimal getTransRemise() {
        return transRemise;
    }

    public void setTransRemise(BigDecimal transRemise) {
        this.transRemise = transRemise;
    }

    public String getTransPaisResId() {
        return transPaisResId;
    }

    public void setTransPaisResId(String transPaisResId) {
        this.transPaisResId = transPaisResId;
    }

    public String getTransPaisRes() {
        return transPaisRes;
    }

    public void setTransPaisRes(String transPaisRes) {
        this.transPaisRes = transPaisRes;
    }

    public BigInteger getTransRefundUserId() {
        return transRefundUserId;
    }

    public void setTransRefundUserId(BigInteger transRefundUserId) {
        this.transRefundUserId = transRefundUserId;
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

    public BigInteger getTransIncidenceUserId() {
        return transIncidenceUserId;
    }

    public void setTransIncidenceUserId(BigInteger transIncidenceUserId) {
        this.transIncidenceUserId = transIncidenceUserId;
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

    public BigDecimal getTransTaxesGrpReal() {
        return transTaxesGrpReal;
    }

    public void setTransTaxesGrpReal(BigDecimal transTaxesGrpReal) {
        this.transTaxesGrpReal = transTaxesGrpReal;
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

    public Date getTransDateHold() {
        return transDateHold;
    }

    public void setTransDateHold(Date transDateHold) {
        this.transDateHold = transDateHold;
    }

    public BigInteger getTransInstanceDelay() {
        return transInstanceDelay;
    }

    public void setTransInstanceDelay(BigInteger transInstanceDelay) {
        this.transInstanceDelay = transInstanceDelay;
    }
    
}
