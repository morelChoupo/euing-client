package com.ufi.euing.client.business.impl;

import com.ufi.euing.client.business.ServiceService;
import com.ufi.euing.client.entity.GenericsResponse;
import com.ufi.euing.client.entity.Service;
import com.ufi.euing.client.exceptions.domain.EmailNotFoundException;
import com.ufi.euing.client.repositories.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Component
public class ServiceServiceImpl implements ServiceService {

    @Autowired
    private EntityManager em;

    final ServiceRepository repository;

    public ServiceServiceImpl(ServiceRepository repository) {
        this.repository = repository;
    }


    @Override
    public GenericsResponse<Service> createService(Service ser) {
        try {
            List<Service> list = new ArrayList<>();
            list = em.createNamedQuery("Service.findByCode").setParameter("code", ser.getCode()).getResultList();
            if (list.size() > 0) {
                return new GenericsResponse<>(405, "Service already exists!", list.get(0));
            }

            ser.setDateCreate(new Date());
            ser.setDateModif(new Date());
            repository.save(ser);
            return new GenericsResponse<>(ser);
        } catch (Exception e) {
            return new GenericsResponse<>(500, "An error occured. Details : " + e.getMessage(), new Service());
        }
    }

    @Override
    public GenericsResponse<Service> updateService(Service ser) {
        try {
            ser.setDateModif(new Date());
            repository.save(ser);
            return new GenericsResponse<>(ser);
        } catch (Exception e) {
            return new GenericsResponse<>(500, "An error occured. Details : " + e.getMessage(), new Service());
        }
    }

    @Override
    public List<Service> getService() {
        return repository.findAll();
    }

    @Override
    public GenericsResponse<Service> getServiceById(Long id) {
        try {
            Service ser = repository.findById(id).orElseThrow(()-> new EmailNotFoundException("Service  not found"));
            if (ser != null) {
                return new GenericsResponse<>(ser);
            } else {
                return new GenericsResponse<>(404, "Data not exist with reference " + id, new Service());
            }
        } catch (Exception e) {
            return new GenericsResponse<>(500, "An error occured. Details : " + e.getMessage(), new Service());
        }
    }

    @Override
    public GenericsResponse<Service> getServiceByCode(String code) {
        try {
            Query query = em.createNamedQuery("Service.findByCode")
                    .setParameter("code",code);
            Service ser = (Service)query.getSingleResult();
            if (ser != null) {
                return new GenericsResponse<>(ser);
            } else {
                return new GenericsResponse<>(404, "Code not exist with code " + code, new Service());
            }
        } catch (Exception e) {
            return new GenericsResponse<>(500, "An error occured. Details : " + e.getMessage(), new Service());
        }
    }

    @Override
    public GenericsResponse<Service> activateService(Service ser) {
        try {
            ser.setDateModif(new Date());
            ser.setStatut(Short.parseShort("" + 1));
            repository.save(ser);
            return new GenericsResponse<>(ser);
        } catch (Exception e) {
            return new GenericsResponse<>(500, "An error occured. Details : " + e.getMessage(), new Service());
        }
    }

    @Override
    public GenericsResponse<Service> desactivateService(Service ser) {
        try {
            ser.setDateModif(new Date());
            ser.setStatut(Short.parseShort("" + 0));
            repository.save(ser);
            return new GenericsResponse<>(ser);
        } catch (Exception e) {
            return new GenericsResponse<>(500, "An error occured. Details : " + e.getMessage(), new Service());
        }
    }

    @Override
    public List<Service> findServiceByStatut(Short sh) {
        Query query = em.createNamedQuery("Service.findByStatut");
        query.setParameter("statut", sh);
        return query.getResultList();
    }
}
