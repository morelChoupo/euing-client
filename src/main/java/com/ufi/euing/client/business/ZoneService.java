package com.ufi.euing.client.business;

import com.ufi.euing.client.entity.Zone;

import java.util.List;

public interface ZoneService {

    void create(Zone zone);

    void edit(Zone zone);

    void remove(Zone zone);

    Zone find(Object id);

    List<Zone> findAll();

    List<Zone> findRange(int[] range);

    int count();

    public List<Zone> findByPays(String code);

    List<Zone> getZoneByStatut(short status);
}
