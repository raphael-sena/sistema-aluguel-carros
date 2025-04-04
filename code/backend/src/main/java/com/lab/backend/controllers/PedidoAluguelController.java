package com.lab.backend.controllers;

import com.lab.backend.models.dtos.PedidoAluguelRequestDTO;
import com.lab.backend.models.dtos.PedidoAluguelResponseDTO;
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

    public PedidoAluguelController(PedidoAluguelService pedidoAluguelService) {
        this.pedidoAluguelService = pedidoAluguelService;
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
}
