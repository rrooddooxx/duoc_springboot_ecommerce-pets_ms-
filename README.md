# MicroServicio Ecommerce Mascotas

Proyecto de Asignatura

```
Desarrollo Full Stack I
Ingeniería en Desarrollo de Software
Duoc UC

Profesor: Carlos Valverde
Estudiante: Sebastián Kravetz

```

### Stack

- Spring Boot v3
- Spring Boot Web Starter
- Jackson Modules
- Lombok
- JDK 22

---

## Endpoints:

> Base Host: localhost:8080

#### I. Ordenes de Compra

    - /api/v1/orders
        - [query] limit
            - ej: /api/v1/orders?limit={n: integer}
    - /api/v1/orders/{orderId: string}
    - /api/v1/orders/{orderId: string}/status

   ```bash
# peticiones cURL

curl -X GET 'localhost:8080/api/v1/orders'
curl -X GET 'localhost:8080/api/v1/orders?limit=2'
curl -X GET 'localhost:8080/api/v1/orders/{orderId}'
curl -X GET 'localhost:8080/api/v1/orders/{orderId}/status'

```

#### II. Productos

    - /api/v1/products
        - [query] limit
            - ej: /api/v1/products?limit={n: integer}
    - /api/v1/products/{productId: string}
    - /api/v1/products/{productId: string}/stock

   ```bash
# peticiones cURL

curl -X GET 'localhost:8080/api/v1/products'
curl -X GET 'localhost:8080/api/v1/products?limit=2'
curl -X GET 'localhost:8080/api/v1/products/{productId}'
curl -X GET 'localhost:8080/api/v1/products/{productId}/stock'

```

#### III. Clientes

    - /api/v1/clients
        - [query] limit
            - ej: /api/v1/clients?limit={n: integer}
    - /api/v1/clients/{clientId: integer}

   ```bash
# peticiones cURL

curl -X GET 'localhost:8080/api/v1/clients'
curl -X GET 'localhost:8080/api/v1/clients?limit=2'
curl -X GET 'localhost:8080/api/v1/clients/{clientId}'

```

### Mantenedor

Sebastián Kravetz (@rrooddooxx)
