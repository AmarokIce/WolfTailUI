package club.someoneice.wolftail.util

import club.someoneice.wolftail.WolfTailUI
import net.minecraft.util.ResourceLocation

object UtilResourceLocation {
  @JvmStatic
  fun getPathFrom(rl: ResourceLocation): String {
    return "assets/${rl.resourceDomain}/${rl.resourcePath}"
  }

  @JvmStatic
  fun createRl(path: String): ResourceLocation {
    return ResourceLocation(WolfTailUI.ID, path)
  }
}
