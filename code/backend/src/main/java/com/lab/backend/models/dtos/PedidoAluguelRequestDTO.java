package com.lab.backend.models.dtos;

import com.lab.backend.models.enums.Status;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record PedidoAluguelRequestDTO(

        @NotNull(message = "Cliente ID não pode ser nulo")
        Long clienteId,

        @NotNull(message = "Veículo ID não pode ser nulo")
        Long veiculoId,

        @NotEmpty(message = "Data de início não pode ser vazia")
        LocalDateTime dataInicio,

        @NotEmpty(message = "Data de fim não pode ser vazia")
        LocalDateTime dataFim,

        @NotNull(message = "Status não pode ser nulo")
        Status status)
{ }
