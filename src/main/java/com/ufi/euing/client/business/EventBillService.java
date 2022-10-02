package com.ufi.euing.client.business;

import com.ufi.euing.client.entity.EventsBill;
import com.ufi.euing.client.entity.GenericsResponse;

import java.util.List;

public interface EventBillService {

    void create(EventsBill eventsBill);

    void edit(EventsBill eventsBill);

    void remove(EventsBill eventsBill);

    EventsBill find(Object id);

    List<EventsBill> findAll();

    List<EventsBill> findRange(int[] range);

    int count();

    List<EventsBill> findEventsBillByTransaction(Long transId);

    GenericsResponse<EventsBill> createEventBill(EventsBill eve);
}
