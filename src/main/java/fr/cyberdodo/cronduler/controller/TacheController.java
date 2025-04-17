package fr.cyberdodo.cronduler.controller;

import fr.cyberdodo.cronduler.dto.TacheDto;
import fr.cyberdodo.cronduler.entity.Tache;
import fr.cyberdodo.cronduler.map.TacheMapper;
import fr.cyberdodo.cronduler.service.TacheService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/taches")
@RequiredArgsConstructor
public class TacheController {

    private final TacheService service;
    private final TacheMapper mapper;

    @GetMapping
    public List<TacheDto> all() {
        return service.list()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public TacheDto one(@PathVariable Long id) {
        return mapper.toDto(service.get(id));
    }

    @PostMapping
    public ResponseEntity<TacheDto> create(@Valid @RequestBody TacheDto dto) {
        Tache entity = mapper.toEntity(dto);
        Tache saved = service.create(entity, dto.getGroupeId());
        return ResponseEntity.status(201).body(mapper.toDto(saved));
    }

    @PutMapping("/{id}")
    public TacheDto update(
            @PathVariable Long id,
            @Valid @RequestBody TacheDto dto) {
        Tache entity = mapper.toEntity(dto);
        Tache updated = service.update(id, entity, dto.getGroupeId());
        return mapper.toDto(updated);
    }

    @PatchMapping("/{id}/activate")
    public ResponseEntity<Void> activate(@PathVariable Long id,
                                         @RequestParam boolean actif) {
        Tache t = service.get(id);
        t.setActif(actif);
        service.update(id, t, t.getGroupe().getId());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}