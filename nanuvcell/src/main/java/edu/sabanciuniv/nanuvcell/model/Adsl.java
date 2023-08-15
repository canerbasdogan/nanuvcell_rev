package edu.sabanciuniv.nanuvcell.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class Adsl extends HomeInternet {

    private double downloadSpeed;
    private double uploadSpeed;
}
