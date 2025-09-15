package club.someoneice.wolftail.api

import net.minecraft.client.gui.Gui
import java.awt.Color

interface ISolidColorStyle: IUIStyle {
  fun getSidebarColor(): Color

  fun getBackgroundColor(): Color

  fun getTextColor(): Color

  override fun renderBackground(pGui: Gui, pPosX: Int, pPosY: Int) {
    // TODO
  }
}
