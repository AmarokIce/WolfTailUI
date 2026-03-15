package club.someoneice.wolftail.json

import club.someoneice.json.node.JsonNode
import club.someoneice.json.node.MapNode
import club.someoneice.wolftail.api.IStyle
import club.someoneice.wolftail.style.StyleFont
import club.someoneice.wolftail.style.StyleToast
import club.someoneice.wolftail.util.ResourceTextureUtil
import net.minecraft.util.ResourceLocation
import java.io.File
import java.io.FileNotFoundException
import javax.imageio.ImageIO

object StyleBuilder {
  private data class GuiData(
    val x: Int,
    val y: Int,
    val w: Int,
    val h: Int,
    val texture: String,
  ) {
    companion object {
      fun create(conf: MapNode): GuiData {
        val x = conf.getAsTypeOrThrow("x", JsonNode.NodeType.Int).obj as Int
        val y = conf.getAsTypeOrThrow("y", JsonNode.NodeType.Int).obj as Int
        val w = conf.getAsTypeOrThrow("w", JsonNode.NodeType.Int).obj as Int
        val h = conf.getAsTypeOrThrow("h", JsonNode.NodeType.Int).obj as Int
        val texture = conf.getAsTypeOrThrow("texture", JsonNode.NodeType.String).toString()

        return GuiData(x, y, w, h, texture)
      }
    }

  }

  fun toastStyleBuilder(conf: MapNode, textureDir: File): StyleToast {
    val data = GuiData.create(conf)
    val texture = findAllFiles(textureDir).firstOrNull() { it.name == data.texture }
      ?: throw FileNotFoundException("Can't get texture file: ${data.texture}. " +
        "Make sure it exists in any dir from root dir texture.")

    val img = ImageIO.read(texture)

    val rl =
      ResourceTextureUtil.createDynamicTexture(ResourceLocation(data.texture), img)

    return StyleToast(data.x, data.y, data.w, data.h, rl)
  }

  fun guiStyleBuilder(conf: MapNode, textureDir: File): IStyle {
    throw NotImplementedError()
  }

  fun buttonStyleBuilder(conf: MapNode, textureDir: File): IStyle {
    throw NotImplementedError()
  }

  fun labelStyleBuilder(conf: MapNode, textureDir: File): IStyle {
    throw NotImplementedError()
  }

  fun imageStyleBuilder(conf: MapNode, textureDir: File): IStyle {
    throw NotImplementedError()
  }

  fun switchStyleBuilder(conf: MapNode, textureDir: File): IStyle {
    throw NotImplementedError()
  }

  fun stringStyleBuilder(conf: MapNode, textureDir: File): IStyle {
    return StyleFont.INSTANCE
  }

  private fun findAllFiles(file: File): List<File> {
    val result = mutableListOf<File>()
    file.listFiles(File::isFile)!!.forEach(result::add)
    file.listFiles(File::isDirectory)!!.flatMap(::findAllFiles).forEach(result::add)
    return result
  }
}
