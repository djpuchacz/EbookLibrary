package com.kodilla.ebooklibrary.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.catalina.User;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "TITLES")
public class Title {
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;
    @Column(nullable = false)
    private String author;
    @Column(nullable = false)
    private String title;
    private int year;

    public Title(User user, String author, String title, int year) {
        this.user = user;
        this.author = author;
        this.title = title;
        this.year = year;
    }
}