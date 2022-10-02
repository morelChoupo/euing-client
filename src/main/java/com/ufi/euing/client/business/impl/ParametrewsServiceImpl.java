package com.ufi.euing.client.business.impl;

import com.ufi.euing.client.business.ParametrewsService;
import com.ufi.euing.client.entity.GenericsResponse;
import com.ufi.euing.client.entity.Parametrews;
import com.ufi.euing.client.exceptions.domain.EmailNotFoundException;
import com.ufi.euing.client.repositories.ParametrewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Component
public class ParametrewsServiceImpl implements ParametrewsService {


    @Autowired
    private EntityManager em;

    @Autowired
    ParametrewsRepository parametrewsRepository;


    @Override
    public List<Parametrews> getParametrews() {
        return parametrewsRepository.findAll();
    }

    @Override
    public List<Parametrews> getParametrewsByWsType(String wsType) {
        Short sh = 1;
        Query query = em.createNamedQuery("Parametrews.findByWstype")
                .setParameter("wstype", wsType)
                .setParameter("statut", sh);
        List<Parametrews> list = query.getResultList();
        return list;
    }

    @Override
    public GenericsResponse<Parametrews> createParametrews(Parametrews paramws) {
        try {
            List<Parametrews> list = em.createNamedQuery("Parametrews.findByPwsPartnerCode").setParameter("pwsPartnerCode", paramws.getPwsPartnerCode()).getResultList();
            if (!list.isEmpty()) {
                return new GenericsResponse<>(200, "Parameter ws already exists!", list.get(0));
            }

            paramws.setDateModif(new Date());
            paramws.setDateCreate(new Date());
            parametrewsRepository.save(paramws);

            return new GenericsResponse<>(paramws);
        } catch (Exception e) {
            return new GenericsResponse<>(500, "An error occured. Details : " + e.getMessage(), new Parametrews());
        }
    }

    @Override
    public GenericsResponse<Parametrews> updateParametrews(Parametrews paramws) {
        try {
            paramws.setDateModif(new Date());
            parametrewsRepository.save(paramws);

            return new GenericsResponse<>(paramws);
        } catch (Exception e) {
            return new GenericsResponse<>(500, "An error occured. Details : " + e.getMessage(), new Parametrews());
        }
    }

    @Override
    public GenericsResponse<Parametrews> getParametrewsById(BigDecimal id) {
        try {
            Parametrews paramws = parametrewsRepository.findById(id).orElseThrow(()-> new EmailNotFoundException("param  not found"));
            if (paramws != null) {
                return new GenericsResponse<>(paramws);
            } else {
                return new GenericsResponse<>(404, "Data not exist with reference " + id, new Parametrews());
            }
        } catch (Exception e) {
            return new GenericsResponse<>(500, "An error occured. Details : " + e.getMessage(), new Parametrews());
        }
    }

    @Override
    public GenericsResponse<Parametrews> activateParametrews(Parametrews paramws) {
        try {
            paramws.setDateModif(new Date());
            paramws.setStatut(Short.parseShort("1"));
            parametrewsRepository.save(paramws);

            return new GenericsResponse<>(paramws);
        } catch (Exception e) {
            return new GenericsResponse<>(500, "An error occured. Details : " + e.getMessage(), new Parametrews());
        }
    }

    @Override
    public GenericsResponse<Parametrews> desactivateParametrews(Parametrews paramws) {
        try {
            paramws.setDateModif(new Date());
            paramws.setStatut(Short.parseShort("0"));
            parametrewsRepository.save(paramws);

            return new GenericsResponse<>(paramws);
        } catch (Exception e) {
            return new GenericsResponse<>(500, "An error occured. Details : " + e.getMessage(), new Parametrews());
        }
    }

    @Override
    public GenericsResponse<Parametrews> getParametrewsByCompagnie(BigDecimal compagnieId, String sens) {
        try {
            Query query = em.createNamedQuery("Parametrews.findByCompagnie")
                    .setParameter("compagnieId", compagnieId)
                    .setParameter("wstype", sens);
            Parametrews ser = (Parametrews) query.getSingleResult();
            if (ser != null) {
                return new GenericsResponse<>(ser);
            } else {
                return new GenericsResponse<>(404, "Parameter WS not exist for compagnie " + compagnieId, new Parametrews());
            }
        } catch (Exception e) {
            return new GenericsResponse<>(500, "An error occured. Details : " + e.getMessage(), new Parametrews());
        }
    }

    @Override
    public GenericsResponse<Parametrews> getParametrewsByPartnerCode(String partnerCode) {
        try {
            Query query = em.createNamedQuery("Parametrews.findByPwsPartnerCode")
                    .setParameter("pwsPartnerCode", partnerCode);
            List<Parametrews> list = new ArrayList<>();
            list = query.getResultList();
            if (list.size() == 1) {
                return new GenericsResponse<>(list.get(0));
            } else if (list.size() == 0) {
                return new GenericsResponse<>(404, "Parameter WS not exist with partner code " + partnerCode, new Parametrews());
            } else {
                return new GenericsResponse<>(500, "Parameter WS not exist with partner code " + partnerCode, new Parametrews());
            }
        } catch (Exception e) {
            return new GenericsResponse<>(500, "An error occured. Details : " + e.getMessage(), new Parametrews());
        }
    }

    @Override
    public GenericsResponse<Parametrews> getParametrewsByPartnerCodeEUI(String partnerCode) {
        try {
            Query query = em.createNamedQuery("Parametrews.findByPwsMystatutIdPartner")
                    .setParameter("pwsMystatutIdPartner", partnerCode);
            List<Parametrews> list = new ArrayList<>();
            list = query.getResultList();

            if (list.size() >= 1) {
                return new GenericsResponse<>(list.get(0));
            } else if (list.size() == 0) {
                return new GenericsResponse<>(404, "Parameter WS not exist with partner code " + partnerCode, new Parametrews());
            } else {
                return new GenericsResponse<>(500, "Parameter WS not exist with partner code " + partnerCode, new Parametrews());
            }
        } catch (Exception e) {
            return new GenericsResponse<>(500, "An error occured. Details : " + e.getMessage(), new Parametrews());
        }
    }

    @Override
    public GenericsResponse<Parametrews> getParametrewsByCode(String partnerCode) {
        try {
            Query query = em.createNamedQuery("Parametrews.findByPwsCode")
                    .setParameter("pwsPartnerCode", partnerCode);
            List<Parametrews> list = query.getResultList();
            if (!list.isEmpty()) {
                return new GenericsResponse<>(list.get(0));
            } else {
                return new GenericsResponse<>(404, "Parameter WS not exist with partner code " + partnerCode, new Parametrews());
            }
        } catch (Exception e) {
            return new GenericsResponse<>(500, "An error occured. Details : " + e.getMessage(), new Parametrews());
        }
    }
}
