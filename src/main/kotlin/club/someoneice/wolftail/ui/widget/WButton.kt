package club.someoneice.wolftail.ui.widget

import club.someoneice.wolftail.api.IStyle
import club.someoneice.wolftail.api.IWidgetFunction
import net.minecraft.client.gui.Gui
import org.lwjgl.util.Rectangle

open class WButton(
  private val title: String,
  private val pos: Rectangle,
  private val style: IStyle,
  private val clicked: () -> Unit = {}
) : IWidgetFunction {
  constructor(title: String, x: Int, y: Int, w: Int, h: Int, style: IStyle,
              clicked: () -> Unit = {}
  ): this(title, Rectangle(x, y, w, h), style, clicked)

  override fun weightPos(): Rectangle = this.pos
  override fun getStyle(): IStyle = this.style

  override fun onMouseClicked(pGui: Gui, pMouseX: Int, pMouseY: Int, pMouseButton: Int): Boolean {
    clicked()
    TODO()
    return false
  }

  override fun render(pGui: Gui, pMouseX: Int, pMouseY: Int, pGuiX: Int, pGuiY: Int) {
    val highlight = this.isInRange(pMouseX, pMouseY, pGuiX, pGuiY)

    val x = pGuiX + this.weightPos().x
    val y = pGuiY + this.weightPos().y

    val w = this.weightPos().width
    val h = this.weightPos().height

    this.style.render(pGui, pGuiX, pGuiY, w, h, mapOf("highlight" to highlight))
    this.style.drawString(title, pGui, pGuiX, pGuiY, mapOf("highlight" to highlight))
  }
}
