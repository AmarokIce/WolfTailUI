package club.someoneice.wolftail.dev

import club.someoneice.wolftail.WolfTailUI
import club.someoneice.wolftail.api.IToast
import club.someoneice.wolftail.style.StyleToast
import club.someoneice.wolftail.ui.GuiWToast
import cpw.mods.fml.common.registry.GameRegistry
import net.minecraft.client.gui.Gui
import net.minecraft.item.Item
import net.minecraft.util.ResourceLocation

object Toasts {
  internal val TOAST_SET = ArrayList<GuiWToast>()

  fun addToast(toast: IToast) {
    this.TOAST_SET.add(GuiWToast(toast))
    WolfTailUI.LOG.debug("Success send a toast.")
  }

  fun create(pTitle: String, pText: String, pStyle: StyleToast, pIconItem: Item, pMeta: Int = 0):
      IToast {
    return object : IToast {
      override fun getToastTitle(): String = pTitle
      override fun getToastText(): String = pText
      override fun getUIStyle(): StyleToast = pStyle
      override fun byItemStack(): Boolean = true
      override fun itemStackMeta(): Int = pMeta
      override fun getToastIcon(): ResourceLocation {
        val uid = GameRegistry.findUniqueIdentifierFor(pIconItem)
        return ResourceLocation(uid.modId, uid.name)
      }
    }
  }

  fun create(
    pTitle: String, pText: String, pStyle: StyleToast, pIconRL: ResourceLocation,
    pU: Int, pV: Int, pW: Int, pH: Int
  ): IToast {
    return object : IToast {
      override fun getToastTitle(): String = pTitle
      override fun getToastText(): String = pText
      override fun getUIStyle(): StyleToast = pStyle
      override fun getToastIcon(): ResourceLocation = pIconRL
      override fun drawToastIcon(pGui: Gui, pPosX: Int, pPosY: Int) {
        pGui.drawTexturedModalRect(pPosX, pPosY, pU, pV, pW, pH)
      }
    }
  }
}
