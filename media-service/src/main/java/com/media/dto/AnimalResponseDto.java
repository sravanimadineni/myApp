package com.media.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnimalResponseDto {
    private String name;
    private double price;
    private String species;
    private String breed;
}

