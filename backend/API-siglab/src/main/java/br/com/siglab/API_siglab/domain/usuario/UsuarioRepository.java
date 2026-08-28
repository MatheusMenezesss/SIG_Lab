package br.com.siglab.API_siglab.domain.usuario;

//import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository extends org.springframework.data.jpa.repository.JpaRepository<Usuario, UUID> {
    Optional<Usuario> findByEmail(String email);
}