package be.ami.maubray.brocscan

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.CheckCircle
import androidx.compose.material.icons.twotone.CloudDownload
import androidx.compose.material.icons.twotone.CloudUpload
import androidx.compose.material.icons.twotone.Delete
import androidx.compose.material.icons.twotone.QrCodeScanner
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import be.ami.maubray.brocscan.composable.RadioGroup
import be.ami.maubray.brocscan.composable.contextSnackbar
import be.ami.maubray.brocscan.model.Entry
import be.ami.maubray.brocscan.style.appColorScheme
import be.ami.maubray.brocscan.style.appTypography

@Composable
@Preview
fun App() {
	val snackbarHostState = remember { SnackbarHostState() }
	val showSnackbar = contextSnackbar(snackbarHostState)
	
	// entries radio button
	val entries = listOf(
		Entry(label = "Entrée A", scanned = 0, total = 5),
		Entry(label = "Entrée B", scanned = 0, total = 10),
		Entry(label = "Entrée C", scanned = 0, total = 15)
	)
	var entry by remember { mutableStateOf(entries.first()) }
	
	// manual entry input
	var verificationCode by remember { mutableStateOf("") }
	
	MaterialTheme(
		colorScheme = appColorScheme,
		typography = appTypography()
	) {
		Scaffold(snackbarHost = { SnackbarHost(snackbarHostState) }) {
			BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
				val iconSize = maxHeight * 0.40f
				
				Column(
					modifier = Modifier
						.background(MaterialTheme.colorScheme.background)
						.padding(20.dp)
						.safeContentPadding()
						.fillMaxSize()
				) {
					Row(
						horizontalArrangement = Arrangement.SpaceBetween,
						verticalAlignment = Alignment.Top,
						modifier = Modifier.fillMaxWidth()
					) {
						RadioGroup(
							options = entries,
							selected = entry,
							onSelected = {
								entry = it
								showSnackbar("${entry.label} selected!")
							},
							label = { it.label }
						)
						Column(horizontalAlignment = Alignment.End) {
							Text(
								"${entry.scanned}/${entry.total}",
								color = MaterialTheme.colorScheme.secondary,
								fontWeight = FontWeight.SemiBold,
								fontSize = 40.sp
							)
						}
					}
					
					Box(
						contentAlignment = Alignment.Center,
						modifier = Modifier
							.weight(1f)
							.fillMaxWidth()
					) {
						Icon(
							imageVector = Icons.TwoTone.QrCodeScanner,
							contentDescription = "Open QR Code reader",
							tint = MaterialTheme.colorScheme.primary,
							modifier = Modifier
								.requiredSize(iconSize)
								.clickable { showSnackbar("QR Code reader opened") },
						)
					}
					
					Row(
						horizontalArrangement = Arrangement.SpaceBetween,
						verticalAlignment = Alignment.Bottom,
						modifier = Modifier.fillMaxWidth()
					) {
						Row(
							verticalAlignment = Alignment.Bottom,
							horizontalArrangement = Arrangement.spacedBy(8.dp),
						) {
							OutlinedTextField(
								value = verificationCode,
								onValueChange = { verificationCode = it },
								label = { Text("n° + code") },
								singleLine = true,
							)
							Icon(
								imageVector = Icons.TwoTone.CheckCircle,
								contentDescription = "Do manual entrance",
								tint = MaterialTheme.colorScheme.tertiary,
								modifier = Modifier
									.requiredSize(50.dp)
									.clickable {
										showSnackbar("Will do manual entrance [$verificationCode]")
										verificationCode = ""
									},
							)
						}
						Column(
							horizontalAlignment = Alignment.End,
							verticalArrangement = Arrangement.spacedBy(10.dp)
						) {
							Icon(
								imageVector = Icons.TwoTone.CloudUpload,
								contentDescription = "Upload data to device",
								tint = MaterialTheme.colorScheme.primary,
								modifier = Modifier
									.requiredSize(50.dp)
									.clickable { showSnackbar("Will upload data") },
							)
							Icon(
								imageVector = Icons.TwoTone.CloudDownload,
								contentDescription = "Download data from device or cloud",
								tint = MaterialTheme.colorScheme.secondary,
								modifier = Modifier
									.requiredSize(50.dp)
									.clickable { showSnackbar("Will download data") },
							)
							Icon(
								imageVector = Icons.TwoTone.Delete,
								contentDescription = "Delete data",
								tint = MaterialTheme.colorScheme.error,
								modifier = Modifier
									.requiredSize(50.dp)
									.clickable { showSnackbar("Will delete data") },
							)
						}
					}
				}
			}
		}
	}
}
