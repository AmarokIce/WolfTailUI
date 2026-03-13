package club.someoneice.wolftail.util

import net.minecraft.util.ResourceLocation

object ResourceLocationUtil {
  fun getPathFrom(rl: ResourceLocation): String {
    return "assets/${rl.resourceDomain}/${rl.resourcePath}"
  }
}
