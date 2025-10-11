package club.someoneice.wolftail.api

import club.someoneice.wolftail.style.StyleFont
import com.google.common.collect.ImmutableMap
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
  fun getUIRange(): Rectangle = IWidget.POS_ZERO

  /**
   * Render the background. Remember, you should set up GL11 by your self.
   *
   * @see club.someoneice.wolftail.style.StyleToast.render BasedToastUIStyle#renderBackground
   */
  fun render(pGui: Gui, pPosX: Int, pPosY: Int,
             pWidth: Int = 0, pHeight: Int = 0,
             args: Map<String, Any> = ImmutableMap.of())

  /**
   * Draw text into screen gui.
   */
  fun drawString(pString: String, pGui: Gui, x: Int, y: Int,
                 args: Map<String, Any> = ImmutableMap.of()) {
    StyleFont.INSTANCE.drawString(pString, pGui, x - 1, y + 1, args)
  }
}
