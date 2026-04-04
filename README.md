# Llevame La Cuenta

- Guia de usuario: [Llevame La Cuenta - Guía de Usuario](./docs/USER_GUIDE.md)
- Repositorio: [Llevame La Cuenta - GitHub](https://github.com/JuanAlberticoHF/Llevame-La-Cuenta)

## ¿Qué es?
**Llevame La Cuenta** es un programa inspirado en el juego de cartas "La Cuenta" que viene a facilitar la 
administración de una partida, gestionando los ahorros de los jugadores por cada ronda, facilitar el 
calcular la cuenta, restarle los pagos a los jugadores y al terminar la partida imprimiendo los resultados.

## ¿Cuál es el objetivo del proyecto?
El objetivo es crear la lógica funcional para administrar la partida del juego de cartas "La Cuenta" desde 
la consola y que esta sirva como base para desarrollar el backend inicial para una futura aplicación móvil
sobre el juego.

Busco aprender a desarrollar software util, de forma ordenada, con buenas prácticas, documentada y que sirva de
ejemplo para proyectos futuros.

## ¿Por qué surgió la idea del proyecto?
La idea vino por la misma naturaleza del juego en el que necesitas algún medio para gestionar la partida. El
juego sugiere utilizar medios físicos como una libreta para administrar los ahorros y el avance de la partida.
En mi caso, utilicé un bloc de notas en el móvil para gestionar la partida, y a su vez como estoy buscando ideas
de proyecto para crear y mejorar mis capacidades decidí que es una buena idea para desarrollar algo que puede ser
utilizado.

## ¿Qué tecnologías utiliza?
El programa "Llevame La Cuenta" utiliza:
- Kotlin: Lenguaje de programación para escribir la lógica.
- Git: Control de versiones local.
- GitHub: Repositorio remoto del proyecto, documentación, organización, etc.

## ¿Qué busco aprender con este proyecto?
Busco planificar de forma ordenada un proyecto, realizar un análisis de los requerimientos, diseñar, en concreto la 
arquitectura del programa y realizar una separación de responsabilidades (MVC), establecer un control de versiones 
bien ramificado basándome en metodología de gitflow y realizar commits bien explicados (Conventional Commits), 
documentar el programa, elaborar una guía, que el programa permita varios idiomas (español, inglés, etc.), organizar 
el proyecto con las propias herramientas de GitHub (Issues, Projects) y desarrollar el programa en varias fases.

## ¿Cuántas fases tendrá el desarrollo?

### 1ª FASE - 26/06/2024

Programa funcional que permite administrar la partida de "La Cuenta" desde la consola, permitiendo gestionar los ahorros
de los jugadores, calcular la cuenta, restarle los pagos a los jugadores y al terminar la partida imprimiendo los resultados.

### 2ª FASE

Implementación de una base de datos para almacenar la información de una partida, permitiendo guardar el estado de la 
partida, los jugadores, sus ahorros y los resultados. Permitira que los jugadores puedan continuar la partida en otro
momento y que puedan consultar el historial de partidas anteriores.

- Nitrite: Base de Datos NoSQL Documental (ODM)

### 3ª FASE

Desarrollo de una aplicación movil que permita a los jugadores gestionar la partida de "La Cuenta" desde su dispositivo 
móvil utilizando la lógica desarrollada en las fases anteriores.  

La aplicación permitirá a los jugadores crear una nueva partida, gestionar cada ronda de la partida, calcular la cuenta,
restarle los ahorros a los jugadores y al terminar la partida mostrar los resultados. Por otra parte, permitira archivar
partidas ya jugadas, consultar el historial de partidas anteriores, compartir resultados, guardar partidas para otro
momento, etc.

- Android: Plataforma de desarrollo móvil.
- Jetpack Compose: Framework de UI para Android.
- Realm: Base de Datos Móvil (ODM)