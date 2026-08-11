-- =====================================================================
-- SIGLab | Migration V8
-- Objetivo: criar índices para as colunas mais consultadas pela
-- aplicação, conforme discutido (telas de listagem/filtro).
--
-- Observação: colunas com UNIQUE (categoria.nome, usuario.email,
-- equipamento.patrimonio) já possuem índice criado automaticamente
-- pelo PostgreSQL, então não são repetidas aqui.
-- =====================================================================

-- Tela "minhas solicitações" (listar solicitações de um usuário)
CREATE INDEX idx_solicitacao_usuario_id
    ON solicitacao (usuario_id);

-- Tela "solicitações pendentes" / filtros por status
CREATE INDEX idx_solicitacao_status
    ON solicitacao (status);

-- Consultas de solicitações por equipamento (ex: histórico de uso)
CREATE INDEX idx_solicitacao_equipamento_id
    ON solicitacao (equipamento_id);

-- Tela "solicitações que eu analisei" (visão do administrador)
CREATE INDEX idx_solicitacao_administrador_id
    ON solicitacao (administrador_id);

-- Listagem de equipamentos filtrada/agrupada por categoria
CREATE INDEX idx_equipamento_categoria_id
    ON equipamento (categoria_id);

-- Linha do tempo de uma solicitação específica
CREATE INDEX idx_evento_historico_solicitacao_id
    ON evento_historico (solicitacao_id);

-- Histórico de ações realizadas por um usuário
CREATE INDEX idx_evento_historico_usuario_id
    ON evento_historico (usuario_id);