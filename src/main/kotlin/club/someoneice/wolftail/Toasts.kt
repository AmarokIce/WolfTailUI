package club.someoneice.wolftail

import club.someoneice.wolftail.api.IToast
import club.someoneice.wolftail.style.StyleToast
import cpw.mods.fml.common.registry.GameRegistry
import net.minecraft.client.gui.Gui
import net.minecraft.item.Item
import net.minecraft.util.ResourceLocation

object Toasts {
  fun create(pTitle: String, pText: String, pStyle: StyleToast, pIconItem: Item, pMeta: Int): IToast {
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
      override fun bindTexture(pGui: Gui, pPosX: Int, pPosY: Int) {
        pGui.drawTexturedModalRect(pPosX, pPosY, pU, pV, pW, pH)
      }
    }
  }
}
