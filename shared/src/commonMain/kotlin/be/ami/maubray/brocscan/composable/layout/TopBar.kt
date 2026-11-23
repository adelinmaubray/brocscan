package be.ami.maubray.brocscan.composable.layout

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun appTopBar() = TopAppBar(
	colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
	title = {
		Text(
			"Brocscan",
			color = MaterialTheme.colorScheme.secondary,
			fontWeight = FontWeight.Bold,
			fontSize = 26.sp
		)
	},
)
