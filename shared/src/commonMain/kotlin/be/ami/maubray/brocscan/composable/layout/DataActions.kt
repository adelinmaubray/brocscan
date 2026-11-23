package be.ami.maubray.brocscan.composable.layout

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.CloudDownload
import androidx.compose.material.icons.twotone.CloudUpload
import androidx.compose.material.icons.twotone.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DataActions(
	onUpload: () -> Unit,
	onDownload: () -> Unit,
	onDelete: () -> Unit,
) {
	Column(
		horizontalAlignment = Alignment.End,
		verticalArrangement = Arrangement.spacedBy(10.dp)
	) {
		Icon(
			imageVector = Icons.TwoTone.CloudDownload,
			contentDescription = "Download data from device or cloud",
			tint = MaterialTheme.colorScheme.primary,
			modifier = Modifier
				.requiredSize(50.dp)
				.clickable { onDownload() },
		)
		Icon(
			imageVector = Icons.TwoTone.CloudUpload,
			contentDescription = "Upload data to device",
			tint = MaterialTheme.colorScheme.secondary,
			modifier = Modifier
				.requiredSize(50.dp)
				.clickable { onUpload() },
		)
		Icon(
			imageVector = Icons.TwoTone.Delete,
			contentDescription = "Delete data",
			tint = MaterialTheme.colorScheme.error,
			modifier = Modifier
				.requiredSize(50.dp)
				.clickable { onDelete() },
		)
	}
}
