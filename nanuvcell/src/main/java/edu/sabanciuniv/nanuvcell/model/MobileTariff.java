package edu.sabanciuniv.nanuvcell.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class MobileTariff extends Tariff {

    private int speakingQuota;
    private int messageQuota;
    @OneToMany(mappedBy = "mobileTariff", fetch = FetchType.LAZY)
    private List<User> userList;
    @OneToMany(mappedBy = "mobileTariff")
    private List<RemainingUse> remainingUseList;
    @OneToOne(mappedBy = "mobileTariff", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Campaign campaign;

    @Override
    public String toString() {
        return "MobileTariff{" +
                "tariffName=" + this.getTariffName() +
                ", internetQuota=" + this.getInternetQuota() +
                ", tariffPrice=" + this.getTariffPrice() +
                /*", tariffStartDate=" + this.getTariffStartDate() +
                ", tariffEndDate=" + this.getTariffEndDate() +*/
                ", speakingQuota=" + speakingQuota +
                ", messageQuota=" + messageQuota +
                '}';
    }
}
