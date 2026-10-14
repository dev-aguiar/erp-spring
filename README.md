# 📦 ERP - Spring

O **ERP - Spring** é um sistema completo e modular desenvolvido para a gestão centralizada de pedidos, produtos, clientes e vendedores.

Construído com uma arquitetura robusta e escalável, o projeto conta com uma API REST segura (Spring Security + JWT) no back-end e uma interface moderna e responsiva no front-end.

---

## 🔗 Demo ao vivo

- 🖥️ **Aplicação (Front-end):** https://erp-frontend-flax-seven.vercel.app
- 📄 **Documentação da API (Swagger):** https://erp-spring.onrender.com/swagger-ui/index.html
- 💻 **Repositório do Front-end:** https://github.com/dev-aguiar/erp-frontend

> ⏳ O back-end está hospedado no plano gratuito do Render, que "hiberna" após inatividade.
> A primeira requisição pode levar ~30-50s para "acordar" o servidor — depois disso, fica rápido.

---

## ✨ Funcionalidades

- **Autenticação e autorização** com Spring Security + JWT, com papéis **ADMIN** e **USER**.
- **CRUD completo** de clientes, produtos, pedidos e vendedores.
- **Controle de estoque automático**: ao adicionar um produto a um pedido, a baixa no estoque é feita de forma transacional (`@Transactional`); estoque insuficiente retorna **409 Conflict**.
- **Pedidos** com itens, cálculo de subtotal por item e total do pedido.
- **Migrations versionadas** com Flyway (schema controlado e reproduzível).
- **Documentação interativa** da API via Swagger/OpenAPI.
- **Tratamento global de exceções** com respostas HTTP padronizadas.

---

## 🔐 Segurança

A API é protegida com **Spring Security + JWT** (biblioteca `jjwt`, algoritmo HS256):

- `POST /auth/login` → autentica e retorna um **token JWT** (válido por 2h).
- `POST /auth/register` → cria um novo usuário (sempre com papel **USER**).
- Demais endpoints exigem o header `Authorization: Bearer <token>`.
- **Papéis**: operações de `DELETE` são restritas a usuários **ADMIN**; as demais exigem apenas autenticação.
- Senhas são armazenadas com hash **BCrypt** (nunca em texto puro).

O segredo de assinatura do token é lido da variável de ambiente `JWT_SECRET` (mínimo de 32 caracteres).

---

## 🏗️ Padrão de Arquitetura (Camadas)

O back-end foi estruturado seguindo o padrão de camadas (Layered Architecture), garantindo alta coesão, baixo acoplamento e facilidade de manutenção:

- **Controller** 🎛️: Exposição dos endpoints REST e interface de comunicação da API.
- **Service** ⚙️: Camada responsável por concentrar toda a regra de negócio e validações complexas.
- **Repository** 🗄️: Interfaces de comunicação direta e persistência de dados no PostgreSQL.
- **Entities** 🗂️: Classes de domínio mapeadas diretamente para o banco de dados.
- **DTOs** 📦: Objetos de Transferência de Dados (Requests e Responses), trafegando apenas o necessário.
- **Security** 🔐: Filtro JWT, serviço de token e configuração de autenticação/autorização.
- **Config** 🔧: Configurações de segurança (`SecurityConfig`) e CORS (`CorsConfig`).
- **Exceptions** 🚨: Tratamento global de erros com respostas HTTP adequadas.

---

## 🚀 Tecnologias Utilizadas

