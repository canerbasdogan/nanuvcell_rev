package edu.sabanciuniv.nanuvcell.dto;

import edu.sabanciuniv.nanuvcell.model.*;

import java.util.List;

public record UserDto(Long id,
                      String name,
                      String surName,
                      String gender,
                      String phoneNumber,
                      String email,
                      String address,
                      Roles role,
                      Long mobileTariffId,
                      Long homeInternetId) {

    public static UserDto convert(User from) {
        return new UserDto(
                from.getId(),
                from.getName(),
                from.getSurName(),
                from.getGender(),
                from.getPhoneNumber(),
                from.getEmail(),
                from.getAddress(),
                from.getRole(),
                from.getMobileTariff().getId(),
                from.getHomeInternet().getId());
    }
}
