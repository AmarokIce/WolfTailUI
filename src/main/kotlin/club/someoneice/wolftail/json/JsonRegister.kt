package club.someoneice.wolftail.json

import club.someoneice.json.JSON
import club.someoneice.wolftail.api.StyleType
import net.minecraft.util.ResourceLocation
import java.io.File
import java.io.FileNotFoundException

object JsonRegister {
  fun registerDir(dir: File) {
    if (!dir.exists() || !dir.isDirectory) {
      throw FileNotFoundException("Can't register WolfTailUI Package: ${dir.name}")
    }

    val packageName = dir.name
    val textureDir = dir.listFiles()!!.firstOrNull { it.name == "textures" } ?: return
    dir.listFiles()!!.forEach {
      if(!it.isFile) {
        return@forEach
      }

      val type = StyleType.valueOf(it.nameWithoutExtension)
      createStyle(it, textureDir, packageName, type)
    }
  }

  fun createStyle(root: File, textureDir: File, packageName: String, type: StyleType) {
    val node = JSON.json5.parse(root).asMapNodeOrEmpty()
    type.builtInRegistry[ResourceLocation(packageName, type.name)] = type.builder(node, textureDir)
  }
}
