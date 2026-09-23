package club.someoneice.wolftail.ui.core

import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.texture.DynamicTexture
import net.minecraft.util.ResourceLocation
import java.awt.image.BufferedImage

object ResourceManager {
  private val DATA_MAP: MutableMap<ResourceLocation, ResourceLocation> = HashMap()

  fun hasTexture(resourceLocation: ResourceLocation): Boolean {
    return DATA_MAP.containsKey(resourceLocation)
  }

  fun hasResource(resourceLocation: ResourceLocation): Boolean {
    return DATA_MAP.containsValue(resourceLocation)
  }

  fun createDynamicTexture(registerName: ResourceLocation, image: BufferedImage): ResourceLocation {
    if (DATA_MAP.containsKey(registerName)) {
      return DATA_MAP[registerName]!!
    }

    val name =
      Minecraft.getMinecraft().textureManager
        .getDynamicTextureLocation(registerName.toString(), DynamicTexture(image))
    DATA_MAP[registerName] = name
    return name
  }
}
