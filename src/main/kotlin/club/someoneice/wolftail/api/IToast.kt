package club.someoneice.wolftail.api

import club.someoneice.wolftail.style.StyleToast
import net.minecraft.client.gui.Gui
import net.minecraft.util.ResourceLocation

interface IToast {
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
   * Return true if this toast's icon is an ItemStack.
   *
   * @see IToast.getToastIcon
   */
  fun byItemStack(): Boolean = false

  /**
   * Get the meta for ItemStack. If `byItemStack` returns false, this method will non to call anymore.
   */
  fun itemStackMeta(): Int = 0

  /**
   * Bind an icon texture if the icon isn't an ItemStack(when IToast#byItemStack return false yet)
   *
   * @see IToast.byItemStack
   * @see IToast.getToastIcon
   */
  fun bindTexture(pGui: Gui, pPosX: Int, pPosY: Int) {}
}
