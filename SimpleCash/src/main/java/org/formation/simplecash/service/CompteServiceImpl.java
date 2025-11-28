package org.formation.simplecash.service;

import lombok.RequiredArgsConstructor;
import org.formation.simplecash.entity.CompteCourant;
import org.formation.simplecash.entity.CompteEpargne;
import org.formation.simplecash.repository.CompteCourantRepository;
import org.formation.simplecash.repository.CompteEpargneRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CompteServiceImpl implements CompteService {
    private static final BigDecimal DECOUVERT_MAX = new BigDecimal("-1000.00");

    private final CompteCourantRepository compteCourantRepository;
    private final CompteEpargneRepository compteEpargneRepository;

    @Override
    @Transactional(readOnly = true)
    public BigDecimal recupererSoldeCompteCourant(Long idCompteCourant) {
        return getCompteCourantOrThrow(idCompteCourant).getSolde();
    }

    @Override
    @Transactional(readOnly = true)
    public BigDecimal recupererSoldeCompteEpargne(Long idCompteEpargne) {
        return getCompteEpargneOrThrow(idCompteEpargne).getSolde();
    }

    @Override
    public void crediterCompteCourant(Long idCompteCourant, BigDecimal montant) {
        if (montant.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("le montant doit etre superieur a 0");
        }
        CompteCourant compte = getCompteCourantOrThrow(idCompteCourant);
        compte.setSolde(compte.getSolde().add(montant));
        compteCourantRepository.save(compte);
    }

    @Override
    public void debiterCompteCourant(Long idCompteCourant, BigDecimal montant) {
        if (montant.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("le montant doit etre superieur a 0");
        }
        CompteCourant compte = getCompteCourantOrThrow(idCompteCourant);
        BigDecimal nouveauSolde = compte.getSolde().subtract(montant);

        if (nouveauSolde.compareTo(DECOUVERT_MAX) < 0) {
            throw new IllegalStateException("Découvert maximal (-1000) dépassé sur le compte courant ");
        }

        compte.setSolde(nouveauSolde);
        compteCourantRepository.save(compte);
    }

    @Override
    public void crediterCompteEpargne(Long idCompteEpargne, BigDecimal montant) {
        if (montant.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("le montant doit etre superieur a 0");
        }
        CompteEpargne compte = getCompteEpargneOrThrow(idCompteEpargne);
        compte.setSolde(compte.getSolde().add(montant));
        compteEpargneRepository.save(compte);
    }

    @Override
    public void debiterCompteEpargne(Long idCompteEpargne, BigDecimal montant) {
        if (montant.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("le montant doit etre superieur a 0");
        }
        CompteEpargne compte = getCompteEpargneOrThrow(idCompteEpargne);
        BigDecimal nouveauSolde = compte.getSolde().subtract(montant);

        if (nouveauSolde.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalStateException("Solde insuffisant sur le compte épargne");
        }

        compte.setSolde(nouveauSolde);
        compteEpargneRepository.save(compte);

    }

    @Override
    public void virementCourantVersCourant(Long idSource, Long idDestination, BigDecimal montant) {
        if (idSource.equals(idDestination)) {
            throw new IllegalArgumentException("Les comptes doivent etre differents");
        }
        if (montant.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("le montant doit etre superieur a 0");
        }

        CompteCourant source = getCompteCourantOrThrow(idSource);
        CompteCourant destination = getCompteCourantOrThrow(idDestination);

        BigDecimal nouveauSoldeSource = source.getSolde().subtract(montant);

        if (nouveauSoldeSource.compareTo(DECOUVERT_MAX) < 0) {
            throw new IllegalStateException("Découvert maximal (-1000) dépassé sur le compte source");
        }

        source.setSolde(nouveauSoldeSource);
        destination.setSolde(destination.getSolde().add(montant));

        compteCourantRepository.save(source);
        compteCourantRepository.save(destination);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CompteCourant> auditComptesCourants(BigDecimal montantMinimal) {
        if (montantMinimal.compareTo(DECOUVERT_MAX) <= 0) {
            throw new IllegalArgumentException("le montant doit etre superieur a -1000");
        }
        return compteCourantRepository.findAll().stream()
                .filter(c -> c.getSolde().compareTo(montantMinimal) >= 0)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CompteEpargne> auditComptesEpargnes(BigDecimal montantMinimal) {
        if (montantMinimal.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("le montant doit etre superieur a 0");
        }
        return compteEpargneRepository.findAll().stream()
                .filter(c -> c.getSolde().compareTo(montantMinimal) >= 0)
                .collect(Collectors.toList());
    }

    private CompteCourant getCompteCourantOrThrow(Long id) {
        return compteCourantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Compte courant introuvable"));
    }

    private CompteEpargne getCompteEpargneOrThrow(Long id) {
        return compteEpargneRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Compte épargne introuvable"));
    }
}
