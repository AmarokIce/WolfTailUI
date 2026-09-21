package club.someoneice.wolftail.ui.core

import club.someoneice.wolftail.api.IKeyboardEventListener
import club.someoneice.wolftail.api.IMouseEventListener
import club.someoneice.wolftail.api.ITooltip
import club.someoneice.wolftail.api.IWidget
import club.someoneice.wolftail.api.style.IStyleUI
import club.someoneice.wolftail.ui.widget.WScrollList
import club.someoneice.wolftail.util.UIPos
import com.google.common.collect.Lists
import net.minecraft.client.gui.FontRenderer
import net.minecraft.client.gui.GuiScreen
import org.lwjgl.input.Mouse
import java.util.*

abstract class WScreen(val w: Int, val h: Int): GuiScreen() {
  protected val widgets: ArrayList<IWidget> = Lists.newArrayList()
  protected val mouseEventWidgets: ArrayList<IMouseEventListener> = Lists.newArrayList()
  protected val keyboardEventWidgets: ArrayList<IKeyboardEventListener> = Lists.newArrayList()
  protected val scrollWidget: ArrayList<WScrollList> = Lists.newArrayList()
  protected var inMouseWidget: IMouseEventListener? = null

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

    if (widget is WScrollList) {
      scrollWidget.add(widget)
    }
  }

  /* protected -> public */
  public override fun drawHoveringText(list: List<*>, pMouseX: Int, pMouseY: Int, font: FontRenderer) {
    super.drawHoveringText(list, pMouseX, pMouseY, font)
  }

  override fun drawBackground(sign: Int) {
    val x: Int = if (this.w == -1) 0 else (this.width - this.w) / 2
    val y: Int = if (this.h == -1) 0 else (this.height - this.h) / 2
    this.getStyle().render(this, UIPos(x, y, w, h), 0, 0)
  }

  override fun drawScreen(mouseX: Int, mouseY: Int, partialTicks: Float) {
    super.drawScreen(mouseX, mouseY, partialTicks)
    this.drawBackground(0)

    val x: Int = if (this.w == -1) 0 else (this.width - this.w) / 2
    val y: Int = if (this.h == -1) 0 else (this.height - this.h) / 2
    this.widgets.forEach {
      it.render(this, mouseX, mouseY, x, y)

      if (it is ITooltip && it.isInRange(mouseX, mouseY, x, y)) {
        val list = it.getData()
        this.drawHoveringText(list, mouseX, mouseY, this.fontRendererObj)
      }
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
    val x: Int = if (this.w == -1) 0 else (this.width - this.w) / 2
    val y: Int = if (this.h == -1) 0 else (this.height - this.h) / 2

    val mouseEvent = this.mouseEventWidgets
      .filter(IMouseEventListener::canBeClick)

    mouseEvent.firstOrNull {
      it.isInRange(pMouseX, pMouseY, x, y)
    }?.let {
      it.onMouseClicked(this, pMouseX, pMouseY,x , y, pMouseKeyInput)
      this.inMouseWidget = it
    }
  }



  override fun mouseMovedOrUp(pMouseX: Int, pMouseY: Int, opt: Int) {
    super.mouseMovedOrUp(pMouseX, pMouseY, opt)
    if (Objects.isNull(this.inMouseWidget) || opt != 0) {
      return
    }

    val x: Int = if (this.w == -1) 0 else (this.width - this.w) / 2
    val y: Int = if (this.h == -1) 0 else (this.height - this.h) / 2
    this.inMouseWidget!!.onMouseReleased(this, pMouseX, pMouseY, x, y)
    this.inMouseWidget = null
  }

  override fun mouseClickMove(pMouseX: Int, pMouseY: Int, pMouseButton: Int, time: Long) {
    super.mouseClickMove(pMouseX, pMouseY, pMouseButton, time)
    if (Objects.isNull(this.inMouseWidget)) {
      return
    }

    val x: Int = if (this.w == -1) 0 else (this.width - this.w) / 2
    val y: Int = if (this.h == -1) 0 else (this.height - this.h) / 2
    this.inMouseWidget!!.onMouseMove(this, pMouseX, pMouseY)
  }

  override fun keyTyped(char: Char, keyCode: Int) {
    val x: Int = if (this.w == -1) 0 else (this.width - this.w) / 2
    val y: Int = if (this.h == -1) 0 else (this.height - this.h) / 2

    keyboardEventWidgets.forEach {
      it.onKeyboardInput(this, char, keyCode, x, y)
    }
    super.keyTyped(char, keyCode)
  }

  override fun initGui() {
    this.initWidgets()
  }
}
