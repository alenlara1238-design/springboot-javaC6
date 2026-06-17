# 🚀 Taller Práctico - Introducción a Spring Boot

## Objetivos

Al finalizar estos ejercicios serás capaz de:

-   Crear un proyecto Spring Boot.
-   Construir un Controller con varias rutas.
-   Comprender cómo una petición HTTP recorre las capas de una
    aplicación.
-   Identificar el papel de cada una de las capas: Controller, Service,
    Repository y Model.

------------------------------------------------------------------------

# Ejercicio 1. Bienvenido a la Cafetería

## Objetivo

Crear un Controller capaz de responder diferentes solicitudes HTTP
relacionadas con una cafetería.

## Paso 1

Crea un proyecto Spring Boot agregando únicamente la dependencia:

-   Spring Web

## Paso 2

Dentro del paquete principal crea un paquete llamado:

``` text
controller
```

## Paso 3

Crea una clase llamada:

``` text
CafeteriaController
```

## Paso 4

Convierte la clase en un Controller utilizando la anotación
correspondiente.

> **Tip:** Busca la anotación que indica que una clase responderá
> solicitudes HTTP.

## Paso 5

Crea los siguientes métodos utilizando `@GetMapping`.

### Ruta 1

``` text
/menu
```

Debe retornar un mensaje indicando cuál es la bebida recomendada del
día.

Ejemplo:

> La bebida recomendada es un Capuccino.

### Ruta 2

``` text
/horario
```

Debe retornar el horario de atención de la cafetería.

Ejemplo:

> Abrimos de 8:00 AM a 7:00 PM.

## Paso 6

Ejecuta la aplicación y prueba ambas rutas desde el navegador.

## Preguntas de reflexión

1.  ¿Qué sucede cuando escribes una URL en el navegador?
2.  ¿Cómo sabe Spring qué método debe ejecutar?
3.  ¿Qué ocurriría si dos métodos utilizaran la misma ruta?

------------------------------------------------------------------------

# Ejercicio 2. Sistema de Consulta de Películas

## Objetivo

Construir un proyecto utilizando las capas:

-   Controller
-   Service
-   Repository
-   Model

Por ahora el Repository simulará la información utilizando objetos
creados manualmente.

## Contexto

Una sala de cine desea publicar información básica de las películas que
tiene disponibles.

Nuestro sistema permitirá consultar:

-   La película destacada.
-   El listado completo de películas.

## Estructura de carpetas

``` text
src
└── main
    └── java
        └── com.ejemplo.cine
            │
            ├── controller
            │      PeliculaController.java
            │
            ├── service
            │      PeliculaService.java
            │
            ├── repository
            │      PeliculaRepository.java
            │
            ├── model
            │      Pelicula.java
            │
            └── CineApplication.java
```

## Paso 1. Modelo

Crea una clase llamada `Pelicula`.

Incluye únicamente el atributo:

``` text
titulo
```

Implementa:

-   Constructor
-   Getter

> **Tip:** Esta clase representa un objeto del negocio y no necesita
> anotaciones de Spring.

## Paso 2. Repository

Crea la clase `PeliculaRepository`.

Conviértela en un Repository.

Implementa dos métodos:

-   Uno que retorne una única película.
-   Otro que retorne una lista de películas (entre cuatro y cinco
    títulos utilizando `List.of(...)`).

## Paso 3. Service

Crea la clase `PeliculaService`.

Conviértela en un Service.

Este Service deberá comunicarse con el Repository.

Implementa dos métodos:

-   Uno que retorne una película.
-   Otro que retorne la lista completa.

No agregues lógica adicional.

## Paso 4. Controller

Crea la clase `PeliculaController`.

Conviértela en un Controller.

Implementa las siguientes rutas.

### Ruta 1

``` text
/pelicula
```

Debe devolver únicamente el título de una película.

### Ruta 2

``` text
/peliculas
```

Debe devolver la lista completa que recibe desde el Service.

No transformes la respuesta.

Observa cuidadosamente cómo aparece la información en el navegador.

## Analiza el recorrido de la solicitud

Completa el siguiente diagrama:

``` text
Navegador
      │
      ▼
____________________
      │
      ▼
____________________
      │
      ▼
____________________
      │
      ▼
____________________
      │
      ▼
Respuesta al navegador
```

## Preguntas para analizar

1.  ¿Cuál capa recibe primero la solicitud HTTP?
2.  ¿Qué responsabilidad tiene el Service?
3.  ¿Por qué el Controller no crea directamente las películas?
4.  ¿Qué observas cuando el navegador recibe una lista en lugar de un
    único dato?
5.  ¿Qué crees que reemplazará la lista manual en las próximas clases?

## Desafío adicional

Agrega una tercera ruta:

``` text
/totalPeliculas
```

Esta ruta deberá mostrar únicamente la cantidad de películas
disponibles.

> **Pista:** Investiga qué método de `List` permite conocer cuántos
> elementos contiene.
