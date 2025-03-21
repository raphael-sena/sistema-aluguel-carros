package com.lab.backend.services;

import com.lab.backend.models.Cliente;
import com.lab.backend.models.dtos.ClienteDTO;
import com.lab.backend.repositories.ClienteRepository;
import org.springframework.stereotype.Service;

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

    private ClienteDTO fromEntityToDTO(Cliente cliente) {
        return new ClienteDTO(cliente.getId(), cliente.getNome(), cliente.getEmail(), cliente.getSenha(), cliente.getEndereco(), cliente.getEntidadeEmpregadora());
    }
}
