-- =====================================================================
-- SIGLab | Migration V3
-- Objetivo: criar a tabela CATEGORIA.
--
-- Decisão de modelagem: categoria é uma entidade própria (e não um
-- VARCHAR direto em equipamento) para permitir que o administrador
-- cadastre novas categorias (ex: Drone, Osciloscópio) sem precisar
-- de alteração de código.
-- =====================================================================

CREATE TABLE categoria (
    id          UUID          PRIMARY KEY DEFAULT gen_random_uuid(),
    nome        VARCHAR(100)  NOT NULL,
    created_at  TIMESTAMPTZ   NOT NULL DEFAULT now(),
    updated_at  TIMESTAMPTZ   NOT NULL DEFAULT now(),

    CONSTRAINT uq_categoria_nome UNIQUE (nome)
);

-- Mantém updated_at sempre em dia
CREATE TRIGGER set_updated_at_categoria
    BEFORE UPDATE ON categoria
    FOR EACH ROW
    EXECUTE FUNCTION trigger_set_updated_at();

COMMENT ON TABLE categoria IS 'Categorias de equipamentos (ex: Notebook, Osciloscópio, Arduino).';
COMMENT ON COLUMN categoria.nome IS 'Nome da categoria. Não pode repetir.';