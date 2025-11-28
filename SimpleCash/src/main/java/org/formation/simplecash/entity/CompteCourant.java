package org.formation.simplecash.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

@Entity
@Getter
public class CompteCourant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numeroCompte = generateNumeroCompte();
    @Setter
    private BigDecimal solde;
    private LocalDate dateOuverture;

    public CompteCourant() {
    }

    public CompteCourant(BigDecimal solde) {
        this.solde = solde;
        this.dateOuverture = LocalDate.now();
    }

    private static String generateNumeroCompte() {
        long randomPart = ThreadLocalRandom.current()
                .nextLong(1_000_000_000L, 10_000_000_000L);
        return "CC" + randomPart;
    }
}
