package club.someoneice.wolftail.api

import net.minecraft.client.gui.Gui

interface IWidget {

  fun wightStartAt(): Pair<Int, Int>

  fun wightEndAt(): Pair<Int, Int> = wightStartAt()

  /**
   * Render the background. Remember, you should set up GL11.
   *
   * @see club.someoneice.wolftail.style.StyleToast.render BasedToastUIStyle#renderBackground
   */
  fun render(pGui: Gui, pMouseX: Int, pMouseY: Int, pGuiX: Int, pGuiY: Int)

  /**
   * If mouse was in range will return true.
   */
  fun isInRange(pMouseX: Int, pMouseY: Int, pGuiX: Int, pGuiY: Int): Boolean {
    val flagInX = pMouseX > pGuiX + this.wightStartAt().first && pMouseX < pGuiX + this.wightEndAt().first
    val flagInY = pMouseY > pGuiY + this.wightStartAt().second && pMouseY < pGuiY + this.wightEndAt().second
    return flagInX && flagInY
  }

  /**
   * The based resource path for UI.
   */
  fun getStyle(): IStyle
}
