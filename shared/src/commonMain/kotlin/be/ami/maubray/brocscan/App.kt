package be.ami.maubray.brocscan

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import be.ami.maubray.brocscan.style.BrocColorScheme

@Composable
@Preview
fun App() {
	var showDialog by remember { mutableStateOf(false) }
	
	MaterialTheme(colorScheme = BrocColorScheme) {
		BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
			val iconSize = maxHeight * 0.40f
			
			Column(
				modifier = Modifier
					.background(MaterialTheme.colorScheme.background)
					.padding(20.dp)
					.safeContentPadding()
					.fillMaxSize()
			) {
				FlowRow {
					Text("Row1")
				}
				
				Box(
					contentAlignment = Alignment.Center,
					modifier = Modifier
						.weight(1f)
						.fillMaxWidth()
				) {
					Icon(
						imageVector = Icons.Default.QrCodeScanner,
						contentDescription = "Open QR Code reader",
						tint = MaterialTheme.colorScheme.primary,
						modifier = Modifier
							.requiredSize(iconSize)
							.clickable { showDialog = true },
					)
				}
				
				FlowRow {
					Text("Row3")
				}
			}
		}
		
		if (showDialog) {
			AlertDialog(
				onDismissRequest = { showDialog = false },
				title = { Text("QR Scanner") },
				text = { Text("QR scanning is not yet implemented.") },
				confirmButton = {
					TextButton(onClick = { showDialog = false }) {
						Text("OK")
					}
				}
			)
		}
	}
}
