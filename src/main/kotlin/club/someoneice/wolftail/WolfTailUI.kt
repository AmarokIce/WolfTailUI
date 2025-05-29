package club.someoneice.wolftail

import club.someoneice.wolftail.api.DefaultUIStyle
import club.someoneice.wolftail.api.IToast
import club.someoneice.wolftail.render.ToastGUI
import cpw.mods.fml.common.FMLCommonHandler
import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.event.FMLInitializationEvent
import cpw.mods.fml.common.eventhandler.SubscribeEvent
import cpw.mods.fml.common.gameevent.InputEvent
import cpw.mods.fml.common.gameevent.TickEvent
import net.minecraft.init.Items
import net.minecraft.util.ChatComponentText
import net.minecraftforge.common.MinecraftForge
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

@Suppress("unused")
@Mod(modid = WolfTailUI.ID)
class WolfTailUI {
    companion object {
        const val ID = "wolftail"
        val LOG: Logger = LogManager.getLogger(ID)

        private val TOAST_SET = HashSet<ToastGUI>()

        fun addToast(toast: IToast) {
            this.TOAST_SET.add(ToastGUI(toast))
        }

        private fun toastTick(index: Int, it: ToastGUI) {
            it.tick(index)
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

    @SubscribeEvent
    fun testingChat(event: InputEvent.KeyInputEvent) {
        addToast(IToast.create(ChatComponentText("Test Tile"), ChatComponentText("Test text"), DefaultUIStyle.TOAST_DARK_UI, Items.pumpkin_pie))
    }

    @SubscribeEvent
    fun onClientTick(event: TickEvent.ClientTickEvent) {
        if (TOAST_SET.isEmpty() || event.phase != TickEvent.Phase.START) {
            return
        }

        TOAST_SET.forEachIndexed(::toastTick)
        TOAST_SET.removeAll(ToastGUI::isDead)
    }
}