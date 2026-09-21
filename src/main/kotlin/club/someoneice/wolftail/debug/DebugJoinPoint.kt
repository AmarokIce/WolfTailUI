package club.someoneice.wolftail.debug

import club.someoneice.wolftail.api.constant.DefaultStyleToast
import club.someoneice.wolftail.ui.core.Toasts
import cpw.mods.fml.common.eventhandler.SubscribeEvent
import cpw.mods.fml.common.gameevent.InputEvent
import net.minecraft.client.Minecraft
import net.minecraft.init.Items
import org.lwjgl.input.Keyboard

object DebugJoinPoint {
  @SubscribeEvent
  fun onKeyInput(event: InputEvent.KeyInputEvent) {
    if (Keyboard.isKeyDown(Keyboard.KEY_F)) {
      Minecraft.getMinecraft().displayGuiScreen(DebugGUI())
      Toasts.addToast(Toasts.create("测试消息", "测试消息", DefaultStyleToast.TOAST_DARK, Items.pumpkin_pie))
    }
  }
}
