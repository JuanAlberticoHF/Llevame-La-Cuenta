# Documentación Técnica
- Ver. Proyecto: 0.1.0
- Ver. Documento: 1.0.0
- Fecha: 04/04/2026

## INDICE
- [1. Visión General del Proyecto](#1-vision-general-del-proyecto)
- [2. Stack Tecnológico (Herramientas usadas)](#2-stack-tecnologico)
- [3. Configuración del Entorno de Desarrollo](#3-configuración-del-entorno-de-desarrollo)
- [4. Arquitectura y Lógica](#4-arquitectura-y-logica)
  - [4.1. Estructura de paquetes](#41-estructura-de-paquetes)
  - [4.2. Gestión del Estado](#42-gestion-del-estado)
  - [4.3. El Game Loop](#43-el-game-loop)
- [5. Manejo de Errores y Validaciones](#5-manejo-de-errores-y-validaciones)
- [6. Convenciones de Código](#6-convenciones-de-codigo)
- [7. Roadmap (Trabajo Futuro)](#7-roadmap-trabajo-futuro)
- [8. Cómo Contribuir](#8-como-contribuir)



## 1. Vision General del Proyecto
Llévame La Cuenta es una aplicación de consola (CLI) desarrollada en Kotlin que permite realizar un seguimiento 
de los ahorros de los usuarios durante una partida del juego de cartas "La Cuenta". El programa permite crear 
una partida, definir a los jugadores y sus estadísticas y realizar un seguimiento ronda por ronda de los ahorros 
de los jugadores, hasta que uno se haga con la victoria en el juego y la partida termine.

Sobre las decisiones del proyecto, se ópto por una aplicación de consola y sin persistencia como punto de partida 
para centrarse en la lógica del programa y desarrollar una primera versión funcional de la misma. En futuras versiones 
se planea implementar una interfaz gráfica y una forma de persistencia de datos para guardar progreso y resultados 
de las partidas.



## 2. Stack Tecnologico

- Lenguaje de programación: Kotlin 2.2.21
- Entorno de ejecución: JDK 21
- JVM Target: Java 8
- Gestor de dependencias: Maven



## 3. Configuración del Entorno de Desarrollo
*Esta sección explica a otro programador cómo clonar tu código y ponerlo a funcionar en su ordenador.*
- **Paso 1: Clonar el repositorio:**
    ```bash
    git clone https://github.com/JuanAlberticoHF/Llevame-La-Cuenta
    ```
- **Paso 2: Abrir el proyecto:**
  
    El proyecto utiliza Maven como gestor de dependencias, por lo que se puede abrir directamente con cualquier IDE
compatible con Maven.
  - En IntelliJ IDEA: `File > Open > selecciona la carpeta del proyecto`
  - En Eclipse: `File > Import > Existing Maven Projects > selecciona la carpeta del proyecto`
- **Paso 3: Compilar y ejecutar desde el código:** 
    
    El archivo que contiene la función o puerta de entrada es `main()` en `Main.kt`, por lo que para ejecutar el
programa, se puede ejecutar directamente esta función desde el IDE.

  - En IntelliJ IDEA: `Run > Run 'MainKt'` o clic derecho en el archivo `Main.kt` y seleccionar `Run 'MainKt'`.
- **Paso 4: Generar el ejecutable:**
    
    Para generar el archivo ejecutable `LlevameLaCuenta-0.1.jar`, se puede utilizar el comando de Maven desde la terminal:
    ```bash
    mvn clean package
    ```
    Esto generará el archivo `LlevameLaCuenta-0.1.jar` en la carpeta `target/` del proyecto, que se puede ejecutar con el comando:
    ```bash
    java -jar target/LlevameLaCuenta-0.1.jar
    ```
  
    Si no se tiene Maven instalado, se puede generar el ejecutable desde el IDE utilizando la opción de empaquetado o
exportación a JAR, asegurándose de incluir todas las dependencias necesarias en el archivo JAR generado. En IntelliJ IDEA,
se puede generar el JAR de la siguiente manera:
  - File > Project Structure > Artifacts > + > JAR > From modules with dependencies > selecciona el módulo principal (Main.kt) > OK > Apply > OK
  - Luego, para generar el JAR, se puede abrir las pestaña de Maven (View > Tool Windows > Maven), abrir el menú de
Lifecycle y hacer doble clic en `package` para ejecutar el comando `mvn package` desde el IDE, lo que generará el
archivo `LlevameLaCuenta-0.1.jar` en la carpeta `target/` del proyecto, que se puede ejecutar con el comando:
    ```bash
    java -jar target/LlevameLaCuenta-0.1.jar
    ```



## 4. Arquitectura y Logica
### 4.1. Estructura de paquetes
  - `controllers/`: Clases que manejan el flujo y lógica del programa de la aplicación, de la partida y 
  de una ronda (`MainController`, `MatchController`,`RoundController`).
  - `models/`: Clases de datos (`Player`, `PlayerStats`, `Match`, `Round`).
    - `enums`: Clases de enumeración (`PaymentMethod`, `PlayerState`)
    - `parsers`: Clases de borradores (`InputRound`)
  - `views/`: Clases que manejan los mensajes a consola y la entrada de datos del usuario.
### 4.2. Gestion del Estado
  La aplicación utiliza persistencia en memoria para almacenar y manipular los datos en tiempo
  de ejecución. Para gestionar los datos de una partida el programa crea utiliza la clase `Match` que almacena la
  lista de jugadores (`List<Player>`) y una lista de rondas (`MutableList<Round>`).



### 4.3. El Game Loop
El "Game Loop" consiste en las partes destacadas del flujo de ejecución: 
  - **Menu Principal**: 
    1. El programa al arrancar desde la función ``main()`` del archivo ``Main.kt`` ejecuta la función startApp de la clase
    MainController.
    2. El programa muestra un mensaje de bienvenida y entra en un bucle infinito mostrando el menu principal, 
    esperando a que el usuario introduzca una opción válida (0, 1) y ejecutando la función correspondiente a cada opción.
    Si se introduce la opción 0, el programa se cierra mostrando un mensaje de despedida.
    3. Si el usuario introduce la opción 1, se llama a la función `matchStart()` de la clase `MatchController` que se encarga de iniciar una nueva partida.
  - **Inicialización Partida**:
    1. La función `matchStart()` de la clase `MatchController` se encarga de solicitar al usuario el número de
    jugadores, sus nombres, establece los ahorros iniciales (base al número de jugadores), crea la lista de objetos 
    ``Player``, que se enviara por parametro a la función `matchInProgress()` de la clase `MatchController` que
    se encargará de gestionar el desarrollo de la partida.
    2. Al llamar a la función `matchInProgress()` se crea un nuevo objeto `Match` con la lista de jugadores, que
    almacenara todos los datos de la partida.
    3. Se inicializa un objeto de tipo ``RoundController`` que se encargará de gestionar el flujo desarrollo de cada 
    ronda.
    4. El programa entra en un bucle que se mantendra activo mientras la partida no haya terminado, en cada iteración
        se llama a la función `newRound()` del `RoundController` que se encargará de gestionar el desarrollo de cada ronda.
  - **Bucle de Rondas**:
    1. La función `newRound()` del `RoundController` en cada iteración creará una nueva ronda.
    2. En cada ronda se mostrará la estadisticas de cada jugador y el reparto de cartas, y se solicitará al usuario 
    que pulse `ENTER` para finalizar la ronda e introducir los datos de la ronda.
    3. Al finalizar la ronda, se solicitará al usuario que introduzca los datos de la ronda (cuenta, pagos de cada 
    jugador y si han jugado suficientes cartas), al introducir los datos, el programa calculará la cuenta, 
    actualizará los ahorros de cada jugador y sus fichas de aumento, y se mostrará los resultados de la ronda.
    4. Al mostrar los resultados, el programa terminará de ejecutar la función `newRound()` y volverá al bucle de la función 
    `matchInProgress()` que comprobara si la partida ha terminado (si algún jugador ha llegado a 0 ahorros) y en 
    caso de que la partida no haya terminado, se iniciará una nueva ronda.
  - **Resolución Ganador** (fin partida):
    1. Cuando la función `matchInProgress()` detecta que la partida ha terminado, se llama a la función `matchEnd()`
    de la clase `MatchController` que se encargará de resolver el ganador de la partida, mostrar los resultados finales
    y finalizar la partida.
    2. La función `matchEnd()` mostrará el jugador que se ha quedado sin ahorros (perdedor) y solcitara al usuario
    que pulse `ENTER` para mostrar el ganador de la partida (el jugador con más ahorros) y finalizar la partida.
    3. Si hay empate a ahorros (varios jugadores con la mayor cantidad de ahorros), se mostrará un mensaje de empate y solicitara al usuario un desempate introduciendo
    el identificador del jugador que gana el desempate (mano más valiosa o desempate acordado). Si solo hay un
    jugador con la mayor cantidad de ahorros, se declarará ese jugador como ganador y se mostrará el resultado final
    de la partida.
    4. Al mostrar el resultado final, el programa terminará de ejecutar la función `matchEnd()` y volverá al bucle del
    menu principal, donde el usuario podrá iniciar una nueva partida o salir del programa.



### 5. Manejo de Errores y Validaciones

⚠️ Actualmente, el programa no implementa un manejo de errores formal utilizando excepciones, sino que se han implementado
validaciones de inputs para evitar que el programa se rompa por entradas no válidas del usuario. En el futuro se planea
implementar un manejo de errores utilizando excepciones para gestionar los errores de una manera más robusta
y controlada.

Las validaciones de inputs se realizan de diferentes maneras, todas ellas en las clases del paquete ``views`` que se
encarga de solicitar información al usuario. 

- Validaciones en bucles: En la mayoria de casos se realizan validaciones de inputs utilizando bucles que se mantienen
activos hasta que el usuario introduce un dato válido. Por ejemplo, al solicitar el número de jugadores, el programa
utiliza un bucle que se mantiene activo hasta que el usuario introduce un número entre 3 y 8, utilizando la función
`toIntOrNull()` para validar que el input es un número y no una letra, y comprobando que el número está dentro del
rango permitido. A continuación los casos donde ocurre estas validaciones:
  - Opción del menu principal (MainView.mainMenu())
  - Número de jugadores (MatchView.requestPlayers())
  - Desempate (MatchView.showAndRequestWinner())
  - Solicitar el valor de la cuenta (RoundView.requestBillAmount())
  - Solicitar los jugadores que pagan la cuenta (RoundView.requestPlayersToPay())
  - Solicitar si se han jugado suficientes cartas (RoundView.requestSufficientCards())

- Inputs sin validaciones: En algunos casos se ha optado por no realizar validaciones de inputs en los siguientes casos:
  - Introducir el nombre de los jugadores (MatchView.requestPlayers()) ya que se pueden asignar nombres por defecto.
  - Inputs para congelar el programa y esperar a que el usuario pulse `ENTER` para continuar:
    - Al iniciar una partida (MatchView.requestStartMatch())
    - Al terminar una partida y declarar el ganador (MatchView.requestEndOfMatch())
    - Al finalizar una partida y volver al menu principal (MatchView.requestGoToMenu())
    - Al finalizar una ronda (RoundView.requestEndRound())
    - Al avanzar a la siguiente ronda (RoundView.requestNextRound())



### 6. Convenciones de Codigo

El código de Llevame La Cuenta está documentado utilizando el estándar de documentación de Kotlin (KDoc), incluyendo
a clases y metodos de todos los paquetes. En futuras versiones se planea mejorar la documentación de cada clase y método
para que sea más clara y detallada, incluyendo ejemplos de uso y explicaciones de la lógica detrás de cada función.



### 7. Roadmap (Trabajo Futuro)
En las futuras versiones del programa se planea implementar las siguientes funcionalidades y mejoras:
- **Implementar una base de datos local** para guardar el progreso de las partidas, guardar una partida para continuarla 
más tarde, y poder mostrar un historial de partidas jugadas con sus resultados.
- **Implementar una interfaz gráfica de usuario (GUI)** para mejorar la experiencia de usuario y hacer
el programa más accesible para usuarios que no estén familiarizados con la consola.
- **Implementar una versión movil** de la aplicación para que los usuarios puedan usar el programa desde sus dispositivos 
móviles-



### 8. Como Contribuir

Sobre contribuciones, el proyecto es un trabajo individual, pero en algún futuro no se descarta la idea, pero si se
permite abrir issues para sugerir mejoras o reportar bugs.