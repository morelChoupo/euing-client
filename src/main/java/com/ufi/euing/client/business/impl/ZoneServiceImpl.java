package com.ufi.euing.client.business.impl;

import com.ufi.euing.client.business.AbstractFacade;
import com.ufi.euing.client.business.ZoneService;
import com.ufi.euing.client.entity.Zone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Component
public class ZoneServiceImpl extends AbstractFacade<Zone> implements ZoneService {

    public ZoneServiceImpl() {
        super(Zone.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Autowired
    private EntityManager em;



    @Override
    public List<Zone> findByPays(String code) {
        Query query = em.createNamedQuery("Zone.findByPaysStatut");
        query.setParameter("pays", code);
        Short sh = Short.valueOf("1");
        query.setParameter("statut", sh);
        return query.getResultList();
    }


    @Override
    public List<Zone> getZoneByStatut(short status) {
        List<Zone> list;
        list = new ArrayList<>();
        Query query = getEntityManager().createNamedQuery("Zone.findByStatut").setParameter("statut", status);
        list = query.getResultList();
        return list;
    }
}
