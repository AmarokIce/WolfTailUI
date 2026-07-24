package club.someoneice.wolftail.ui.widget

import club.someoneice.wolftail.WolfTailUI
import club.someoneice.wolftail.api.IKeyboardEventListener
import club.someoneice.wolftail.api.IMouseEventListener
import club.someoneice.wolftail.api.IStyle
import club.someoneice.wolftail.api.IWidget
import club.someoneice.wolftail.style.StyleAdapter
import club.someoneice.wolftail.util.UIPos
import com.google.common.collect.Lists
import net.minecraft.client.gui.Gui

open class WBox(
  private val pos: UIPos,
  private val style: IStyle = StyleAdapter.EMPTY_STYLE
): IWidget, IMouseEventListener, IKeyboardEventListener {
  private val child = Lists.newArrayList<IWidget>()

  fun addChild(child: IWidget): WBox {
    val cpos = child.weightPos()
    if (cpos.x + cpos.w > this.pos.h || cpos.y + cpos.w > this.pos.h) {
      WolfTailUI.LOG.warn("Child widget failed added to box because it too large to the box!")
      return this
    }
    this.child.add(child)
    return this
  }

  override fun weightPos(): UIPos = this.pos
  override fun getStyle(): IStyle = this.style

  override fun render(
    pGui: Gui,
    pMouseX: Int,
    pMouseY: Int,
    pGuiX: Int,
    pGuiY: Int
  ) {
    child.forEach {
      it.render(pGui, pMouseX, pMouseY, pGuiX + this.pos.x, pGuiY + this.pos.y)
    }
  }

  override fun onMouseClicked(
    pGui: Gui,
    pMouseX: Int,
    pMouseY: Int,
    pGuiX: Int,
    pGuiY: Int,
    pMouseButton: Int
  ) {
    val pX = this.pos.x + pGuiX
    val pY = this.pos.y + pGuiY
    child
      .filterIsInstance<IMouseEventListener>()
      .filter { it.isInRange(pMouseX, pMouseY, pX, pY) }
      .forEach {
        it.onMouseClicked(pGui, pMouseX, pMouseY, pX, pY, pMouseButton)
      }
  }

  override fun onKeyboardInput(
    pGui: Gui,
    keyChar: Char,
    keyCode: Int,
    pGuiX: Int,
    pGuiY: Int
  ) {
    val pX = this.pos.x + pGuiX
    val pY = this.pos.y + pGuiY
    child
      .filterIsInstance<IKeyboardEventListener>()
      .forEach {
        it.onKeyboardInput(pGui, keyChar, keyCode, pX, pY)
      }
  }
}
