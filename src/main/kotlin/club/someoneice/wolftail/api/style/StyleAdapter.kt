package club.someoneice.wolftail.api.style

import club.someoneice.wolftail.api.IStyle
import club.someoneice.wolftail.api.IWidget
import club.someoneice.wolftail.util.UIPos
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.Gui
import net.minecraft.util.ResourceLocation
import java.awt.Color

abstract class StyleAdapter: IStyle {
  override fun getUIRange(): UIPos = IWidget.POS_ZERO
  override fun getTexture(): ResourceLocation = IWidget.DEF_RESOURCE

  override fun render(pGui: Gui, rect: UIPos, pGuiX: Int, pGuiY: Int, args: Map<String, Any>) {
  }

  override fun drawString(pString: String, pGui: Gui, x: Int, y: Int, pGuiX: Int, pGuiY: Int, args: Map<String, Any>) {
    Minecraft.getMinecraft().fontRenderer.drawString(pString, x, y, Color.WHITE.rgb)
  }

  fun asEmptyUIStyle(): IUIStyle {
    return object : IUIStyle {
      override fun getTexture(): ResourceLocation = IWidget.DEF_RESOURCE
      override fun render(
        pGui: Gui,
        rect: UIPos,
        pGuiX: Int,
        pGuiY: Int,
        args: Map<String, Any>
      ) {
        this@StyleAdapter.render(pGui, rect, pGuiX, pGuiY, args)
      }

      override fun drawSlot(
        pGui: Gui,
        x: Int,
        y: Int,
        args: Map<String, Any>
      ) {
      }
    }
  }

  companion object {
    val EMPTY_STYLE = object : StyleAdapter() {}
    val EMPTY_UI_STYLE: IUIStyle = EMPTY_STYLE.asEmptyUIStyle()
  }
}
