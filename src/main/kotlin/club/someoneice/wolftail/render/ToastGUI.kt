package club.someoneice.wolftail.render

import club.someoneice.wolftail.api.IToast
import cpw.mods.fml.common.registry.GameRegistry
import cpw.mods.fml.relauncher.Side
import cpw.mods.fml.relauncher.SideOnly
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.Gui
import net.minecraft.client.gui.ScaledResolution
import net.minecraft.client.renderer.entity.RenderItem
import net.minecraft.util.ResourceLocation
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL12

@SideOnly(Side.CLIENT)
class ToastGUI(val toast: IToast): Gui() {
    var lessAliveTick = MAX_TICK
    var scaleW: Int = 0
    var scaleH: Int = 0

    fun tick(index: Int) {
        setUp()
        GL11.glDisable(GL11.GL_DEPTH_TEST)
        GL11.glDepthMask(false)
        run {
            val x = this.scaleW - (160 * getXFactor()).toInt()
            val y = 32 + index * 8
            toast.getUIStyle().renderBackground(this, x, y, -1, -1)

            mc.fontRenderer.drawString(this.toast.getToastTitle().toString(), x + 30, y + 7, -256)
            mc.fontRenderer.drawString(this.toast.getToastText().toString(), x + 30, y + 18, -1)

            if (this.toast.byItemStack()) {
                renderItemStack(x, y)
            } else {
                renderIcon(this.toast.getToastIcon(), x, y)
            }
        }
        GL11.glDepthMask(true)
        GL11.glEnable(GL11.GL_DEPTH_TEST)

        lessAliveTick--
    }

    fun renderIcon(rl: ResourceLocation, x: Int, y: Int) {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f)
        GL11.glEnable(GL11.GL_TEXTURE_2D)
        Minecraft.getMinecraft().textureManager.bindTexture(rl)
        GL11.glDisable(GL11.GL_LIGHTING)
        this.toast.bindTexture(this, x + 8, y + 8)
    }

    fun renderItemStack(x: Int, y: Int) {
        GL11.glDisable(GL11.GL_LIGHTING)
        GL11.glEnable(GL12.GL_RESCALE_NORMAL)
        GL11.glEnable(GL11.GL_COLOR_MATERIAL)
        GL11.glEnable(GL11.GL_LIGHTING)
        val rl = this.toast.getToastIcon()
        itemRenderer.renderItemAndEffectIntoGUI(
            mc.fontRenderer,
            mc.textureManager,
            GameRegistry.findItemStack(rl.resourceDomain, rl.resourcePath, 1),
            x + 8,
            y + 8
        )
        GL11.glDisable(GL11.GL_LIGHTING)
        GL11.glDepthMask(true)
        GL11.glEnable(GL11.GL_DEPTH_TEST)
    }

    private fun setUp() {
        GL11.glViewport(0, 0, mc.displayWidth, mc.displayHeight)
        GL11.glMatrixMode(GL11.GL_PROJECTION)
        GL11.glLoadIdentity()
        GL11.glMatrixMode(GL11.GL_MODELVIEW)
        GL11.glLoadIdentity()
        val scale = ScaledResolution(mc, mc.displayWidth, mc.displayHeight)
        this.scaleW = scale.scaledWidth
        this.scaleH = scale.scaledHeight
        GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT)
        GL11.glMatrixMode(GL11.GL_PROJECTION)
        GL11.glLoadIdentity()
        GL11.glOrtho(0.0, this.scaleW.toDouble(), this.scaleH.toDouble(), 0.0, 1000.0, 3000.0)
        GL11.glMatrixMode(GL11.GL_MODELVIEW)
        GL11.glLoadIdentity()
        GL11.glTranslatef(0.0f, 0.0f, -2000.0f)
    }

    private fun getXFactor(): Double {
        if (lessAliveTick < 50) {
            return lessAliveTick / 50.0
        } else if (lessAliveTick > 150) {
            return (200 - lessAliveTick) / 50.0
        }
        return 1.0
    }

    fun isDead(): Boolean = lessAliveTick < 0

    companion object {
        private const val MAX_TICK = 200
        private val itemRenderer = RenderItem()
        private val mc = Minecraft.getMinecraft()
    }
}