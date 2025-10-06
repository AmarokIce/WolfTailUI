package club.someoneice.wolftail.wiget

import club.someoneice.wolftail.api.IStyle
import club.someoneice.wolftail.api.IWidgetFunction
import net.minecraft.client.gui.Gui
import org.lwjgl.util.Rectangle

open class WButton(
  private val title: String,
  private val pos: Rectangle,
  private val clicked: () -> Unit,
  private val style: IStyle
) : IWidgetFunction {
  constructor(title: String, x: Int, y: Int, w: Int, h: Int,
                     clicked: () -> Unit, style: IStyle
  ): this(title, Rectangle(x, y, w, h), clicked, style)

  protected constructor(title: String, x: Int, y: Int, w: Int, h: Int,
                        style: IStyle): this(title, x, y, w, h, {}, style)

  override fun canBeClick(): Boolean = true
  override fun weightPos(): Rectangle = this.pos
  override fun getStyle(): IStyle = this.style

  override fun onClick(pGui: Gui, pMouseX: Int, pMouseY: Int, pGuiY: Int, pGuiX: Int) {
    clicked.invoke()
  }

  override fun onPassed(pGui: Gui, pMouseX: Int, pMouseY: Int, pGuiY: Int, pGuiX: Int) {
  }

  override fun render(pGui: Gui, pMouseX: Int, pMouseY: Int, pGuiX: Int, pGuiY: Int) {
    val highlight = this.isInRange(pMouseX, pMouseY, pGuiX, pGuiY)

    val x = pGuiX + this.weightPos().x
    val y = pGuiY + this.weightPos().y

    val w = this.weightPos().width
    val h = this.weightPos().height

    this.style.render(pGui, pGuiX, pGuiY, w, h, highlight)
    this.style.drawString(title, pGui, pGuiX, pGuiY, highlight)
  }
}
