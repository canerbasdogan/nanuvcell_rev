package edu.sabanciuniv.nanuvcell.controller;

import edu.sabanciuniv.nanuvcell.dto.RemainingUseDto;
import edu.sabanciuniv.nanuvcell.dto.RemainingUseHomeInternetDto;
import edu.sabanciuniv.nanuvcell.service.RemainingUseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/remaining-uses")
public class RemainingUseController {

    private final RemainingUseService service;

    public RemainingUseController(RemainingUseService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<RemainingUseDto> findRemainingUseById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findRemainingUse(id));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<RemainingUseDto> findRemainingUseByUserId(@PathVariable Long id) {
        return ResponseEntity.ok(service.findRemainingUseById(id));
    }

    @GetMapping("/home-internet/user/{id}")
    public ResponseEntity<RemainingUseHomeInternetDto> findRemainingUseHomeInternetByUserId(@PathVariable Long id) {
        return ResponseEntity.ok(service.findRemainingUseHomeInternet(id));
    }

}
