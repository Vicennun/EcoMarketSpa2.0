# EcoMarketSPA DetallePedido API

API RESTful para la gestión de detalles de pedidos en EcoMarketSPA. Permite crear, listar, actualizar y eliminar detalles asociados a un pedido.

## Descripción del Modelo de Datos

- **Usuario** tiene un **Carrito** (1 a 1).
- **Usuario** puede tener muchos **Pedidos** (1 a muchos).
- **Pedido** puede tener muchos **DetallePedido** (1 a muchos).
- **Carrito** puede tener muchos **CarritoItem** (1 a muchos).
- **Producto** puede estar en muchos **CarritoItem** y **DetallePedido** (1 a muchos).

## Modelo:

- **DetallePedido**
  - `id`: Identificador único del detalle de pedido.
  - `pedidoId`: Identificador del pedido asociado (obligatorio).
  - `productoId`: Identificador del producto agregado al pedido (obligatorio).
  - `cantidad`: Cantidad de unidades del producto (obligatorio).
  - `precioUnitario`: Precio por unidad del producto (obligatorio).
  - `subtotal`: Subtotal calculado dinámicamente como `cantidad * precioUnitario` (puede almacenarse o calcularse en el modelo).

## Endpoints

Base URL: `/api/v1/detalles-pedido`

| Método | Endpoint                      | Descripción                               | Body (JSON)                                         |
|--------|-------------------------------|-------------------------------------------|-----------------------------------------------------|
| GET    | `/`                           | Listar todos los detalles de pedido       | -                                                   |
| GET    | `/{id}`                       | Obtener detalle de pedido por ID          | -                                                   |
| GET    | `/pedido/{pedidoId}`          | Listar detalles por ID de pedido          | -                                                   |
| POST   | `/`                           | Crear nuevo detalle de pedido             | `{ pedidoId, productoId, cantidad, precioUnitario }`|
| PUT    | `/{id}`                       | Actualizar detalle de pedido existente    | `{ pedidoId, productoId, cantidad, precioUnitario }`|
| DELETE | `/{id}`                       | Eliminar detalle de pedido por ID         | -                                                   |

### Ejemplo de JSON para POST/PUT

```json
{
  "pedidoId": 1,
  "productoId": 2,
  "cantidad": 3,
  "precioUnitario": 15.0
}
```

## Ejemplo de Respuesta

```json
{
  "id": 1,
  "pedidoId": 1,
  "productoId": 2,
  "cantidad": 3,
  "precioUnitario": 15.0,
  "subtotal": 45.0
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
3. Ejecuta la aplicación: 'DetallePedidoServiceApplication'

La API estará disponible en `http://localhost:8080/api/v1/detalles-pedido`.

---

> Proyecto de aprendizaje desarrollado con Spring Boot.