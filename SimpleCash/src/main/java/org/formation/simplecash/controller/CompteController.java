package org.formation.simplecash.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.formation.simplecash.dto.OperationMontantDto;
import org.formation.simplecash.dto.VirementDto;
import org.formation.simplecash.service.CompteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/comptes")
@RequiredArgsConstructor
public class CompteController {
    private final CompteService compteService;

    @GetMapping("/courant/{id}/solde")
    public ResponseEntity<BigDecimal> getSoldeCompteCourant(@PathVariable Long id) {
        return ResponseEntity.ok(compteService.recupererSoldeCompteCourant(id));
    }

    @GetMapping("/epargne/{id}/solde")
    public ResponseEntity<BigDecimal> getSoldeCompteEpargne(@PathVariable Long id) {
        return ResponseEntity.ok(compteService.recupererSoldeCompteEpargne(id));
    }

    @PostMapping("/courant/{id}/credit")
    public ResponseEntity<Void> creditCompteCourant(@PathVariable Long id,
                                                    @RequestBody @Valid OperationMontantDto dto) {
        compteService.crediterCompteCourant(id, dto.montant());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/courant/{id}/debit")
    public ResponseEntity<Void> debitCompteCourant(@PathVariable Long id,
                                                   @RequestBody @Valid OperationMontantDto dto) {
        compteService.debiterCompteCourant(id, dto.montant());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/epargne/{id}/credit")
    public ResponseEntity<Void> creditCompteEpargne(@PathVariable Long id,
                                                    @RequestBody @Valid OperationMontantDto dto) {
        compteService.crediterCompteEpargne(id, dto.montant());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/epargne/{id}/debit")
    public ResponseEntity<Void> debitCompteEpargne(@PathVariable Long id,
                                                   @RequestBody @Valid OperationMontantDto dto) {
        compteService.debiterCompteEpargne(id, dto.montant());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/courant/virement")
    public ResponseEntity<Void> virementCourantVersCourant(@RequestBody @Valid VirementDto dto) {
        compteService.virementCourantVersCourant(
                dto.idSource(),
                dto.idDestination(),
                dto.montant()
        );
        return ResponseEntity.noContent().build();
    }
}
