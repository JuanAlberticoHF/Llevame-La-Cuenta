package com.github.juanalberticohf.models

/**
 * La clase Match representa todos los datos de una partida.
 * @param players Lista de participantes en la partida.
 * @param isMatchEnded Indica si la partida ha finalizado.
 * @param rounds Lista de rondas jugadas en la partida.
 */
data class Match(
    val players: List<Player>,
    val rounds : List<Round> = mutableListOf(),
    val isMatchEnded: Boolean = false
)