package club.someoneice.wolftail.ui

import club.someoneice.wolftail.api.IToast
import cpw.mods.fml.common.registry.GameRegistry
import cpw.mods.fml.relauncher.Side
import cpw.mods.fml.relauncher.SideOnly
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.Gui
import net.minecraft.client.gui.ScaledResolution
import net.minecraft.client.renderer.RenderHelper
import net.minecraft.client.renderer.entity.RenderItem
import net.minecraft.client.resources.I18n
import net.minecraft.util.ResourceLocation
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL12

@SideOnly(Side.CLIENT)
class WGuiToast(val toast: IToast): Gui() {
    var lessAliveTick = MAX_TICK
    var speed = 25

    fun tick() {
        GL11.glDisable(GL11.GL_DEPTH_TEST)
        GL11.glDepthMask(false)

        GL11.glEnable(GL11.GL_TEXTURE_2D)

        val style = toast.getUIStyle()

        val x = scaleW - (160 * getXFactor()).toInt()
        val y = indexY

        style.renderBackground(this, x, y)

        mc.fontRenderer.drawString(I18n.format(this.toast.getToastTitle()), x + 30, y + 7, -256)
        mc.fontRenderer.drawString(I18n.format(this.toast.getToastText()), x + 30, y + 18, -1)

        if (this.toast.byItemStack()) {
            renderItemStack(x + 8, y + 8)
        } else {
            renderIcon(this.toast.getToastIcon(), x + 8, y + 8)
        }

        GL11.glDepthMask(true)
        GL11.glEnable(GL11.GL_DEPTH_TEST)

        GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT)

        lessAliveTick--
        indexY += style.getUIRange().height
    }

    fun renderIcon(rl: ResourceLocation, x: Int, y: Int) {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f)
        GL11.glEnable(GL11.GL_TEXTURE_2D)
        mc.textureManager.bindTexture(rl)
        GL11.glDisable(GL11.GL_LIGHTING)
        this.toast.bindTexture(this, x, y)
    }

    // Fixme - The seconds and more will have no alphas.
    fun renderItemStack(x: Int, y: Int) {

        val rl = this.toast.getToastIcon()
        val stack = GameRegistry.findItemStack(rl.resourceDomain, rl.resourcePath, 1)
        val icon = stack.iconIndex

        GL11.glEnable(GL12.GL_RESCALE_NORMAL)
        RenderHelper.enableGUIStandardItemLighting()
        itemRenderer.renderItemAndEffectIntoGUI(mc.fontRenderer, mc.textureManager, stack, x, y)
        itemRenderer.renderItemOverlayIntoGUI(mc.fontRenderer, mc.textureManager, stack, x, y)
        RenderHelper.disableStandardItemLighting()
        GL11.glDisable(GL12.GL_RESCALE_NORMAL)
    }

    private fun getXFactor(): Double  {
        return if (lessAliveTick < speed) {
            lessAliveTick / speed.toDouble()
        } else if (lessAliveTick > 200 - speed) {
            (200 - lessAliveTick) / speed.toDouble()
        } else {
            1.0
        }
    }

    fun isDead(): Boolean = lessAliveTick < 0

    companion object {
        private const val MAX_TICK = 200
        private val itemRenderer = RenderItem()
        private val mc = Minecraft.getMinecraft()

        var scaleW: Int = 0
        var scaleH: Int = 0

        var indexY = 0

        fun setUp() {
            GL11.glViewport(0, 0, mc.displayWidth, mc.displayHeight)
            GL11.glMatrixMode(GL11.GL_PROJECTION)
            GL11.glLoadIdentity()
            GL11.glMatrixMode(GL11.GL_MODELVIEW)
            GL11.glLoadIdentity()
            val scale = ScaledResolution(mc, mc.displayWidth, mc.displayHeight)
            scaleW = scale.scaledWidth
            scaleH = scale.scaledHeight
            GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT)
            GL11.glMatrixMode(GL11.GL_PROJECTION)
            GL11.glLoadIdentity()
            GL11.glOrtho(0.0, scaleW.toDouble(), scaleH.toDouble(), 0.0, 1000.0, 3000.0)
            GL11.glMatrixMode(GL11.GL_MODELVIEW)
            GL11.glLoadIdentity()
            GL11.glTranslatef(0.0f, 0.0f, -2000.0f)

            indexY = 0
        }
    }
}