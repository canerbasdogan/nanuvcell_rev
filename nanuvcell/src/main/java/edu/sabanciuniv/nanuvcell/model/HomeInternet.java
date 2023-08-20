package edu.sabanciuniv.nanuvcell.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class HomeInternet extends Tariff {

    private int internetSpeed;
    @OneToMany(mappedBy = "homeInternet")
    private List<RemainingUseHomeInternet> remainingUseHomeInternetList;
    @OneToMany(mappedBy = "homeInternet", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private List<User> userList;
}
