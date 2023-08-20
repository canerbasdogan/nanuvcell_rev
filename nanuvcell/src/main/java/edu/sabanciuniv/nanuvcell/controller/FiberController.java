package edu.sabanciuniv.nanuvcell.controller;

import edu.sabanciuniv.nanuvcell.dto.CreateFiberRequest;
import edu.sabanciuniv.nanuvcell.dto.FiberDto;
import edu.sabanciuniv.nanuvcell.service.FiberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fibers")
public class FiberController {

    private final FiberService service;

    public FiberController(FiberService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<FiberDto>> findAllFiber() {
        return ResponseEntity.ok(service.findAllFiber());
    }

    @PostMapping
    public ResponseEntity<String> createFiber(@RequestBody CreateFiberRequest request) {
        service.createFiber(request);
        return ResponseEntity.ok("Fiber created");
    }

    @GetMapping("/{id}")
    public ResponseEntity<FiberDto> findFiberById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findFiberByGivenId(id));
    }
}
