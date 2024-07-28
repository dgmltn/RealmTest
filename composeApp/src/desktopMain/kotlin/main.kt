import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.dgmltn.todo.ui.TodoScreen

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "RealmTest",
    ) {
        TodoScreen()
    }
}