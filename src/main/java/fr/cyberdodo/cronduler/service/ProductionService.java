package fr.cyberdodo.cronduler.service;

import fr.cyberdodo.cronduler.entity.Production;
import fr.cyberdodo.cronduler.exception.ResourceNotFoundException;
import fr.cyberdodo.cronduler.repository.ProductionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductionService {
    private final ProductionRepository repo;

    public Production create(Production p) {
        return repo.save(p);
    }

    public Production get(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Production non trouvée: " + id));
    }

    public List<Production> list() {
        return repo.findAll();
    }

    public Production update(Long id, Production p) {
        Production ex = get(id);
        ex.setNom(p.getNom());
        ex.setDebut(p.getDebut());
        ex.setFin(p.getFin());
        return repo.save(ex);
    }

    public void delete(Long id) {
        repo.delete(get(id));
    }
}