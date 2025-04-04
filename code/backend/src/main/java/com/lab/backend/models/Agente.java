package com.lab.backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@EqualsAndHashCode(callSuper = false)
public class Agente extends Usuario {

    @OneToMany(mappedBy = "agente")
    private List<AvaliacaoAluguel> avaliacoes = new ArrayList<>();
}
