package fr.cyberdodo.cronduler.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TacheDto {
    private Long id;
    @NotBlank
    private String nom;
    private String description;
    @NotBlank
    private String cronExpression;
    @NotNull
    private Integer ordre;
    @NotNull
    private Boolean actif;
    @NotNull
    private Long groupeId;
}