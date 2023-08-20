package edu.sabanciuniv.nanuvcell.dto;

import edu.sabanciuniv.nanuvcell.model.*;

public record UserDto(Long id,
                      String name,
                      String surName,
                      String gender,
                      String phoneNumber,
                      String email,
                      String address,
                      Roles role,
                      String mobileTariffName,
                      String homeInternetName) {

    public static UserDto convert(User from) {

        if(from.getHomeInternet()==null&&from.getMobileTariff()!=null){
            return new UserDto(
                    from.getId(),
                    from.getName(),
                    from.getSurName(),
                    from.getGender(),
                    from.getPhoneNumber(),
                    from.getEmail(),
                    from.getAddress(),
                    from.getRole(),
                    from.getMobileTariff().getTariffName(),
                    null);
        }

        if(from.getHomeInternet()!=null&&from.getMobileTariff()==null){
            return new UserDto(
                    from.getId(),
                    from.getName(),
                    from.getSurName(),
                    from.getGender(),
                    from.getPhoneNumber(),
                    from.getEmail(),
                    from.getAddress(),
                    from.getRole(),
                    null,
                    from.getHomeInternet().getTariffName());
        }

        if(from.getHomeInternet()==null&&from.getMobileTariff()==null){
            return new UserDto(
                    from.getId(),
                    from.getName(),
                    from.getSurName(),
                    from.getGender(),
                    from.getPhoneNumber(),
                    from.getEmail(),
                    from.getAddress(),
                    from.getRole(),
                    null,
                    null);
        }

        return new UserDto(
                from.getId(),
                from.getName(),
                from.getSurName(),
                from.getGender(),
                from.getPhoneNumber(),
                from.getEmail(),
                from.getAddress(),
                from.getRole(),
                from.getMobileTariff().getTariffName(),
                from.getHomeInternet().getTariffName());
    }
}
