# SIGLab - Sistema Integrado para Gestão Laboratorial

## Sobre o Projeto

O **SIGLab** é uma plataforma web desenvolvida para gerenciar o processo de solicitação e empréstimo de equipamentos laboratoriais.

O projeto surgiu da necessidade de substituir o processo atual, baseado em comunicação informal e registros manuais, por um sistema centralizado, rastreável e de fácil utilização.

Além de atender uma necessidade real do laboratório, o SIGLab também possui um objetivo educacional: servir como um projeto completo para estudo de Engenharia de Software, Arquitetura de Sistemas, Banco de Dados, Desenvolvimento Backend, Frontend e DevOps.

O desenvolvimento será realizado de forma incremental, seguindo a filosofia de **MVP (Minimum Viable Product)**, permitindo validar funcionalidades com usuários reais antes da implementação de recursos mais complexos.

---

# Objetivos do Projeto

## Objetivo funcional

Construir uma plataforma que permita:

* Cadastro de equipamentos;
* Consulta de equipamentos disponíveis;
* Solicitação de empréstimos;
* Análise das solicitações por administradores;
* Aprovação ou rejeição de solicitações;
* Registro histórico das operações realizadas.

## Objetivo técnico

O projeto também possui como objetivo proporcionar aprendizado prático em:

* Engenharia de Software
* Modelagem de Domínio
* Banco de Dados Relacional
* Desenvolvimento Backend com Java
* Desenvolvimento Frontend com React
* Arquitetura de APIs REST
* Docker
* Versionamento de banco com Flyway
* Boas práticas de desenvolvimento

---

# Filosofia de Desenvolvimento

O SIGLab será desenvolvido seguindo uma abordagem incremental.

A prioridade não é construir um sistema completo desde o início, mas sim desenvolver um **produto funcional**, colocá-lo em produção, coletar métricas de utilização e evoluir continuamente a partir das necessidades observadas.

Essa abordagem reduz a complexidade inicial do projeto e aproxima o desenvolvimento da realidade encontrada em produtos de software.

---

# Arquitetura Geral

O projeto será dividido em módulos independentes.

```text
siglab/

├── backend/
├── frontend/
├── database/
├── docs/
├── infra/
├── .github/
├── docker-compose.yml
└── README.md
```

Cada diretório possui uma responsabilidade específica, permitindo organização, escalabilidade e facilidade de manutenção.

---

# Arquitetura do Backend

O backend será desenvolvido utilizando **Java** e **Spring Boot**.

A organização seguirá o conceito de **Package by Feature**, agrupando os componentes por funcionalidade do domínio em vez de separá-los apenas por camada técnica.

Exemplo:

```text
backend/

src/main/java/br/ufpe/siglab/

config/

security/

common/

usuario/

categoria/

equipamento/

solicitacao/

historico/
```

Cada módulo poderá conter seus próprios:

* Controller
* Service
* Repository
* DTO
* Mapper
* Domain
* Validation

Essa organização reduz o acoplamento e facilita a evolução do sistema.

---

# Arquitetura do Frontend

O frontend será desenvolvido utilizando **React**.

A estrutura inicial prevista é:

```text
frontend/

src/

components/

pages/

contexts/

hooks/

services/

routes/

assets/

styles/

utils/
```

O frontend consumirá exclusivamente a API REST disponibilizada pelo backend.

---

# Arquitetura do Banco de Dados

O banco de dados será desenvolvido utilizando **PostgreSQL**.

O versionamento do esquema será realizado através do **Flyway**, permitindo evolução controlada do banco de dados.

Estrutura prevista:

```text
database/

diagram/

dictionary/

migrations/

seed/
```

---

# Modelagem do Domínio

O domínio do sistema foi modelado priorizando simplicidade e aderência ao processo de negócio do laboratório.

A entidade central do sistema é:

**Solicitação**

Todo o fluxo de negócio é construído em torno do ciclo de vida de uma solicitação.

As entidades inicialmente previstas são:

* Usuário
* Categoria
* Equipamento
* Solicitação
* Evento Histórico

Na versão 1.1 será adicionada a entidade:

* Empréstimo

---

# Fluxo Principal

```text
Usuário

↓

Pesquisa equipamentos

↓

Seleciona equipamento

↓

Sistema verifica disponibilidade

↓

Cria solicitação

↓

Administrador analisa

↓

Aprova ou rejeita

↓

Sistema registra evento histórico

↓

Sistema informa resultado ao usuário
```

---

# Decisões Arquiteturais

Durante o desenvolvimento algumas decisões serão seguidas para manter consistência no projeto.

## Backend

* Java 21 (LTS)
* Spring Boot 3
* Maven
* Spring Data JPA
* Spring Security
* Flyway
* PostgreSQL

---

## Frontend

* React
* React Router
* Axios
* Context API

---

## Banco de Dados

* PostgreSQL
* UUID como chave primária
* TIMESTAMPTZ para datas
* Snake Case para tabelas e colunas
* Migrations utilizando Flyway

---

## API

A comunicação entre frontend e backend seguirá o padrão REST.

As respostas da API serão padronizadas e documentadas utilizando OpenAPI (Swagger).

---

## Git

O projeto utilizará um fluxo simplificado baseado em GitFlow.

Branches principais:

```text
main

develop

feature/*
```

Cada funcionalidade será desenvolvida em uma branch específica.

---

# Organização da Documentação

Toda a documentação do projeto ficará centralizada no diretório **docs**.

Estrutura prevista:

```text
docs/

01-visao-geral/

02-requisitos/

03-modelagem/

04-banco/

05-backend/

06-frontend/

07-api/

08-deploy/
```

A documentação será mantida juntamente com a evolução do código.

---

# Roadmap

## MVP (v1.0)

* Modelagem do domínio
* Modelagem do banco
* Cadastro de usuários
* Cadastro de categorias
* Cadastro de equipamentos
* Solicitação de equipamentos
* Aprovação/Rejeição
* Histórico de eventos
* API REST
* Interface Web

---

## Versão 1.1

* Empréstimos
* Controle de devoluções
* Controle automático de estoque
* Notificações persistentes
* Dashboard administrativo

---

## Futuras Evoluções

* QR Code
* RFID
* Assinatura digital
* Controle de manutenção
* Reserva antecipada
* Relatórios
* Dashboard com métricas
* Integração com sistemas institucionais

---

# Tecnologias

| Camada                 | Tecnologia            |
| ---------------------- | --------------------- |
| Backend                | Java 21 + Spring Boot |
| Frontend               | React                 |
| Banco de Dados         | PostgreSQL            |
| ORM                    | Spring Data JPA       |
| Versionamento do Banco | Flyway                |
| Containerização        | Docker                |
| Controle de Versão     | Git                   |
| Documentação da API    | OpenAPI / Swagger     |

---

# Objetivo do Repositório

Este repositório documenta todo o processo de desenvolvimento do SIGLab.

Além da implementação do sistema, o projeto busca registrar as decisões arquiteturais, a evolução da modelagem, as práticas de engenharia utilizadas e a justificativa técnica de cada etapa do desenvolvimento.

O objetivo é que este repositório represente não apenas um produto de software, mas também um estudo completo de engenharia aplicado a um problema real.