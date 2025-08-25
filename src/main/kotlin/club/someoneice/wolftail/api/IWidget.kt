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
     * @param pGui The GuiScreen.
     * @param pMouseX The mouse's position x.
     * @param pMouseY The mouse's position y.
     * @param pGuiX The gui render position's 'zero' x.
     * @param pGuiY The gui render position's 'zero' y.
     *
     * @see club.someoneice.wolftail.style.StyleToast.renderBackground BasedToastUIStyle#renderBackground
     */
    fun render(pGui: Gui, pMouseX: Int, pMouseY: Int, pGuiX: Int, pGuiY: Int)

    /**
     * @param pMouseX The mouse's position x.
     * @param pMouseY The mouse's position y.
     * @param pGuiX The gui render position's 'zero' x.
     * @param pGuiY The gui render position's 'zero' y.
     * @return Return true if the mouse is in widget range.
     */
    fun isInRange(pMouseX: Int, pMouseY: Int, pGuiX: Int, pGuiY: Int): Boolean {
        val flagInX = pMouseX > this.wightStartAt().first && pMouseX < this.wightEndAt().first
        val flagInY = pMouseY > this.wightStartAt().second && pMouseY < this.wightEndAt().second
        return flagInX && flagInY
    }
}
