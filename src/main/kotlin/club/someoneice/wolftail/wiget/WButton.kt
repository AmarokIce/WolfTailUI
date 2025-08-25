package club.someoneice.wolftail.wiget

import club.someoneice.wolftail.WolfTailUI
import club.someoneice.wolftail.api.IWidget
import club.someoneice.wolftail.getMC
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.Gui
import net.minecraft.client.renderer.texture.DynamicTexture
import net.minecraft.client.resources.I18n
import net.minecraft.util.ResourceLocation
import java.awt.Color
import java.awt.image.BufferedImage

open class WButton(
    private val title: String,
    private val startAt: Pair<Int, Int>,
    private val endAt: Pair<Int, Int>,
    private val lightStyle: Boolean = false,
    private val whenClicked: () -> Unit
) : IWidget {
    private val texture: DynamicTexture
    private val resourceID: ResourceLocation
    private val sizeOf = Pair(this.endAt.first - this.startAt.first, this.endAt.second - this.startAt.second)

    private val textureID = ResourceLocation(WolfTailUI.ID, "button_$title")

    init {
        val imageBackground = BufferedImage(256, 256, BufferedImage.TYPE_INT_ARGB)
        val g2d = imageBackground.createGraphics()
        g2d.color = if (!lightStyle) COLOR_B0 else COLOR_L0
        g2d.fillRect(0, 0, sizeOf.first, sizeOf.second)

        g2d.color = if (!lightStyle) COLOR_B1 else COLOR_L1
        g2d.fillRect(0, sizeOf.second + 1, sizeOf.first, sizeOf.second)
        g2d.dispose()

        this.texture = DynamicTexture(imageBackground)

        this.resourceID = Minecraft.getMinecraft().textureManager
            .getDynamicTextureLocation(this.getTexture().toString(), texture)
    }

    override fun canBeClick(): Boolean = true
    override fun getTexture(): ResourceLocation = this.textureID

    override fun wightStartAt(): Pair<Int, Int> = startAt
    override fun wightEndAt(): Pair<Int, Int> = endAt

    override fun onClick(gui: Gui, mouseX: Int, mouseY: Int, x: Int, y: Int) {
        if (!this.isInRange(mouseX, mouseY, x, y)) {
            return
        }

        whenClicked()
    }

    override fun render(gui: Gui, mouseX: Int, mouseY: Int, x: Int, y: Int) {
        Minecraft.getMinecraft().renderEngine.bindTexture(resourceID)

        val startY = if (this.isInRange(mouseX, mouseY, x, y)) sizeOf.second + 1 else 0

        gui.drawTexturedModalRect(
            x + this.wightStartAt().first,
            y + this.wightStartAt().second,
            0,
            startY,
            this.sizeOf.first,
            this.sizeOf.second
        )

        drawString(gui, mouseX, mouseY, x, y)
    }

    open fun drawString(gui: Gui, mouseX: Int, mouseY: Int, x: Int, y: Int) {
        val data = I18n.format(title)
        val font = getMC().fontRenderer
        val width = font.getStringWidth(data)
        val height = font.FONT_HEIGHT

        val startX = x + this.wightStartAt().first
        val startY = y + this.wightStartAt().second

        gui.drawString(
            getMC().fontRenderer,
            data,
            startX + (this.sizeOf.first / 2 - width / 2),
            startY + sizeOf.second / 2 - height / 2,
            Color.WHITE.rgb
        )
    }

    companion object {
        val COLOR_B0 = Color(180, 142, 173, 255)
        val COLOR_B1 = Color(208, 135, 112, 255)

        val COLOR_L0 = Color(129, 161, 193, 255)
        val COLOR_L1 = Color(94, 129, 172, 255)
    }
}
