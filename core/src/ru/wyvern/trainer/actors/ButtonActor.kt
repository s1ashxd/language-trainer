package ru.wyvern.trainer.actors

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.GlyphLayout
import com.badlogic.gdx.scenes.scene2d.Actor
import ru.wyvern.trainer.LanguageTrainer

class ButtonActor(private val label: String, texture: String = "textures/buttons/small-button.png") : Actor() {

    private val glyphLayout = GlyphLayout(font, label)
    private val texture = LanguageTrainer.assetManager.get<Texture>(texture)

    override fun draw(batch: Batch, parentAlpha: Float) {
        batch.draw(texture, x, y, width, height)
        font.draw(
            batch,
            label,
            x + (width - glyphLayout.width) / 2f,
            y + (height - glyphLayout.height) / 2f
        )
    }

    companion object {
        val font = BitmapFont()
    }

}