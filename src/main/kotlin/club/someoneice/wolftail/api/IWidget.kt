package club.someoneice.wolftail.api

import net.minecraft.client.gui.Gui

interface IWidget {
  /**
   * Every widget needed a start pos.
   */
  fun wightStartAt(): Pair<Int, Int>

  /**
   * Like button needed an end, but like label didn't need an end.
   */
  fun wightEndAt(): Pair<Int, Int> = wightStartAt()

  /**
   * Render the background. Remember, you should set up GL11.
   *
   * @see club.someoneice.wolftail.style.StyleToast.renderBackground BasedToastUIStyle#renderBackground
   */
  fun render(pGui: Gui, pMouseX: Int, pMouseY: Int, pGuiX: Int, pGuiY: Int)

  /**
   * If mouse was in range will return true.
   */
  fun isInRange(pMouseX: Int, pMouseY: Int, pGuiX: Int, pGuiY: Int): Boolean {
    val flagInX = pMouseX > this.wightStartAt().first && pMouseX < this.wightEndAt().first
    val flagInY = pMouseY > this.wightStartAt().second && pMouseY < this.wightEndAt().second
    return flagInX && flagInY
  }
}
