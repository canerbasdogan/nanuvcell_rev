package edu.sabanciuniv.nanuvcell.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    //@ManyToOne
    //@JoinColumn(name = "tariff_id")
    @OneToOne
    private Tariff tariff;
    private Double invoiceAmount;
    private LocalDateTime invoiceAmountPaymentDate;
    private LocalDateTime invoiceAmountPaymentLastDate;
}
