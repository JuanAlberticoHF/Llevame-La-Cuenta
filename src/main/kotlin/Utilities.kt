package com.github.juanalberticohf

import com.github.juanalberticohf.models.Player
import java.text.DecimalFormat

/**
 * Calcula el dinero inicial para cada jugador basado en el numero total de jugadores.
 * @param numPlayers Numero total de jugadores en la partida.
 * @return el ahorro incial de cada jugador.
 */
fun getStartingMoney(numPlayers: Int): Int {
    return 900 + 100 * (numPlayers - 3)
}

/**
 * Construye y devuelve una tabla con las columnas: JUGADORES | AHORROS | FICHAS A.
 * La columna de nombres adapta su ancho al nombre más largo para mantener coherencia visual.
 *
 * @param players Lista de objetos Player que contiene la información de cada jugador.
 * @return Una cadena formateada que representa la tabla de jugadores, ahorros y fich
 */
fun getPlayersTable(players: List<Player>): String {
    val sb = StringBuilder()

    if (players.isEmpty()) {
        sb.append("(Sin jugadores)\n")
        return sb.toString()
    }

    val nameHeader = "JUGADORES"
    val savingsHeader = "AHORROS"
    val tokensHeader = "FICHAS A."

    val savingsFmt = DecimalFormat("0") // Usar formato sin separador de miles: 1400, 1200, 900

    val maxNameLength = players.maxOf { it.name.length }
    val nameColWidth = maxOf(nameHeader.length, maxNameLength) + 2

    val maxSavingsLength = players.maxOf { savingsFmt.format(it.currentSaves).length + 1 } // +1 para €
    val savingsColWidth = maxOf(savingsHeader.length, maxSavingsLength) + 2

    val maxTokensLength = players.maxOf { it.increaseTokens.toString().length }
    val tokensColWidth = maxOf(tokensHeader.length, maxTokensLength) + 2

    val headerFormat = "| %-${nameColWidth}s | %${savingsColWidth}s | %${tokensColWidth}s |"
    val rowFormat = "| %-${nameColWidth}s | %${savingsColWidth}s | %${tokensColWidth}s |"

    val headerLine = headerFormat.format(nameHeader, savingsHeader, tokensHeader)
    val separator = "-".repeat(headerLine.length)

    sb.append(separator).append('\n')
    sb.append(headerLine).append('\n')
    sb.append(separator).append('\n')

    for (p in players) {
        val savingsStr = savingsFmt.format(p.currentSaves) + "€"
        val tokensStr = p.increaseTokens.toString()
        sb.append(rowFormat.format(p.name, savingsStr, tokensStr)).append('\n')
    }

    sb.append(separator)
    return sb.toString()
}