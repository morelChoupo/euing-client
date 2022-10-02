/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufi.euing.client.integration.juba.model;

import com.ufi.euing.client.entity.Service;
import com.ufi.euing.client.entity.Utilisateur;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *
 * @author UFI
 */

@Data @NoArgsConstructor
public class JubaDoGetTransaction implements Serializable {

    String reference;
    Utilisateur usr;
    Service service;

    @Override
    public String toString() {
        return "DoGetTransaction{" + "reference=" + reference + ", usr=" + usr + ", service=" + service + '}';
    }
}
