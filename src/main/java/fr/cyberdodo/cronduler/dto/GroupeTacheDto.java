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
public class GroupeTacheDto {
    private Long id;
    @NotBlank
    private String nom;
    @NotNull
    private Integer priorite;
    @NotNull
    private Boolean concurrent;
    @NotNull
    private Long productionId;
}