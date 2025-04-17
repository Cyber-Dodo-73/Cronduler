package fr.cyberdodo.cronduler.repository;

import fr.cyberdodo.cronduler.entity.Production;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductionRepository extends JpaRepository<Production, Long> { }