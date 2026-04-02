package com.github.juanalberticohf.controllers

import com.github.juanalberticohf.getStartingMoney
import com.github.juanalberticohf.models.Match
import com.github.juanalberticohf.models.Player
import com.github.juanalberticohf.views.MatchView

/**
 * Controlador de la partida.
 * Gestiona el flujo de la partida, desde la obtención de datos iniciales hasta la finalización de la partida mostrando el resultado final y el ganador.
 * Utiliza la vista de la partida para interactuar con el usuario y mostrar la información relevante durante la partida.
 * Crea una instancia de Match con los jugadores y un controlador de ronda para gestionar las rondas de la partida.
 * El bucle principal de la partida se ejecuta mientras la partida no haya finalizado, iniciando nuevas rondas.
 * Una vez finalizada la partida se llama al mét0do matchEnd para mostrar el resultado final y el ganador.
 * @see MatchView
 * @see Match
 * @see Player
 */
class MatchController (val matchView: MatchView = MatchView()): IMatchController {

    /**
     * Inicia una nueva partida solicitando los nombres de los jugadores,
     * calculando el dinero inicial y mostrando la tabla de ahorros.
     * Crea una instancia de Match con los jugadores.
     */
    override fun matchStart() {
        matchView.startMatchMessage()

        // OBTENER DATOS DE LA PARTIDA
        val playerNameList = matchView.requestPlayers() // Listado de nombres de los jugadores
        val startingMoney = getStartingMoney(playerNameList.size) // Dinero inicial
        val playerList = getPlayerList(playerNameList, startingMoney) // Listado de jugadores

        // MOSTRAR DATOS INICIALES DE LA PARTIDA
        matchView.showPlayersBill(playerList.size) // Mostrar la tabla de ahorros iniciales
        matchView.showRules() // Mostrar reglas basicas del juego
        matchView.requestStartMatch() // Solicitar iniciar la partida

        matchInProgress(playerList) // Iniciar la partida
    }

    /**
     * Gestiona el flujo principal de la partida, creando una instancia de Match con los jugadores
     * y un controlador de ronda para gestionar las rondas de la partida.
     * El bucle principal de la partida se ejecuta mientras la partida no haya finalizado, iniciando nuevas rondas.
     * Una vez finalizada la partida, se llama al mét0do matchEnd para mostrar el resultado final y el ganador.
     * @param playerList Lista de objetos Player que participan en la partida
     */
    override fun matchInProgress(playerList: List<Player>) {
        val match = Match(playerList) // Creación de la partida con los jugadores

        val roundController = RoundController(match) // Creación del controlador de ronda con la partida

        // Bucle principal de la partida, se ejecuta mientras la partida no haya finalizado
        while (!match.isMatchEnded) {
            roundController.newRound() // Iniciar la primera o siguiente ronda
        }

        matchEnd(match) // Finalizar la partida
    }

    /**
     * Gestiona el flujo de finalización de la partida, mostrando el resultado final y el ganador.
     * Busca el jugador sin ahorros para mostrar un mensaje de fin de partida,
     * calcula el jugador ganador o solicita el desempate en caso y finalizar la partida finalmente.
     * @param match Instancia de Match con el estado final de la partida
     */
    override fun matchEnd(match: Match) {
        // Muestra un mensaje indicando el fin de la partida
        val playerWithoutSaves = match.players.find { it.currentSaves <= 0 }!! // Buscar un jugador sin ahorros
        matchView.showEndOfMatch(playerWithoutSaves)
        // Solicitar al usuario que pulse ENTER para finalizar la partida
        matchView.requestEndOfMatch()
        // Calcular el jugador ganador o solicitar el desempate en caso de empate
        val maxSavings = match.players.maxOf { it.currentSaves } // Ahorros maximos entre los jugadores
        val winners: List<Player> = match.players.filter { it.currentSaves == maxSavings } // Lista de jugadores con los ahorros maximos (puede haber empates)

        if (winners.size == 1) { // Si hay un solo ganador, mostrar su nombre y ahorros finales
            val winner = winners.first()
            winner.isWinner = true
            matchView.showWinner(winner)
        } else if (winners.size > 1) {// Si hay varios ganadores se solicita el desempate.
            val winner = matchView.showAndRequestWinner(winners)
            winner.isWinner = true
            matchView.showWinner(winner)
        } else {
            throw Exception("Error al calcular el ganador de la partida.")
        }

        // Muestra las estadisticas finales de la partida
        matchView.matchResults(match)

        // Solicitar al usuario que pulse ENTER volver al menu principal
        matchView.requestGoToMenu()
    }

    /**
     * Genera una lista de objetos Player a partir de una lista de nombres y el dinero inicial.
     * @param playerNameList Lista de nombres de los jugadores.
     * @param startingMoney Dinero inicial para cada jugador.
     * @return Lista de objetos Player.
     */
    override fun getPlayerList(playerNameList: List<String>, startingMoney: Int): List<Player> {
        val playerList = mutableListOf<Player>()

        for (i in playerNameList.indices) {
            val player = Player (
                i+1,
                playerNameList[i],
                startingMoney
            )
            playerList.add(player)
        }

        return playerList
    }
}