# SIGLab — Migrations do Banco de Dados

Scripts organizados no padrão do **Flyway**, prontos para irem em
`src/main/resources/db/migration/` de um projeto Spring Boot.

## Passo a passo (ordem de execução)

| Ordem | Arquivo | O que faz |
|-------|---------|-----------|
| 1 | `V1__create_extensions.sql` | Habilita `pgcrypto` (UUIDs) e cria a função `trigger_set_updated_at()` |
| 2 | `V2__create_enums.sql` | Cria os tipos `perfil_usuario`, `status_solicitacao`, `tipo_evento_historico`, `status_emprestimo` |
| 3 | `V3__create_categoria.sql` | Cria `categoria` |
| 4 | `V4__create_usuario.sql` | Cria `usuario` |
| 5 | `V5__create_equipamento.sql` | Cria `equipamento` (depende de `categoria`) |
| 6 | `V6__create_solicitacao.sql` | Cria `solicitacao` (depende de `usuario` e `equipamento`) |
| 7 | `V7__create_evento_historico.sql` | Cria `evento_historico` (depende de `solicitacao` e `usuario`) |
| 8 | `V8__create_indexes.sql` | Cria os índices de apoio para as telas de listagem/filtro |
| 9 | `V9__create_emprestimo.sql` | **v1.1** — Cria `emprestimo` (depende de `solicitacao`). Só aplicar quando for implementar esse módulo. |

Essa ordem existe justamente para nunca darmos de cara com erro de
Foreign Key: nada referencia uma tabela que ainda não existe.

## Como rodar

**Opção 1 — via Spring Boot + Flyway (recomendado):**
Basta colocar os arquivos em `src/main/resources/db/migration/` e
subir a aplicação. O Flyway detecta e aplica automaticamente, na
ordem correta, registrando o histórico na tabela
`flyway_schema_history`.

**Opção 2 — manual, direto no psql:**
```bash
psql -U seu_usuario -d siglab -f V1__create_extensions.sql
psql -U seu_usuario -d siglab -f V2__create_enums.sql
psql -U seu_usuario -d siglab -f V3__create_categoria.sql
psql -U seu_usuario -d siglab -f V4__create_usuario.sql
psql -U seu_usuario -d siglab -f V5__create_equipamento.sql
psql -U seu_usuario -d siglab -f V6__create_solicitacao.sql
psql -U seu_usuario -d siglab -f V7__create_evento_historico.sql
psql -U seu_usuario -d siglab -f V8__create_indexes.sql
# V9 é opcional (v1.1)
psql -U seu_usuario -d siglab -f V9__create_emprestimo.sql
```

## Decisões aplicadas nos scripts

- **Chave primária:** `UUID` com `DEFAULT gen_random_uuid()` em todas as tabelas.
- **Datas:** `TIMESTAMPTZ` (com fuso horário) para `created_at`/`updated_at` e demais timestamps.
- **Nomenclatura:** tabelas e colunas em `snake_case`, no singular.
- **Enums:** `perfil`, `status` e `tipo_evento` usam `ENUM` nativo do PostgreSQL em vez de `VARCHAR`, evitando valores inválidos no banco.
- **`updated_at` automático:** cada tabela (exceto `evento_historico`, que é imutável) tem uma trigger `BEFORE UPDATE` que atualiza esse campo sozinha.
- **`estoque_disponivel` não existe:** é sempre calculado em consulta (`estoque_total` − quantidade emprestada), evitando dado duplicado.
- **Regras que dependem de consultar outra tabela** (ex: "só pode gerar empréstimo se a solicitação estiver APROVADA") ficam na camada de serviço (Spring Boot), não em `CHECK`/trigger do banco — isso mantém o schema simples e a regra de negócio centralizada no código.

## Próximo passo sugerido

Como conversado, depois do banco criado o próximo documento natural é
a **Especificação das Regras de Negócio por Entidade**, que vai guiar
a implementação da camada `Service` no Spring Boot.