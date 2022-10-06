package com.ufi.euing.client.business;

import com.ufi.euing.client.entity.CompagnieTpi;
import com.ufi.euing.client.entity.GenericsResponse;

import java.util.List;

public interface CompagnieTPIService {

    void create(CompagnieTpi compagnieTpi);

    void edit(CompagnieTpi compagnieTpi);

    void remove(CompagnieTpi compagnieTpi);

    CompagnieTpi find(Object id);

    List<CompagnieTpi> findAll();

    List<CompagnieTpi> findRange(int[] range);

    int count();
    //    List<CompagnieTpi> getCompagnieTpiByStatut(short status) ;
    List<CompagnieTpi> getCompagnieTpiByCompagnie(Long compagnie) ;
    public GenericsResponse<CompagnieTpi> createCompagnieTpi(CompagnieTpi comtpi);
    public GenericsResponse<CompagnieTpi> updateCompagnieTpi(CompagnieTpi comtpi);
    //    public List<CompagnieTpi> getCompagnieTpi();
    public GenericsResponse<CompagnieTpi> getCompagnieTpiById(Long id);
    //public GenericsResponse<String> removeCompagnieTpi(CompagnieTpi comtpi);
    public GenericsResponse<CompagnieTpi> findCompagnieTpiByTpiComagnie(Long compagnieId, String tpiCode);
}
