package com.lab.backend.controllers;
import com.lab.backend.models.dtos.VeiculoDTO;
import com.lab.backend.models.dtos.VeiculosClienteDTO;
import com.lab.backend.services.VeiculoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    private final VeiculoService veiculoService;

    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<VeiculoDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(veiculoService.fromEntityToDTO(veiculoService.findById(id)));
    }

    @GetMapping
    public ResponseEntity<List<VeiculoDTO>> findAll() {
        List<VeiculoDTO> veiculos = veiculoService.findAll();
        return ResponseEntity.ok(veiculos);
    }

    @GetMapping("/cliente/{id}")
    public ResponseEntity<VeiculosClienteDTO> findByClienteId(@PathVariable Long id) {
        VeiculosClienteDTO clienteVeiculos = veiculoService.findAllById(id);
        return ResponseEntity.ok(clienteVeiculos);
    }

    @PostMapping
    public ResponseEntity<VeiculoDTO> save(@RequestBody @Valid VeiculoDTO veiculoDTO) {
        VeiculoDTO veiculo = veiculoService.save(veiculoDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(veiculo.id())
                .toUri();
        return ResponseEntity.created(uri).body(veiculo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VeiculoDTO> update(@PathVariable Long id, @RequestBody @Valid VeiculoDTO veiculoDTO) {
        VeiculoDTO veiculo = veiculoService.update(id, veiculoDTO);
        return ResponseEntity.ok(veiculo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        veiculoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}