package com.lab.backend.services;

import com.lab.backend.models.Agente;
import com.lab.backend.models.dtos.AgenteDTO;
import com.lab.backend.repositories.AgenteRepository;
import com.lab.backend.services.exceptions.DatabaseException;
import com.lab.backend.services.exceptions.ObjectNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import java.util.List;

@Service
public class AgenteService {

    private final AgenteRepository agenteRepository;

    public AgenteService(AgenteRepository agenteRepository) {
        this.agenteRepository = agenteRepository;
    }

    public Agente findById(Long id) {
        return agenteRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("Agente não encontrado"));
    }

    public List<AgenteDTO> findAll() {
        List<Agente> agentes = agenteRepository.findAll();
        return agentes.stream()
                .map(this::fromEntityToDTO)
                .toList();
    }

    @Transactional
    public AgenteDTO save(AgenteDTO obj) {
        Agente agente = new Agente();
        agente = fromDTOToEntity(obj, agente);
        agente.setId(agente.getId());
        return fromEntityToDTO(agente);
    }

    @Transactional
    public AgenteDTO update(Long id, AgenteDTO agenteDTO) {
        Agente agente = findById(id);
        agente = fromDTOToEntity(agenteDTO, agente);
        return fromEntityToDTO(agente);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if(!agenteRepository.existsById(id)) {
            throw new ObjectNotFoundException("Agente não encontrado");
        }

        try {
            agenteRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial. Há entidades relacionadas a este cliente.");
        }
    }

    private Agente fromDTOToEntity(AgenteDTO obj, Agente agente) {
        agente.setNome(obj.nome());
        agente.setEmail(obj.email());
        agente.setSenha(obj.senha());
        agente = agenteRepository.save(agente);
        return agente;
    }

    public AgenteDTO fromEntityToDTO(Agente agente) {
        return new AgenteDTO(
                agente.getId(),
                agente.getNome(),
                agente.getEmail(),
                agente.getSenha()
        );
    }
}
