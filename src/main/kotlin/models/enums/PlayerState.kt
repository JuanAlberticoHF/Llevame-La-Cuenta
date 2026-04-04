package com.github.juanalberticohf.models.enums

/**
 * Estados posibles de un jugador al finalizar una ronda.
 * - `PAYER`: Jugador que debe pagar.
 * - `NON_PAYER`: Jugador que no ha pagado.
 */
enum class PlayerState {
    PAYER,
    NON_PAYER,
    ELIGIBLE, // FUTURO -> Jugador elegible para pagar
    BATHROOM, // FUTURO -> Jugador que fue al baño.
    BIRTHDAY // FUTURO -> Jugador que es el cumpleañero.
}