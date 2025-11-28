package org.formation.simplecash.mapper;

import org.formation.simplecash.dto.ClientDto;
import org.formation.simplecash.dto.ClientUpdateDto;
import org.formation.simplecash.entity.Client;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ClientMapper {
    @Mapping(target = "conseillerId", source = "conseiller.id")
    @Mapping(target = "numeroCompteCourant", source = "compteCourant.numeroCompte")
    @Mapping(target = "soldeCompteCourant", source = "compteCourant.solde")
    @Mapping(target = "numeroCompteEpargne", source = "compteEpargne.numeroCompte")
    @Mapping(target = "soldeCompteEpargne", source = "compteEpargne.solde")
    ClientDto toDto(Client client);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "conseiller", ignore = true)
    @Mapping(target = "compteCourant", ignore = true)
    @Mapping(target = "compteEpargne", ignore = true)
    void updateEntity(@MappingTarget Client client, ClientUpdateDto dto);
}
