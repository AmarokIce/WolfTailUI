package club.someoneice.wolftail.wiget

import club.someoneice.wolftail.api.IStyle
import club.someoneice.wolftail.api.IWidget
import club.someoneice.wolftail.getStartAtByRec
import net.minecraft.client.gui.Gui

open class WString(
  private val title: String,
  private val style: IStyle
) : IWidget {
  override fun wightStartAt(): Pair<Int, Int> = getStartAtByRec(style.getUIRange())

  override fun render(pGui: Gui, pMouseX: Int, pMouseY: Int, pGuiX: Int, pGuiY: Int) {
    style.drawString(this.title, pGui, pGuiX, pGuiY)
  }

  override fun getStyle(): IStyle = style
}
