package com.lab.backend.models.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public record AgenteDTO (
    Long id,

    @NotEmpty
    String nome,

    @Email
    String email,

    @Size(min = 6)
    String senha
) {}
