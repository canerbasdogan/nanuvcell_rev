package edu.sabanciuniv.nanuvcell.controller;

import edu.sabanciuniv.nanuvcell.dto.AdslDto;
import edu.sabanciuniv.nanuvcell.dto.HomeInternetDto;
import edu.sabanciuniv.nanuvcell.service.HomeInternetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/home-internet")
public class HomeInternetController {

    private final HomeInternetService service;

    public HomeInternetController(HomeInternetService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<HomeInternetDto>> findAllHomeInternet() {
        return ResponseEntity.ok(service.findAllHomeInternet());
    }

}
