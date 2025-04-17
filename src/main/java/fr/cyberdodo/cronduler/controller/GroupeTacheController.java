package fr.cyberdodo.cronduler.controller;

import fr.cyberdodo.cronduler.dto.GroupeTacheDto;
import fr.cyberdodo.cronduler.entity.GroupeTache;
import fr.cyberdodo.cronduler.map.GroupeTacheMapper;
import fr.cyberdodo.cronduler.service.GroupeTacheService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groupes")
@RequiredArgsConstructor
public class GroupeTacheController {
    private final GroupeTacheService service;
    private final GroupeTacheMapper mapper;

    @GetMapping
    public List<GroupeTacheDto> all() {
        return service.list().stream().map(mapper::toDto).toList();
    }

    @GetMapping("/{id}")
    public GroupeTacheDto one(@PathVariable Long id) {
        return mapper.toDto(service.get(id));
    }

    @PostMapping
    public ResponseEntity<GroupeTacheDto> create(@Valid @RequestBody GroupeTacheDto dto) {
        GroupeTache g = service.create(mapper.toEntity(dto));
        return ResponseEntity.status(201).body(mapper.toDto(g));
    }

    @PutMapping("/{id}")
    public GroupeTacheDto update(@PathVariable Long id, @Valid @RequestBody GroupeTacheDto dto) {
        return mapper.toDto(service.update(id, mapper.toEntity(dto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}