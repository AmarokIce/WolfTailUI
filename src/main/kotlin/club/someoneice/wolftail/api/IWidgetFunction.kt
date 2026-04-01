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
   * @param pMouseButton The mouse's input key.
   *
   * @return Returns true if this widget should record into input pool,
   *         then it's will accept the [IWidgetFunction.onMousePassed].
   */
  fun onMouseClicked(pGui: Gui, pMouseX: Int, pMouseY: Int, pMouseButton: Int): Boolean {
    return false
  }

  /**
   * On mouse passed.
   *
   * @param pGui The GuiScreen.
   * @param pMouseX The mouse's position x.
   * @param pMouseY The mouse's position y.
   */
  fun onMousePassed(pGui: Gui, pMouseX: Int, pMouseY: Int) {}

  fun onMouseMove(pGui: Gui, pMouseX: Int, pMouseY: Int, pMouseButton: Int): Boolean {
    return false
  }

  fun onKeyboardInput(pGui: Gui, keyChar: Char, keyCode: Int) {}
}
