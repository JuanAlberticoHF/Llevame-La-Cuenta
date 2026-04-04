package com.github.juanalberticohf.models

/**
 * La clase PlayerStats representa las estadísticas de un jugador en una ronda.
 * @param playerId Identificador del jugador.
 * @param payAmount Cantidad a pagar en la ronda (si aplica).
 * @param obtainedTokens Numero de fichas obtenidas por el jugador en la ronda (si aplica).
 */
data class PlayerStats(
    val playerId: Int,
    val payAmount: Double,
    val obtainedTokens: Int,
//    val status: PlayerState, // Estado del jugador al finalizar la ronda.
//    val bathroomCount: Int, // Número de veces que el jugador ha ido al baño.
//    val tips: Int, // Propinas dadas por el jugador después de pedir la cuenta.
)