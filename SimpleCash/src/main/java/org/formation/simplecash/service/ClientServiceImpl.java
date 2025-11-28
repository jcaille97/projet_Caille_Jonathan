package org.formation.simplecash.service;

import lombok.RequiredArgsConstructor;
import org.formation.simplecash.dto.ClientCreateDto;
import org.formation.simplecash.dto.ClientDto;
import org.formation.simplecash.dto.ClientUpdateDto;
import org.formation.simplecash.entity.Client;
import org.formation.simplecash.entity.Conseiller;
import org.formation.simplecash.mapper.ClientMapper;
import org.formation.simplecash.repository.ClientRepository;
import org.formation.simplecash.repository.ConseillerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final ConseillerRepository conseillerRepository;
    private final ClientMapper clientMapper;

    @Override
    public ClientDto creerClient(ClientCreateDto dto) {
        Conseiller conseiller = conseillerRepository.findById(dto.conseillerId())
                .orElseThrow(() -> new IllegalArgumentException("Conseiller introuvable"));
        int nbClients = clientRepository.findByConseillerId(dto.conseillerId()).size();
        if (nbClients >= 10) {
            throw new IllegalStateException("Le conseiller a déjà 10 clients.");
        }
        Client client = new Client(
                dto.nom(),
                dto.prenom(),
                dto.adresse(),
                dto.codePostal(),
                dto.ville(),
                dto.telephone(),
                dto.soldeCompteCourant(),
                dto.soldeCompteEpargne()
        );

        client.setConseiller(conseiller);

        Client saved = clientRepository.save(client);
        return clientMapper.toDto(saved);
    }

    @Override
    public boolean supprimerClient(Long idClient) {
        return clientRepository.findById(idClient)
                .map(client -> {
                    BigDecimal soldeCourant = client.getCompteCourant().getSolde();
                    BigDecimal soldeEpargne = client.getCompteEpargne().getSolde();

                    if (soldeCourant.compareTo(BigDecimal.ZERO) != 0
                            || soldeEpargne.compareTo(BigDecimal.ZERO) != 0) {
                        throw new IllegalStateException("Impossible de supprimer le client; comptes non soldés.");
                    }

                    clientRepository.delete(client);
                    return true;
                })
                .orElse(false);
    }

    @Override
    public Optional<ClientDto> modifierClient(Long idClient, ClientUpdateDto dto) {
        return clientRepository.findById(idClient).map(e -> {
            clientMapper.updateEntity(e, dto);
            return clientMapper.toDto(e);
        });
    }

    @Override
    public Optional<ClientDto> recupererClient(Long idClient) {
        return clientRepository.findById(idClient).map(clientMapper::toDto);
    }
}
