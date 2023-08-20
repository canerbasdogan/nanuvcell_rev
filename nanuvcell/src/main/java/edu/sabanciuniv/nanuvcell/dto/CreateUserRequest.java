package edu.sabanciuniv.nanuvcell.dto;

import edu.sabanciuniv.nanuvcell.model.Roles;

public record CreateUserRequest(String name,
                                String surName,
                                String gender,
                                String phoneNumber,
                                String email,
                                String address,
                                String password,
                                Roles role,
                                Long creditCardNumber,
                                Long creditCardCVV,
                                String creditCardPassword/*,
                                Double creditCardLimit*/) {
}
