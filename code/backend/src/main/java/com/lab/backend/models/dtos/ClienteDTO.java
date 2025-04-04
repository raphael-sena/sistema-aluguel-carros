package com.lab.backend.models.dtos;

import com.lab.backend.models.enums.TipoCliente;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public record ClienteDTO(
        Long id,

        @NotEmpty
        String nome,

        @Email
        String email,

        @NotEmpty
        @Size(min = 6)
        String senha,

        @NotEmpty
        @Size(min = 3)
        String endereco,

        @NotEmpty
        @Size(min = 3)
        String entidadeEmpregadora,

        @NotNull
        TipoCliente tipoCliente
) {}
