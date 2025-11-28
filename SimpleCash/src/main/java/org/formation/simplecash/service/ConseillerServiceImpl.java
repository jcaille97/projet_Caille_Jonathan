package org.formation.simplecash.service;

import lombok.RequiredArgsConstructor;
import org.formation.simplecash.dto.ClientDto;
import org.formation.simplecash.dto.ConseillerCreateDto;
import org.formation.simplecash.dto.ConseillerDto;
import org.formation.simplecash.entity.Client;
import org.formation.simplecash.entity.Conseiller;
import org.formation.simplecash.mapper.ClientMapper;
import org.formation.simplecash.mapper.ConseillerMapper;
import org.formation.simplecash.repository.ClientRepository;
import org.formation.simplecash.repository.ConseillerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ConseillerServiceImpl implements ConseillerService {
    private final ConseillerRepository conseillerRepository;
    private final ClientRepository clientRepository;
    private final ConseillerMapper conseillerMapper;
    private final ClientMapper clientMapper;
    @Override
    public ConseillerDto creerConseiller(ConseillerCreateDto dto) {
        Conseiller conseiller = conseillerMapper.toEntity(dto);
        Conseiller saved = conseillerRepository.save(conseiller);
        return conseillerMapper.toDto(saved);
    }

    @Override
    public void supprimerConseiller(Long idConseiller) {
        Conseiller conseiller = conseillerRepository.findById(idConseiller)
                .orElseThrow(() -> new IllegalArgumentException("Le conseiller n'existe pas"));
        List<Client> clients = clientRepository.findByConseillerId(idConseiller);
        if (!clients.isEmpty()) {
            throw new IllegalStateException("Impossible de supprimer le conseiller, des clients lui sont encore associés.");
        }

        conseillerRepository.delete(conseiller);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClientDto> recupererClients(Long idConseiller) {
        if (conseillerRepository.findById(idConseiller).isEmpty()) {
            throw new IllegalArgumentException("Le conseiller n'existe pas");
        }
        return clientRepository.findByConseillerId(idConseiller)
                .stream()
                .map(clientMapper::toDto)
                .toList();
    }
}
