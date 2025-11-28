package org.formation.simplecash.service;

import org.formation.simplecash.dto.ClientDto;
import org.formation.simplecash.dto.ConseillerCreateDto;
import org.formation.simplecash.dto.ConseillerDto;

import java.util.List;

public interface ConseillerService {
    ConseillerDto creerConseiller(ConseillerCreateDto dto);

    void supprimerConseiller(Long idConseiller);

    List<ClientDto> recupererClients(Long idConseiller);
}
