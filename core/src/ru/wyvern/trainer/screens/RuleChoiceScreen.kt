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
        private const val RULE_COUNT = 10
    }

    private val headerLabel1 = Label("Выберите", LanguageTrainer.skin, "header")
    private val headerLabel2 = Label("правило:", LanguageTrainer.skin, "header")
    private val buttonTable = Table().left().top().apply {
        for (i1 in 1..RULE_COUNT) {
            val i = 0
            LanguageTrainer.assetManager.apply {
                val rule = if (!contains("rules/rule-$i.json", LanguageRule::class.java)) {
                    load("rules/rule-$i.json", LanguageRule::class.java)
                    finishLoadingAsset("rules/rule-$i.json")
                } else get("rules/rule-$i.json", LanguageRule::class.java)
                add(TextButton(rule.name, LanguageTrainer.skin).apply {
                    addClickListener { LanguageTrainer.screen = LevelChoiceScreen(rule) }
                }).size(240f, 45f).pad(5f)
                if (i1 % 2 == 0) row()
            }
        }
    }

    override val stage = createDefaultStage().apply {
        addActor(headerLabel1)
        addActor(headerLabel2)
        addActor(buttonTable)
    }

    override fun resize(width: Int, height: Int) {
        headerLabel1.setPosition(20f, height - 90f)
        headerLabel2.setPosition(20f, height - 150f)
        buttonTable.setPosition(10f, height - 160f)
    }

}