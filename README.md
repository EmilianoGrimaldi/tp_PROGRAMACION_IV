# 🛒 Sistema de Gestión de Pedidos - API REST

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white)
![H2 Database](https://img.shields.io/badge/H2_Database-4479A1?style=for-the-badge&logoColor=white)

Este proyecto es la resolución del **Trabajo Práctico: Fundamentos de Spring** para la materia **Programación IV** de la **Tecnicatura Universitaria en Programación (UTN)**.

## 🎯 Objetivo General
Desarrollar un Sistema de Gestión de Pedidos que demuestre la comprensión de la arquitectura **Spring Boot** y las buenas prácticas de desarrollo en Java.

## 🛠️ Tecnologías y Dependencias
El proyecto fue generado utilizando Spring Initializr con las siguientes dependencias:
- **Spring Web:** Para la creación de la API REST.
- **Spring Data JPA:** Para la persistencia de datos mediante Hibernate.
- **Lombok:** Para reducir el código boilerplate (Getters, Setters, Constructores, etc.).
- **H2 Database:** Base de datos relacional en memoria para el desarrollo y pruebas rápidas.
- **Spring Boot DevTools:** Para la recarga automática en el entorno de desarrollo.

## 🏗️ Arquitectura y Modelo de Datos
La aplicación está construida utilizando una arquitectura en capas, definiendo un modelo de dominio basado en el diagrama UML del trabajo práctico e implementando una capa completa de **DTOs** (Data Transfer Objects).

### Entidades Principales
- `Usuario`
- `Pedido` y `DetallePedido`
- `Producto` y `Categoria`
- `Base` (Superclase para atributos comunes como `id`, `eliminado` y `createdAt`)

### Capa de DTOs
Para asegurar un diseño robusto y proteger el modelo de dominio, cada entidad cuenta con distintos DTOs según la operación a realizar:
- **Create:** (ej. `ProductoCreate`, `UsuarioCreate`) para la inserción de nuevos datos.
- **Dto (Read):** (ej. `ProductoDto`, `UsuarioDto`) para la devolución de información al cliente.
- **Edit:** (ej. `ProductoEdit`, `UsuarioEdit`) para la actualización de registros existentes.

## 📚 Conceptos Teóricos Aplicados
Durante el desarrollo de este proyecto se han implementado exitosamente los siguientes conceptos de Spring:

| Concepto | Aplicación en el proyecto |
| :--- | :--- |
| **Application Context** | Uso del contenedor IoC de Spring para gestionar el ciclo de vida de los Beans y sus dependencias. |
| **Beans** | Creación y gestión de los objetos de la aplicación delegados a Spring. |
| **Inyección de Dependencias** | Desacoplamiento de los componentes inyectando dependencias **por constructor** (la mejor práctica recomendada). |
| **Estereotipos** | Uso de anotaciones como `@Service`, `@Repository`, `@RestController` y `@Component` para definir correctamente el rol de cada clase en la arquitectura. |
| **Properties** | Configuración centralizada de la aplicación (como el puerto y H2) desde el archivo `application.properties`. |

## 🚀 Datos Iniciales (Data Seeding)
Al iniciar la aplicación, se instancian y persisten en la base de datos automáticamente (a partir de sus respectivos DTOs):
- **2** Usuarios
- **3** Categorías
- **10** Productos
- **3** Pedidos (con al menos 2 detalles de pedido por cada uno)

## ⚙️ Cómo ejecutar el proyecto
1. Clonar este repositorio.
2. Abrir el proyecto en tu IDE favorito (IntelliJ IDEA, Eclipse, VS Code).
3. Asegurarse de tener instalado el JDK compatible (ej. Java 17 o superior).
4. Ejecutar la aplicación desde la clase principal que contiene la anotación `@SpringBootApplication` o ejecutar en consola mediante Gradle:
   ```bash
   ./gradlew bootRun
   ```
5. La API estará disponible localmente (por defecto en `http://localhost:8080`). Si está habilitada, la consola de H2 se podrá acceder en `http://localhost:8080/h2-console`.

---
*Desarrollado por Emiliano Grimaldi para Programación IV (UTN).*
