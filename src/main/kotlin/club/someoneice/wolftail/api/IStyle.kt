package club.someoneice.wolftail.api

import net.minecraft.client.gui.Gui
import net.minecraft.util.ResourceLocation
import org.lwjgl.util.Rectangle

interface IStyle {
  /**
   * The based resource path for UI.
   */
  fun getTexture(): ResourceLocation

  /**
   * The pos of data will render. Start and end.
   */
  fun getUIRange(): Rectangle

  /**
   * Render the background. Remember, you should set up GL11 by your self.
   *
   * @see club.someoneice.wolftail.style.StyleToast.render BasedToastUIStyle#renderBackground
   */
  fun render(pGui: Gui, pPosX: Int, pPosY: Int)

  /**
   * Draw text into screen gui.
   */
  fun drawString(pString: String, pGui: Gui, x: Int, y: Int)
}
