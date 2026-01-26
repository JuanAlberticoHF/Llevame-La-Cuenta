package com.github.juanalberticohf.controllers

import com.github.juanalberticohf.getStartingMoney
import com.github.juanalberticohf.models.Match
import com.github.juanalberticohf.models.Player
import com.github.juanalberticohf.views.MatchView

/**
 * Controlador de la partida.
 * Gestiona el flujo de la partida, desde la obtención de datos iniciales
 * hasta la creación de la instancia de Match.
 */
class MatchController {
    val matchView = MatchView() // Instancia de la vista de la partida

    /**
     * Inicia una nueva partida solicitando los nombres de los jugadores,
     * calculando el dinero inicial y mostrando la tabla de ahorros.
     * Crea una instancia de Match con los jugadores.
     */
    fun matchStart() {
        matchView.startMatchMessage()

        // OBTENER DATOS DE LA PARTIDA
        val playerNameList = matchView.requestPlayers() // Listado de nombres de los jugadores
        val startingMoney = getStartingMoney(playerNameList.size) // Dinero inicial
        val playerList = getPlayerList(playerNameList, startingMoney) // Listado de jugadores

        // MOSTRAR DATOS INICIALES DE LA PARTIDA
        matchView.showPlayersBill(playerList.size) // Mostrar la tabla de ahorros iniciales
        matchView.showRules() // Mostrar reglas basicas del juego
        matchView.requestStartMatch() // Solicitar iniciar la partida

        val match = Match(playerList) // Creación de la partida con los jugadores
    }

    /**
     * Genera una lista de objetos Player a partir de una lista de nombres y el dinero inicial.
     * @param playerNameList Lista de nombres de los jugadores.
     * @param startingMoney Dinero inicial para cada jugador.
     * @return Lista de objetos Player.
     */
    fun getPlayerList(playerNameList: List<String>, startingMoney: Int): List<Player> {
        val playerList = mutableListOf<Player>()

        for (i in playerNameList.indices) {
            val player = Player (
                i,
                playerNameList[i],
                startingMoney
            )
            playerList.add(player)
        }

        return playerList
    }
}