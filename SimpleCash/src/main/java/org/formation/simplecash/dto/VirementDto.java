package org.formation.simplecash.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record VirementDto(
        Long idSource,
        Long idDestination,
        @NotNull
        @DecimalMin(value = "0.01", message = "Le montant doit etre superieur a 0")
        BigDecimal montant
) {}
