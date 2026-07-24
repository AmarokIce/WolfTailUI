package club.someoneice.wolftail.debug

import club.someoneice.wolftail.api.style.IUIStyle
import club.someoneice.wolftail.api.style.StyleAdapter
import club.someoneice.wolftail.ui.core.WScreen
import club.someoneice.wolftail.ui.widget.WScrollContainer
import club.someoneice.wolftail.ui.widget.WString
import org.lwjgl.opengl.GL11

class DebugGUI: WScreen(196, 168) {
  override fun initWidgets() {
    this.addWidget(WString("Help me", 20, 40))
    this.addWidget(WScrollContainer(20, 60, 160, 120)
        .addChild(WString("Pineapple", 5, 5, hasShadow = false, hasHighlight = false))
        .addChild(WString("Coffee", 5, 5, hasShadow = false, hasHighlight = false))
        .addChild(WString("Mutton", 5, 5, hasShadow = false, hasHighlight = false))
        .addChild(WString("Pineapple", 5, 5, hasShadow = false, hasHighlight = false))
        .addChild(WString("Coffee", 5, 5, hasShadow = false, hasHighlight = false))
        .addChild(WString("Mutton", 5, 5, hasShadow = false, hasHighlight = false))
        .addChild(WString("Pineapple", 5, 5, hasShadow = false, hasHighlight = false))
        .addChild(WString("Coffee", 5, 5, hasShadow = false, hasHighlight = false))
        .addChild(WString("Mutton", 5, 5, hasShadow = false, hasHighlight = false))
        .addChild(WString("Pineapple", 5, 5, hasShadow = false, hasHighlight = false))
        .addChild(WString("Coffee", 5, 5, hasShadow = false, hasHighlight = false))
        .addChild(WString("Mutton", 5, 5, hasShadow = false, hasHighlight = false))
    )
  }

  override fun render(mouseX: Int, mouseY: Int, partialTicks: Float) {
  }

  override fun drawScreen(mouseX: Int, mouseY: Int, partialTicks: Float) {
    GL11.glClearColor(1f, 1f, 1f, 1f)
    this.drawWorldBackground(0)

    val x: Int = (this.width - this.w) / 2
    val y: Int = (this.height - this.h) / 2
    this.widgets.forEach {
      it.render(this, mouseX, mouseY, x, y)
    }
  }

  override fun doesGuiPauseGame(): Boolean {
    return false
  }

  override fun getStyle(): IUIStyle = StyleAdapter.EMPTY_UI_STYLE
}
