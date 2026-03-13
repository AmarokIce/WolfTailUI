package club.someoneice.wolftail.util

import com.google.common.cache.Cache
import com.google.common.cache.CacheBuilder
import net.minecraft.util.ResourceLocation
import sun.font.FontDesignMetrics
import java.awt.Color
import java.awt.Font
import java.awt.image.BufferedImage
import java.util.concurrent.TimeUnit

object FontManager {
  val EMPTY_IMAGE = BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB)
  val FONTS = mutableMapOf<String, ResourceLocation>()
  val CACHE_FONTS: Cache<String, Font> = CacheBuilder
    .newBuilder()
    .maximumSize(10)
    .expireAfterAccess(5, TimeUnit.MINUTES)
    .build()

  init {
    FONTS["vonwaon"] = ResourceLocation("wolftail:VonwaonBitmap.ttf")
  }

  fun renderFontToDmyTexture(fontName: String, text: String, color: Color = Color.WHITE, scale: Float = 16.0f): BufferedImage {
    if (!(FONTS.containsKey(fontName))) {
      return EMPTY_IMAGE
    }

    val font = (CACHE_FONTS.getIfPresent(fontName) ?: run {
      val fontPath = FONTS[fontName]!!
      val path = ResourceLocationUtil.getPathFrom(fontPath)
      val stream =
        javaClass.classLoader.getResourceAsStream(path) ?: return EMPTY_IMAGE
      val font = Font.createFont(Font.TRUETYPE_FONT, stream)
      CACHE_FONTS.put(fontName, font)
      return@run font
    }).deriveFont(scale)

    val fm = FontDesignMetrics.getMetrics(font)
    val width = fm.stringWidth(text) + 4
    val height = fm.height + 2

    val img = BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)
    val graphics = img.createGraphics()
    graphics.font = font
    graphics.color = color
    graphics.drawString(text, 2, height - 1)
    graphics.dispose()
    return img
  }
}
