package club.someoneice.wolftail.ui.widget

import club.someoneice.wolftail.api.IMouseEventListener
import club.someoneice.wolftail.api.IStyle
import club.someoneice.wolftail.api.IWidget
import club.someoneice.wolftail.api.style.IStyleDataTree
import club.someoneice.wolftail.util.UIPos
import com.google.common.collect.ImmutableSet
import com.google.common.collect.Lists
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.Gui
import net.minecraft.client.gui.ScaledResolution
import org.lwjgl.opengl.GL11
import java.awt.Color
import java.util.*

open class WDataTree(
  private val pos: UIPos,
  private val style: IStyleDataTree,
  protected val canvasSize: Pair<Int, Int>,
  protected val lineColor: Color = Color.WHITE
): IWidget, IMouseEventListener {
  protected val children: MutableList<Node> = Lists.newArrayList()

  protected var offsetX = 0f
  protected var offsetY = 0f

  protected var lastMouseX = 0
  protected var lastMouseY = 0

  data class Node(
    val widget: IWidget,
    val root: ImmutableSet<Node>
  )

  fun addChild(child: Node): WDataTree {
    this.children.add(child)
    return this
  }

  fun addChild(widget: IWidget): WDataTree {
    this.children.add(Node(widget, ImmutableSet.of()))
    return this
  }

  fun addChild(widget: IWidget, vararg root: Node): WDataTree {
    this.children.add(Node(widget, ImmutableSet.copyOf(root)))
    return this
  }

  fun addChild(widget: IWidget, vararg root: Int): WDataTree {
    this.children.add(Node(widget, ImmutableSet.copyOf(root.map(this.children::get))))
    return this
  }

  fun removeChild(child: Node): WDataTree {
    this.children.remove(child)
    return this
  }

  fun removeChild(widget: IWidget): WDataTree {
    this.children.removeIf { it.widget == widget }
    return this
  }

  open fun onClicked(pGui: Gui, pMouseX: Int, pMouseY: Int, pGuiX: Int, pGuiY: Int, pMouseInput: Int): Boolean {
    val node = this.children
      .map(Node::widget)
      .filterIsInstance<IMouseEventListener>()
      .firstOrNull { it.isInRange(pMouseX, pMouseY, pGuiX, pGuiY) }
      ?.apply { this.onMouseClicked(pGui, pMouseX, pMouseY, pGuiX, pGuiY, pMouseInput) }
    return Objects.nonNull(node)
  }

  private fun drawLine(node: Node, pGuiX: Int, pGuiY: Int) {
    node.root.forEach {
      this.style.drawLine(it to node, pGuiX, pGuiY, this.lineColor)
    }
  }

  private fun clampOffset() {
    val minX = (this.pos.w - this.canvasSize.first).coerceAtMost(0).toFloat()
    val minY = (this.pos.h - this.canvasSize.second).coerceAtMost(0).toFloat()
    offsetX = offsetX.coerceIn(minX, 0f)
    offsetY = offsetY.coerceIn(minY, 0f)
  }

  override fun getStyle(): IStyle = this.style
  override fun weightPos(): UIPos = this.pos

  override fun render(pGui: Gui, pMouseX: Int, pMouseY: Int, pGuiX: Int, pGuiY: Int) {
    this.clampOffset()

    this.style.render(pGui, this.weightPos(), pGuiX, pGuiY)
    GL11.glEnable(GL11.GL_SCISSOR_TEST)
    val mc = Minecraft.getMinecraft()
    val scaledResolution = ScaledResolution(mc, mc.displayWidth, mc.displayHeight)
    val scaleFactor = scaledResolution.scaleFactor
    GL11.glScissor(
      (this.pos.x + pGuiX) * scaleFactor,
      mc.displayHeight - (this.pos.y + pGuiY + this.pos.h) * scaleFactor,
      this.pos.w * scaleFactor,
      this.pos.h * scaleFactor
    )
    GL11.glPushMatrix()
    GL11.glTranslatef(this.pos.x + offsetX, this.pos.y + offsetY, 0f)

    val cMouseX = pMouseX - (this.pos.x + offsetX).toInt()
    val cMouseY = pMouseY - (this.pos.y + offsetY).toInt()
    this.children.forEach {
      if (it.root.isNotEmpty()) {
        this.drawLine(it, pGuiX, pGuiY)
      }

      it.widget.render(pGui, cMouseX, cMouseY, pGuiX, pGuiY)
    }

    GL11.glPopMatrix()
    GL11.glDisable(GL11.GL_SCISSOR_TEST)
  }

  override fun onMouseClicked(pGui: Gui, pMouseX: Int, pMouseY: Int, pGuiX: Int, pGuiY: Int, pMouseButton: Int) {
    val cMouseX = pMouseX - (this.pos.x + offsetX).toInt()
    val cMouseY = pMouseX - (this.pos.y + offsetY).toInt()
    if (this.onClicked(pGui, cMouseX, cMouseY, pGuiX, pGuiY, pMouseButton)) {
      return
    }

    this.lastMouseX = pMouseX
    this.lastMouseY = pMouseY
  }

  override fun onMouseMove(pGui: Gui, pMouseX: Int, pMouseY: Int) {
    val dx = pMouseX - this.lastMouseX
    val dy = pMouseY - this.lastMouseY
    this.offsetX += dx
    this.offsetY += dy
    this.lastMouseX = pMouseX
    this.lastMouseY = pMouseY

    this.clampOffset()
  }
}
