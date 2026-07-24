package club.someoneice.wolftail.api.style

import club.someoneice.wolftail.ui.core.ResourceManager
import club.someoneice.wolftail.util.UIPos
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.Gui
import net.minecraft.util.ResourceLocation
import org.lwjgl.opengl.GL11
import java.awt.image.BufferedImage

sealed class StyleImage: IStyleImage {
  class StyleImageResourceLocation(
    val pos: UIPos,
    val resourceLocation: ResourceLocation
  ) : StyleImage() {
    override fun render(pGui: Gui, rect: UIPos, pGuiX: Int, pGuiY: Int, args: Map<String, Any>) {
      GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f)
      GL11.glEnable(GL11.GL_TEXTURE_2D)
      Minecraft.getMinecraft().textureManager.bindTexture(resourceLocation)
      GL11.glDisable(GL11.GL_LIGHTING)
      pGui.drawTexturedModalRect(
        rect.x + pGuiX, rect.y + pGuiY,
        pos.x, pos.y, pos.w, pos.h)

    }
  }

  class StyleImageBufferedImage(
    val img: BufferedImage
  ): StyleImage() {
    override fun render(pGui: Gui, rect: UIPos, pGuiX: Int, pGuiY: Int, args: Map<String, Any>) {
      GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f)
      ResourceManager.drawImageWithoutResource(rect.x + pGuiX, rect.y + pGuiY, img)
    }
  }
}
