package edu.sabanciuniv.nanuvcell.dto;

import edu.sabanciuniv.nanuvcell.model.Adsl;
import edu.sabanciuniv.nanuvcell.model.HomeInternet;
import edu.sabanciuniv.nanuvcell.model.Roles;

public record HomeInternetDto(Long id,
                              String tariffName,
                              double internetQuota,
                              double tariffPrice,
                              Roles role,
                              int internetSpeed) {

    public static HomeInternetDto convert(HomeInternet from) {
        return new HomeInternetDto(
                from.getId(),
                from.getTariffName(),
                from.getInternetQuota(),
                from.getTariffPrice(),
                from.getRole(),
                from.getInternetSpeed());
    }
}
