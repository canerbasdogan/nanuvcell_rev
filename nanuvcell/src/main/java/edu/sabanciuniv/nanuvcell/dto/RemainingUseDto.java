package edu.sabanciuniv.nanuvcell.dto;

import edu.sabanciuniv.nanuvcell.model.RemainingUse;

import java.time.LocalDateTime;

public record RemainingUseDto(Long id,
                              int remainingMessage,
                              int remainingSpeaking,
                              double remainingInternet,
                              String tariffName,
                              LocalDateTime tariffStartDate,
                              LocalDateTime tariffEndDate,
                              double tariffPrice) {

    public static RemainingUseDto convert(RemainingUse from) {
        return new RemainingUseDto(
                from.getId(),
                from.getRemainingMessage(),
                from.getRemainingSpeaking(),
                from.getRemainingInternet(),
                from.getMobileTariff().getTariffName(),
                from.getUser().getMobileTariffStartDate(),
                from.getUser().getMobileTariffEndDate(),
                from.getMobileTariff().getTariffPrice());
    }
}
