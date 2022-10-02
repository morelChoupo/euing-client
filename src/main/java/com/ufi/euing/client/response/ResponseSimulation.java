package com.ufi.euing.client.response;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ResponseSimulation {
    private int pRes;
    private String pres_message;
    private double pamount_to_paid ;
    private double pfees;
    private double pglobal_tva;
    private double pglobal_other_taxe;
    private double page_commission;
    private double pcom_commission;
    private double pgrp_commission;
    private double psys_commission;
    private double pgui_commission;
    private double pexchange_rate;

    public ResponseSimulation(int pRes, String pres_message, double pamount_to_paid, double pfees, double pglobal_tva, double pglobal_other_taxe, double page_commission, double pcom_commission, double pgrp_commission, double psys_commission, double pgui_commission, double pexchange_rate) {
        this.pRes = pRes;
        this.pres_message = pres_message;
        this.pamount_to_paid = pamount_to_paid;
        this.pfees = pfees;
        this.pglobal_tva = pglobal_tva;
        this.pglobal_other_taxe = pglobal_other_taxe;
        this.page_commission = page_commission;
        this.pcom_commission = pcom_commission;
        this.pgrp_commission = pgrp_commission;
        this.psys_commission = psys_commission;
        this.pgui_commission = pgui_commission;
        this.pexchange_rate = pexchange_rate;
    }

    public ResponseSimulation() {
    }


}
