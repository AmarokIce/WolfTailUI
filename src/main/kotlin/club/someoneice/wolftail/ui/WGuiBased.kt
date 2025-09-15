package club.someoneice.wolftail.ui

import club.someoneice.wolftail.api.IWidget
import club.someoneice.wolftail.clearColor
import club.someoneice.wolftail.style.StyleGameUI
import net.minecraft.client.gui.GuiScreen
import org.lwjgl.opengl.GL11

abstract class WGuiBased(private val sizeOfTexture: Pair<Int, Int>, private val style: StyleGameUI) : GuiScreen() {
  constructor(title: String, size: Pair<Int, Int> = Pair(176, 166), lightStyle: Boolean = false) : this(
    size,
    StyleGameUI(title, size, lightStyle)
  )

  var basicX = 0
  var basicY = 0

  private val widgets = ArrayList<IWidget>()

  fun addWidget(pWidget: IWidget) {
    this.widgets.add(pWidget)
  }

  fun removeWidget(pIndex: Int) {
    this.widgets.removeAt(pIndex)
  }

  fun removeWidget(pWidget: IWidget) {
    this.widgets.remove(pWidget)
  }

  fun preRender() {
    clearColor()
    this.basicX = (this.width - this.sizeOfTexture.first) / 2
    this.basicY = (this.height - this.sizeOfTexture.second) / 2
  }

  override fun drawBackground(pBackgroundIndexOf: Int) {
    this.drawGradientRect(0, 0, this.width, this.height, -1072689136, -804253680)

    GL11.glDisable(GL11.GL_DEPTH_TEST)
    GL11.glEnable(GL11.GL_TEXTURE_2D)
    style.renderBackground(this, basicX, basicY)
  }

  final override fun drawScreen(pMouseX: Int, pMouseY: Int, pDelta: Float) {
    preRender()

    this.drawBackground(0)
    super.drawScreen(pMouseX, pMouseY, pDelta)

    val mouseX = pMouseX - basicX
    val mouseY = pMouseY - basicY

    this.render(basicX, basicY, mouseX, mouseY)

    widgets.forEach {
      it.render(this, mouseX, mouseY, basicX, basicY)
    }
  }

  override fun initGui() {
    super.initGui()
    start()
  }

  /**
   * Add the game widget and more.
   */
  abstract fun start()

  /**
   * Custom render the data.
   */
  abstract fun render(pPosX: Int, pPosY: Int, pMouseX: Int, pMouseY: Int)
}
