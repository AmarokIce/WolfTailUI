package club.someoneice.wolftail.ui.widget

import club.someoneice.wolftail.api.IMouseEventListener
import club.someoneice.wolftail.api.IStyle
import club.someoneice.wolftail.api.IWidget
import club.someoneice.wolftail.util.UIPos
import net.minecraft.client.gui.Gui

open class WSwitch(
  private val pos: UIPos,
  private val style: IStyle
) : IWidget, IMouseEventListener {
  constructor(
    x: Int,
    y: Int,
    width: Int,
    height: Int,
    style: IStyle
  ) : this(UIPos(x, y, width, height), style)

  private var isOn = false

  override fun weightPos(): UIPos = this.pos
  override fun getStyle(): IStyle = this.style

  override fun canBeClick(): Boolean {
    return true
  }

  override fun onMouseClicked(pGui: Gui, pMouseX: Int, pMouseY: Int, pGuiX: Int, pGuiY: Int, pMouseButton: Int) {
    this.isOn = !this.isOn
  }

  override fun render(
    pGui: Gui,
    pMouseX: Int,
    pMouseY: Int,
    pGuiX: Int,
    pGuiY: Int
  ) {
    val flag = this.isInRange(pMouseX, pMouseY, pGuiX, pGuiY)

    this.getStyle().render(
      pGui, this.weightPos(), pGuiX, pGuiY,
      mapOf("highlight" to flag, "isOn" to this.isOn)
    )
  }
}
