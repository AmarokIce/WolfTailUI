package club.someoneice.wolftail.api

import net.minecraft.client.gui.Gui

interface IWidgetFunction : IWidget {
  /**
   * Return true if it can click by mouse.
   */
  fun canBeClick(): Boolean

  /**
   * On mouse clicked.
   *
   * @param pGui The GuiScreen.
   * @param pMouseX The mouse's position x.
   * @param pMouseY The mouse's position y.
   * @param pGuiX The gui render position's 'zero' x.
   * @param pGuiY The gui render position's 'zero' y.
   */
  fun onClick(pGui: Gui, pMouseX: Int, pMouseY: Int, pGuiY: Int, pGuiX: Int)

  /**
   * On mouse passed.
   *
   * @param pGui The GuiScreen.
   * @param pMouseX The mouse's position x.
   * @param pMouseY The mouse's position y.
   * @param pGuiX The gui render position's 'zero' x.
   * @param pGuiY The gui render position's 'zero' y.
   */
  fun onPassed(pGui: Gui, pMouseX: Int, pMouseY: Int, pGuiY: Int, pGuiX: Int)
}
