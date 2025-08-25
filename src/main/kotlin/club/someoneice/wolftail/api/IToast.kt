package club.someoneice.wolftail.api

import club.someoneice.wolftail.style.StyleToast
import cpw.mods.fml.common.registry.GameRegistry
import net.minecraft.client.gui.Gui
import net.minecraft.item.Item
import net.minecraft.util.ResourceLocation

interface IToast {
    companion object {
        fun create(pTitle: String, pText: String, pStyle: StyleToast, pIconItem: Item): IToast {
            return object : IToast {
                override fun getToastTitle(): String = pTitle
                override fun getToastText(): String = pText
                override fun getUIStyle(): StyleToast = pStyle
                override fun byItemStack(): Boolean = true
                override fun getToastIcon(): ResourceLocation {
                    val uid = GameRegistry.findUniqueIdentifierFor(pIconItem)
                    return ResourceLocation(uid.modId, uid.name)
                }
            }
        }

        fun create(
            pTitle: String, pText: String, pStyle: StyleToast,
            pIconRL: ResourceLocation, pU: Int, pV: Int, pW: Int, pH: Int
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

    fun getToastTitle(): String
    fun getToastText(): String

    /**
     * The icon resource path. If it is an ItemStack, return the ItemStack's ResourceID (modid:itemRegisterName).
     */
    fun getToastIcon(): ResourceLocation

    /**
     * The toast background.
     *
     * @see DefaultUIStyle
     * @see club.someoneice.wolftail.style.StyleToast
     */
    fun getUIStyle(): StyleToast

    /**
     * Mark the toast's icon is an ItemStack.
     * @return Return true if the toast icon is an ItemStack.
     *
     * @see IToast.getToastIcon
     */
    fun byItemStack(): Boolean = false

    /**
     * Bind an icon texture if the icon isn't an ItemStack(when IToast#byItemStack return false yet)
     *
     * @see IToast.byItemStack
     * @see IToast.getToastIcon
     */
    fun bindTexture(pGui: Gui, pPosX: Int, pPosY: Int) {}
}
