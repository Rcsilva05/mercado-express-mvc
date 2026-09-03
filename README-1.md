# Mercado Express — MVC (Checkpoint 4, Parte 2)

Projeto acadêmico desenvolvido para o **Checkpoint 4 — Parte 2 (MVC e Deploy)** da disciplina de **Tecnologia em Análise e Desenvolvimento de Sistemas (TDS)** na **FIAP**, sob orientação do **Prof. Dr. Marcel Stefan Wagner**.

> Este repositório contém **apenas a Parte 2** do Checkpoint 4 (interface Web / Spring MVC), separada da Parte 1 (API REST), conforme exigido pelo enunciado. O repositório da Parte 1 está em: **https://github.com/Rcsilva05/mercado-express-api**

## Links principais

| | |
|---|---|
| **Vídeo de demonstração** | https://www.youtube.com/watch?v=_W6nb_9tGHc |
| **Link de produção (deploy)** | https://mercado-express-mvc-jxxx.onrender.com/produtos |
| **Repositório desta Parte (MVC)** | https://github.com/Rcsilva05/mercado-express-mvc |
| **Repositório da Parte 1 (API)** | https://github.com/Rcsilva05/mercado-express-api |

## Sumário

- [Sobre o projeto](#sobre-o-projeto)
- [Integrantes](#integrantes)
- [Tecnologias utilizadas](#tecnologias-utilizadas)
- [IDE utilizada](#ide-utilizada)
- [Modelo de dados](#modelo-de-dados)
- [Como rodar localmente](#como-rodar-localmente)
- [Funcionalidades e telas (CRUD)](#funcionalidades-e-telas-crud)
- [Segurança (Spring Security)](#segurança-spring-security)
- [Configuração do Spring Initializr](#configuração-do-spring-initializr)
- [Deploy](#deploy)
- [Vídeo de demonstração](#vídeo-de-demonstração)
- [Estrutura do projeto](#estrutura-do-projeto)

---

## Sobre o projeto

Aplicação Web desenvolvida com **Spring Boot + Spring MVC + Thymeleaf** para uma empresa fictícia do tipo **mercado express** (meias, produtos de limpeza, frutas, etc). A aplicação implementa o CRUD completo (**C**reate, **R**ead, **U**pdate, **D**elete) de produtos através de uma interface Web navegável — sem a necessidade de Postman/Insomnia, como na Parte 1 — com formulários, listagem, tela de detalhes, autenticação e controle de acesso via **Spring Security**.

O tema segue o mesmo da Parte 1: um mercado express que vende produtos como meias, produtos de limpeza e frutas.

## Integrantes

| Nome | RM |
|---|---|
| Rodrigo Carvalho Silva | 565162 |
| Nickolas Davi | 564105 |
| Samara Vilela | 566133 |
| Natália Cristina | 564099 |
| Otávio Ferreira | 565960 |

**Turma:** 2TDSR

## Tecnologias utilizadas

- **Java 17**
- **Spring Boot 3.2.5** (Maven)
- **Spring MVC** — camada Web (Controllers)
- **Thymeleaf** — motor de templates para renderização das páginas HTML
- **Spring Security** — autenticação e autorização (rotas públicas/privadas)
- **Spring Data JPA / Hibernate** — persistência
- **Oracle Database** (`ORACLE_FIAP`, driver `ojdbc11`) — mesmo banco utilizado na Parte 1
- **H2 Database** — perfil alternativo em memória, usado para os testes locais e no ambiente de deploy
