package club.someoneice.wolftail.style

import club.someoneice.wolftail.api.StyleAdapter
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.Gui
import java.awt.Color

open class StyleFont(private val color: Color = Color.WHITE,
                     private val highlightColor: Color = Color.BLUE,
                     private val hasShadow: Boolean = true,
                     private val shadowColor: Color = Color.GRAY,
                     private val highlightShadowColor: Color = Color.GRAY
) : StyleAdapter() {
  override fun drawString(
    pString: String,
    pGui: Gui,
    x: Int,
    y: Int,
    args: Map<String, Any>
  ) {
    val color: Color
    val shadowColor: Color

    val highlight = args.containsKey("highlight") && args["highlight"] == true

    if (highlight) {
      color = this.highlightColor
      shadowColor = this.highlightShadowColor
    } else {
      color = this.color
      shadowColor = this.shadowColor
    }

    val font = Minecraft.getMinecraft().fontRenderer

    if (hasShadow) {
      font.drawString(pString, x + 1, y + 1, shadowColor.rgb)
    }

    font.drawString(pString, x, y, color.rgb)
  }

  companion object {
    val INSTANCE = StyleFont()
  }
}
