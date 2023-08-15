package edu.sabanciuniv.nanuvcell.controller;

import edu.sabanciuniv.nanuvcell.dto.AdslDto;
import edu.sabanciuniv.nanuvcell.dto.CreateAdslRequest;
import edu.sabanciuniv.nanuvcell.service.AdslService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/adsl")
public class AdslController {

    private final AdslService service;

    public AdslController(AdslService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<AdslDto>> findAllAdsl() {
        return ResponseEntity.ok(service.findAllAdsl());
    }

    @PostMapping
    public void createAdsl(@RequestBody CreateAdslRequest request) {
        service.createAdsl(request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdslDto> findAdslById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findAdslByGivenId(id));
    }

}
