package br.com.siglab.API_siglab.domain.categoria;

import java.util.Optional;
import java.util.UUID;

public interface CategoriaRepository extends org.springframework.data.jpa.repository.JpaRepository<Categoria, UUID> {
    Optional<Categoria> findByNome(String nome);
}
