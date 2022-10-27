package ru.wyvern.trainer

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.ScreenUtils
import com.badlogic.gdx.utils.viewport.FillViewport
import ru.wyvern.trainer.screens.MainMenuScreen

object LanguageTrainer : Game() {

    const val APPLICATION_TITLE = "Language Trainer"
    const val APPLICATION_WIDTH = 540f
    const val APPLICATION_HEIGHT = 340f

    lateinit var batch: SpriteBatch
    lateinit var camera: OrthographicCamera
    lateinit var viewport: FillViewport
    lateinit var assetManager: AssetManager

    fun createStage() = Stage(viewport, batch)

    override fun create() {
        batch = SpriteBatch()
        camera = OrthographicCamera(APPLICATION_WIDTH, APPLICATION_HEIGHT)
        viewport = FillViewport(APPLICATION_WIDTH, APPLICATION_HEIGHT, camera)
        assetManager = AssetManager().apply {
            load("textures/buttons/small-button.png", Texture::class.java)
            finishLoading()
        }
        screen = MainMenuScreen
    }

    override fun render() {
        ScreenUtils.clear(0f, 0f, 0f, 1f)
        viewport.update(Gdx.graphics.width, Gdx.graphics.height)
        super.render()
    }

    override fun dispose() {
        batch.dispose()
        assetManager.dispose()
    }
}