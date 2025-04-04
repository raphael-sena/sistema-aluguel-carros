package com.lab.backend.models.dtos;

public record PedidoAluguelResponseDTO(
        Long id,
        Long clienteId,
        Long veiculoId,
        String dataInicio,
        String dataFim,
        String status
) {
}
