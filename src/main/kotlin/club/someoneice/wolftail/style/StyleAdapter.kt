package club.someoneice.wolftail.style

import club.someoneice.wolftail.api.IStyle
import club.someoneice.wolftail.api.IWidget
import club.someoneice.wolftail.util.Positioning
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.Gui
import net.minecraft.util.ResourceLocation
import java.awt.Color

abstract class StyleAdapter: IStyle {
  override fun getUIRange(): Positioning = IWidget.POS_ZERO
  override fun getTexture(): ResourceLocation = IWidget.DEF_RESOURCE

  override fun render(pGui: Gui, pPosX: Int, pPosY: Int, pWidth: Int, pHeight: Int,
                      args: Map<String, Any>) {
  }

  override fun drawString(pString: String, pGui: Gui, x: Int, y: Int,
                          args: Map<String, Any>
  ) {
    Minecraft.getMinecraft().fontRenderer.drawString(pString, x, y, Color.WHITE.rgb)
  }
}
