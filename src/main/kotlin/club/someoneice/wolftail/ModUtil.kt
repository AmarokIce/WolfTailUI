package club.someoneice.wolftail

import net.minecraft.client.Minecraft
import org.lwjgl.opengl.GL11
import org.lwjgl.util.Rectangle

private val mc = Minecraft.getMinecraft()

fun getMC() = mc
fun clearColor() {
  GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f)
}

fun getStartAtByRec(rec: Rectangle): Pair<Int, Int> {
  return Pair(rec.x, rec.y)
}

fun getEndAtByRec(rec: Rectangle): Pair<Int, Int> {
  return Pair(rec.x + rec.width, rec.y + rec.height)
}
