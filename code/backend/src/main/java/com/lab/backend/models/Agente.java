package com.lab.backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Entity
@EqualsAndHashCode(callSuper = false)
public class Agente extends Usuario {

    @OneToMany
    private List<AvaliacaoAluguel> avaliacoes;
}
