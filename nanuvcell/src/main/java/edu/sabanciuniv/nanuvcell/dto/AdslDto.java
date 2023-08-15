package edu.sabanciuniv.nanuvcell.dto;

import edu.sabanciuniv.nanuvcell.model.Adsl;
import edu.sabanciuniv.nanuvcell.model.Roles;

import java.time.LocalDateTime;

public record AdslDto(Long id,
                      String tariffName,
                      double internetQuota,
                      double tariffPrice,
                      LocalDateTime tariffStartDate,
                      LocalDateTime tariffEndDate,
                      Roles role,
                      int internetSpeed,
                      double downloadSpeed,
                      double uploadSpeed) {

    public static AdslDto convert(Adsl from) {
        return new AdslDto(
                from.getId(),
                from.getTariffName(),
                from.getInternetQuota(),
                from.getTariffPrice(),
                from.getTariffStartDate(),
                from.getTariffEndDate(),
                from.getRole(),
                from.getInternetSpeed(),
                from.getDownloadSpeed(),
                from.getUploadSpeed());
    }
}
