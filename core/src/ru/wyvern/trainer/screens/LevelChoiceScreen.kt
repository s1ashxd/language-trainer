package ru.wyvern.trainer.screens

import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import ru.wyvern.trainer.LanguageTrainer
import ru.wyvern.trainer.loaders.rules.LanguageRule
import ru.wyvern.trainer.utils.addClickListener
import ru.wyvern.trainer.utils.setCenteredPosition

class LevelChoiceScreen(rule: LanguageRule) : StageScreen() {

    private val headerLabel = Label("Уровень:", LanguageTrainer.skin, "header")
    private val levelList = Table(LanguageTrainer.skin).apply {
        rule.levels.forEachIndexed { index, ruleLevel ->
            val levelTable = Table()
            val starsTable = Table()
            val starsCount = (ruleLevel.progress / 0.333f).toInt()
            for (i in 1..starsCount) {
                val cell = starsTable.add(Image(LanguageTrainer.skin, "filled-star")).size(20f)
                if (i < 3) cell.padRight(5f)
            }
            for (i in starsCount until 3) {
                val cell = starsTable.add(Image(LanguageTrainer.skin, "empty-star")).size(20f)
                if (i < 2) cell.padRight(5f)
            }
            levelTable.add(TextButton("${index + 1}", LanguageTrainer.skin).apply {
                addClickListener {

                }
            }).size(60f).padBottom(5f)
            levelTable.row()
            levelTable.add(starsTable)
            val cell = add(levelTable).padRight(10f)
            if (index < rule.levels.size - 1) cell.padRight(10f)
        }
    }
    private val ruleInfo = Table().apply {
        add(Label("Запомни:", LanguageTrainer.skin)).left().width(200f)
        row()
        rule.description.split("\\n").forEach {
            add(Label(it, LanguageTrainer.skin)).left()
            row()
        }
    }

    override val stage = Stage().apply {
        addActor(headerLabel)
        addActor(ruleInfo)
        addActor(levelList)
    }

    override fun resize(width: Int, height: Int) {
        headerLabel.setCenteredPosition(width, height, y = 200f)
        levelList.setCenteredPosition(width, height, y = 60f)
        ruleInfo.setCenteredPosition(width, height, y = -90f)
    }

}