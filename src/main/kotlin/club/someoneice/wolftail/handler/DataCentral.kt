package club.someoneice.wolftail.handler

import club.someoneice.wolftail.api.IStyle
import com.google.common.collect.BiMap
import com.google.common.collect.HashBiMap
import net.minecraft.util.ResourceLocation

object DataCentral {
  val STYLE_DATA: BiMap<ResourceLocation, IStyle> = HashBiMap.create()
}
