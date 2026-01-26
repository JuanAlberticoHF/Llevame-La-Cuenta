package com.github.juanalberticohf.models

import com.github.juanalberticohf.models.enums.PaymentMethod

/**
 * La clase Round representa todos los datos de una ronda jugada en la partida.
 * @param roundNumber Número de ronda en la partida.
 * @param billRequester Identificador del jugador que solicita la cuenta.
 * @param billAmount Importe total de la cuenta solicitada.
 * @param paymentMethod Métod0 de pago utilizado en la ronda.
 * @param hasSufficientCards Indica se han jugado tantas cartas como jugadores hay en la partida.
 * @param playersStats Lista de estadísticas de los jugadores en la ronda.
 */
data class Round (
    val roundNumber: Int,
    val billRequester: Int,
    val billAmount: Int,
    val paymentMethod: PaymentMethod,
    val hasSufficientCards: Boolean,
    val playersStats: List<PlayerStats>
)

// val lowestCardValue: Int // Valor de la carta más baja jugada en la ronda