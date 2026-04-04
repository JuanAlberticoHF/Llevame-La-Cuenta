package com.github.juanalberticohf.models

/**
* La clase Player sirve para modelar y guardar los datos de un
* jugador durante la partida.
* @param id Identificador del jugador en la partida
* @param name Nombre del jugador en la partida
* @param initialSaves Ahorros iniciales asignados
* @param currentSaves Los ahorros gestionados durante la partida
* @param increaseTokens Fichas de aumento del jugador (maximo: 5)
* @param isWinner Indica si el jugador es el ganador de la partida
* */
data class Player (
    val id: Int,
    val name: String,
    val initialSaves: Int,
) {
    var currentSaves: Double = initialSaves.toDouble()
        private set
    var increaseTokens = 0
    var isWinner: Boolean = false

    /**
     * Permite restar una cantidad a los ahorros del jugador y devolver el estado de ahorros.
     * @param amount Cantidad a restar a los ahorros del jugador
     * @return ahorros actuales tras la resta
     */
    fun subtractSavings (amount: Double): Double {
        currentSaves -= amount
        return currentSaves
    }

    /**
     * Permite aumentar el número de fichas de aumento del jugador, con un máximo de 5.
     * @return 1 si se ha podido aumentar la ficha, 0 si ya se han alcanzado las 5 fichas de aumento
     */
    fun increaseTokenInOne(): Int {
        if (increaseTokens < 5) {
            ++increaseTokens
            return 1
        } else {
            return 0
        }
    }

    override fun toString(): String {
        return "Player(id=$id, name='$name', initialSaves=$initialSaves, currentSaves=$currentSaves, increaseTokens=$increaseTokens, isWinner=$isWinner)"
    }
}