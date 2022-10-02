package com.ufi.euing.client.business.impl;

import com.ufi.euing.client.business.AbstractFacade;
import com.ufi.euing.client.business.TransactionBillService;
import com.ufi.euing.client.entity.*;
import com.ufi.euing.client.integration.billpay.CalculTarifBill;
import com.ufi.euing.client.integration.billpay.CheckBillResponse;
import com.ufi.euing.client.repositories.TransactionBillRepository;
import com.ufi.euing.client.repositories.TransactionEuingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class TransactionBillServiceImpl extends AbstractFacade<TransactionBill> implements TransactionBillService {

    final TransactionBillRepository transactionBillRepository;

    @Autowired
    private EntityManager em;

    public TransactionBillServiceImpl(TransactionBillRepository transactionBillRepository) {
        super(TransactionBill.class);
        this.transactionBillRepository = transactionBillRepository;
    }


    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public List<TransactionBill> findByStatus(String transactionStatus) {
        Query query = em.createNamedQuery("TransactionEuing.findByTransStatut");
        query.setParameter("transStatut", transactionStatus);
        return query.getResultList();
    }

    @Override
    public TransactionBill findById(Long transId) {
        Query query = em.createNamedQuery("TransactionEuing.findByTransId");
        query.setParameter("transId", transId);
        List<TransactionBill> list = query.getResultList();
        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }

    @Override
    public TransactionBill save(TransactionBill transaction) {
        return transactionBillRepository.save(transaction);
    }

    @Override
    public List<TransactionBill> searchTransactions(Date searchDateStart, Date searchDateEnd, String searchStatut, Succursale searchGroupeId, Compagnie searchCompagnieId, Agence searchAgenceId, Guichet searchGuichetId, String searchReference, Service searchServiceId) {
        em.getEntityManagerFactory().getCache().evict(TransactionBill.class);
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery q = cb.createQuery(TransactionBill.class);
        Root<TransactionBill> trx = q.from(TransactionBill.class);
        q.select(trx);
        List<Predicate> predicateList = new ArrayList<>();

        if (searchGuichetId != null) {
            predicateList.add(cb.equal(trx.get("transGuichetId"), searchGuichetId));
        }

        if (searchCompagnieId != null) {
            predicateList.add(cb.equal(trx.get("transCompagnieId"), searchCompagnieId));
        }

        if (searchGroupeId != null) {
            predicateList.add(cb.equal(trx.get("transGroupId"), searchGroupeId));
        }

        if (searchAgenceId != null) {
            predicateList.add(cb.equal(trx.get("transAgenceId"), searchAgenceId));
        }

        if (searchServiceId != null) {
            //serviceCode
            //predicateList.add(cb.equal(trx.get("transServiceId"), searchServiceId));
            predicateList.add(cb.equal(trx.get("transServiceId"), searchServiceId));
        }

        if (!"".equals(searchReference.trim().toUpperCase())) {
            predicateList.add(cb.equal(cb.trim(cb.upper(trx.<String>get("transReference"))), searchReference.trim().toUpperCase()));
        }

        if (!"-1".equals(searchStatut.trim().toUpperCase())) {
            switch (searchStatut.trim().toUpperCase()) {
                case "I":
                    predicateList.add(cb.greaterThanOrEqualTo(trx.<Date>get("transDateCreate"), searchDateStart));
                    predicateList.add(cb.lessThanOrEqualTo(trx.<Date>get("transDateCreate"), searchDateEnd));
                    predicateList.add(cb.equal(cb.trim(cb.upper(trx.<String>get("transactionStatus"))), searchStatut.trim().toUpperCase()));
                    break;
                case "U":
                    predicateList.add(cb.greaterThanOrEqualTo(trx.<Date>get("transDateCreate"), searchDateStart));
                    predicateList.add(cb.lessThanOrEqualTo(trx.<Date>get("transDateCreate"), searchDateEnd));
                    predicateList.add(cb.equal(cb.trim(cb.upper(trx.<String>get("transactionStatus"))), searchStatut.trim().toUpperCase()));
                    break;
                case "P":
                    predicateList.add(cb.greaterThanOrEqualTo(trx.<Date>get("transDateCreate"), searchDateStart));
                    predicateList.add(cb.lessThanOrEqualTo(trx.<Date>get("transDateCreate"), searchDateEnd));
                    predicateList.add(cb.equal(cb.trim(cb.upper(trx.<String>get("transactionStatus"))), searchStatut.trim().toUpperCase()));
                    break;
                default:
                    break;
            }

        } else {
            predicateList.add(cb.greaterThanOrEqualTo(trx.<Date>get("transDateCreate"), searchDateStart));
            predicateList.add(cb.lessThanOrEqualTo(trx.<Date>get("transDateCreate"), searchDateEnd));
        }

        Predicate[] predicateArray = new Predicate[predicateList.size()];
        predicateList.toArray(predicateArray);
        q.where(predicateArray);
        q.orderBy(cb.asc(trx.get("transReference")), cb.asc(trx.get("id")), cb.asc(trx.get("transServiceName")));
        em.getEntityManagerFactory().getCache().evict(TransactionBill.class);
        TypedQuery<TransactionBill> query = em.createQuery(q);

        List<TransactionBill> results = query.getResultList();
        System.out.println("Nombre ===> " + results.size());
        System.out.println(">>>> Query ===> " + query.toString());
        return results;
    }

    @Override
    public GenericsResponse<CalculTarifBill> calculFee(Compagnie compagnie, Guichet guichet, Long serviceID, String ServiceName, BigDecimal amount, BigDecimal userID, String userLogin) {
        try {
            // set parameters
            Double amountSent = null;
            Double amountToPaid = null;
            Double totalToPaid = null;
            Double fees = null;
            Double othersFees = null;
            Double exchangeRate = null;
            Integer res = null;
            String resMessage = null;
            // CALL SP
            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("SP_CAL_TARIF_BILL_TRANSACTION");
            // IN PARAMETERS
            storedProcedure.registerStoredProcedureParameter("pCOMPANY_ID", Long.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pSERVICE_ID", Long.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_ORIGIN_CUR", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_DEST_CUR", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pDESTINATION_COUNTRY_CODE", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pCALCULATION_MODE", Long.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pAMOUNT", Long.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pSENDER_ID", Long.class, ParameterMode.IN);
            // OUT PARAMETERS
            storedProcedure.registerStoredProcedureParameter("pAMOUNT_TO_SENT", Double.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("pAMOUNT_TO_PAID", Double.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("pTOTAL_TO_PAID", Double.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("pFEES", Double.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("pOTHERS_FEES", Double.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("pEXCHANGE_RATE", Double.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("pRES", Integer.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("pRES_MESSAGE", String.class, ParameterMode.OUT);
            // SET IN PARAMETERS
            storedProcedure.setParameter("pCOMPANY_ID", guichet.getAgence().getCompagnie().getId());
            storedProcedure.setParameter("pSERVICE_ID", serviceID);
            storedProcedure.setParameter("pTRANS_ORIGIN_CUR", compagnie.getDeviseCompensation().getDevCode());
            storedProcedure.setParameter("pTRANS_DEST_CUR", guichet.getAgence().getCompagnie().getDeviseCompensation().getDevCode());
            storedProcedure.setParameter("pDESTINATION_COUNTRY_CODE", compagnie.getPays().getPsCode());
            storedProcedure.setParameter("pCALCULATION_MODE", compagnie.getModeCalculPartner());
            storedProcedure.setParameter("pAMOUNT", amount);
            storedProcedure.setParameter("pSENDER_ID", null);
            // INITIALISE OUT PARAMETERS
            storedProcedure.setParameter("pAMOUNT_TO_SENT", amountSent);
            storedProcedure.setParameter("pAMOUNT_TO_PAID", amountToPaid);
            storedProcedure.setParameter("pTOTAL_TO_PAID", totalToPaid);
            storedProcedure.setParameter("pFEES", fees);
            storedProcedure.setParameter("pOTHERS_FEES", othersFees);
            storedProcedure.setParameter("pEXCHANGE_RATE", exchangeRate);
            storedProcedure.setParameter("pRES", res);
            storedProcedure.setParameter("pRES_MESSAGE", resMessage);
            // execute stored procedure
            storedProcedure.execute();
            // GET OUT PARAMETERS
            res = (Integer) storedProcedure.getOutputParameterValue("pRES");
            resMessage = (String) storedProcedure.getOutputParameterValue("pRES_MESSAGE");
            System.out.println("res SP_CAL_TARIF_BILL_TRANSACTION = " + res);
            if (res == 1) {
                amountSent = (double) storedProcedure.getOutputParameterValue("pAMOUNT_TO_SENT");
                amountToPaid = (double) storedProcedure.getOutputParameterValue("pAMOUNT_TO_PAID");
                totalToPaid = (double) storedProcedure.getOutputParameterValue("pTOTAL_TO_PAID");
                fees = (double) storedProcedure.getOutputParameterValue("pFEES");
                othersFees = (double) storedProcedure.getOutputParameterValue("pOTHERS_FEES");
                exchangeRate = (double) storedProcedure.getOutputParameterValue("pEXCHANGE_RATE");
                return new GenericsResponse<>(new CalculTarifBill(res, resMessage, amountSent, amountToPaid, totalToPaid, fees, othersFees, exchangeRate));
            } else {
                //return new GenericsResponse<>(500, "Une erreur s'est produite lors du calcul des tarrif. Code d'erreur " + res + "::: message " + resMessage, null);
                return new GenericsResponse<>(500, resMessage, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new GenericsResponse<>(500, "Une erreur s'est produite lors du calcul des tarrif. Détails : " + e.getMessage(), null);
        }
    }

    @Override
    public GenericsResponse<CheckBillResponse> precheckBillPayment(Compagnie compagnie, Guichet guichet, Long serviceID, String ServiceName, BigDecimal amount, BigDecimal userID, String userLogin) {
        try {
            // set parameters
            Integer res = null;
            String resMessage = null;
            // CALL SP
            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("SP_PRECHECK_BILL_PAYMENT");
            // IN PARAMETERS
            storedProcedure.registerStoredProcedureParameter("pTRANS_PAYER_GUICHET_ID", Long.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_PAYER_GUICHET", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_PAYER_AGENCY_ID", Long.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_PAYER_AGENCY", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_PAYER_COMPANY", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_PAYER_COMPANY_ID", Long.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_PAYER_GROUP", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_PAYER_GROUP_ID", Long.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_PAYER_CASHER", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_PAYER_USERID", BigDecimal.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_ORIGIN_COUNTRY", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_ORIGIN_COUNTRY_ID", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_DEST_COUNTRY", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_DEST_COUNTRY_ID", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_ORIGIN_CUR", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_DEST_CUR", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_SERVICE_ID", Long.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_SERVICE_NAME", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_AMOUNT_TO_PAID", Long.class, ParameterMode.IN);
            // OUT PARAMETERS
            storedProcedure.registerStoredProcedureParameter("pRES", Integer.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("pRES_MESSAGE", String.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("pTRANS_ID", Long.class, ParameterMode.OUT);
            // SET IN PARAMETERS
            storedProcedure.setParameter("pTRANS_PAYER_GUICHET_ID", guichet.getId());
            storedProcedure.setParameter("pTRANS_PAYER_GUICHET", guichet.getLibelle());
            storedProcedure.setParameter("pTRANS_PAYER_AGENCY_ID", guichet.getAgence().getId());
            storedProcedure.setParameter("pTRANS_PAYER_AGENCY", guichet.getAgence().getLibelle());
            storedProcedure.setParameter("pTRANS_PAYER_COMPANY", guichet.getAgence().getCompagnie().getLibelle());
            storedProcedure.setParameter("pTRANS_PAYER_COMPANY_ID", guichet.getAgence().getCompagnie().getId());
            storedProcedure.setParameter("pTRANS_PAYER_GROUP", guichet.getAgence().getCompagnie().getSuccursale().getLibelle());
            storedProcedure.setParameter("pTRANS_PAYER_GROUP_ID", guichet.getAgence().getCompagnie().getSuccursale().getId());
            storedProcedure.setParameter("pTRANS_PAYER_CASHER", userLogin);
            storedProcedure.setParameter("pTRANS_PAYER_USERID", userID);
            storedProcedure.setParameter("pTRANS_ORIGIN_COUNTRY", compagnie.getPays().getPsLibelle());
            storedProcedure.setParameter("pTRANS_ORIGIN_COUNTRY_ID", compagnie.getPays().getPsCode());
            storedProcedure.setParameter("pTRANS_DEST_COUNTRY", guichet.getAgence().getCompagnie().getPays().getPsLibelle());
            storedProcedure.setParameter("pTRANS_DEST_COUNTRY_ID", guichet.getAgence().getCompagnie().getPays().getPsCode());
            storedProcedure.setParameter("pTRANS_ORIGIN_CUR", compagnie.getDeviseCompensation().getDevCode());
            storedProcedure.setParameter("pTRANS_DEST_CUR", guichet.getAgence().getCompagnie().getDeviseCompensation().getDevCode());
            storedProcedure.setParameter("pTRANS_SERVICE_ID", serviceID);
            storedProcedure.setParameter("pTRANS_SERVICE_NAME", ServiceName);
            storedProcedure.setParameter("pTRANS_AMOUNT_TO_PAID", amount);
            // INITIALISE OUT PARAMETERS
            storedProcedure.setParameter("pRES", res);
            storedProcedure.setParameter("pRES_MESSAGE", resMessage);
            storedProcedure.setParameter("pTRANS_ID", null);
            // execute stored procedure
            storedProcedure.execute();
            // SET OUT PARAMETERS
            res = (Integer) storedProcedure.getOutputParameterValue("pRES");
            resMessage = (String) storedProcedure.getOutputParameterValue("pRES_MESSAGE");

            System.out.println("res precheckBillPayment = " + res);
            System.out.println("resMessage precheckBillPayment = " + resMessage);
            if (res == 0) {
                return new GenericsResponse<>(new CheckBillResponse(res, null, resMessage, null));
            } else {
                //return new GenericsResponse<>(500, "Une erreur s'est produite lors du precheck des trarifier. Code d'erreur " + res + ". Details " + resMessage, null);
                return new GenericsResponse<>(500, resMessage, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new GenericsResponse<>(500, "Une erreur s'est produite lors du precheck des trarifier. Détails : " + e.getMessage(), null);
        }
    }

    @Override
    public GenericsResponse<TransactionBill> insertTrxBillPay(TransactionBill transaction) {
        try {
            // set parameters
            Integer res = null;
            String resMessage = null;
            Long transId = null;
            String transRef = null;
            // CALL SP
            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("SP_INSERT_TRX_BILL_PAY");
            // IN PARAMETERS
            storedProcedure.registerStoredProcedureParameter("pTRANS_DATE_COMPLETED", Date.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_DEPOSITOR_NAME", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_DEPOSITOR_PHONE", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_THIRD_PARTY_REF", String.class, ParameterMode.IN);

            storedProcedure.registerStoredProcedureParameter("pTRANS1", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS2", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS3", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS4", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS5", String.class, ParameterMode.IN);

            storedProcedure.registerStoredProcedureParameter("pTRANS_GUICHET_ID", Long.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_GUICHET", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_AGENCE_ID", Long.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_AGENCY", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_COMPAGNIE_ID", Long.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_COMPANY", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_GROUPE_ID", Long.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_GROUP", String.class, ParameterMode.IN);

            storedProcedure.registerStoredProcedureParameter("pTRANS_DEST_COMPAGNIE_ID", Long.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_DEST_COMPAGNIE_CODE", String.class, ParameterMode.IN);

            storedProcedure.registerStoredProcedureParameter("pTRANS_DEST_COUNTRY_ID", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_DEST_COUNTRY", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_ORIGIN_COUNTRY_ID", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_ORIGIN_COUNTRY", String.class, ParameterMode.IN);

            storedProcedure.registerStoredProcedureParameter("pTRANS_SERVICE_ID", Long.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_SERVICE_NAME", String.class, ParameterMode.IN);

            storedProcedure.registerStoredProcedureParameter("pTRANS_ORIGIN_CUR", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_DEST_CUR", String.class, ParameterMode.IN);

            storedProcedure.registerStoredProcedureParameter("pTRANS_AMOUNT", BigDecimal.class, ParameterMode.IN);

            storedProcedure.registerStoredProcedureParameter("pTRANS_CASHER", String.class, ParameterMode.IN);

            storedProcedure.registerStoredProcedureParameter("pTRANS_USERID", BigDecimal.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_USER_CREATE", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_USER_MODIF", String.class, ParameterMode.IN);

            storedProcedure.registerStoredProcedureParameter("pTRANS_RETURN_CODE", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_RETURN_MESSAGE", Long.class, ParameterMode.IN);

            storedProcedure.registerStoredProcedureParameter("pTRANS_STATUS", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pFACTURE", Long.class, ParameterMode.IN);
            // OUT PARAMETERS
            storedProcedure.registerStoredProcedureParameter("pRES", Integer.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("pRES_MESSAGE", String.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("pTRANS_ID", Long.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("pReference", String.class, ParameterMode.OUT);
            // SET IN PARAMETERS
            storedProcedure.setParameter("pTRANS_DATE_COMPLETED", transaction.getDateCompleted());
            storedProcedure.setParameter("pTRANS_DEPOSITOR_NAME", transaction.getDepositorName());
            storedProcedure.setParameter("pTRANS_DEPOSITOR_PHONE", transaction.getDepositorPhone());
            storedProcedure.setParameter("pTRANS_THIRD_PARTY_REF", transaction.getThirdPartyReference()); // update on response

            storedProcedure.setParameter("pTRANS1", transaction.getTrans1());
            storedProcedure.setParameter("pTRANS2", transaction.getTrans2());
            storedProcedure.setParameter("pTRANS3", transaction.getTrans3());
            storedProcedure.setParameter("pTRANS4", transaction.getTrans4());
            storedProcedure.setParameter("pTRANS5", transaction.getTrans5());

            storedProcedure.setParameter("pTRANS_GUICHET_ID", transaction.getTransGuichetId().getId());
            storedProcedure.setParameter("pTRANS_GUICHET", transaction.getTransGuichet());
            storedProcedure.setParameter("pTRANS_AGENCE_ID", transaction.getTransAgenceId().getId());
            storedProcedure.setParameter("pTRANS_AGENCY", transaction.getTransAgency());
            storedProcedure.setParameter("pTRANS_COMPAGNIE_ID", transaction.getTransCompagnieId().getId());
            storedProcedure.setParameter("pTRANS_COMPANY", transaction.getTransCompany());
            storedProcedure.setParameter("pTRANS_GROUPE_ID", transaction.getTransGroupId().getId());
            storedProcedure.setParameter("pTRANS_GROUP", transaction.getTransGroup());

            storedProcedure.setParameter("pTRANS_DEST_COMPAGNIE_ID", transaction.getTransDestCompagnieId().getId());
            storedProcedure.setParameter("pTRANS_DEST_COMPAGNIE_CODE", transaction.getTransDestCompagnieId().getCodePartenaire());

            storedProcedure.setParameter("pTRANS_DEST_COUNTRY_ID", transaction.getTransDestCountryId().getPsCode());
            storedProcedure.setParameter("pTRANS_DEST_COUNTRY", transaction.getTransDestCountry());
            storedProcedure.setParameter("pTRANS_ORIGIN_COUNTRY_ID", transaction.getTransOriginCountryId().getPsCode());
            storedProcedure.setParameter("pTRANS_ORIGIN_COUNTRY", transaction.getTransOriginCountry());

            storedProcedure.setParameter("pTRANS_SERVICE_ID", transaction.getTransServiceId().getId());
            storedProcedure.setParameter("pTRANS_SERVICE_NAME", transaction.getTransServiceName());

            storedProcedure.setParameter("pTRANS_ORIGIN_CUR", transaction.getTransOriginCur());
            storedProcedure.setParameter("pTRANS_DEST_CUR", transaction.getTransDestCur());

            storedProcedure.setParameter("pTRANS_AMOUNT", transaction.getTransAmountSent());

            storedProcedure.setParameter("pTRANS_CASHER", transaction.getTransCasher());

            storedProcedure.setParameter("pTRANS_USERID", transaction.getTransUserid().getUsrId());
            storedProcedure.setParameter("pTRANS_USER_CREATE", transaction.getTransUserCreate());
            storedProcedure.setParameter("pTRANS_USER_MODIF", transaction.getTransUserModif());

            storedProcedure.setParameter("pTRANS_RETURN_CODE", transaction.getTransactionReturnCode()); // update from partner response
            storedProcedure.setParameter("pTRANS_RETURN_MESSAGE", transaction.getTransactionReturnMessage()); // update from partner response

            storedProcedure.setParameter("pTRANS_STATUS", transaction.getTransactionStatus());
            //storedProcedure.setParameter("pFACTURE", transaction.getFacture().getId());
            // INITIALISE OUT PARAMETERS
            storedProcedure.setParameter("pRES", res);
            storedProcedure.setParameter("pRES_MESSAGE", resMessage);
            storedProcedure.setParameter("pTRANS_ID", transId);
            storedProcedure.setParameter("pReference", transRef);
            // execute stored procedure
            storedProcedure.execute();
            // SET OUT PARAMETERS
            res = (Integer) storedProcedure.getOutputParameterValue("pRES");
            resMessage = (String) storedProcedure.getOutputParameterValue("pRES_MESSAGE");

            System.out.println("res insertTrxBillPay = " + res);
            System.out.println("resMessage insertTrxBillPay = " + resMessage);

            if (res == 0) {
                transId = Long.valueOf(storedProcedure.getOutputParameterValue("pTRANS_ID") + "");
                transRef = (String) storedProcedure.getOutputParameterValue("pReference");

                System.out.println("transId insertTrxBillPay = " + transId);
                System.out.println("transRef insertTrxBillPay = " + transRef);

                transaction.setTransactionReturnCode(String.valueOf(res));
                transaction.setTransactionReturnMessage(resMessage);
                transaction.setTransReference(transRef);
                transaction.setId(transId);
                em.getEntityManagerFactory().getCache().evict(TransactionBill.class);
                TransactionBill result = (TransactionBill) em.createNativeQuery("select * from transaction_bill where id =" + transId, TransactionBill.class).getSingleResult();
                System.out.println("result ::: " + result);
                return new GenericsResponse<>(result);
            } else {
                //return new GenericsResponse<>(500, "Une erreur s'est produite lors de la validation du payement. Code d'erreur " + res + "::: message " + resMessage, null);
                return new GenericsResponse<>(500, resMessage, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new GenericsResponse<>(500, "Une erreur s'est produite lors  de la validation du payement. Détails : " + e.getMessage(), null);
        }
    }

    @Override
    public GenericsResponse cancelTransaction(Long transId, BigDecimal userId, Integer keepFees, String commentaire) {
        try {
            // set parameters
            Integer res = null;
            String resMessage = null;
            // CALL SP
            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("SP_CANCEL_TRANSACTION_BILL");
            // IN PARAMETERS
            storedProcedure.registerStoredProcedureParameter("pTRANS_ID", Long.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("puser_id", BigDecimal.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pKEEP_FEES", Integer.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pCOMMENTAIRE", String.class, ParameterMode.IN);
            // OUT PARAMETERS
            storedProcedure.registerStoredProcedureParameter("pRES", Integer.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("pRES_MESSAGE", String.class, ParameterMode.OUT);
            // SET IN PARAMETERS
            storedProcedure.setParameter("pTRANS_ID", transId);
            storedProcedure.setParameter("puser_id", userId);
            storedProcedure.setParameter("pKEEP_FEES", keepFees);
            storedProcedure.setParameter("pCOMMENTAIRE", commentaire);
            // INITIALISE OUT PARAMETERS
            storedProcedure.setParameter("pRES", res);
            storedProcedure.setParameter("pRES_MESSAGE", resMessage);
            // execute stored procedure
            storedProcedure.execute();
            // GET OUT PARAMETERS
            res = (Integer) storedProcedure.getOutputParameterValue("pRES");
            resMessage = (String) storedProcedure.getOutputParameterValue("pRES_MESSAGE");
            System.out.println("res SP_CANCEL_TRANSACTION = " + res);
            if (res == 0) {
                return new GenericsResponse<>(null);
            } else {
                //return new GenericsResponse<>(500, "Une erreur s'est produite lors du calcul des tarrif. Code d'erreur " + res + "::: message " + resMessage, null);
                return new GenericsResponse<>(500, resMessage, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new GenericsResponse<>(500, "Une erreur s'est produite lors de l'annulation ou remboursement de la transation. Détails : " + e.getMessage(), null);
        }
    }
}
