# 🍔 Delivery Tech API

API de Delivery desenvolvida com **Spring Boot** e **Java 21**, que permite o gerenciamento de **clientes, restaurantes, produtos e pedidos**.  
O sistema foi projetado com boas práticas de arquitetura e uso de recursos modernos do Java.

---

## 🚀 Tecnologias Principais

- **Java 21 LTS**  
- **Spring Boot 3.2.x**  
- **Spring Web** – Criação de endpoints REST  
- **Spring Data JPA** – Persistência de dados  
- **H2 Database** – Banco de dados em memória  
- **Maven** – Gerenciamento de dependências  

---

## ⚡ Recursos Modernos Utilizados

- **Records** – para criação de DTOs e responses imutáveis  
- **Text Blocks** – para strings multilinhas  
- **Pattern Matching for instanceof**  
- **Virtual Threads (Java 21)** – suporte experimental para alta performance  

---

## 🧩 Estrutura do Projeto

src/
├─ main/java/com/deliverytech/delivery/
│ ├─ controllers/ → Endpoints REST
│ ├─ models/ → Entidades JPA (Cliente, Restaurante, Produto, Pedido)
│ ├─ repository/ → Interfaces JPA Repository
│ ├─ service/ → Regras de negócio
│ └─ DeliveryApplication.java
│
└─ main/resources/
├─ application.properties
└─ data.sql (opcional para dados iniciais)


---

## 💻 Endpoints Principais

### 🔹 Clientes
| Método | Endpoint | Descrição |
|--------|-----------|-----------|
| `POST` | `/clientes` | Cria um novo cliente |
| `GET` | `/clientes` | Lista todos os clientes |
| `GET` | `/clientes/{id}` | Busca cliente por ID |
| `PUT` | `/clientes/{id}` | Atualiza cliente existente |
| `DELETE` | `/clientes/{id}` | Exclui cliente |

---

### 🔹 Restaurantes
| Método | Endpoint | Descrição |
|--------|-----------|-----------|
| `POST` | `/restaurantes` | Cadastra restaurante |
| `GET` | `/restaurantes` | Lista todos os restaurantes |
| `GET` | `/restaurantes/{id}` | Busca restaurante por ID |
| `PUT` | `/restaurantes/{id}` | Atualiza restaurante |
| `DELETE` | `/restaurantes/{id}` | Exclui restaurante |
| `GET` | `/restaurantes/categoria/{categoria}` | Busca restaurantes por categoria |
| `PATCH` | `/restaurantes/{id}/ativo?ativo={true/false}` | Ativa/Inativa restaurante |

---

### 🔹 Produtos
| Método | Endpoint | Descrição |
|--------|-----------|-----------|
| `POST` | `/produtos?restauranteId={id}` | Cadastra produto vinculado a um restaurante |
| `GET` | `/produtos` | Lista todos os produtos |
| `GET` | `/produtos/{id}` | Busca produto por ID |
| `PUT` | `/produtos/{id}` | Atualiza produto |
| `DELETE` | `/produtos/{id}` | Exclui produto |
| `GET` | `/produtos/restaurante/{id}` | Lista produtos por restaurante |

---

### 🔹 Pedidos
| Método | Endpoint | Descrição |
|--------|-----------|-----------|
| `POST` | `/pedidos` | Cria um novo pedido (com cliente e itens) |
| `GET` | `/pedidos` | Lista todos os pedidos |
| `GET` | `/pedidos/{id}` | Busca pedido por ID |
| `GET` | `/pedidos/cliente/{id}` | Lista pedidos de um cliente específico |
| `PATCH` | `/pedidos/{id}/status?novoStatus={status}` | Atualiza o status do pedido |

---

## 🧠 Regras de Negócio

- Um **pedido** deve conter **cliente e pelo menos um produto**.  
- O **status** do pedido pode ser:  
  `PENDENTE`, `EM_ANDAMENTO`, `ENTREGUE`, `CANCELADO`.  
- Produtos precisam estar **disponíveis** para serem adicionados a pedidos.  
- Restaurantes podem ser **ativados ou desativados** via endpoint PATCH.  

---

## 🏃‍♀️ Como Executar Localmente

1. **Pré-requisitos:**  
   - JDK 21 instalado  
   - Maven configurado  

2. **Clonar o repositório:**
   ```bash
   git clone https://github.com/teu-usuario/deliverytech.git
3. **Executar o projeto:**
   ```bash
   ./mvnw spring-boot:run
4. **Acessar:**
   API: http://localhost:8080
   H2 Console: http://localhost:8080/h2-console

---

## 🔧 Configurações
| Propriedade | Porta Padrão |  Banco de Dados  | Profile Ativo |
|-------------|--------------|------------------| --------------|
|   `valor`   |    `8080`    | `H2 (em memória)`| `development` |

---

## 📲 Recursos Extras
📸 Screenshots e configuração inicial
Google Drive - Delivery Tech

---

## 👩‍💻 Desenvolvido por Luiza Matos
- 📚 Estudante de Ciência da Computação (2º período - semipresencial)
- 💻 Desenvolvido com Java 21 + Spring Boot 3.2.x
