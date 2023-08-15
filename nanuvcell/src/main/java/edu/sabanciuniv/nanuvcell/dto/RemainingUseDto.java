package edu.sabanciuniv.nanuvcell.dto;

import edu.sabanciuniv.nanuvcell.model.RemainingUse;

import java.time.LocalDateTime;

public record RemainingUseDto(Long id,
                              int remainingMessage,
                              int remainingSpeaking,
                              int remainingInternet,
                              String tariffName,
                              LocalDateTime tariffEndDate,
                              double tariffPrice) {

    public static RemainingUseDto convert(RemainingUse from) {
        return new RemainingUseDto(
                from.getId(),
                from.getRemainingMessage(),
                from.getRemainingSpeaking(),
                from.getRemainingInternet(),
                from.getMobileTariff().getTariffName(),
                from.getMobileTariff().getTariffEndDate(),
                from.getMobileTariff().getTariffPrice());
    }
}
