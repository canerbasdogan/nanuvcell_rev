package edu.sabanciuniv.nanuvcell.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class Fiber extends HomeInternet {

    private boolean fiberInfrastructure;
}
