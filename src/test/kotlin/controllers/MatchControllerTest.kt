package controllers

import com.github.juanalberticohf.controllers.MatchController
import com.github.juanalberticohf.models.Player
import com.github.juanalberticohf.views.MatchView
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import net.bytebuddy.matcher.ElementMatchers.any
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MatchControllerTest {
    @Test
    fun testMatchStart() {
        // Preparar
        val playerListStr = listOf("Juan", "Javier", "Jose")
        val initialSaves = 900
        val listOfPlayers = listOf(
            Player(1,"Juan", initialSaves),
            Player(2, "Javier", initialSaves),
            Player(3, "Jose", initialSaves)
        )

        val mockMatchView = mockk<MatchView>()
        every { mockMatchView.startMatchMessage() } just Runs
        every { mockMatchView.requestPlayers() } returns playerListStr
        every { mockMatchView.showPlayersBill(any()) } just Runs
        every { mockMatchView.showRules() } just Runs
        every { mockMatchView.requestStartMatch() } just Runs

        val spykMatchController = spyk(MatchController(mockMatchView))

        every {spykMatchController.matchInProgress(any())} just Runs

        // Ejecutar
        spykMatchController.matchStart()

        // Comprobar
        verify { spykMatchController.matchInProgress(any()) }
        verify { mockMatchView.startMatchMessage() }
        verify { mockMatchView.requestPlayers() }
        verify { mockMatchView.showPlayersBill(any()) }
        verify { mockMatchView.showRules() }
        verify { mockMatchView.requestStartMatch() }

        assertEquals(
            spykMatchController.getPlayerList(playerListStr, initialSaves),
            listOfPlayers)
    }

    @Test
    fun testMatchInProgress() {
        // TODO: Implementar
    }

    @Test
    fun testMatchEnd() {
        // TODO: Implementar
    }

    @Test
    fun testGetPlayerList(){
        // TODO: Implementar
    }
}