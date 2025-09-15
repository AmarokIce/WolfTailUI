package club.someoneice.wolftail

import net.minecraft.client.Minecraft
import org.lwjgl.opengl.GL11

private val mc = Minecraft.getMinecraft()

fun getMC() = mc
fun clearColor() {
  GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f)
}

/**
 * Just a sandman for pass the function.
 */
fun sandman() {
  /* Do nothing. */
}
