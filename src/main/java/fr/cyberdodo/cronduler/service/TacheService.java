package fr.cyberdodo.cronduler.service;

import fr.cyberdodo.cronduler.entity.Tache;
import fr.cyberdodo.cronduler.exception.ResourceNotFoundException;
import fr.cyberdodo.cronduler.repository.TacheRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TacheService {
    private final TacheRepository repo;

    public Tache create(Tache t) {
        return repo.save(t);
    }

    public Tache get(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tâche non trouvée: " + id));
    }

    public List<Tache> list() {
        return repo.findAll();
    }

    public List<Tache> listActives() {
        return repo.findByActifTrue();
    }

    public Tache update(Long id, Tache t) {
        Tache ex = get(id);
        ex.setNom(t.getNom());
        ex.setDescription(t.getDescription());
        ex.setCronExpression(t.getCronExpression());
        ex.setOrdre(t.getOrdre());
        ex.setActif(t.getActif());
        ex.setGroupe(t.getGroupe());
        return repo.save(ex);
    }

    public void delete(Long id) {
        repo.delete(get(id));
    }
}