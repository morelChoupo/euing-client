package com.ufi.euing.client.business.impl;

import com.ufi.euing.client.business.AbstractFacade;
import com.ufi.euing.client.business.FactureService;
import com.ufi.euing.client.entity.Facture;
import com.ufi.euing.client.repositories.FactureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;


@Component
public class FactureServiceImpl extends AbstractFacade<Facture> implements FactureService {

    @Autowired
    private EntityManager em;

    final FactureRepository factureRepository;

    public FactureServiceImpl(FactureRepository factureRepository) {
        super(Facture.class);
        this.factureRepository = factureRepository;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Facture createFctureDb(Facture facture) {
        return factureRepository.save(facture);
    }

    @Override
    public List<Facture> findByFactureNumber(String param) {
        Query query = em.createNamedQuery("Facture.findByFactureNumber");
        query.setParameter("factureNumber", param);
        return query.getResultList();
    }

    @Override
    public List<Facture> findByNumAbonne(String param) {
        Query query = em.createNamedQuery("Facture.findByNumeroAbonne");
        query.setParameter("numeroAbonne", param);
        return query.getResultList();
    }

    @Override
    public Facture findById(Long param) {
        Query query = em.createNamedQuery("Facture.findById");
        query.setParameter("id", param);
        List<Facture> list = query.getResultList();
        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }
}
