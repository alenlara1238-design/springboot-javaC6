# Ejercicio 1: Gestión de Suscripciones en una Startup

Estás trabajando en el backend de una plataforma de contenido bajo
demanda (estilo Netflix para programadores). Tu equipo ya ha definido el
modelo de datos que representa una suscripción y ha creado un
repositorio que simula el almacenamiento de datos en memoria.

Tu misión como desarrollador es construir las dos capas superiores del
software siguiendo la arquitectura limpia:

-   **Capa de Servicio (@Service):** Donde procesarás los datos
    aplicando lógica de negocio con Java Streams.
-   **Capa de Controlador (@RestController):** Donde expondrás los
    endpoints para que los clientes puedan interactuar con la
    aplicación.

# Código Base Provisto (No debes modificarlo)

## 1. El Modelo (`model/Suscripcion.java`)

``` java
package com.startup.streaming.model;

public class Suscripcion {
    private Long id;
    private String usuario;
    private String plan;
    private boolean activa;

    public Suscripcion() {}

    public Suscripcion(Long id, String usuario, String plan, boolean activa) {
        this.id = id;
        this.usuario = usuario;
        this.plan = plan;
        this.activa = activa;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }
    public String getPlan() { return plan; }
    public void setPlan(String plan) { this.plan = plan; }
    public boolean isActiva() { return activa; }
    public void setActiva(boolean activa) { this.activa = activa; }
}
```

## 2. El Repositorio Simulado (`repository/SuscripcionRepository.java`)

``` java
package com.startup.streaming.repository;

import com.startup.streaming.model.Suscripcion;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SuscripcionRepository {
    private final List<Suscripcion> db = new ArrayList<>();
    private long idSecuenciador = 1;

    public SuscripcionRepository() {
        db.add(new Suscripcion(idSecuenciador++, "user_alpha", "PREMIUM", true));
        db.add(new Suscripcion(idSecuenciador++, "user_beta", "BASIC", false));
        db.add(new Suscripcion(idSecuenciador++, "user_gamma", "PREMIUM", false));
        db.add(new Suscripcion(idSecuenciador++, "user_delta", "ENTERPRISE", true));
    }

    public List<Suscripcion> encontrarTodas() {
        return db;
    }

    public Suscripcion guardar(Suscripcion suscripcion) {
        suscripcion.setId(idSecuenciador++);
        db.add(suscripcion);
        return suscripcion;
    }
}
```

# Instrucciones del Ejercicio

## Paso 1: Desarrollar la Capa de Servicio (`SuscripcionService.java`)

Crea la clase dentro del paquete `com.startup.streaming.service`,
márcala con la anotación correspondiente para que Spring la detecte y
aplica inyección por constructor para conectar el repositorio.

Implementa los siguientes métodos:

-   Obtener todas las suscripciones.
-   `guardarNuevaSuscripcion(Suscripcion nueva)`: forzar `activa = true`
    antes de guardar.
-   `buscarPorId(Long id)`.
-   `listarSuscripcionesPorPlan(String plan)` usando comparación
    insensible a mayúsculas/minúsculas.

### 💡 Tips de Java Streams

-   Buscar por ID: `stream()`, `filter()`, `equals()`, `findFirst()`.
-   Filtrar por plan: `stream()`, `filter()`, `equalsIgnoreCase()`,
    `collect(Collectors.toList())`.

## Paso 2: Desarrollar la Capa de Controlador (`SuscripcionController.java`)

Crea la clase en `com.startup.streaming.controller`, inyecta el servicio
por constructor y configura la ruta base:

`/api/suscripciones`

Expón los siguientes endpoints:

