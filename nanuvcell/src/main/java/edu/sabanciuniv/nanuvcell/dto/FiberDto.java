package edu.sabanciuniv.nanuvcell.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import edu.sabanciuniv.nanuvcell.model.Fiber;
import edu.sabanciuniv.nanuvcell.model.Roles;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public record FiberDto(Long id,
                       String tariffName,
                       double internetQuota,
                       double tariffPrice,
                       //LocalDateTime tariffStartDate,
                       //LocalDateTime tariffEndDate,
                       Roles role,
                       int internetSpeed,
                       boolean fiberInfrastructure) {

    public static FiberDto convert(Fiber from) {
        return new FiberDto(
                from.getId(),
                from.getTariffName(),
                from.getInternetQuota(),
                from.getTariffPrice(),
                //from.getTariffStartDate(),
                //from.getTariffEndDate(),
                from.getRole(),
                from.getInternetSpeed(),
                from.isFiberInfrastructure());
    }
}
