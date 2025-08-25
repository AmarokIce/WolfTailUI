package club.someoneice.wolftail.api

import net.minecraft.client.gui.Gui
import net.minecraft.util.ResourceLocation

interface IWidget {
    /**
     * Return true if it can click by mouse.
     */
    fun canBeClick(): Boolean = false

    /**
     * The based resource path for UI.
     */
    fun getTexture(): ResourceLocation

    /**
     * Every widget needed a start pos.
     */
    fun wightStartAt(): Pair<Int, Int>

    /**
     * Like button needed an end, but like label didn't need an end.
     */
    fun wightEndAt(): Pair<Int, Int> = wightStartAt()

    fun isInRange(mouseX: Int, mouseY: Int, x: Int, y: Int): Boolean {
        val mouseXIn = mouseX - x
        val mouseYIn = mouseY - y
        val flagInX = mouseXIn > this.wightStartAt().first && mouseXIn < this.wightEndAt().first
        val flagInY = mouseYIn > this.wightStartAt().second && mouseYIn < this.wightEndAt().second
        return flagInX && flagInY
    }

    /**
     * On mouse click in.
     */
    fun onClick(gui: Gui, mouseX: Int, mouseY: Int, x: Int, y: Int) {
    }

    /**
     * Render the background. Remember, you should set up GL11.
     * @param gui The GuiScreen.
     * @param mouseX The mouse's position x.
     * @param mouseY The mouse's position y.
     * @param x The gui render position's 'zero' x.
     * @param y The gui render position's 'zero' y.
     *
     * @see club.someoneice.wolftail.style.StyleToast.renderBackground BasedToastUIStyle#renderBackground
     */
    fun render(gui: Gui, mouseX: Int, mouseY: Int, x: Int, y: Int)

    /**
     * If the wight had something should do. Like button or slice.
     */
    fun run() {
    }
}
