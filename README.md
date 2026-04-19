# API REST de QPRs (Spring Boot)

Proyecto Spring Boot para consultar QPRs desde PostgreSQL (Neon inicialmente).

## Endpoints

- `GET /qprs` → devuelve todas las QPRs.
- `GET /qprs/{id}` → devuelve una QPR por ID (404 si no existe).

## Requisitos

- Java 17+
- Maven 3.9+
- Base de datos PostgreSQL con tabla `qpr`

## Configuración de credenciales (sin tocar código)

La app usa variables de entorno:

- `DB_URL`
- `DB_USERNAME`
- `DB_PASSWORD`

`application.properties` ya está preparado con estos valores.

### Ejemplo (PowerShell)

```powershell
$env:DB_URL="jdbc:postgresql://<tu-host-neon>.neon.tech/neondb?sslmode=require"
$env:DB_USERNAME="<tu-usuario>"
$env:DB_PASSWORD="<tu-password>"
mvn spring-boot:run
```

## Perfil `azure`

Existe `application-azure.properties` para migración a Azure Database for PostgreSQL.

Variables soportadas en ese perfil:

- `AZURE_DB_URL` (fallback a `DB_URL`)
- `AZURE_DB_USERNAME` (fallback a `DB_USERNAME`)
- `AZURE_DB_PASSWORD` (fallback a `DB_PASSWORD`)

### Activar perfil Azure

```powershell
$env:SPRING_PROFILES_ACTIVE="azure"
mvn spring-boot:run
```

También puedes pasarlo por argumento:

```powershell
mvn spring-boot:run -Dspring-boot.run.profiles=azure
```

## Notas de esquema

La entidad `QPR` incluye:

- `id` (`Long`)
- `nombre` (`String`)
- `estado` (`String`)
- `fecha` (`LocalDate`)
