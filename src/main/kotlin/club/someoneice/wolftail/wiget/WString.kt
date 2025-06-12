package club.someoneice.wolftail.wiget

import club.someoneice.wolftail.WolfTailUI
import club.someoneice.wolftail.api.IWidget
import club.someoneice.wolftail.getMC
import net.minecraft.client.gui.Gui
import net.minecraft.client.resources.I18n
import net.minecraft.util.ResourceLocation
import java.awt.Color

open class WString(private val title: String, private val startPos: Pair<Int, Int>, val color: Color = Color.WHITE, private val shadow: Color = Color.BLACK): IWidget {
    override fun getTexture(): ResourceLocation = ResourceLocation(WolfTailUI.ID, "label_string_$title")
    override fun wightStartAt(): Pair<Int, Int> = startPos

    override fun render(gui: Gui, mouseX: Int, mouseY: Int, x: Int, y: Int) {
        val font = getMC().fontRenderer
        val posX = x + this.wightStartAt().first
        val posY = y + this.wightStartAt().second
        font.drawString(I18n.format(title), posX + 1, posY + 1, shadow.rgb)
        font.drawString(I18n.format(title), posX, posY, color.rgb)
    }
}