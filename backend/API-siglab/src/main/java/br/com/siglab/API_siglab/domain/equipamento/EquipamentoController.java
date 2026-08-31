package br.com.siglab.API_siglab.domain.equipamento;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/equipamentos")
public class EquipamentoController {

    private final EquipamentoService equipamentoService;

    public EquipamentoController(EquipamentoService equipamentoService) {
        this.equipamentoService = equipamentoService;
    }

    @PostMapping
    public ResponseEntity<Equipamento> createEquipamento(@RequestBody Equipamento equipamento) {
        Equipamento newEquipamento = equipamentoService.createEquipamento(equipamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(newEquipamento);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipamento> getEquipamentoById(@PathVariable UUID id) {
        Equipamento equipamento = equipamentoService.getEquipamentoById(id);
        return ResponseEntity.ok(equipamento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipamento> updateEquipamento(@PathVariable UUID id, @RequestBody Equipamento equipamento) {
        Equipamento updatedEquipamento = equipamentoService.updateEquipamento(id, equipamento);
        return ResponseEntity.ok(updatedEquipamento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipamento(@PathVariable UUID id) {
        equipamentoService.deleteEquipamento(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Equipamento>> getAllEquipamentos() {
        List<Equipamento> equipamentos = equipamentoService.getAllEquipamentos();
        return ResponseEntity.ok(equipamentos);
    }
}
