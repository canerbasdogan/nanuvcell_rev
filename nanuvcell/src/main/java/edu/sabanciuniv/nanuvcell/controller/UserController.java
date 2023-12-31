package edu.sabanciuniv.nanuvcell.controller;

import edu.sabanciuniv.nanuvcell.dto.*;
import edu.sabanciuniv.nanuvcell.model.Invoice;
import edu.sabanciuniv.nanuvcell.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody CreateUserRequest request){
        service.createUser(request);
        return ResponseEntity.ok("User registration done successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id) {

        service.deleteUserById(id);
        return ResponseEntity.ok("User deleted successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findUserById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findUserById(id));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(service.login(request));
    }

    @PostMapping("/home-internet")
    public ResponseEntity<String> registerUserToTheHomeInternet(@RequestBody RegisterTariffRequest request) {
        service.registerUserToTheHomeInternet(request);
        return ResponseEntity.ok("User registered to Home Internet successfully");
    }

    @PostMapping("/mobile-tariff")
    public ResponseEntity<String> registerUserToTHeMobileTariff(@RequestBody RegisterTariffRequest request) {
        service.registerUserToTHeMobileTariff(request);
        return ResponseEntity.ok("User registered to Mobile Tariff successfully");
    }

    @PostMapping("/invoice-payment")
    public ResponseEntity<String> payAllInvoices(@RequestBody InvoicePaymentRequest request){
        service.payAllInvoices(request);
        return ResponseEntity.ok("Your invoices paid successfully");
    }

    @PostMapping("/invoice-payment/mobile-invoice")
    public ResponseEntity<String> payMobileInvoice(@RequestBody InvoicePaymentRequest request){
        service.payMobileInvoice(request);
        return ResponseEntity.ok("Your mobile invoice paid successfully");
    }

    @PostMapping("/invoice-payment/home-internet-invoice")
    public ResponseEntity<String> payHomeInternetInvoice(@RequestBody InvoicePaymentRequest request){
        service.payHomeInternetInvoice(request);
        return ResponseEntity.ok("Your home internet invoice paid successfully");
    }

    @GetMapping("/mobile-invoice/{id}")
    public InvoiceDto viewMobileInvoiceByUserId(@PathVariable Long id){

        return service.viewMobileInvoiceByUserId(id);
    }

    @GetMapping("/home-internet-invoice/{id}")
    public InvoiceDto viewHomeInternetInvoiceByUserId(@PathVariable Long id){

        return service.viewHomeInternetInvoiceByUserId(id);
    }

    /*@PostMapping("/home-internet/change-package")
    public void registerUserToAnotherHomeInternetPackage(@RequestBody RegisterTariffRequest request) {
        service.registerUserToAnotherHomeInternetPackage(request);
    }*/



}
