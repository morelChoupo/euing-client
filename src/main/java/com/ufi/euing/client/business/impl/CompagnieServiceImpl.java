package com.ufi.euing.client.business.impl;

import com.ufi.euing.client.business.AbstractFacade;
import com.ufi.euing.client.business.CompagnieService;
import com.ufi.euing.client.entity.Compagnie;
import com.ufi.euing.client.repositories.CompagnieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;


@Component
public class CompagnieServiceImpl extends AbstractFacade<Compagnie> implements CompagnieService {

    final CompagnieRepository compagnieRepository;

    public CompagnieServiceImpl(CompagnieRepository compagnieRepository) {
        super(Compagnie.class);
        this.compagnieRepository = compagnieRepository;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Autowired
    private EntityManager em;

    @Override
    public Compagnie findByCodePartenaire(String code) {
        List<Compagnie> list = new ArrayList<>();
        Query query = em.createNamedQuery("Compagnie.findByCodePartenaire");
        query.setParameter("codePartenaire", code);
        list = query.getResultList();
        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }

    @Override
    public List<Compagnie> findBySuccursale(Long code) {
        Query query = em.createNamedQuery("Compagnie.findBySuccursaleCodeStatut");
        query.setParameter("succursale", code);
        query.setParameter("statut", Short.valueOf("1"));
        return query.getResultList();
    }

    @Override
    public List<Compagnie> findByPays(String code) {
        Query query = em.createNamedQuery("Compagnie.findByPaysCode");
        Short sh = 1;
        query.setParameter("pays", code);
        query.setParameter("statut", sh);
        return query.getResultList();
    }



    @Override
    public List<Compagnie> getCompagnieByStatut(short status) {
        List<Compagnie> list;
        list = new ArrayList<>();
        Query query = getEntityManager().createNamedQuery("Compagnie.findByStatut").setParameter("statut", status);
        list = query.getResultList();
        return list;
    }
}
