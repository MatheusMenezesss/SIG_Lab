-- =====================================================================
-- SIGLab | Migration V1
-- Objetivo: habilitar extensões do PostgreSQL e criar funções utilitárias
--           que serão reaproveitadas pelas próximas migrations.
-- =====================================================================

-- ---------------------------------------------------------------------
-- pgcrypto: necessária para usarmos gen_random_uuid() como DEFAULT
-- da coluna "id" em todas as tabelas (chave primária como UUID).
-- ---------------------------------------------------------------------
CREATE EXTENSION IF NOT EXISTS pgcrypto;

-- ---------------------------------------------------------------------
-- Função utilitária: trigger_set_updated_at
--
-- Toda tabela do SIGLab possui a coluna updated_at. Em vez de deixar
-- a aplicação (Spring Boot) responsável por atualizar esse campo em
-- todo UPDATE, deixamos o próprio banco garantir essa regra através
-- de uma trigger BEFORE UPDATE. Isso evita inconsistência caso algum
-- UPDATE seja feito fora da aplicação (ex: script manual, migração
-- de dados, etc).
-- ---------------------------------------------------------------------
CREATE OR REPLACE FUNCTION trigger_set_updated_at()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = now();
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

COMMENT ON FUNCTION trigger_set_updated_at() IS
    'Atualiza automaticamente a coluna updated_at antes de qualquer UPDATE.';