package fr.cyberdodo.cronduler.controller;

import fr.cyberdodo.cronduler.dto.ExecutionDto;
import fr.cyberdodo.cronduler.map.ExecutionMapper;
import fr.cyberdodo.cronduler.service.ExecutionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/executions")
@RequiredArgsConstructor
public class ExecutionController {
    private final ExecutionService service;
    private final ExecutionMapper mapper;

    @GetMapping
    public List<ExecutionDto> all(
            @RequestParam(required = false) Long tache,
            @RequestParam(required = false) Instant from,
            @RequestParam(required = false) Instant to) {
        return service.list(tache, from, to)
                .stream().map(mapper::toDto).toList();
    }

    @GetMapping("/{id}")
    public ExecutionDto one(@PathVariable Long id) {
        return mapper.toDto(service.get(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}