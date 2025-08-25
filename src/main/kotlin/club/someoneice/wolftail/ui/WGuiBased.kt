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

    override fun drawBackground(pBackgroundIndexOf: Int) {
        this.drawGradientRect(0, 0, this.width, this.height, -1072689136, -804253680)

        clearColor()
        val x: Int = (this.width - this.sizeOfTexture.first) / 2
        val y: Int = (this.height - this.sizeOfTexture.second) / 2

        GL11.glDisable(GL11.GL_DEPTH_TEST)
        GL11.glEnable(GL11.GL_TEXTURE_2D)
        style.renderBackground(this, x, y)
    }

    final override fun drawScreen(pMouseX: Int, pMouseY: Int, pDelta: Float) {
        drawBackground(0)
        super.drawScreen(pMouseX, pMouseY, pDelta)

        val x: Int = (this.width - this.sizeOfTexture.first) / 2
        val y: Int = (this.height - this.sizeOfTexture.second) / 2
        val mouseX = pMouseX - x;
        val mouseY = pMouseY - y;

        this.render(x, y, mouseX, mouseY)

        widgets.forEach {
            it.render(this, mouseX, mouseY, x, y)
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
