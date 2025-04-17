package fr.cyberdodo.cronduler.service;

import fr.cyberdodo.cronduler.entity.GroupeTache;
import fr.cyberdodo.cronduler.exception.ResourceNotFoundException;
import fr.cyberdodo.cronduler.repository.GroupeTacheRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class GroupeTacheService {

    private final GroupeTacheRepository repo;
    private final ProductionService productionService;

    public GroupeTache create(GroupeTache g, Long productionId) {
        // Charge la Production existante
        var prod = productionService.get(productionId);
        g.setProduction(prod);
        return repo.save(g);
    }

    public GroupeTache get(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Groupe non trouvé: " + id));
    }

    public List<GroupeTache> list() {
        return repo.findAll();
    }

    public GroupeTache update(Long id, GroupeTache g, Long productionId) {
        var existing = get(id);
        var prod = productionService.get(productionId);
        existing.setNom(g.getNom());
        existing.setPriorite(g.getPriorite());
        existing.setConcurrent(g.getConcurrent());
        existing.setProduction(prod);
        return repo.save(existing);
    }

    public void delete(Long id) {
        repo.delete(get(id));
    }
}