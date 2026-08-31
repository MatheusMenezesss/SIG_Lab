package br.com.siglab.API_siglab.domain.equipamento;

import java.util.List;
import java.util.UUID;

public interface EquipamentoRepository extends org.springframework.data.jpa.repository.JpaRepository<Equipamento, UUID> {
	List<Equipamento> findByCategoriaId(UUID categoriaId);
}
