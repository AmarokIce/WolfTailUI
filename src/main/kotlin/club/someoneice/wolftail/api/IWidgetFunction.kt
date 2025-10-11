package club.someoneice.wolftail.api

import net.minecraft.client.gui.Gui

interface IWidgetFunction : IWidget {
  /**
   * Return true if it can click by mouse.
   */
  fun canBeClick(): Boolean = true

  /**
   * On mouse clicked.
   *
   * @param pGui The GuiScreen.
   * @param pMouseX The mouse's position x.
   * @param pMouseY The mouse's position y.
   */
  fun onClick(pGui: Gui, pMouseX: Int, pMouseY: Int) {}

  /**
   * On mouse passed.
   *
   * @param pGui The GuiScreen.
   * @param pMouseX The mouse's position x.
   * @param pMouseY The mouse's position y.
   */
  fun onPassed(pGui: Gui, pMouseX: Int, pMouseY: Int) {}

  fun onKeyboardInput(pGui: Gui, keyChar: Char, keyCode: Int) {}
}
