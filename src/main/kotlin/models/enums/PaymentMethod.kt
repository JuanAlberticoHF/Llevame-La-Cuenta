package com.github.juanalberticohf.models.enums

/**
 * Establece 3 tipos de metodos de pago al pagar una cuenta.
 * Almacena 3 pagos y un metodo para obtener el pago base a un String
 */
enum class PaymentMethod {
    SINGLE_PAYER, FIFTY_FIFTY, GO_DUTCH;

    /**
     * Permite introducir un String para obtener un PaymentMethod
     * @param value metodo de pago en String
     * @return devuelve el PaymentMethod correspondiente
     */
    fun valueOfString (value: String): PaymentMethod {
        return when (value.uppercase()) {
            "SINGLE_PAYER" -> SINGLE_PAYER
            "FIFTY_FIFTY" -> FIFTY_FIFTY
            "GO_DUTCH" -> GO_DUTCH
            else -> throw IllegalArgumentException("El valor de paymentMethod no es válido")
        }
    }
}