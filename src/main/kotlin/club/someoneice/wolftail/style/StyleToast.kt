package club.someoneice.wolftail.style

import club.someoneice.wolftail.WolfTailUI
import club.someoneice.wolftail.api.IUIStyle
import club.someoneice.wolftail.clearColor
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.Gui
import net.minecraft.util.ResourceLocation
import org.lwjgl.opengl.GL11
import org.lwjgl.util.Rectangle

open class StyleToast(val startAt: Int) : IUIStyle {
    companion object {
        val TOAST_DARK_UI = StyleToast(0)
        val TOAST_LIGHT_UI = StyleToast(32)
        val TOAST_T_DARK_UI = StyleToast(64)
        val TOAST_T_LIGHT_UI = StyleToast(96)
    }

    private val range = Rectangle(96, startAt, 160, 32)
    private val texture = ResourceLocation(WolfTailUI.ID, "default_ui.png")

    override fun getTexture(): ResourceLocation = texture
    override fun getUIRange(): Rectangle = range

    override fun renderBackground(pGui: Gui, pPosX: Int, pPosY: Int) {
        clearColor()
        GL11.glEnable(GL11.GL_TEXTURE_2D)
        Minecraft.getMinecraft().textureManager.bindTexture(this.getTexture())
        GL11.glDisable(GL11.GL_LIGHTING)
        this.renderTexture(pGui, pPosX, pPosY)
    }

    open fun renderTexture(pGui: Gui, pPosX: Int, pPosY: Int) {
        val rangeIn = this.getUIRange()
        pGui.drawTexturedModalRect(pPosX, pPosY, rangeIn.x, rangeIn.y, rangeIn.width, rangeIn.height)
    }
}
