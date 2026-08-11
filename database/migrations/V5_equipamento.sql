-- =====================================================================
-- SIGLab | Migration V5
-- Objetivo: criar a tabela EQUIPAMENTO.
--
-- Decisões de modelagem discutidas:
--   - NÃO existe coluna "estoque_disponivel": o disponível é sempre
--     calculado em tempo de consulta (estoque_total - quantidade
--     emprestada/reservada), evitando dado duplicado e desatualizado.
--   - "patrimonio" foi incluído pois laboratórios costumam
--     identificar cada unidade física por número de patrimônio,
--     mesmo quando existem vários equipamentos iguais.
-- =====================================================================

CREATE TABLE equipamento (
    id              UUID          PRIMARY KEY DEFAULT gen_random_uuid(),
    categoria_id    UUID          NOT NULL,
    nome            VARCHAR(150)  NOT NULL,
    descricao       TEXT,
    patrimonio      VARCHAR(50),
    estoque_total   INTEGER       NOT NULL,
    ativo           BOOLEAN       NOT NULL DEFAULT TRUE,
    created_at      TIMESTAMPTZ   NOT NULL DEFAULT now(),
    updated_at      TIMESTAMPTZ   NOT NULL DEFAULT now(),

    CONSTRAINT fk_equipamento_categoria
        FOREIGN KEY (categoria_id) REFERENCES categoria (id),

    CONSTRAINT uq_equipamento_patrimonio UNIQUE (patrimonio),

    -- Regra do dicionário de dados: estoque_total > 0
    CONSTRAINT ck_equipamento_estoque_total_positivo
        CHECK (estoque_total > 0)
);

CREATE TRIGGER set_updated_at_equipamento
    BEFORE UPDATE ON equipamento
    FOR EACH ROW
    EXECUTE FUNCTION trigger_set_updated_at();

COMMENT ON TABLE equipamento IS 'Equipamentos disponíveis para empréstimo no laboratório.';
COMMENT ON COLUMN equipamento.categoria_id IS 'Categoria do equipamento (FK -> categoria.id).';
COMMENT ON COLUMN equipamento.patrimonio IS 'Número patrimonial. Opcional no MVP, mas único quando informado.';
COMMENT ON COLUMN equipamento.estoque_total IS 'Quantidade total existente no laboratório (> 0).';
COMMENT ON COLUMN equipamento.ativo IS 'Equipamentos inativos não podem ser solicitados.';