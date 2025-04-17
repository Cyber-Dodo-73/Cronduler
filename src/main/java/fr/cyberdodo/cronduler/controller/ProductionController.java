package fr.cyberdodo.cronduler.controller;

import fr.cyberdodo.cronduler.dto.ProductionDto;
import fr.cyberdodo.cronduler.entity.Production;
import fr.cyberdodo.cronduler.map.ProductionMapper;
import fr.cyberdodo.cronduler.service.ProductionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productions")
@RequiredArgsConstructor
public class ProductionController {
    private final ProductionService service;
    private final ProductionMapper mapper;

    @GetMapping
    public List<ProductionDto> all() {
        return service.list().stream().map(mapper::toDto).toList();
    }

    @GetMapping("/{id}")
    public ProductionDto one(@PathVariable Long id) {
        return mapper.toDto(service.get(id));
    }

    @PostMapping
    public ResponseEntity<ProductionDto> create(@Valid @RequestBody ProductionDto dto) {
        Production saved = service.create(mapper.toEntity(dto));
        return ResponseEntity.status(201).body(mapper.toDto(saved));
    }

    @PutMapping("/{id}")
    public ProductionDto update(@PathVariable Long id, @Valid @RequestBody ProductionDto dto) {
        return mapper.toDto(service.update(id, mapper.toEntity(dto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}