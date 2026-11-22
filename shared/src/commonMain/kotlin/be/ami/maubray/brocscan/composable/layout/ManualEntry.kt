package be.ami.maubray.brocscan.composable.layout

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ManualEntry(
	onSubmit: (String) -> Unit,
) {
	var verificationCode by remember { mutableStateOf("") }

	Row(
		verticalAlignment = Alignment.Bottom,
		horizontalArrangement = Arrangement.spacedBy(8.dp),
	) {
		OutlinedTextField(
			value = verificationCode,
			onValueChange = { verificationCode = it },
			label = { Text("n° + code") },
			singleLine = true,
			modifier = Modifier.fillMaxWidth(0.55f),
		)
		Icon(
			imageVector = Icons.TwoTone.CheckCircle,
			contentDescription = "Do manual entrance",
			tint = MaterialTheme.colorScheme.tertiary,
			modifier = Modifier
				.requiredSize(50.dp)
				.clickable {
					onSubmit(verificationCode)
					verificationCode = ""
				},
		)
	}
}
