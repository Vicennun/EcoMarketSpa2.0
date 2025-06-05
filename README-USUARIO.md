# EcoMarketSPA Usuario API

API RESTful para la gestión de usuarios en EcoMarketSPA. Permite crear, listar, actualizar, buscar y eliminar usuarios.

## Descripción del Modelo de Datos

- **Usuario** tiene un **Carrito** (1 a 1).
- **Usuario** puede tener muchos **Pedidos** (1 a muchos).
- **Pedido** puede tener muchos **DetallePedido** (1 a muchos).
- **Carrito** puede tener muchos **CarritoItem** (1 a muchos).
- **Producto** puede estar en muchos **CarritoItem** y **DetallePedido** (1 a muchos).

## Modelo

- **Usuario**
  - `id`: Identificador único del usuario.
  - `nombre`: Nombre del usuario (obligatorio).
  - `apellidos`: Apellidos del usuario (obligatorio).
  - `rut`: RUT chileno, único (obligatorio).
  - `email`: Correo electrónico, único (obligatorio).
  - `telefono`: Teléfono, único (obligatorio).
  - `direccion`: Dirección del usuario (obligatorio).
  - `region`: Región (obligatorio).
  - `ciudad`: Ciudad (obligatorio).
  - `codigoPostal`: Código postal (obligatorio).

  ## Endpoints

Base URL: `/api/v1/usuarios`

| Método | Endpoint                | Descripción                                 | Body (JSON)                                     |
|--------|-------------------------|---------------------------------------------|-------------------------------------------------|
| GET    | `/`                     | Listar todos los usuarios                   | -                                               |
| GET    | `/{id}`                 | Obtener usuario por ID                      | -                                               |
| GET    | `/rut/{rut}`            | Buscar usuario por RUT                      | -                                               |
| GET    | `/telefono/{telefono}`  | Buscar usuario por teléfono                 | -                                               |
| GET    | `/email/{email}`        | Buscar usuario por email                    | -                                               |
| POST   | `/`                     | Crear nuevo usuario                         | `{ nombre, apellidos, rut, email, telefono, direccion, region, ciudad, codigoPostal }` |
| PUT    | `/{id}`                 | Actualizar usuario existente                | `{ nombre, apellidos, rut, email, telefono, direccion, region, ciudad, codigoPostal }` |
| DELETE | `/{id}`                 | Eliminar usuario por ID                     | -                                               |

### Ejemplo de JSON para POST/PUT

{
  "nombre": "Juan",
  "apellidos": "Pérez Soto",
  "rut": "12345678-9",
  "email": "juan.perez@email.com",
  "telefono": "+56912345678",
  "direccion": "Av. Siempre Viva 123",
  "region": "Metropolitana",
  "ciudad": "Santiago",
  "codigoPostal": "8320000"
}

## Ejemplo de Respuesta

{
  "id": 1,
  "nombre": "Juan",
  "apellidos": "Pérez Soto",
  "rut": "12345678-9",
  "email": "juan.perez@email.com",
  "telefono": "+56912345678",
  "direccion": "Av. Siempre Viva 123",
  "region": "Metropolitana",
  "ciudad": "Santiago",
  "codigoPostal": "8320000"
}

## Requisitos

- Java 17+
- Maven
- MySQL (configurado localmente o en AWS)

## Configuración

Configura la conexión a MySQL en `src/main/resources/application.properties`:

spring.datasource.url=jdbc:mysql://<host>:<port>/<database>
spring.datasource.username=<usuario>
spring.datasource.password=<contraseña>
spring.jpa.hibernate.ddl-auto=update

## Ejecución

1. Clona el repositorio.
2. Configura el archivo `application.properties`.
3. Ejecuta la aplicación: `EcoMarketSpaUsuarioApplication`

La API estará disponible en `http://localhost:8080/api/v1/usuarios`.

---

> Proyecto de aprendizaje desarrollado con Spring Boot.