package com.lab.backend.models.dtos;

public record VeiculoDTO(
        Long id,
        Integer ano,
        String marca,
        String modelo,
        String placa,
        Long clienteId
) {}
