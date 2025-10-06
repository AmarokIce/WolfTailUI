package club.someoneice.wolftail.wiget

import club.someoneice.wolftail.api.IStyle
import club.someoneice.wolftail.api.IWidgetFunction
import net.minecraft.client.gui.Gui
import org.lwjgl.util.Rectangle

open class WSwitch(
  private val pos: Rectangle,
  private val sytle: IStyle
) : IWidgetFunction {
  constructor(
    x: Int,
    y: Int,
    width: Int,
    height: Int,
    style: IStyle
  ): this(Rectangle(x, y, width, height), style)

  private var isOn = false

  override fun weightPos(): Rectangle = this.pos

  override fun canBeClick(): Boolean {
    return true
  }

  override fun onClick(pGui: Gui, pMouseX: Int, pMouseY: Int, pGuiY: Int, pGuiX: Int) {
    this.isOn = !this.isOn
  }

  override fun onPassed(pGui: Gui, pMouseX: Int, pMouseY: Int, pGuiY: Int, pGuiX: Int) {
  }

  override fun render(
    pGui: Gui,
    pMouseX: Int,
    pMouseY: Int,
    pGuiX: Int,
    pGuiY: Int
  ) {
    val flag = this.isInRange(pMouseX, pMouseY, pGuiX, pGuiY)

    this.getStyle().render(pGui, pMouseX, pMouseY,
      pGuiX + this.weightPos().x, pGuiY + this.weightPos().y, flag)
  }

  override fun getStyle(): IStyle = this.sytle
}
