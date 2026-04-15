# ShopFlow Backend

A production-ready, modular e-commerce REST API built with **Spring Boot 3.4** and **Java 21**.  
Clean layered architecture — scalable, interview-ready, and extensible.

---

## 🚀 Live Demo
- **URL:** [https://shopflow-backend-9jat.onrender.com/](https://shopflow-backend-9jat.onrender.com/)
- **API Docs:** [/swagger-ui/index.html](https://shopflow-backend-9jat.onrender.com/swagger-ui/index.html)
- **H2 Database (Live Interface):** [/h2-console/](https://shopflow-backend-9jat.onrender.com/h2-console/)

### H2 Console Login:
| Field | Value |
| :--- | :--- |
| **JDBC URL** | `jdbc:h2:mem:shopflowdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE` |
| **Username** | `shopflow_login` |
| **Password** | `Pass1234` |

---

## 🛠️ Tech Stack
- **Framework:** Spring Boot 3.4
- **Security:** Spring Security + JWT + BCrypt
- **Database:** H2 (In-Memory)
- **Documentation:** Swagger / OpenAPI 3.1
- **Container:** Docker

---

## 📖 How to Use

### Local Setup
1. **Clone & Run:**
   ```bash
   git clone https://github.com/parthbais/ShopFlow_Backend.git
   cd ShopFlow_Backend
   ./mvnw spring-boot:run
   ```
2. **Access:**
   - App: `http://localhost:8080`
   - Console: `http://localhost:8080/h2-console`

### API Modules
- **Users:** Register, Login, Profile Management
- **Products:** CRUD, Pagination Support
- **Orders:** Checkout, Status Tracking (`PENDING` → `DELIVERED`)

---

## 📐 Architecture
`Controller → Service → Repository → Entity`

Modular package structure organized by domain (config, controller, service, repository, entity, dto, mapper, exception, security).

---

## 👤 Author
**Parth Bais** — [github.com/parthbais](https://github.com/parthbais)
