package com.github.juanalberticohf.controllers

import com.github.juanalberticohf.views.AppView
import com.github.juanalberticohf.views.IAppView

/**
 * Controlador principal de la aplicacion
 * Gestiona el flujo principal del programa
 */
class MainController (
    val appView: IAppView = AppView(),
    private val matchController: IMatchController = MatchController()
) {

    /**
     * Inicia la aplicación mostrando un mensaje de bienvenida y el menu principal.
     * Gestiona las opciones del usuario.
     */
    fun startApp() {
        appView.welcomeMessage() // Vista de bienvenida

        while(true) {
            // Mostrar vista menu principal y obtener opcion
            val option = appView.mainMenu()

            handleOption(option)

            if (option == 0) break
        }

    }

    /**
     * Gestiona las opciones seleccionadas por el usuario en el menu principal.
     * @param option Opcion seleccionada por el usuario.
     */
    private fun handleOption(option: Int) {
        when(option) {
            1 -> { matchController.matchStart() } // Crea el controlador de la partida y comienza una nueva partida
            0 -> {
                appView.leaveMessage()
            } // Salir del programa
            else -> { appView.notAvaiableMessage(option) }
        }
    }
}