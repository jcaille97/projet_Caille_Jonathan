package org.formation.simplecash.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "conseiller_id")
    private Conseiller conseiller;

    @Setter
    private String nom;
    @Setter
    private String prenom;
    @Setter
    private String adresse;
    @Setter
    private String codePostal;
    @Setter
    private String ville;
    @Setter
    private String telephone;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "compte_courant_id")
    private CompteCourant compteCourant;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "compte_epargne_id")
    private CompteEpargne compteEpargne;

    public Client() {
    }
    public Client(String nom,
                  String prenom,
                  String adresse,
                  String codePostal,
                  String ville,
                  String telephone,
                  BigDecimal soldeCourant,
                  BigDecimal soldeEpargne) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.codePostal = codePostal;
        this.ville = ville;
        this.telephone = telephone;
        this.compteCourant = new CompteCourant(soldeCourant);
        this.compteEpargne = new CompteEpargne(soldeEpargne);
    }

    public void setConseiller(Conseiller conseiller) {
        this.conseiller = conseiller;
    }
}
