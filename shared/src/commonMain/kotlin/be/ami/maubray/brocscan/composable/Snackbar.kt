package be.ami.maubray.brocscan.composable

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch

@Composable
fun contextSnackbar(hostState: SnackbarHostState): (String) -> Unit {
	val scope = rememberCoroutineScope()
	return remember(hostState, scope) {
		{ message: String ->
			scope.launch {
				hostState.currentSnackbarData?.dismiss()
				hostState.showSnackbar(
					message = message,
					actionLabel = "Ok",
					duration = SnackbarDuration.Short
				)
			}
		}
	}
}
