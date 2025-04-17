package fr.cyberdodo.cronduler.service;

import fr.cyberdodo.cronduler.entity.JourFerie;
import fr.cyberdodo.cronduler.exception.ResourceNotFoundException;
import fr.cyberdodo.cronduler.repository.JourFerieRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class JourFerieService {
    private final JourFerieRepository repo;

    public JourFerie create(JourFerie j) {
        return repo.save(j);
    }

    public JourFerie get(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Jour férié non trouvé: " + id));
    }

    public List<JourFerie> list() {
        return repo.findAll();
    }

    public JourFerie update(Long id, JourFerie j) {
        JourFerie ex = get(id);
        ex.setDate(j.getDate());
        ex.setLibelle(j.getLibelle());
        return repo.save(ex);
    }

    public void delete(Long id) {
        repo.delete(get(id));
    }
}