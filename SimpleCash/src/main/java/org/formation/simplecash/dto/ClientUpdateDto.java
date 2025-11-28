package org.formation.simplecash.dto;

public record ClientUpdateDto(
        String nom,
        String prenom,
        String adresse,
        String codePostal,
        String ville,
        String telephone
) {}
