package fr.cyberdodo.cronduler.repository;

import fr.cyberdodo.cronduler.entity.Tache;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TacheRepository extends JpaRepository<Tache, Long> {
    List<Tache> findByActifTrue();
}