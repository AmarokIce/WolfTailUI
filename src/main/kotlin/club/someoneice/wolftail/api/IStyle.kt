package club.someoneice.wolftail.api

import club.someoneice.wolftail.api.style.StyleFont
import club.someoneice.wolftail.util.UIPos
import com.google.common.collect.ImmutableMap
import net.minecraft.client.gui.Gui
import net.minecraft.util.ResourceLocation
import javax.annotation.CheckForNull

/**
 * @see club.someoneice.wolftail.api.style.StyleAdapter
 */
interface IStyle {
  /**
   * The based resource path for UI.
   * Returns null if the style draw by coding.
   */
  @CheckForNull
  fun getTexture(): ResourceLocation?

  /**
   * The pos of data will render. Start and end.
   */
  fun getUIRange(): UIPos = IWidget.POS_ZERO

  /**
   * Render the background. Remember, you should set up GL11 by your self.
   *
   * @sample club.someoneice.wolftail.api.style.StyleToast.render
   */
  fun render(pGui: Gui, rect: UIPos, pGuiX: Int, pGuiY: Int, args: Map<String, Any> = ImmutableMap.of())

  /**
   * Draw text into screen gui.
   */
  fun drawString(pString: String, pGui: Gui, x: Int, y: Int, pGuiX: Int, pGuiY: Int,
                 args: Map<String, Any> = ImmutableMap.of()) {
    StyleFont.INSTANCE.drawString(pString, pGui, x, y, pGuiX, pGuiY, args)
  }
}
