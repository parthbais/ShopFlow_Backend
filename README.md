# ShopFlow Backend

A production-ready, modular e-commerce REST API built with **Spring Boot 3.4** and **Java 21**.  
Clean layered architecture — scalable, interview-ready, and easily extensible.

---

## Tech Stack

| Layer | Technology |
|---|---|
| Framework | Spring Boot 3.4.4 |
| Language | Java 21 |
| Database | H2 (dev) / MySQL (prod) |
| ORM | Spring Data JPA + Hibernate |
| Security | Spring Security + BCrypt |
| Validation | Jakarta Bean Validation |
| Documentation | Swagger UI (SpringDoc OpenAPI 3.1) |
| Build | Maven |
| Containerization | Docker |
| CI/CD | GitHub Actions |

---

## Architecture

```
Client → Controller → Service → Repository → Entity
```

**Package Structure**
```
com.shopflow
├── config/        AppConstants, CorsConfig, SecurityConfig, SwaggerConfig
├── controller/    UserController, ProductController, OrderController
├── service/       interfaces + impl/
├── repository/    JpaRepository extensions
├── entity/        User, Product, Order, OrderStatus
├── dto/
│   ├── request/   RegisterRequest, LoginRequest, ProductRequest, OrderRequest
│   └── response/  UserResponse, ProductResponse, OrderResponse, ApiResponse<T>
├── mapper/        UserMapper, ProductMapper, OrderMapper
├── exception/     ResourceNotFoundException, GlobalExceptionHandler
└── security/      JwtUtil, JwtFilter, UserDetailsServiceImpl
```

---

## API Endpoints

### User Module — `/api/v1/users`

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/register` | Register a new user |
| `POST` | `/login` | Login |
| `GET` | `/{id}` | Get user by ID |
| `GET` | `/` | Get all users |
| `DELETE` | `/{id}` | Delete user |

### Product Module — `/api/v1/products`

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/` | Create a product |
| `GET` | `/?page=0&size=10` | Get all products (paginated) |
| `GET` | `/{id}` | Get product by ID |
| `PUT` | `/{id}` | Update product |
| `DELETE` | `/{id}` | Delete product |

### Order Module — `/api/v1/orders`

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/` | Place an order |
| `GET` | `/{id}` | Get order by ID |
| `GET` | `/user/{userId}` | Get all orders by user |
| `PATCH` | `/{id}/status?status=CONFIRMED` | Update order status |

Order status flow: `PENDING → CONFIRMED → SHIPPED → DELIVERED → CANCELLED`

---

## Standard Response Format

```json
{
  "success": true,
  "message": "Order placed successfully",
  "data": {
    "id": 1,
    "userName": "Parth Shah",
    "productName": "MacBook Pro",
    "quantity": 2,
    "totalPrice": 300000.00,
    "status": "PENDING",
    "createdAt": "2026-04-10T00:07:00"
  }
}
```

---

## Quick Start

**Prerequisites:** Java 21, Maven 3.9+

```bash
git clone https://github.com/parthbais/ShopFlow_Backend.git
cd ShopFlow_Backend
./mvnw spring-boot:run
```

App runs on `http://localhost:8080`

| URL | Description |
|-----|-------------|
| `http://localhost:8080/swagger-ui/index.html` | Interactive API docs |
| `http://localhost:8080/h2-console` | H2 database browser |

> H2 Console: JDBC URL `jdbc:h2:mem:shopflowdb` · User `sa` · Password *(empty)*

### Run with Docker

```bash
./mvnw package -DskipTests
docker build -t shopflow-backend .
docker run -p 8080:8080 shopflow-backend
```

### Switch to MySQL

Update `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/shopflow_db?useSSL=false&serverTimezone=UTC
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```

---

## Author

**Parth Bais** — [github.com/parthbais](https://github.com/parthbais)
