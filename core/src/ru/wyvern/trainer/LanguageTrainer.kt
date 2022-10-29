package ru.wyvern.trainer

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Preferences
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.utils.ScreenUtils
import com.badlogic.gdx.utils.viewport.FillViewport
import ru.wyvern.trainer.loaders.rules.LanguageRule
import ru.wyvern.trainer.loaders.rules.LanguageRuleLoader
import ru.wyvern.trainer.loaders.skins.FreeTypeSkinLoader
import ru.wyvern.trainer.screens.MainMenuScreen


object LanguageTrainer : Game() {

    const val APPLICATION_TITLE = "Language Trainer"
    const val APPLICATION_WIDTH = 740f
    const val APPLICATION_HEIGHT = 540f

    lateinit var camera: OrthographicCamera
    lateinit var viewport: FillViewport
    lateinit var preferences: Preferences
    lateinit var batch: SpriteBatch
    lateinit var assetManager: AssetManager

    lateinit var skin: Skin

    override fun create() {
        camera = OrthographicCamera(APPLICATION_WIDTH, APPLICATION_HEIGHT)
        viewport = FillViewport(APPLICATION_WIDTH, APPLICATION_HEIGHT, camera)
        preferences = Gdx.app.getPreferences("language-trainer.xml")
        batch = SpriteBatch()
        assetManager = AssetManager().apply {
            setLoader(LanguageRule::class.java, LanguageRuleLoader(fileHandleResolver))
            setLoader(Skin::class.java, FreeTypeSkinLoader(fileHandleResolver))
            load("skin/skin.json", Skin::class.java)
            finishLoading()
        }
        skin = assetManager.get("skin/skin.json")
        setScreen(MainMenuScreen())
    }

    override fun render() {
        ScreenUtils.clear(1f, 0.99f, 0.73f, 1f)
        super.render()
    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height)
        batch.projectionMatrix = camera.combined
        super.resize(width, height)
    }

    override fun dispose() {
        batch.dispose()
        assetManager.dispose()
        super.dispose()
    }
}