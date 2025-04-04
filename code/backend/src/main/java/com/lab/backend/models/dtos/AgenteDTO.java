package com.lab.backend.models.dtos;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record AgenteDTO (
    Long id,

    @NotEmpty
    String nome,

    @Email
    String email,

    @Size(min = 6)
    String senha
) {}
