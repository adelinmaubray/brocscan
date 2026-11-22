package be.ami.maubray.brocscan

import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
	Window(
		alwaysOnTop = true,
		onCloseRequest = ::exitApplication,
		title = "Brocscan",
	) {
		App(contentPadding = 20.dp)
	}
}
