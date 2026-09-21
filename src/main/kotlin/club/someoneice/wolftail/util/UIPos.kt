package club.someoneice.wolftail.util

import net.minecraft.client.gui.Gui
import org.lwjgl.util.Rectangle

class UIPos(
  var x: Int = 0,
  var y: Int = 0,
  var w: Int = 0,
  var h: Int = 0
) {
  fun getPosU(): Int = x + w
  fun getPosV(): Int = y + h

  fun toRectangle(): Rectangle = Rectangle(this.x, this.y, this.w, this.h)
  fun fromRectangle(rec: Rectangle): UIPos = UIPos(rec.x, rec.y, rec.width, rec.height)

  fun copy(): UIPos = UIPos(x, y, w, h)

  fun drawTexture(pGui: Gui, pGuiX: Int, pGuiY: Int) {
    pGui.drawTexturedModalRect(pGuiX, pGuiY,
      this.x, this.y, this.w, this.h)
  }

  companion object {
    fun Rectangle.toPositioning(): UIPos =
      UIPos(this.x, y, width, height)
  }
}
