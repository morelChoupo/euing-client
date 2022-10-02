package com.ufi.euing.client.business;

import com.ufi.euing.client.entity.GenericsResponse;
import com.ufi.euing.client.entity.Parametrews;

import java.math.BigDecimal;
import java.util.List;

public interface ParametrewsService {

     List<Parametrews> getParametrews();

     List<Parametrews> getParametrewsByWsType(String wsType);

     GenericsResponse<Parametrews> createParametrews(Parametrews paramws);

     GenericsResponse<Parametrews> updateParametrews(Parametrews paramws);

     GenericsResponse<Parametrews> getParametrewsById(BigDecimal id);

     GenericsResponse<Parametrews> activateParametrews(Parametrews paramws);

     GenericsResponse<Parametrews> desactivateParametrews(Parametrews paramws);

     GenericsResponse<Parametrews> getParametrewsByCompagnie(BigDecimal compagnieId, String sens);

     GenericsResponse<Parametrews> getParametrewsByPartnerCode(String partnerCode);

     GenericsResponse<Parametrews> getParametrewsByPartnerCodeEUI(String partnerCode);

     GenericsResponse<Parametrews> getParametrewsByCode(String partnerCode);
}
