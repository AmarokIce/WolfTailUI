package club.someoneice.wolftail.ui

import club.someoneice.wolftail.api.IWidget
import club.someoneice.wolftail.clearColor
import club.someoneice.wolftail.sandman
import club.someoneice.wolftail.style.StyleGameUI
import net.minecraft.client.gui.GuiScreen
import org.lwjgl.opengl.GL11

open class WGuiBased(private val sizeOfTexture: Pair<Int, Int>, private val style: StyleGameUI) : GuiScreen() {
    constructor(title: String, size: Pair<Int, Int> = Pair(176, 166), lightStyle: Boolean = false) : this(
        size,
        StyleGameUI(title, size, lightStyle)
    )

    private val widgets = ArrayList<IWidget>()

    fun addWidget(widget: IWidget): WGuiBased {
        this.widgets.add(widget)
        return this
    }

    fun removeWidget(index: Int) {
        this.widgets.removeAt(index)
    }

    fun removeWidget(widget: IWidget) {
        this.widgets.remove(widget)
    }

    override fun drawBackground(ingot: Int) {
        this.drawGradientRect(0, 0, this.width, this.height, -1072689136, -804253680)

        clearColor()
        val x: Int = (this.width - this.sizeOfTexture.first) / 2
        val y: Int = (this.height - this.sizeOfTexture.second) / 2

        GL11.glDisable(GL11.GL_DEPTH_TEST)
        GL11.glEnable(GL11.GL_TEXTURE_2D)
        style.renderBackground(this, x, y)
    }

    override fun drawScreen(mouseX: Int, mouseY: Int, d: Float) {
        drawBackground(0)
        super.drawScreen(mouseX, mouseY, d)
        this.render(mouseX, mouseY)

        val x: Int = (this.width - this.sizeOfTexture.first) / 2
        val y: Int = (this.height - this.sizeOfTexture.second) / 2

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
    open fun start() {
    }

    /**
     * Custom render the data.
     */
    open fun render(mouseX: Int, mouseY: Int) {
    }

    /**
     * If you want loop to play something, write in here.
     */
    override fun updateScreen() {
        sandman()
    }
}
