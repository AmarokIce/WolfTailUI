package club.someoneice.wolftail.api

import club.someoneice.json.node.MapNode
import club.someoneice.wolftail.json.StyleBuilder
import com.google.common.collect.BiMap
import com.google.common.collect.HashBiMap
import com.google.common.collect.Maps
import net.minecraft.util.ResourceLocation
import java.io.File

enum class StyleType(
  val builder: (MapNode, File) -> IStyle,
  val builtInRegistry: BiMap<ResourceLocation, IStyle> = Maps.synchronizedBiMap(HashBiMap.create())
) {
  TOAST(StyleBuilder::toastStyleBuilder),
  GUI(StyleBuilder::guiStyleBuilder),
  BUTTON(StyleBuilder::buttonStyleBuilder),
  LABEL(StyleBuilder::labelStyleBuilder),
  SWITCH(StyleBuilder::switchStyleBuilder),
  STRING(StyleBuilder::stringStyleBuilder),
  IMAGE(StyleBuilder::imageStyleBuilder);
}
