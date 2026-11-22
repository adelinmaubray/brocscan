package be.ami.maubray.brocscan

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import be.ami.maubray.brocscan.composable.contextSnackbar
import be.ami.maubray.brocscan.composable.layout.DataActions
import be.ami.maubray.brocscan.composable.layout.EntryHeader
import be.ami.maubray.brocscan.composable.layout.ManualEntry
import be.ami.maubray.brocscan.composable.layout.QrCodeScannerIcon
import be.ami.maubray.brocscan.model.Entry
import be.ami.maubray.brocscan.style.appColorScheme
import be.ami.maubray.brocscan.style.appTypography

@Composable
@Preview
fun App(contentPadding: Dp = 0.dp) {
	val snackbarHostState = remember { SnackbarHostState() }
	val showSnackbar = contextSnackbar(snackbarHostState)
	
	// entries radio button
	val entries = listOf(
		Entry(label = "Entrée A", scanned = 0, total = 5),
		Entry(label = "Entrée B", scanned = 0, total = 10),
		Entry(label = "Entrée C", scanned = 0, total = 15)
	)
	var entry by remember { mutableStateOf(entries.first()) }
	
	MaterialTheme(
		colorScheme = appColorScheme,
		typography = appTypography()
	) {
		Scaffold(snackbarHost = { SnackbarHost(snackbarHostState) }) {
			BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
				val iconSize = maxHeight * 0.35f
				
				Column(
					modifier = Modifier
						.background(MaterialTheme.colorScheme.background)
						.padding(contentPadding)
						.fillMaxSize()
				) {
					Box(
						modifier = Modifier
							.background(Color.Black)
							.fillMaxWidth()
							.windowInsetsTopHeight(WindowInsets.statusBars)
					)
					
					Column(
						modifier = Modifier
							.padding(5.dp)
							.windowInsetsPadding(WindowInsets.safeContent.only(WindowInsetsSides.Horizontal + WindowInsetsSides.Bottom))
							.fillMaxSize()
					) {
						EntryHeader(
							entries = entries,
							selected = entry,
							onSelected = {
								entry = it
								showSnackbar("${entry.label} selected!")
							}
						)
						
						Box(
							contentAlignment = Alignment.Center,
							modifier = Modifier
								.weight(1f)
								.fillMaxWidth()
						) {
							QrCodeScannerIcon(
								size = iconSize,
								onClick = { showSnackbar("QR Code reader opened") }
							)
						}
						
						Row(
							horizontalArrangement = Arrangement.SpaceBetween,
							verticalAlignment = Alignment.Bottom,
							modifier = Modifier.fillMaxWidth()
						) {
							ManualEntry(
								onSubmit = { code ->
									showSnackbar("Will do manual entrance [$code]")
								}
							)
							DataActions(
								onUpload = { showSnackbar("Will upload data") },
								onDownload = { showSnackbar("Will download data") },
								onDelete = { showSnackbar("Will delete data") }
							)
						}
					}
				}
			}
		}
	}
}
