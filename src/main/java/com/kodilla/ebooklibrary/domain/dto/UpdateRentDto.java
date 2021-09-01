package com.kodilla.ebooklibrary.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UpdateRentDto {
    private long userId;
    private long id;
    private String customerName;
    private LocalDate rentDate;
    private LocalDate expirationDate;
}