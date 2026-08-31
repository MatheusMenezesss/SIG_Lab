package br.com.siglab.API_siglab.domain.equipamento;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class EquipamentoService {

    private final EquipamentoRepository equipamentoRepository;

    @Autowired
    public EquipamentoService(EquipamentoRepository equipamentoRepository) {
        this.equipamentoRepository = equipamentoRepository;
    }

    public Equipamento createEquipamento(Equipamento equipamento) {
        if (equipamento.getCategoriaId() == null) {
            throw new IllegalArgumentException("Categoria é obrigatória.");
        }
        if (equipamento.getNome() == null || equipamento.getNome().isBlank()) {
            throw new IllegalArgumentException("Nome do equipamento é obrigatório.");
        }
        if (equipamento.getEstoqueTotal() == null || equipamento.getEstoqueTotal() <= 0) {
            throw new IllegalArgumentException("Estoque total deve ser maior que zero.");
        }

        equipamento.setUpdatedAt(LocalDateTime.now());
        return equipamentoRepository.save(equipamento);
    }

    public Equipamento getEquipamentoById(UUID id) {
        Optional<Equipamento> equipamentoOptional = equipamentoRepository.findById(id);
        return equipamentoOptional.orElseThrow(() ->
            new IllegalArgumentException("Equipamento não encontrado para o ID: " + id));
    }

    public List<Equipamento> getAllEquipamentos() {
        return equipamentoRepository.findAll();
    }

    public Equipamento updateEquipamento(UUID id, Equipamento updateEquipamento) {
        Equipamento equipamentoExistente = equipamentoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Equipamento não encontrado para o ID: " + id));

        if (updateEquipamento.getCategoriaId() != null) {
            equipamentoExistente.setCategoriaId(updateEquipamento.getCategoriaId());
        }
        if (updateEquipamento.getNome() != null && !updateEquipamento.getNome().isBlank()) {
            equipamentoExistente.setNome(updateEquipamento.getNome());
        }
        if (updateEquipamento.getDescricao() != null) {
            equipamentoExistente.setDescricao(updateEquipamento.getDescricao());
        }
        if (updateEquipamento.getPatrimonio() != null) {
            equipamentoExistente.setPatrimonio(updateEquipamento.getPatrimonio());
        }
        if (updateEquipamento.getEstoqueTotal() != null) {
            if (updateEquipamento.getEstoqueTotal() <= 0) {
                throw new IllegalArgumentException("Estoque total deve ser maior que zero.");
            }
            equipamentoExistente.setEstoqueTotal(updateEquipamento.getEstoqueTotal());
        }
        if (updateEquipamento.getAtivo() != null) {
            equipamentoExistente.setAtivo(updateEquipamento.getAtivo());
        }

        equipamentoExistente.setUpdatedAt(LocalDateTime.now());
        return equipamentoRepository.save(equipamentoExistente);
    }

    public void deleteEquipamento(UUID id) {
        try {
            equipamentoRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new IllegalArgumentException("Equipamento não encontrado para o ID: " + id);
        }
    }

}
