package com.github.juanalberticohf.models.enums

/**
 * Establece 3 tipos de metodos de pago al pagar una cuenta.
 * Almacena 3 pagos y un metodo para obtener el pago base a un String
 */
enum class PaymentMethod {
    SINGLE_PAYER, FIFTY_FIFTY, GO_DUTCH;

    override fun toString(): String {
        return when (this) {
            SINGLE_PAYER -> "UN PAGADOR"
            FIFTY_FIFTY -> "A MEDIAS"
            GO_DUTCH -> "A PACHAS"
        }
    }
}