package views

import com.github.juanalberticohf.console.IConsoleInput
import com.github.juanalberticohf.models.Player
import com.github.juanalberticohf.views.MatchView
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MatchViewTest {
    @Test
    fun requestPlayers() {
        // Preparar
        // Inputs: número de jugadores = 3, nombre: Juan, Vacio (por defecto), Charlie
        val inputs: List<String> = listOf("3", "Alice", "", "Charlie")
        val consoleInput = mockk<IConsoleInput>()
        every {consoleInput.readInput() } returnsMany inputs
        val view = MatchView(consoleInput)

        // Ejecutar
        val players = view.requestPlayers()

        // Comprobar
        assertEquals(3, players.size)
        assertEquals("Alice", players[0])
        assertEquals("Jugador 2", players[1])
        assertEquals("Charlie", players[2])
    }

    @Test
    fun showAndRequestWinner() {
        // Create three players; method expects a list of tied winners
        val p1 = Player(1, "A", 1000)
        val p2 = Player(2, "B", 1000)
        val p3 = Player(3, "C", 1000)

        // First input invalid, second selects player with id 2
        val inputs: List<String> = listOf("99", "2")
        val consoleInput = mockk<IConsoleInput>()
        every {consoleInput.readInput() } returnsMany inputs
        val view = MatchView(consoleInput)

        val winner = view.showAndRequestWinner(listOf(p1, p2, p3))
        assertNotNull(winner)
        assertEquals(2, winner.id)
    }

}