## 🛠 Tecnologias
- Java 23
- Spring Boot 3.5
- Lombok
- Maven

---

## 🚀 Funcionalidades
- Listar todos os filmes (`GET /filmes`)
- Buscar filme por ID (`GET /filmes/{id}`)
- Adicionar novo filme (`POST /filmes`)
- Atualizar filme existente (`PUT /filmes/{id}`)
- Remover filme (`DELETE /filmes/{id}`)

---

## ⚡ Estrutura do Projeto
- `Model` → contém a classe `Filme`
- `Controller` → endpoints da API
- `Map<Long, Filme>` → armazena os filmes em memória
- `AtomicLong` → gera IDs únicos para os filmes

---

## 📦 Testando a API com Postman

### GET /filmes
- Retorna todos os filmes
```http
GET http://localhost:8080/filmes
