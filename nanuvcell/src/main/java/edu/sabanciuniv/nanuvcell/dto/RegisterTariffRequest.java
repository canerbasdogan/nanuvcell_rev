package edu.sabanciuniv.nanuvcell.dto;

public record RegisterTariffRequest(Long userId,
                                    Long tariffId,
                                    Long creditCardNumber,
                                    Long creditCardCVV,
                                    String creditCardPassword
                                    ) {
}
