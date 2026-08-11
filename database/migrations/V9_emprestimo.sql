-- =====================================================================
-- SIGLab | Migration V9 (v1.1)
-- Objetivo: criar a tabela EMPRESTIMO.
--
-- Esta tabela NÃO faz parte do MVP (v1.0), mas já foi modelada agora
-- para deixar o caminho pronto, conforme decidido na etapa de
-- modelagem lógica. Só rode esta migration quando for de fato
-- implementar o módulo de empréstimos.
--
-- Relacionamento: solicitacao (1) ------ (0..1) emprestimo
-- Por isso solicitacao_id é UNIQUE: uma solicitação gera, no
-- máximo, um empréstimo.
-- =====================================================================

CREATE TABLE emprestimo (
    id                          UUID                PRIMARY KEY DEFAULT gen_random_uuid(),
    solicitacao_id              UUID                NOT NULL,
    data_retirada               TIMESTAMPTZ         NOT NULL,
    data_prevista_devolucao     DATE                NOT NULL,
    data_devolucao              TIMESTAMPTZ,
    status                      status_emprestimo   NOT NULL DEFAULT 'ATIVO',
    created_at                  TIMESTAMPTZ         NOT NULL DEFAULT now(),
    updated_at                  TIMESTAMPTZ         NOT NULL DEFAULT now(),

    -- Garante que cada solicitação gere, no máximo, um empréstimo
    -- e já cria automaticamente um índice único para consultas
    -- "existe empréstimo para esta solicitação?".
    CONSTRAINT uq_emprestimo_solicitacao_id UNIQUE (solicitacao_id),

    CONSTRAINT fk_emprestimo_solicitacao
        FOREIGN KEY (solicitacao_id) REFERENCES solicitacao (id),

    -- Regra do dicionário de dados: prevista de devolução deve ser
    -- posterior à data de retirada.
    CONSTRAINT ck_emprestimo_data_prevista_maior_retirada
        CHECK (data_prevista_devolucao > data_retirada::date)
);

CREATE TRIGGER set_updated_at_emprestimo
    BEFORE UPDATE ON emprestimo
    FOR EACH ROW
    EXECUTE FUNCTION trigger_set_updated_at();

COMMENT ON TABLE emprestimo IS 'Registro efetivo de retirada/devolução de um equipamento (v1.1).';
COMMENT ON COLUMN emprestimo.solicitacao_id IS 'Solicitação de origem. Apenas solicitações APROVADAS devem gerar empréstimo (regra de serviço).';
COMMENT ON COLUMN emprestimo.status IS 'ATIVO -> DEVOLVIDO | ATRASADO | CANCELADO.';

-- Nota: a regra "apenas solicitações aprovadas podem gerar
-- empréstimo" não é feita por CHECK/FK, pois dependeria de consultar
-- outra tabela (status da solicitação) — isso é responsabilidade da
-- camada de serviço no Spring Boot.