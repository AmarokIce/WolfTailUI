package club.someoneice.wolftail.ui.core

import club.someoneice.wolftail.api.IKeyboardEventListener
import club.someoneice.wolftail.api.IMouseEventListener
import club.someoneice.wolftail.api.style.IStyleUI
import club.someoneice.wolftail.api.IWidget
import club.someoneice.wolftail.ui.widget.WScrollContainer
import club.someoneice.wolftail.util.UIPos
import com.google.common.collect.Lists
import net.minecraft.client.gui.GuiScreen
import org.lwjgl.input.Mouse
import java.util.ArrayList

abstract class WScreen(val w: Int, val h: Int): GuiScreen() {
  protected val widgets: ArrayList<IWidget> = Lists.newArrayList()
  protected val mouseEventWidgets: ArrayList<IMouseEventListener> = Lists.newArrayList()
  protected val keyboardEventWidgets: ArrayList<IKeyboardEventListener> = Lists.newArrayList()
  protected val scrollWidget: ArrayList<WScrollContainer> = Lists.newArrayList()

  abstract fun initWidgets()

  abstract fun getStyle(): IStyleUI

  abstract fun render(mouseX: Int, mouseY: Int, partialTicks: Float)

  fun addWidget(widget: IWidget) {
    this.widgets.add(widget)
    if (widget is IMouseEventListener) {
      mouseEventWidgets.add(widget)
    }

    if (widget is IKeyboardEventListener) {
      keyboardEventWidgets.add(widget)
    }

    if (widget is WScrollContainer) {
      scrollWidget.add(widget)
    }
  }

  override fun drawBackground(sign: Int) {
    val x: Int = (this.width - this.w) / 2
    val y: Int = (this.height - this.h) / 2
    this.getStyle().render(this, UIPos(x, y, w, h), 0, 0)
  }

  override fun drawScreen(mouseX: Int, mouseY: Int, partialTicks: Float) {
    super.drawScreen(mouseX, mouseY, partialTicks)
    this.drawBackground(0)

    val x: Int = (this.width - this.w) / 2
    val y: Int = (this.height - this.h) / 2
    this.widgets.forEach {
      it.render(this, mouseX, mouseY, x, y)
    }

    render(mouseX, mouseY, partialTicks)
  }

  override fun handleMouseInput() {
    super.handleMouseInput()
    val dWheel = Mouse.getEventDWheel()
    if (dWheel == 0) {
      return
    }

    this.scrollWidget.firstOrNull {
          val mx = Mouse.getEventX() * this.width / this.mc.displayWidth
          val my = this.height - Mouse.getEventY() * this.height / this.mc.displayHeight - 1

          val x: Int = (this.width - this.w) / 2
          val y: Int = (this.height - this.h) / 2

          it.isInRange(mx, my, x, y)
        }?.handleMouseInput(dWheel)
  }

  override fun mouseClicked(pMouseX: Int, pMouseY: Int, pMouseKeyInput: Int) {
    if (pMouseKeyInput != 0) {
      return
    }

    val x: Int = (this.width - this.w) / 2
    val y: Int = (this.height - this.h) / 2

    val mouseEvent = this.mouseEventWidgets
      .filter(IMouseEventListener::canBeClick)

    val inRange = mouseEvent.firstOrNull {
      it.isInRange(pMouseX, pMouseY, x, y)
    }

    mouseEvent.forEach {
      if (it == inRange) {
        it.onMouseClicked(this, pMouseX, pMouseY,x , y, pMouseKeyInput)
        return@forEach
      }

      it.onMousePressed(this, pMouseX, pMouseY, x, y)
    }

  }

  override fun mouseMovedOrUp(pMouseX: Int, pMouseY: Int, opt: Int) {
    // TODO
  }

  override fun keyTyped(p_73869_1_: Char, p_73869_2_: Int) {
    super.keyTyped(p_73869_1_, p_73869_2_)
  }

  override fun initGui() {
    this.initWidgets()
  }
}
