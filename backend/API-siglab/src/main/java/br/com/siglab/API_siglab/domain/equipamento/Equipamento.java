package br.com.siglab.API_siglab.domain.equipamento;


import java.time.LocalDateTime;

// id, nome, descricao, categoria, estoqueTotal, estoqueDisponivel, ativo.

/*
id              UUID          PRIMARY KEY DEFAULT gen_random_uuid(),
    categoria_id    UUID          NOT NULL,
    nome            VARCHAR(150)  NOT NULL,
    descricao       TEXT,
    patrimonio      VARCHAR(50),
    estoque_total   INTEGER       NOT NULL,
    ativo           BOOLEAN       NOT NULL DEFAULT TRUE,
    created_at      TIMESTAMPTZ   NOT NULL DEFAULT now(),
    updated_at      TIMESTAMPTZ   NOT NULL DEFAULT now(),
*/

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "equipamento")
public class Equipamento {
    @Id
    @GeneratedValue
    private UUID id;

    private String nome;

    private String descricao;

    private String patrimonio;

    private Integer estoqueTotal;

    private Boolean ativo;

    private LocalDateTime create_at;

    private LocalDateTime update_at;

}
