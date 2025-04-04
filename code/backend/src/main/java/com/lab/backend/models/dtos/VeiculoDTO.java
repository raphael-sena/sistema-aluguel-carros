package com.lab.backend.models.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record VeiculoDTO(
        Long id,

        @NotNull(message = "O ano não pode ser nulo")
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
