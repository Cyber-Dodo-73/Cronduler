package fr.cyberdodo.cronduler.controller;

import fr.cyberdodo.cronduler.dto.JourFerieDto;
import fr.cyberdodo.cronduler.entity.JourFerie;
import fr.cyberdodo.cronduler.map.JourFerieMapper;
import fr.cyberdodo.cronduler.service.JourFerieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jours-feries")
@RequiredArgsConstructor
public class JourFerieController {
    private final JourFerieService service;
    private final JourFerieMapper mapper;

    @GetMapping
    public List<JourFerieDto> all() {
        return service.list().stream().map(mapper::toDto).toList();
    }

    @GetMapping("/{id}")
    public JourFerieDto one(@PathVariable Long id) {
        return mapper.toDto(service.get(id));
    }

    @PostMapping
    public ResponseEntity<JourFerieDto> create(@Valid @RequestBody JourFerieDto dto) {
        JourFerie j = service.create(mapper.toEntity(dto));
        return ResponseEntity.status(201).body(mapper.toDto(j));
    }

    @PutMapping("/{id}")
    public JourFerieDto update(@PathVariable Long id, @Valid @RequestBody JourFerieDto dto) {
        return mapper.toDto(service.update(id, mapper.toEntity(dto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}