package org.formation.simplecash.dto;

import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record ClientCreateDto(
        Long conseillerId,

        @NotBlank String nom,
        @NotBlank String prenom,
        @NotBlank String adresse,
        @NotBlank String codePostal,
        @NotBlank String ville,
        @NotBlank String telephone,

        BigDecimal soldeCompteCourant,
        BigDecimal soldeCompteEpargne
) {}
