package com.kodilla.ebooklibrary.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "RENTS")
public class Rent {
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;
    @ManyToOne
    @JoinColumn(name = "ITEM_ID")
    private Item item;
    @Column(nullable = false)
    private String customerName;
    @Column(nullable = false)
    private LocalDate rentDate;
    @Column(nullable = false)
    private LocalDate expirationDate;

    public Rent(
            User user, Item item, String customerName, LocalDate rentDate, LocalDate expirationDate) {

        this.user = user;
        this.item = item;
        this.customerName = customerName;
        this.rentDate = rentDate;
        this.expirationDate = expirationDate;
    }
}
