package views

import com.github.juanalberticohf.console.IConsoleInput
import com.github.juanalberticohf.models.Player
import com.github.juanalberticohf.views.RoundView
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class RoundViewTest {
    @Test
    fun requestBillAmount() {
        val console = mockk<IConsoleInput>()
        every { console.readInput() } returns "100+50-20"

        val view = RoundView(console)
        val amount = view.requestBillAmount()

        assertEquals(130, amount)
        verify(exactly = 1) { console.readInput() }
    }

    @Test
    fun requestPlayersToPay() {
        val players = listOf(
            Player(1, "A", 1000),
            Player(2, "B", 1000),
            Player(3, "C", 1000)
        )

        val console = mockk<IConsoleInput>()
        every { console.readInput() } returns "1,2"

        val view = RoundView(console)
        val result = view.requestPlayersToPay(players)

        assertEquals(listOf(1,2), result)
        verify(exactly = 1) { console.readInput() }
    }

    @Test
    fun requestSufficientCards() {
        // case 1: direct S
        val console1 = mockk<IConsoleInput>()
        every { console1.readInput() } returns "S"
        val view1 = RoundView(console1)
        assertTrue(view1.requestSufficientCards())
        verify(exactly = 1) { console1.readInput() }

        // case 2: invalid then N
        val console2 = mockk<IConsoleInput>()
        every { console2.readInput() } returnsMany listOf("x", "N")
        val view2 = RoundView(console2)
        assertFalse(view2.requestSufficientCards())
        verify(exactly = 2) { console2.readInput() }
    }
}