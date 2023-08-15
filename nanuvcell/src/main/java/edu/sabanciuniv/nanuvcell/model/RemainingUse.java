package edu.sabanciuniv.nanuvcell.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RemainingUse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int remainingMessage;
    private int remainingSpeaking;
    private int remainingInternet;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "mobile_tariff_id")
    private MobileTariff mobileTariff;

    @Override
    public String toString() {
        return "RemainingUse{" +
                "id=" + id +
                ", remainingMessage=" + remainingMessage +
                ", remainingSpeaking=" + remainingSpeaking +
                ", user=" + user +
                ", mobileTariff=" + mobileTariff +
                '}';
    }
}
