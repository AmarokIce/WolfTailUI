package club.someoneice.wolftail.widget

import club.someoneice.wolftail.api.IStyle
import club.someoneice.wolftail.api.IWidgetFunction
import net.minecraft.client.gui.Gui
import org.lwjgl.input.Keyboard
import org.lwjgl.util.Rectangle

open class WLabel(
  val pos: Rectangle,
  val offset: Pair<Int, Int> = 2 to 2,
  val style: IStyle
) : IWidgetFunction {
  constructor(x: Int, y: Int, w: Int, h: Int,
              offsetX: Int = 2, offsetY: Int = 2,
              style: IStyle
  ): this(Rectangle(x, y, w, h), offsetX to offsetY, style)

  protected val text = StringBuilder()
  protected var isOn = false

  fun getText(): String = text.toString()


  override fun weightPos(): Rectangle = this.pos
  override fun getStyle(): IStyle = this.style

  override fun render(pGui: Gui, pMouseX: Int, pMouseY: Int, pGuiX: Int, pGuiY: Int) {
    this.style.render(pGui, pMouseX, pMouseY, pGuiX, pGuiY
    , mapOf("highlight" to this.isOn))
    if (this.text.isEmpty()) return
    this.style.drawString(this.getText(), pGui,
      pGuiX + offset.first, pGuiY + offset.second)
  }

  override fun onKeyboardInput(pGui: Gui, keyChar: Char, keyCode: Int) {
    if (!this.isOn) return

    if (keyCode == Keyboard.KEY_BACK && this.text.isNotEmpty()) {
      this.text.deleteCharAt(this.text.length - 1)
      return
    }

    if (keyCode == Keyboard.KEY_RETURN || keyCode == Keyboard.KEY_NUMPADENTER) {
      this.isOn = false
      return
    }

    this.text.append(keyChar)
  }

  override fun onClick(pGui: Gui, pMouseX: Int, pMouseY: Int) {
    this.isOn = true
  }

  override fun onPassed(pGui: Gui, pMouseX: Int, pMouseY: Int) {
    this.isOn = false
  }
}
