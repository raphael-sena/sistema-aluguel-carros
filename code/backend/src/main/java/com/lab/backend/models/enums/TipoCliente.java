package com.lab.backend.models.enums;

import lombok.Getter;

@Getter
public enum TipoCliente {
    EMPRESA(0),
    BANCO(1),
    PESSOA(2);

    private final int tipo;

    TipoCliente(int tipo) {
        this.tipo = tipo;
    }

    public static TipoCliente toEnum(Integer tipo) {
        if (tipo == null) {
            return null;
        }

        for (TipoCliente x : TipoCliente.values()) {
            if (tipo.equals(x.getTipo())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Id inválido: " + tipo);
    }
}
