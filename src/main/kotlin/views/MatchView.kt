package com.github.juanalberticohf.views

import com.github.juanalberticohf.getStartingMoney
import com.github.juanalberticohf.models.Match
import com.github.juanalberticohf.models.Player

/**
 * Vista de la partida
 * Gestiona la interaccion con el usuario para inicializar la partida
 */
class MatchView {
    /** Muestra un mensaje indicando el inicio de una nueva partida */
    fun startMatchMessage() {
        println("\n[] Iniciando nueva partida")
    }

    /**
     * Solicita el numero de jugadores y sus nombres
     * @return Lista con los nombres de los jugadores
     */
    fun requestPlayers(): List<String> {
        // Introducir el numero de jugadores
        print("\nIntroduce el numero de jugadores (3-8): ")

        // TODO validar input
        val input = readln().toInt()

        // Introducir los nombres de los jugadores
        val playerNameList = mutableListOf<String>()

        println("\nIntroduce el nombre de los $input jugadores: ")
        for (i in 1..input) {
            print("Nombre del jugador $i: ")
            var playerName = readln()

            if (playerName.isEmpty()) {
                playerName = "Jugador $i"
            }

            playerNameList.add(playerName)
        }

        return playerNameList
    }

    /**
     * Muestra la tabla de ahorros iniciales segun el numero de jugadores
     * @param numPlayers Numero de jugadores en la partida
     */
    fun showPlayersBill(numPlayers: Int) {
        println("")
        println("""
            Cada jugador empieza la partida con unos ahorros:
            ------------------------------------------------------------------
            | JUGADORES |   3   |    4   |    5   |    6   |    7   |    8   |
            ------------------------------------------------------------------
            | AHORROS   |  900€ | 1.000€ | 1.100€ | 1.200€ | 1.300€ | 1.400€ |
            ------------------------------------------------------------------
        """.trimIndent())
        println("Al ser $numPlayers jugadores, cada uno empezara la partida con ${getStartingMoney(numPlayers)}€ de ahorros.")
    }

    /** Muestra las reglas basicas del juego */
    fun showRules() {
        println("")
        println("""
            Reglas basicas del juego:
            - Todos los jugadores al inicio de cada ronda deberan tener 5 cartas mas el numero de fichas de aumento.
            - Pagara la cuenta quien no pueda jugar carta en su turno o quien a pague voluntariamente.
            - El jugador que pague la cuenta se le restara de sus ahorros.
            - El jugador que pague la cuenta si se jugaron tantas cartas como el numero de jugadores aumentara su mano en +1.
            - Cuando un jugador se quede sin ahorros, la partida termina.
            - Gana el que tenga mas ahorros.
        """.trimIndent())
    }

    /** Solicita al usuario que presione ENTER para iniciar la partida */
    fun requestStartMatch() {
        print("\nPresiona ENTER para iniciar la partida...")
        readln()
    }

    /**
     * Muestra un mensaje indicando el fin de la partida, el jugador que se ha quedado sin ahorros y su valor, y que se
     * comparan los ahorros de los jugadores restantes para determinar el ganador.
     * @param playerWithoutSaves El jugador que se ha quedado sin ahorros al finalizar la partida.
     */
    fun showEndOfMatch(playerWithoutSaves: Player) {
        println("\n[] FIN DE LA PARTIDA")
        println("El jugador ${playerWithoutSaves.name} se ha quedado sin ahorros con un valor de ${playerWithoutSaves.currentSaves}€.")
        println("Para determinar el ganador se comparan los ahorros de los jugadores restantes.")
    }

    /** Solicita al usuario que presione ENTER para finalizar la partida y declarar al ganador */
    fun requestEndOfMatch() {
        print("\nPresiona ENTER para finalizar la partida y declarar el ganador...")
        readln()
    }

    /**
     * Muestra un mensaje indicando el ganador de la partida, su nombre y su cantidad de ahorros finales.
     * @param winner El jugador ganador de la partida.
     */
    fun showWinner(winner: Player) {
        println("\n[] GANADOR")
        println("${winner.name} ha ganado la partida con ${winner.currentSaves}€ ahorrados. ¡¡FELICIDADES!!")
    }

    /**
     * Muestra un mensaje indicando que hay una disputa por el ganador entre los jugadores con la misma cantidad de
     * ahorros máximos, sus nombres y su cantidad de ahorros.
     * Solicita al usuario que seleccione el ganador entre los jugadores empatados, indicando que se debe determinar el
     * ganador por la cantidad de dinero en mano o por consenso si también tienen la misma cantidad de dinero en mano.
     * @param winners Lista de jugadores empatados con la misma cantidad de ahorros máximos al finalizar la partida.
     * @return El jugador seleccionado como ganador entre los jugadores empatados.
     */
    fun showAndRequestWinner(winners: List<Player>): Player {
        println("\n[] DISPUTA POR EL GANADOR")
        print("El jugador ")
        winners.forEach {
            when (it) {
                winners.first() -> {
                    print("${it.name} ")
                }
                winners.last() -> {
                    print("y ${it.name} ")
                }
                else -> {
                    print(",${it.name} ")
                }
            }
        }
        println("tienen la maxima cantidad de ahorros (${winners.first().currentSaves}€).")
        println("- Para el desempate se debe determinar cual jugador tiene mas dinero en su mano para elegirlo ganador.")
        println("- Si los jugadores tambien tienen la misma cantidad de dinero en mano, los mismos jugadores decidiran el ganador por consenso.")
        winners.forEach {
            println("\t${it.id}. ${it.name}")
        }
        print("Selecciona el ganador (n): ")
        // TODO verificar que los ids introducidos son validos
        val idsInputs = readln().toInt()
        val winner = winners.find { it.id == idsInputs }
        return winner!!
    }

    /** Solicita al usuario que presione ENTER para volver al menu principal tras finalizar la partida y mostrar el ganador. */
    fun requestGoToMenu() {
        print("\nPresiona ENTER para volver al menu principal...")
        readln()
    }

    /**
     * Muestra los resultados finales de la partida.
     * - Nombre del ganador
     * - El número de rondas jugadas
     * - Las estadísticas finales de cada jugador (nombre, ahorros finales y fichas de aumento obtenidas).
     */
    fun matchResults(match: Match) {
        println("\n[] RESULTADOS DE LA PARTIDA")
        println("- Ganador: ${match.players.find { it.isWinner}?.name}")
        println("- Rondas jugadas: ${match.rounds.size}.")
        match.players.forEach {
            println("\t${it.name}: ${it.currentSaves}€ - ${it.increaseTokens} Fichas A.")
        }
    }
}