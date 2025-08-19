package club.someoneice.wolftail.style

import club.someoneice.wolftail.clearColor
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.Gui
import net.minecraft.client.renderer.texture.DynamicTexture
import net.minecraft.util.ResourceLocation
import java.awt.Color
import java.awt.image.BufferedImage

object StyleSlot {
    val COLOR = Color(76, 86, 106)

    private val texture: DynamicTexture
    private val resourceID: ResourceLocation

    init {
        val imageBackground = BufferedImage(256, 256, BufferedImage.TYPE_INT_ARGB)
        val g2d = imageBackground.createGraphics()
        g2d.color = COLOR
        g2d.fillRect(0, 0, 16, 16)
        g2d.dispose()

        this.texture = DynamicTexture(imageBackground)

        this.resourceID = Minecraft.getMinecraft().textureManager
            .getDynamicTextureLocation("wolftailui:slot", texture)
    }

    fun renderAt(gui: Gui, x: Int, y: Int) {
        clearColor()
        Minecraft.getMinecraft().renderEngine.bindTexture(resourceID)
        gui.drawTexturedModalRect(x, y, 0, 0, 16, 16)
    }
}
