package ru.wyvern.trainer.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import ru.wyvern.trainer.LanguageTrainer
import ru.wyvern.trainer.loaders.rules.LanguageRule
import ru.wyvern.trainer.utils.addClickListener
import ru.wyvern.trainer.utils.createDefaultStage
import ru.wyvern.trainer.utils.setCenteredPosition


class GameScreen(private val rule: LanguageRule, private val levelIndex: Int) : StageScreen() {

    private val words = rule.levels[levelIndex].words.shuffled()
    private var correctAnswers = 0
    private var noticed = false

    private lateinit var headerLabel: Label
    private lateinit var variantsTable: Table
    private lateinit var noticeTable: Table

    override val stage = createDefaultStage().apply {
        loop(0)
        addActor(headerLabel)
        addActor(variantsTable)
    }

    override fun resize(width: Int, height: Int) {
        headerLabel.setCenteredPosition(width, height, y = 160f)
        variantsTable.setCenteredPosition(width, height, y = 70f)
        noticeTable.setCenteredPosition(width, height, y = -170f)
    }

    private fun loop(index: Int) {
        if (index < words.size) {
            val word = words[index]
            headerLabel = Label(word.name, LanguageTrainer.skin, "header")
            variantsTable = Table().top().apply {
                word.allVariants.forEach {
                    add(TextButton(it, LanguageTrainer.skin).apply {
                        addClickListener {
                            if (it == word.correctVariant)
                                correctAnswers++
                            else noticed = true
                            loop(index + 1)
                        }
                    }).height(40f).padBottom(5f)
                    row()
                }
                add(TextButton("Закончить", LanguageTrainer.skin).apply { addClickListener { endGame() } }).height(45f)
            }
            noticeTable = Table().apply {
                add(Label("Напоминание:", LanguageTrainer.skin)).left().width(200f)
                row()
                rule.description.split("\\n").forEach {
                    add(Label(it, LanguageTrainer.skin)).left()
                    row()
                }
            }
            if (index > 0) refreshStage()
            return
        }
        endGame()
    }

    private fun refreshStage() {
        stage.run {
            actors.clear()
            addActor(headerLabel)
            addActor(variantsTable)
            if (noticed) addActor(noticeTable)
        }
        resize(Gdx.graphics.width, Gdx.graphics.height)
    }

    private fun endGame() {
        val percent = ((correctAnswers.toDouble() / words.size.toDouble()).coerceIn(0.0, 1.0)).toFloat()
        val newRecord = rule.levels[levelIndex].progress < percent
        if (newRecord) {
            rule.levels[levelIndex].progress = percent
            LanguageTrainer.preferences.run {
                putFloat("${rule.name}_$levelIndex", percent)
                flush()
            }
        }
        LanguageTrainer.screen = GameOverScreen(rule, correctAnswers.toFloat() / words.size)
    }

}