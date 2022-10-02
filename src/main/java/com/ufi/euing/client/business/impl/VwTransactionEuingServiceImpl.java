package com.ufi.euing.client.business.impl;

import com.ufi.euing.client.business.VwTransactionEuingService;
import com.ufi.euing.client.entity.*;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Component
public class VwTransactionEuingServiceImpl implements VwTransactionEuingService {

    private EntityManager em;

    @Override
    public List<VwTransactionEuing> searchTransactions(Date searchDateStart, Date searchDateEnd, Succursale searchGroupeId, Compagnie searchCompagnieId, Agence searchAgenceId, Guichet searchGuichetId, String searchReference, String searchCodeClient, String searchExpediteur, String searchBeneficiaire, Pays searchDestinationCode, String searchStatut, Service searchServiceId, String searchIdSender, String searchIdBenef, String searchPhoneSender, String searchPhoneBenef) {
        //To change body of generated methods, choose Tools | Templates.
        em.getEntityManagerFactory().getCache().evict(VwTransactionEuing.class);
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery q = cb.createQuery(VwTransactionEuing.class);

        Root<Sender> sender = q.from(Sender.class);
        Root<VwTransactionEuing> trx = q.from(VwTransactionEuing.class);

        q.select(trx);

        List<Predicate> predicateList = new ArrayList<>();

        if (searchGroupeId.getId() != -1) {
            predicateList.add(cb.equal(trx.get("transGroupId"), BigInteger.valueOf(searchGroupeId.getId())));
        }

        if (searchCompagnieId.getId() != -1) {
            predicateList.add(cb.equal(trx.get("transCompanyId"), BigInteger.valueOf(searchCompagnieId.getId())));
        }

        if (searchAgenceId.getId() != -1) {
            predicateList.add(cb.equal(trx.get("transAgencyId"), BigInteger.valueOf(searchAgenceId.getId())));
        }

        if (searchGuichetId.getId() != -1) {
            predicateList.add(cb.equal(trx.get("transGuichetId"), BigInteger.valueOf(searchGuichetId.getId())));
        }

        if (searchServiceId.getId() != -1) {
            //serviceCode
            //predicateList.add(cb.equal(trx.get("transServiceId"), searchServiceId));
            predicateList.add(cb.equal(trx.get("serviceCode"), searchServiceId.getCode()));
        }

        if (!"-1".equals(searchDestinationCode.getPsCode())) {
            predicateList.add(cb.equal(trx.get("transDestCountryId"), searchDestinationCode.getPsCode()));
        }

        if (!"".equals(searchReference.trim().toUpperCase())) {
            predicateList.add(cb.equal(cb.trim(cb.upper(trx.<String>get("transReference"))), searchReference.trim().toUpperCase()));
        }

        if (!"".equals(searchExpediteur.trim().toUpperCase())) {
            predicateList.add(cb.equal(
                    cb.concat(cb.concat(trx.<String>get("transSenLastname"), " "), trx.<String>get("transSenFirstname")),
                    searchExpediteur.trim().toUpperCase()
                    )
            );
        }

        if (!"".equals(searchBeneficiaire.trim().toUpperCase())) {
            predicateList.add(cb.equal(
                    cb.concat(cb.concat(trx.<String>get("transBenLastname"), " "), trx.<String>get("transBenFirstname")),
                    searchBeneficiaire.trim().toUpperCase()
                    )
            );
        }
        if (!"".equals(searchIdSender.trim().toUpperCase())) {
            predicateList.add(cb.equal(cb.trim(cb.upper(trx.<String>get("transSenIdnumber"))), searchIdSender.trim().toUpperCase()));
        }

        if (!"".equals(searchIdBenef.trim().toUpperCase())) {
            predicateList.add(cb.equal(cb.trim(cb.upper(trx.<String>get("transBenIdnumber"))), searchIdBenef.trim().toUpperCase()));
        }

        if (!"".equals(searchPhoneSender.trim().toUpperCase())) {
            predicateList.add(cb.equal(cb.trim(cb.upper(trx.<String>get("transSenPhone"))), searchPhoneSender.trim().toUpperCase()));
        }

        if (!"".equals(searchPhoneBenef.trim().toUpperCase())) {
            predicateList.add(cb.equal(cb.trim(cb.upper(trx.<String>get("transBenPhone"))), searchPhoneBenef.trim().toUpperCase()));
        }

        if (!"-1".equals(searchStatut.trim().toUpperCase())) {
            switch (searchStatut.trim().toUpperCase()) {
                case "N":
                    predicateList.add(cb.greaterThanOrEqualTo(trx.<Date>get("transDateCreated"), searchDateStart));
                    predicateList.add(cb.lessThanOrEqualTo(trx.<Date>get("transDateCreated"), searchDateEnd));
                    predicateList.add(cb.equal(cb.trim(cb.upper(trx.<String>get("transStatut"))), searchStatut.trim().toUpperCase()));
                    break;
                case "V":
                    predicateList.add(cb.greaterThanOrEqualTo(trx.<Date>get("transDateValidated"), searchDateStart));
                    predicateList.add(cb.lessThanOrEqualTo(trx.<Date>get("transDateValidated"), searchDateEnd));
                    predicateList.add(cb.equal(cb.trim(cb.upper(trx.<String>get("transStatut"))), searchStatut.trim().toUpperCase()));
                    break;
                case "C":
                    predicateList.add(cb.greaterThanOrEqualTo(trx.<Date>get("transDateCanceled"), searchDateStart));
                    predicateList.add(cb.lessThanOrEqualTo(trx.<Date>get("transDateCanceled"), searchDateEnd));
                    predicateList.add(cb.equal(cb.trim(cb.upper(trx.<String>get("transStatut"))), searchStatut.trim().toUpperCase()));
                    break;
                case "P":
                    predicateList.add(cb.greaterThanOrEqualTo(trx.<Date>get("transDatePaid"), searchDateStart));
                    predicateList.add(cb.lessThanOrEqualTo(trx.<Date>get("transDatePaid"), searchDateEnd));
                    predicateList.add(cb.equal(cb.trim(cb.upper(trx.<String>get("transStatut"))), searchStatut.trim().toUpperCase()));
                    break;
                case "A":
                    predicateList.add(cb.greaterThanOrEqualTo(trx.<Date>get("transDateAuthorization"), searchDateStart));
                    predicateList.add(cb.lessThanOrEqualTo(trx.<Date>get("transDateAuthorization"), searchDateEnd));
                    predicateList.add(cb.equal(cb.trim(cb.upper(trx.<String>get("transStatut"))), searchStatut.trim().toUpperCase()));
                    break;
                case "S":
                    predicateList.add(cb.greaterThanOrEqualTo(trx.<Date>get("transDateTransmission"), searchDateStart));
                    predicateList.add(cb.lessThanOrEqualTo(trx.<Date>get("transDateTransmission"), searchDateEnd));
                    predicateList.add(cb.equal(cb.trim(cb.upper(trx.<String>get("transStatut"))), searchStatut.trim().toUpperCase()));
                    break;
                case "H":
                    predicateList.add(cb.greaterThanOrEqualTo(trx.<Date>get("transDateHold"), searchDateStart));
                    predicateList.add(cb.lessThanOrEqualTo(trx.<Date>get("transDateHold"), searchDateEnd));
                    predicateList.add(cb.equal(cb.trim(cb.upper(trx.<String>get("transStatut"))), searchStatut.trim().toUpperCase()));
                    break;
                case "Y":
                    predicateList.add(cb.greaterThanOrEqualTo(trx.<Date>get("transRefundDate"), searchDateStart));
                    predicateList.add(cb.lessThanOrEqualTo(trx.<Date>get("transRefundDate"), searchDateEnd));
                    predicateList.add(cb.equal(cb.trim(cb.upper(trx.<String>get("transStatut"))), "C"));
                    break;
                case "E":
                    predicateList.add(cb.greaterThanOrEqualTo(trx.<Date>get("transDateInstance"), searchDateStart));
                    predicateList.add(cb.lessThanOrEqualTo(trx.<Date>get("transDateInstance"), searchDateEnd));
                    predicateList.add(cb.equal(cb.trim(cb.upper(trx.<String>get("transStatut"))), "H"));
                    break;
                case "Z":
                    predicateList.add(cb.greaterThanOrEqualTo(trx.<Date>get("transIncidenceDate"), searchDateStart));
                    predicateList.add(cb.lessThanOrEqualTo(trx.<Date>get("transIncidenceDate"), searchDateEnd));
                    break;
                default:
                    break;
            }

        } else {
            predicateList.add(cb.greaterThanOrEqualTo(trx.<Date>get("transDateCreated"), searchDateStart));
            predicateList.add(cb.lessThanOrEqualTo(trx.<Date>get("transDateCreated"), searchDateEnd));
        }

        Predicate[] predicateArray = new Predicate[predicateList.size()];
        predicateList.toArray(predicateArray);
        q.where(predicateArray);
        q.orderBy(cb.asc(trx.get("transReference")), cb.asc(trx.get("transId")),cb.asc(trx.get("serviceCode")));
        em.getEntityManagerFactory().getCache().evict(VwTransactionEuing.class);
        TypedQuery<VwTransactionEuing> query = em.createQuery(q);

        List<VwTransactionEuing> results = query.getResultList();
        System.out.println("Nombre ===> " + results.size());
        System.out.println(">>>> Query ===> "+query.toString());
        return results;
    }

}
