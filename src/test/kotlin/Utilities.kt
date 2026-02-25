import com.github.juanalberticohf.getPlayersTable
import com.github.juanalberticohf.getStartingMoney
import com.github.juanalberticohf.models.Player
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Utilities {
    @Test
    fun getStartingMoney() {
        assertEquals(900, getStartingMoney(3))
        assertEquals(1000, getStartingMoney(4))
        assertEquals(1100, getStartingMoney(5))
        assertEquals(1200, getStartingMoney(6))
        assertEquals(1300, getStartingMoney(7))
        assertEquals(1400, getStartingMoney(8))
    }

    @Test
    fun getPlayersTable() {
        val tablaResultado = """
-----------------------------------------
| JUGADORES   |   AHORROS |   FICHAS A. |
-----------------------------------------
| Juan        |      900€ |           0 |
| Noemi       |      900€ |           0 |
| Alejandro   |      900€ |           0 |
-----------------------------------------
        """.trimIndent()

        val players = listOf(
            Player(1, "Juan", 900),
            Player(2, "Noemi", 900),
            Player(3, "Alejandro", 900)
        )
        assertEquals(tablaResultado, getPlayersTable(players) )}
}