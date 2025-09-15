package club.someoneice.wolftail

import club.someoneice.wolftail.api.IToast
import cpw.mods.fml.common.FMLCommonHandler
import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.event.FMLInitializationEvent
import cpw.mods.fml.common.eventhandler.SubscribeEvent
import cpw.mods.fml.common.gameevent.TickEvent
import net.minecraftforge.common.MinecraftForge
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

@Suppress("unused")
@Mod(modid = WolfTailUI.ID)
class WolfTailUI {
  companion object {
    const val ID = "wolftail"
    val LOG: Logger = LogManager.getLogger(ID)

    internal val TOAST_SET = ArrayList<GuiToast>()

    fun addToast(toast: IToast) {
      this.TOAST_SET.add(GuiToast(toast))
    }
  }

  @Mod.EventHandler
  fun init(event: FMLInitializationEvent) {
    LOG.info("Welcome to use WolfTail UI for Minecraft 1.7.10!")
    LOG.info("Github: https://github.com/AmarokIce/WolfTailUI/")
    LOG.info("Issues: https://github.com/AmarokIce/WolfTailUI/issues")

    MinecraftForge.EVENT_BUS.register(this)
    FMLCommonHandler.instance().bus().register(this)
  }

//    The Debug Method.
//    @SubscribeEvent
//    fun debugTestUIEvent(event: InputEvent.KeyInputEvent) {
//        if (Keyboard.getEventKey() == Keyboard.KEY_R) {
//            Minecraft.getMinecraft().displayGuiScreen(object: WGuiBased(title = "wolftail_testUI", size = Pair(200, 180), lightStyle = true) {
//                override fun start() {
//                    this.addWidget(WString("The string of 'Hello' from WolfTailUI!", Pair(5, 5)))
//                    this.addWidget(WButton("And a button!", Pair(5, 20), Pair(95, 40), false) {})
//
//                    this.addWidget(WString("The string of 'Hello' from WolfTailUI!", Pair(13, 180 - 15), color = Color(64, 76, 94), shadow = Color.WHITE))
//                    this.addWidget(WButton("And a button!", Pair(200 - 95, 180 - 40), Pair(200 - 5, 180 - 20), true) {})
//                }
//            })
//        }
//    }

  @SubscribeEvent
  fun onClientTick(event: TickEvent.RenderTickEvent) {
    if (TOAST_SET.isEmpty() || event.phase == TickEvent.Phase.START) {
      return
    }

    GuiToast.setUp()

    TOAST_SET.forEach(GuiToast::tick)
    TOAST_SET.removeAll(GuiToast::isDead)
  }
}
