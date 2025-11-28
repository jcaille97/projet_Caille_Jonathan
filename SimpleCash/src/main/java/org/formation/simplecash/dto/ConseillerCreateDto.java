package org.formation.simplecash.dto;

import jakarta.validation.constraints.NotBlank;

public record ConseillerCreateDto(
        @NotBlank String nom
) {}
