package org.formation.simplecash.service;

import org.formation.simplecash.dto.ClientDto;
import org.formation.simplecash.dto.ConseillerCreateDto;
import org.formation.simplecash.dto.ConseillerDto;

import java.util.List;
import java.util.Optional;

public interface ConseillerService {
    ConseillerDto creerConseiller(ConseillerCreateDto dto);

    void supprimerConseiller(Long idConseiller);

    Optional<List<ClientDto>> recupererClients(Long idConseiller);
}
