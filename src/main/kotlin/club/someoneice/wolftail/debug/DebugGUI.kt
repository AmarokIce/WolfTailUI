package club.someoneice.wolftail.debug

import club.someoneice.wolftail.api.style.IStyleUI
import club.someoneice.wolftail.api.style.StyleAdapter
import club.someoneice.wolftail.ui.core.WScreen
import club.someoneice.wolftail.ui.widget.WButton
import club.someoneice.wolftail.ui.widget.WEditField
import club.someoneice.wolftail.ui.widget.WScrollList
import club.someoneice.wolftail.ui.widget.WString
import club.someoneice.wolftail.ui.widget.WVarString

class DebugGUI: WScreen(196, 168) {
  var text: String = ""
  var index = 0

  override fun initWidgets() {
    this.addWidget(WButton("", 50, 30, 50, 10, StyleBackground) {
      index++
      text = index.toString()
    })
    this.addWidget(WVarString(::text, 50, 50))
  }

  override fun render(mouseX: Int, mouseY: Int, partialTicks: Float) {
  }

  override fun doesGuiPauseGame(): Boolean {
    return false
  }

  override fun getStyle(): IStyleUI = IStyleUI.INSTANCE
}
