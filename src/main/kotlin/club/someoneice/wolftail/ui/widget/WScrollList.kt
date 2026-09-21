package club.someoneice.wolftail.ui.widget

import club.someoneice.wolftail.api.IMouseEventListener
import club.someoneice.wolftail.api.IStyle
import club.someoneice.wolftail.api.IWidget
import club.someoneice.wolftail.util.UIPos
import com.google.common.collect.Lists
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.Gui
import net.minecraft.client.gui.ScaledResolution
import org.lwjgl.opengl.GL11

open class WScrollList(
  private val pos: UIPos,
  private val style: IStyle
) : IWidget, IMouseEventListener {
  constructor(
    x: Int,
    y: Int,
    width: Int,
    height: Int,
    style: IStyle
  ): this(UIPos(x, y, width, height), style)
  protected val children: MutableList<IWidget> = Lists.newArrayList()
  protected var contentHeight = 0

  var scrollAmount = 0f
    private set

  fun addChild(child: IWidget): WScrollList {
    this.children.add(child)
    recalculateHeight()
    return this
  }

  fun removeChild(child: IWidget): WScrollList {
    this.children.remove(child)
    recalculateHeight()
    return this
  }

  private fun recalculateHeight() {
    contentHeight = children.sumOf { it.weightPos().h }
  }

  override fun weightPos(): UIPos = this.pos
  override fun getStyle(): IStyle = this.style

  override fun render(pGui: Gui, pMouseX: Int, pMouseY: Int, pGuiX: Int, pGuiY: Int) {
    val rX = this.pos.x + pGuiX
    val rY = this.pos.y + pGuiY

    val maxScroll = 0.coerceAtLeast(contentHeight - pos.h)
    scrollAmount = scrollAmount.coerceIn(0f, maxScroll.toFloat())

    // FIXME - UI Style
    this.style.render(pGui, this.pos, pGuiX, pGuiY)
//    if (drawBackground) {
//      val color = 0x80000000.toInt()
//      Gui.drawRect(
//        rX, rY,
//        rX + pos.w, rY + pos.h,
//        color
//      )
//    }

    GL11.glEnable(GL11.GL_SCISSOR_TEST)
    applyScissor(Minecraft.getMinecraft(), rX, rY, pos.w, pos.h)

    var currentY = rY - scrollAmount.toInt()
    children.forEach {
      val h = it.weightPos().h
      if (currentY + h >= rY && currentY <= rY + pos.h) {
        it.render(pGui, pMouseX, pMouseY, rX, currentY)
      }

      currentY += h
    }

    GL11.glDisable(GL11.GL_SCISSOR_TEST)
  }

  override fun onMouseClicked(pGui: Gui, pMouseX: Int, pMouseY: Int, pGuiX: Int, pGuiY: Int, pMouseButton: Int) {
    var currentY = this.pos.y - scrollAmount.toInt()
    children.filterIsInstance<IMouseEventListener>()
      .filter(IMouseEventListener::canBeClick)
      .forEach {
        val childHeight = it.weightPos().h
        if (pMouseY in currentY until (currentY + childHeight)) {
          it.onMouseClicked(pGui, pMouseX, pMouseY, pGuiX, pGuiY, pMouseButton)
        }
        currentY += childHeight
      }
  }

  fun handleMouseInput(dWheel: Int) {
    scrollAmount -= if (dWheel > 0) 15f else -15f
  }

  private fun applyScissor(mc: Minecraft, x: Int, y: Int, w: Int, h: Int) {
    val res = ScaledResolution(mc, mc.displayWidth, mc.displayHeight)
    val scale = res.scaleFactor
    GL11.glScissor(
      x * scale,
      mc.displayHeight - (y + h) * scale,
      w * scale,
      h * scale
    )
  }
}
