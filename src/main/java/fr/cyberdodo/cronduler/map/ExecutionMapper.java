package fr.cyberdodo.cronduler.map;

import fr.cyberdodo.cronduler.dto.ExecutionDto;
import fr.cyberdodo.cronduler.entity.Execution;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface ExecutionMapper {
    @Mapping(source = "tache.id", target = "tacheId")
    ExecutionDto toDto(Execution entity);
}