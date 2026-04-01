package club.someoneice.wolftail.api

import com.google.common.collect.ImmutableMap
import net.minecraft.client.gui.Gui

interface IUIStyle: IStyle {
  fun drawSlot(pGui: Gui, x: Int, y: Int, args: Map<String, Any> = ImmutableMap.of())

  fun drawBackground(pGui: Gui, x: Int, y: Int, w: Int, h: Int,
                     args: Map<String, Any> = ImmutableMap.of())

  override fun render(pGui: Gui, pPosX: Int, pPosY: Int, pWidth: Int, pHeight: Int, args: Map<String, Any>) {
    return drawBackground(pGui, pPosX, pPosY, pWidth, pHeight)
  }
}
