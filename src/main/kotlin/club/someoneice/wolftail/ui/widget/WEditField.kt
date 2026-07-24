package club.someoneice.wolftail.ui.widget

import club.someoneice.wolftail.api.IKeyboardEventListener
import club.someoneice.wolftail.api.IMouseEventListener
import club.someoneice.wolftail.api.IStyle
import club.someoneice.wolftail.api.IWidget
import club.someoneice.wolftail.util.UIPos
import net.minecraft.client.gui.Gui
import org.lwjgl.input.Keyboard

open class WEditField(
  private val pos: UIPos,
  private val offset: Pair<Int, Int> = 2 to 2,
  private val style: IStyle
) : IWidget, IMouseEventListener, IKeyboardEventListener {
  constructor(
    x: Int, y: Int, w: Int, h: Int,
    offsetX: Int = 2, offsetY: Int = 2,
    style: IStyle
  ) : this(UIPos(x, y, w, h), offsetX to offsetY, style)

  protected val text = StringBuilder()
  protected var isOn = false

  fun getText(): String = text.toString()

  override fun weightPos(): UIPos = this.pos
  override fun getStyle(): IStyle = this.style

  // TODO
  override fun render(pGui: Gui, pMouseX: Int, pMouseY: Int, pGuiX: Int, pGuiY: Int) {
    this.style.render(pGui, this.weightPos(), pGuiX, pGuiY, mapOf("highlight" to this.isOn))
    if (this.text.isEmpty()) {
      return
    }

    // TODO: The input line pos.
    var text = this.getText()
    if (this.isOn) {
      text += "_"
    }

    text.split("\n").forEachIndexed { index, it ->
      this.style.drawString(
        it, pGui,
        offset.first, offset.second + index * 18, pGuiX, pGuiY
      )
    }
  }

  override fun onKeyboardInput(pGui: Gui, keyChar: Char, keyCode: Int, pGuiX: Int, pGuiY: Int) {
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

  override fun onMouseClicked(pGui: Gui, pMouseX: Int, pMouseY: Int, pGuiX: Int, pGuiY: Int, pMouseButton: Int) {
    this.isOn = true
  }

  override fun onMousePressed(pGui: Gui, pMouseX: Int, pMouseY: Int, pGuiX: Int, pGuiY: Int) {
    this.isOn = false
  }
}
