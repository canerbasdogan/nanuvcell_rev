package edu.sabanciuniv.nanuvcell.dto;

import edu.sabanciuniv.nanuvcell.model.Roles;

public record LoginResponseDto(Long id,
                               Roles role) {
}
