package club.someoneice.wolftail.ui.widget

import club.someoneice.wolftail.api.IStyle
import club.someoneice.wolftail.api.style.StyleFont
import club.someoneice.wolftail.ui.widget.WString
import com.google.common.base.Supplier
import net.minecraft.client.gui.Gui
import java.awt.Color

open class WVarString(
  protected val text: Supplier<String>,
  x: Int,
  y: Int,
  style: StyleFont = StyleFont.INSTANCE,
  hasHighlight: Boolean = false
): WString("", x, y, style, hasHighlight) {
  constructor(
    title: Supplier<String>,
    x: Int,
    y: Int,
    color: Color = Color.WHITE,
    shadowColor: Color = Color.GRAY,
    highlightColor: Color = Color.BLUE,
    highlightShadowColor: Color = Color.GRAY,
    hasShadow: Boolean = true,
    hasHighlight: Boolean = false
  ): this(title, x, y, StyleFont(color, highlightColor, hasShadow, shadowColor, highlightShadowColor), hasHighlight)

  constructor(
    title: Supplier<String>,
    x: Int,
    y: Int,
    hasShadow: Boolean,
    hasHighlight: Boolean
  ): this(title, x, y, StyleFont(hasShadow = hasShadow), hasHighlight)

  override fun render(pGui: Gui, pMouseX: Int, pMouseY: Int, pGuiX: Int, pGuiY: Int) {
    val highlight = hasHighlight && isInRange(pMouseX, pMouseY, pGuiX, pGuiY)
    val tPos = this.weightPos()
    this.getStyle().drawString(
      this.text.get(), pGui, tPos.x, tPos.y, pGuiX, pGuiY,
      mapOf("highlight" to highlight)
    )
  }
}
