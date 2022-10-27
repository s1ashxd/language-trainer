package ru.wyvern.trainer.screens

import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.scenes.scene2d.Stage

abstract class StageScreen : ScreenAdapter() {

    abstract val stage: Stage

    override fun render(delta: Float) {
        stage.draw()
        stage.act(delta)
    }

}