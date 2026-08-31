package br.com.siglab.API_siglab.domain.historico;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "evento_historico")
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

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_evento", nullable = false)
    private TipoEventoHistorico tipoEvento;

    @Column(name = "descricao")
    private String descricao;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public Historico() {
        this.createdAt = LocalDateTime.now();
    }

    public Historico(UUID solicitacaoId, UUID usuarioId, TipoEventoHistorico tipoEvento, String descricao) {
        this();
        this.solicitacaoId = solicitacaoId;
        this.usuarioId = usuarioId;
        this.tipoEvento = tipoEvento;
        this.descricao = descricao;
    }

    public UUID getId() {
        return id;
    }

    public UUID getSolicitacaoId() {
        return solicitacaoId;
    }

    public void setSolicitacaoId(UUID solicitacaoId) {
        this.solicitacaoId = solicitacaoId;
    }

    public UUID getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(UUID usuarioId) {
        this.usuarioId = usuarioId;
    }

    public TipoEventoHistorico getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(TipoEventoHistorico tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

