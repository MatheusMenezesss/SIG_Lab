package br.com.siglab.API_siglab.domain.equipamento;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "equipamento")
public class Equipamento {
    @Id
    @GeneratedValue
    private UUID id;

    @NotNull
    @Column(name = "categoria_id", nullable = false)
    private UUID categoriaId;

    @NotBlank
    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "patrimonio")
    private String patrimonio;

    @NotNull
    @Column(name = "estoque_total", nullable = false)
    private Integer estoqueTotal;

    @NotNull
    @Column(name = "ativo", nullable = false)
    private Boolean ativo;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @NotNull
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public Equipamento() {
        this.ativo = true;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Equipamento(UUID categoriaId, String nome, String descricao, String patrimonio, Integer estoqueTotal) {
        this();
        this.categoriaId = categoriaId;
        this.nome = nome;
        this.descricao = descricao;
        this.patrimonio = patrimonio;
        this.estoqueTotal = estoqueTotal;
    }

    public UUID getId() {
        return id;
    }

    public UUID getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(UUID categoriaId) {
        this.categoriaId = categoriaId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPatrimonio() {
        return patrimonio;
    }

    public void setPatrimonio(String patrimonio) {
        this.patrimonio = patrimonio;
    }

    public Integer getEstoqueTotal() {
        return estoqueTotal;
    }

    public void setEstoqueTotal(Integer estoqueTotal) {
        this.estoqueTotal = estoqueTotal;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
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
