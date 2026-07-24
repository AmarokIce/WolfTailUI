package club.someoneice.wolftail.api

import net.minecraft.client.gui.Gui

interface IMouseEventListener: IWidget {
  fun canBeClick(): Boolean = true

  fun onMouseClicked(pGui: Gui, pMouseX: Int, pMouseY: Int, pGuiX: Int, pGuiY: Int, pMouseButton: Int)

  fun onMouseReleased(pGui: Gui, pMouseX: Int, pMouseY: Int, pGuiX: Int, pGuiY: Int) {
  }

  fun onMousePressed(pGui: Gui, pMouseX: Int, pMouseY: Int, pGuiX: Int, pGuiY: Int) {
  }
}
