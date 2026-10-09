package club.someoneice.wolftail.ui.widget

import club.someoneice.wolftail.api.IKeyboardEventListener
import club.someoneice.wolftail.api.IMouseEventListener
import club.someoneice.wolftail.api.IStyle
import club.someoneice.wolftail.api.IWidget
import club.someoneice.wolftail.util.UIPos
import club.someoneice.wolftail.util.UtilGui
import net.minecraft.client.gui.Gui
import net.minecraft.client.gui.GuiScreen
import org.lwjgl.input.Keyboard
import java.awt.Color
import kotlin.math.abs
import kotlin.math.min


open class WEditField(
  private val pos: UIPos,
  protected val offset: Pair<Int, Int> = 2 to 2,
  private val style: IStyle
) : IWidget, IMouseEventListener, IKeyboardEventListener {
  constructor(
    x: Int, y: Int, w: Int, h: Int,
    offsetX: Int = 2, offsetY: Int = 2,
    style: IStyle
  ) : this(UIPos(x, y, w, h), offsetX to offsetY, style)

  protected val text = StringBuilder()
  protected var cursorPos = 0
  protected var selection = 0
  protected var focused = false

  fun getText(): String = text.toString()

  @JvmName("setCursorPosition")
  fun setCursorPos(x: Int) {
    this.cursorPos = x
    this.selection = x
  }

  fun isSelected(): Boolean = focused && selection != cursorPos

  fun getSelected(): String {
    if (!isSelected()) {
      return ""
    }

    return this.text.substring(
      this.selection.coerceIn(0, this.cursorPos),
      this.selection.coerceIn(this.cursorPos, this.text.length)
    )
  }

  fun deleteSelected(): String {
    if (!isSelected()) {
      return ""
    }

    val start = this.selection.coerceIn(0, this.cursorPos)
    val txt = getText()
    this.text.deleteRange(
      start,
      this.selection.coerceIn(this.cursorPos, this.text.length)
    )

    this.setCursorPos(start)
    return txt
  }

  protected fun handleCtrlKeycode(pGui: Gui, keyChar: Char, keyCode: Int, pGuiX: Int, pGuiY: Int): Boolean {
    if (!GuiScreen.isCtrlKeyDown()) {
      return false
    }

    when (keyCode) {
      Keyboard.KEY_A -> {
        this.cursorPos = this.text.length
        this.selection = 0
        return true
      }

      Keyboard.KEY_C -> {
        GuiScreen.setClipboardString(this.getText())
        return true
      }

      Keyboard.KEY_V -> {
        this.text.append(GuiScreen.getClipboardString())
        return true
      }

      Keyboard.KEY_X -> {
        GuiScreen.setClipboardString(this.deleteSelected())
        return true
      }
    }

    return false
  }

  protected fun handleKeycode(pGui: Gui, keyChar: Char, keyCode: Int, pGuiX: Int, pGuiY: Int) {
    when (keyCode) {
      Keyboard.KEY_BACK -> {
        if (isSelected()) {
          deleteSelected()
          return
        }

        if (this.cursorPos <= 0) {
          return
        }

        this.text.deleteCharAt(--this.cursorPos)
        this.selection = this.cursorPos
      }

      Keyboard.KEY_DELETE -> {
        if (isSelected()) {
          deleteSelected()
          return
        }

        if (this.cursorPos >= this.text.length) {
          return
        }

        this.text.deleteCharAt(this.cursorPos)
        this.selection = this.cursorPos
      }

      Keyboard.KEY_LEFT -> {
        this.cursorPos--
        if (!GuiScreen.isShiftKeyDown()) {
          this.selection = this.cursorPos
          return
        }

        if (!this.isSelected()) {
          this.selection = this.cursorPos + 1
        }
      }

      Keyboard.KEY_RIGHT -> {
        this.cursorPos++
        if (!GuiScreen.isShiftKeyDown()) {
          this.selection = this.cursorPos
          return
        }

        if (!this.isSelected()) {
          this.selection = this.cursorPos - 1
        }
      }

      Keyboard.KEY_HOME -> {
        this.setCursorPos(0)
      }

      Keyboard.KEY_END -> {
        this.setCursorPos(this.text.length)
      }

      // TODO: Multi-Text?
      Keyboard.KEY_RETURN, Keyboard.KEY_NUMPADENTER -> {
        this.focused = false
      }

      else -> {
        if (UtilGui.mc.fontRenderer.getStringWidth(this.getText() + keyChar) > this.pos.w) {
          return
        }

        this.text.insert(this.cursorPos.coerceIn(0, this.text.length), keyChar.toString())
        this.setCursorPos(this.cursorPos + 1)
      }
    }
  }

  fun getCursorPosOnText(mouseX: Int, textX: Int): Int {
    val font = UtilGui.mc.fontRenderer
    if (this.text.isEmpty()) {
      return 0
    }
    val rX = mouseX - textX
    if (rX <= 0) {
      return 0
    }

    val index = font.trimStringToWidth(this.getText(), rX).length
    if (index >= this.text.length) {
      return index
    }

    val prev = font.getStringWidth(this.text.substring(0, index))
    val next = font.getStringWidth(this.text.substring(0, index + 1))

    val distPrev = abs(rX - prev)
    val distNext = abs(rX - next)
    if (distNext < distPrev) {
      return index + 1
    }

    return index
  }

  override fun weightPos(): UIPos = this.pos
  override fun getStyle(): IStyle = this.style

  override fun render(pGui: Gui, pMouseX: Int, pMouseY: Int, pGuiX: Int, pGuiY: Int) {
    this.style.render(pGui, this.weightPos(), pGuiX, pGuiY, mapOf("highlight" to this.focused))

    val font = UtilGui.mc.fontRenderer

    if (this.text.isEmpty()) {
      return
    }

    if (this.isSelected()) {
      val start = this.selection.coerceAtMost(this.cursorPos).coerceIn(0, this.text.length)

      GuiScreen.drawRect(
        this.pos.x + pGuiX + this.offset.first +
          font.getStringWidth(this.text.substring(0, start)),
        this.pos.y + pGuiY + this.offset.second,
        this.pos.x + pGuiX + this.offset.first + font.getStringWidth(this.getSelected()),
        this.pos.y + pGuiY + this.offset.second + font.FONT_HEIGHT,
        Color.BLUE.rgb
      )
    }

    this.style.drawString(this.getText(), pGui,
      this.pos.x + this.offset.first,
      this.pos.y + this.offset.second,
      pGuiX, pGuiY
    )

    if (this.focused && (System.currentTimeMillis() / 500) % 2 == 0L) {
      val p = font.getStringWidth(this.text.substring(0,
        this.cursorPos.coerceIn(0, this.text.length)))
      GuiScreen.drawRect(
        this.pos.x + pGuiX + this.offset.first + p,
        this.pos.y + pGuiY + this.offset.second,
        this.pos.x + pGuiX + this.offset.first + p + 1,
        this.pos.y + pGuiY + this.offset.second + font.FONT_HEIGHT,
        Color.WHITE.rgb
      )
    }
  }

  override fun onKeyboardInput(pGui: Gui, keyChar: Char, keyCode: Int, pGuiX: Int, pGuiY: Int) {
    if (!this.focused) {
      return
    }

    if (this.handleCtrlKeycode(pGui, keyChar, keyCode, pGuiX, pGuiY)) {
      return
    }

    this.handleKeycode(pGui, keyChar, keyCode, pGuiX, pGuiY)
  }

  override fun onMouseClicked(pGui: Gui, pMouseX: Int, pMouseY: Int, pGuiX: Int, pGuiY: Int, pMouseButton: Int) {
    this.focused = true
    val index = this.getCursorPosOnText(pMouseX, this.pos.x + this.offset.first + pGuiX)
    this.setCursorPos(min(index, this.text.length))
  }

  override fun onMouseMove(pGui: Gui, pMouseX: Int, pMouseY: Int, pGuiX: Int, pGuiY: Int, pMouseButton: Int) {
    if (!this.focused) {
      return
    }

    this.cursorPos = this.getCursorPosOnText(pMouseX, this.pos.x + this.offset.first + pGuiX)
  }

  override fun onMouseClickedOutside(pGui: Gui, pMouseX: Int, pMouseY: Int, pGuiX: Int, pGuiY: Int, pMouseButton: Int) {
    this.focused = false
  }
}
