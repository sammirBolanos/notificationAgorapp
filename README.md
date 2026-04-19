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

Puerto por defecto para perfil `default` (Neon): `8082`.

Nota: si no defines perfil, la app arranca por defecto en perfil `swagger` para facilitar pruebas locales.

### Ejemplo (PowerShell)

```powershell
$env:DB_URL="jdbc:postgresql://<tu-host-neon>.neon.tech/neondb?sslmode=require"
$env:DB_USERNAME="<tu-usuario>"
$env:DB_PASSWORD="<tu-password>"
mvn spring-boot:run
```

### Ejecucion desde VS Code (Run)

La configuracion de depuracion en `.vscode/launch.json` usa `envFile=${workspaceFolder}/.env`.

1. Crea un archivo `.env` en la raiz del proyecto.
2. Puedes partir de `.env.example`.
3. Asegurate de definir `DB_PASSWORD` con el valor real.

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

### Forzar perfil default (PostgreSQL/Neon)

```powershell
mvn spring-boot:run "-Dspring-boot.run.profiles=default"
```

## Perfil `swagger` (local sin PostgreSQL)

Para probar endpoints y Swagger UI sin depender de Neon, usa el perfil `swagger`.

Este perfil usa H2 en memoria y evita el error de autenticacion cuando `DB_PASSWORD` no es valido.

### Ejecutar con Maven

```powershell
mvn spring-boot:run -Dspring-boot.run.profiles=swagger
```

### Ejecutar desde VS Code

Selecciona la configuracion de ejecucion: `Spring Boot-Swagger Local (H2)`

### URLs utiles

- Swagger UI: `http://localhost:8081/swagger-ui/index.html`
- OpenAPI: `http://localhost:8081/v3/api-docs`
- H2 Console: `http://localhost:8081/h2-console`

## Notas de esquema

La entidad `QPR` incluye:

- `id` (`Long`)
- `nombre` (`String`)
- `estado` (`String`)
- `fecha` (`LocalDate`)

## Despliegue con Docker y Render

Se agregaron estos archivos para facilitar despliegue:

- `Dockerfile`
- `docker-compose.yml`
- `.dockerignore`
- `render.yaml` (opcional, para Blueprint en Render)

### 1. Levantar local con Docker Compose

Antes de ejecutar, crea un `.env` con credenciales reales de Neon:

```env
DB_URL=jdbc:postgresql://<host-neon>/<db>?sslmode=require&channelBinding=require
DB_USERNAME=<usuario>
DB_PASSWORD=<password>
CORS_ALLOWED_ORIGINS=*
```

Luego ejecuta:

```powershell
docker compose up --build
```

API disponible en `http://localhost:8082`.

### 2. Desplegar en Render (paso a paso)

Render no usa `docker-compose.yml` para producción; usa `Dockerfile` (o `render.yaml` como plantilla).

1. Sube el proyecto a GitHub.
2. En Render, crea un `New +` -> `Web Service`.
3. Conecta el repositorio.
4. Runtime: `Docker`.
5. En variables de entorno configura:
	- `SPRING_PROFILES_ACTIVE=default`
	- `DB_URL=...`
	- `DB_USERNAME=...`
	- `DB_PASSWORD=...`
	- `CORS_ALLOWED_ORIGINS=*` (o dominio de tu frontend)
6. Deploy.

Listo: Render inyecta `PORT` automáticamente y la app ya está preparada para usarlo.

### 3. URL de Swagger en Render

- `https://<tu-servicio>.onrender.com/swagger-ui/index.html`
