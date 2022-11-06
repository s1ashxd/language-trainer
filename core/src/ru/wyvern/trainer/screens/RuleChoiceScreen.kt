package ru.wyvern.trainer.screens

import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import ru.wyvern.trainer.LanguageTrainer
import ru.wyvern.trainer.loaders.rules.LanguageRule
import ru.wyvern.trainer.utils.addClickListener
import ru.wyvern.trainer.utils.createDefaultStage

class RuleChoiceScreen : StageScreen() {

    companion object {
        private const val RULE_COUNT = 8
    }

    private val headerLabel1 = Label("Выберите", LanguageTrainer.skin, "header")
    private val headerLabel2 = Label("правило:", LanguageTrainer.skin, "header")
    private val buttonTable = Table().left().top().apply {
        val table = Table().apply {
            for (i in 0 until RULE_COUNT) {
                LanguageTrainer.assetManager.apply {
                    val rule = if (!contains("rules/rule-$i.json", LanguageRule::class.java)) {
                        load("rules/rule-$i.json", LanguageRule::class.java)
                        finishLoadingAsset("rules/rule-$i.json")
                    } else get("rules/rule-$i.json", LanguageRule::class.java)
                    add(TextButton(rule.name, LanguageTrainer.skin).apply {
                        addClickListener { LanguageTrainer.screen = LevelChoiceScreen(rule) }
                    }).size(300f, 45f).pad(5f)
                    if ((i + 1) % 2 == 0) row()
                }
            }
        }
        add(table).padBottom(10f)
        row()
        add(TextButton("Назад", LanguageTrainer.skin).apply {
            addClickListener { LanguageTrainer.screen = MainMenuScreen() }
        })
    }

    override val stage = createDefaultStage().apply {
        addActor(headerLabel1)
        addActor(headerLabel2)
        addActor(buttonTable)
    }

    init {
        headerLabel1.setPosition(20f, (LanguageTrainer.APPLICATION_HEIGHT - headerLabel1.height) / 2f + 200f)
        headerLabel2.setPosition(20f, (LanguageTrainer.APPLICATION_HEIGHT - headerLabel2.height) / 2f + 150f)
        buttonTable.setPosition(10f, (LanguageTrainer.APPLICATION_HEIGHT - headerLabel1.height) / 2f + 130f)
    }

}