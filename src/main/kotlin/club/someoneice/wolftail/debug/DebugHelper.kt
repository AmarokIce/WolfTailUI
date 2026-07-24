package club.someoneice.wolftail.debug

import club.someoneice.wolftail.api.constant.DefaultStyleToast
import club.someoneice.wolftail.ui.core.Toasts
import net.minecraft.client.Minecraft
import net.minecraft.init.Items

object DebugHelper {
  fun debugJoinpoint() {
    Minecraft.getMinecraft().displayGuiScreen(DebugGUI())
    Toasts.addToast(Toasts.create("测试消息", "测试消息", DefaultStyleToast.TOAST_DARK, Items.pumpkin_pie))
  }
}
