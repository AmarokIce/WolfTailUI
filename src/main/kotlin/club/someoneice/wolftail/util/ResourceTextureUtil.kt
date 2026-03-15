package club.someoneice.wolftail.util

import net.minecraft.client.Minecraft
import net.minecraft.client.gui.Gui
import net.minecraft.client.renderer.texture.DynamicTexture
import net.minecraft.util.ResourceLocation
import java.awt.image.BufferedImage
import kotlin.math.max
import kotlin.math.min

object ResourceTextureUtil {
  private val DATA_MAP: MutableMap<ResourceLocation, ResourceLocation> = HashMap()

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

  fun drawImageWithoutResource(pX: Int, pY: Int, image: BufferedImage,
                               u: Int, v: Int, w: Int, h: Int) {
    val startU = max(0, u)
    val startV = max(0, v)
    val endU = min(image.width, w)
    val endV = min(image.height, h)

    for (y in startV until endV) {
      for (x in startU until endU) {
        val color = image.getRGB(x, y)
        Gui.drawRect(pX + x, pY + y, pX + x + 1, pY + y + 1, color)
      }
    }
  }

  fun drawImageWithoutResource(pX: Int, pY: Int, image: BufferedImage) {
    drawImageWithoutResource(pX, pY, image, 0, 0, image.width, image.height)
  }

  fun drawImageWithoutResourceCentral(pX: Int, pY: Int, image: BufferedImage,
                               u: Int, v: Int, h: Int, w: Int) {
    val startU = max(0, u)
    val startV = max(0, v)
    val endU = min(image.width, w)
    val endV = min(image.height, h)

    val posX = pX - (endU - startU) / 2
    val posY = pY - (endV - startV) / 2

    for (x in startU until endU) {
      for (y in startV until endV) {
        val color = image.getRGB(x, y)
        Gui.drawRect(posX + x, posY + y, posX + x + 1, posY + y + 1, color)
      }
    }
  }

  fun drawImageWithoutResourceCentral(pX: Int, pY: Int, image: BufferedImage) {
    drawImageWithoutResource(pX, pY, image, 0, 0, image.width, image.height)
  }
}
