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
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String campaignName;
    private String campaignDetail;
    private LocalDateTime campaignStartDate;
    private LocalDateTime campaignEndDate;
    private double internetQuota;
    private double speakingQuota;
    private int messageQuota;
    private double discountFactor;
    @OneToOne
    @JoinColumn(name = "mobile_tariff_id")
    private MobileTariff mobileTariff;
}
