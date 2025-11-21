#LevelUP – Plataforma de Desenvolvimento de Soft Skills (API Backend)

Bem-vindo ao repositório do **GS-Java**, um sistema completo desenvolvido em Java utilizando o padrão **MVC + DAO**, arquitetura em camadas e API REST para interação com Front-End.

Este projeto implementa controle de cursos, usuários, desafios, níveis e conversas, com deploy funcional no Render.

---

## 🚀 Tecnologias Utilizadas

* **Java 17**
* **Quarkus Framework**
* **RESTEasy / JAX-RS**
* **JDBC + DAO Pattern**
* **Oracle Database**
* **Maven**
* **Render (Deploy)**
---

## 🌐  Link do Repositório
👉[Repositório no GitHub](https://github.com/pamellachristiny/GS-Java)

---

## 🌐 Link da API em Produção

👉 [https://gs-java-ditp.onrender.com](https://gs-java-ditp.onrender.com)

---

## 🌐 Link do Video de apresentação do projeto

---

## 🌐 Link do pitch

---

## 📁 Estrutura do Projeto

```
src/main/java/br/com/fiap/biblioteca/
│
├── controller/      → Camada Resource (REST)
├── dominio/         → Camada Model
├── dao/             → Camada DAO
└── service/bo/      → Regras de negócio (quando aplicável)
```

---

# 📌 Endpoints da Aplicação

Aqui está a **Tabela de Endpoints em Markdown**, pronta para colar diretamente no seu README — profissional, organizada e sem emojis.

---

## **Tabela de Endpoints (API RESTful)**

### **Usuários**

| URI              | Método | Descrição                        | Status             |
| ---------------- | ------ | -------------------------------- | ------------------ |
| `/usuarios`      | GET    | Retorna a lista de usuários.     | 200, 500           |
| `/usuarios/{id}` | GET    | Retorna um usuário específico.   | 200, 404, 500      |
| `/usuarios`      | POST   | Cadastra um novo usuário.        | 201, 400, 500      |
| `/usuarios/{id}` | PUT    | Atualiza os dados de um usuário. | 200, 400, 404, 500 |
| `/usuarios/{id}` | DELETE | Remove um usuário.               | 204, 404, 500      |

---

### **Categorias**

| URI                | Método | Descrição                         | Status        |
| ------------------ | ------ | --------------------------------- | ------------- |
| `/categorias`      | GET    | Lista todas as categorias.        | 200, 500      |
| `/categorias/{id}` | GET    | Retorna uma categoria específica. | 200, 404, 500 |
| `/categorias`      | POST   | Cadastra uma nova categoria.      | 201, 400, 500 |

---

### **Challenges**

| URI                | Método | Descrição                      | Status             |
| ------------------ | ------ | ------------------------------ | ------------------ |
| `/challenges`      | GET    | Lista todos os desafios.       | 200, 500           |
| `/challenges/{id}` | GET    | Retorna um desafio específico. | 200, 404, 500      |
| `/challenges`      | POST   | Cadastra um novo desafio.      | 201, 400, 500      |
| `/challenges/{id}` | PUT    | Atualiza um desafio existente. | 200, 400, 404, 500 |
| `/challenges/{id}` | DELETE | Remove um desafio.             | 204, 404, 500      |

---

### **Cursos**

| URI            | Método | Descrição                    | Status        |
| -------------- | ------ | ---------------------------- | ------------- |
| `/cursos`      | GET    | Lista todos os cursos.       | 200, 500      |
| `/cursos/{id}` | GET    | Retorna um curso específico. | 200, 404, 500 |
| `/cursos`      | POST   | Cadastra um novo curso.      | 201, 400, 500 |

---

### **Professores**

| URI                 | Método | Descrição                        | Status        |
| ------------------- | ------ | -------------------------------- | ------------- |
| `/professores`      | GET    | Lista todos os professores.      | 200, 500      |
| `/professores/{id}` | GET    | Retorna um professor específico. | 200, 404, 500 |

---

### **Feedbacks**

| URI               | Método | Descrição                       | Status        |
| ----------------- | ------ | ------------------------------- | ------------- |
| `/feedbacks`      | GET    | Lista todos os feedbacks.       | 200, 500      |
| `/feedbacks/{id}` | GET    | Retorna um feedback específico. | 200, 404, 500 |
| `/feedbacks`      | POST   | Envia um novo feedback.         | 201, 400, 500 |

---

### **Recomendações**

| URI                          | Método | Descrição                                         | Status        |
| ---------------------------- | ------ | ------------------------------------------------- | ------------- |
| `/recomendacoes/{idUsuario}` | GET    | Retorna recomendações personalizadas de desafios. | 200, 404, 500 |

---

### **Simulação de Conversa (IA)**

| URI                    | Método | Descrição                                                          | Status        |
| ---------------------- | ------ | ------------------------------------------------------------------ | ------------- |
| `/conversas/simulacao` | POST   | Simula uma conversa com a agente de IA (apenas para demonstração). | 200, 400, 500 |

---

# 🏛 Arquitetura do Projeto

O sistema segue a arquitetura em camadas:

### ✔ **Model (Domain)**

Classes que representam as tabelas do banco.

### ✔ **DAO (Data Access Object)**

Responsável por execução de SQL, CRUD e comunicação com a conexão.

### ✔ **BO / Service**

Onde ficam regras de negócio (quando necessário).

### ✔ **Resource (Controller)**

Endpoints REST seguindo princípios RESTful.

### ✔ **ConnectionFactory**

Gerencia conexões com banco Oracle.

---

# 🛠 Boas Práticas Utilizadas

* Padrão MVC + DAO
* Controllers organizados por entidade
* Respostas HTTP adequadas (200, 201, 404, 400)
* Deploy contínuo no Render
* Camadas separadas corretamente

---

# ☁ Deploy

O deploy foi realizado no Render com suporte ao Maven e Java 17.

Para rodar localmente:

```
mvn clean install
mvn quarkus:dev
```

Para gerar build:

```
mvn package
```

---

# 👩‍💻 Autora

**Pamella Christiny**

Projeto acadêmico desenvolvido para avaliação FIAP, baseado no tema **LevelUP**, uma plataforma para desenvolvimento de soft skills com desafios inteligentes recomendados por uma Agente de IA.

### 🎮 Sobre o Tema do Projeto

**LevelUP** é uma plataforma inovadora focada no desenvolvimento das **soft skills essenciais para as profissões do futuro**. A experiência do usuário é centrada em:

* Atividades lúdicas
* Desafios estimulantes
* Reforço do pensamento crítico
* Recomendações personalizadas via IA

A parte de **conversas** presente no backend (classes de conversa e indicação por conversa) **é utilizada apenas para simulação**, com o objetivo de representar como a IA recomendaria desafios ao usuário.

---

# 📄 Licença

Este projeto foi criado para fins educacionais.

