package club.someoneice.wolftail.api

import club.someoneice.wolftail.WolfTailUI
import net.minecraft.client.gui.Gui
import net.minecraft.util.ResourceLocation
import org.lwjgl.util.Rectangle

interface IWidget {
  fun weightPos(): Rectangle

  /**
   * Render the background. Remember, you should set up GL11.
   *
   * @see club.someoneice.wolftail.style.StyleToast.render BasedToastUIStyle#renderBackground
   */
  fun render(pGui: Gui, pMouseX: Int, pMouseY: Int, pGuiX: Int, pGuiY: Int)

  /**
   * The based resource path for UI.
   */
  fun getStyle(): IStyle

  /**
   * If mouse was in range will return true.
   */
  fun isInRange(pMouseX: Int, pMouseY: Int, pGuiX: Int, pGuiY: Int): Boolean {
    val pos = this.weightPos()

    val flagInX = pMouseX > pGuiX + pos.x && pMouseX < pGuiX + pos.x + pos.width
    val flagInY = pMouseY > pGuiY + pos.y && pMouseY < pGuiY + pos.y + pos.height

    return flagInX && flagInY
  }

  companion object {
    val POS_ZERO = Rectangle(0, 0, 0, 0)
    val DEF_RESOURCE = ResourceLocation(WolfTailUI.ID, "default")
  }
}
