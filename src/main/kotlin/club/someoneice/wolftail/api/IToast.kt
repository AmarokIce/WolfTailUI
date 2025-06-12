package club.someoneice.wolftail.api

import club.someoneice.wolftail.style.StyleBasedToast
import cpw.mods.fml.common.registry.GameRegistry
import net.minecraft.client.gui.Gui
import net.minecraft.item.Item
import net.minecraft.util.IChatComponent
import net.minecraft.util.ResourceLocation

interface IToast {
    companion object {
        fun create(title: IChatComponent, text: IChatComponent, style: StyleBasedToast, item: Item): IToast {
            return object: IToast {
                override fun getToastTitle(): IChatComponent = title
                override fun getToastText(): IChatComponent = text
                override fun getUIStyle(): StyleBasedToast = style
                override fun byItemStack(): Boolean = true
                override fun getToastIcon(): ResourceLocation {
                    val uid = GameRegistry.findUniqueIdentifierFor(item)
                    return ResourceLocation(uid.modId, uid.name)
                }
            }
        }

        fun create(title: IChatComponent, text: IChatComponent, style: StyleBasedToast,
                   rl: ResourceLocation, u: Int, v: Int, w: Int, h: Int): IToast {
            return object: IToast {
                override fun getToastTitle(): IChatComponent = title
                override fun getToastText(): IChatComponent = text
                override fun getUIStyle(): StyleBasedToast = style
                override fun getToastIcon(): ResourceLocation = rl
                override fun bindTexture(gui: Gui, x: Int, y: Int) {
                    gui.drawTexturedModalRect(x, y, u, v, w, h)
                }
            }
        }
    }

    fun getToastTitle(): IChatComponent
    fun getToastText(): IChatComponent

    /**
     * The icon resource path. If it is an ItemStack, return the ItemStack's ResourceID (modid:itemRegisterName).
     */
    fun getToastIcon(): ResourceLocation

    /**
     * The toast background.
     *
     * @see DefaultUIStyle
     * @see club.someoneice.wolftail.style.StyleBasedToast
     */
    fun getUIStyle(): StyleBasedToast

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
    fun bindTexture(gui: Gui, x: Int, y: Int) {}
}