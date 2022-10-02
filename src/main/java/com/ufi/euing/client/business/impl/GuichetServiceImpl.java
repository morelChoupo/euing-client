package com.ufi.euing.client.business.impl;

import com.ufi.euing.client.business.AbstractFacade;
import com.ufi.euing.client.business.GuichetService;
import com.ufi.euing.client.entity.Guichet;
import com.ufi.euing.client.exceptions.domain.EmailNotFoundException;
import com.ufi.euing.client.repositories.GuichetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;


@Component
public class GuichetServiceImpl extends AbstractFacade<Guichet> implements GuichetService {


    @Autowired
    private EntityManager em;

    final GuichetRepository repository;

    public GuichetServiceImpl(GuichetRepository repository) {
        super(Guichet.class);
        this.repository = repository;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Guichet getById(Long id) throws EmailNotFoundException {
        return repository.findById(id).orElseThrow(()-> new EmailNotFoundException("Guichet  not found"));
    }

    @Override
    public List<Guichet> findByAgence(Long code) {
        Query query = em.createNamedQuery("Guichet.findByAgence");
        query.setParameter("agence", code);
        return query.getResultList();
    }

    @Override
    public List<Guichet> getGuichetByStatut(short status) {
        List<Guichet> list;
        list = new ArrayList<>();
        Query query = em.createNamedQuery("Guichet.findByStatut").setParameter("statut", status);
        list = query.getResultList();
        return list;
    }


}
