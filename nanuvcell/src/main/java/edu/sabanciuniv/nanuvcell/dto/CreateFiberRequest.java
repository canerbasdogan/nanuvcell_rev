package edu.sabanciuniv.nanuvcell.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import edu.sabanciuniv.nanuvcell.model.Roles;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public record CreateFiberRequest(String tariffName,
                                 double internetQuota,
                                 double tariffPrice,
                                 //String tariffStartDate,
                                 //String tariffEndDate,
                                 Roles role,
                                 int internetSpeed,
                                 boolean fiberInfrastructure) {
}
