package fr.cyberdodo.cronduler.service;

import fr.cyberdodo.cronduler.entity.GroupeTache;
import fr.cyberdodo.cronduler.exception.ResourceNotFoundException;
import fr.cyberdodo.cronduler.repository.GroupeTacheRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class GroupeTacheService {
    private final GroupeTacheRepository repo;

    public GroupeTache create(GroupeTache g) {
        return repo.save(g);
    }

    public GroupeTache get(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Groupe non trouvé: " + id));
    }

    public List<GroupeTache> list() {
        return repo.findAll();
    }

    public GroupeTache update(Long id, GroupeTache g) {
        GroupeTache ex = get(id);
        ex.setNom(g.getNom());
        ex.setPriorite(g.getPriorite());
        ex.setConcurrent(g.getConcurrent());
        ex.setProduction(g.getProduction());
        return repo.save(ex);
    }

    public void delete(Long id) {
        repo.delete(get(id));
    }
}