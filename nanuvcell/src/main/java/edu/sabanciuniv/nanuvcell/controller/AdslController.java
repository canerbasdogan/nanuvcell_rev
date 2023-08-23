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
    public ResponseEntity<String> createAdsl(@RequestBody CreateAdslRequest request) {
        service.createAdsl(request);
        return ResponseEntity.ok("ADSL created");
    }

    @PostMapping("/adsl-list")
    public ResponseEntity<String> createAdslList(@RequestBody List<CreateAdslRequest> requestList) {
        service.createAdslList(requestList);
        return ResponseEntity.ok("ADSL List created");
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdslDto> findAdslById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findAdslByGivenId(id));
    }

}
