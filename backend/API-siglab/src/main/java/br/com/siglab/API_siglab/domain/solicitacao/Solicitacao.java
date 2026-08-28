package br.com.siglab.API_siglab.domain.solicitacao;

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

@Entity
@Table(name = "solicitacao")
public class Solicitacao {

    @Id
    @GeneratedValue
    private UUID id;

    @NotNull
    @Column(name = "usuarios_id", nullable = false)
    private UUID usuarioId;

    @NotNull
    @Column(name = "equipamento_id", nullable = false)
    private UUID equipamentoId;

    @Column(name = "administrador_id")
    private UUID administradorId;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusSolicitacao status;


    @NotBlank
    @Column(nullable = false)
    private String finalidade;

    @NotNull
    @Column(name = "data_solicitacao",nullable = false)
    private LocalDateTime dataSolicitacao;

    @Column(name = "data_resposta")
    private LocalDateTime dataResposta;

    @NotNull
    @Column(name = "motivo_resposta")
    private String motivoResposta;

    @NotNull
    @Column(name = "create_at", nullable = false)
    private LocalDateTime createdAt;

    @NotNull
    @Column(name = "update_at", nullable = false)
    private LocalDateTime updatedAt;
    public Solicitacao(){
        this.status = StatusSolicitacao.PENDENTE;
        this.dataSolicitacao = LocalDateTime.now();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        
    }

    public Solicitacao(UUID usuarioId, UUID equipamentoId, String finalidade) {
        this();
        this.usuarioId = usuarioId;
        this.equipamentoId = equipamentoId;
        this.finalidade = finalidade;
    }

    // Getters e Setters
    public UUID getId() {
        return id;
    }

    public UUID getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(UUID usuarioId) {
        this.usuarioId = usuarioId;
    }

    public UUID getEquipamentoId() {
        return equipamentoId;
    }

    public void setEquipamentoId(UUID equipamentoId) {
        this.equipamentoId = equipamentoId;
    }

    public UUID getAdministradorId() {
        return administradorId;
    }

    public void setAdministradorId(UUID administradorId) {
        this.administradorId = administradorId;
    }

    public StatusSolicitacao getStatus() {
        return status;
    }

    public void setStatus(StatusSolicitacao status) {
        this.status = status;
    }

    public String getFinalidade() {
        return finalidade;
    }

    public void setFinalidade(String finalidade) {
        this.finalidade = finalidade;
    }

    public LocalDateTime getDataSolicitacao() {
        return dataSolicitacao;
    }

    public LocalDateTime getDataResposta() {
        return dataResposta;
    }

    public void setDataResposta(LocalDateTime dataResposta) {
        this.dataResposta = dataResposta;
    }

    public String getMotivoResposta() {
        return motivoResposta;
    }

    public void setMotivoResposta(String motivoResposta) {
        this.motivoResposta = motivoResposta;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    
}
