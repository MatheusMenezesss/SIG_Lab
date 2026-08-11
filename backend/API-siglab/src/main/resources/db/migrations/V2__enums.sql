-- =====================================================================
-- SIGLab | Migration V2
-- Objetivo: criar os tipos ENUM que serão usados pelas tabelas.
--
-- Usamos ENUM nativo do PostgreSQL (em vez de VARCHAR + CHECK) porque:
--   - o banco impede fisicamente a inserção de um valor inválido;
--   - fica autoexplicativo no \d da tabela;
--   - é o padrão que combina bem com @Enumerated do JPA/Hibernate.
-- =====================================================================

-- ---------------------------------------------------------------------
-- perfil_usuario
-- Usado em: usuario.perfil
-- ---------------------------------------------------------------------
CREATE TYPE perfil_usuario AS ENUM (
    'USER',
    'ADMIN'
);

-- ---------------------------------------------------------------------
-- status_solicitacao
-- Usado em: solicitacao.status
-- Regra de negócio: toda solicitação nasce PENDENTE e, uma vez
-- aprovada/rejeitada, não pode voltar para PENDENTE (regra aplicada
-- na camada de serviço, não no banco).
-- ---------------------------------------------------------------------
CREATE TYPE status_solicitacao AS ENUM (
    'PENDENTE',
    'APROVADA',
    'REJEITADA'
);

-- ---------------------------------------------------------------------
-- tipo_evento_historico
-- Usado em: evento_historico.tipo_evento
-- ---------------------------------------------------------------------
CREATE TYPE tipo_evento_historico AS ENUM (
    'SOLICITACAO_CRIADA',
    'SOLICITACAO_APROVADA',
    'SOLICITACAO_REJEITADA',
    'RETIRADA_REALIZADA',
    'DEVOLUCAO_REALIZADA',
    'OBSERVACAO'
);

-- ---------------------------------------------------------------------
-- status_emprestimo (v1.1)
-- Usado em: emprestimo.status
-- Já criado agora para não exigir ALTER TYPE quando a v1.1 chegar.
-- ---------------------------------------------------------------------
CREATE TYPE status_emprestimo AS ENUM (
    'ATIVO',
    'DEVOLVIDO',
    'ATRASADO',
    'CANCELADO'
);