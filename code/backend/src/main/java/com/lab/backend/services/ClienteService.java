package com.lab.backend.services;

import com.lab.backend.models.Cliente;
import com.lab.backend.models.dtos.ClienteDTO;
import com.lab.backend.repositories.ClienteRepository;
import jakarta.transaction.Transactional;
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

    @Transactional
    public ClienteDTO save(ClienteDTO obj) {
        Cliente cliente = new Cliente();
        cliente.setNome(obj.getNome());
        cliente.setEmail(obj.getEmail());
        cliente.setSenha(obj.getSenha());
        cliente.setEndereco(obj.getEndereco());
        cliente.setEntidadeEmpregadora(obj.getEntidadeEmpregadora());
        cliente.setTipoCliente(obj.getTipoCliente());
        cliente = clienteRepository.save(cliente);
        return fromEntityToDTO(cliente);
    }

    public ClienteDTO update(Long id, ClienteDTO obj) {
        ClienteDTO cliente = findById(id);
        cliente.setNome(obj.getNome());
        cliente.setEmail(obj.getEmail());
        cliente.setSenha(obj.getSenha());
        cliente.setEndereco(obj.getEndereco());
        cliente.setEntidadeEmpregadora(obj.getEntidadeEmpregadora());
        cliente.setTipoCliente(obj.getTipoCliente());
        clienteRepository.save(fromDTOToEntity(cliente));
        return cliente;
    }

    public void delete(Long id) {
        clienteRepository.deleteById(id);
    }

    private Cliente fromDTOToEntity(ClienteDTO cliente) {
        return new Cliente(cliente.getId(), cliente.getNome(), cliente.getEmail(), cliente.getSenha(), cliente.getEndereco(), cliente.getEntidadeEmpregadora(), cliente.getTipoCliente());
    }

    private ClienteDTO fromEntityToDTO(Cliente cliente) {
        return new ClienteDTO(cliente.getId(), cliente.getNome(), cliente.getEmail(), cliente.getSenha(), cliente.getEndereco(), cliente.getEntidadeEmpregadora(), cliente.getTipoCliente());
    }
}
