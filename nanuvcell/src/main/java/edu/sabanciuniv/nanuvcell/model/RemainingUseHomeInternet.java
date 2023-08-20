package edu.sabanciuniv.nanuvcell.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RemainingUseHomeInternet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double remainingInternet;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "home_internet_id")
    private HomeInternet homeInternet;

    @Override
    public String toString() {
        return "RemainingUse{" +
                "id=" + id +
                ", remainingInternet=" + remainingInternet +
                ", user=" + user +
                ", homeInternet=" + homeInternet +
                '}';
    }
}
