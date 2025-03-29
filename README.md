# bank-services
Prueba técnica VECTORA SAS

_________________________________________________________________________


# 🚀 Bank Services - Monorepo

Este proyecto es una arquitectura de microservicios para la gestión de cuentas bancarias y transacciones, utilizando **Spring Boot**, **MySQL**, **RabbitMQ** y **Docker**.

## 📂 Estructura del Proyecto

```
├── account-service/          # Microservicio de cuentas
├── transaction-service/      # Microservicio de transacciones
├── common-dto/              # Módulo común para DTOs compartidos
├── docker-compose.yml       # Configuración para desplegar con Docker
├── init.sql                 # Script de creación de tablas en MySQL
├── start.sh                 # Script de inicio automático
└── README.md                # Documentación del proyecto
```

## 🚀 Tecnologías Utilizadas
- **Spring Boot** - Para la lógica de los microservicios
- **MySQL** - Base de datos relacional
- **RabbitMQ** - Mensajería asíncrona entre microservicios
- **Docker & Docker Compose** - Para contenerización y despliegue

---

## 📦 Configuración y Despliegue

### 🔧 **Requisitos Previos**
Asegúrate de tener instalado en tu máquina:
- **Java 17** o superior
- **Maven**
- **Docker & Docker Compose**

### 🚀 **Cómo Iniciar el Proyecto**
Ejecuta el siguiente script para compilar, construir y levantar todos los servicios:

```sh
chmod +x start.sh  # Otorga permisos de ejecución (solo la primera vez)
./start.sh         # Ejecuta el script de inicio
```

Esto hará lo siguiente:
1. **Compila los microservicios** con Maven.
2. **Levanta MySQL y RabbitMQ** y espera a que estén listos.
3. **Ejecuta el script `init.sql`** para crear las tablas en MySQL.
4. **Construye las imágenes Docker de los microservicios**.
5. **Levanta `account-service` y `transaction-service`**.

### 📜 **Endpoints Principales**

#### 🏦 **Account Service** (`http://localhost:8080`)
| Método | Endpoint        | Descripción |
|--------|---------------|-------------|
| `GET`  | `/accounts/{id}` | Obtiene información de una cuenta |
| `PUT`  | `/update-balance` | Actualiza el saldo de una cuenta |

#### 💸 **Transaction Service** (`http://localhost:8081`)
| Método | Endpoint         | Descripción |
|--------|----------------|-------------|
| `POST` | `/transactions` | Realiza una transferencia |

### 🛠 **Ver Logs de los Servicios**
```sh
docker-compose logs -f account-service  # Ver logs de account-service
docker-compose logs -f transaction-service  # Ver logs de transaction-service
```

### 🛑 **Cómo Detener el Proyecto**
```sh
docker-compose down
```

---

## 📩 RabbitMQ - Mensajería entre Microservicios
El `transaction-service` publica eventos en RabbitMQ cuando se registra una nueva transacción.

📌 **Acceder al panel de RabbitMQ**
```sh
http://localhost:15672  # Usuario: guest | Contraseña: guest
```

---

## 📌 Notas Adicionales
- **Configuración de conexión a MySQL** en `application.properties`:
  ```properties
  spring.datasource.url=jdbc:mysql://mysql-db:3306/bankdb
  spring.datasource.username=root
  spring.datasource.password=root
  ```
- **Variables de RabbitMQ** en `application.properties`:
  ```properties
  rabbitmq.exchange.name=transaction-exchange
  rabbitmq.queue.name=transaction-queue
  rabbitmq.routing.key=transaction-routing-key
  ```

---

## 🎯 Autor
Desarrollado por **David Felipe Gómez R**

🚀 ¡Listo para usar! Si tienes dudas, revisa los logs o edita la configuración en `application.properties`. 🚀

