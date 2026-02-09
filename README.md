# 📚 Library Management System (BookReview DB)

Este é um sistema de gerenciamento de biblioteca desenvolvido em Java com integração MySQL, focado em operações CRUD para livros e avaliações (reviews). Projeto desenvolvido para fins de estudo de JDBC e arquitetura em camadas.

*This is a library management system developed in Java with MySQL integration, focusing on CRUD operations for books and reviews. Project developed for studying JDBC and layered architecture.*

---

## 🛠️ Tecnologias / Technologies

- **Java 17+**
- **MySQL** (Database)
- **JDBC** (Java Database Connectivity)
- **Git** (Version Control)

---

## 🏗️ Arquitetura / Architecture

O projeto segue uma estrutura organizada para facilitar a manutenção:
*The project follows an organized structure to facilitate maintenance:*

- `connection`: Configuração da conexão com o banco e inicialização do esquema. / *Database connection setup and schema initialization.*
- `model`: Classes de entidade (Book, Review). / *Entity classes.*
- `repository`: Padrão DAO para persistência de dados. / *DAO pattern for data persistence.*
- `view`: Interface de usuário via console (CLI). / *User interface via console.*



---

## 🚀 Como Executar / How to Run

### 1. Requisitos / Requirements
- MySQL Server rodando localmente. / *MySQL Server running locally.*
- Criar um banco de dados chamado `bookreview_db` (ou o nome configurado em sua `ConnectionFactory`). / *Create a database named `bookreview_db`.*

### 2. Configuração / Configuration
Certifique-se de atualizar as credenciais do MySQL em / *Make sure to update MySQL credentials in:*
`database.properties.example`

### 3. Rodar o projeto / Run
Execute a classe `Main.java` para iniciar a aplicação e criar as tabelas automaticamente.
*Run the `Main.java` class to start the application and automatically create the tables.*

---

## 📋 Funcionalidades / Features

- [x] **Register Books**: Cadastro de novos livros. / *Register new books.*
- [x] **List Books**: Tabela formatada de todos os livros. / *Formatted table of all books.*
- [x] **Detailed Search**: Ver livro e todas as suas reviews. / *Search book and see its reviews.*
- [x] **Add/Delete Reviews**: Sistema de avaliação de usuários. / *User review system.*
- [x] **Data Integrity**: Exclusão em cascata e validação de chaves estrangeiras. / *Cascade delete and foreign key validation.*

---

## ✒️ Autor / Author

**Fernando Calisto** - *Computer Engineering Student*
- [LinkedIn](https://www.linkedin.com/in/fernando-henrique-braga-calisto-34a703350/)
- [GitHub](https://github.com/fernandocalisto)
