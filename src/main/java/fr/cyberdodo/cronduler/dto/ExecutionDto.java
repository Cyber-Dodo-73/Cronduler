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
public class ExecutionDto {
    private Long id;
    private Long tacheId;
    private Instant dateLancement;
    private Instant dateFin;
    private String status;
}