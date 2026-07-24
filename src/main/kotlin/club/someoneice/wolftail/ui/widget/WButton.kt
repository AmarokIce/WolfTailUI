package club.someoneice.wolftail.ui.widget

import club.someoneice.wolftail.api.IMouseEventListener
import club.someoneice.wolftail.api.IStyle
import club.someoneice.wolftail.api.IWidget
import club.someoneice.wolftail.util.UIPos
import net.minecraft.client.gui.Gui

open class WButton(
  private val title: String,
  private val pos: UIPos,
  private val style: IStyle,
  private val clicked: () -> Unit = {}
) : IWidget, IMouseEventListener {
  constructor(
    title: String, x: Int, y: Int, w: Int, h: Int, style: IStyle,
    clicked: () -> Unit = {}
  ) : this(title, UIPos(x, y, w, h), style, clicked)

  override fun weightPos(): UIPos = this.pos
  override fun getStyle(): IStyle = this.style

  override fun onMouseClicked(pGui: Gui, pMouseX: Int, pMouseY: Int, pGuiX: Int, pGuiY: Int, pMouseButton: Int) {
    clicked()
  }

  override fun render(pGui: Gui, pMouseX: Int, pMouseY: Int, pGuiX: Int, pGuiY: Int) {
    val highlight = this.isInRange(pMouseX, pMouseY, pGuiX, pGuiY)

    this.style.render(pGui, this.weightPos(), pGuiX, pGuiY,
      mapOf(
        "highlight" to highlight,
        "title" to title
      ))
  }
}
