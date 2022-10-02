package com.ufi.euing.client.business.impl;

import com.ufi.euing.client.api.GimacApi;
import com.ufi.euing.client.business.AbstractFacade;
import com.ufi.euing.client.business.PaysService;
import com.ufi.euing.client.entity.GenericsResponse;
import com.ufi.euing.client.entity.ParametreBase;
import com.ufi.euing.client.entity.Pays;
import com.ufi.euing.client.repositories.PaysRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;


@Component
public class PaysServiceImpl extends AbstractFacade<Pays> implements PaysService {

    private static final Logger log = LoggerFactory.getLogger(PaysServiceImpl.class);

    final PaysRepository repository;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Autowired
    private EntityManager em;

    public PaysServiceImpl(PaysRepository repository) {
        super(Pays.class);
        this.repository = repository;
    }

    @Override
    public List<Pays> getAllCountries() {
        return repository.findAll();
    }

    @Override
    public Pays getCountryByCode(String code) {
        return repository.findPaysByPsCode(code);
    }

    @Override
    public GenericsResponse<Pays> findPaysByCodeIso(String codeIso) {
        try {
            if(codeIso.length() == 3){
                Query query = em.createNamedQuery("Pays.findByPsCode");
                query.setParameter("psCode", codeIso);
                List<Pays> list = new ArrayList<>();
                list = query.getResultList();
                if(!list.isEmpty())
                    return new GenericsResponse<>(list.get(0));
                else
                    return new GenericsResponse<>(404, "Country with code " +codeIso+" not exist", null);
            } else {
                log.info("Country iso {}", codeIso);
                Query query = em.createNamedQuery("Pays.findByPsCode2");
                query.setParameter("psCode2", codeIso);
                List<Pays> list = new ArrayList<>();
                list = query.getResultList();
                log.info("list {}", list);
                if(!list.isEmpty())
                    return new GenericsResponse<>(list.get(0));
                else
                    return new GenericsResponse<>(404, "Country with code " +codeIso+" not exist", null);
            }

        } catch (Exception e) {
            return new GenericsResponse<>(500, "An error occured. Details : " + e.getMessage(), new Pays());
        }
    }


}
