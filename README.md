# ERP - Spring

O ERP - Spring permite o cadastro e gestão de pedidos, produtos, clientes e vendedores.
Este projeto é a atualização de um sistema ERP básico que desenvolvi durante meus primeiros passos em programação, utilizando classes e orientação a objetos.
Agora, estou aplicando conceitos mais avançados, usando Spring, PostgreSQL e Maven, para melhorar a estrutura e funcionalidade do sistema.

---

## Padrão em Camadas  
A arquitetura do ERP segue o padrão de camadas, facilitando a manutenção e escalabilidade do sistema.  
- **Controller** 🎛️: Interface entre o usuário e o sistema.
- **Entities** 🎛️: Entidades do projeto.
- **DTOs** 🎛️: Contém todos os DTOs (Requests e Responses) para manipulação das entidades.
- **Service** ⚙️: Contém a lógica do projeto.  
- **Repository** 🗄️: Acesso aos dados no banco, podendo ser personalizado.

![image](https://github.com/user-attachments/assets/bf7c47fe-34ce-40aa-82d4-c2b3aead2ec5)

---

## Tecnologias Utilizadas  

### Backend  
- ![Java](https://img.shields.io/badge/Java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)  
  **Descrição**: Linguagem de programação principal utilizada no desenvolvimento do backend.  
- ![Spring](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)  
  **Descrição**: Framework que facilita o desenvolvimento de aplicações Java, com suporte para APIs RESTful e injeção de dependências.  
- ![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white)  
  **Descrição**: Framework de mapeamento objeto-relacional (ORM) para facilitar o acesso e manipulação de dados no banco de dados.  
- ![Postman](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=Postman&logoColor=white)  
- ![Swaager](https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=Swagger&logoColor=white)  
  **Descrição**: Ferramenta para testar e documentar APIs REST, garantindo a qualidade das requisições e respostas.

### Banco de Dados  
- ![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)  
  **Descrição**: Banco de dados relacional utilizado em ambiente de produção, garantindo alta performance e robustez.  
- ![Docker](https://img.shields.io/badge/Docker-2CA5E0?style=for-the-badge&logo=docker&logoColor=white)  
  **Descrição**: Ferramenta para gerenciamento de contêineres, usada para facilitar o deploy do banco de dados PostgreSQL e outros serviços do sistema.

### Documentação dos endpoints do back:

- [Swagger do ERP](https://erp-spring.onrender.com/swagger-ui/index.html#/)

![image](https://github.com/user-attachments/assets/aace2b62-c0ed-47fd-aaf2-98bf43d664bd)


### Progresso Frontend do projeto
- A interface gráfica está 80% concluída!
- Conta com uma página HOME, CLIENTES, PRODUTOS, PEDIDOS, VENDEDORES e CONTATO
- As páginas CLIENTES, PRODUTOS, PEDIDOS, VENDEDORES já tem integrado um modal de criação

Home:
![image](https://github.com/user-attachments/assets/eae73a36-6cf3-489c-aa5c-8e62da607d6a)

Clientes:
![image](https://github.com/user-attachments/assets/e8421c32-bc50-4693-87b5-2946e73d324c)

Pedidos:
![image](https://github.com/user-attachments/assets/3fe9c39b-068b-4d35-a0e6-9def3eb0e673)

Produtos:
![image](https://github.com/user-attachments/assets/5b190a95-d177-4f94-8fb6-6ec95c6e2db4)

Vendedores:
![image](https://github.com/user-attachments/assets/b5548516-becb-4ee1-ad8f-8408f448a05d)

Contato:
![image](https://github.com/user-attachments/assets/add2e194-f7e8-4d31-bab2-46b2b0a8b7b1)

### Próximos passos:
- Adicionar a lógica de cadastrar produtos nos pedidos
- Conectar o front com o banco de dados cadastrado no Render
- Popular o banco de dados

### Links Úteis  
- [Java](https://www.oracle.com/java/)  
- [Spring](https://spring.io/)  
- [Hibernate](https://hibernate.org/)  
- [Postman](https://www.postman.com/)
- [Swagger](https://swagger.io/)  
- [PostgreSQL](https://www.postgresql.org/)  
- [Docker](https://www.docker.com/)  
