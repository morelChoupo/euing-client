package com.ufi.euing.client.business.impl;

import com.ufi.euing.client.business.AbstractFacade;
import com.ufi.euing.client.business.ParametreBaseService;
import com.ufi.euing.client.entity.ParametreBase;
import com.ufi.euing.client.repositories.ParametreBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;


@Component
public class ParametreBaseServiceImpl extends AbstractFacade<ParametreBase> implements ParametreBaseService {

    @Autowired
    private EntityManager em;

    final ParametreBaseRepository parametreBaseRepository;

    public ParametreBaseServiceImpl(ParametreBaseRepository parametreBaseRepository) {
        super(ParametreBase.class);
        this.parametreBaseRepository = parametreBaseRepository;
    }


    @Override
    protected EntityManager getEntityManager() {
        return em;
    }


    @Override
    public List<ParametreBase> findAll() {
        return parametreBaseRepository.findAll();
    }


    @Override
    public List<ParametreBase> findParametreSMS() {
        Query query = em.createNamedQuery("ParametreBase.findParamSMS");
        List<ParametreBase> list = new ArrayList<>();
        list = query.getResultList();
        return list;
    }

    @Override
    public List<ParametreBase> findByCle(String cle) {
        Query query = em.createNamedQuery("ParametreBase.findByCle");
        query.setParameter("cle", cle);
        List<ParametreBase> list = query.getResultList();
        return list;
    }
}
