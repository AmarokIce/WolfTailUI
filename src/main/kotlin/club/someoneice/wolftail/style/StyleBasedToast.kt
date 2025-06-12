package club.someoneice.wolftail.style

import club.someoneice.wolftail.WolfTailUI
import club.someoneice.wolftail.api.IUIStyle
import club.someoneice.wolftail.clearColor
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.Gui
import net.minecraft.util.ResourceLocation
import org.lwjgl.opengl.GL11
import org.lwjgl.util.Rectangle

open class StyleBasedToast(val startAt: Int): IUIStyle {
    companion object {
        val TOAST_DARK_UI = StyleBasedToast(0)
        val TOAST_LIGHT_UI = StyleBasedToast(32)
        val TOAST_T_DARK_UI = StyleBasedToast(64)
        val TOAST_T_LIGHT_UI = StyleBasedToast(96)
    }

    private val range = Rectangle(96, startAt, 160, 32)
    private val texture = ResourceLocation(WolfTailUI.ID, "default_ui.png")

    override fun getTexture(): ResourceLocation = texture
    override fun getUIRange(): Rectangle = range

    override fun renderBackground(gui: Gui, x: Int, y: Int) {
        clearColor()
        GL11.glEnable(GL11.GL_TEXTURE_2D)
        Minecraft.getMinecraft().textureManager.bindTexture(this.getTexture())
        GL11.glDisable(GL11.GL_LIGHTING)
        this.renderTexture(gui, x, y)
    }

    open fun renderTexture(gui: Gui, x: Int, y: Int) {
        gui.drawTexturedModalRect(x, y, 96, startAt, 160, 32)
    }
}