# GUIA DE USUARIO
- Ver. Proyecto: 0.1.0
- Ver. Documento: 1.0.0
- Fecha: 04/04/2026

# INDICE
- [Introducción](#introducción)
- [Gestión de Datos](#gestión-de-datos)
- [Requisitos previos](#requisitos-previos)
- [Ejecución del programa](#ejecución-del-programa)
- [Como usar Llevame La Cuenta](#como-usar-llevame-la-cuenta)
  - [Menu Principal](#menu-principal)
  - [Inicialización Partida](#inicialización-partida)
  - [Rondas](#rondas)
  - [Finalización de la partida](#finalización-de-la-partida)
- [FAQ: Problemas comunes y soluciones](#faq-problemas-comunes-y-soluciones)
- [Contacto y Soporte](#contacto-y-soporte)


# Introducción
**Llevame La Cuenta** es un programa diseñado para facilitar la administración de partidas del juego de 
cartas [La Cuenta](https://2tomatoesgames.com/es/la-cuenta-8437027014796.html) de **2Tomatoes Games**. Permite gestionar 
los ahorros de los jugadores, calcular la cuenta, en cada ronda, restarle los pagos a los jugadores y al finalizar 
la partida, declarar al ganador y imprimir los resultados.

Las características principales del programa incluyen:
- **Creación de partidas con un número variable de jugadores**. (minimo 3 y maximo 8 jugadores)
- **Introducción de las reglas del juego, los ahorros de cada jugador y el reparto de cartas**.
- **Gestión de los resultados de cada ronda**, incluyendo el **calculo de la cuenta** y la **actualización
de los ahorros de los jugadores** y sus **fichas de aumento**.
- Declaración del ganador al finalizar la partida (o desempate en caso de empate) y presentación 
de los resultados finales.

# Gestión de Datos
El programa no almacena datos de forma persistente, toda la gestión de la partida se realiza en memoria
durante la ejecución del programa. Al finalizar la partida o cerrar el programa, los datos se pierden, 
por lo que es importante finalizar la partida correctamente para obtener los resultados finales.

# Requisitos previos
Para ejecutar el programa, en la consola se requiere:
- Tener acceso a una terminal o consola de comandos en el sistema operativo.
- Tener instalado Java Runtime Environment (JRE) versión 8 o superior.
- Tener el archivo ejecutable del programa (llevame-la-cuenta-0.1.jar) en el directorio de trabajo.

# Ejecución del programa
Para ejecutar el programa, sigue estos pasos:
1. Abre una terminal o consola de comandos.
2. Navega al directorio donde se encuentra el archivo ejecutable del programa (LlevameLaCuenta-0.1.jar).
3. Ejecuta el siguiente comando:
```bash
java -jar llevame-la-cuenta-0.1.0.jar
```

# Como usar Llevame La Cuenta
Una vez ejecutado el programa, el programa mostrara un mensaje de bienvenida y posteriormente el menu.

### Menu Principal
El menu principal ofrece las siguientes opciones:

- `1.` Iniciar nueva partida: Permite crear una nueva partida, introduciendo el número de jugadores, sus nombres y
  establecer los ahorros iniciales.
- `2.` Continuar partida guardada (No Disponible): En una futura versión, esta opción permitirá cargar una partida previamente guardada.
- `3.` Ver historial de partidas (No Disponible): En una futura versión, esta opción permitirá revisar el historial de partidas jugadas.
- `0.` Salir: Cierra el programa.

### Inicialización Partida
Al seleccionar la opción `1. Iniciar nueva partida`, el programa procedera a inicializar la partida solicitando:
- **El número de jugadores** (entre 3 y 8).
- **El nombre de cada jugador o un nombre por defecto** si el usuario no introduce uno. El programa asignará un nombre 
por defecto en caso de que el usuario no introduzca uno.
  - El nombre por defecto se asignará base al orden de introducción de los jugadores, por ejemplo: Jugador 1, Jugador 2, etc.

Posteriormente, el programa imprimira una tabla mostrando los ahorros que le corresponde a cada jugador base al 
número de jugadores de la partida, mostrara las reglas basicas y solicitara al usuario presionar `ENTER` para 
iniciar la partida.

### Rondas
Una vez iniciada la partida, el programa entrará en un bucle de rondas, donde en cada ronda se estructura de la 
siguiente manera:
1. **Datos de la ronda**: El programa mostrar el número de ronda, las estadisticas de cada jugador (ahorros, fichas de 
aumento, etc.), el reparto de cartas para cada jugador y solicitara al usuario presionar `ENTER` para finalizar la ronda.
2. **Ronda Finalizada**: El programa al finalizar la ronda solicitara los datos de la ronda, que incluyen:
   - **El valor total de la cuenta para esa ronda**, ya sea el valor total o una operación de sumas y restas para calcular la cuenta.
   - **Los jugadores que pagan la cuenta**: Se introduce primero el ID de quien pide la cuenta (y paga) y posteriormente 
   los jugadores que acompañan al pago de la cuenta, introduciendo sus IDs separados por comas (1,2,3,...).
   - **Introducir si se han jugado tantas cartas como jugadores**, lo que implica recibir ficha de aumento y poder cambiar 
   cartas de su mano, o si se han jugado menos cartas, no recibir ficha de aumento y reemplazar toda su mano.
3. **Resultados de la ronda**: El programa gestionara los resultados de la ronda, actualizando los ahorros de los 
jugadores, calculando el metodo de pago utilizado mostrara los resultados de la ronda, que incluyen:
   - Quien pidio la cuenta.
   - El valor total de la cuenta.
   - Metodo de pago utilizado.
   - Pagos realizados por cada jugador (quien pago, cuanto pago y si el que pide la cuenta recibe ficha de aumento o no).

### Finalización de la partida
La partida finaliza cuando un jugador pierde ahorros hasta tener de 0 o menos. 

En ese momento el programa detecta el
final de la partida y busca al jugador con mayor cantidad de ahorros para declararlo ganador. 
En caso de empate, el programa solicitará a los jugadores resolver el desempate, introduciendo el ID del jugador que 
gana el desempate, ya sea por tener una mano valiosa o por cualquier otro criterio acordado entre los jugadores.

Una vez declarado el ganador, el programa mostrara el jugador ganador junto a sus ahorros finales, y posteriormente
imprimira los resultados de la partida, que incluyen:
- El nombre del jugador ganador.
- Rondas jugadas.
- Estadísticas finales de cada jugador (nombre, ahorros y fichas de aumento).

Al finalizar la partida, el programa solicitara al usuario presionar `ENTER` para volver al menu principal, 
donde podrá iniciar una nueva partida o salir del programa.

# FAQ: Problemas comunes y soluciones
- **¿Qué hago si el programa no se ejecuta?**
  - Asegúrate de tener Java Runtime Environment (JRE) instalado en tu sistema y de estar ejecutando el comando en el 
  directorio correcto donde se encuentra el archivo `llevame-la-cuenta-0.1.0.jar`.
- **¿Puedo guardar mi partida para continuarla más tarde?**
  - Actualmente, la opción de guardar y continuar partidas no está disponible, pero se planea implementar esta 
  funcionalidad en futuras versiones del programa.
- **¿Cómo se calculan los ahorros y las fichas de aumento?**
  - Los ahorros se actualizan en cada ronda según el valor de la cuenta y los pagos realizados por los jugadores. 
  Las fichas de aumento se otorgan a los jugadores que pidieron la cuenta y jugaron tantas cartas como jugadores en esa 
  ronda, lo que les permite cambiar cartas en su mano para la siguiente ronda. Si un jugador no jugó tantas cartas 
  como jugadores, no recibe ficha de aumento y debe reemplazar toda su mano.
- **¿Qué pasa si hay un empate al final de la partida?**
  - En caso de empate, el programa solicitará a los jugadores resolver el desempate introduciendo el ID del jugador 
  que gana el desempate, ya sea por tener una mano valiosa o por cualquier otro criterio acordado entre los jugadores. 
  El jugador que gane el desempate será declarado ganador de la partida.
- **¿Puedo jugar con más de 8 jugadores?**
  - No, el programa está diseñado para un máximo de 8 jugadores. Si deseas jugar con más jugadores, te recomendamos 
  dividirlos en grupos y jugar varias partidas.
- **¿Donde puedo consultar las reglas oficiales del juego?**
  - Las reglas oficiales del juego "La Cuenta" se pueden consultar en la pagina oficial de la marca 2Tomatoes Games, 
  en el siguiente enlace: [La Cuenta - Pagina Oficial](https://2tomatoesgames.com/es/la-cuenta-8437027014796.html)

# Contacto y Soporte
Si tienes alguna pregunta, sugerencia o necesitas soporte tecnico, animo a crear un issue en el repositorio del proyecto en GitHub: [Llevame La Cuenta - GitHub](https://github.com/JuanAlberticoHF/Llevame-La-Cuenta)