package club.someoneice.wolftail.api

import club.someoneice.wolftail.api.IWidget.Companion.DEF_RESOURCE
import club.someoneice.wolftail.api.IWidget.Companion.POS_ZERO
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.Gui
import net.minecraft.util.ResourceLocation
import org.lwjgl.util.Rectangle
import java.awt.Color

abstract class StyleAdapter: IStyle {
  override fun getUIRange(): Rectangle = POS_ZERO
  override fun getTexture(): ResourceLocation = DEF_RESOURCE

  override fun render(pGui: Gui, pPosX: Int, pPosY: Int, pWidth: Int, pHeight: Int,
                      args: Map<String, Any>) {
  }

  override fun drawString(pString: String, pGui: Gui, x: Int, y: Int,
                          args: Map<String, Any>
  ) {
    Minecraft.getMinecraft().fontRenderer.drawString(pString, x, y, Color.WHITE.rgb)
  }
}
