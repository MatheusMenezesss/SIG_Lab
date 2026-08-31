# Backend / API SIGLab

Este diretório concentra o backend do SIGLab, desenvolvido em Java com Spring Boot e organizado por **package by feature**.

## Estrutura esperada

```text
backend/
├── src/
├── pom.xml
├── Dockerfile
└── README.md
```

## Organização interna do código Java

> A documentação anterior citava `br/ufpe/siglab`, mas o pacote real do projeto é `br/com/siglab/API_siglab`.

```text
src/main/java/br/com/siglab/API_siglab/
├── config/
├── controller/
├── service/
├── repository/
├── domain/
├── dto/
├── mapper/
├── exception/
├── security/
├── validation/
└── util/
```

Dentro de `domain`, cada funcionalidade possui seu próprio módulo:

```text
domain/
├── usuario/
├── equipamento/
├── categoria/
├── solicitacao/
└── historico/
```

Exemplo do padrão esperado em cada feature:

```text
solicitacao/
├── Solicitacao.java
├── SolicitacaoRepository.java
├── SolicitacaoService.java
├── SolicitacaoController.java
├── SolicitacaoMapper.java
└── dto/
```

## Diagnóstico atual do módulo backend/API

Com base na documentação do projeto e no estado atual do código, o backend ainda não está finalizado. Hoje o módulo já tem a base do domínio e das migrações, mas ainda precisa de consolidação em três frentes:

1. **Domínio incompleto**: `Equipamento` e `Historico` já possuem modelo básico, mas o restante da feature ainda não foi implementado no mesmo nível de `Solicitacao`.
2. **Camada de aplicação incompleta**: `equipamento` e `historico` ainda não têm fluxo completo de persistência, serviço, controller, mapper e DTOs.
3. **Padrões transversais pendentes**: ainda faltam validações mais fortes, tratamento centralizado de erros, documentação da API e testes mais representativos.

Além disso, há uma pendência estrutural importante: a documentação fala em `categoria`, mas o pacote correspondente ainda não existe no código. Isso precisa ser fechado para o backend ficar coerente com o banco e com o domínio descrito no projeto.

## O que ainda falta para finalizar o backend/API

### 1. Completar o módulo `categoria`

O banco já prevê a entidade `categoria`, mas o código ainda não possui o pacote correspondente. Antes de considerar o backend fechado, é necessário criar essa feature com:

* `Categoria.java`
* `CategoriaRepository.java`
* `CategoriaService.java`
* `CategoriaController.java`
* `CategoriaMapper.java`
* DTOs de entrada e saída

### 2. Finalizar o módulo `equipamento`

O domínio já possui a entidade `Equipamento`, mas ainda falta o restante da feature:

* Repository com métodos de busca relevantes
* Service com regras de negócio do estoque e da ativação/desativação
* Controller REST com endpoints completos
* Mapper entre entidade e DTO
* DTOs para criação, atualização e resposta
* Validações para `categoriaId`, `estoqueTotal`, `nome` e `patrimonio`

### 3. Finalizar o módulo `historico`

O histórico já possui entidade e enum de evento, mas ainda falta o fluxo de uso:

* Repository
* Service para criação automática de eventos
* Controller para consulta e auditoria
* Mapper e DTOs
* Regras para gravação de eventos a partir da solicitação

### 4. Fortalecer `solicitacao`

O módulo de solicitação já existe, mas ainda está em um nível inicial. Para ficar pronto para uso real, ele precisa de:

* DTOs no lugar de exposição direta da entidade
* Mapper consistente
* Regras de transição de status mais claras
* Atualização automática de `updatedAt`
* Validação de entrada mais completa
* Integração com `historico`
* Tratamento correto de exceções e respostas HTTP

### 5. Padronizar a camada transversal

O backend ainda precisa de estrutura comum para suportar os módulos:

* Exception handler global
* Respostas padronizadas de erro
* Validações reutilizáveis
* Configuração de Swagger/OpenAPI
* Segurança/autenticação, caso o acesso ao sistema exija perfis distintos

### 6. Cobrir com testes

Hoje existe apenas o teste base de inicialização. Para considerar o módulo finalizado, é importante adicionar:

* Testes unitários de service
* Testes de controller
* Testes de repository com banco de apoio
* Testes de integração para os fluxos principais

### 7. Revisar documentação e contrato da API

O backend precisa ter a documentação alinhada ao que realmente existe no código:

* Endpoints REST documentados
* Contratos de request/response
* Regras de negócio descritas
* Dependências entre `usuario`, `categoria`, `equipamento`, `solicitacao` e `historico`

## Passo a passo para finalização do backend/API

### Etapa 1. Fechar o domínio base

1. Criar o pacote `categoria`.
2. Confirmar os campos finais de `categoria`, `equipamento`, `solicitacao` e `historico`.
3. Garantir que os nomes de tabela, colunas e enums estejam consistentes com as migrations.

### Etapa 2. Completar cada feature

1. Implementar `Repository`, `Service`, `Controller`, `Mapper` e `DTOs` para `categoria`.
2. Fazer o mesmo para `equipamento`.
3. Fazer o mesmo para `historico`.
4. Revisar `solicitacao` para sair do CRUD básico e virar fluxo de negócio.

### Etapa 3. Aplicar regras de negócio

1. Impedir estados inválidos em `solicitacao`.
2. Atualizar `updatedAt` automaticamente nas alterações.
3. Registrar eventos no `historico` quando a solicitação mudar de estado.
4. Validar estoque, equipamento ativo e integridade dos vínculos.

### Etapa 4. Padronizar a API

1. Trocar qualquer exposição direta de entidade por DTO.
2. Criar tratamento global de exceções.
3. Padronizar mensagens e status HTTP.
4. Publicar a documentação da API com Swagger/OpenAPI.

### Etapa 5. Cobrir com testes

1. Criar testes unitários para services.
2. Criar testes de controller.
3. Validar os fluxos de solicitação, aprovação, rejeição e histórico.
4. Garantir que o banco e as migrations sobem sem inconsistências.

### Etapa 6. Preparar para entrega

1. Revisar `application.properties` e perfis de ambiente.
2. Validar o empacotamento com Maven.
3. Garantir que o Dockerfile do backend funciona.
4. Conferir se a documentação do repositório reflete o código real.

## Critério prático de “backend finalizado”

O backend pode ser considerado pronto quando:

* todas as features do domínio principal existirem no código;
* cada feature tiver entidade, DTO, mapper, service, controller e repository;
* os fluxos de negócio estiverem validados por testes;
* a API estiver documentada;
* o schema do banco estiver alinhado ao código;
* o tratamento de erros estiver centralizado;
* e os endpoints principais do MVP funcionarem de ponta a ponta.