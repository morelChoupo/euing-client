package com.ufi.euing.client.business;

import com.ufi.euing.client.entity.GenericsResponse;
import com.ufi.euing.client.entity.Service;

import java.math.BigDecimal;
import java.util.List;

public interface ServiceService {

     GenericsResponse<Service> createService(Service ser);
     GenericsResponse<Service> updateService(Service ser);
     List<Service> getService();
     GenericsResponse<Service> getServiceById(Long id);
     GenericsResponse<Service> getServiceByCode(String code);
     GenericsResponse<Service> activateService(Service ser);
     GenericsResponse<Service> desactivateService(Service ser);
     List<Service> findServiceByStatut(Short sh);
}
