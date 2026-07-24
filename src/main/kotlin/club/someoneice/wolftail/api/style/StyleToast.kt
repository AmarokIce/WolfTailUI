package club.someoneice.wolftail.api.style

import club.someoneice.wolftail.api.IStyle
import club.someoneice.wolftail.api.constant.DefaultStyleToast
import club.someoneice.wolftail.util.UIPos
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.Gui
import net.minecraft.util.ResourceLocation
import org.lwjgl.opengl.GL11
import java.awt.Color

open class StyleToast(
  private val texture: ResourceLocation,
  private val range: UIPos
) : IStyle {

  constructor(texture: ResourceLocation, x: Int, y: Int, w: Int, h: Int):
    this(texture, UIPos(x, y, w, h))

  override fun getTexture(): ResourceLocation = this.texture
  override fun getUIRange(): UIPos = range

  override fun render(pGui: Gui, rect: UIPos, pGuiX: Int, pGuiY: Int, args: Map<String, Any>) {
    GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f)
    GL11.glEnable(GL11.GL_TEXTURE_2D)
    Minecraft.getMinecraft().textureManager.bindTexture(this.getTexture())
    GL11.glDisable(GL11.GL_LIGHTING)
    this.renderTexture(pGui, rect.x, rect.y)
  }

  override fun drawString(pString: String, pGui: Gui, x: Int, y: Int, pGuiX: Int, pGuiY: Int, args: Map<String, Any>) {
    StyleFont.INSTANCE.drawString(pString, pGui, x, y, pGuiX, pGuiY, mapOf("color" to (
      when(args["flag"].toString()) {
        "title" -> Color.YELLOW
        "text" -> Color.WHITE
        else -> Color.WHITE
      }).rgb))  }

  open fun renderTexture(pGui: Gui, pPosX: Int, pPosY: Int) {
    val rangeIn = this.getUIRange()
    pGui.drawTexturedModalRect(
      pPosX, pPosY,
      rangeIn.x, rangeIn.y,
      rangeIn.w, rangeIn.h
    )
  }

  @Deprecated("Will remove future.")
  companion object {
    val TOAST_DARK_UI = DefaultStyleToast.TOAST_DARK
    val TOAST_LIGHT_UI = DefaultStyleToast.TOAST_LIGHT
    val TOAST_T_DARK_UI = DefaultStyleToast.TOAST_T_DARK
    val TOAST_T_LIGHT_UI = DefaultStyleToast.TOAST_T_LIGHT
  }
}
