package com.kodilla.ebooklibrary.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.kodilla.ebooklibrary.domain.dto.ItemStatus;

import java.time.LocalDate;
import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "ITEMS")
public class Item {
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;
    @ManyToOne
    @JoinColumn(name = "TITLE_ID")
    private Title title;
    @Column(nullable = false)
    private LocalDate purchaseDate;
    private ItemStatus status;

    public Item(User user, Title title, LocalDate purchaseDate) {
        this.user = user;
        this.title = title;
        this.purchaseDate = purchaseDate;
        status = ItemStatus.AVAILABLE;
    }
}
