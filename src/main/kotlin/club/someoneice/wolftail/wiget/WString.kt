package club.someoneice.wolftail.wiget

import club.someoneice.wolftail.api.IStyle
import club.someoneice.wolftail.api.IWidget
import club.someoneice.wolftail.style.StyleFont
import net.minecraft.client.gui.Gui
import org.lwjgl.util.Rectangle
import java.awt.Color

open class WString(
  private val title: String,
  private val x: Int,
  private val y: Int,
  private val style: IStyle = StyleFont.INSTANCE,
  private val hasHighlight: Boolean = false
) : IWidget {
  constructor(title: String, x: Int, y: Int,
              color: Color = Color.WHITE,
              shadowColor: Color = Color.GRAY,
              highlightColor: Color = Color.BLUE,
              highlightShadowColor: Color = Color.GRAY,
              hasShadow: Boolean = true,
              hasHighlight: Boolean = false
  ): this(title, x, y,
    StyleFont(color, highlightColor, hasShadow, shadowColor, highlightShadowColor), hasShadow)

  constructor(title: String,
              x: Int,
              y: Int,
              hasShadow: Boolean = true,
              hasHighlight: Boolean = false
  ): this(title, x, y, StyleFont(hasShadow = hasShadow), hasHighlight)

  val pos: Rectangle = Rectangle(x, y, -1, -1)

  override fun weightPos(): Rectangle = this.pos

  override fun render(pGui: Gui, pMouseX: Int, pMouseY: Int, pGuiX: Int, pGuiY: Int) {
    val highlight = if (hasHighlight) isInRange(pMouseX, pMouseY, pGuiX, pGuiY) else false
    val tPos = this.weightPos()
    style.drawString(this.title, pGui, pGuiX + tPos.x, pGuiY + tPos.y, highlight)
  }

  override fun getStyle(): IStyle = style
}
