package com.ufi.euing.client.business.impl;

import com.ufi.euing.client.business.AbstractFacade;
import com.ufi.euing.client.business.CompagnieService;
import com.ufi.euing.client.business.CompagnieTPIService;
import com.ufi.euing.client.entity.Compagnie;
import com.ufi.euing.client.entity.CompagnieTpi;
import com.ufi.euing.client.entity.GenericsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CompagnieTPIServiceImpl extends AbstractFacade<CompagnieTpi> implements CompagnieTPIService {

    public CompagnieTPIServiceImpl() {
        super(CompagnieTpi.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Autowired
    private EntityManager em;


    @Override
    public List<CompagnieTpi> getCompagnieTpiByCompagnie(Long compagnie) {
        List<CompagnieTpi> list;
        list = new ArrayList<>();
        Query query = em.createNamedQuery("CompagnieTpi.findByCompagnie").setParameter("compagnie", compagnie);
        list = query.getResultList();
        return list;
    }


    @Override
    public GenericsResponse<CompagnieTpi> findCompagnieTpiByTpiComagnie(Long compagnieId, String tpiCode) {
        try {
            CompagnieTpi ctpi = (CompagnieTpi)getEntityManager().createNamedQuery("CompagnieTpi.checkDoublon").setParameter("compagnie", compagnieId).setParameter("tpiCode", tpiCode).getSingleResult();
            return new GenericsResponse<>(ctpi);
        } catch (Exception e) {
            return new GenericsResponse<>(500, "An error occured. Details : " + e.getMessage(), new CompagnieTpi());
        }
    }

    @Override
    public GenericsResponse<CompagnieTpi> createCompagnieTpi(CompagnieTpi comtpi) {
        try {
            List<CompagnieTpi> list = new ArrayList<>();
            list = getEntityManager().createNamedQuery("CompagnieTpi.checkDoublon").setParameter("compagnie", comtpi.getCompagnie().getId()).setParameter("tpiCode", comtpi.getTpiCode().getTpiCode()).getResultList();
            if (list.size() > 0) {
                return new GenericsResponse<>(201, "Data already exists!", list.get(0));
            }
            comtpi.setDateModif(new Date());
            comtpi.setDateCreate(new Date());
//            System.out.println("id service = "+comtau.getService().getId());
            this.create(comtpi);

            return new GenericsResponse<>(comtpi);
        } catch (Exception e) {
            return new GenericsResponse<>(500, "An error occured. Details : " + e.getMessage(), new CompagnieTpi());
        }
    }

    @Override
    public GenericsResponse<CompagnieTpi> updateCompagnieTpi(CompagnieTpi comtpi) {
        try {
            comtpi.setDateModif(new Date());
            this.edit(comtpi);

            return new GenericsResponse<>(comtpi);
        } catch (Exception e) {
            return new GenericsResponse<>(500, "An error occured. Details : " + e.getMessage(), new CompagnieTpi());
        }
    }

    @Override
    public GenericsResponse<CompagnieTpi> getCompagnieTpiById(Long id) {
        try {

            CompagnieTpi comtpi = this.find(id);
            if(comtpi != null){
                return new GenericsResponse<>(comtpi);
            }
            else{
                return new GenericsResponse<>(404, "Data not exist with reference " + id, new CompagnieTpi());
            }
        } catch (Exception e) {
            return new GenericsResponse<>(500, "An error occured. Details : " + e.getMessage(), new CompagnieTpi());
        }
    }
}
