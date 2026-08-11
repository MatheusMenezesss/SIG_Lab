-- =====================================================================
-- SIGLab | Migration V6
-- Objetivo: criar a tabela SOLICITACAO (tabela principal do sistema).
--
-- Ponto importante discutido: existem DUAS foreign keys para usuario:
--   - usuario_id       -> quem solicitou o equipamento
--   - administrador_id -> quem analisou a solicitação (perfil ADMIN)
-- Essa é uma solução comum para representar dois papéis diferentes
-- de uma mesma entidade dentro do mesmo relacionamento.
-- =====================================================================

CREATE TABLE solicitacao (
    id                  UUID                  PRIMARY KEY DEFAULT gen_random_uuid(),
    usuario_id          UUID                  NOT NULL,
    equipamento_id      UUID                  NOT NULL,
    administrador_id    UUID,
    status              status_solicitacao    NOT NULL DEFAULT 'PENDENTE',
    finalidade          TEXT                  NOT NULL,
    data_solicitacao    TIMESTAMPTZ           NOT NULL DEFAULT now(),
    data_resposta       TIMESTAMPTZ,
    motivo_resposta     TEXT,
    created_at          TIMESTAMPTZ           NOT NULL DEFAULT now(),
    updated_at          TIMESTAMPTZ           NOT NULL DEFAULT now(),

    CONSTRAINT fk_solicitacao_usuario
        FOREIGN KEY (usuario_id) REFERENCES usuario (id),

    CONSTRAINT fk_solicitacao_equipamento
        FOREIGN KEY (equipamento_id) REFERENCES equipamento (id),

    -- administrador_id é opcional: só é preenchido quando alguém
    -- analisa a solicitação (por isso NULL é permitido).
    CONSTRAINT fk_solicitacao_administrador
        FOREIGN KEY (administrador_id) REFERENCES usuario (id),

    -- Regra do dicionário de dados: "finalidade não pode ser vazia"
    CONSTRAINT ck_solicitacao_finalidade_nao_vazia
        CHECK (length(trim(finalidade)) > 0)
);

CREATE TRIGGER set_updated_at_solicitacao
    BEFORE UPDATE ON solicitacao
    FOR EACH ROW
    EXECUTE FUNCTION trigger_set_updated_at();

COMMENT ON TABLE solicitacao IS 'Solicitações de empréstimo de equipamento feitas pelos usuários.';
COMMENT ON COLUMN solicitacao.usuario_id IS 'Quem solicitou o equipamento.';
COMMENT ON COLUMN solicitacao.administrador_id IS 'Quem analisou a solicitação (perfil ADMIN). NULL enquanto PENDENTE.';
COMMENT ON COLUMN solicitacao.status IS 'PENDENTE -> APROVADA ou REJEITADA (não pode voltar a PENDENTE).';
COMMENT ON COLUMN solicitacao.finalidade IS 'Justificativa do solicitante para o empréstimo.';
COMMENT ON COLUMN solicitacao.data_resposta IS 'Preenchida somente após a análise do administrador.';

-- Nota: as regras "apenas ADMIN pode alterar o status" e "apenas
-- equipamentos ativos podem ser solicitados" são regras de negócio
-- aplicadas na camada de serviço (Spring Boot), não no banco.