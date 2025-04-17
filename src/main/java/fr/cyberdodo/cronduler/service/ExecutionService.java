package fr.cyberdodo.cronduler.service;

import fr.cyberdodo.cronduler.entity.Execution;
import fr.cyberdodo.cronduler.exception.ResourceNotFoundException;
import fr.cyberdodo.cronduler.repository.ExecutionRepository;
import fr.cyberdodo.cronduler.repository.TacheRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ExecutionService {
    private final ExecutionRepository repo;
    private final TacheRepository tacheRepo;

    public Execution create(Execution e) {
        return repo.save(e);
    }

    public Execution get(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Execution non trouvée: " + id));
    }

    public List<Execution> list(Long tacheId, Instant from, Instant to) {
        if (tacheId != null && from != null && to != null) {
            return repo.findByTacheIdAndDateLancementBetween(tacheId, from, to);
        }
        return repo.findAll();
    }

    public void delete(Long id) {
        repo.delete(get(id));
    }
}