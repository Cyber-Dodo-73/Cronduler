package fr.cyberdodo.cronduler.repository;

import fr.cyberdodo.cronduler.entity.LogExecution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogExecutionRepository extends JpaRepository<LogExecution, Long> { }