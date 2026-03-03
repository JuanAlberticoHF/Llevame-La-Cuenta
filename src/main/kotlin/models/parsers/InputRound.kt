package com.github.juanalberticohf.models.parsers

import com.github.juanalberticohf.models.PlayerStats
import com.github.juanalberticohf.models.enums.PaymentMethod
import com.github.juanalberticohf.models.Round

data class InputRound (
    val roundNumber: Int,
) {
    var billRequester: Int = -1
        set(value) {
            if (value in 1..8)
                field = value
            else
                throw IllegalArgumentException("El valor de billRequester debe estar entre 1 y 8")
        }
    var billAmount: Int = -1
    var paymentMethod: PaymentMethod = PaymentMethod.SINGLE_PAYER
    var hasSufficientCards: Boolean = false
    val playersStats: MutableList<PlayerStats> = mutableListOf()

    // FUNCIONES //
    fun procesarRonda() : Round {
        return Round(
            roundNumber,
            billRequester,
            billAmount,
            paymentMethod,
            hasSufficientCards,
            playersStats
        )
    }
}

//    var lowestCardValue: Int = 0
//        set(value) {
//            if (value >= 0)
//                field = value
//            else
//                throw IllegalArgumentException("El valor de lowestCardValue no puede ser negativo")
//        }