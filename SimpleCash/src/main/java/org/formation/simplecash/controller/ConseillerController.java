package org.formation.simplecash.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.formation.simplecash.dto.ClientDto;
import org.formation.simplecash.dto.ConseillerCreateDto;
import org.formation.simplecash.dto.ConseillerDto;
import org.formation.simplecash.service.ConseillerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/conseillers")
@RequiredArgsConstructor
public class ConseillerController {
    private final ConseillerService conseillerService;

    @PostMapping
    public ConseillerDto createConseiller(@RequestBody @Valid ConseillerCreateDto dto) {
        return conseillerService.creerConseiller(dto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteConseiller(@PathVariable Long id) {
        conseillerService.supprimerConseiller(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}/clients")
    public ResponseEntity<List<ClientDto>> getClients(@PathVariable Long id) {
        return conseillerService.recupererClients(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
