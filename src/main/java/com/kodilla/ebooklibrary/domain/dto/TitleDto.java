package com.kodilla.ebooklibrary.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TitleDto {
    private long id;
    private String author;
    private String title;
    private int year;
}
