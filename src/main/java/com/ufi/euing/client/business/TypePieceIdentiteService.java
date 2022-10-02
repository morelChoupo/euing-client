package com.ufi.euing.client.business;

import com.ufi.euing.client.entity.GenericsResponse;
import com.ufi.euing.client.entity.TypePieceIdentite;

import java.util.List;

public interface TypePieceIdentiteService {

     void create(TypePieceIdentite typePieceIdentite);

     void edit(TypePieceIdentite typePieceIdentite);

     void remove(TypePieceIdentite typePieceIdentite);

     TypePieceIdentite find(Object id);

     List<TypePieceIdentite> findAll();

     List<TypePieceIdentite> findRange(int[] range);

     int count();

     GenericsResponse<TypePieceIdentite> getTypePieceIdentiteByCode(String code);
}
