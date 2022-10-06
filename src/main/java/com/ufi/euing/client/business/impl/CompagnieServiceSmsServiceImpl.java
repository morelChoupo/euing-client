package com.ufi.euing.client.business.impl;

import com.ufi.euing.client.business.AbstractFacade;
import com.ufi.euing.client.business.CompagnieServiceSmsService;
import com.ufi.euing.client.entity.CompagnieServiceSms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;


@Component
public class CompagnieServiceSmsServiceImpl extends AbstractFacade<CompagnieServiceSms> implements CompagnieServiceSmsService {

    public CompagnieServiceSmsServiceImpl() {
        super(CompagnieServiceSms.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Autowired
    private EntityManager em;


    @Override
    public CompagnieServiceSms findByCompagnieAndService(Long cmp, Long serv) {
        short sh = 1;
        em.getEntityManagerFactory().getCache().evict(CompagnieServiceSms.class);
        Query query = em.createNamedQuery("CompagnieServiceSms.findByCompagnieAndService");
        query.setParameter("statut", sh);
        query.setParameter("compagnie", cmp);
        query.setParameter("service", serv);
        if (query.getResultList().isEmpty()) {
            return null;
        } else {
            return (CompagnieServiceSms) query.getResultList().get(0);
        }
    }
}
