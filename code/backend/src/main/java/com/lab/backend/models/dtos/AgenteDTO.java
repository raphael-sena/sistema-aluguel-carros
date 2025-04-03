package com.lab.backend.models.dtos;

public record AgenteDTO (
    Long id,
    String nome,
    String email,
    String senha
) {}
