package club.someoneice.wolftail.util

import net.minecraft.client.gui.Gui
import org.lwjgl.util.Rectangle

class Positioning(
  var x: Int = 0,
  var y: Int = 0,
  var width: Int = 0,
  var height: Int = 0
) {
  fun getPosU(): Int = x + width
  fun getPosV(): Int = y + height

  fun toRectangle(): Rectangle = Rectangle(this.x, this.y, this.width, this.height)
  fun fromRectangle(rec: Rectangle): Positioning = Positioning(rec.x, rec.y, rec.width, rec.height)

  fun drawTexture(pGui: Gui, pX: Int, pY: Int) {
    pGui.drawTexturedModalRect(pX, pY, this.x, this.y, this.width, this.height)
  }

  companion object {
    fun Rectangle.toPositioning(): Positioning =
      Positioning(this.x, y, width, height)
  }
}
