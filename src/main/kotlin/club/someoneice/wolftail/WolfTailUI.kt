package club.someoneice.wolftail

import club.someoneice.wolftail.Toasts.TOAST_SET
import cpw.mods.fml.common.FMLCommonHandler
import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.event.FMLInitializationEvent
import cpw.mods.fml.common.event.FMLPreInitializationEvent
import cpw.mods.fml.common.eventhandler.SubscribeEvent
import cpw.mods.fml.common.gameevent.TickEvent
import net.minecraftforge.common.MinecraftForge
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.lwjgl.opengl.GL11

@Suppress("unused")
@Mod(modid = WolfTailUI.ID)
class WolfTailUI {
  companion object {
    const val ID = "wolftail"
    const val NAME = "WolfTail UI"
    val LOG: Logger = LogManager.getLogger(ID)
  }

  fun perInit(event: FMLPreInitializationEvent) {
    printLog()

    MinecraftForge.EVENT_BUS.register(this)
    FMLCommonHandler.instance().bus().register(this)
  }

  @Mod.EventHandler
  fun init(event: FMLInitializationEvent) {

  }

  @SubscribeEvent
  fun onClientTick(event: TickEvent.RenderTickEvent) {
    if (TOAST_SET.isEmpty() || event.phase == TickEvent.Phase.START) {
      return
    }

    GuiToast.setUp()

    TOAST_SET.forEach(GuiToast::tick)
    TOAST_SET.removeAll(GuiToast::isDead)
  }

  private fun printLog() {
    LOG.info("")
    LOG.info(",--.   ,--.       ,--. ,---.,--------.       ,--.,--.    ,--. ,--.,--. ")
    LOG.info("|  |   |  | ,---. |  |/  .-''--.  .--',--,--.`--'|  |    |  | |  ||  | ")
    LOG.info("|  |.'.|  || .-. ||  ||  `-,   |  |  ' ,-.  |,--.|  |    |  | |  ||  | ")
    LOG.info("|   ,'.   |' '-' '|  ||  .-'   |  |  \\ '-'  ||  ||  |    '  '-'  '|  | ")
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

fun clearColor() {
  GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f)
}
