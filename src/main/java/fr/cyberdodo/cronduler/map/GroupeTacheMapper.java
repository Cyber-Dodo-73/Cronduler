package fr.cyberdodo.cronduler.map;

import fr.cyberdodo.cronduler.dto.GroupeTacheDto;
import fr.cyberdodo.cronduler.entity.GroupeTache;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GroupeTacheMapper {
    @Mapping(source = "production.id", target = "productionId")
    GroupeTacheDto toDto(GroupeTache entity);
    GroupeTache toEntity(GroupeTacheDto dto);
}