# EcoMarketSPA CarritoItem API

API RESTful para la gestión de ítems en el carrito de compras de EcoMarketSPA. Permite crear, listar, actualizar y eliminar ítems asociados a un carrito.

## Descripción del Modelo de Datos

- **Usuario** tiene un **Carrito** (1 a 1).
- **Usuario** puede tener muchos **Pedidos** (1 a muchos).
- **Pedido** puede tener muchos **DetallePedido** (1 a muchos).
- **Carrito** puede tener muchos **CarritoItem** (1 a muchos).
- **Producto** puede estar en muchos **CarritoItem** y **DetallePedido** (1 a muchos).

## Modelo:

- **CarritoItem**
  - `id`: Identificador único del ítem en el carrito.
  - `productoId`: Identificador del producto agregado al carrito (obligatorio).
  - `cantidad`: Cantidad de unidades del producto (obligatorio).
  - `precioUnitario`: Precio por unidad del producto (obligatorio).
  - `subtotal`: Subtotal calculado dinámicamente como `cantidad * precioUnitario` (no almacenado en base de datos, solo método de cálculo).

## Endpoints

Base URL: `/api/v1/carrito-items`

| Método | Endpoint            | Descripción                        | Body (JSON)                  |
|--------|---------------------|------------------------------------|------------------------------|
| GET    | `/`                 | Listar todos los ítems             | -                            |
| GET    | `/{id}`             | Obtener ítem por ID                | -                            |
| POST   | `/`                 | Crear nuevo ítem                   | `{ productoId, cantidad, precioUnitario }` |
| PUT    | `/{id}`             | Actualizar ítem existente          | `{ productoId, cantidad, precioUnitario }` |
| DELETE | `/{id}`             | Eliminar ítem por ID               | -                            |

### Ejemplo de JSON para POST/PUT

```json
{
  "productoId": 1,
  "cantidad": 2,
  "precioUnitario": 10.5
}
```

## Ejemplo de Respuesta

```json
{
  "id": 1,
  "productoId": 1,
  "cantidad": 2,
  "precioUnitario": 10.5,
  "subtotal": 21.0
}
```

## Requisitos

- Java 17+
- Maven
- MySQL (configurado en AWS)

## Configuración

Configura la conexión a MySQL en `src/main/resources/application.properties`:

```
spring.datasource.url=jdbc:mysql://<host>:<port>/<database>
spring.datasource.username=<usuario>
spring.datasource.password=<contraseña>
spring.jpa.hibernate.ddl-auto=update
```

## Ejecución

1. Clona el repositorio.
2. Configura el archivo `application.properties`.
3. Ejecuta la aplicación: 'EcoMarketSpaCarritoItemApplication'

La API estará disponible en `http://localhost:8080/api/v1/carrito-items`.

---

> Proyecto de aprendizaje desarrollado con Spring Boot.