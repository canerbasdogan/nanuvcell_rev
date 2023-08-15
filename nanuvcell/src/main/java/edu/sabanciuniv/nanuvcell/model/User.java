package edu.sabanciuniv.nanuvcell.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surName;
    private String gender;
    private String phoneNumber;
    private String email;
    private String address;
    private String password;
    @Enumerated(EnumType.STRING)
    private Roles role;
    @ManyToOne
    @JoinColumn(name = "mobile_tariff_id")
    private MobileTariff mobileTariff;
    @ManyToOne
    @JoinColumn(name = "home_internet_id")
    private HomeInternet homeInternet;
    @OneToOne(mappedBy = "user", cascade = CascadeType.REMOVE)
    private RemainingUse remainingUse;
    @OneToMany(mappedBy = "user")
    private List<Invoice> invoiceList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(surName, user.surName) && Objects.equals(gender, user.gender) && Objects.equals(phoneNumber, user.phoneNumber) && Objects.equals(email, user.email) && Objects.equals(address, user.address) && Objects.equals(password, user.password) && role == user.role && Objects.equals(mobileTariff, user.mobileTariff) && Objects.equals(homeInternet, user.homeInternet) && Objects.equals(remainingUse, user.remainingUse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surName, gender, phoneNumber, email, address, password, role, mobileTariff, homeInternet, remainingUse);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surName='" + surName + '\'' +
                ", gender='" + gender + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", eMail='" + email + '\'' +
                ", address='" + address + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", mobileTariff=" + mobileTariff +
                ", homeInternet=" + homeInternet +
                '}';
    }
}
