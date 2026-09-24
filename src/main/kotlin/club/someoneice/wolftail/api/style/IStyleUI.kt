package club.someoneice.wolftail.api.style

import club.someoneice.wolftail.api.IStyle
import club.someoneice.wolftail.util.UIPos
import com.google.common.collect.ImmutableMap
import net.minecraft.client.gui.Gui
import net.minecraft.util.ResourceLocation

interface IStyleUI: IStyle {
  companion object {
    val INSTANCE: IStyleUI = object : IStyleUI {
      override fun getTexture(): ResourceLocation? = null

      override fun drawSlot(pGui: Gui, pPosX: Int, pPosY: Int, pGuiX: Int, pGuiY: Int,
                            args: Map<String, Any>) {
      }

      override fun render(pGui: Gui, rect: UIPos, pGuiX: Int, pGuiY: Int, args: Map<String, Any>) {
      }
    }

    fun fromStyle(style: IStyle): IStyleUI {
      return object : IStyleUI {
        override fun getTexture(): ResourceLocation? = style.getTexture()
        override fun render(pGui: Gui, rect: UIPos, pGuiX: Int, pGuiY: Int, args: Map<String, Any>) {
          style.render(pGui, rect, pGuiX, pGuiY, args)
        }

        override fun drawSlot(pGui: Gui, pPosX: Int, pPosY: Int, pGuiX: Int, pGuiY: Int, args: Map<String, Any>) {
        }
      }
    }
  }

  fun drawSlot(pGui: Gui, pPosX: Int, pPosY: Int, pGuiX: Int, pGuiY: Int, args: Map<String, Any> = ImmutableMap.of())
}
