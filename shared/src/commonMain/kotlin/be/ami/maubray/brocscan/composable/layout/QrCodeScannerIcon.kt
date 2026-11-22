package be.ami.maubray.brocscan.composable.layout

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.QrCodeScanner
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun QrCodeScannerIcon(
	size: Dp,
	onClick: () -> Unit,
) {
	Icon(
		imageVector = Icons.TwoTone.QrCodeScanner,
		contentDescription = "Open QR Code reader",
		tint = MaterialTheme.colorScheme.primary,
		modifier = Modifier
			.requiredSize(size)
			.clickable { onClick() },
	)
}
