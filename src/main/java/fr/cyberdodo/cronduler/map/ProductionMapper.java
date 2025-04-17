package fr.cyberdodo.cronduler.map;

import fr.cyberdodo.cronduler.dto.ProductionDto;
import fr.cyberdodo.cronduler.entity.Production;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductionMapper {
    ProductionDto toDto(Production entity);
    Production toEntity(ProductionDto dto);
}