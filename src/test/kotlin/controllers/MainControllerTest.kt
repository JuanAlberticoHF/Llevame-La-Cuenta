package controllers

import com.github.juanalberticohf.controllers.IMatchController
import com.github.juanalberticohf.controllers.MainController
import com.github.juanalberticohf.views.IAppView
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.invoke

class MainControllerTest {
    @Test
    fun testMainController() {
        // Preparación
        val mockAppView = mockk<IAppView>() // Mock AppView
        val mockMatchController = mockk<IMatchController>() // Mock MatchController

        val mainController = spyk(MainController(mockAppView, mockMatchController), recordPrivateCalls = true) // Objeto MainController Espiado
        val opciones = listOf(3,2,1,0)

        every {mockAppView.welcomeMessage()} just Runs
        every {mockAppView.notAvaiableMessage(any())} just Runs
        every {mockAppView.mainMenu()} returnsMany opciones
        every {mockAppView.leaveMessage()} just Runs

        every { mockMatchController.matchStart() } just Runs

        // Ejecución
        mainController.startApp()

        // Verificación
        verify(exactly = 4) { mainController invoke "handleOption" withArguments listOf(any<Int>()) }
        verify(exactly = 1) { mockAppView.welcomeMessage() }
        verify(exactly = 1) {
            mockAppView.notAvaiableMessage(3)
            mockAppView.notAvaiableMessage(2)
        }
        verify(exactly = 1) { mockMatchController.matchStart() }
        verify(exactly = opciones.size) { mockAppView.mainMenu() }
        verify(exactly = 1) { mockAppView.leaveMessage() }
    }
}