package fr.cyberdodo.cronduler.map;

import fr.cyberdodo.cronduler.dto.TacheDto;
import fr.cyberdodo.cronduler.entity.Tache;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TacheMapper {
    @Mapping(source = "groupe.id", target = "groupeId")
    TacheDto toDto(Tache entity);
    Tache toEntity(TacheDto dto);
}