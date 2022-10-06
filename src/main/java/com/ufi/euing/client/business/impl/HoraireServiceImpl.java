package com.ufi.euing.client.business.impl;

import com.ufi.euing.client.business.AbstractFacade;
import com.ufi.euing.client.business.HoraireService;
import com.ufi.euing.client.entity.GenericsResponse;
import com.ufi.euing.client.entity.Horaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Component
public class HoraireServiceImpl extends AbstractFacade<Horaire> implements HoraireService {

    public HoraireServiceImpl() {
        super(Horaire.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Autowired
    private EntityManager em;



    @Override
    public GenericsResponse<Horaire> createHoraire(Horaire horaire) {
        try {
            List<Horaire> list = new ArrayList<>();
            list = getEntityManager().createNamedQuery("Horaire.findByDayTypeUoUoId")
                    .setParameter("hrUoId", horaire.getHrUoId())
                    .setParameter("hrDay", horaire.getHrDay())
                    .setParameter("hrUoType", horaire.getHrUoType())
                    .getResultList();
            if (list.size() > 0) {
                return new GenericsResponse<>(405, "Horaire "+horaire.getHrUoType()+"-"+horaire.getHrUoId()+"-"+horaire.getHrDay()+" already exists!", list.get(0));
            }

            horaire.setDateCreate(new Date());
            horaire.setDateModif(new Date());
            this.create(horaire);
            return new GenericsResponse<>(horaire);
        } catch (Exception e) {
            return new GenericsResponse<>(500, "An error occured. Details : " + e.getMessage(), new Horaire());
        }
    }

    @Override
    public GenericsResponse<Horaire> updateHoraire(Horaire horaire) {
        try {
            horaire.setDateModif(new Date());
            this.edit(horaire);
            return new GenericsResponse<>(horaire);
        } catch (Exception e) {
            return new GenericsResponse<>(500, "An error occured. Details : " + e.getMessage(), new Horaire());
        }
    }

    @Override
    public List<Horaire> getHoraire() {
        List<Horaire> list;
        list = new ArrayList<>();
        list = this.findAll();
        return list;
    }

    @Override
    public GenericsResponse<Horaire> getHoraireById(BigDecimal id) {
        try {
            Horaire ser = this.find(id);
            if (ser != null) {
                return new GenericsResponse<>(ser);
            } else {
                return new GenericsResponse<>(404, "Data not exist with reference " + id, new Horaire());
            }
        } catch (Exception e) {
            return new GenericsResponse<>(500, "An error occured. Details : " + e.getMessage(), new Horaire());
        }
    }

    @Override
    public GenericsResponse<Horaire> activateHoraire(Horaire horaire) {
        try {
            horaire.setDateModif(new Date());
            horaire.setStatut(Short.parseShort("" + 1));
            this.edit(horaire);
            return new GenericsResponse<>(horaire);
        } catch (Exception e) {
            return new GenericsResponse<>(500, "An error occured. Details : " + e.getMessage(), new Horaire());
        }
    }

    @Override
    public GenericsResponse<Horaire> desactivateHoraire(Horaire horaire) {
        try {
            horaire.setDateModif(new Date());
            horaire.setStatut(Short.parseShort("" + 0));
            this.edit(horaire);
            return new GenericsResponse<>(horaire);
        } catch (Exception e) {
            return new GenericsResponse<>(500, "An error occured. Details : " + e.getMessage(), new Horaire());
        }
    }

    @Override
    public GenericsResponse<Horaire> findHoraireByDayTypeUoUoId(BigInteger day, String typeUo, BigInteger uoId) {
        try {
            List<Horaire> list = new ArrayList<>();
            list = getEntityManager().createNamedQuery("Horaire.findByDayTypeUoUoId")
                    .setParameter("hrUoId", uoId)
                    .setParameter("hrDay", day)
                    .setParameter("hrUoType", typeUo)
                    .getResultList();
            if (list.size() > 0) {
                return new GenericsResponse<>(list.get(0));
            } else {
                return new GenericsResponse<>(404, "Horaire not exists", new Horaire());
            }
        } catch (Exception e) {
            return new GenericsResponse<>(500, "An error occured. Details : " + e.getMessage(), new Horaire());
        }
    }

}
