package club.someoneice.wolftail.ui

import club.someoneice.wolftail.WolfTailUI
import club.someoneice.wolftail.api.IUIStyle
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.Gui
import net.minecraft.util.ResourceLocation
import org.lwjgl.opengl.GL11

open class BasedToastUIStyle(val startAt: Int): IUIStyle {
    override fun getTexture(): ResourceLocation =
        ResourceLocation(WolfTailUI.ID, "default_ui.png")

    override fun renderBackground(gui: Gui, x: Int, y: Int, w: Int, h: Int) {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f)
        GL11.glEnable(GL11.GL_TEXTURE_2D)
        Minecraft.getMinecraft().textureManager.bindTexture(this.getTexture())
        GL11.glDisable(GL11.GL_LIGHTING)
        this.renderTexture(gui, x, y, w, h)
    }

    open fun renderTexture(gui: Gui, x: Int, y: Int, w: Int, h: Int) {
        gui.drawTexturedModalRect(x, y, 96, startAt, 160, 32)
    }
}