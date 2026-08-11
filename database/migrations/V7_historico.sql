-- =====================================================================
-- SIGLab | Migration V7
-- Objetivo: criar a tabela EVENTO_HISTORICO.
--
-- Decisão de modelagem: NÃO armazenamos "status anterior/status novo",
-- pois isso seria específico demais para o cenário atual. Em vez
-- disso, usamos um tipo_evento genérico (ENUM), que já contempla
-- futuros eventos como RETIRADA e DEVOLUÇÃO sem precisar remodelar
-- a tabela.
--
-- Importante: eventos são um registro de auditoria imutável.
-- Por isso esta tabela NÃO possui updated_at nem trigger de
-- atualização — "eventos não podem ser alterados ou removidos".
-- =====================================================================

CREATE TABLE evento_historico (
    id              UUID                     PRIMARY KEY DEFAULT gen_random_uuid(),
    solicitacao_id  UUID                     NOT NULL,
    usuario_id      UUID                     NOT NULL,
    tipo_evento     tipo_evento_historico    NOT NULL,
    descricao       TEXT,
    created_at      TIMESTAMPTZ              NOT NULL DEFAULT now(),

    CONSTRAINT fk_evento_historico_solicitacao
        FOREIGN KEY (solicitacao_id) REFERENCES solicitacao (id),

    CONSTRAINT fk_evento_historico_usuario
        FOREIGN KEY (usuario_id) REFERENCES usuario (id)
);

COMMENT ON TABLE evento_historico IS 'Linha do tempo (auditoria) de cada solicitação. Registros imutáveis.';
COMMENT ON COLUMN evento_historico.usuario_id IS 'Responsável pelo evento (quem criou, aprovou, retirou, etc).';
COMMENT ON COLUMN evento_historico.tipo_evento IS 'Tipo do evento ocorrido (ver ENUM tipo_evento_historico).';
COMMENT ON COLUMN evento_historico.descricao IS 'Observações adicionais livres sobre o evento.';