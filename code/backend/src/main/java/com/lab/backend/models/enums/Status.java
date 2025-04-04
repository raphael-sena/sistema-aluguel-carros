package com.lab.backend.models.enums;

import lombok.Getter;

@Getter
public enum Status {
    PENDENTE(1, "Pendente"),
    APROVADO(2, "Aprovado"),
    REJEITADO(3, "Rejeitado"),
    EM_ANDAMENTO(4, "Em Andamento"),
    FINALIZADO(5, "Finalizado");

    private final int id;
    private final String descricao;

    Status(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public static Status fromId(int id) {
        for (Status status : values()) {
            if (status.getId() == id) {
                return status;
            }
        }
        throw new IllegalArgumentException("Status não encontrado para o ID: " + id);
    }
}
