package ru.wyvern.trainer.screens

import ru.wyvern.trainer.LanguageTrainer
import ru.wyvern.trainer.actors.ButtonActor
import ru.wyvern.trainer.actors.setCenteredPosition

object MainMenuScreen : StageScreen() {

    private val button = ButtonActor("Start").apply {
        setSize(100f, 50f)
    }

    override val stage = LanguageTrainer.createStage().apply {
        addActor(button)
    }

    override fun resize(width: Int, height: Int) {
        button.setCenteredPosition(width, height)
    }

}