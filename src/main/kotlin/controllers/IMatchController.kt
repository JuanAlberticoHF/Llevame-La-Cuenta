package com.github.juanalberticohf.controllers

import com.github.juanalberticohf.models.Match
import com.github.juanalberticohf.models.Player

interface IMatchController {
    fun matchStart()
    fun matchInProgress(playerList: List<Player>)
    fun matchEnd(match: Match)
    fun getPlayerList(playerNameList: List<String>, startingMoney: Int): List<Player>
}