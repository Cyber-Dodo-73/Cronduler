package fr.cyberdodo.cronduler.repository;

import fr.cyberdodo.cronduler.entity.Execution;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;

public interface ExecutionRepository extends JpaRepository<Execution, Long> {
    List<Execution> findByTacheIdAndDateLancementBetween(Long tacheId, Instant from, Instant to);
}