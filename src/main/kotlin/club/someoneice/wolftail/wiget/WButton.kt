package club.someoneice.wolftail.wiget

import club.someoneice.wolftail.api.IStyle
import club.someoneice.wolftail.api.IWidgetFunction
import club.someoneice.wolftail.getEndAtByRec
import club.someoneice.wolftail.getStartAtByRec
import net.minecraft.client.gui.Gui

abstract class WButton(
  private val title: String,
  private val clicked: () -> Unit,
  private val style: IStyle
) : IWidgetFunction {
  override fun canBeClick(): Boolean = true
  override fun getStyle(): IStyle = this.style
  override fun wightStartAt(): Pair<Int, Int> = getStartAtByRec(style.getUIRange())
  override fun wightEndAt(): Pair<Int, Int> = getEndAtByRec(style.getUIRange())

  override fun onClick(pGui: Gui, pMouseX: Int, pMouseY: Int, pGuiY: Int, pGuiX: Int) {
    if (!this.isInRange(pMouseX, pMouseY, pGuiY, pGuiX)) {
      return
    }

    clicked()
  }

  override fun onPassed(pGui: Gui, pMouseX: Int, pMouseY: Int, pGuiY: Int, pGuiX: Int) {
  }

  override fun render(pGui: Gui, pMouseX: Int, pMouseY: Int, pGuiX: Int, pGuiY: Int) {
    this.style.render(pGui, pGuiX, pGuiY)
    this.style.drawString(title, pGui, pGuiX, pGuiY)
  }
}
