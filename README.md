# Delivery Tech API

Sistema de delivery desenvolvido com Spring Boot e Java 21.

## 🚀 Tecnologias
- **Java 21 LTS** (versão mais recente)
- Spring Boot 3.2.x
- Spring Web
- Spring Data JPA
- H2 Database
- Maven

## ⚡ Recursos Modernos Utilizados
- Records (Java 14+)
- Text Blocks (Java 15+)
- Pattern Matching (Java 17+)
- Virtual Threads (Java 21)

## 🏃‍♂️ Como executar
1. **Pré-requisitos:** JDK 21 instalado
2. Clone o repositório
3. Execute: `./mvnw spring-boot:run`
4. Acesse: http://localhost:8080/health

## 📋 Endpoints
- GET /health - Status da aplicação (inclui versão Java)
- GET /info - Informações da aplicação
- GET /h2-console - Console do banco H2

## 🔧 Configuração
- Porta: 8080
- Banco: H2 em memória
- Profile: development

## 👍 Testes
1.	GET http://localhost:8080/health - retorna JSON com status UP e versão Java 21
<img width="1366" height="731" alt="endpoint funcionando" src="https://github.com/user-attachments/assets/dbe5bb95-db95-4f25-b358-f8605b7930b6" />
<br>
2.	GET http://localhost:8080/info - retorna informações da aplicação
<img width="1366" height="768" alt="info" src="https://github.com/user-attachments/assets/088feb7f-d04a-415b-b4d8-2109973e4fd3" />
<br>
3.	GET http://localhost:8080/h2-console - abre console do banco
<img width="1366" height="768" alt="teste h2 console" src="https://github.com/user-attachments/assets/83debece-daed-415a-9c43-636d492ea960" />
<br>
4.  Hot Reload (DevTools)
<img width="1366" height="768" alt="teste devtols" src="https://github.com/user-attachments/assets/f875a879-f0b7-4360-bc81-2d603b14874c" />

## 📲 Screenshots e arquivo Spring Initializer
https://drive.google.com/drive/folders/1cCp5FQbLBepOGAh5VbqUz6AYjOzhDh9F?usp=sharing 

## 👨‍💻 Desenvolvedor
[Luiza Matos] - [Ciências da Computação - 2º período - semipresencil]  
Desenvolvido com JDK 21 e Spring Boot 3.2.x
