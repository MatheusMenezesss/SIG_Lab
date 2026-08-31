package br.com.siglab.API_siglab.domain.historico;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoricoService {

	private final HistoricoRepository historicoRepository;

	@Autowired
	public HistoricoService(HistoricoRepository historicoRepository) {
		this.historicoRepository = historicoRepository;
	}

	public Historico createHistorico(Historico historico) {
		if (historico.getSolicitacaoId() == null) {
			throw new IllegalArgumentException("Solicitação é obrigatória.");
		}
		if (historico.getUsuarioId() == null) {
			throw new IllegalArgumentException("Usuário é obrigatório.");
		}
		if (historico.getTipoEvento() == null) {
			throw new IllegalArgumentException("Tipo de evento é obrigatório.");
		}

		return historicoRepository.save(historico);
	}

	public Historico getHistoricoById(UUID id) {
		Optional<Historico> historicoOptional = historicoRepository.findById(id);
		return historicoOptional.orElseThrow(() ->
			new IllegalArgumentException("Histórico não encontrado para o ID: " + id));
	}

	public List<Historico> getAllHistoricos() {
		return historicoRepository.findAll();
	}

	public List<Historico> getHistoricosBySolicitacaoId(UUID solicitacaoId) {
		return historicoRepository.findBySolicitacaoId(solicitacaoId);
	}

	public List<Historico> getHistoricosByUsuarioId(UUID usuarioId) {
		return historicoRepository.findByUsuarioId(usuarioId);
	}
}