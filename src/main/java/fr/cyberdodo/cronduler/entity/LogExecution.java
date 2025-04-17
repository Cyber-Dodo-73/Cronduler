package fr.cyberdodo.cronduler.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "log_executions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogExecution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "execution_id")
    private Execution execution;

    private Instant dateLog;
    private String niveau;   // INFO, WARN, ERROR
    private String message;
}