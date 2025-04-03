package com.lab.backend.models.dtos;

import com.lab.backend.models.enums.TipoCliente;

public record ClienteDTO(
        Long id,
        String nome,
        String email,
        String senha,
        String endereco,
        String entidadeEmpregadora,
        TipoCliente tipoCliente
) {}
