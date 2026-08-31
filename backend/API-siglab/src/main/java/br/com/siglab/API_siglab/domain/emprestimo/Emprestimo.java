/*package br.com.siglab.API_siglab.domain.emprestimo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.UUID;

id                          UUID                PRIMARY KEY DEFAULT gen_random_uuid(),
    solicitacao_id              UUID                NOT NULL,
    data_retirada               TIMESTAMPTZ         NOT NULL,
    data_prevista_devolucao     DATE                NOT NULL,
    data_devolucao              TIMESTAMPTZ,
    status                      status_emprestimo   NOT NULL DEFAULT 'ATIVO',
    created_at                  TIMESTAMPTZ         NOT NULL DEFAULT now(),
    updated_at 

@Entity
@Table(name = "emprestimos")
public class Emprestimo {
    @GeneratedValue
    @Id
    private UUID id;

    @Column(name = "solicitacao_id", nullable = false)
    private UUID solicitacaoId;

    @Column(name = "data_retirada", nullable = false)
    private LocalDateTime dataRetirada;

    @Column(name = "data_prevista_devolucao", nullable = false)
    private LocalDateTime dataPrevistaDevolucao;

    @Column(name = "data_devolucao")
    private LocalDateTime dataDevolucao;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    //Constructor

    public Emprestimo() {
        this.status = EmprestimoStatus.ATIVO.name();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public LocalDateTime getDataRetirada() {
        return dataRetirada;
    }



}
*/