package edu.sabanciuniv.nanuvcell.dto;

import edu.sabanciuniv.nanuvcell.model.Invoice;
import edu.sabanciuniv.nanuvcell.model.Tariff;

import java.time.LocalDateTime;

public record InvoiceDto(Long id,
                         String name,
                         String surName,
                         String email,
                         //Tariff tariff,
                         String tariffName,
                         double tariffPrice,
                         LocalDateTime invoicePaymentDate,
                         LocalDateTime invoicePaymentLastDate) {

    public static InvoiceDto convert(Invoice from) {
        return new InvoiceDto(
                from.getId(),
                from.getUser().getName(),
                from.getUser().getSurName(),
                from.getUser().getEmail(),
                //from.getTariff(),
                from.getTariff().getTariffName(),
                from.getTariff().getTariffPrice(),
                from.getInvoiceAmountPaymentDate(),
                from.getInvoiceAmountPaymentLastDate());
    }
}
