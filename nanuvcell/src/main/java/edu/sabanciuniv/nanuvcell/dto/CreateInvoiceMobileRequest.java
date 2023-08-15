package edu.sabanciuniv.nanuvcell.dto;

import edu.sabanciuniv.nanuvcell.model.Roles;

import java.time.LocalDateTime;

public record CreateInvoiceMobileRequest(String tariffName,
                                         double internetQuota,
                                         double tariffPrice,
                                         String tariffStartDate,
                                         String tariffEndDate,
                                         Roles role,
                                         int speakingQuota,
                                         int messageQuota,
                                         boolean limitlessInternet) {
}
