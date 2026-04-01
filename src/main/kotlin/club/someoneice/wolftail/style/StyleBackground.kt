package club.someoneice.wolftail.style

import club.someoneice.wolftail.api.IUIStyle
import club.someoneice.wolftail.util.Positioning
import com.google.common.collect.Lists
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.Gui
import net.minecraft.util.ResourceLocation
import org.lwjgl.opengl.GL11

class StyleBackground(
  private val texture: ResourceLocation,
  private val slotRenderer: (IUIStyle, Gui, Int, Int, Map<String, Any>) -> Unit,
  private val backgroundRenderer: (IUIStyle, Gui, Int, Int, Int, Int, Map<String, Any>) -> Unit
): IUIStyle {
/* TODO()
    constructor(
    texture: ResourceLocation,
    slotRange: Positioning,
    bgUpLeft: Positioning,
    bgUp: Positioning,
    bgUpRight: Positioning,
    bgMidLeft: Positioning,
    bgMid: Positioning,
    bgMidRight: Positioning,
    bgDownLeft: Positioning,
    bgDown: Positioning,
    bgDownRight: Positioning
  ): this(
  )*/

  override fun getTexture(): ResourceLocation = this.texture

  override fun drawSlot(
    pGui: Gui,
    x: Int,
    y: Int,
    args: Map<String, Any>
  ) {
    this.slotRenderer(this, pGui, x, y, args)
  }

  override fun drawBackground(
    pGui: Gui,
    x: Int,
    y: Int,
    w: Int,
    h: Int,
    args: Map<String, Any>
  ) {
    this.backgroundRenderer(this, pGui, x, y, w, h, args)
  }

  companion object {
    private fun defaultSlotRenderer(slotRange: Positioning): (IUIStyle, Gui, Int, Int, Map<String, Any>) -> Unit =
      { pStyle, pGui, pX, pY, args ->
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f)
        GL11.glEnable(GL11.GL_TEXTURE_2D)
        Minecraft.getMinecraft().textureManager.bindTexture(pStyle.getTexture())
        GL11.glDisable(GL11.GL_LIGHTING)

        slotRange.drawTexture(pGui, pX, pY)
      }

    private fun defaultBackgroundRenderer(
      bgUpLeft: Positioning,
      bgUp: Positioning,
      bgUpRight: Positioning,
      bgMidLeft: Positioning,
      bgMid: Positioning,
      bgMidRight: Positioning,
      bgDownLeft: Positioning,
      bgDown: Positioning,
      bgDownRight: Positioning
    ): (IUIStyle, Gui, Int, Int, Int, Int, Map<String, Any>) -> Unit =
      { pStyle, pGui, pX, pY, pW, pH, args ->
        val renderList: MutableList<TileCommand> =
          Lists.newArrayListWithExpectedSize((pW / bgUpLeft.width + 1) * (pH / bgUpLeft.height + 1))

        fun fixHeight(fixValue: Int, top: Boolean = true) {
          if (fixValue < 0) {
            if (top) {

            }
          }
        }

        var lessOf = 0

        /* Top */
        renderList.add(TileCommand(bgUpLeft, pX, pY))

        for(i in 1 .. ((pW - bgUpRight.width) / bgUpLeft.width)) {
          renderList.add(TileCommand(bgUp, bgUpLeft.width + bgUp.width * i, pY))
        }

        lessOf = (pW - bgUpRight.width) % bgUpLeft.width
        if (lessOf > 0) {
          renderList.add(TileCommand(bgUp, (pX + pW - bgUpRight.width - lessOf), pY, cutX = lessOf))
          lessOf = 0
        }

        renderList.add(TileCommand(bgUpRight, pX + pW - bgUpRight.width, pY))

        /* Top fix */
        var fix = bgUpLeft.height - bgUp.height


      }



    data class TileCommand(
      val textureCut: Positioning,
      val posX: Int,
      val posY: Int,
      val startX: Int = textureCut.x,
      val startY: Int = textureCut.y,
      val cutX: Int = textureCut.width,
      val cutY: Int = textureCut.height
    )
  }
}
