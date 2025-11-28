package org.formation.simplecash.service;

import org.formation.simplecash.entity.CompteCourant;
import org.formation.simplecash.entity.CompteEpargne;

import java.math.BigDecimal;
import java.util.List;

public interface CompteService {
    BigDecimal recupererSoldeCompteCourant(Long idCompteCourant);
    BigDecimal recupererSoldeCompteEpargne(Long idCompteEpargne);

    void crediterCompteCourant(Long idCompteCourant, BigDecimal montant);
    void debiterCompteCourant(Long idCompteCourant, BigDecimal montant);

    void crediterCompteEpargne(Long idCompteEpargne, BigDecimal montant);
    void debiterCompteEpargne(Long idCompteEpargne, BigDecimal montant);

    void virementCourantVersCourant(Long idSource, Long idDestination, BigDecimal montant);

    List<CompteCourant> auditComptesCourants(BigDecimal montantMinimal);
    List<CompteEpargne> auditComptesEpargnes(BigDecimal montantMinimal);

}
