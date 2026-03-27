package views

import com.github.juanalberticohf.console.ConsoleInput
import com.github.juanalberticohf.views.AppView
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class AppViewTest {
    @ParameterizedTest
    @CsvSource(value = [
        "1, 1",
        "2, 2",
        "3, 3",
        "0, 0"
    ])
    fun mainMenu(
        opcionUsuario: String,
        opcionEsperada: Int
    ) {
        // Preparar
        val mockkConsole = mockk<ConsoleInput>()
        every { mockkConsole.readInput() } returns opcionUsuario
        val appView = AppView(mockkConsole)
        // Ejecutar
        val resultado = appView.mainMenu()
        // Comprobar
        assertEquals(opcionEsperada, resultado)
    }
}