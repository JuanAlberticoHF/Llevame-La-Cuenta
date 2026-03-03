package com.github.juanalberticohf.views

import com.github.juanalberticohf.calculateBill
import com.github.juanalberticohf.models.Player
import com.github.juanalberticohf.getPlayersTable
import com.github.juanalberticohf.models.enums.PaymentMethod
import com.github.juanalberticohf.models.parsers.InputRound

class RoundView {
    /**
     * Muestra la información de la ronda actual, incluyendo el número de ronda, las estadísticas de los jugadores y la guía de reparto de cartas.
     * @param roundNumber El número de la ronda actual.
     * @param playerList La lista de jugadores con sus estadísticas para mostrar en la tabla.
     */
    fun showRoundInfo(roundNumber: Int, playerList: List<Player>) {
        // Mostrar el numero de ronda con un marco decorativo
        val roundFrame = "============="+"=".repeat(roundNumber / 10)
        println("\n"+roundFrame)
        println("[] RONDA $roundNumber []")
        println(roundFrame)

        // Mostrar la tabla de jugadores con sus estadísticas
        println("\n[] ESTADISTICAS")
        println(getPlayersTable(playerList))

        // Mostrar la guia de reparto de cartas
        println("\n[] REPARTO: ")
        playerList.forEach {
            println("${it.name} recibe hasta ${5+it.increaseTokens} cartas.")
        }
    }

    /**
     * Solicita al usuario que introduzca el calculo del monto total de la cuenta.
      * @return El monto total de la cuenta introducido por el usuario
     */
    fun requestBillAmount(): Int {
        while (true) {
            print("\nIntroduce el calculo del precio total de la cuenta: ")
            val input = readlnOrNull()?.trim()
            if (input != null) {
                val billAmount = calculateBill(input)
                if (billAmount == -1) {
                    println("Entrada no válida. Por favor, introduce un cálculo válido para el monto total de la cuenta.")
                    continue
                }
                println("Monto calculado: $billAmount€.")
                return billAmount
            }
        }
    }

    /**
     * Solicita al usuario que seleccione los jugadores que van a pagar la cuenta
     * @param players Lista de jugadores disponibles para seleccionar como pagadores
     * @return Lista de identificadores de los jugadores seleccionados como pagadores
     */
    fun requestPlayersToPay(players: List<Player>): List<Int> {
        println("\n¿Que participantes pagan la cuenta? (primero quien pidio la cuenta)")
        players.forEach {
            println("\t${it.id}. ${it.name}")
        }
        while (true) {
            print("Selecciona uno o varios (1,2,...): ")
            val idsInputs = readln()
            // Convertir la entrada en una lista de enteros, ignorando espacios y validando que sean numeros
            val idsList = idsInputs.split(",").map { it.trim().toIntOrNull() }
            // Todos los ids introducidos deben ser validos y corresponder a jugadores existentes
            if (idsList.all { id -> players.any { it.id == id } }) {
                return idsList.filterNotNull() // Devolver solo los ids validos eliminando los nulls
            }
            println("\nEntrada no válida. Por favor, introduce los números de los jugadores separados por comas.")
        }
    }

    /**
     * Solicita al usuario que confirme si se han jugado suficientes cartas para la ronda
     * @return **true** si el usuario confirma que se han jugado suficientes cartas, **false** en caso contrario
     */
    fun requestSufficientCards(): Boolean {
        print("\n¿Se han jugado suficientes cartas? (S/N): ")
        val input = readln().uppercase()
        when (input) {
            "S" -> {
                return true
            }
            "N" -> {
                return false
            }
            else -> {
                println("Entrada no válida. Por favor, introduce 'S' o 'N'.")
                return requestSufficientCards() // Volver a solicitar la entrada si es inválida
            }
        }
    }

    /**
     * Solicita al usuario que seleccione el mét0do de pago utilizado para pagar la cuenta.
     * @return El mét0do de pago seleccionado por el usuario como un valor del enum PaymentMethod
     */
    fun requestPaymentMethod(): PaymentMethod {
        // ToDo: Implementar un control para solo mostrar los metodos de pago realmente disponibles, si una sola persona
        //  paga la cuenta no puede poder seleccionar otro metodo de pago que no sea UN PAGADOR, si hay varios pagadores
        //  no se puede seleccionar UN PAGADOR.
        println("\n¿Que metodo de pago se ha utilizado?: ")
        println("\t1. UN PAGADOR")
        println("\t2. A MEDIAS")
        println("\t3. A PACHAS")
        while (true) {
            print("Selecciona una opcion (1-3): ")
            val input = readlnOrNull()?.trim()?.toIntOrNull()

            if (input != null && input in 1..3) {
                when (input) {
                    1 -> { return PaymentMethod.SINGLE_PAYER }
                    2 -> { return PaymentMethod.FIFTY_FIFTY }
                    3 -> { return PaymentMethod.GO_DUTCH }
                }
            }

            println("\nEntrada no válida. Por favor, introduce un número entre 1 y 3.")
        }
    }

    /**
     * Muestra las estadísticas finales de la ronda, incluye:
     * - El nombre del jugador que solicitó la cuenta
     * - El monto total de la cuenta
     * - El métod0 de pago utilizado
     * - Los pagos realizados por cada jugador.
     * @param inputRound El objeto InputRound que contiene toda la información de la ronda actual, incluyendo las estadísticas de los jugadores.
     * @param playerList La lista de jugadores para obtener los nombres de los jugadores a partir de sus identificadores en las estadísticas de la ronda.
     */
    fun showRoundStats(inputRound: InputRound, playerList: List<Player>) {
        println("\n[] RESULTADOS DE LA RONDA")
        println("- ${playerList.find { it.id == inputRound.billRequester}?.name} pidio la cuenta.")
        println("- Valor total de la cuenta: ${inputRound.billAmount}€.")
        println("- Metodo de pago utilizado: ${inputRound.paymentMethod}.")
        println("- Pagos realizados por cada jugador:")
        inputRound.playersStats.forEach {
            if (it.payAmount > 0 && it.playerId == inputRound.billRequester) {
                println("\t- ${playerList.find { player -> player.id == it.playerId }?.name}: -${it.payAmount}€ +" +
                        if(it.obtainedTokens > 0) "1 ficha de aumento" else " no recibe ficha de aumento"
                )
            } else if (it.payAmount > 0) {
                println("\t- ${playerList.find { player -> player.id == it.playerId }?.name}: -${it.payAmount}€.")
            }
        }
    }

    /** Solicita al usuario que presione ENTER para finalizar la ronda */
    fun requestEndRound() {
        print("\nPresiona ENTER para finalizar la ronda...")
        readln()
    }

    /** Solicita al usuario que presione ENTER para avanzar a la siguiente ronda */
    fun requestNextRound() {
        print("\nPresiona ENTER para avanzar a la siguiente ronda...")
        readln()
    }

    /**
     * Muestra un mensaje indicando el fin de la ronda, el número de ronda que ha finalizado y las condiciones para
     * pagar la cuenta en la siguiente ronda.
     * @param roundNumber El número de la ronda que ha finalizado.
     */
    fun showEndOfRound(roundNumber: Int) {
        println("""
            
            ¡¡LA RONDA $roundNumber HA FINALIZADO!!
            - Quien haya utilizado la carta de cumpleaños no puede pagar
            - Quien en este en el baño no puede pagar
        """.trimIndent())
    }
}