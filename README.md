# 📦 ERP - Spring

O **ERP - Spring** é um sistema completo e modular desenvolvido para a gestão centralizada de pedidos, produtos, clientes e vendedores. 

Construído com uma arquitetura robusta e escalável, o projeto visa otimizar operações comerciais através de uma API REST no back-end e uma interface gráfica responsiva no front-end.

---

## 🏗️ Padrão de Arquitetura (Camadas)
O back-end do sistema foi estruturado seguindo o padrão de camadas (Layered Architecture), garantindo alta coesão, baixo acoplamento e facilidade de manutenção:

- **Controller** 🎛️: Exposição dos endpoints REST e interface de comunicação da API.
- **Entities** 🗂️: Classes de domínio mapeadas diretamente para o banco de dados.
- **DTOs** 📦: Objetos de Transferência de Dados (Requests e Responses) para garantir segurança e tráfego apenas das informações necessárias.
- **Service** ⚙️: Camada responsável por concentrar toda a regra de negócio e validações complexas.
- **Repository** 🗄️: Interfaces de comunicação direta e persistência de dados no PostgreSQL.

![image](https://github.com/user-attachments/assets/bf7c47fe-34ce-40aa-82d4-c2b3aead2ec5)

---

## 🚀 Tecnologias Utilizadas

### Back-end
- ![Java](https://img.shields.io/badge/Java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white) **Java**: Linguagem base do projeto.
- ![Spring](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white) **Spring Boot**: Framework principal para criação da API REST, injeção de dependências e segurança.
- ![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white) **Hibernate**: ORM utilizado para o mapeamento objeto-relacional.
- ![Swagger](https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=Swagger&logoColor=white) **Swagger (OpenAPI)**: Documentação interativa e padronizada de todos os endpoints.
- ![Postman](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=Postman&logoColor=white) **Postman**: Utilizado para testes de integração e simulação de requisições.

### Banco de Dados & Infraestrutura
- ![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white) **PostgreSQL**: Banco de dados relacional robusto para garantir a integridade das transações do ERP.
- ![Docker](https://img.shields.io/badge/Docker-2CA5E0?style=for-the-badge&logo=docker&logoColor=white) **Docker**: Containerização do banco de dados para facilitar a execução local e padronização do ambiente.

---

## 📄 Documentação da API

A API conta com documentação completa via Swagger, mapeando todos os métodos HTTP (GET, POST, PUT, DELETE) e detalhando os schemas de requisição e resposta para as entidades do sistema.

![image](https://github.com/user-attachments/assets/aace2b62-c0ed-47fd-aaf2-98bf43d664bd)

---

## 💻 Interface Gráfica (Front-end)

O front-end foi desenvolvido para ser intuitivo e dinâmico, contando com rotas para o gerenciamento completo do ERP. As telas incluem modais de criação, listagens interativas e navegação fluida.

**Página Inicial (Dashboard):**
![image](https://github.com/user-attachments/assets/eae73a36-6cf3-489c-aa5c-8e62da607d6a)

**Gestão de Clientes:**
![image](https://github.com/user-attachments/assets/e8421c32-bc50-4693-87b5-2946e73d324c)

**Gestão de Pedidos:**
![image](https://github.com/user-attachments/assets/3fe9c39b-068b-4d35-a0e6-9def3eb0e673)

**Gestão de Produtos:**
![image](https://github.com/user-attachments/assets/5b190a95-d177-4f94-8fb6-6ec95c6e2db4)

**Gestão de Vendedores:**
![image](https://github.com/user-attachments/assets/b5548516-becb-4ee1-ad8f-8408f448a05d)

**Contato / Suporte:**
![image](https://github.com/user-attachments/assets/add2e194-f7e8-4d31-bab2-46b2b0a8b7b1)

---

## ⚙️ Como executar o projeto localmente

Para rodar este projeto na sua máquina, você precisará do [Java](https://www.oracle.com/java/), [Maven](https://maven.apache.org/) e do [Docker](https://www.docker.com/) instalados.

1. Clone este repositório:
```bash
   git clone https://github.com/dev-aguiar/erp-spring.git
