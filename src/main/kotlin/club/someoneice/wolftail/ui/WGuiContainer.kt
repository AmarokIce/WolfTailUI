package club.someoneice.wolftail.ui

import club.someoneice.wolftail.api.IWidget
import club.someoneice.wolftail.clearColor
import club.someoneice.wolftail.style.StyleGameUI
import club.someoneice.wolftail.style.StyleSlot
import net.minecraft.client.gui.inventory.GuiContainer
import net.minecraft.inventory.Container
import net.minecraft.inventory.Slot
import org.lwjgl.input.Mouse
import org.lwjgl.opengl.GL11

open class WGuiContainer(private val container: Container, private val sizeOfTexture: Pair<Int, Int>, private val style: StyleGameUI) : GuiContainer(container) {
    constructor(container: Container, title: String, size: Pair<Int, Int> = Pair(176, 166), lightStyle: Boolean = false): this(container, size, StyleGameUI(title, size, lightStyle))

    private val widgets = ArrayList<IWidget>()

    fun addWidget(widget: IWidget) {
        this.widgets.add(widget)
    }

    fun removeWidget(index: Int) {
        this.widgets.removeAt(index)
    }

    fun removeWidget(widget: IWidget) {
        this.widgets.remove(widget)
    }

    override fun drawGuiContainerBackgroundLayer(d: Float, mouseX: Int, mouseY: Int) {
        clearColor()
        val x: Int = (this.width - this.sizeOfTexture.first) / 2
        val y: Int = (this.height - this.sizeOfTexture.second) / 2

        GL11.glDisable(GL11.GL_DEPTH_TEST)
        GL11.glEnable(GL11.GL_TEXTURE_2D)
        style.renderBackground(this, x, y)

        this.render(mouseX, mouseY)

        widgets.forEach {
            it.render(this, mouseX, mouseY, x, y)
        }
    }

    override fun initGui() {
        super.initGui()
        start()
    }

    override fun mouseClicked(mouseX: Int, mouseY: Int, mouseButton: Int) {
        super.mouseClicked(mouseX, mouseY, mouseButton)
        if (!Mouse.isButtonDown(0)) {
            return
        }

        val x: Int = (this.width - this.sizeOfTexture.first) / 2
        val y: Int = (this.height - this.sizeOfTexture.second) / 2
        this.widgets.forEach {
            it.onClick(this, mouseX, mouseY, x, y)
        }
    }

    /**
     * Add the game widget and more.
     */
    fun start() {
    }

    /**
     * Custom render the data.
     */
    fun render(mouseX: Int, mouseY: Int) {
    }

    fun renderSlot() {
        container.inventorySlots.map { it as Slot } .forEach {
            StyleSlot.renderAt(this, it.xDisplayPosition, it.yDisplayPosition)
        }
    }
}