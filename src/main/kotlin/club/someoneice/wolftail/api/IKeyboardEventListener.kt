package club.someoneice.wolftail.api

import net.minecraft.client.gui.Gui

interface IKeyboardEventListener {
  fun onKeyboardInput(pGui: Gui, keyChar: Char, keyCode: Int, pGuiX: Int, pGuiY: Int)

  fun onKeyboardRelease(pGui: Gui, pGuiX: Int, pGuiY: Int) {
  }
}
