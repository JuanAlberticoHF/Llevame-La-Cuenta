package com.github.juanalberticohf.controllers

import com.github.juanalberticohf.views.AppView

/**
 * Controlador principal de la aplicacion
 * Gestiona el flujo principal del programa
 */
class MainController {
    val appView = AppView()

    /**
     * Inicia la aplicación mostrando un mensaje de bienvenida y el menu principal.
     * Gestiona las opciones del usuario.
     */
    fun startApp() {
        appView.welcomeMessage() // Vista de bienvenida

        var looping: Boolean = true
        while(looping) {
            val userOption = appView.mainMenu() // Mostrar vista menu principal y obtener opcion

            when(userOption) {
                1 -> { // Crea el controlador de la partida y comienza una nueva partida
                    val matchController = MatchController()
                    matchController.matchStart()
                }
                0 -> { // Salir del programa
                    looping = false
                    appView.leaveMessage()
                }
                else -> {
                    appView.notAvaiableMessage(userOption)
                }
            }
        }

    }
}