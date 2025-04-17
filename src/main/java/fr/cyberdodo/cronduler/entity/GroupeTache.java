package fr.cyberdodo.cronduler.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tache_groupes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupeTache {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private Integer priorite;
    private Boolean concurrent;

    @ManyToOne(optional = false)
    @JoinColumn(name = "production_id")
    private Production production;

    @OneToMany(mappedBy = "groupe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tache> taches = new ArrayList<>();
}