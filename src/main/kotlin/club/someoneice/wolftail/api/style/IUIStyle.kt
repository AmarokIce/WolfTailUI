package club.someoneice.wolftail.api.style

import club.someoneice.wolftail.api.IStyle
import com.google.common.collect.ImmutableMap
import net.minecraft.client.gui.Gui

interface IUIStyle: IStyle {
  // FIXME pGuiX & pGuiY
  fun drawSlot(pGui: Gui, x: Int, y: Int, args: Map<String, Any> = ImmutableMap.of())
}
