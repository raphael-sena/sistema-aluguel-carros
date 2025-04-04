package com.lab.backend.controllers;

import com.lab.backend.models.dtos.AgenteDTO;
import com.lab.backend.services.AgenteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/agentes")
public class AgenteController {

    private final AgenteService agenteService;

    public AgenteController(AgenteService agenteService) {
        this.agenteService = agenteService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgenteDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(agenteService.fromEntityToDTO(agenteService.findById(id)));
    }

    @GetMapping
    public ResponseEntity<List<AgenteDTO>> findAll() {
        List<AgenteDTO> agentes = agenteService.findAll();
        return ResponseEntity.ok(agentes);
    }

    @PostMapping
    public ResponseEntity<AgenteDTO> save(@RequestBody @Valid AgenteDTO agenteDTO) {
        AgenteDTO agente = agenteService.save(agenteDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(agente.id())
                .toUri();
        return ResponseEntity.created(uri).body(agente);
    }

    @PostMapping("/login")
    public ResponseEntity<AgenteDTO> login(@RequestBody AgenteDTO loginDTO) {
        AgenteDTO agente = agenteService.login(loginDTO.email(), loginDTO.senha());
        return ResponseEntity.ok(agente);
    }


    @PutMapping("/{id}")
    public ResponseEntity<AgenteDTO> update(@PathVariable Long id, @RequestBody @Valid AgenteDTO agenteDTO) {
        AgenteDTO agente = agenteService.update(id, agenteDTO);
        return ResponseEntity.ok(agente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        agenteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
