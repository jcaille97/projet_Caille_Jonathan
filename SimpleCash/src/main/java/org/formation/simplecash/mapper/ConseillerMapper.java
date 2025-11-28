package org.formation.simplecash.mapper;
import org.formation.simplecash.dto.ConseillerCreateDto;
import org.formation.simplecash.dto.ConseillerDto;
import org.formation.simplecash.entity.Conseiller;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ConseillerMapper {
    ConseillerDto toDto(Conseiller conseiller);

    @Mapping(target = "id", ignore = true)
    Conseiller toEntity(ConseillerCreateDto dto);
}
