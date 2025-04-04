package com.lab.backend.services;

import com.lab.backend.models.Agente;
import com.lab.backend.models.AvaliacaoAluguel;
import com.lab.backend.models.PedidoAluguel;
import com.lab.backend.models.dtos.PedidoAluguelRequestDTO;
import com.lab.backend.models.dtos.PedidoAluguelResponseDTO;
import com.lab.backend.models.enums.Status;
import com.lab.backend.repositories.AvaliacaoAluguelRepository;
import com.lab.backend.repositories.PedidoAluguelRepository;
import com.lab.backend.services.exceptions.ObjectNotFoundException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class PedidoAluguelService {

    private final PedidoAluguelRepository pedidoAluguelRepository;
    private final ClienteService clienteService;
    private final VeiculoService veiculoService;
    private final AluguelService aluguelService;
    private final AgenteService agenteService;
    private final AvaliacaoAluguelRepository avaliacaoAluguelRepository;


    public PedidoAluguelService(PedidoAluguelRepository pedidoAluguelRepository, ClienteService clienteService, VeiculoService veiculoService, AluguelService aluguelService, AgenteService agenteService, AvaliacaoAluguelRepository avaliacaoAluguelRepository) {
        this.pedidoAluguelRepository = pedidoAluguelRepository;
        this.clienteService = clienteService;
        this.veiculoService = veiculoService;
        this.aluguelService = aluguelService;
        this.agenteService = agenteService;
        this.avaliacaoAluguelRepository = avaliacaoAluguelRepository;
    }

    public PedidoAluguel findById(Long id) {
        return pedidoAluguelRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("Pedido de aluguel não encontrado"));
    }

    public List<PedidoAluguelResponseDTO> findAll() {
        return pedidoAluguelRepository.findAll().stream()
                .map(this::fromEntityToResponseDTO)
                .toList();
    }

    @Transactional
    public PedidoAluguelResponseDTO save(PedidoAluguelRequestDTO pedidoAluguelRequestDTO) {
        PedidoAluguel pedidoAluguel = fromDTOToEntity(pedidoAluguelRequestDTO, new PedidoAluguel());
        pedidoAluguel.setStatus(Status.PENDENTE);
        pedidoAluguelRepository.save(pedidoAluguel);
        return fromEntityToResponseDTO(pedidoAluguel);
    }

    @Transactional
    public PedidoAluguelResponseDTO update(Long id, PedidoAluguelRequestDTO pedidoAluguelRequestDTO) {
        PedidoAluguel pedidoAluguel = findById(id);
        pedidoAluguelRepository.save(pedidoAluguel);
        return fromEntityToResponseDTO(fromDTOToEntity(pedidoAluguelRequestDTO, pedidoAluguel));
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        findById(id);
        pedidoAluguelRepository.deleteById(id);
    }

    private PedidoAluguel fromDTOToEntity(PedidoAluguelRequestDTO dto, PedidoAluguel entity) {
        entity.setCliente(clienteService.findById(dto.clienteId()));
        entity.setVeiculo(veiculoService.findById(dto.veiculoId()));

        if (!dto.dataFim().isAfter(dto.dataInicio())) {
            throw new RuntimeException("A data de fim deve ser posterior à data de início");
        }

        entity.setDataInicio(dto.dataInicio());
        entity.setDataFim(dto.dataFim());

        if (entity.getStatus() == null) {
            entity.setStatus(Status.PENDENTE);
        } else {
            entity.setStatus(entity.getStatus());
        }

        return entity;
    }

    public PedidoAluguelResponseDTO fromEntityToResponseDTO(PedidoAluguel pedidoAluguel) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return new PedidoAluguelResponseDTO(
                pedidoAluguel.getId(),
                pedidoAluguel.getCliente().getId(),
                pedidoAluguel.getVeiculo().getId(),
                pedidoAluguel.getDataInicio().format(formatter),
                pedidoAluguel.getDataFim().format(formatter),
                pedidoAluguel.getStatus().getDescricao()
        );
    }
}
