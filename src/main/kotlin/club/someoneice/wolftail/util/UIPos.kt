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

  fun drawTexture(pGui: Gui, pGuiX: Int, pGuiY: Int) {
    pGui.drawTexturedModalRect(pGuiX, pGuiY,
      this.x, this.y, this.w, this.h)
  }

  fun offset(x: Int = 0, y: Int = 0, w: Int = 0, h: Int = 0): UIPos {
    return UIPos(
      x = this.x + x,
      y = this.y + y,
      w = this.w + w,
      h = this.h + h
    )
  }

  fun offsetSelf(x: Int = 0, y: Int = 0, w: Int = 0, h: Int = 0): UIPos {
    this.x += x
    this.y += y
    this.w += w
    this.h += h
    return this
  }

  companion object {
    fun Rectangle.toPositioning(): UIPos =
      UIPos(this.x, y, width, height)
  }
}
