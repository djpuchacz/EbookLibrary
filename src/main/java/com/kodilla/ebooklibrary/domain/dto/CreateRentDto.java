package com.kodilla.ebooklibrary.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateRentDto {
    private int userId;
    private int itemId;
    private String customerName;
    private LocalDate rentDate;
}