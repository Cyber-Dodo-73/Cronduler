package fr.cyberdodo.cronduler.controller;

import fr.cyberdodo.cronduler.dto.LogExecutionDto;
import fr.cyberdodo.cronduler.map.LogExecutionMapper;
import fr.cyberdodo.cronduler.repository.LogExecutionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/logs")
@RequiredArgsConstructor
public class LogExecutionController {
    private final LogExecutionRepository repo;
    private final LogExecutionMapper mapper;

    @GetMapping
    public List<LogExecutionDto> all() {
        return repo.findAll().stream().map(mapper::toDto).toList();
    }

    @GetMapping("/execution/{execId}")
    public List<LogExecutionDto> byExecution(@PathVariable Long execId) {
        return repo.findAll().stream()
                .filter(log -> log.getExecution().getId().equals(execId))
                .map(mapper::toDto).toList();
    }
}