package edu.sabanciuniv.nanuvcell.controller;

import edu.sabanciuniv.nanuvcell.dto.*;
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

    @PostMapping
    public void createUser(@RequestBody CreateUserRequest request) {
        service.createUser(request);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable Long id) {
        service.deleteUserById(id);
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
    public void registerUserToTheHomeInternet(@RequestBody RegisterTariffRequest request) {
        service.registerUserToTheHomeInternet(request);
    }

    @PostMapping("/mobile-tariff")
    public void registerUserToTHeMobileTariff(@RequestBody RegisterTariffRequest request) {
        service.registerUserToTHeMobileTariff(request);
    }
}
