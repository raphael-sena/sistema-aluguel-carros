package com.lab.backend.services;

import com.lab.backend.models.Veiculo;
import com.lab.backend.models.dtos.VeiculoDTO;
import com.lab.backend.models.dtos.VeiculosClienteDTO;
import com.lab.backend.repositories.VeiculoRepository;
import com.lab.backend.services.exceptions.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;
    private final ClienteService clienteService;

    public VeiculoService(VeiculoRepository veiculoRepository, ClienteService clienteService) {
        this.veiculoRepository = veiculoRepository;
        this.clienteService = clienteService;
    }

    public Veiculo findById(Long id) {
        return veiculoRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("Veículo não encontrado"));
    }

    public List<VeiculoDTO> findAll() {
        List<Veiculo> veiculos = veiculoRepository.findAll();
        return veiculos.stream()
                .map(this::fromEntityToDTO)
                .toList();
    }

    public VeiculosClienteDTO findAllById(Long id) {
        List<Veiculo> veiculos = veiculoRepository.findAllById(Collections.singleton(id));
        return new VeiculosClienteDTO(
                id,
                clienteService.findById(id).getNome(),
                veiculos.stream()
                        .map(this::fromEntityToDTO)
                        .toList()
        );
    }

    @Transactional
    public VeiculoDTO save(VeiculoDTO obj) {
        Veiculo veiculo = new Veiculo();
        veiculo = fromDTOToEntity(obj, veiculo);
        veiculo.setId(veiculo.getId());
        return fromEntityToDTO(veiculo);
    }

    @Transactional
    public VeiculoDTO update(Long id, VeiculoDTO veiculoDTO) {
        Veiculo veiculo = findById(id);
        veiculo = fromDTOToEntity(veiculoDTO, veiculo);
        return fromEntityToDTO(veiculo);
    }

    @Transactional
    public void delete(Long id) {
        findById(id);
        veiculoRepository.deleteById(id);
    }

    private Veiculo fromDTOToEntity(VeiculoDTO obj, Veiculo veiculo) {
        veiculo.setAno(obj.ano());
        veiculo.setMarca(obj.marca());
        veiculo.setModelo(obj.modelo());
        veiculo.setPlaca(obj.placa());
        veiculo.setDono(clienteService.findById(obj.clienteId()));
        veiculo = veiculoRepository.save(veiculo);
        return veiculo;
    }

    public VeiculoDTO fromEntityToDTO(Veiculo veiculo) {
        return new VeiculoDTO(
                veiculo.getId(),
                veiculo.getAno(),
                veiculo.getMarca(),
                veiculo.getModelo(),
                veiculo.getPlaca(),
                clienteService.findById(veiculo.getDono().getId()).getId()
        );
    }
}
