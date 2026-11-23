package be.ami.maubray.brocscan

import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import io.github.vinceglb.filekit.FileKit

fun main() {
	FileKit.init(appId = "be.ami.maubray.brocscan")

	application {
		Window(
			alwaysOnTop = true,
			onCloseRequest = ::exitApplication,
			title = "Brocscan",
		) {
			App(contentPadding = 20.dp)
		}
	}
}
