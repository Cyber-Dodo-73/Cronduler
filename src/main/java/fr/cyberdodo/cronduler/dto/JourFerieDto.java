package fr.cyberdodo.cronduler.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JourFerieDto {
    private Long id;
    @NotNull
    private LocalDate date;
    @NotBlank
    private String libelle;
}