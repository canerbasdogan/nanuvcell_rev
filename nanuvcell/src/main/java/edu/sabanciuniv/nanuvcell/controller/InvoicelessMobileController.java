package edu.sabanciuniv.nanuvcell.controller;

import edu.sabanciuniv.nanuvcell.dto.CreateInvoicelessMobileRequest;
import edu.sabanciuniv.nanuvcell.dto.InvoicelessMobileDto;
import edu.sabanciuniv.nanuvcell.service.InvoicelesssMobileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoiceless-mobiles")
public class InvoicelessMobileController {

    private final InvoicelesssMobileService service;

    public InvoicelessMobileController(InvoicelesssMobileService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<InvoicelessMobileDto>> findAllInvoicelessMobile() {
        return ResponseEntity.ok(service.findAllInvoicelessMobile());
    }

    @PostMapping
    public void createInvoicelessMobile(@RequestBody CreateInvoicelessMobileRequest request) {
        service.createInvoicelessMobile(request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoicelessMobileDto> findInvoicelessMobileById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findInvoicelessMobileByGivenId(id));
    }
}
