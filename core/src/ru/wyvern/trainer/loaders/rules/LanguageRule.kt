package ru.wyvern.trainer.loaders.rules

data class LanguageRule(val name: String, val description: String, val levels: List<RuleLevel>) {

    data class RuleLevel(val words: List<LevelWord>, var progress: Float)

    data class LevelWord(val name: String, val correctVariant: String, val allVariants: List<String>)

}