-   **GET /**: listar todas las suscripciones (HTTP 200).
-   **POST /**: crear una suscripción recibiendo un JSON en el cuerpo.
-   **GET /{id}**: buscar por ID; devolver HTTP 200 o HTTP 404.
-   **GET /plan/{nombrePlan}**: listar suscripciones por plan.

### 💡 Tips de Anotaciones

-   `@RestController`
-   `@RequestMapping`
-   `@RequestBody`
-   `@PathVariable`

## Verificación

Compila la aplicación, inicia el servidor y realiza peticiones HTTP desde Postman para
verificar que los endpoints, los filtros con Streams y la captura de
parámetros dinámicos funcionen correctamente. Aquí están los endpoints que deberías probar:

### 1. Obtener todas las suscripciones iniciales
Debería retornar las 4 suscripciones precargadas en el repositorio (HTTP 200)
GET http://localhost:8080/api/suscripciones
Accept: application/json

###

### 2. Dar de alta una nueva suscripción
El estudiante debe notar que, aunque no enviemos el campo "activa", 
la capa de servicio debe forzarlo a "true" en la respuesta (HTTP 200 o 201)
POST http://localhost:8080/api/suscripciones
Content-Type: application/json

{
    "usuario": "dev_coder",
    "plan": "PREMIUM"
}

###

### 3. Buscar una suscripción existente por ID
Debería retornar la suscripción de "user_alpha" (HTTP 200)
GET http://localhost:8080/api/suscripciones/1
Accept: application/json

###

### 4. Buscar una suscripción que NO existe
Prueba de fuego para el uso de Optional: debe retornar un error 404 Not Found
GET http://localhost:8080/api/suscripciones/99
Accept: application/json

###

### 5. Filtrar suscripciones por un plan específico (Caso exacto)
Debería retornar únicamente las suscripciones con plan "PREMIUM"
GET http://localhost:8080/api/suscripciones/plan/PREMIUM
Accept: application/json

###

### 6. Filtrar suscripciones por plan (Prueba de insensibilidad a mayúsculas)
Como se pidió usar .equalsIgnoreCase(), buscar "premium" en minúsculas 
debe seguir retornando los mismos resultados que el endpoint anterior
GET http://localhost:8080/api/suscripciones/plan/premium
Accept: application/json

# Ejercicio 2: Logística Interna de una Startup de Delivery

Estás desarrollando el módulo de logística para una startup de envíos rápidos a domicilio (estilo Rappi para comercios locales). Tu equipo técnico ya definió la clase que representa un pedido y el repositorio que simula el almacenamiento en memoria de las entregas del día.

Tu tarea es programar las dos capas superiores del backend:

- **Capa de Servicio (`@Service`):** Implementarás reglas de negocio sencillas y filtros utilizando Java Streams.
- **Capa de Controlador (`@RestController`):** Crearás los puntos de acceso (endpoints) para que las aplicaciones de los repartidores puedan consultar y actualizar la información de las entregas.

---

# Código Base Provisto (No debes modificarlo)

## 1. El Modelo (`model/Pedido.java`)

```java
package com.startup.delivery.model;

public class Pedido {
    private Long id;
    private String comercio;
    private double total;
    private String zona;       // Ejemplos: "NORTE", "SUR", "CENTRO"
    private String estado;     // Ejemplos: "PREPARANDO", "EN_CAMINO", "ENTREGADO"

    public Pedido() {}

    public Pedido(Long id, String comercio, double total, String zona, String estado) {
        this.id = id;
        this.comercio = comercio;
        this.total = total;
        this.zona = zona;
        this.estado = estado;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getComercio() { return comercio; }
    public void setComercio(String comercio) { this.comercio = comercio; }
    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
    public String getZona() { return zona; }
    public void setZona(String zona) { this.zona = zona; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
```

---

## 2. El Repositorio Simulado (`repository/PedidoRepository.java`)

```java
package com.startup.delivery.repository;

import com.startup.delivery.model.Pedido;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PedidoRepository {
    private final List<Pedido> db = new ArrayList<>();
    private long idSecuenciador = 1;

    public PedidoRepository() {
        db.add(new Pedido(idSecuenciador++, "Pizzeria Don Tomas", 25.50, "NORTE", "PREPARANDO"));
        db.add(new Pedido(idSecuenciador++, "Hamburguesas Tech", 18.20, "CENTRO", "EN_CAMINO"));
        db.add(new Pedido(idSecuenciador++, "Sushi Express", 42.00, "SUR", "ENTREGADO"));
        db.add(new Pedido(idSecuenciador++, "Farmacia Central", 12.50, "CENTRO", "PREPARANDO"));
    }

    public List<Pedido> encontrarTodos() {
        return db;
    }

    public Pedido guardar(Pedido pedido) {
        pedido.setId(idSecuenciador++);
        db.add(pedido);
        return pedido;
    }
}
```

---

# Instrucciones del Ejercicio

## Paso 1: Desarrollar la Capa de Servicio (`PedidoService.java`)

Crea la clase en el paquete `com.startup.delivery.service`. Añade la anotación correspondiente para que Spring la registre en su contenedor de dependencias e inyecta el repositorio a través del constructor de la clase.

Debes escribir los siguientes **4 métodos**:

### 1. Obtener todos los pedidos

Devuelve la lista completa llamando al repositorio.

### 2. `registrarPedido(Pedido nuevo)`

Modifica el objeto recibido para asegurar que su campo **estado** comience siempre como `"PREPARANDO"` antes de enviarlo a guardar en el repositorio.

### 3. `buscarPorId(Long id)`

Busca un pedido específico por su ID.

### 4. `listarPedidosPorZona(String nombreZona)`

Retorna una lista filtrada que contenga únicamente los pedidos que pertenecen a la zona solicitada (ignora mayúsculas y minúsculas).

### 💡 Tips de Java Streams para el Servicio

**Para buscar por ID:**

- Usa `.stream()`.
- Aplica un `.filter(...)` comparando el ID del pedido actual con el ID recibido mediante `.equals()`.
- Recupera el resultado con `.findFirst()` (recuerda que devolverá un `Optional`).

**Para filtrar por zona:**

- Inicia el flujo con `.stream()`.
- Agrega un `.filter(...)` utilizando `.equalsIgnoreCase()` para validar el campo `zona`.
- Recolecta las coincidencias con `.collect(Collectors.toList())`.

---

# Paso 2: Desarrollar la Capa de Controlador (`PedidoController.java`)

Crea la clase dentro del paquete `com.startup.delivery.controller`.

Enlázala con el servicio utilizando inyección por constructor y define la ruta base unificada para todos los endpoints como:

```text
/api/pedidos
```

Implementa las rutas configurando adecuadamente las anotaciones de Spring Boot según las siguientes pautas:

### Obtener listado general

Mapea una solicitud **GET** a la ruta base.

Debe retornar todos los pedidos registrados en el sistema.

### Crear un pedido

Mapea una solicitud **POST** a la ruta base.

Debe extraer el JSON enviado por el cliente desde el cuerpo de la petición y pasarlo al método del servicio.

### Obtener pedido individual

Mapea una solicitud **GET** con un segmento dinámico en la URL siguiendo el patrón:

```text
/{id}
```

Extrae esa variable de la ruta para buscar el pedido.

- Si existe, responde con el objeto.
- Si no existe, devuelve un código de estado **404 (Not Found)**.

### Filtrar por zona geográfica

Mapea una solicitud **GET** a una ruta dinámica con la estructura:

```text
/zona/{nombreZona}
```

Debe capturar el nombre de la zona desde la URL, ejecutar el filtro del servicio y retornar los resultados correspondientes.

---

## 💡 Tips de Anotaciones para el Controlador

Recuerda usar las anotaciones estructurales:

- `@RestController`
- `@RequestMapping`

Utiliza:

- `@RequestBody` para capturar la información del **POST**.
- `@PathVariable` para mapear los parámetros dinámicos de las URLs.

> **Importante:** Si el nombre del parámetro en la firma del método de Java difiere del nombre declarado entre llaves en la ruta (por ejemplo, `{nombreZona}` frente a `String zona`), recuerda indicar explícitamente el nombre de la variable dentro de la anotación:

```java
@PathVariable("nombreZona")
String zona
```

---

# Verificación

Una vez implementado el código:

1. Compila el proyecto.
2. Levanta la aplicación Spring Boot.
3. Realiza pruebas sobre los endpoints utilizando Postman.
4. Verifica que:
   - Los pedidos se registren correctamente.
   - El estado inicial siempre sea `"PREPARANDO"`.
   - Sea posible consultar un pedido por su ID.
   - El filtrado por zonas funcione correctamente ignorando mayúsculas y minúsculas.
   - La información permanezca almacenada en memoria mientras la aplicación esté en ejecución.
5. Utiliza los siguientes endpoints para tus pruebas:

### 1. Obtener todos los pedidos del día
### Debería retornar los 4 pedidos iniciales cargados en la simulación (HTTP 200)
GET http://localhost:8080/api/pedidos
Accept: application/json

###

### 2. Registrar un nuevo pedido en el sistema
### Prueba de lógica: Aunque el cliente mande otro estado (o no lo mande),
### el servicio debe forzar el estado a "PREPARANDO" (HTTP 200 o 201)
POST http://localhost:8080/api/pedidos
Content-Type: application/json

{
    "comercio": "Tacos El Rey",
    "total": 31.40,
    "zona": "NORTE"
}

###

### 3. Buscar un pedido existente por su ID
### Debería retornar con éxito el pedido de "Pizzeria Don Tomas" (HTTP 200)
GET http://localhost:8080/api/pedidos/1
Accept: application/json

###

### 4. Intentar buscar un pedido con un ID que no existe
### Evaluación de manejo de nulos: Debe retornar un código de estado 404 Not Found
GET http://localhost:8080/api/pedidos/999
Accept: application/json

###

### 5. Filtrar pedidos por zona geográfica (Caso exacto)
 Debería retornar únicamente los pedidos mapeados en la zona "CENTRO"
GET http://localhost:8080/api/pedidos/zona/CENTRO
Accept: application/json

###

### 6. Filtrar pedidos por zona (Prueba de insensibilidad a mayúsculas)
Al usar .equalsIgnoreCase() en el stream, buscar "centro" en minúsculas
debe retornar exactamente los mismos registros que la petición anterior
GET http://localhost:8080/api/pedidos/zona/centro
Accept: application/json