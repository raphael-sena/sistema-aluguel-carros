package com.lab.backend.services;

import com.lab.backend.models.Aluguel;
import com.lab.backend.models.PedidoAluguel;
import com.lab.backend.models.dtos.AluguelDTO;
import com.lab.backend.models.dtos.PedidoAluguelResponseDTO;
import com.lab.backend.repositories.AluguelRepository;
import com.lab.backend.services.exceptions.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;

@Service
public class AluguelService {

    private final AluguelRepository aluguelRepository;

    public AluguelService(AluguelRepository aluguelRepository) {
        this.aluguelRepository = aluguelRepository;
    }

    @Transactional
    public Aluguel gerarAluguel(PedidoAluguel pedidoAluguel) {
        Aluguel aluguel = new Aluguel();
        aluguel.setAvaliacaoAluguel(pedidoAluguel.getAvaliacaoAluguel());
        aluguel.setTaxa(calcularTaxa(pedidoAluguel));
        aluguelRepository.save(aluguel);
        return aluguel;
    }

    private double calcularTaxa(PedidoAluguel pedidoAluguel) {
        double valorBase = 100.0;
        int diasAluguel = (int) java.time.Duration.between(pedidoAluguel.getDataInicio(), pedidoAluguel.getDataFim()).toDaysPart();
        return valorBase * diasAluguel;
    }

    public Aluguel findById(Long id) {
        return aluguelRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Aluguel não encontrado"));
    }

    public AluguelDTO fromEntityToDTO(Aluguel aluguel) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return new AluguelDTO(
                new PedidoAluguelResponseDTO(
                        aluguel.getId(),
                        aluguel.getAvaliacaoAluguel().getPedidoAluguel().getCliente().getId(),
                        aluguel.getAvaliacaoAluguel().getPedidoAluguel().getVeiculo().getId(),
                        aluguel.getAvaliacaoAluguel().getPedidoAluguel().getDataInicio().format(formatter),
                        aluguel.getAvaliacaoAluguel().getPedidoAluguel().getDataFim().format(formatter),
                        aluguel.getAvaliacaoAluguel().getPedidoAluguel().getStatus().toString()
                ),
                aluguel.getTaxa()
        );
    }
}
