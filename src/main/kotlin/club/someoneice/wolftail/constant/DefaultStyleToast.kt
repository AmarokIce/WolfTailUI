package club.someoneice.wolftail.constant
import club.someoneice.wolftail.api.StyleType
import club.someoneice.wolftail.style.StyleToast
import club.someoneice.wolftail.util.UtilResourceLocation

object DefaultStyleToast {
  private val defaultRL = UtilResourceLocation.createRl("default_ui.png")

  val TOAST_DARK = StyleToast(defaultRL , 96, 0, 160, 32)
  val TOAST_LIGHT = StyleToast(defaultRL, 96, 32, 160, 32)
  val TOAST_T_DARK = StyleToast(defaultRL, 96, 64, 160, 32)
  val TOAST_T_LIGHT = StyleToast(defaultRL, 96, 96, 160, 32)

  init {
    StyleType.TOAST.builtInRegistry[UtilResourceLocation.createRl("toast_dark")]= TOAST_DARK
    StyleType.TOAST.builtInRegistry[UtilResourceLocation.createRl("toast_light")] = TOAST_LIGHT
    StyleType.TOAST.builtInRegistry[UtilResourceLocation.createRl("toast_modern_dark")] = TOAST_T_DARK
    StyleType.TOAST.builtInRegistry[UtilResourceLocation.createRl("toast_modern_light")] = TOAST_T_LIGHT
  }
}
