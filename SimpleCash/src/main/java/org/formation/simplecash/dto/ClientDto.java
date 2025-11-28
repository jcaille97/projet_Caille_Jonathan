package org.formation.simplecash.dto;

import java.math.BigDecimal;

public record ClientDto(
        Long id,
        Long conseillerId,

        String nom,
        String prenom,
        String adresse,
        String codePostal,
        String ville,
        String telephone,

        String numeroCompteCourant,
        BigDecimal soldeCompteCourant,
        String numeroCompteEpargne,
        BigDecimal soldeCompteEpargne
) {}
