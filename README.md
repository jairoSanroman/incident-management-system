<h1 align="center">📌 Sistema de Gestión de Incidencias (mini‑Jira)</h1>

<p align="center">
  Aplicación backend en <b>Java</b> y <b>Spring Boot</b> para gestionar incidencias de forma similar a un mini‑Jira,
  con arquitectura por capas, seguridad, roles de usuario y buenas prácticas más allá de un CRUD sencillo.
</p>

---

## 🏗️ Tecnologías

<div align="center">

| Backend | Base de datos | Seguridad | Otros |
|--------|----------------|-----------|-------|
| Java 17 | PostgreSQL | Spring Security (planificado) | Docker (fase final) |
| Spring Boot 3 |  | JWT (planificado) | Swagger / OpenAPI |

</div>

---

## 📦 Dominio principal

### 👤 Usuario

- `id`  
- `nombre`  
- `email`  
- `password`  
- `rol` (`ADMIN` / `USER`)

### 🎫 Incidencia

- `id`  
- `titulo`  
- `descripcion`  
- `prioridad` (`BAJA` / `MEDIA` / `ALTA`)  
- `estado` (`ABIERTA` / `EN_PROCESO` / `CERRADA`)  
- `fechaCreacion`  
- `usuarioAsignado`

---

## 🗂️ Arquitectura por capas

El proyecto está organizado en una estructura profesional:

- `controller` – Endpoints REST para gestionar usuarios e incidencias.  
- `service` – Lógica de negocio y orquestación de operaciones.  
- `repository` – Acceso a datos usando Spring Data JPA.  
- `model` – Entidades JPA del dominio.  
- `dto` – Objetos de transferencia para separar API pública del modelo interno.  
- `exception` – Manejo centralizado de errores y respuestas coherentes.  
- `config` – Configuración general de la aplicación (CORS, Swagger, etc.).  
- `security` – Configuración de Spring Security y JWT (cuando se implemente).

---

## 🚀 Puesta en marcha (desarrollo)

### 1️⃣ Requisitos

- JDK 17 instalado  
- Maven 3.x  
- PostgreSQL en local o en contenedor  
- (Opcional) Docker si se quiere levantar todo con contenedores

### 2️⃣ Configurar base de datos

Crear una base de datos en PostgreSQL, por ejemplo:

```sql
CREATE DATABASE incidencias_db;
