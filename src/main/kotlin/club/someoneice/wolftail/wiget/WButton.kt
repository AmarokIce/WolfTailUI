package club.someoneice.wolftail.wiget

import club.someoneice.wolftail.api.IWidgetFunction
import club.someoneice.wolftail.api.IWidgetWithStyle
import club.someoneice.wolftail.getMC
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.Gui
import net.minecraft.client.resources.I18n
import java.awt.Color

abstract class WButton(
  private val title: String,
  private val startAt: Pair<Int, Int>,
  private val endAt: Pair<Int, Int>,
  private val whenClicked: () -> Unit
) : IWidgetFunction, IWidgetWithStyle {
  private val sizeOf = Pair(this.endAt.first - this.startAt.first, this.endAt.second - this.startAt.second)

  init {

  }

  override fun canBeClick(): Boolean = true

  override fun wightStartAt(): Pair<Int, Int> = startAt
  override fun wightEndAt(): Pair<Int, Int> = endAt

  override fun onClick(gui: Gui, mouseX: Int, mouseY: Int, x: Int, y: Int) {
    if (!this.isInRange(mouseX, mouseY, x, y)) {
      return
    }

    whenClicked()
  }

  override fun onPassed(pGui: Gui, pMouseX: Int, pMouseY: Int, pGuiY: Int, pGuiX: Int) {
  }

  override fun render(gui: Gui, mouseX: Int, mouseY: Int, x: Int, y: Int) {
    // Fixme - set style render.
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
}
