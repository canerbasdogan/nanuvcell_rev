package edu.sabanciuniv.nanuvcell.dto;

import edu.sabanciuniv.nanuvcell.model.InvoicelessMobile;
import edu.sabanciuniv.nanuvcell.model.Roles;

import java.time.LocalDateTime;

public record InvoicelessMobileDto(Long id,
                                   String tariffName,
                                   double internetQuota,
                                   double tariffPrice,
                                   LocalDateTime tariffStartDate,
                                   LocalDateTime tariffEndDate,
                                   Roles role,
                                   int speakingQuota,
                                   int messageQuota,
                                   int tlQuota) {

    public static InvoicelessMobileDto convert(InvoicelessMobile from) {
        return new InvoicelessMobileDto(
                from.getId(),
                from.getTariffName(),
                from.getInternetQuota(),
                from.getTariffPrice(),
                from.getTariffStartDate(),
                from.getTariffEndDate(),
                from.getRole(),
                from.getSpeakingQuota(),
                from.getMessageQuota(),
                from.getTlQuota());
    }
}
