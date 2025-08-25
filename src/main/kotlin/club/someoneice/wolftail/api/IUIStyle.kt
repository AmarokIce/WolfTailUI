package club.someoneice.wolftail.api

import net.minecraft.client.gui.Gui
import net.minecraft.util.ResourceLocation
import org.lwjgl.util.Rectangle

interface IUIStyle {
    /**
     * The based resource path for UI.
     */
    fun getTexture(): ResourceLocation

    fun getUIRange(): Rectangle

    /**
     * Render the background. Remember, you should set up GL11 by your self.
     *
     * @param pGui The gui will render now.
     * @param pPosX The pos of X will render.
     * @param pPosY The pos of Y will render.
     *
     * @see club.someoneice.wolftail.style.StyleToast.renderBackground BasedToastUIStyle#renderBackground
     */
    fun renderBackground(pGui: Gui, pPosX: Int, pPosY: Int)
}
