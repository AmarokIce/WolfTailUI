package club.someoneice.wolftail.api.style

import club.someoneice.wolftail.api.IStyle
import club.someoneice.wolftail.ui.widget.WDataTree
import club.someoneice.wolftail.util.UIPos
import net.minecraft.client.gui.Gui
import net.minecraft.util.ResourceLocation
import java.awt.Color

interface IStyleDataTree: IStyle {
  companion object {
    val INSTANCE = object: IStyleDataTree {
      override fun getTexture(): ResourceLocation? = null
      override fun render(pGui: Gui, rect: UIPos, pGuiX: Int, pGuiY: Int, args: Map<String, Any>) {
      }
    }

    fun fromStyle(style: IStyle): IStyleDataTree = object: IStyleDataTree {
      override fun getTexture(): ResourceLocation? = style.getTexture()
      override fun render(pGui: Gui, rect: UIPos, pGuiX: Int, pGuiY: Int, args: Map<String, Any>) {
        style.render(pGui, rect, pGuiX, pGuiY, args)
      }
    }
  }

  fun drawLine(pNodes: Pair<WDataTree.Node, WDataTree.Node>, pGuiX: Int, pGuiY: Int, pColor: Color) {


    val posFrom = pNodes.first.widget.weightPos()
    val posTo = pNodes.second.widget.weightPos()
    val offset = getOffset(posFrom, posTo)
    val from = posFrom.x + (posFrom.w / 2) + pGuiX + offset.x to posFrom.y + (posFrom.h / 2) + pGuiY + offset.y
    val to = posTo.x + (posTo.w / 2) + pGuiX + offset.w to posTo.y + (posTo.h / 2) + pGuiY + offset.h

    Gui.drawRect(
      from.first, from.second,
      from.first + 1, to.second - 1,
      pColor.rgb
    )

    Gui.drawRect(
      from.first + 1, to.second - 1,
      to.first, to.second,
      pColor.rgb
    )
  }

  fun getOffset(from: UIPos, to: UIPos): UIPos {
    if (from.x == to.x) {
      return UIPos(0, from.h / 2, 0, -(to.h / 2))
    }

    if (from.y == to.y) {
      return UIPos(from.w / 2, 0, -(to.w / 2), 0)
    }

    val offset = UIPos(0, 0, 0, 0)

    if (from.y < to.y) {
      offset.y = from.h / 2 + 1
      offset.w = -(to.w / 2) - 1
    } else {
      offset.x = -(from.w / 2) - 1
      offset.h = to.h / 2 + 1
    }

    return offset
  }
}
