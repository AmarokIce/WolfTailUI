package club.someoneice.wolftail.debug

import club.someoneice.wolftail.api.style.IStyleUI
import club.someoneice.wolftail.api.style.StyleAdapter
import club.someoneice.wolftail.ui.core.WScreen
import club.someoneice.wolftail.ui.widget.WEditField

class DebugGUI: WScreen(196, 168) {
  override fun initWidgets() {
//    this.addWidget(WString("Help me", 20, 5))
//    this.addWidget(WScrollList(20, 10, 160, 40, StyleAdapter.INSTANCE)
//        .addChild(WString("Pineapple", 5, 5, hasShadow = false, hasHighlight = true))
//        .addChild(WString("Coffee", 5, 5, hasShadow = false, hasHighlight = true))
//        .addChild(WString("Mutton", 5, 5, hasShadow = false, hasHighlight = true))
//    )
    this.addWidget(
      WEditField(60, 60, 80, 20, 2, 2, StyleAdapter.INSTANCE))
  }

  override fun render(mouseX: Int, mouseY: Int, partialTicks: Float) {
  }

  override fun doesGuiPauseGame(): Boolean {
    return false
  }

  override fun getStyle(): IStyleUI = IStyleUI.INSTANCE
}
