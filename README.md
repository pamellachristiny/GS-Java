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
[Repositório no GitHub](https://github.com/pamellachristiny/GS-Java)

---

## 🌐 Link da API em Produção

👉 [https://gs-java-ditp.onrender.com](https://gs-java-ditp.onrender.com)

---

## 🌐 Link do Video de apresentação do projeto
👉 [https://youtu.be/05msVwnAnXo](https://youtu.be/05msVwnAnXo)
---

## 🌐 Link do pitch
👉 [https://youtu.be/TayY6gN3Z5c](https://youtu.be/TayY6gN3Z5c)
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

