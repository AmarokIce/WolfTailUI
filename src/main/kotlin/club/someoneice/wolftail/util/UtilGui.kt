package club.someoneice.wolftail.util

import club.someoneice.wolftail.ui.GuiWToast.Companion.itemRenderer
import club.someoneice.wolftail.ui.GuiWToast.Companion.mc
import cpw.mods.fml.common.registry.GameRegistry
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.Gui
import net.minecraft.client.renderer.RenderHelper
import net.minecraft.client.renderer.entity.RenderItem
import net.minecraft.item.ItemStack
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL12
import java.awt.image.BufferedImage
import kotlin.math.max
import kotlin.math.min

object UtilGui {
  val itemRenderer = RenderItem()
  val mc: Minecraft = Minecraft.getMinecraft()

  @JvmStatic
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

  @JvmStatic
  fun drawImageWithoutResource(pX: Int, pY: Int, image: BufferedImage) {
    drawImageWithoutResource(pX, pY, image, 0, 0, image.width, image.height)
  }

  @JvmStatic
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

  @JvmStatic
  fun drawImageWithoutResourceCentral(pX: Int, pY: Int, image: BufferedImage) {
    drawImageWithoutResource(pX, pY, image, 0, 0, image.width, image.height)
  }

  @JvmStatic
  fun fill(x: Int, y: Int, w: Int, h: Int, color: Int) {
    for(i in 0 ..< h) {
      Gui.drawRect(x, y + i, x + w, y + h + i, color)
    }
  }

  @JvmStatic
  fun drawItemStack(x: Int, y: Int, stack: ItemStack) {
    GL11.glEnable(GL12.GL_RESCALE_NORMAL)
    RenderHelper.enableGUIStandardItemLighting()
    itemRenderer.renderItemAndEffectIntoGUI(mc.fontRenderer,
      mc.textureManager, stack, x, y)
    itemRenderer.renderItemOverlayIntoGUI(mc.fontRenderer,
      mc.textureManager, stack, x, y)
    RenderHelper.disableStandardItemLighting()
    GL11.glDisable(GL12.GL_RESCALE_NORMAL)
    GL11.glEnable(GL11.GL_BLEND)
  }
}
