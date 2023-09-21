package app.mehmaan.mobile.compose

import androidx.compose.ui.graphics.Color
import dev.reaktor.core.annotations.reaktor.Expose
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

@Expose
class Shibasis(
    val id: Int,
    val name: String,
    val marks: HashMap<String, String>
) {
    fun getFlow() = flow {
        emit(1)
    }
}
