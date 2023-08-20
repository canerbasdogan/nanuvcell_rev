package edu.sabanciuniv.nanuvcell.controller;

import edu.sabanciuniv.nanuvcell.dto.CreateInvoiceMobileRequest;
import edu.sabanciuniv.nanuvcell.dto.InvoiceMobileDto;
import edu.sabanciuniv.nanuvcell.service.InvoiceMobileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoice-mobiles")
public class InvoiceMobileController {

    private final InvoiceMobileService service;

    public InvoiceMobileController(InvoiceMobileService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<InvoiceMobileDto>> findAllInvoiceMobile() {
        return ResponseEntity.ok(service.findAllInvoiceMobile());
    }

    @PostMapping
    public ResponseEntity<String> createInvoiceMobile(@RequestBody CreateInvoiceMobileRequest request) {
        service.createInvoiceMobile(request);
        return ResponseEntity.ok("Invoice Mobile created");
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceMobileDto> findInvoiceMobileById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findInvoiceMobileByGivenId(id));
    }
}
