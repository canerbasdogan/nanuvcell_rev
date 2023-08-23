package edu.sabanciuniv.nanuvcell.controller;

import edu.sabanciuniv.nanuvcell.dto.CreateInvoiceRequest;
import edu.sabanciuniv.nanuvcell.dto.InvoiceDto;
import edu.sabanciuniv.nanuvcell.service.InvoiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    private final InvoiceService service;

    public InvoiceController(InvoiceService service) {
        this.service = service;
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<InvoiceDto>> findInvoiceById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getInvoicesDtosByUserId(id));
    }

    //Bu metod bir işe yaramıyor?
    /*@PostMapping
    public ResponseEntity<String> createInvoice(@RequestBody CreateInvoiceRequest request) {
        service.createInvoice(request);
        return ResponseEntity.ok("Invoice created successfully!");
    }*/
}
