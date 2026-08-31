package br.com.siglab.API_siglab.domain.historico;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/historicos")
public class HistoricoController {

	private final HistoricoService historicoService;

	public HistoricoController(HistoricoService historicoService) {
		this.historicoService = historicoService;
	}

	@PostMapping
	public ResponseEntity<Historico> createHistorico(@RequestBody Historico historico) {
		Historico newHistorico = historicoService.createHistorico(historico);
		return ResponseEntity.status(HttpStatus.CREATED).body(newHistorico);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Historico> getHistoricoById(@PathVariable UUID id) {
		Historico historico = historicoService.getHistoricoById(id);
		return ResponseEntity.ok(historico);
	}

	@GetMapping
	public ResponseEntity<List<Historico>> getAllHistoricos() {
		return ResponseEntity.ok(historicoService.getAllHistoricos());
	}

	@GetMapping("/solicitacao/{solicitacaoId}")
	public ResponseEntity<List<Historico>> getHistoricosBySolicitacaoId(@PathVariable UUID solicitacaoId) {
		return ResponseEntity.ok(historicoService.getHistoricosBySolicitacaoId(solicitacaoId));
	}

	@GetMapping("/usuario/{usuarioId}")
	public ResponseEntity<List<Historico>> getHistoricosByUsuarioId(@PathVariable UUID usuarioId) {
		return ResponseEntity.ok(historicoService.getHistoricosByUsuarioId(usuarioId));
	}
}