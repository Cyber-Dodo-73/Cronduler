package fr.cyberdodo.cronduler.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LogExecutionDto {
    private Long id;
    private Long executionId;
    private Instant dateLog;
    private String niveau;
    private String message;
}