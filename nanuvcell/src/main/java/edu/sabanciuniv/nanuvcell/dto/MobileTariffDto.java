package edu.sabanciuniv.nanuvcell.dto;

import edu.sabanciuniv.nanuvcell.model.HomeInternet;
import edu.sabanciuniv.nanuvcell.model.MobileTariff;
import edu.sabanciuniv.nanuvcell.model.Roles;

public record MobileTariffDto(Long id,
                              String tariffName,
                              double internetQuota,
                              int speakingQuota,
                              int messageQuota,
                              double tariffPrice,
                              Roles role) {

    public static MobileTariffDto convert(MobileTariff from) {
        return new MobileTariffDto(
                from.getId(),
                from.getTariffName(),
                from.getInternetQuota(),
                from.getSpeakingQuota(),
                from.getMessageQuota(),
                from.getTariffPrice(),
                from.getRole());
    }
}
