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
    private final GroupeTacheService groupeService;

    public Tache create(Tache t, Long groupeId) {
        // Charge le GroupeTache existant
        var groupe = groupeService.get(groupeId);
        t.setGroupe(groupe);
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

    public Tache update(Long id, Tache t, Long groupeId) {
        var existing = get(id);
        var groupe = groupeService.get(groupeId);
        existing.setNom(t.getNom());
        existing.setDescription(t.getDescription());
        existing.setCronExpression(t.getCronExpression());
        existing.setOrdre(t.getOrdre());
        existing.setActif(t.getActif());
        existing.setGroupe(groupe);
        return repo.save(existing);
    }

    public void delete(Long id) {
        repo.delete(get(id));
    }
}