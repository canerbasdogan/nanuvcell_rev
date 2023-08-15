package edu.sabanciuniv.nanuvcell.controller;

import edu.sabanciuniv.nanuvcell.dto.StoreDto;
import edu.sabanciuniv.nanuvcell.service.StoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/stores")
public class StoreController {

    private final StoreService service;

    public StoreController(StoreService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<StoreDto>> findAllStores() {
        return ResponseEntity.ok(service.findAllStore());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StoreDto> findStoreById(@PathVariable int id) {
        return ResponseEntity.ok(service.findStoreById(id));
    }
}
