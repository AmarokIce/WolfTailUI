package club.someoneice.wolftail.api

import com.google.common.collect.ImmutableList
import net.minecraft.util.IChatComponent

interface ITooltip {
  fun getData(): ImmutableList<IChatComponent>
}
