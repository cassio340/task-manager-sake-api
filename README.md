# Sake — API de organização de rotina

API REST para organizar a rotina do dia a dia por meio de tarefas com data e horário. Construída com Java e Spring Boot.

> **Status:** em desenvolvimento. Hoje a API já tem o modelo de dados, migrations e consulta por ID. O CRUD completo e a autenticação são os próximos passos.

## Tecnologias

- Java 21
- Spring Boot 4 (Web MVC, Data JPA, Security, Validation)
- PostgreSQL
- Flyway (migrations)
- Maven

## Como rodar

**Pré-requisitos:** JDK 21 e PostgreSQL.

1. Clone o projeto e crie o banco:

   ```bash
   git clone https://github.com/cassio340/task-manager-sake-api.git
   cd task-manager-sake-api
   ```

   ```sql
   CREATE DATABASE sake;
   ```

2. Defina as variáveis de ambiente:

   ```bash
   export DB_URL=jdbc:postgresql://localhost:5432/sake
   export DB_USER=postgres
   export DB_PASSWORD=sua_senha
   ```

   No Windows (PowerShell), use `$env:DB_URL="..."`.

3. Execute:

   ```bash
   ./mvnw spring-boot:run
   ```

A API sobe em `http://localhost:8080` e o Flyway cria as tabelas automaticamente.

## Endpoints

| Método | Rota | Descrição |
| --- | --- | --- |
| `GET` | `/users/{id}` | Busca um usuário pelo ID |
| `GET` | `/appointment/{id}` | Busca uma tarefa (appointment) pelo ID |

## Modelo de dados

- **User:** `id`, `name`, `email`, `password`
- **Appointment (tarefa):** `id`, `name`, `description`, `date`, `time`

## Arquitetura

Organização em camadas: `controller` → `service` → `repository` → `entity`, dentro de `src/main/java/br/com/sake`.

## Próximos passos

- [ ] CRUD completo de tarefas
- [ ] Vincular tarefas a usuários
- [ ] Autenticação com JWT e senhas com BCrypt
- [ ] Tratamento de erros padronizado
- [ ] Testes automatizados
- [ ] Documentação com Swagger/OpenAPI
- [ ] Docker

## Licença

Consulte o arquivo [LICENSE](LICENSE).