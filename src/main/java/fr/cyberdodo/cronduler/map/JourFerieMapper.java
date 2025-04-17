package fr.cyberdodo.cronduler.map;

import fr.cyberdodo.cronduler.dto.JourFerieDto;
import fr.cyberdodo.cronduler.entity.JourFerie;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JourFerieMapper {
    JourFerieDto toDto(JourFerie entity);
    JourFerie toEntity(JourFerieDto dto);
}