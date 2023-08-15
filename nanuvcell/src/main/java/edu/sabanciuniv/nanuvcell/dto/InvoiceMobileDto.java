package edu.sabanciuniv.nanuvcell.dto;

import edu.sabanciuniv.nanuvcell.model.InvoiceMobile;
import edu.sabanciuniv.nanuvcell.model.Roles;

import java.time.LocalDateTime;

public record InvoiceMobileDto(Long id,
                               String tariffName,
                               double internetQuota,
                               double tariffPrice,
                               LocalDateTime tariffStartDate,
                               LocalDateTime tariffEndDate,
                               Roles role,
                               int speakingQuota,
                               int messageQuota,
                               boolean limitlessInternet) {

    public static InvoiceMobileDto convert(InvoiceMobile from) {
        return new InvoiceMobileDto(
                from.getId(),
                from.getTariffName(),
                from.getInternetQuota(),
                from.getTariffPrice(),
                from.getTariffStartDate(),
                from.getTariffEndDate(),
                from.getRole(),
                from.getSpeakingQuota(),
                from.getMessageQuota(),
                from.isLimitlessInternet());
    }
}
