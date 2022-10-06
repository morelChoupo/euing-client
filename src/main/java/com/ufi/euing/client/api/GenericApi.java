package com.ufi.euing.client.api;


import com.ufi.euing.client.business.*;
import com.ufi.euing.client.entity.*;
import com.ufi.euing.client.exceptions.EuingServiceException;
import com.ufi.euing.client.exceptions.domain.EmailNotFoundException;
import com.ufi.euing.client.props.EuingProperties;
import com.ufi.euing.client.response.EuingApiResponse;
import com.ufi.euing.client.utils.others.Tools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/generic")
public class GenericApi {

    private static final Logger log = LoggerFactory.getLogger(GenericApi.class);

    final ListToUseService listToUseService;

    final PaysService paysService;

    final TypePieceIdentiteService typePieceIdentiteService;

    final GuichetService guichetService;

    final EuingProperties euingProperties;

    final Tools tools;

    final ZoneService zoneService;

    public GenericApi(ListToUseService listToUseService,
                      PaysService paysService,
                      TypePieceIdentiteService typePieceIdentiteService,
                      GuichetService guichetService, EuingProperties euingProperties, Tools tools, ZoneService zoneService) {
        this.listToUseService = listToUseService;
        this.paysService = paysService;
        this.typePieceIdentiteService = typePieceIdentiteService;
        this.guichetService = guichetService;
        this.euingProperties = euingProperties;
        this.tools = tools;
        this.zoneService = zoneService;
    }


    @PostMapping("/partner-choice")
    public ResponseEntity<EuingApiResponse<List<ListeToUse>>> choicePartner(@RequestParam String country, @RequestParam String serviceCode) {
        EuingApiResponse<List<ListeToUse>> payload = new EuingApiResponse<>();

        String usageFinal = "PARTNER_CHOICE_".concat(country);
        try {
            return ResponseEntity.ok(payload.success(listToUseService.getListeToUseByUsageAndLibelle(usageFinal, serviceCode)));
        } catch (EuingServiceException e) {
            log.error(e.getMessage());
            return new ResponseEntity(e.getMessage(), HttpStatus.valueOf(e.getStatus()));
        }
    }


    @GetMapping("/country/all")
    public ResponseEntity<EuingApiResponse<List<Pays>>> getCountry() {
        EuingApiResponse<List<Pays>> payload = new EuingApiResponse<>();
        try {
            return ResponseEntity.ok(payload.success(paysService.getAllCountries()));
        } catch (EuingServiceException e) {
            log.error(e.getMessage());
            return new ResponseEntity(e.getMessage(), HttpStatus.valueOf(e.getStatus()));
        }

    }


    @GetMapping("/typePiece")
    public List<DoubleValue> getAllTypePiece() {

        List<TypePieceIdentite> _pieces = typePieceIdentiteService.findAll();
        List<DoubleValue> dv = new ArrayList<>();
        for (int i = 0; i < _pieces.size(); i++) {
            dv.add(new DoubleValue(_pieces.get(i).getTpiCode(), _pieces.get(i).getTpiLibelle()));
        }
        return dv;
    }

    @PostMapping("/chargeAndTaxTown")
    public ResponseEntity<EuingApiResponse<GetTransfinQuote>> getChargesAndTaxesByTown(GetTransfinQuoteRequest quote) throws EmailNotFoundException {
        log.info("chargeAndTaxTown =====>  {}" , quote.toString());
        Guichet gui = guichetService.getById(euingProperties.getGuichetCode());
        String po = guichetService.find(gui.getId()).getAgence().getVille().getViZnId().getZnPsCode().getPsCode();
        String pd = zoneService.find(BigDecimal.valueOf(quote.getDestinationTown())).getZnPsCode().getPsCode();
        EuingApiResponse<GetTransfinQuote> payload = new EuingApiResponse<>();
        try {
            quote.setDestinationCountry(pd);
            quote.setOriginatingCountry(po);
            return ResponseEntity.ok(payload.success(tools.onPaysChanged(quote)));
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity(e.getMessage(), HttpStatus.valueOf(500));
        }
    }


    @PostMapping("/chargeAndTaxCountry")
    public ResponseEntity<EuingApiResponse<GetTransfinQuote>> getChargesAndTaxesByCountry(GetTransfinQuoteRequest quote) {
        log.info("chargeAndTaxCountry =====>  {}", quote.toString());
        EuingApiResponse<GetTransfinQuote> payload = new EuingApiResponse<>();
        try {
            return ResponseEntity.ok(payload.success(tools.onPaysChanged(quote)));
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity(e.getMessage(), HttpStatus.valueOf(500));
        }
    }
}
