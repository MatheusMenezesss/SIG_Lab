package br.com.siglab.API_siglab.domain.historico;

import java.util.List;
import java.util.UUID;

public interface HistoricoRepository extends org.springframework.data.jpa.repository.JpaRepository<Historico, UUID> {
	List<Historico> findBySolicitacaoId(UUID solicitacaoId);
	List<Historico> findByUsuarioId(UUID usuarioId);
}