# Documentación Técnica
- Ver. Proyecto: 0.1.0
- Ver. Documento: 1.0.0
- Fecha: 04/04/2026

## 1. Visión General del Proyecto
Llevame La Cuenta es una aplicación de consola (CLI) desarrollada en Kotlin que permite realizar un seguimiento 
de los ahorros de los usuarios durante una partida del juego de cartas "La Cuenta". El programa permite crear 
una partida, definir a los jugadores y sus estadisticas y realizar un seguimiento ronda por ronda de los ahorros 
de los jugadores, hasta que uno se haga con la victoria en el juego y la partida termine.

Sobre las decisiones del proyecto, se ópto por una aplicación de consola y sin persistencia como punto de partida 
para centrarse en la logica del programa y desarrollar una primera versión funcional de la misma. En futuras versiones 
se planea implementar una interfaz gráfica y una forma de persistencia de datos para guardar progreso y resultados 
de las partidas.

## 2. Stack Tecnológico (Herramientas usadas)

- Lenguaje de programación: Kotlin 2.2.21
- Entorno de ejecución: JDK 21
- JVM Target: Java 8
- Gestor de dependencias: Maven

## 3. Configuración del Entorno de Desarrollo
*Esta sección explica a otro programador cómo clonar tu código y ponerlo a funcionar en su ordenador.*
*   **Paso 1: Clonar el repositorio:** El comando de Git (`git clone ...`).
*   **Paso 2: Abrir el proyecto:** Instrucciones para importarlo en su IDE (recomendando IntelliJ IDEA).
*   **Paso 3: Compilar y ejecutar desde el código:** Qué archivo contiene la función `main()` y cómo arrancar el juego en modo desarrollador.
*   **Paso 4: Generar el ejecutable (Build):** Comandos para crear el archivo `.jar` final (ej. `./gradlew build` o usar los *Artifacts* del IDE).

## 4. Arquitectura y Lógica
- **Estructura de paquetes:**
  - `controllers/`: Clases que manejan el flujo y lógica del programa de la aplicación, de la partida y 
  de una ronda (`MainController`, `MatchController`,`RoundController`).
  - `models/`: Clases de datos (`Player`, `PlayerStats`, `Match`, `Round`).
    - `enums`: Clases de enumeración (`PaymentMethod`, `PlayerState`)
    - `parsers`: Clases de borradores (`InputRound`)
  - `views/`: Clases que manejan los mensajes a consola y la entrada de datos del usuario.
- **Gestión del Estado:** La aplicación utiliza persistencia en memoria para almacenar y manipular los datos en tiempo
  de ejecución. Para gestionar los datos de una partida el programa crea utiliza la clase `Match` que almacena la
  lista de jugadores (`List<Player>`) y una lista de rondas (`MutableList<Round>`).
### 4.1 El Game Loop:
El "Game Loop" consiste en las partes destacadas del flujo de ejecución: 
  - Menu Principal: 
    1. El programa al arrancar desde la función ``main()`` del archivo ``Main.kt`` ejecuta la función startApp de la clase
    MainController.
  - Inicialización Partida
  - Bucle de Rondas
  - Resolución Ganador (Fin Partida)


### 5. Manejo de Errores y Validaciones
*   **Validación de Inputs:** Cómo manejas el hecho de que el usuario meta datos incorrectos (letras en vez de números) usando bloques `try-catch` o funciones como `toIntOrNull()` de Kotlin para que la consola no se cuelgue.

### 6. Convenciones de Código
*   **Documentación KDoc:** Explicar que el código está documentado usando el estándar de Kotlin (KDoc) y pedir a los futuros colaboradores que mantengan ese estándar al crear nuevas funciones.

### 7. Roadmap (Trabajo Futuro)
*   **Próximos pasos técnicos:** Aquí puedes anotar las mejoras técnicas que tienes pensadas. Al tener ahora mismo todo en memoria, el paso número uno aquí debería ser: *"Implementar persistencia de datos (guardar/cargar en JSON, CSV o base de datos SQLite)"*.

### 8. Cómo Contribuir (Guía de GitHub)
*   **Flujo de trabajo (Git Flow):** Cómo quieres que la gente colabore. (Ej. "Haz un *fork* del repositorio, crea una rama con tu nueva función, y envía un *Pull Request*").
*   **Reporte de Bugs:** Pedir que usen la pestaña de *Issues* de GitHub si encuentran fallos en el código.

---

Con esto, cualquier programador que entre a tu GitHub sabrá exactamente cómo compilar tu proyecto, dónde tocar para mejorarlo y cuáles son los planes futuros.

Para que la sección 3 ("Configuración del Entorno de Desarrollo") sea súper precisa: **¿Estás usando alguna herramienta como Gradle o Maven para compilar y gestionar tu proyecto, o creaste un proyecto nativo básico directamente desde tu entorno de desarrollo (IDE)?**