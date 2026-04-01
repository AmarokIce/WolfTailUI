package club.someoneice.wolftail.constant

import club.someoneice.wolftail.WolfTailUI
import club.someoneice.wolftail.api.StyleType
import club.someoneice.wolftail.style.StyleToast
import net.minecraft.util.ResourceLocation

object DefStyleToast {
  private val defaultRL = ResourceLocation(WolfTailUI.ID, "default_ui.png")

  val TOAST_DARK = StyleToast(defaultRL , 96, 0, 160, 32)
  val TOAST_LIGHT = StyleToast(defaultRL, 96, 32, 160, 32)
  val TOAST_T_DARK = StyleToast(defaultRL, 96, 64, 160, 32)
  val TOAST_T_LIGHT = StyleToast(defaultRL, 96, 96, 160, 32)

  init {
    StyleType.TOAST.builtInRegistry[ResourceLocation(WolfTailUI.ID, "toast_dark")]= TOAST_DARK
    StyleType.TOAST.builtInRegistry[ResourceLocation(WolfTailUI.ID, "toast_light")] = TOAST_LIGHT
    StyleType.TOAST.builtInRegistry[ResourceLocation(WolfTailUI.ID, "toast_modern_dark")] = TOAST_T_DARK
    StyleType.TOAST.builtInRegistry[ResourceLocation(WolfTailUI.ID, "toast_modern_light")] = TOAST_T_LIGHT
  }
}
