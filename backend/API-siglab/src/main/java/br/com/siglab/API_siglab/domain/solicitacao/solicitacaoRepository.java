package br.com.siglab.API_siglab.domain.solicitacao;

import java.util.List;
import java.util.UUID;

public interface SolicitacaoRepository extends org.springframework.data.jpa.repository.JpaRepository<Solicitacao, UUID> {
    List<Solicitacao> findByUsuarioId(UUID usuarioId);
    
}
