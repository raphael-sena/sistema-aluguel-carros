package com.lab.backend.services;

import com.lab.backend.models.Agente;
import com.lab.backend.models.AvaliacaoAluguel;
import com.lab.backend.models.PedidoAluguel;
import com.lab.backend.models.dtos.AluguelDTO;
import com.lab.backend.models.dtos.PedidoAluguelRequestDTO;
import com.lab.backend.models.enums.Status;
import com.lab.backend.repositories.PedidoAluguelRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AvaliacaoService {

    private final AgenteService agenteService;
    private final PedidoAluguelService pedidoAluguelService;
    private final AluguelService aluguelService;
    private final PedidoAluguelRepository pedidoAluguelRepository;


    public AvaliacaoService(AgenteService agenteService, PedidoAluguelService pedidoAluguelService, AluguelService aluguelService, PedidoAluguelRepository pedidoAluguelRepository) {
        this.agenteService = agenteService;
        this.pedidoAluguelService = pedidoAluguelService;
        this.aluguelService = aluguelService;
        this.pedidoAluguelRepository = pedidoAluguelRepository;
    }

    @Transactional
    public AluguelDTO avaliarAluguel(Long pedidoId, Long agenteId, boolean aprovacao) {
        PedidoAluguel pedidoAluguel = pedidoAluguelService.findById(pedidoId);
        Agente agente = agenteService.findById(agenteId);

        AvaliacaoAluguel avaliacaoAluguel = new AvaliacaoAluguel();
        avaliacaoAluguel.setAprovado(aprovacao);
        avaliacaoAluguel.setAgente(agente);
        avaliacaoAluguel.setPedidoAluguel(pedidoAluguel);

        pedidoAluguel.setAvaliacaoAluguel(avaliacaoAluguel);

        if (aprovacao) {
            avaliacaoAluguel.setAluguel(aluguelService.gerarAluguel(pedidoAluguel));
            pedidoAluguel.setStatus(Status.APROVADO);
        } else {
            avaliacaoAluguel.setAluguel(null);
            pedidoAluguel.setStatus(Status.REPROVADO);
        }

        pedidoAluguelRepository.save(pedidoAluguel);

        pedidoAluguelService.update(pedidoId, new PedidoAluguelRequestDTO(
                pedidoAluguel.getCliente().getId(),
                pedidoAluguel.getVeiculo().getId(),
                pedidoAluguel.getDataInicio(),
                pedidoAluguel.getDataFim(),
                pedidoAluguel.getStatus()
        ));

        return aluguelService.fromEntityToDTO(avaliacaoAluguel.getAluguel());
    }
}
