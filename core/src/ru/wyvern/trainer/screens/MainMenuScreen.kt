package ru.wyvern.trainer.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import ru.wyvern.trainer.LanguageTrainer
import ru.wyvern.trainer.utils.addClickListener
import ru.wyvern.trainer.utils.createDefaultStage
import ru.wyvern.trainer.utils.setCenteredPosition

class MainMenuScreen : StageScreen() {

    private val headerLabel1 = Label("Русский язык", LanguageTrainer.skin, "header")
    private val headerLabel2 = Label("Тренажер", LanguageTrainer.skin, "header")
    private val startButton = TextButton("Начать", LanguageTrainer.skin).apply {
        height = 50f
        addClickListener { LanguageTrainer.screen = RuleChoiceScreen() }
    }
    private val exitButton = TextButton("Выйти", LanguageTrainer.skin).apply {
        height = 50f
        addClickListener { Gdx.app.exit() }
    }

    override val stage = createDefaultStage().apply {
        addActor(headerLabel1)
        addActor(headerLabel2)
        addActor(startButton)
        addActor(exitButton)
    }

    override fun resize(width: Int, height: Int) {
        headerLabel1.setCenteredPosition(width, height, y = 160f)
        headerLabel2.setCenteredPosition(width, height, y = 100f)
        startButton.setCenteredPosition(width, height, y = -40f)
        exitButton.setCenteredPosition(width, height, y = -100f)
    }

}