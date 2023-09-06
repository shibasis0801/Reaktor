package app.mehmaan.core.extensions

import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.compose.ui.geometry.Rect
import androidx.core.content.ContextCompat
import kotlin.math.roundToInt


data class Bounds(
    val left: Float,
    val top: Float,
    val width: Int,
    val height: Int
) {
   companion object {
       fun fromComposeBounds(rect: Rect) = Bounds(
           left = -rect.left,
           top = -rect.top,
           width = rect.width.roundToInt(),
           height = rect.height.roundToInt()
       )
   }
}

suspend fun View.screenshot(
    bounds: Bounds
): Bitmap {
    val bitmap = Bitmap.createBitmap(bounds.width, bounds.height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    canvas.translate(bounds.left, bounds.top)
    draw(canvas) // This is expensive
    return bitmap
}


@ColorInt
fun View.getColor(@ColorRes id: Int) = ContextCompat.getColor(context, id)