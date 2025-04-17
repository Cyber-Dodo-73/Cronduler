package fr.cyberdodo.cronduler.service;

import fr.cyberdodo.cronduler.entity.Execution;
import fr.cyberdodo.cronduler.entity.LogExecution;
import fr.cyberdodo.cronduler.entity.Tache;
import fr.cyberdodo.cronduler.repository.ExecutionRepository;
import fr.cyberdodo.cronduler.repository.LogExecutionRepository;
import fr.cyberdodo.cronduler.repository.TacheRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
@Transactional
public class TacheExecutionService {
    private final TacheRepository tacheRepo;
    private final ExecutionRepository execRepo;
    private final LogExecutionRepository logRepo;

    public void runTache(Long tacheId) {
        Tache t = tacheRepo.findById(tacheId)
                .orElseThrow(() -> new RuntimeException("Tâche introuvable: " + tacheId));

        Execution exec = new Execution();
        exec.setTache(t);
        exec.setDateLancement(Instant.now());
        exec.setStatus(Execution.StatusExecution.EN_COURS);
        exec = execRepo.save(exec);

        try {
            // Ici, placez votre logique métier de la tâche
            logRepo.save(new LogExecution(null, exec, Instant.now(), "INFO", "Début du traitement"));

            // Simuler traitement
            Thread.sleep(1000);

            exec.setStatus(Execution.StatusExecution.SUCCES);
            logRepo.save(new LogExecution(null, exec, Instant.now(), "INFO", "Fin du traitement"));

        } catch (Exception e) {
            exec.setStatus(Execution.StatusExecution.ECHEC);
            logRepo.save(new LogExecution(null, exec, Instant.now(), "ERROR", e.getMessage()));
        } finally {
            exec.setDateFin(Instant.now());
            execRepo.save(exec);
        }
    }
}