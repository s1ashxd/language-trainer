package ru.wyvern.trainer.utils

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import ru.wyvern.trainer.LanguageTrainer

fun createDefaultStage() = Stage(LanguageTrainer.viewport, LanguageTrainer.batch)

fun Actor.setCenteredPosition(x: Float = 0f, y: Float = 0f) =
    setPosition(
        x + (LanguageTrainer.APPLICATION_WIDTH - width) / 2f,
        y + (LanguageTrainer.APPLICATION_HEIGHT - height) / 2f
    )

inline fun Actor.addClickListener(crossinline listener: () -> Unit) {
    addListener(object : ClickListener() {
        override fun clicked(event: InputEvent?, x: Float, y: Float) = listener()
    })
}