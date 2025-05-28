package club.someoneice.wolftail.api

import net.minecraft.client.gui.Gui
import net.minecraft.util.ResourceLocation

interface IUIStyle {
    /**
     * The based resource path for UI.
     */
    fun getTexture(): ResourceLocation

    /**
     * Render the background. Remember, you should setup GL11.
     *
     * @see club.someoneice.wolftail.ui.BasedToastUIStyle.renderBackground BasedToastUIStyle#renderBackground
     */
    fun renderBackground(gui: Gui, x: Int, y: Int, w: Int, h: Int)
}