package com.github.juanalberticohf.views

interface IAppView {
    fun welcomeMessage()
    fun mainMenu(): Int
    fun notAvaiableMessage(optionNumber: Int)
    fun leaveMessage()
}