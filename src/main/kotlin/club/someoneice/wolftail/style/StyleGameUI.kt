package club.someoneice.wolftail.style

import club.someoneice.wolftail.WolfTailUI
import club.someoneice.wolftail.api.IUIStyle
import club.someoneice.wolftail.clearColor
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.Gui
import net.minecraft.client.renderer.texture.DynamicTexture
import net.minecraft.util.ResourceLocation
import org.lwjgl.util.Rectangle
import java.awt.Color
import java.awt.image.BufferedImage

/**
 * The title is the name for the ui in generator.
 */
class StyleGameUI(private val title: String, private val sizeOf: Pair<Int, Int>, private val light: Boolean = false) :
  IUIStyle {
  private val texture: DynamicTexture
  private val resourceID: ResourceLocation

  private val textureID = ResourceLocation(WolfTailUI.ID, "gui_$title")

  init {
    val imageBackground = BufferedImage(256, 256, BufferedImage.TYPE_INT_ARGB)
    val g2d = imageBackground.createGraphics()
    g2d.color = if (!light) COLOR_B0 else COLOR_L0
    g2d.fillRect(0, 0, this.sizeOf.first, this.sizeOf.second)
    g2d.color = if (!light) COLOR_B1 else COLOR_L1
    g2d.fillRect(4, 4, this.sizeOf.first - 8, this.sizeOf.second - 8)
    g2d.dispose()

    this.texture = DynamicTexture(imageBackground)

    this.resourceID = Minecraft.getMinecraft().textureManager
      .getDynamicTextureLocation(this.getTexture().toString(), texture)
  }

  override fun getTexture(): ResourceLocation = textureID

  override fun getUIRange(): Rectangle = Rectangle()

  override fun renderBackground(gui: Gui, x: Int, y: Int) {
    clearColor()

    Minecraft.getMinecraft().renderEngine.bindTexture(resourceID)
    gui.drawTexturedModalRect(x, y, 0, 0, this.sizeOf.first, this.sizeOf.second)
  }

  companion object {
    val COLOR_B0 = Color(59, 66, 82, 255)
    val COLOR_B1 = Color(42, 56, 64, 255)

    val COLOR_L0 = Color(236, 239, 244, 255)
    val COLOR_L1 = Color(216, 222, 233, 255)
  }
}
