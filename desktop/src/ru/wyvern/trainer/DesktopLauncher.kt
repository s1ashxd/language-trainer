package ru.wyvern.trainer

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration

object DesktopLauncher {
    @JvmStatic
    fun main(args: Array<String>) {
        Lwjgl3Application(LanguageTrainer, Lwjgl3ApplicationConfiguration().apply {
            setForegroundFPS(60)
            setWindowIcon("icon.png")
            setWindowedMode(LanguageTrainer.APPLICATION_WIDTH.toInt(), LanguageTrainer.APPLICATION_HEIGHT.toInt())
            setResizable(false)
            setTitle(LanguageTrainer.APPLICATION_TITLE)
        })
    }
}