package club.someoneice.wolftail.wiget

import club.someoneice.wolftail.api.IWidget
import club.someoneice.wolftail.getMC
import net.minecraft.client.gui.Gui
import net.minecraft.client.resources.I18n
import java.awt.Color

open class WString(
  private val title: String,
  private val startPos: Pair<Int, Int>,
  private val color: Color = Color.WHITE,
  private val shadow: Color? = Color.BLACK
) : IWidget {
  override fun wightStartAt(): Pair<Int, Int> = startPos

  override fun render(gui: Gui, mouseX: Int, mouseY: Int, x: Int, y: Int) {
    val font = getMC().fontRenderer
    val posX = x + this.wightStartAt().first
    val posY = y + this.wightStartAt().second
    shadow?.let {
      font.drawString(I18n.format(title), posX + 1, posY + 1, it.rgb)
    }
    font.drawString(I18n.format(title), posX, posY, color.rgb)
  }
}
