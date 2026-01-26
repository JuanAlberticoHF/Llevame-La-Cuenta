package com.github.juanalberticohf

/**
 * Calcula el dinero inicial para cada jugador basado en el numero total de jugadores.
 * @param numPlayers Numero total de jugadores en la partida.
 * @return el ahorro incial de cada jugador.
 */
fun getStartingMoney(numPlayers: Int): Int {
    return 900 + 100 * (numPlayers - 3)
}