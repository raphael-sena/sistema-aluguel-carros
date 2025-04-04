package com.lab.backend.services;

import com.lab.backend.models.Cliente;
import com.lab.backend.models.dtos.ClienteDTO;
import com.lab.backend.repositories.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente findById(Long id) {
        return clienteRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Usuário não encontrado"));
    }

    public List<ClienteDTO> findAll() {
        return clienteRepository.findAll().stream()
                .map(this::fromEntityToDTO)
                .toList();
    }

    @Transactional
    public ClienteDTO save(ClienteDTO obj) {
        Cliente cliente = new Cliente();
        cliente = fromDTOToEntity(obj, cliente);
        cliente.setId(cliente.getId());
        return fromEntityToDTO(cliente);
    }

    public ClienteDTO update(Long id, ClienteDTO obj) {
        Cliente cliente = findById(id);
        cliente = fromDTOToEntity(obj, cliente);
        return fromEntityToDTO(cliente);
    }

    public void delete(Long id) {
        clienteRepository.deleteById(id);
    }

    private Cliente fromDTOToEntity(ClienteDTO obj, Cliente cliente) {
        cliente.setNome(obj.nome());
        cliente.setEmail(obj.email());
        cliente.setSenha(obj.senha());
        cliente.setEndereco(obj.endereco());
        cliente.setEntidadeEmpregadora(obj.entidadeEmpregadora());
        cliente.setTipoCliente(obj.tipoCliente());
        cliente = clienteRepository.save(cliente);
        return cliente;
    }

    public Optional<Cliente> findByEmail(String email) {
        return clienteRepository.findByEmail(email);
    }


    public ClienteDTO fromEntityToDTO(Cliente cliente) {
        return new ClienteDTO(cliente.getId(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getSenha(),
                cliente.getEndereco(),
                cliente.getEntidadeEmpregadora(),
                cliente.getTipoCliente()
        );
    }
}
