package ru.wyvern.trainer.loaders.rules

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.assets.AssetLoaderParameters
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.FileHandleResolver
import com.badlogic.gdx.assets.loaders.SynchronousAssetLoader
import com.badlogic.gdx.files.FileHandle
import com.badlogic.gdx.utils.Array
import com.badlogic.gdx.utils.JsonReader
import ru.wyvern.trainer.LanguageTrainer

class LanguageRuleLoader(resolver: FileHandleResolver) :
    SynchronousAssetLoader<LanguageRule, LanguageRuleLoader.LanguageRuleParameter>(resolver) {

    override fun getDependencies(
        fileName: String?,
        file: FileHandle?,
        parameter: LanguageRuleParameter?
    ): Array<AssetDescriptor<Any>>? = null

    override fun load(
        assetManager: AssetManager?,
        fileName: String,
        file: FileHandle?,
        parameter: LanguageRuleParameter?
    ): LanguageRule {
        val json = JsonReader().parse(Gdx.files.internal(fileName))
        val name = json.get("rule_name").asString()
        val desc = json.get("description").asString()
        val levels = mutableListOf<LanguageRule.RuleLevel>()
        json.get("levels").forEachIndexed { index, jsonLevel ->
            val words = mutableListOf<LanguageRule.LevelWord>()
            jsonLevel.forEach {
                val correctVariant = it["correct_variant"].asString()
                words.add(
                    LanguageRule.LevelWord(
                        it["word"].asString(),
                        correctVariant,
                        mutableListOf<String>().apply {
                            add(correctVariant)
                            addAll(it["other_variants"].asStringArray())
                        }.shuffled()
                    )
                )
            }
            levels.add(LanguageRule.RuleLevel(words, LanguageTrainer.preferences.getFloat("${name}_$index", 0f)))
        }
        return LanguageRule(name, desc, levels)
    }

    class LanguageRuleParameter : AssetLoaderParameters<LanguageRule>()

}