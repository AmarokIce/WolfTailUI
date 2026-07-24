package club.someoneice.wolftail.ui.widget

import club.someoneice.wolftail.api.IStyle
import club.someoneice.wolftail.api.IWidget
import club.someoneice.wolftail.style.StyleFont
import club.someoneice.wolftail.util.UIPos
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.Gui
import java.awt.Color

open class WString(
  private val title: String,
  private val x: Int,
  private val y: Int,
  private val style: IStyle = StyleFont.INSTANCE,
  private val hasHighlight: Boolean = false
) : IWidget {
  constructor(
    title: String, x: Int, y: Int,
    color: Color = Color.WHITE,
    shadowColor: Color = Color.GRAY,
    highlightColor: Color = Color.BLUE,
    highlightShadowColor: Color = Color.GRAY,
    hasShadow: Boolean = true,
    hasHighlight: Boolean = false
  ) : this(
    title, x, y,
    StyleFont(color, highlightColor, hasShadow, shadowColor, highlightShadowColor), hasShadow
  )

  constructor(
    title: String,
    x: Int,
    y: Int,
    hasShadow: Boolean,
    hasHighlight: Boolean
  ) : this(title, x, y, StyleFont(hasShadow = hasShadow), hasHighlight)

  val pos: UIPos = UIPos(
    x, y,
    Minecraft.getMinecraft().fontRenderer.getStringWidth(this.title),
    Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT + 4
  )

  override fun weightPos(): UIPos = this.pos

  override fun render(pGui: Gui, pMouseX: Int, pMouseY: Int, pGuiX: Int, pGuiY: Int) {
    val highlight = if (hasHighlight) isInRange(pMouseX, pMouseY, pGuiX, pGuiY) else false
    val tPos = this.weightPos()
    style.drawString(
      this.title, pGui, tPos.x, tPos.y, pGuiX, pGuiY,
      mapOf("highlight" to highlight)
    )
  }

  override fun getStyle(): IStyle = style
}
