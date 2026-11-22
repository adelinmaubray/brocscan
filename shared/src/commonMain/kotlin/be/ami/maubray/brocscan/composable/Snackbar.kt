package be.ami.maubray.brocscan.composable

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import be.ami.maubray.brocscan.createDeviceVibrator
import kotlinx.coroutines.launch

@Composable
fun contextSnackbar(hostState: SnackbarHostState, vibrate: Boolean = true): (String) -> Unit {
	val scope = rememberCoroutineScope()
	val vibrator = remember { createDeviceVibrator() }
	return remember(hostState, scope, vibrator, vibrate) {
		{ message: String ->
			scope.launch {
				hostState.currentSnackbarData?.dismiss()
				if (vibrate) vibrator.vibrate()
				hostState.showSnackbar(
					message = message,
					actionLabel = "Ok",
					duration = SnackbarDuration.Short
				)
			}
		}
	}
}
