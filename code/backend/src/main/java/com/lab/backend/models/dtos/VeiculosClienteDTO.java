package com.lab.backend.models.dtos;

import java.util.List;

public record VeiculosClienteDTO(
        Long clienteId,
        String nomeCliente,
        List<VeiculoDTO> veiculos
) {
}
