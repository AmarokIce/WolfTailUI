package club.someoneice.wolftail.api.style

import club.someoneice.wolftail.api.IStyle
import net.minecraft.util.ResourceLocation
import javax.annotation.CheckForNull

interface IStyleImage: IStyle {
  @CheckForNull
  override fun getTexture(): ResourceLocation? = null

}
