package ru.wyvern.trainer.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.scenes.scene2d.Stage

abstract class StageScreen : ScreenAdapter() {

    abstract val stage: Stage

    override fun render(delta: Float) {
        stage.draw()
        stage.act(delta)
    }

    override fun show() {
        Gdx.input.inputProcessor = stage
    }

    override fun hide() {
        Gdx.input.inputProcessor = null
    }

    override fun dispose() {
        stage.dispose()
    }

}