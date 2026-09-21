package club.someoneice.wolftail.debug

import club.someoneice.wolftail.api.style.IDataTreeStyle
import club.someoneice.wolftail.api.style.IStyleUI
import club.someoneice.wolftail.ui.core.WScreen
import club.someoneice.wolftail.ui.widget.WDataTree
import club.someoneice.wolftail.ui.widget.WString
import club.someoneice.wolftail.util.UIPos

class DebugGUI: WScreen(196, 168) {
  override fun initWidgets() {
//    this.addWidget(WString("Help me", 20, 5))
//    this.addWidget(WScrollList(20, 10, 160, 40, StyleAdapter.INSTANCE)
//        .addChild(WString("Pineapple", 5, 5, hasShadow = false, hasHighlight = true))
//        .addChild(WString("Coffee", 5, 5, hasShadow = false, hasHighlight = true))
//        .addChild(WString("Mutton", 5, 5, hasShadow = false, hasHighlight = true))
//    )
    this.addWidget(
      WDataTree(UIPos(10, 10, 180, 160), IDataTreeStyle.INSTANCE, 600 to 600)
        .addChild(WString("Pineapple", 5, 5, hasHighlight = true))
        .addChild(WString("Pineapple", 5, 40, hasHighlight = true), 0)
        .addChild(WString("Pineapple", 40, 80, hasHighlight = true), 1)
        .addChild(WString("Pineapple", 90, 120, hasHighlight = true))
    )
  }

  override fun render(mouseX: Int, mouseY: Int, partialTicks: Float) {
  }

  override fun doesGuiPauseGame(): Boolean {
    return false
  }

  override fun getStyle(): IStyleUI = IStyleUI.INSTANCE
}
