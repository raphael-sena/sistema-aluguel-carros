package com.lab.backend.services;

import com.lab.backend.models.Cliente;
import com.lab.backend.models.dtos.ClienteDTO;
import com.lab.backend.repositories.ClienteRepository;
import com.lab.backend.services.exceptions.ObjectNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente findById(Long id) {
        return clienteRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("Usuário não encontrado"));
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

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {

        if (!clienteRepository.existsById(id)) {
            throw new ObjectNotFoundException("Cliente não encontrado");
        }

        try {
            clienteRepository.deleteById(id);
        } catch (Exception e) {
            throw new ObjectNotFoundException("Falha de integridade referencial. Há entidades relacionadas a este cliente.");
        }
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
