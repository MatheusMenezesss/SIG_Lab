/* 
package br.com.siglab.API_siglab.domain.historico;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/*
id (PK)

solicitacao_id (FK)

usuario_id (FK)

tipo_evento

descricao

*/

/* 
@Entity
@Table(name = "historico")
public class Historico {
    @Id
    @GeneratedValue
    private UUID id;

    @NotNull
    @Column(name = "solicitacao_id", nullable = false)
    private UUID solicitacaoId;

    @NotNull
    @Column(name = "usuario_id", nullable = false)
    private UUID usuarioId;


}*/

