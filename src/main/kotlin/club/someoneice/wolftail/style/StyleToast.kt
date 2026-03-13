package club.someoneice.wolftail.style

import club.someoneice.wolftail.WolfTailUI
import club.someoneice.wolftail.api.IStyle
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.Gui
import net.minecraft.util.ResourceLocation
import org.lwjgl.opengl.GL11
import org.lwjgl.util.Rectangle
import java.awt.Color

open class StyleToast(val startAt: Int) : IStyle {
  companion object {
    val TOAST_DARK_UI = StyleToast(0)
    val TOAST_LIGHT_UI = StyleToast(32)
    val TOAST_T_DARK_UI = StyleToast(64)
    val TOAST_T_LIGHT_UI = StyleToast(96)
  }

  private val range = Rectangle(96, startAt, 160, 32)
  private val texture = ResourceLocation(WolfTailUI.ID, "default_ui.png")

  override fun getTexture(): ResourceLocation = texture
  override fun getUIRange(): Rectangle = range

  override fun render(pGui: Gui, pPosX: Int, pPosY: Int, pWidth: Int, pHeight: Int,
                      args: Map<String, Any>) {
    GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f)
    GL11.glEnable(GL11.GL_TEXTURE_2D)
    Minecraft.getMinecraft().textureManager.bindTexture(this.getTexture())
    GL11.glDisable(GL11.GL_LIGHTING)
    this.renderTexture(pGui, pPosX, pPosY)
  }

  override fun drawString(pString: String, pGui: Gui, x: Int, y: Int,
                          args: Map<String, Any>) {
    StyleFont.INSTANCE.drawString(pString, pGui, x, y, mapOf("color" to (
      when(args["flag"].toString()) {
        "title" -> Color.YELLOW
        "text" -> Color.WHITE
        else -> Color.WHITE
      }).rgb))
  }

  open fun renderTexture(pGui: Gui, pPosX: Int, pPosY: Int) {
    val rangeIn = this.getUIRange()
    pGui.drawTexturedModalRect(
      pPosX, pPosY,
      rangeIn.x, rangeIn.y,
      rangeIn.width, rangeIn.height
    )
  }
}
