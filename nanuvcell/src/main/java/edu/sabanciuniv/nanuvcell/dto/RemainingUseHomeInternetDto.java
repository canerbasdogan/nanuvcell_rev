package edu.sabanciuniv.nanuvcell.dto;

import edu.sabanciuniv.nanuvcell.model.RemainingUse;
import edu.sabanciuniv.nanuvcell.model.RemainingUseHomeInternet;

import java.time.LocalDateTime;

public record RemainingUseHomeInternetDto(Long id,
                                          double remainingInternet,
                                          String tariffName,
                                          LocalDateTime tariffStartDate,
                                          LocalDateTime tariffEndDate,
                                          double tariffPrice) {

    public static RemainingUseHomeInternetDto convert(RemainingUseHomeInternet from) {
        return new RemainingUseHomeInternetDto(
                from.getId(),
                from.getRemainingInternet(),
                from.getHomeInternet().getTariffName(),
                from.getUser().getHomeInternetTariffStartDate(),
                from.getUser().getHomeInternetTariffEndDate(),
                from.getHomeInternet().getTariffPrice());
    }
}