### Back-end
- ![Java](https://img.shields.io/badge/Java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white) **Java 17**: Linguagem base do projeto.
- ![Spring](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white) **Spring Boot 3**: Framework principal para criação da API REST e injeção de dependências.
- ![Spring Security](https://img.shields.io/badge/Spring_Security-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white) **Spring Security + JWT**: Autenticação e autorização baseadas em token (`jjwt`).
- ![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white) **Hibernate / JPA**: ORM utilizado para o mapeamento objeto-relacional.
- ![Swagger](https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=Swagger&logoColor=white) **Swagger (OpenAPI)**: Documentação interativa e padronizada de todos os endpoints.
- ![JUnit](https://img.shields.io/badge/JUnit5-25A162?style=for-the-badge&logo=junit5&logoColor=white) **JUnit 5 + Mockito**: Testes unitários dos services e dos controllers (MockMvc).
- ![Flyway](https://img.shields.io/badge/Flyway-CC0200?style=for-the-badge&logo=flyway&logoColor=white) **Flyway**: Versionamento e migrations do banco de dados (schema controlado).

### Banco de Dados & Infraestrutura
- ![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white) **PostgreSQL**: Banco de dados relacional robusto para garantir a integridade das transações do ERP.
- ![Docker](https://img.shields.io/badge/Docker-2CA5E0?style=for-the-badge&logo=docker&logoColor=white) **Docker**: Containerização da aplicação para padronização do ambiente e deploy.
- ![Render](https://img.shields.io/badge/Render-46E3B7?style=for-the-badge&logo=render&logoColor=black) **Render**: Deploy do back-end (Docker).
- ![Supabase](https://img.shields.io/badge/Supabase-3ECF8E?style=for-the-badge&logo=supabase&logoColor=white) **Supabase**: PostgreSQL gerenciado em nuvem.
- ![Vercel](https://img.shields.io/badge/Vercel-000000?style=for-the-badge&logo=vercel&logoColor=white) **Vercel**: Deploy do front-end.

---

## 📄 Documentação da API

A API conta com documentação completa via Swagger, mapeando todos os métodos HTTP (GET, POST, PUT, DELETE) e detalhando os schemas de requisição e resposta para as entidades do sistema.

![image](https://github.com/user-attachments/assets/aace2b62-c0ed-47fd-aaf2-98bf43d664bd)

---

## 💻 Interface Gráfica (Front-end)

O front-end foi redesenhado com um **design system próprio** (Tailwind CSS), contando com sidebar de navegação, dashboard com indicadores, autenticação (login e cadastro) e telas de gestão completas.

**Login/Cadastro:**
<img width="1590" height="728" alt="image" src="https://github.com/user-attachments/assets/865c646b-f5f0-4e32-9d0a-e8efeffa3bae" />
<img width="1590" height="722" alt="image" src="https://github.com/user-attachments/assets/35bbed69-c78e-4295-9cc2-a148a09e6ed2" />

**Página Inicial (Dashboard):**
<img width="1590" height="730" alt="image" src="https://github.com/user-attachments/assets/0eee0875-1ba9-4089-ba90-b3845f7e3f63" />

**Gestão de Clientes:**
<img width="1588" height="730" alt="image" src="https://github.com/user-attachments/assets/5d808e74-2960-4403-8029-273f0a19c7fb" />

**Gestão de Pedidos:**
<img width="1590" height="731" alt="image" src="https://github.com/user-attachments/assets/bff61d3a-99e4-4e5a-9e82-a14a9bfe8d13" />

**Gestão de Produtos:**
<img width="1582" height="734" alt="image" src="https://github.com/user-attachments/assets/9b3b3979-3fd7-43ef-8516-fca60c4873c6" />

---

## 🗺️ Próximos passos

- 📄 Paginação e filtros nos endpoints de listagem.
- 🤖 CI com GitHub Actions rodando os testes automaticamente.
- 📊 Relatórios de vendas e estoque.

---

## ⚙️ Como executar localmente

Pré-requisitos: [Java 17+](https://www.oracle.com/java/), [Maven](https://maven.apache.org/) e [Docker](https://www.docker.com/) (para o banco).

1. Clone o repositório:
    ```bash
    git clone https://github.com/dev-aguiar/erp-spring.git
    cd erp-spring
    ```

2. Suba um PostgreSQL local (via Docker):
    ```bash
    docker run --name erp-db \
      -e POSTGRES_DB=erp \
      -e POSTGRES_PASSWORD=123456 \
      -p 5432:5432 -d postgres:16
    ```

3. Rode a aplicação (o perfil `local` já é o padrão):
    ```bash
    ./mvnw spring-boot:run
    ```

4. Acesse o Swagger: http://localhost:8080/swagger-ui/index.html

> Para produção, defina a variável de ambiente **`JWT_SECRET`** (string de 32+ caracteres) usada na assinatura dos tokens.

---

## 🧪 Testes

```bash
./mvnw test
```

Cobertura de testes unitários nos **services** (regras de negócio) e nos **controllers** (via MockMvc).

---

## 👤 Autor

**André Aguiar**

[![GitHub](https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white)](https://github.com/dev-aguiar) [![LinkedIn](https://img.shields.io/badge/LinkedIn-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/dev-aguiar/)
