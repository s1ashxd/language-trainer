package ru.wyvern.trainer.screens

import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import ru.wyvern.trainer.LanguageTrainer
import ru.wyvern.trainer.loaders.rules.LanguageRule
import ru.wyvern.trainer.utils.addClickListener
import ru.wyvern.trainer.utils.createDefaultStage
import ru.wyvern.trainer.utils.setCenteredPosition

class GameOverScreen(rule: LanguageRule, progress: Float) : StageScreen() {

    private val stars = (progress / 0.333f).toInt()
    private val headerLabel = Label("Тест окончен", LanguageTrainer.skin, "header")
    private val starsTable = Table().apply {
        add(Label("Результат:", LanguageTrainer.skin, "small-header")).padRight(20f)
        for (i in 1..stars)
            addStarImage(this, "filled-star-64", i < 3)
        for (i in stars until 3)
            addStarImage(this, "empty-star-64", i < 2)
    }
    private val statusTable = Table().top().apply {
        when (stars) {
            0 -> {
                add(Label("Повтори правило", LanguageTrainer.skin, "header-error"))
                row()
                add(Label("и попробуй снова", LanguageTrainer.skin, "header-error"))
            }

            1 -> {
                add(Label("Повтори правило", LanguageTrainer.skin, "header-error"))
                row()
                add(Label("и попробуй снова", LanguageTrainer.skin, "header-error"))
            }

            2 -> add(Label("Хорошая работа!", LanguageTrainer.skin, "small-header"))
            else -> add(Label("Отлично!", LanguageTrainer.skin, "header-success"))
        }
    }
    private val exitButton = TextButton(
        if (stars == 3) "К списку уровней" else "Повторить правило",
        LanguageTrainer.skin
    ).apply { addClickListener { LanguageTrainer.screen = LevelChoiceScreen(rule) } }

    override val stage = createDefaultStage().apply {
        addActor(headerLabel)
        addActor(starsTable)
        addActor(statusTable)
        addActor(exitButton)
    }

    override fun resize(width: Int, height: Int) {
        headerLabel.setCenteredPosition(y = 200f)
        starsTable.setCenteredPosition(y = 100f)
        statusTable.setCenteredPosition(y = 40f)
        exitButton.setCenteredPosition(y = -170f)
    }

    private fun addStarImage(table: Table, starID: String, padding: Boolean) {
        val cell = table.add(Image(LanguageTrainer.skin, starID).apply {
            setOrigin(25f, 25f)
            addAction(
                if (starID == "filled-star-64") {
                    Actions.sequence(
                        Actions.alpha(0f),
                        Actions.parallel(
                            Actions.fadeIn(1.3f),
                            Actions.sequence(
                                Actions.scaleBy(-0.5f, -0.5f, 0.7f),
                                Actions.scaleBy(0.5f, 0.5f, 0.3f)
                            )
                        )
                    )
                } else {
                    Actions.sequence(
                        Actions.alpha(0f),
                        Actions.scaleBy(-1f, -1f),
                        Actions.parallel(
                            Actions.fadeIn(1.3f),
                            Actions.scaleBy(1f, 1f, 1f)
                        )
                    )
                }
            )
        }).size(60f)
        if (padding) cell.padRight(15f)
    }

}