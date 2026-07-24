package club.someoneice.wolftail.api

import club.someoneice.wolftail.WolfTailUI
import club.someoneice.wolftail.util.UIPos
import net.minecraft.client.gui.Gui
import net.minecraft.util.ResourceLocation

interface IWidget {
  fun weightPos(): UIPos

  /**
   * Render the background. Remember, you should set up GL11.
   *
   * @sample club.someoneice.wolftail.style.StyleToast.render
   */
  fun render(pGui: Gui, pMouseX: Int, pMouseY: Int, pGuiX: Int, pGuiY: Int)

  /**
   * The based resource path for widget.
   */
  fun getStyle(): IStyle

  /**
   * If mouse was in range will return true.
   *
   * Careful the mouseX and mouseY was raw in windows.
   */
  fun isInRange(pMouseX: Int, pMouseY: Int, pGuiX: Int, pGuiY: Int): Boolean {
    val pos = this.weightPos()

    val flagInX = pMouseX > pGuiX + pos.x && pMouseX < pGuiX + pos.x + pos.w
    val flagInY = pMouseY > pGuiY + pos.y && pMouseY < pGuiY + pos.y + pos.h

    return flagInX && flagInY
  }

  companion object {
    val POS_ZERO = UIPos(0, 0, 0, 0)
    val DEF_RESOURCE = ResourceLocation(WolfTailUI.ID, "default")
  }
}
