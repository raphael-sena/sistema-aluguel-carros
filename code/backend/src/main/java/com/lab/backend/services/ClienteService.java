package com.lab.backend.services;

import com.lab.backend.models.Cliente;
import com.lab.backend.models.dtos.ClienteDTO;
import com.lab.backend.repositories.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public ClienteDTO findById(Long id) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return fromEntityToDTO(cliente);
    }

    public List<ClienteDTO> findAll() {
        return clienteRepository.findAll().stream()
                .map(this::fromEntityToDTO)
                .toList();
    }

    private ClienteDTO fromEntityToDTO(Cliente cliente) {
        return new ClienteDTO(cliente.getId(), cliente.getNome(), cliente.getEmail(), cliente.getSenha(), cliente.getEndereco(), cliente.getEntidadeEmpregadora());
    }


}
