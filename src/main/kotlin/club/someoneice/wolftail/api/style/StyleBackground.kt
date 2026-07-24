package club.someoneice.wolftail.api.style

import club.someoneice.wolftail.util.UIPos
import com.google.common.collect.Lists
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.Gui
import net.minecraft.util.ResourceLocation
import org.lwjgl.opengl.GL11

class StyleBackground(
  private val texture: ResourceLocation,
  private val slotRenderer: (IStyleUI, Gui, Int, Int, Map<String, Any>) -> Unit,
  private val backgroundRenderer: (IStyleUI, Gui, Int, Int, Int, Int, Int, Int, Map<String, Any>) -> Unit
): IStyleUI {
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

  override fun render(pGui: Gui, rect: UIPos, pGuiX: Int, pGuiY: Int, args: Map<String, Any>) {
    this.backgroundRenderer(this, pGui, rect.x, rect.y, rect.w, rect.h, pGuiX, pGuiY, args)
  }

  companion object {
    private fun defaultSlotRenderer(slotRange: UIPos): (IStyleUI, Gui, Int, Int, Map<String, Any>) -> Unit =
      { pStyle, pGui, pX, pY, args ->
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f)
        GL11.glEnable(GL11.GL_TEXTURE_2D)
        Minecraft.getMinecraft().textureManager.bindTexture(pStyle.getTexture())
        GL11.glDisable(GL11.GL_LIGHTING)

        slotRange.drawTexture(pGui, pX, pY)
      }

    private fun defaultBackgroundRenderer(
      bgUpLeft: UIPos,
      bgUp: UIPos,
      bgUpRight: UIPos,
      bgMidLeft: UIPos,
      bgMid: UIPos,
      bgMidRight: UIPos,
      bgDownLeft: UIPos,
      bgDown: UIPos,
      bgDownRight: UIPos
    ): (IStyleUI, Gui, Int, Int, Int, Int, Map<String, Any>) -> Unit =
      { pStyle, pGui, pX, pY, pW, pH, args ->
        val renderList: MutableList<TileCommand> =
          Lists.newArrayListWithExpectedSize((pW / bgUpLeft.w + 1) * (pH / bgUpLeft.h + 1))

        fun fixHeight(fixValue: Int, top: Boolean = true) {
          if (fixValue < 0) {
            if (top) {

            }
          }
        }

        var lessOf = 0

        /* Top */
        renderList.add(TileCommand(bgUpLeft, pX, pY))

        for(i in 1 .. ((pW - bgUpRight.w) / bgUpLeft.w)) {
          renderList.add(TileCommand(bgUp, bgUpLeft.w + bgUp.w * i, pY))
        }

        lessOf = (pW - bgUpRight.w) % bgUpLeft.w
        if (lessOf > 0) {
          renderList.add(TileCommand(bgUp, (pX + pW - bgUpRight.w - lessOf), pY, cutX = lessOf))
          lessOf = 0
        }

        renderList.add(TileCommand(bgUpRight, pX + pW - bgUpRight.w, pY))

        /* Top fix */
        var fix = bgUpLeft.h - bgUp.h
      }



    data class TileCommand(
      val textureCut: UIPos,
      val posX: Int,
      val posY: Int,
      val startX: Int = textureCut.x,
      val startY: Int = textureCut.y,
      val cutX: Int = textureCut.w,
      val cutY: Int = textureCut.h
    )
  }
}
