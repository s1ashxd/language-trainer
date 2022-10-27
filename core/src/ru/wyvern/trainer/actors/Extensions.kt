package ru.wyvern.trainer.actors

import com.badlogic.gdx.scenes.scene2d.Actor

fun Actor.setCenteredPosition(screenWidth: Int, screenHeight: Int) {
    setPosition((screenWidth - width) / 2f, (screenHeight - height) / 2f)
}