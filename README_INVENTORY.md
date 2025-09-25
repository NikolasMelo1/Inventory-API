
# 📦 Inventory API

API REST em **Spring Boot 3** para controle de produtos e estoque.  
Inclui autenticação JWT, versionamento de banco e PostgreSQL.

---

## 🚀 Tecnologias
- Java 17
- Spring Boot 3 (Web, Security, Data JPA)
- PostgreSQL + Flyway
- JWT
- Maven

---

## ▶️ Como rodar
1. Suba o PostgreSQL:
```bash
docker compose up -d db-inventory
```

2. Entre na pasta do projeto:
```bash
cd inventory-api
mvn spring-boot:run
```

3. Acesse em: **http://localhost:8082**

---

## 🔑 Autenticação
Mesmo processo do Commissions API:
```json
{ "username": "admin", "password": "admin" }
```

---

## 📚 Endpoints principais
- `GET /products` → lista produtos
- `POST /products` → cadastra produto

---

## 🧪 Exemplo com cURL
```bash
curl -X POST http://localhost:8082/products   -H "Authorization: Bearer <TOKEN>"   -H "Content-Type: application/json"   -d '{"sku":"ABC123","name":"Notebook Lenovo","price":3500,"stock":5}'
```

---

## 📦 Docker Compose (Banco)
```yaml
services:
  db-inventory:
    image: postgres:16
    environment:
      POSTGRES_PASSWORD: postgres
      POSTGRES_USER: postgres
      POSTGRES_DB: inventorydb
    ports:
      - "5434:5432"
```

---

## ✅ Futuras melhorias
- Movimentação de estoque (entrada/saída)
- Relatórios de produtos com baixo estoque
- Integração com sistemas de vendas
