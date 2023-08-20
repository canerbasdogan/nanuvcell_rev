package edu.sabanciuniv.nanuvcell.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class Tariff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tariffName;
    private double internetQuota;
    private double tariffPrice;
    //Tarifeler kişiye özel olmamalı. Her bir User için ayrı registrationDate ve packageEndDate olmalı.
    //private LocalDateTime tariffStartDate;
    //private LocalDateTime tariffEndDate;
    @Enumerated(EnumType.STRING)
    private Roles role;
    //@OneToMany(mappedBy = "tariff")
    //private List<Invoice> invoiceList;

    @Override
    public String toString() {
        return "Tariff{" +
                "id=" + id +
                ", tariffName='" + tariffName + '\'' +
                ", internetQuota=" + internetQuota +
                ", tariffPrice=" + tariffPrice +
                /*", tariffStartDate=" + tariffStartDate +
                ", tariffEndDate=" + tariffEndDate +*/
                ", role=" + role +
                '}';
    }
}
