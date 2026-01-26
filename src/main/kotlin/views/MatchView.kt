package com.github.juanalberticohf.views

import com.github.juanalberticohf.getStartingMoney

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
            - Todos los jugadores al inicio de cada ronda deberan tener 5 cartas 
              mas el numero de fichas de aumento.
            - Pagara la cuenta quien no pueda jugar carta en su turno o quien 
              la pague voluntariamente.
            - El participante que pague la cuenta se le restara de sus ahorros.
            - El participante que pague la cuenta si se jugaron tantas cartas como 
              el numero de participantes aumentara su mano en +1.
            - Cuando se un participante se quede sin ahorros, la partida termina.
            - Gana el que tenga mas ahorros.
        """.trimIndent())
    }

    /** Solicita al usuario que presione ENTER para iniciar la partida */
    fun requestStartMatch() {
        println("\nPresiona ENTER para iniciar la partida...")
        readln()
    }
}