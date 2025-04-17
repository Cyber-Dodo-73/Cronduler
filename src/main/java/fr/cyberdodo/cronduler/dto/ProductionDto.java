package fr.cyberdodo.cronduler.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductionDto {
    private Long id;
    @NotBlank
    private String nom;
    @NotNull
    private LocalTime debut;
    @NotNull
    private LocalTime fin;
}