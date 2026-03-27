package com.github.juanalberticohf.console

class ConsoleInput : IConsoleInput {
    override fun readInput(): String? = readlnOrNull()
}