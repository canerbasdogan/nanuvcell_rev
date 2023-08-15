package edu.sabanciuniv.nanuvcell.dto;

import edu.sabanciuniv.nanuvcell.model.Invoice;

import java.time.LocalDateTime;

public record InvoiceDto(Long id,
                         String name,
                         String surName,
                         String email,
                         String tariffName,
                         LocalDateTime tariffStartDate,
                         LocalDateTime tariffEndDate,
                         double tariffPrice) {

    public static InvoiceDto convert(Invoice from) {
        return new InvoiceDto(
                from.getId(),
                from.getUser().getName(),
                from.getUser().getSurName(),
                from.getUser().getEmail(),
                from.getTariff().getTariffName(),
                from.getTariff().getTariffStartDate(),
                from.getTariff().getTariffEndDate(),
                from.getTariff().getTariffPrice());
    }
}
