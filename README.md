
# User Registration API

## Descrição

Este projeto é uma API de cadastro de usuários que utiliza **Spring Boot**, **Spring Security com JWT**, **JPA/Hibernate** e **MySQL**. Ele permite realizar operações de CRUD com autenticação e autorização baseada em tokens JWT. 

## Funcionalidades

- Autenticação e autorização usando JWT.
- CRUD completo para a entidade Usuário.
- Validação de CPF e campos obrigatórios.
- Documentação da API com Swagger.
- Testes unitários e de integração com JUnit.

## Requisitos

- Java 17+
- Maven 3.6+
- MySQL 8+
- Postman (ou qualquer cliente de API REST)
  
## Passos para Instalar e Configurar

### 1. Clonar o repositório

```bash
git clone https://github.com/seu-usuario/user-registration-api.git
cd user-registration-api
```

### 2. Configurar o MySQL

Crie um banco de dados no MySQL chamado `userdb`. No terminal MySQL:

```sql
CREATE DATABASE userdb;
```

### 3. Configurar as variáveis de ambiente do MySQL

Abra o arquivo `src/main/resources/application.properties` e configure o `username` e `password` do seu banco de dados MySQL:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/userdb?useSSL=false
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```

### 4. Rodar o projeto

No diretório raiz do projeto, execute o comando Maven para rodar a aplicação:

```bash
mvn spring-boot:run
```

### 5. Acessar a Documentação da API (Swagger)

Uma vez que a aplicação estiver em execução, você pode acessar a documentação da API via **Swagger** no seguinte endereço:

```
http://localhost:8080/swagger-ui/index.html
```

### 6. Autenticação e Geração de JWT

Para acessar os endpoints protegidos, você precisa se autenticar e obter um token JWT. Use o seguinte endpoint para autenticação:

- **Endpoint**: `/api/auth/login`
- **Método**: `POST`
- **Body**:
```json
{
  "username": "admin",
  "password": "admin"
}
```

O token JWT será retornado e você deve incluí-lo no header das próximas requisições da seguinte forma:

```
Authorization: Bearer <seu-token-jwt>
```

### 7. Operações CRUD

Você pode realizar operações de CRUD para o usuário após obter o token JWT:

- **POST /api/users** - Criar usuário
- **GET /api/users/{cpf}** - Buscar usuário pelo CPF
- **PUT /api/users/{cpf}** - Atualizar usuário
- **DELETE /api/users/{cpf}** - Remover usuário logicamente

### 8. Rodar os Testes

Para rodar os testes automatizados com **JUnit**, utilize o comando Maven:

```bash
mvn test
```

---

## Estrutura do Projeto

- **/controller**: Contém os controladores REST.
- **/model**: Contém as entidades e modelos de dados.
- **/repository**: Interface de persistência de dados (JPA).
- **/service**: Contém a lógica de negócios.
- **/security**: Configurações de segurança e JWT.
- **/resources**: Arquivos de configuração, como `application.properties`.

## Dependências Principais

- **Spring Boot**
- **Spring Security com JWT**
- **JPA/Hibernate**
- **MySQL**
- **Lombok**
- **Swagger**
- **JUnit**

---

## Contato

Para dúvidas ou sugestões, entre em contato em: `seu-email@exemplo.com`
