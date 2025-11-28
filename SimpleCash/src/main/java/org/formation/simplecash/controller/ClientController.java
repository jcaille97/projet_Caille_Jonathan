package org.formation.simplecash.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.formation.simplecash.dto.ClientCreateDto;
import org.formation.simplecash.dto.ClientDto;
import org.formation.simplecash.dto.ClientUpdateDto;
import org.formation.simplecash.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @PostMapping
    public ClientDto createClient(@RequestBody @Valid ClientCreateDto dto) {
        return clientService.creerClient(dto);
    }

    @GetMapping("{id}")
    public ResponseEntity<ClientDto> getClient(@PathVariable Long id) {
        return clientService.recupererClient(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("{id}")
    public ResponseEntity<ClientDto> updateClient(@PathVariable Long id,
                                                  @RequestBody @Valid ClientUpdateDto dto) {
        return clientService.modifierClient(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.supprimerClient(id);
        return ResponseEntity.noContent().build();
    }
}
