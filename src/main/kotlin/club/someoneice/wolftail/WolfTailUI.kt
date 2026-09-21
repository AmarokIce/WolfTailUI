package club.someoneice.wolftail

import club.someoneice.wolftail.debug.DebugJoinPoint
import club.someoneice.wolftail.ui.GuiWToast
import club.someoneice.wolftail.ui.core.Toasts.TOAST_SET
import cpw.mods.fml.common.FMLCommonHandler
import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.event.FMLInitializationEvent
import cpw.mods.fml.common.event.FMLPreInitializationEvent
import cpw.mods.fml.common.eventhandler.SubscribeEvent
import cpw.mods.fml.common.gameevent.TickEvent
import net.minecraft.launchwrapper.Launch
import net.minecraftforge.common.MinecraftForge
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger


@Suppress("unused")
@Mod(modid = WolfTailUI.ID, modLanguage = "kotlin")
class WolfTailUI {
  companion object {
    const val ID = "wolftail"
    const val NAME = "WolfTail UI"
    val LOG: Logger = LogManager.getLogger(ID)

    val isDevEnvironment: Boolean
      get() = (Launch.blackboard["fml.deobfuscatedEnvironment"] as? Boolean) == true
  }

  @Mod.EventHandler
  fun perInit(event: FMLPreInitializationEvent) {
    this.printLog()

    MinecraftForge.EVENT_BUS.register(this)
    FMLCommonHandler.instance().bus().register(this)

    if (isDevEnvironment) {
      LOG.info("[Info] WolfTail UI is ready for debugging.")
      LOG.debug("[Debug] WolfTail UI is ready for debugging.")
      MinecraftForge.EVENT_BUS.register(DebugJoinPoint)
      FMLCommonHandler.instance().bus().register(DebugJoinPoint)
    }
  }

  @Mod.EventHandler
  fun init(event: FMLInitializationEvent) {
  }

  @SubscribeEvent
  fun onClientTick(event: TickEvent.RenderTickEvent) {
    if (TOAST_SET.isEmpty() || event.phase == TickEvent.Phase.START) {
      return
    }

    GuiWToast.setUp()

    TOAST_SET.forEach(GuiWToast::tick)
    TOAST_SET.removeAll(GuiWToast::isDead)
  }

  private fun printLog() {
    LOG.info("")
    LOG.info(",--.   ,--.       ,--. ,---.,--------.       ,--.,--.    ,--. ,--.,--. ")
    LOG.info("|  |   |  | ,---. |  |/  .-''--.  .--',--,--.`--'|  |    |  | |  ||  | ")
    LOG.info("|  |.'.|  || .-. ||  ||  `-,   |  |  ' ,-.  |,--.|  |    |  | |  ||  | ")
    LOG.info("|   ,'.   |' '-' '|  ||  .-'   |  |  | '-'  ||  ||  |    '  '-'  '|  | ")
    LOG.info("'--'   '--' `---' `--'`--'     `--'   `--`--'`--'`--'     `-----' `--' ")
    LOG.info("||                                                                  ||")
    LOG.info("||         Github: https://github.com/AmarokIce/WolfTailUI/         ||")
    LOG.info("||      Issues: https://github.com/AmarokIce/WolfTailUI/issues      ||")
    LOG.info("||             Thanks for use WolfTail UI for 1.7.10                ||")
    LOG.info("||                                                                  ||")
    LOG.info(" \\\\================================================================//")
    LOG.info("")
  }
}
