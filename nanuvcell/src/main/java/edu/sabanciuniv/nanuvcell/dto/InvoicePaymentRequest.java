package edu.sabanciuniv.nanuvcell.dto;

public record InvoicePaymentRequest(Long userId,
                                    Long creditCardNumber,
                                    Long creditCardCVV,
                                    String creditCardPassword
                                    ) {
}
