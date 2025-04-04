package com.lab.backend.controllers;

import com.lab.backend.models.Aluguel;
import com.lab.backend.models.dtos.AluguelDTO;
import com.lab.backend.services.AluguelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alugueis")
public class AluguelController {

    private final AluguelService aluguelService;

    public AluguelController(AluguelService aluguelService) {
        this.aluguelService = aluguelService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AluguelDTO> getAluguelById(@PathVariable Long id) {
        Aluguel aluguel = aluguelService.findById(id);
        return ResponseEntity.ok(aluguelService.fromEntityToDTO(aluguel));
    }
}
