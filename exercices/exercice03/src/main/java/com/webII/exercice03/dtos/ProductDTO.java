package com.webII.exercice03.dtos;

import jakarta.validation.constraints.NotBlank;

public record ProductDTO(
        Long id,
        @NotBlank String name,
        @NotBlank String description,
        int quantity) {
    /* Empty! */
}
