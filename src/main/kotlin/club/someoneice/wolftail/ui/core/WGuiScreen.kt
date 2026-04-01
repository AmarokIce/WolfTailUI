package club.someoneice.wolftail.ui.core

import club.someoneice.wolftail.api.IUIStyle
import club.someoneice.wolftail.api.IWidget
import club.someoneice.wolftail.api.IWidgetFunction
import com.google.common.collect.Sets
import net.minecraft.client.gui.GuiScreen

abstract class WGuiScreen(val w: Int, val h: Int): GuiScreen() {
  protected val widgets = Sets.newHashSet<IWidget>()
  protected val widgetsOnClicked = Sets.newHashSet<IWidgetFunction>()

  abstract fun initWidgets()

  abstract fun getStyle(): IUIStyle

  fun addWidget(widget: IWidget) {
    this.widgets.add(widget)
  }

  override fun drawBackground(p_146278_1_: Int) {
    this.mc.renderEngine.bindTexture(this.getStyle().getTexture())
    val x: Int = (this.width - this.w) / 2
    val y: Int = (this.height - this.h) / 2
    this.getStyle().drawBackground(this, x, y, w, h)
  }

  override fun mouseClicked(pMouseX: Int, pMouseY: Int, pMouseKeyInput: Int) {
    if (pMouseKeyInput != 0) {
      return
    }

    val x: Int = (this.width - this.w) / 2
    val y: Int = (this.height - this.h) / 2

    this.widgets
      .filterIsInstance<IWidgetFunction>()
      .filter(IWidgetFunction::canBeClick)
      .filter { it.isInRange(pMouseX, pMouseY, x, y) }
      .forEach {
        it.onMouseClicked(this, pMouseX, pMouseY, pMouseKeyInput)
        widgetsOnClicked.add(it)
      }
  }

  override fun mouseMovedOrUp(pMouseX: Int, pMouseY: Int, pMouseAction: Int) {
    widgetsOnClicked.forEach {
      it.onMousePassed(this, pMouseX, pMouseY)
    }
    widgetsOnClicked.clear()
  }

  override fun mouseClickMove(p_146273_1_: Int, p_146273_2_: Int, p_146273_3_: Int, p_146273_4_: Long) {
    super.mouseClickMove(p_146273_1_, p_146273_2_, p_146273_3_, p_146273_4_)
  }
}
