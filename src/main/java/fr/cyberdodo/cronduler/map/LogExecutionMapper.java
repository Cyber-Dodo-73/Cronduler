package fr.cyberdodo.cronduler.map;

import fr.cyberdodo.cronduler.dto.LogExecutionDto;
import fr.cyberdodo.cronduler.entity.LogExecution;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LogExecutionMapper {
    @Mapping(source = "execution.id", target = "executionId")
    LogExecutionDto toDto(LogExecution entity);
}