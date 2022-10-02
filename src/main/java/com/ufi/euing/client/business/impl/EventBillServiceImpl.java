package com.ufi.euing.client.business.impl;

import com.ufi.euing.client.business.AbstractFacade;
import com.ufi.euing.client.business.EventBillService;
import com.ufi.euing.client.entity.EventsBill;
import com.ufi.euing.client.entity.GenericsResponse;
import com.ufi.euing.client.repositories.EventBillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;


@Component
public class EventBillServiceImpl extends AbstractFacade<EventsBill> implements EventBillService {

    final EventBillRepository repository;

    @Autowired
    private EntityManager em;

    public EventBillServiceImpl(EventBillRepository repository) {
        super(EventsBill.class);
        this.repository = repository;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public List<EventsBill> findEventsBillByTransaction(Long transId) {
        Query query = em.createNamedQuery("EventsBill.findByEveTransId");
        query.setParameter("id", transId);
        return query.getResultList();
    }

    @Override
    public GenericsResponse<EventsBill> createEventBill(EventsBill eve) {
        try {
            eve.setEveDate(new Date());
            this.create(eve);
            return new GenericsResponse<>(eve);
        } catch (Exception e) {
            e.printStackTrace();
            return new GenericsResponse<>(500, "An error occured. Details : " + e.getMessage(), null);
        }
    }
}
