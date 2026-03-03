package com.github.juanalberticohf.controllers

import com.github.juanalberticohf.models.Match
import com.github.juanalberticohf.models.PlayerStats
import com.github.juanalberticohf.models.parsers.InputRound
import com.github.juanalberticohf.views.RoundView

/**
 * Controlador de ronda
 * La clase RoundController es responsable de gestionar la lógica de una ronda en la partida.
 * Se encarga de interactuar con la vista para mostrar la información de la ronda, solicitar los datos necesarios al
 * usuario y procesar la ronda para actualizar el estado de los jugadores y la partida.
 * @see RoundView
 * @see Match
 * @see PlayerStats
 */
class RoundController (
    val match: Match
) {
    val roundView = RoundView() // Instancia de la vista de ronda

    /**
     * Inicia una nueva ronda en la partida.
     * Muestra la información de la ronda, solicita los datos necesarios al usuario, procesa la ronda y actualiza el
     * estado de los jugadores y la partida.
     */
    fun newRound () {
        // Inicializar el objeto InputRound con la ronda actual y los jugadores
        val inputRound = InputRound(match.rounds.size + 1)

        // Mostrar el numero de ronda, tabla de jugadores con sus estadísticas y la guia de reparto de cartas
        roundView.showRoundInfo(inputRound.roundNumber, match.players)

        // Pausar el programa y solicitar al usuario que pulse ENTER para finalizar la ronda
        roundView.requestEndRound()

        // Mostrar mensaje de fin de ronda
        roundView.showEndOfRound(inputRound.roundNumber)

        // Solicita el monto total de la cuenta
        inputRound.billAmount = roundView.requestBillAmount()

        // Solicita los jugadores que van a pagar la cuenta
        val playersToPay = roundView.requestPlayersToPay(match.players)
        inputRound.billRequester = playersToPay.first()

        // Solicitar si se han jugado suficientes cartas en la ronda
        inputRound.hasSufficientCards = roundView.requestSufficientCards()

        // Solicitamos el metodo de pago estimado para pagar la cuenta
        inputRound.paymentMethod = roundView.requestPaymentMethod()

        // Calcular el monto a pagar por cada jugador que paga la cuenta
        // Si el monto total de la cuenta es negativo, se considera que no hay monto a pagar y se asignara 0 a cada jugador
        val payAmount: Double = if (inputRound.billAmount < 0) 0.0 else (inputRound.billAmount / playersToPay.size).toDouble()

        // Crea los objetos PlayerStats para cada jugador, resta el monto y asigna las fichas de aumento
        // Recorre cada jugador de la partida y gestiona sus estadisticas
        match.players.forEach {
            // Cantidad que paga el jugador (si no paga, es 0)
            val playerPayAmount = if (playersToPay.contains(it.id)) payAmount else 0.toDouble()
            // Fichas de aumento recibidas por el jugador y su incremento si es el solicitante de la cuenta y se han jugado suficientes cartas
            val playerObtainedTokens = if (inputRound.billRequester == it.id && inputRound.hasSufficientCards) it.increaseTokenInOne() else 0
            // Creación del PlayerStats del jugador para la ronda
            val playerStats = PlayerStats(it.id, playerPayAmount, playerObtainedTokens)
            // Restar el monto a pagar del jugador a su ahorro
            if (playerPayAmount > 0) it.subtractSavings(playerPayAmount)
            // Agregar el PlayerStats del jugador a la lista de estadisticas de jugadores de la ronda
            inputRound.playersStats.add(playerStats)
        }

        // Mostrar las estadisticas finales de la ronda antes de finalizarla
        roundView.showRoundStats(inputRound, match.players)

        // Procesar la ronda y obtener el objeto Round con toda la información de la ronda
        val round = inputRound.procesarRonda()

        // Agregar la ronda procesada a la lista de rondas de la partida
        match.rounds.add(round)

        // Verificar si algun jugador se ha quedado sin ahorros (0 o menos) o proseguir con la partida
        if (match.players.any { it.currentSaves <= 0 }) {
            match.isMatchEnded = true
        } else {
            // Pausar el programa y solicitar al usuario que pulse ENTER para avanzar a la siguiente ronda
            roundView.requestNextRound()
        }
    }
}