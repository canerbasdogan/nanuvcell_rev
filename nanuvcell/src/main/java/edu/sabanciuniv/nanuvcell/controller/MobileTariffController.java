package edu.sabanciuniv.nanuvcell.controller;


import edu.sabanciuniv.nanuvcell.dto.HomeInternetDto;
import edu.sabanciuniv.nanuvcell.dto.MobileTariffDto;
import edu.sabanciuniv.nanuvcell.service.MobileTariffService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/mobile-tariff")
public class MobileTariffController {

    private final MobileTariffService service;

    public MobileTariffController(MobileTariffService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<MobileTariffDto>> findAllMobileTariff() {
        return ResponseEntity.ok(service.findAllMobileTariff());
    }
}
