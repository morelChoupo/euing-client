package com.ufi.euing.client.business;

import com.ufi.euing.client.entity.GenericsResponse;
import com.ufi.euing.client.entity.Utilisateur;

import java.util.List;

public interface UtilisateurService {

    void create(Utilisateur utilisateur);

    void edit(Utilisateur utilisateur);

    void remove(Utilisateur utilisateur);

    Utilisateur find(Object id);

    List<Utilisateur> findAll();

    List<Utilisateur> findRange(int[] range);

    int count();

     GenericsResponse<Utilisateur> updateUtilisateur(Utilisateur user);
     List<Utilisateur> getUtilisateur();
     GenericsResponse<Utilisateur> getUtilisateurById(Long id);
     GenericsResponse<Utilisateur> getUtilisateurByLogin(String login);
     List<Utilisateur> searchUtilisateurs(String login, String nom, String prenom, String fctCode, int status);
     GenericsResponse<Utilisateur> activateUtilisateur(Utilisateur user);
     GenericsResponse<Utilisateur> desactivateUtilisateur(Utilisateur user);
     List<Utilisateur> searchUsersOnline(String login, String nom, String prenom, String fctCode, int status);
     List<Utilisateur> searchUsersDesactivated(String login, String nom, String prenom, String fctCode);
     GenericsResponse<Utilisateur> resetPassword(Utilisateur user);
     GenericsResponse<Utilisateur> setUnitOrgUser(Long idUser, String typeUo, int uoId);
     GenericsResponse<Boolean> checkSessionUser(Long idUser);
     GenericsResponse<Utilisateur> findByUoAndTypeUo(int uoId, String typeUo);


    GenericsResponse<Utilisateur> login(String email, String password);
    GenericsResponse<Utilisateur> createUtilisateur(Utilisateur user);
}
