package ru.wyvern.trainer.loaders.skins

import com.badlogic.gdx.assets.loaders.FileHandleResolver
import com.badlogic.gdx.assets.loaders.SkinLoader
import com.badlogic.gdx.files.FileHandle
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.utils.Json
import com.badlogic.gdx.utils.JsonValue

class FreeTypeSkinLoader(resolver: FileHandleResolver) : SkinLoader(resolver) {
    override fun newSkin(atlas: TextureAtlas?): Skin {
        return object : Skin(atlas) {
            override fun getJsonLoader(skinFile: FileHandle): Json {
                val json = super.getJsonLoader(skinFile)
                val skin = this
                json.setSerializer(
                    FreeTypeFontGenerator::class.java,
                    object : Json.ReadOnlySerializer<FreeTypeFontGenerator>() {
                        override fun read(
                            json: Json,
                            jsonData: JsonValue,
                            type: Class<*>
                        ): FreeTypeFontGenerator? {
                            val path = json.readValue("font", String::class.java, jsonData)
                            jsonData.remove("font")
                            val hinting = FreeTypeFontGenerator.Hinting.valueOf(
                                json.readValue(
                                    "hinting",
                                    String::class.java, "AutoMedium", jsonData
                                )
                            )
                            jsonData.remove("hinting")
                            val minFilter = Texture.TextureFilter.valueOf(
                                json.readValue("minFilter", String::class.java, "Nearest", jsonData)
                            )
                            jsonData.remove("minFilter")
                            val magFilter = Texture.TextureFilter.valueOf(
                                json.readValue("magFilter", String::class.java, "Nearest", jsonData)
                            )
                            jsonData.remove("magFilter")
                            val parameter =
                                json.readValue(FreeTypeFontGenerator.FreeTypeFontParameter::class.java, jsonData)
                            parameter.hinting = hinting
                            parameter.minFilter = minFilter
                            parameter.magFilter = magFilter
                            val generator = FreeTypeFontGenerator(skinFile.parent().child(path))
                            val font = generator.generateFont(parameter)
                            skin.add(jsonData.name, font)
                            return if (parameter.incremental) {
                                generator.dispose()
                                null
                            } else {
                                generator
                            }
                        }
                    }
                )
                return json
            }
        }
    }
}