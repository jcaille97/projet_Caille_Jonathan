package org.formation.simplecash.service;

import org.formation.simplecash.dto.ClientCreateDto;
import org.formation.simplecash.dto.ClientDto;
import org.formation.simplecash.dto.ClientUpdateDto;

import java.util.Optional;

public interface ClientService {
    ClientDto creerClient(ClientCreateDto dto);

    boolean supprimerClient(Long idClient);

    Optional<ClientDto> modifierClient(Long idClient, ClientUpdateDto dto);

    Optional<ClientDto> recupererClient(Long idClient);
}
