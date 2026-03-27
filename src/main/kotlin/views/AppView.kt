package com.github.juanalberticohf.views

import com.github.juanalberticohf.console.ConsoleInput
import com.github.juanalberticohf.console.IConsoleInput

/**
 * Vista principal de la aplicacion
 * Gestiona la interaccion con el usuario en el menu principal
 */
class AppView (private val console: IConsoleInput = ConsoleInput()) {
    /** Muestra el mensaje de bienvenida al usuario al iniciar el programa. */
    fun welcomeMessage() {
        println("¡¡BIENVENIDO A LLEVAME LA CUENTA!!")
        println("Este programa te permitira administrar tus ahorros durante la partida. El objetivo es ser el jugador" +
                " con mas ahorros al final de la partida.")
    }

    /**
     * Muestra el menu principal del programa y solicita al usuario que seleccione una opcion.
     * @return Opcion seleccionada por el usuario.
     */
    fun mainMenu(): Int {
        while (true) {
            println("\n[] MENU INICIO")
            println("\t1. Iniciar nueva partida")
            println("\t2. Continuar partida guardada (No Disponible)")
            println("\t3. Ver historial de partidas (No Disponible)")
            println("\t0. Salir")
            print("Selecciona una opcion: ")

            val opcionMenu = console.readInput()?.trim()?.toInt()
            if (opcionMenu != null && opcionMenu in 0..3) {
                return opcionMenu
            }
            println("\nEntrada no válida. Por favor, introduce un número entre 0 y 3.")
        }
    }

    /**
     * Muestra un mensaje indicando que la opcion no esta disponible en la version actual del programa.
     * @param optionNumber Numero de la opcion seleccionada por el usuario.
     */
    fun notAvaiableMessage(optionNumber: Int) {
        println("\nLa opcion $optionNumber no esta disponible en esta version del programa.")
    }

    /** Muestra un mensaje de salida al usuario al finalizar el programa. */
    fun leaveMessage() {
        println("\nGracias por usar Llevame la cuenta. ¡Hasta luego!")
    }
}