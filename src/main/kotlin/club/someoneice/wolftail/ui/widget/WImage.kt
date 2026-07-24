package club.someoneice.wolftail.ui.widget

import club.someoneice.wolftail.api.IWidget
import club.someoneice.wolftail.api.style.IStyleImage
import club.someoneice.wolftail.util.UIPos
import net.minecraft.client.gui.Gui

open class WImage(
  private val pos: UIPos,
  private val style: IStyleImage
) : IWidget {
  override fun weightPos(): UIPos = this.pos
  override fun getStyle(): IStyleImage = this.style

  override fun render(
    pGui: Gui,
    pMouseX: Int,
    pMouseY: Int,
    pGuiX: Int,
    pGuiY: Int
  ) {
    this.style.render(pGui, this.pos, pGuiX, pGuiY)
  }
}
