/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufi.euing.client.integration.gimac.model;

/**
 *
 * @author User
 */
public class WalletIncomingRemittanceRequest extends IncomingRemittanceRequest {

    private String walletdestination;

    public String getWalletdestination() {
        return walletdestination;
    }

    public void setWalletdestination(String walletdestination) {
        this.walletdestination = walletdestination;
    }

    @Override
    public String toString() {
        return "WalletIncomingRemittanceRequest{" + "walletdestination=" + walletdestination + '}';
    }

}
