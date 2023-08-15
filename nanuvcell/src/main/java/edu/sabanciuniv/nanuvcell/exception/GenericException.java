package edu.sabanciuniv.nanuvcell.exception;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class GenericException {

    private String message;
    private LocalDateTime time;
}
