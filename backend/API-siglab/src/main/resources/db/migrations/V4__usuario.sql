-- =====================================================================
-- SIGLab | Migration V4
-- Objetivo: criar a tabela USUARIO.
--
-- Observações do dicionário de dados:
--   - email é único e usado para login;
--   - senha nunca deve ser armazenada em texto puro (hash na aplicação);
--   - perfil não vira tabela separada por ter apenas dois valores
--     possíveis (USER / ADMIN) -> resolvido com ENUM (ver V2).
-- =====================================================================

CREATE TABLE usuario (
    id          UUID              PRIMARY KEY DEFAULT gen_random_uuid(),
    nome        VARCHAR(150)      NOT NULL,
    email       VARCHAR(150)      NOT NULL,
    senha       VARCHAR(255)      NOT NULL,
    perfil      perfil_usuario    NOT NULL,
    ativo       BOOLEAN           NOT NULL DEFAULT TRUE,
    created_at  TIMESTAMPTZ       NOT NULL DEFAULT now(),
    updated_at  TIMESTAMPTZ       NOT NULL DEFAULT now(),

    CONSTRAINT uq_usuario_email UNIQUE (email)
);

CREATE TRIGGER set_updated_at_usuario
    BEFORE UPDATE ON usuario
    FOR EACH ROW
    EXECUTE FUNCTION trigger_set_updated_at();

COMMENT ON TABLE usuario IS 'Usuários do sistema (solicitantes e administradores).';
COMMENT ON COLUMN usuario.email IS 'Único. Utilizado para autenticação (login).';
COMMENT ON COLUMN usuario.senha IS 'Hash da senha. Nunca gravar em texto puro.';
COMMENT ON COLUMN usuario.perfil IS 'USER (solicitante) ou ADMIN (analisa solicitações).';
COMMENT ON COLUMN usuario.ativo IS 'Usuários inativos não podem autenticar.';

-- Observação: a UNIQUE(email) acima já cria automaticamente um índice
-- único no PostgreSQL, então não é necessário criar outro índice
-- manualmente para busca por e-mail (ver V8 para os demais índices).