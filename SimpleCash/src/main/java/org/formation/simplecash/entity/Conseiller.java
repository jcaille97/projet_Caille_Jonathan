package org.formation.simplecash.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Conseiller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    public Conseiller() {}

    public Conseiller(String nom) {
        this.nom = nom;
    }
}
