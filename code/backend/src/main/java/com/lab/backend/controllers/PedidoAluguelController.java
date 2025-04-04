package com.lab.backend.controllers;

import com.lab.backend.models.dtos.AluguelDTO;
import com.lab.backend.models.dtos.PedidoAluguelRequestDTO;
import com.lab.backend.models.dtos.PedidoAluguelResponseDTO;
import com.lab.backend.services.AvaliacaoService;
import com.lab.backend.services.PedidoAluguelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pedido-aluguel")
public class PedidoAluguelController {

    private final PedidoAluguelService pedidoAluguelService;
    private final AvaliacaoService avaliacaoService;

    public PedidoAluguelController(PedidoAluguelService pedidoAluguelService, AvaliacaoService avaliacaoService) {
        this.pedidoAluguelService = pedidoAluguelService;
        this.avaliacaoService = avaliacaoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoAluguelResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoAluguelService.fromEntityToResponseDTO(pedidoAluguelService.findById(id)));
    }

    @GetMapping
    public ResponseEntity<List<PedidoAluguelResponseDTO>> findAll() {
        return ResponseEntity.ok(pedidoAluguelService.findAll());
    }

    @PostMapping
    public ResponseEntity<PedidoAluguelResponseDTO> createPedidoAluguel(@RequestBody @Valid PedidoAluguelRequestDTO pedidoAluguelRequestDTO) {
        PedidoAluguelResponseDTO pedidoAluguel = pedidoAluguelService.save(pedidoAluguelRequestDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(pedidoAluguel.id())
                .toUri();
        return ResponseEntity.created(uri).body(pedidoAluguel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoAluguelResponseDTO> updatePedidoAluguel(@PathVariable Long id, @Valid @RequestBody PedidoAluguelRequestDTO pedidoAluguelRequestDTO) {
        PedidoAluguelResponseDTO pedidoAluguel = pedidoAluguelService.update(id, pedidoAluguelRequestDTO);
        return ResponseEntity.ok(pedidoAluguel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePedidoAluguel(@PathVariable Long id) {
        pedidoAluguelService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{pedidoId}/avaliacao/agente/{agenteId}")
    public ResponseEntity<AluguelDTO> avaliarPedidoAluguel(@PathVariable Long pedidoId, @PathVariable Long agenteId, @RequestParam boolean aprovacao) {
        AluguelDTO aluguelDTO = avaliacaoService.avaliarAluguel(pedidoId, agenteId, aprovacao);
        return ResponseEntity.ok(aluguelDTO);
    }
}
