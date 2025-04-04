package com.lab.backend.models.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public record VeiculoDTO(
        Long id,

        @NotEmpty
        @Size(min = 4, max = 4, message = "O ano deve ter 4 dígitos")
        Integer ano,

        @NotEmpty(message = "A marca não pode ser vazia")
        String marca,

        @NotEmpty(message = "O modelo não pode ser vazio")
        String modelo,

        @NotEmpty(message = "A placa não pode ser vazia")
        String placa,

        @NotNull(message = "O valor não pode ser nulo")
        Long clienteId
) {}
