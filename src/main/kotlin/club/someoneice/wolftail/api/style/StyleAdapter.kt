package club.someoneice.wolftail.api.style

import club.someoneice.wolftail.api.IStyle
import club.someoneice.wolftail.api.IWidget
import club.someoneice.wolftail.util.UIPos
import net.minecraft.client.gui.Gui
import net.minecraft.util.ResourceLocation

abstract class StyleAdapter: IStyle {
  companion object {
    val INSTANCE: IStyle = object: StyleAdapter() {}
  }

  override fun getUIRange(): UIPos = IWidget.POS_ZERO
  override fun getTexture(): ResourceLocation = IWidget.DEF_RESOURCE

  override fun render(pGui: Gui, rect: UIPos, pGuiX: Int, pGuiY: Int, args: Map<String, Any>) {
  }
}
