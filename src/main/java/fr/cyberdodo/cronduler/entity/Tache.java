package fr.cyberdodo.cronduler.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "taches")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tache {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String description;
    private String cronExpression;
    private Integer ordre;
    private Boolean actif = true;

    @ManyToOne(optional = false)
    @JoinColumn(name = "groupe_id")
    private GroupeTache groupe;
}